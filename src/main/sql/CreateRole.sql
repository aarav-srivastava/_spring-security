DELIMITER $$

CREATE PROCEDURE create_role_table()
BEGIN
    -- Check if the table exists
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'role') THEN
        -- Create the role table if it doesn't exist
        CREATE TABLE role (
            id INT PRIMARY KEY AUTO_INCREMENT,
            role_name VARCHAR(255) NOT NULL,
            description VARCHAR(255) NOT NULL
        );
    END IF;
END $$

DELIMITER ;
