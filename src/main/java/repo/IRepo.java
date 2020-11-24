package repo;


import javafx.collections.ObservableList;
import model.History;
import model.Users;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public interface IRepo {

    void insertUsers(Users u);
    List<Users> getAllUsers();
    Users getUsers(int id);
    boolean checkUsers(String email, String password);
    boolean checkEmail(String email);
    void insertHistory(History h);
    ObservableList<History> getAllHistoryForUsers(int id);


}
