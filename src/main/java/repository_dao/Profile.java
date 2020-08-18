package repository_dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.DatabaseUtil;

public class Profile {

    private static Logger log = LogManager.getLogger(Profile.class.getName());

    public List<Manager> getCredentials() {
        Manager user;
        List<Manager> users = new ArrayList<>();

        try (Connection con = DatabaseUtil.getConnection(); Statement s = con.createStatement();) {

            try (ResultSet rs = s.executeQuery("select * from EmployeeInfo");)
            {
                log.info("Querying the database");
                while (rs.next()) {
                    user = new Manager(rs.getString("email"), rs.getString("passwords"));
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            log.error("Something went wrong while querying the database");
            e.printStackTrace();
        }

        return users;
    }

}
