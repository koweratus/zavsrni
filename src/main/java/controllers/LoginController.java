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
import repo.IRepo;
import repo.RepoFactory;
import utils.Preferences;

import java.io.IOException;
import java.net.URL;
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
    private String croatia = "Croatian";
    private String englishUK = "English (UK)";


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)) {
            loadLang("hr");
        }
        if (preferences.getLanguage().toString().equals(englishUK)) {
            loadLang("en");
        }
    }

/*
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = StringUtils.trimToEmpty(username.getText());
        String pword = DigestUtils.shaHex(password.getText());

        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
            closeStage();
            loadMain();
            LOGGER.log(Level.INFO, "User successfully logged in {}", uname);
        }
        else {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }
*/


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
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleLoginButtonAction(ActionEvent event){
        if(event.getSource()== btnCancel){
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
        if (event.getSource()==btnLogin){
            login(event);
        }
        if (event.getSource()==btnRegister)
        {
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

    private void login(ActionEvent actionEvent){

        IRepo repo = RepoFactory.getRepo();
        String username= txtEmail.getText().toString();
        String password= txtPassword.getText().toString();

        if (repo.checkUsers(username,password)){

            try {
                //Stage appStage = new Stage();
                FXMLLoader loader= new FXMLLoader((getClass().getResource("/fxml/sample.fxml")));
                Pane blah = loader.load();
                Controller controller = (Controller) loader.getController();
                controller.GetUser(txtEmail.getText());
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else
        {
            txtEmail.getStyleClass().add("wrong-credentials");
            txtPassword.getStyleClass().add("wrong-credentials");

        }


    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        btnCancel.setText(bundle.getString("btnCancel"));
        btnRegister.setText(bundle.getString("btnRegister"));
        btnLogin.setText(bundle.getString("btnLogin"));
        dontAcc.setText(bundle.getString("dontAcc"));


    }
}

