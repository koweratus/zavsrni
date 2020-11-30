package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Preferences;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ImagesController implements Initializable {

    @FXML
    private JFXButton btnBack;
    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String serbian = "Serbian";
    private final String russian = "Russian";
    private final String englishUS = "English (US)";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)) {
            loadLang("hr");
        }
        if (preferences.getLanguage().equals(englishUK)) {
            loadLang("en");
        }     if (preferences.getLanguage().equals(russian)) {
            loadLang("ru");
        }     if (preferences.getLanguage().equals(serbian)) {
            loadLang("sr");
        } if (preferences.getLanguage().equals(englishUS)) {
            loadLang("en");
        }

    }
    public void handleButtonAction(ActionEvent event){
        if (event.getSource()==btnBack){
            try {
                FXMLLoader loader= new FXMLLoader((getClass().getResource("/fxml/sample.fxml")));
                Pane blah = loader.load();
                Controller controller = loader.getController();
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setFullScreen(false);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        btnBack.setText(bundle.getString("btnCancel"));


    }
}
