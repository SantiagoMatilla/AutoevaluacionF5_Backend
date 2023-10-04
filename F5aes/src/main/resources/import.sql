-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

-- Usuarios
INSERT INTO users (id, first_name, last_name, phone, email, password, repeat_password) VALUES (1, 'NombreUsuario1', 'ApellidoUsuario1', 123456789, 'usuario1@example.com', 'contraseña1', 'contraseña1');
INSERT INTO users (id, first_name, last_name, phone, email, password, repeat_password) VALUES (2, 'NombreUsuario2', 'ApellidoUsuario2', 987654321, 'usuario2@example.com', 'contraseña2', 'contraseña2');

-- Asociación de Usuario1 con ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
-- Asociación de Usuario2 con ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);


-- Bootcamps
INSERT INTO bootcamp (id, name, duration, start_date, end_date) VALUES (1, 'Bootcamp1', '3 months', '2023-01-01', '2023-04-01');
INSERT INTO bootcamp (id, name, duration, start_date, end_date) VALUES (2, 'Bootcamp2', '2 months', '2023-02-01', '2023-04-01');

-- Stacks
INSERT INTO stack (id, name) VALUES (1, 'Java');
INSERT INTO stack (id, name) VALUES (2, 'Python');

-- Skills
INSERT INTO skills (id, name, stack_id) VALUES (1, 'Java Skill 1', 1);
INSERT INTO skills (id, name, stack_id) VALUES (2, 'Java Skill 2', 1);
INSERT INTO skills (id, name, stack_id) VALUES (3, 'Python Skill 1', 2);
INSERT INTO skills (id, name, stack_id) VALUES (4, 'Python Skill 2', 2);

-- Bootcamp1 is associated with Stack1
INSERT INTO bootcamps_stacks (bootcamp_id, stack_id) VALUES (1, 1);
-- Bootcamp1 is associated with Stack2
INSERT INTO bootcamps_stacks (bootcamp_id, stack_id) VALUES (1, 2);
-- Bootcamp2 is associated with Stack1
INSERT INTO bootcamps_stacks (bootcamp_id, stack_id) VALUES (2, 1);
-- Bootcamp2 is associated with Stack2
INSERT INTO bootcamps_stacks (bootcamp_id, stack_id) VALUES (2, 2);

-- Content
INSERT INTO content (id, name, skill_id) VALUES (1, 'Bootcamp Content 1', 1);
INSERT INTO content (id, name, skill_id) VALUES (2, 'Bootcamp Content 2', 2);
