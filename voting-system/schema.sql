-- Create the database
CREATE DATABASE voting_system;

-- Use the newly created database
USE voting_system;

-- Create a user for the voting system
CREATE USER 'voting_user'@'localhost' IDENTIFIED BY 'yourpassword';

-- Grant all privileges on the voting_system database to the user
GRANT ALL PRIVILEGES ON voting_system.* TO 'voting_user'@'localhost';

-- Flush privileges to apply changes
FLUSH PRIVILEGES;

-- Create a table for Users
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create a table for Candidates
CREATE TABLE candidates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    logo_url VARCHAR(255) NOT NULL
);

-- Create a table for Votes
CREATE TABLE votes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    candidate_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (candidate_id) REFERENCES candidates(id)
);
