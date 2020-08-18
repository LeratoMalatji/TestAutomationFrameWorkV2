package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseUtil {

    private static Logger log = LogManager.getLogger(DatabaseUtil.class.getName());

    public static Connection getConnection() {

        Properties properties = new Properties();
        FileInputStream input = null;
        Connection con = null;

        try {
            input = new FileInputStream(new File(
                    System.getProperty("user.dir") + "/src/main/resources/DataBaseData.properties"));

        } catch (FileNotFoundException e1) {
            log.error("property file could not be be found ");
            e1.printStackTrace();
        }

        try {
            properties.load(input);
            log.info("loading property file needed for database input values");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("userName"),properties.getProperty("password"));
            log.info("Successfully connected to database");

        } catch (SQLException e) {
            log.error("Could not connect to the database");
            e.printStackTrace();
        }

        return con;
    }

}
