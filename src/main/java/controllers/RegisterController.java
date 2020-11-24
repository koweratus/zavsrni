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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Users;
import repo.IRepo;
import repo.RepoFactory;
import utils.Preferences;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {

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
    private JFXButton btnCancel;

    private FileInputStream fis;
    private TextArea textArea;
    private ImageView imageView;
    private Image image;

    private ResourceBundle bundle;
    private Locale locale;
    private String croatia = "Croatian";
    private String englishUK = "English (UK)";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // preference = Preferences.getPreferences();
        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)) {
            loadLang("hr");
        }
        if (preferences.getLanguage().equals(englishUK)) {
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
            Parent parent = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
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
        if(event.getSource()== btnCancel) {
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
        if (event.getSource()==btnRegister){
            register();
        }

    }

    private void register(){
        String email = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();
        String firstname=txtFirstName.getText().toString();
        String lastname=txtLastName.getText().toString();
        IRepo repo = RepoFactory.getRepo();
        if (repo.checkEmail(email) || !email.isEmpty())
        {
            System.out.println("ahaha");
            txtEmail.getStyleClass().add("wrong-credentials");
        }else{

            Users u = new Users(firstname, lastname,
                    email, pass);
            repo.insertUsers(u);
        }

    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        btnCancel.setText(bundle.getString("btnCancel"));
        btnRegister.setText(bundle.getString("btnRegister"));
        txtFirstName.setPromptText(bundle.getString("lblName"));
        txtLastName.setPromptText(bundle.getString("lblSurname"));
        txtEmail.setPromptText(bundle.getString("lblEmail"));
        txtPassword.setPromptText(bundle.getString("lblPassword"));


    }

}
