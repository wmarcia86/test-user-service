package com.nissum.test.userservice.service;

import com.nissum.test.userservice.model.dto.PhoneResponseDTO;
import com.nissum.test.userservice.model.dto.UserCreateRequestDTO;
import com.nissum.test.userservice.model.dto.UserResponseDTO;
import com.nissum.test.userservice.model.dto.UserUpdateRequestDTO;
import com.nissum.test.userservice.model.entity.Phone;
import com.nissum.test.userservice.model.entity.Role;
import com.nissum.test.userservice.model.entity.User;
import com.nissum.test.userservice.repository.PhoneRepository;
import com.nissum.test.userservice.repository.RoleRepository;
import com.nissum.test.userservice.repository.UserRepository;
import com.nissum.test.userservice.security.JwtProvider;
import com.nissum.test.userservice.util.validator.ValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * UserServiceImplTest Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-24-2023
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PhoneRepository phoneRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ValidationHandler validationHandler;

    @InjectMocks
    UserServiceImpl userService;

    private final String USER_UUID_STRING = "c0a83801-8682-199d-8186-826ae3720000";
    private final String PHONE_UUID_STRING = "c0a83801-8682-199d-8186-826cbcbe0001";
    private final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWdpdGFyaW8ud2lsYmVydEBnbWFpbC5jb20iLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjc3MjI1MjQzLCJleHAiOjE2Nzk4MTcyNDN9.5v4GMjiz3zJzrWYCW0jr5lPAQsWD1XF4XMcpVE4QJLQ";
    private final String EMAIL = "sagitario.wilbert@gmail.com";
    private final String PASSWORD = "Wml.2023";
    private final String PASSWORD_ENCODED = "$2b$10$pmcqWcfNObXFb72jFGV8reOug6G47Ly0Nc2LyXKhqOb91GrX530DS";

    private final UUID USER_UUID = UUID.fromString(USER_UUID_STRING);

    private User userForTest;
    private Phone phoneForTest;
    private Role roleTest;

    private UserCreateRequestDTO userCreateRequestDTOTest;
    private UserUpdateRequestDTO userUpdateRequestDTOTest;

    private List<User> lstUserTest;
    private List<Phone> lstPhoneTest;
    private List<Role> lstRole;

    @BeforeEach
    void setUp() {

        userForTest = new User();
        userForTest.setId(USER_UUID);
        userForTest.setName("Wilbert Antonio Marcia Lanzas");
        userForTest.setEmail("sagitario.wilbert@gmail.com");
        userForTest.setPassword(PASSWORD_ENCODED);
        userForTest.setCreated(LocalDateTime.parse("2023-02-24T01:54:42.272"));
        userForTest.setModified(null);
        userForTest.setLastLogin(LocalDateTime.parse("2023-02-24T01:54:42.272"));
        userForTest.setToken(TOKEN);
        userForTest.setActive(true);

        phoneForTest = new Phone();
        phoneForTest.setId(UUID.fromString(PHONE_UUID_STRING));
        phoneForTest.setUserId(USER_UUID);
        phoneForTest.setNumber("(505) 0000-0000");
        phoneForTest.setCityCode("MG");
        phoneForTest.setCountryCode("NI");
        phoneForTest.setCreated(LocalDateTime.parse("2023-02-24T01:54:42.272"));
        phoneForTest.setModified(null);
        phoneForTest.setActive(true);

        roleTest = new Role();
        roleTest.setId(1);
        roleTest.setName("ROLE_ADMIN");
        roleTest.setDescription("Administrator Role");

        userCreateRequestDTOTest = new UserCreateRequestDTO();
        userCreateRequestDTOTest.setName("Test");
        userCreateRequestDTOTest.setEmail("test@domain.com");
        userCreateRequestDTOTest.setPassword("Tst.2023");

        userUpdateRequestDTOTest = new UserUpdateRequestDTO();
        userUpdateRequestDTOTest.setName("Test");
        userUpdateRequestDTOTest.setEmail("test@domain.com");
        userUpdateRequestDTOTest.setPassword("TsT.2023");
        userUpdateRequestDTOTest.setActive(true);

        lstUserTest = Arrays.asList(userForTest);
        lstPhoneTest = Arrays.asList(phoneForTest);
        lstRole = Arrays.asList(roleTest);

        userForTest.setRoles(lstRole);
    }

    @Test
    void createUser() {
        UserCreateRequestDTO userCreateRequestDTOTestSource = userCreateRequestDTOTest;
        Role roleSource = roleTest;

        Mockito.when(validationHandler.validateFormatEmail(userCreateRequestDTOTestSource.getEmail())).thenReturn(true);
        Mockito.when(validationHandler.validateFormatPassword(userCreateRequestDTOTestSource.getPassword())).thenReturn(true);

        Assertions.assertTrue(validationHandler.validateFormatEmail(userCreateRequestDTOTestSource.getEmail()));
        Assertions.assertTrue(validationHandler.validateFormatPassword(userCreateRequestDTOTestSource.getPassword()));

        Mockito.when(userRepository.existsUserByEmail(userCreateRequestDTOTestSource.getEmail())).thenReturn(false);
        Assertions.assertFalse(userRepository.existsUserByEmail(userCreateRequestDTOTestSource.getEmail()));

        Mockito.when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.ofNullable(roleSource));
        Role roleTarget = roleRepository.findByName("ROLE_ADMIN").get();

        Assertions.assertNotNull(roleTarget);
        Assertions.assertEquals(roleTarget, roleSource);

        Mockito.when(passwordEncoder.encode(userCreateRequestDTOTestSource.getPassword())).thenReturn(PASSWORD_ENCODED);
        String passwordEncoded = passwordEncoder.encode(userCreateRequestDTOTestSource.getPassword());

        List<Role> lstRoleSource = Arrays.asList(roleSource);

        Mockito.when(jwtProvider.generateToken(userCreateRequestDTOTestSource.getEmail(), lstRoleSource)).thenReturn(TOKEN);
        String token = jwtProvider.generateToken(userCreateRequestDTOTestSource.getEmail(), lstRoleSource);

        User userTarget = new User(userCreateRequestDTOTestSource, passwordEncoded, token, lstRoleSource);

        Mockito.when(userRepository.save(userTarget)).thenAnswer(x -> userTarget);
        userRepository.save(userTarget);

        new UserResponseDTO(userTarget, false);
    }

    @Test
    void retrieveUserById() {
        User userSource = userForTest;

        Mockito.when(userRepository.findById(USER_UUID)).thenReturn(Optional.ofNullable(userSource));
        User userTarget = userRepository.findById(USER_UUID).get();

        Assertions.assertNotNull(userTarget);
        Assertions.assertEquals(userTarget, userSource);

        List<Phone> lstPhoneSource = lstPhoneTest;

        Mockito.when(phoneRepository.findByUserId(USER_UUID))
                .thenReturn(lstPhoneSource);
        List<Phone> lstPhoneTarget = phoneRepository.findByUserId(USER_UUID);

        Assertions.assertEquals(lstPhoneTarget, lstPhoneSource);

        List<PhoneResponseDTO> lstPhoneResponseDTOSource = lstPhoneSource.stream()
                .map(phone -> new PhoneResponseDTO(phone)).collect(Collectors.toList());

        List<PhoneResponseDTO> lstPhoneResponseDTOTarget = lstPhoneTarget.stream()
                .map(phone -> new PhoneResponseDTO(phone)).collect(Collectors.toList());

        Assertions.assertEquals(lstPhoneResponseDTOTarget, lstPhoneResponseDTOSource);

        UserResponseDTO userResponseDTOSource = new UserResponseDTO(userSource, true);
        userResponseDTOSource.setPhones(lstPhoneResponseDTOSource);

        UserResponseDTO userResponseDTOTarget = new UserResponseDTO(userTarget, true);
        userResponseDTOTarget.setPhones(lstPhoneResponseDTOTarget);

        Assertions.assertEquals(userResponseDTOTarget, userResponseDTOSource);
    }

    @Test
    void retrieveAllUser() {
        List<User> lstUserSource = lstUserTest;

        Mockito.when(userRepository.findAll()).thenReturn(lstUserSource);
        List<User> lstUserTarget = userRepository.findAll();

        Assertions.assertEquals(lstUserTarget, lstUserSource);
        Assertions.assertFalse(lstUserTarget.isEmpty());

        List<UserResponseDTO> lstUserResponseDTOSource = lstUserSource.stream().map(user -> new UserResponseDTO(user, true))
                .collect(Collectors.toList());

        List<UserResponseDTO> lstUserResponseDTOTarget = lstUserTarget.stream().map(user -> new UserResponseDTO(user, true))
                .collect(Collectors.toList());

        Assertions.assertEquals(lstUserResponseDTOTarget, lstUserResponseDTOSource);

        UserResponseDTO userResponseDTOSource = new UserResponseDTO(userForTest, true);

        List<Phone> lstPhoneSource = lstPhoneTest;

        Mockito.when(phoneRepository.findByUserId(userResponseDTOSource.getId())).thenReturn(lstPhoneSource);

        lstUserResponseDTOSource.forEach(userResponseDTO -> {
            List<Phone> lstPhone = lstPhoneSource;

            List<PhoneResponseDTO> lstPhoneResponseDTO = lstPhone.stream()
                    .map(phone -> new PhoneResponseDTO(phone)).collect(Collectors.toList());

            userResponseDTO.setPhones(lstPhoneResponseDTO);
        });

        lstUserResponseDTOTarget.forEach(userResponseDTO -> {
            List<Phone> lstPhone = phoneRepository.findByUserId(userResponseDTO.getId());;

            List<PhoneResponseDTO> lstPhoneResponseDTO = lstPhone.stream()
                    .map(phone -> new PhoneResponseDTO(phone)).collect(Collectors.toList());

            userResponseDTO.setPhones(lstPhoneResponseDTO);
        });

        Assertions.assertEquals(lstUserResponseDTOTarget, lstUserResponseDTOSource);
    }

    @Test
    void updateUser() {
        User userSource = userForTest;

        UserUpdateRequestDTO userUpdateRequestDTOTestSource = userUpdateRequestDTOTest;

        Mockito.when(validationHandler.validateFormatEmail(userUpdateRequestDTOTestSource.getEmail())).thenReturn(true);
        Mockito.when(validationHandler.validateFormatPassword(userUpdateRequestDTOTestSource.getPassword())).thenReturn(true);

        Assertions.assertTrue(validationHandler.validateFormatEmail(userUpdateRequestDTOTestSource.getEmail()));
        Assertions.assertTrue(validationHandler.validateFormatPassword(userUpdateRequestDTOTestSource.getPassword()));

        Mockito.when(userRepository.findById(USER_UUID)).thenReturn(Optional.ofNullable(userSource));
        User userTarget = userRepository.findById(USER_UUID).get();

        Assertions.assertNotNull(userTarget);
        Assertions.assertEquals(userTarget, userSource);

        Mockito.when(userRepository.findByEmail(userUpdateRequestDTOTestSource.getEmail())).thenReturn(Optional.ofNullable(userSource));
        User userFound = userRepository.findByEmail(userUpdateRequestDTOTestSource.getEmail()).get();

        boolean isGenerateToken = false;

        Assertions.assertNotNull(userFound);

        if (!Objects.nonNull(userFound)) {
            isGenerateToken = true;
        }

        Mockito.when(passwordEncoder.encode(userUpdateRequestDTOTestSource.getPassword())).thenReturn(PASSWORD_ENCODED);
        String passwordEncoded = passwordEncoder.encode(userUpdateRequestDTOTestSource.getPassword());

        userTarget.setName(userUpdateRequestDTOTestSource.getName());
        userTarget.setPassword(passwordEncoded);
        userTarget.setEmail(userUpdateRequestDTOTestSource.getEmail());
        userTarget.setActive(userUpdateRequestDTOTestSource.isActive());
        userTarget.setModified(LocalDateTime.now());

        if (isGenerateToken) {
            Mockito.when(jwtProvider.generateToken(userUpdateRequestDTOTestSource.getEmail(), userTarget.getRoles())).thenReturn(TOKEN);
            String token = jwtProvider.generateToken(userUpdateRequestDTOTestSource.getEmail(), userTarget.getRoles());

            userTarget.setToken(token);
        }

        Mockito.when(userRepository.save(userTarget)).thenAnswer(x -> userTarget);
        userRepository.save(userTarget);

        new UserResponseDTO(userTarget, false);
    }

    @Test
    void deleteUser() {
        Mockito.when(userRepository.existsById(USER_UUID))
                .thenReturn(true);

        Assertions.assertTrue(userRepository.existsById(USER_UUID));

        phoneRepository.deleteByUserId(USER_UUID);

        userRepository.deleteById(USER_UUID);
    }

    @Test
    void authenticate() {
        User userSource = userForTest;

        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.ofNullable(userSource));
        User userTarget = userRepository.findByEmail(EMAIL).get();

        Assertions.assertNotNull(userTarget);
        Assertions.assertEquals(userTarget, userSource);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userTarget.getEmail(), PASSWORD));

        Mockito.when(jwtProvider.generateToken(userTarget.getEmail(), userTarget.getRoles())).thenReturn(TOKEN);
        String token = jwtProvider.generateToken(userTarget.getEmail(), userTarget.getRoles());

        userTarget.setToken(token);
        userTarget.setModified(LocalDateTime.now());
        userTarget.setLastLogin(LocalDateTime.now());

        Mockito.when(userRepository.save(userTarget)).thenAnswer(x -> userTarget);
        userRepository.save(userTarget);

        new UserResponseDTO(userTarget, false);
    }

}
