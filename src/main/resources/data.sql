-- Los roles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

--Un admin / pass: 12345678
INSERT INTO users(username,email,password,height,weight,sex) VALUES ('admin1','admin@gmail.com','$2a$10$1dCKuQoQqbBNCK.Rb8XQSemwqdHdVAcCTb1kUQLg2key/4VX./TvS',null, null,null);
INSERT INTO user_roles(user_id, role_id) VALUES ('1','3');

--Un trainer o moderator/ pass: 12345678
INSERT INTO users(username,email,password,height,weight,sex) VALUES ('mod1','mod@gmail.com','$2a$10$1dCKuQoQqbBNCK.Rb8XQSemwqdHdVAcCTb1kUQLg2key/4VX./TvS',null, null,null);
INSERT INTO user_roles(user_id, role_id) VALUES ('2','2');

--Comidas test
INSERT INTO meals(title, description, calories, category) VALUES ('Desayuno por defecto', 'Desayuno saludable para todos los publicos. Comer una manzana', 52, 'BREAKFAST');
INSERT INTO meals(title, description, calories, category) VALUES ('Almuerzo por defecto', 'Almuerzo saludable para todos los publicos. Comer 200 gr de ensalada de patata y una gelatina de 100 gr', 193, 'LUNCH');
INSERT INTO meals(title, description, calories, category) VALUES ('Merienda por defecto', 'Merienda saludable para todos los publicos. Tomar un zumo de piña', 60, 'SNACK');
INSERT INTO meals(title, description, calories, category) VALUES ('Cena por defecto', 'Cena saludable para todos los publicos. Comer 100 gr de merluza a la plancha', 93, 'DINNER');
INSERT INTO meals(title, description, calories, category) VALUES ('Tortilla francesa','Solo de un huevo y elavorar con una gota de aceite.', 104, 'DINNER');
INSERT INTO meals(title, description, calories, category) VALUES ('Ensalada','Simplemente trocear los alimentos y aliñar con un poco de aceite.', 54, 'DINNER');

--Ejercicios test
INSERT INTO exercises(title, description, lose_weight) VALUES ('Ejercicio por defecto','Dar un paseo de una hora, preferiblemente por un parque.', FALSE);
INSERT INTO exercises(title, description, lose_weight) VALUES ('Biceps curl','Hasta fallo.', FALSE);
INSERT INTO exercises(title, description, lose_weight) VALUES ('Squat','Hasta fallo.', TRUE);