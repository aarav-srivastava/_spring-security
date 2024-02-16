DELIMITER $$

CREATE PROCEDURE create_user_profile_table()
BEGIN
    -- Check if the table exists
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'user_profile') THEN
        -- Create the user_profile table if it doesn't exist
        CREATE TABLE user_profile (
            id INT PRIMARY KEY AUTO_INCREMENT,
            login_name VARCHAR(255) NOT NULL,
            pwd_hash VARCHAR(255) NOT NULL
        );
    END IF;
END $$

DELIMITER ;
