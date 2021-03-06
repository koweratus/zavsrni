package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Users;
import org.apache.commons.codec.digest.DigestUtils;
import repo.IRepo;
import repo.RepoFactory;
import utils.DataValidation;
import utils.Preferences;
import org.mindrot.jbcrypt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    @FXML
    private Label emailLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label fnLabel;
    @FXML
    private Label lnLabel;

    private FileInputStream fis;
    private TextArea textArea;
    private ImageView imageView;
    private Image image;

    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String serbian = "Serbian";
    private final String russian = "Russian";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // preference = Preferences.getPreferences();
        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)) {
            loadLang("hr");
        }
        if (preferences.getLanguage().equals(englishUK)) {
            loadLang("en");
        }    if (preferences.getLanguage().equals(russian)) {
            loadLang("ru");
        }    if (preferences.getLanguage().equals(serbian)) {
            loadLang("sr");
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
        if (event.getSource() == btnRegister) {
            register(event);

        }

    }

    private void register(ActionEvent event) {

        System.out.println("Action Button Method Passed");
        boolean emailB = DataValidation.validateEmaill(txtEmail, emailLabel);
        System.out.println(emailB);
        boolean pw = DataValidation.validatePassword(txtPassword, passLabel);
        boolean fn = DataValidation.validateName(txtFirstName, fnLabel);
        boolean ln = DataValidation.validateName(txtLastName, lnLabel);
        String email = txtEmail.getText();
        String pass = hashPassword(txtPassword.getText());
        String firstname = txtFirstName.getText();
        String lastname = txtLastName.getText();
        IRepo repo = RepoFactory.getRepo();
        System.out.println(pass);
        if (!(repo.checkEmail(email))&& !email.isEmpty() && pw && fn && emailB && ln) {
            Users u = new Users(firstname, lastname,
                    email, pass);
            repo.insertUsers(u);
            switchLogin(event);
        }

    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
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
    void switchLogin(ActionEvent event){
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
