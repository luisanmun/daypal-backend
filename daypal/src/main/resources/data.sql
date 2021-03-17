-- Los roles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

--Un user / pass: 12345678
INSERT INTO users(username,email,password) VALUES ('user1','user@gmail.com','$2a$10$1dCKuQoQqbBNCK.Rb8XQSemwqdHdVAcCTb1kUQLg2key/4VX./TvS');
INSERT INTO user_roles(user_id, role_id) VALUES ('1','1');

--Un admin / pass: 12345678
INSERT INTO users(username,email,password) VALUES ('admin1','admin@gmail.com','$2a$10$1dCKuQoQqbBNCK.Rb8XQSemwqdHdVAcCTb1kUQLg2key/4VX./TvS');
INSERT INTO user_roles(user_id, role_id) VALUES ('1','3');