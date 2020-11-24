package utils;

import javafx.collections.ObservableList;
import model.History;
import model.Users;
import repo.IRepo;
import repo.RepoFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {


    public static Users getCustomerFromEmail(String email) {
        Users u = null;
        IRepo repo = RepoFactory.getRepo();
        List<Users> users = repo.getAllUsers();
        for (Users user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return u;
    }

    public static String getTodaysDate() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }

}
