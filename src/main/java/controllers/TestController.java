package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private Label lbAccount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void GetUser(String user){
        lbAccount.setText(user);
    }

}
