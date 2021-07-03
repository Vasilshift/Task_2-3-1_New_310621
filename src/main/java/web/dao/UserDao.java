package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    private static int USER_COUNT;
    private List<User> users;

    private static final String URL = "jdbc:mysql://localhost:3306/test?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> index() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * from users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurName(resultSet.getString("surname"));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public User show(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurName(resultSet.getString("surname"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users VALUES(?, ?, ?)");
            preparedStatement.setInt(1, USER_COUNT++);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurName());
            preparedStatement.executeUpdate();
            System.out.println("user added in DB");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, User updateUser) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE users SET name=?, surname=? WHERE id=?");
            preparedStatement.setString(1, updateUser.getName());
            preparedStatement.setString(2, updateUser.getSurName());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROMusers WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
