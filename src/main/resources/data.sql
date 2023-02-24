-- INSERT USER
INSERT INTO SECURITY.USER (ID, NAME, EMAIL, PASSWORD, CREATED, MODIFIED, LAST_LOGIN, TOKEN, IS_ACTIVE) VALUES
    ('c0a83801-8682-199d-8186-826ae3720000',
     'Wilbert Antonio Marcia Lanzas',
     'sagitario.wilbert@gmail.com',
     '$2b$10$pmcqWcfNObXFb72jFGV8reOug6G47Ly0Nc2LyXKhqOb91GrX530DS',
     '2023-02-24 01:54:42.272',
     null,
     '2023-02-24 01:54:42.272',
     'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWdpdGFyaW8ud2lsYmVydEBnbWFpbC5jb20iLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjc3MjI1MjQzLCJleHAiOjE2Nzk4MTcyNDN9.5v4GMjiz3zJzrWYCW0jr5lPAQsWD1XF4XMcpVE4QJLQ',
     true);

-- INSERT PHONE
INSERT INTO SECURITY.PHONE (ID, USER_ID, NUMBER, CITY_CODE, COUNTRY_CODE, CREATED, MODIFIED, IS_ACTIVE) VALUES
    ('c0a83801-8682-199d-8186-826cbcbe0001',
     'c0a83801-8682-199d-8186-826ae3720000',
     '(505) 0000-0000',
     'MG',
     'NI',
     '2023-02-24 01:54:42.272',
     null,
     true);

-- INSERT ROL
INSERT INTO SECURITY.ROLE (ID, NAME, DESCRIPTION) VALUES (1, 'ROLE_ADMIN', 'Administrator Role');
INSERT INTO SECURITY.ROLE (ID, NAME, DESCRIPTION) VALUES (2, 'ROLE_USER', 'User Role');

-- INSERT USER_ROLE
INSERT INTO SECURITY.USER_ROLE (ID, USER_ID, ROLE_ID) VALUES (1, 'c0a83801-8682-199d-8186-826ae3720000', 1);