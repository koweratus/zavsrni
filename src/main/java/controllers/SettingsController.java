package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controllers.Controller;
import utils.Preferences;
import utils.Utils;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsController {
    @FXML
    private ChoiceBox cbVideo;
    @FXML
    private ChoiceBox cbProgramLang;
    @FXML
    private ComboBox cbLanguage;
    @FXML
    private ComboBox cbKeyboardLayout;
    @FXML
    private Label lbEmail;
    @FXML
    private Button btn;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    @FXML
    private Label lblVid;
    @FXML
    private Label lblPlang;
    @FXML
    private Label lblLanguage;
    @FXML
    private Label lblKbLayout;

    @FXML
    private Label lblSettings;
    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String englishUS = "English (US)";
    private final String serbian = "Serbian";
    private final String russian = "Russian";

    private final ObservableList<String> VideoResouliton = FXCollections
            .observableArrayList("Fullscreen", "Windowed");
    private final ObservableList<String> ProgrammingLanguage = FXCollections
            .observableArrayList("C#", "C++", "Java", "JavaScript");
    private final ObservableList<String> Language = FXCollections
            .observableArrayList(croatia, russian, serbian, englishUK, englishUS);
    private final ObservableList<String> KeyboardLayout = FXCollections
            .observableArrayList("QWERTY", "Colemak", "Dvorak");
    private final ObservableList<String> KeyboardLayoutNormal = FXCollections
            .observableArrayList("QWERTZ");
    private final ObservableList<String> KeyboardLayoutRus = FXCollections
            .observableArrayList("QWERTY");


    @FXML
    private void initialize() {
        lbEmail.setVisible(false);
        cbVideo.setItems(VideoResouliton);
        cbProgramLang.setItems(ProgrammingLanguage);
        cbLanguage.setItems(Language);
        initDefaultValues();
        language();
    }

    void initDefaultValues() {
        Preferences preferences = Preferences.getPreferences();
        cbVideo.setValue(String.valueOf(preferences.getVideoResolution()));
        cbLanguage.setValue(String.valueOf(preferences.getLanguage()));
        cbProgramLang.setValue(String.valueOf(preferences.getProgrammingLanguage()));
        cbKeyboardLayout.setValue(String.valueOf(preferences.getKeyboardLayout()));
        cbSelection();

    }

    @FXML
    void cbSelection() {

        cbLanguage.setOnAction(e -> {
            String lang = (String) cbLanguage.getSelectionModel().getSelectedItem();
            System.out.println(lang);
            if (lang.equals(croatia) || lang.equals(serbian) || lang.equals(englishUK)) {
                cbKeyboardLayout.setValue("QWERTZ");
                cbKeyboardLayout.setItems(KeyboardLayoutNormal);

            } else if (lang.equals(englishUS)) {
                cbKeyboardLayout.setValue("QWERTY");
                cbKeyboardLayout.setItems(KeyboardLayout);
            } else if (lang.equals(russian)) {
                cbKeyboardLayout.setValue("QWERTY");
                cbKeyboardLayout.setItems(KeyboardLayoutRus);

            }

        });

    }

    private void language() {

        if (cbLanguage.getValue().toString().equals("Croatian")) {
            loadLang("hr");
        }
        else if (cbLanguage.getValue().toString().equals("English (UK)")) {
            loadLang("en");
        }
        else if (cbLanguage.getValue().toString().equals("Russian")){
            loadLang("ru");
        }
        else if (cbLanguage.getValue().toString().equals("Serbian")){
            loadLang("sr");
        }
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        btnBack.setText(bundle.getString("btnBack"));
        btnSave.setText(bundle.getString("btnSave"));
        lblVid.setText(bundle.getString("lblVid"));
        lblPlang.setText(bundle.getString("lblPlang"));
        lblLanguage.setText(bundle.getString("lblLanguage"));
        lblKbLayout.setText(bundle.getString("lblKbLayout"));
        lblSettings.setText(bundle.getString("lblSettings"));
    }

    public void GetUser(String user) {
        lbEmail.setText(user);
    }

    @FXML
    void handleSave(ActionEvent event) {
        String videoResolution = (String) cbVideo.getValue();
        String keyboardLayout = (String) cbKeyboardLayout.getValue();
        String language = (String) cbLanguage.getValue();
        String programmingLanguage = (String) cbProgramLang.getValue();
        Preferences preferences = Preferences.getPreferences();
        preferences.setVideoResolution(videoResolution);
        preferences.setKeyboardLayout(keyboardLayout);
        preferences.setProgrammingLanguage(programmingLanguage);
        preferences.setLanguage(language);
        Preferences.writePreferenceToFile(preferences);
    }

    @FXML
    void handleBack(ActionEvent event) {
        if (lbEmail.getText().isEmpty()) {

            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/guest.fxml")));
                Pane blah = loader.load();
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setFullScreen(false);
                appStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/sample.fxml")));
                Pane blah = loader.load();
                Controller controller = loader.getController();
                controller.GetUser(lbEmail.getText());
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setFullScreen(false);
                appStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
