package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Users;
import org.mindrot.jbcrypt.BCrypt;
import repo.IRepo;
import repo.RepoFactory;
import utils.Preferences;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label lbl_close;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private Label dontAcc;
    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String serbian = "Serbian";
    private final String russian = "Russian";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)) {
            loadLang("hr");
        }
        if (preferences.getLanguage().equals(englishUK)) {
            loadLang("en");
        }
        if (preferences.getLanguage().equals(serbian)) {
            loadLang("sr");
        }
        if (preferences.getLanguage().equals(russian)) {
            loadLang("ru");
        }
    }


    private void closeStage() {
        ((Stage) txtEmail.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Zavrsni rad");
            stage.setScene(new Scene(parent));
            stage.show();
            //LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleLoginButtonAction(ActionEvent event) {
        if (event.getSource() == btnCancel) {
            try {
                Parent blah = FXMLLoader.load(getClass().getResource("/fxml/guest.fxml"));
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getSource() == btnLogin) {
            login(event);
        }
        if (event.getSource() == btnRegister) {
            try {
                Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Register.fxml"));
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void login(ActionEvent actionEvent) {

        IRepo repo = RepoFactory.getRepo();
        Users u = utils.Utils.getCustomerFromEmail(txtEmail.getText());
        String username = txtEmail.getText();
        String password = txtPassword.getText();
        if (repo.checkUsers(username, password)) {
                try {
                    //Stage appStage = new Stage();
                    FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/sample.fxml")));
                    Pane blah = loader.load();
                    Controller controller = loader.getController();
                    controller.GetUser(txtEmail.getText());
                    Scene scene = new Scene(blah);
                    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        } else {
            txtEmail.getStyleClass().add("wrong-credentials");
            txtPassword.getStyleClass().add("wrong-credentials");

        }


    }

    private void checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            System.out.println("The password matches.");
        else
            System.out.println("The password does not match.");
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        btnCancel.setText(bundle.getString("btnCancel"));
        btnRegister.setText(bundle.getString("btnRegister"));
        btnLogin.setText(bundle.getString("btnLogin"));
        dontAcc.setText(bundle.getString("dontAcc"));
        txtPassword.setText(bundle.getString("lblPassword"));
        txtEmail.setText(bundle.getString("lblEmail"));


    }
}

