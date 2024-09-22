-- Usa la base de datos deseada
USE test;

-- Crear la tabla de animales
CREATE TABLE animals (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  animal_name VARCHAR(255) NOT NULL,
  animal_type ENUM('DOG', 'CAT') NOT NULL,
  age INT NOT NULL,
  breed VARCHAR(255) NOT NULL,
  sex ENUM('MALE', 'FEMALE') NOT NULL,
  animal_weight FLOAT NOT NULL,
  animal_description VARCHAR(250),
  adoption_status ENUM('ADOPTED', 'IN ADOPTION', 'AWAITING ADOPTION') NOT NULL,
  date_of_admission DATE
);

-- Crear la tabla de usuarios
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone_number VARCHAR(20),
  user_address VARCHAR(255) NOT NULL
);

-- Crear la tabla de adopciones
CREATE TABLE adoptions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  animal_id BIGINT,
  user_id BIGINT,
  adoption_date DATE NOT NULL,
  observations VARCHAR(250),
  FOREIGN KEY (adopted_animal_id) REFERENCES animals(id),
  FOREIGN KEY (adopting_user_id) REFERENCES users(id)
);

-- Crear la tabla de administradores
CREATE TABLE admins (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE
);
