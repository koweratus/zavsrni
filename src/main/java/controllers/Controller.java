package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Preferences;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label lbLogo;
    @FXML
    private Button btnExit;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnAccount;

    @FXML
    private Button btnTutorial;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnStart;

    @FXML
    private Label lbAccount;
    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String serbian = "Serbian";
    private final String russian = "Russian";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)){
            loadLang("hr");
        }
        if (preferences.getLanguage().equals(englishUK)){
            loadLang("en");
        }      if (preferences.getLanguage().equals(serbian)){
            loadLang("sr");
        }      if (preferences.getLanguage().equals(russian)){
            loadLang("ru");
        }
    }

    public void GetUser(String user){
        lbAccount.setText(user);
    }


    public void handleClicks(ActionEvent actionEvent) {
        Preferences preferences = Preferences.getPreferences();

        if (actionEvent.getSource() == btnStart ) {

            if (preferences.getVideoResolution().equals("Fullscreen")) {
                try {
                    Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Tutorial.fxml"));
                    Scene scene = new Scene(blah);
                    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.setFullScreen(true);
                    appStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Tutorial.fxml")));
                    Pane blah = loader.load();
                    TutorialController controller = (TutorialController) loader.getController();
                    controller.GetUser(lbAccount.getText());
                    Scene scene = new Scene(blah);
                    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.setFullScreen(false);
                    appStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


        if (actionEvent.getSource() == btnTutorial) {

            FXMLLoader loader= new FXMLLoader((getClass().getResource("/fxml/Images.fxml")));
            Pane blah = null;
            try {
                blah = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImagesController controller = (ImagesController) loader.getController();
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setFullScreen(false);
            appStage.show();
        }
        if (actionEvent.getSource() == btnAccount) {

            try {
               // Stage appStage = new Stage();
                FXMLLoader loader= new FXMLLoader((getClass().getResource("/fxml/Account.fxml")));
                Pane blah = loader.load();
                AccountController controller = (AccountController) loader.getController();
                controller.initDefaultValues(lbAccount.getText());
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnHistory) {
            try {
                FXMLLoader loader= new FXMLLoader((getClass().getResource("/fxml/History.fxml")));
                Pane blah = loader.load();
                HistoryController controller = (HistoryController) loader.getController();
                String email=controller.GetUser(lbAccount.getText());
                controller.fillHistory(email);
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setFullScreen(false);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == btnSettings) {
            try {
                FXMLLoader loader= new FXMLLoader((getClass().getResource("/fxml/Settings.fxml")));
                Pane blah = loader.load();
                SettingsController controller = (SettingsController) loader.getController();
                controller.GetUser(lbAccount.getText());
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setFullScreen(false);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (actionEvent.getSource() == btnSignout) {
            try {
                Parent blah = FXMLLoader.load(getClass().getResource("/fxml/guest.fxml"));
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == btnExit) {
            System.exit(0);
        }
    }

    private void loadLang(String lang){
        locale = new Locale(lang);
        bundle=ResourceBundle.getBundle("/lang/lang",locale);
        btnAccount.setText(bundle.getString("btnAccount"));
        btnHistory.setText(bundle.getString("btnHistory"));
        btnSettings.setText(bundle.getString("btnSettings"));
        btnStart.setText(bundle.getString("btnStart"));
        btnTutorial.setText(bundle.getString("btnTutorial"));
        btnSignout.setText(bundle.getString("btnSignout"));
        btnExit.setText(bundle.getString("btnExit"));
        lbLogo.setText(bundle.getString("lblNaslov"));

    }

}
