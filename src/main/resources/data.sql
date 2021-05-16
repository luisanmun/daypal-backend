-- Los roles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

--Un user / pass: 12345678
INSERT INTO users(username,email,password,height,weight,sex) VALUES ('user1','user@gmail.com','$2a$10$1dCKuQoQqbBNCK.Rb8XQSemwqdHdVAcCTb1kUQLg2key/4VX./TvS', 170,60,true);
INSERT INTO user_roles(user_id, role_id) VALUES ('1','1');

--Un admin / pass: 12345678
INSERT INTO users(username,email,password,height,weight,sex) VALUES ('admin1','admin@gmail.com','$2a$10$1dCKuQoQqbBNCK.Rb8XQSemwqdHdVAcCTb1kUQLg2key/4VX./TvS',null, null,null);
INSERT INTO user_roles(user_id, role_id) VALUES ('2','3');

--Un trainer o moderator/ pass: 12345678
INSERT INTO users(username,email,password,height,weight,sex) VALUES ('mod1','mod@gmail.com','$2a$10$1dCKuQoQqbBNCK.Rb8XQSemwqdHdVAcCTb1kUQLg2key/4VX./TvS',null, null,null);
INSERT INTO user_roles(user_id, role_id) VALUES ('3','2');

--Comidas test
INSERT INTO meals(title, description, calories, category) VALUES ('Tortilla francesa','Solo de un huevo y elavorar con una gota de aceite.', 104, 'DINNER');
INSERT INTO meals(title, description, calories, category) VALUES ('Ensalada','Simplemente trocear los alimentos y aliñar con un poco de aceite.', 54, 'DINNER');

--Ejercicios test
INSERT INTO exercises(title, description, lose_weight) VALUES ('Biceps curl','Hasta fallo.', FALSE);
INSERT INTO exercises(title, description, lose_weight) VALUES ('Squat','Hasta fallo.', TRUE);