package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.History;
import model.Users;
import repo.IRepo;
import repo.RepoFactory;
import utils.Preferences;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private final VBox pnItems = null;
    @FXML
    private Label lbAccuracy;
    @FXML
    private Label lbLang;
    @FXML
    private Label lbEmail;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnView;

    @FXML
    private TableColumn<History, String> colAccuracy;
    @FXML
    private TableColumn<History, String> colLang;
    @FXML
    private TableColumn<History, String> colPlang;
    @FXML
    private TableColumn<History, String> colLayout;
    @FXML
    private TableColumn<History, String> colWpm;
    @FXML
    private TableColumn<History, Integer> colId;
    @FXML
    private TableColumn<History, String> colType;
    @FXML
    private TableColumn<History, String> colDate;
    @FXML
    private TableView<History> tvHistory;

    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String serbian = "Serbian";
    private final String russian = "Russian";
    private final String englishUS = "English (US)";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lbEmail.setVisible(false);
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


    void fillHistory(String email){
        IRepo repo = RepoFactory.getRepo();
        Users u = utils.Utils.getCustomerFromEmail(email);
        ObservableList<History> histories = repo.getAllHistoryForUsers(u.getUsersId());
        colAccuracy.setCellValueFactory(new PropertyValueFactory<History,String>("Accuracy"));
        colDate.setCellValueFactory(new PropertyValueFactory<History, String>("HistoryDate"));
        colLang.setCellValueFactory(new PropertyValueFactory<History, String>("Language"));
        colLayout.setCellValueFactory(new PropertyValueFactory<History, String>("KeyboardLayout"));
        colType.setCellValueFactory(new PropertyValueFactory<History, String>("TypeOfTest"));
        colWpm.setCellValueFactory(new PropertyValueFactory<History, String>("WPM"));
        colId.setCellValueFactory(new PropertyValueFactory<History, Integer>("UsersID"));
        colPlang.setCellValueFactory(new PropertyValueFactory<History, String>("ProgrammingLanguage"));
        tvHistory.setItems(histories);

    }
    public void handleButtonAction(ActionEvent event){
        if(event.getSource()== btnView) {
        System.out.println("Vidi miseca");
        }
        if (event.getSource()==btnBack){
            try {
                FXMLLoader loader= new FXMLLoader((getClass().getResource("/fxml/sample.fxml")));
                Pane blah = loader.load();
                Controller controller = loader.getController();
                controller.GetUser(lbEmail.getText());
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
    public String GetUser(String text) {
        lbEmail.setText(text);
        return text;
    }
    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        btnBack.setText(bundle.getString("btnCancel"));
//        btnView.setText(bundle.getString("btnView"));

    }
}
