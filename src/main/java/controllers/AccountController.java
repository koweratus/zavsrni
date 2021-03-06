package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Users;
import repo.IRepo;
import repo.RepoFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;
import utils.Preferences;

public class AccountController implements Initializable {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSurname;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label lbAccount;
    private String password;
    @FXML
    private Label lblName;
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPassword;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    private ResourceBundle bundle;
    private Locale locale;
    private String croatia = "Croatian";
    private String englishUK = "English (UK)";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initDefaultValues(String email) {
        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)) {
            loadLang("hr");
        }
        if (preferences.getLanguage().equals(englishUK)) {
            loadLang("en");
        }
        IRepo repo = RepoFactory.getRepo();
        Users c = utils.Utils.getCustomerFromEmail(email);
        Users u = new Users(c.getUsersId(), c.getFirstName(), c.getLastName(), c.getEmail(),
                c.getPassword());

        String firstName = u.getFirstName();
        String finePerDay = u.getLastName();
        String username = u.getEmail();
        password = u.getPassword();
        setPassword(password);


        lbAccount.setText(String.valueOf(username));
        txtName.setText(String.valueOf(firstName));
        txtSurname.setText(String.valueOf(finePerDay));
        txtEmail.setText(String.valueOf(username));
        txtPassword.setText(String.valueOf(password));


    }

    public void setPassword(String password) {
        if (password.length() < 16) {
            this.password = DigestUtils.shaHex(password);
        } else
            this.password = password;
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/sample.fxml")));
            Pane blah = loader.load();
            Controller controller = (Controller) loader.getController();
            controller.GetUser(txtEmail.getText());
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setFullScreen(false);
            appStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void handleSave(ActionEvent actionEvent) {
        IRepo repo = RepoFactory.getRepo();
        Users c = utils.Utils.getCustomerFromEmail(txtEmail.getText());
        Users u = new Users(c.getUsersId(), c.getFirstName(), c.getLastName(), c.getEmail(),
                c.getPassword());


        String name = txtName.getText();
        c.setFirstName(name);
        System.out.println(name);
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        lblEmail.setText(bundle.getString("lblEmail"));
        lblName.setText(bundle.getString("lblName"));
        lblSurname.setText(bundle.getString("lblSurname"));
        lblPassword.setText(bundle.getString("lblPassword"));
        btnBack.setText(bundle.getString("btnBack"));


    }
}
