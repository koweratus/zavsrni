package repo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import model.History;
import model.Users;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepo implements IRepo {
    private static final String INSERT_USERS = "{ CALL  INSERT_USERS (?,?,?,?)}";
    private static final String GET_ALL_USERS = "{ CALL  GET_ALL_USERS ()}";
    private static final String GET_USERS = "{ CALL  GET_USERS (?)}";
    private static final String GET_HISTORY_FOR_USERS = "{ CALL  GET_HISTORY_FOR_USERS (?)}";
    private static final String INSERT_HISTORY = "{ CALL  INSERT_History (?,?,?,?,?,?,?,?)}";

    @Override
    public void insertUsers(Users u) {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement statement = con.prepareCall(INSERT_USERS)) {
            statement.setString(1, u.getFirstName());
            statement.setString(2, u.getLastName());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPassword());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try {
            Connection con = dataSource.getConnection();
            CallableStatement statement = con.prepareCall(GET_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                InputStream is;
                users.add(new Users(resultSet.getInt("IDUsers"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password")));


            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Users getUsers(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_USERS)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Users(
                            resultSet.getInt("IDUsers"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getString("Password")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkUsers(String email, String password) {
        List<Users> users = getAllUsers();
        for (Users user : users) {
            if (user.getEmail().equals(email) && BCrypt.checkpw(password,user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        List<Users> customers=getAllUsers();
        for (Users customer : customers) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void insertHistory(History h) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();

             CallableStatement stmt = con.prepareCall(INSERT_HISTORY)) {
            stmt.setString(1, h.getHistoryDate());
            stmt.setInt(2, h.getUsersID());
            stmt.setString(3, h.getWPM());
            stmt.setString(4, h.getAccuracy());
            stmt.setString(5, h.getLanguage());
            stmt.setString(6, h.getKeyboardLayout());
            stmt.setString(7, h.getTypeOfTest());
            stmt.setString(8, h.getProgrammingLanguage());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<History> getAllHistoryForUsers(int id) {
        ObservableList<History> histories = FXCollections.observableArrayList();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_HISTORY_FOR_USERS)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    histories.add(
                            new History(
                                    resultSet.getInt("IDHistory"),
                                    resultSet.getString("HistoryDate"),
                                    resultSet.getInt("UsersID"),
                                    resultSet.getString("WPM"),
                                    resultSet.getString("Accuracy"),
                                    resultSet.getString("Language"),
                                    resultSet.getString("KeyboardLayout"),
                                    resultSet.getString("ProgrammingLanguage"),
                                    resultSet.getString("TypeOfTest"))
                    );
                }
                return histories;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return histories;
    }


}
