package com.nissum.test.userservice.service;

import com.nissum.test.userservice.model.dto.*;
import com.nissum.test.userservice.model.entity.Phone;
import com.nissum.test.userservice.model.entity.Role;
import com.nissum.test.userservice.model.entity.User;
import com.nissum.test.userservice.repository.PhoneRepository;
import com.nissum.test.userservice.repository.RoleRepository;
import com.nissum.test.userservice.repository.UserRepository;
import com.nissum.test.userservice.security.JwtProvider;
import com.nissum.test.userservice.util.constants.MessageEnum;
import com.nissum.test.userservice.util.exception.NotRecordsException;
import com.nissum.test.userservice.util.exception.RoleNotFoundException;
import com.nissum.test.userservice.util.exception.UserNotFoundException;
import com.nissum.test.userservice.util.exception.ValidationException;
import com.nissum.test.userservice.util.validator.ValidationHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * UserServiceImpl Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PhoneRepository phoneRepository;

    private RoleRepository roleRepository;

    private JwtProvider jwtProvider;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private ValidationHandler validationHandler;

    public UserServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository, RoleRepository roleRepository,
                           JwtProvider jwtProvider, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                           ValidationHandler validationHandler) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.roleRepository = roleRepository;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.validationHandler = validationHandler;
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) throws ValidationException {

        if (!validationHandler.validateFormatEmail(userCreateRequestDTO.getEmail())) {
            throw new ValidationException(String.format(MessageEnum.INVALID_EMAIL_FORMAT.getMessage(), userCreateRequestDTO.getEmail()));
        }

        if (!validationHandler.validateFormatPassword(userCreateRequestDTO.getPassword())) {
            throw new ValidationException(String.format(MessageEnum.INVALID_PASSORD_FORMAT.getMessage(), userCreateRequestDTO.getPassword()));
        }

        if (userRepository.existsUserByEmail(userCreateRequestDTO.getEmail())) {
            throw new ValidationException(String.format(MessageEnum.EMAIL_EXISTS.getMessage(), userCreateRequestDTO.getEmail()));
        }

        Role role = roleRepository.findByName("ROLE_ADMIN").orElse(null);

        if (!Objects.nonNull(role)) {
            throw new RoleNotFoundException(String.format(MessageEnum.ROLE_NOT_FOUND.getMessage(), "ROLE_ADMIN"));
        }

        String passwordEncoded = passwordEncoder.encode(userCreateRequestDTO.getPassword());
        List<Role> lstRoles = Arrays.asList(role);
        String token = jwtProvider.generateToken(userCreateRequestDTO.getEmail(), lstRoles);

        User user = new User(userCreateRequestDTO, passwordEncoded, token, lstRoles);

        userRepository.save(user);
        userRepository.flush();

        List<PhoneCreateRequestDTO> lstPhoneCreateRequestDTO = userCreateRequestDTO.getPhones();

        final UUID userId = user.getId();
        List<Phone> lstPhone = lstPhoneCreateRequestDTO.stream()
                .map(phoneCreateRequestDTO -> new Phone(userId, phoneCreateRequestDTO)).collect(Collectors.toList());

        phoneRepository.saveAll(lstPhone);
        phoneRepository.flush();

        return new UserResponseDTO(user, false);
    }

    @Override
    public UserResponseDTO retrieveUserById(UUID userId) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElse(null);

        if (!Objects.nonNull(user)) {
            throw new UserNotFoundException(String.format(MessageEnum.USER_NOT_FOUND.getMessage(), userId));
        }

        List<Phone> lstPhone = phoneRepository.findByUserId(userId);

        List<PhoneResponseDTO> lstPhoneResponseDTO = lstPhone.stream()
                .map(phone -> new PhoneResponseDTO(phone)).collect(Collectors.toList());

        UserResponseDTO userResponseDTO = new UserResponseDTO(user, true);
        userResponseDTO.setPhones(lstPhoneResponseDTO);

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> retrieveAllUser() throws NotRecordsException {

        List<User> lstUser = userRepository.findAll();

        if (lstUser.isEmpty()) {
            throw new NotRecordsException(MessageEnum.NOT_RECORDS.getMessage());
        }

        List<UserResponseDTO> lstUserResponseDTO = lstUser.stream().map(user -> new UserResponseDTO(user, true))
                .collect(Collectors.toList());

        lstUserResponseDTO.forEach(userResponseDTO -> {
            List<Phone> lstPhone = phoneRepository.findByUserId(userResponseDTO.getId());

            List<PhoneResponseDTO> lstPhoneResponseDTO = lstPhone.stream()
                    .map(phone -> new PhoneResponseDTO(phone)).collect(Collectors.toList());

            userResponseDTO.setPhones(lstPhoneResponseDTO);
        });

        return lstUserResponseDTO;
    }

    @Override
    public UserResponseDTO updateUser(UUID userId, UserUpdateRequestDTO userUpdateRequestDTO)
            throws ValidationException, UserNotFoundException {

        if (!validationHandler.validateFormatEmail(userUpdateRequestDTO.getEmail())) {
            throw new ValidationException(String.format(MessageEnum.INVALID_EMAIL_FORMAT.getMessage(), userUpdateRequestDTO.getEmail()));
        }

        if (!validationHandler.validateFormatPassword(userUpdateRequestDTO.getPassword())) {
            throw new ValidationException(String.format(MessageEnum.INVALID_PASSORD_FORMAT.getMessage(), userUpdateRequestDTO.getPassword()));
        }

        User user = userRepository.findById(userId).orElse(null);

        if (!Objects.nonNull(user)) {
            throw new UserNotFoundException(String.format(MessageEnum.USER_NOT_FOUND.getMessage(), userId));
        }

        User userFound = userRepository.findByEmail(userUpdateRequestDTO.getEmail()).orElse(null);

        boolean isGenerateToken = false;

        if (Objects.nonNull(userFound) && !user.equals(userFound)) {
            throw new ValidationException(String.format(MessageEnum.EMAIL_EXISTS.getMessage(), userUpdateRequestDTO.getEmail()));
        }

        if (!Objects.nonNull(userFound)) {
            isGenerateToken = true;
        }

        String passwordEncoded = passwordEncoder.encode(userUpdateRequestDTO.getPassword());

        user.setName(userUpdateRequestDTO.getName());
        user.setPassword(passwordEncoded);
        user.setEmail(userUpdateRequestDTO.getEmail());
        user.setActive(userUpdateRequestDTO.isActive());
        user.setModified(LocalDateTime.now());

        if (isGenerateToken) {
            String token = jwtProvider.generateToken(userUpdateRequestDTO.getEmail(), user.getRoles());
            user.setToken(token);
        }

        userRepository.save(user);
        userRepository.flush();

        List<Phone> phones = phoneRepository.findByUserId(userId);

        List<PhoneUpdateRequestDTO> phonesRequest = userUpdateRequestDTO.getPhones();

        List<Phone> phonesUpdate = new ArrayList<>();

        phonesRequest.forEach(phoneRequest -> {
            if (!Objects.nonNull(phoneRequest.getId())) {
                phonesUpdate.add(new Phone(userId, phoneRequest));
            } else {
                Phone phoneFound = phones.stream().filter(phone -> phone.getId().equals(phoneRequest.getId()))
                        .findFirst().orElse(null);

                if (Objects.nonNull(phoneFound)) {
                    phoneFound.updateValues(phoneRequest);

                    phonesUpdate.add(phoneFound);
                } else {
                    phonesUpdate.add(new Phone(userId, phoneRequest));
                }
            }
        });

        phoneRepository.saveAll(phonesUpdate);
        phoneRepository.flush();

        return new UserResponseDTO(user, false);
    }

    @Override
    public int deleteUser(UUID userId) throws UserNotFoundException {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format(MessageEnum.USER_NOT_FOUND.getMessage(), userId));
        }

        phoneRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);

        userRepository.flush();

        return 1;
    }

    @Override
    public UserResponseDTO authenticate(String email, String password) throws AuthenticationException {

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(MessageEnum.AUTHENTICATION_ERROR.getMessage()));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), password));

        String token = jwtProvider.generateToken(user.getEmail(), user.getRoles());

        user.setToken(token);
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);
        userRepository.flush();

        return new UserResponseDTO(user, false);
    }

}
