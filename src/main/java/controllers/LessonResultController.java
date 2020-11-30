package controllers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.History;
import model.Users;
import repo.IRepo;
import repo.RepoFactory;
import utils.Preferences;
import utils.Utils;


public class LessonResultController implements Initializable {

    @FXML
    public TableColumn colAcc;
    @FXML
    public TableColumn colType;
    @FXML
    public TableColumn colWpm;
    @FXML
    public TableView tvHistory;
    @FXML
    public Label filterField;
    @FXML
    public Label lblNaslov;
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton goBackButton;

    @FXML
    private JFXButton redoButton;

    @FXML
    private JFXButton nextButton;

    @FXML
    private Label resultHead;

    @FXML
    private Label resultBody;

    @FXML
    private Label speedWPM;

    @FXML
    private Label positiveWords;
    @FXML
    private Label negativeWords;

    @FXML
    private Label trueAccuracy;
    @FXML
    private Label lblTN;
    @FXML
    private Label lblTypingTime;
    @FXML
    private Label lblAcc;
    @FXML
    private Label lblSpeed;

    @FXML
    private Label timeSpent;

    @FXML
    private Label troubleKeys;

    @FXML
    private Label accuracy;
    @FXML
    private Label lblPoz;
    @FXML
    private Label lblNeg;
    @FXML
    private Label txtEmail;
    @FXML
    private Label lblN;
    private int currentLessonChoice;
    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String englishUS = "English (US)";
    private final String serbian = "Serbian";
    private final String russian = "Russian";

    @FXML
    void goHome(ActionEvent event) {
        if (txtEmail.getText().isEmpty()) {

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
    }

    @FXML
    void nextLesson(ActionEvent event) {
        if (txtEmail.getText().isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/Tutorial.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage theStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                theStage.setScene(scene);
                theStage.show();

                TutorialController controller = loader.getController();
                controller.initializeLessonChoiceAndBegin(++currentLessonChoice);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Tutorial.fxml")));
                Pane blah = loader.load();
                TutorialController controller = (TutorialController) loader.getController();
                controller.GetUser(txtEmail.getText());
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setFullScreen(false);
                appStage.show();

                controller.initializeLessonChoiceAndBegin(++currentLessonChoice);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void redoLesson(ActionEvent event) {
        if (txtEmail.getText().isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/Tutorial.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage theStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                theStage.setScene(scene);
                theStage.show();

                TutorialController controller = loader.getController();
                controller.initializeLessonChoiceAndBegin(++currentLessonChoice);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Tutorial.fxml")));
                Pane blah = loader.load();
                TutorialController controller = (TutorialController) loader.getController();
                controller.GetUser(txtEmail.getText());
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.setFullScreen(false);
                appStage.show();

                controller.initializeLessonChoiceAndBegin(currentLessonChoice);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    void initializeMyData(int totalChar, int errorCountWithBackspace, int errorCountWithoutBackspace,
                          String timeToComplete, int wordCount,
                          int currentLessonChoice, String email, String lesson, String pozitivni, String negativni) {
        try {
            Preferences preferences = Preferences.getPreferences();
            if (preferences.getLanguage().equals(croatia)) {
                loadLang("hr");
            }
            if (preferences.getLanguage().equals(englishUK)) {
                loadLang("en");
            }
            if (preferences.getLanguage().equals(russian)) {
                loadLang("ru");
            }
            if (preferences.getLanguage().equals(serbian)) {
                loadLang("sr");
            }

            System.out.println("email "+email);
            System.out.println("lesson "+lesson);
            Double timeInMin = (Double.parseDouble(timeToComplete.substring(0, 2)) + (Double.parseDouble(timeToComplete.substring(3, 5)) / 60.0));
            double tacc = (double) (103 - (errorCountWithoutBackspace * 100) / totalChar);
            double wpm;
            double acc;
            int words=totalChar/5;
            // double acc = (double) (103 - (errorCountWithBackspace * 100) / totalChar);

            if (pozitivni.equals("")) {
                acc = 0;
                negativeWords.setText(String.valueOf(words));
                positiveWords.setText("0");


            } else {
                acc = (Double.parseDouble(pozitivni) / words) * 100;
                negativeWords.setText(String.valueOf(negativni));
                positiveWords.setText(String.valueOf(pozitivni));

            }


            //  speedWPM.setText(String.format("%.0f", ((wordCount / 5) / timeInMin)));
            if (negativni.equals("")) {
                wpm = (double) ((words) / timeInMin) - 0;
                acc=100;
                negativeWords.setText("0");
                positiveWords.setText(String.valueOf(words));


            } else {
                wpm = (double) ((words) / timeInMin) - Double.parseDouble(String.valueOf(negativni));
                positiveWords.setText(String.valueOf(pozitivni));
                negativeWords.setText(String.valueOf(negativni));

            }
            trueAccuracy.setText(String.format("%.1f", acc));
            // accuracy.setText(String.format("%.1f", acc) + "%");
            timeSpent.setText(timeToComplete);
            speedWPM.setText(String.format("%.0f", wpm));

            IRepo repo = RepoFactory.getRepo();
            String lang = preferences.getLanguage().toString();
            String keyboard = preferences.getKeyboardLayout().toString();
            String pLang = preferences.getProgrammingLanguage().toString();
            String date = Utils.getTodaysDate();


            //  String lesson = controller.getLessonName();

            if (!email.isEmpty()) {
                Users c = utils.Utils.getCustomerFromEmail(email);
                Users u = new Users(c.getUsersId(), c.getFirstName(), c.getLastName(), c.getEmail(),
                        c.getPassword());
                ObservableList<History> histories = repo.getAllHistoryForUsers(c.getUsersId());
                int firstName = u.getUsersId();
                History h = new History(date, firstName, trueAccuracy.getText(), speedWPM.getText(), lang, keyboard, pLang,
                        lesson);

                System.out.println(lesson);
                System.out.println(email);

                colWpm.setCellValueFactory(new PropertyValueFactory<History, String>("WPM"));
                colAcc.setCellValueFactory(new PropertyValueFactory<History, String>("Accuracy"));
                colType.setCellValueFactory(new PropertyValueFactory<History, String>("TypeOfTest"));

                FilteredList<History> filteredData = new FilteredList<>(histories, p -> true);

                // 2. Set the filter Predicate whenever the filter changes.


                filteredData.setPredicate(person -> {

                    // If filter text is empty, display all persons.


                    // Does not match.
                    return person.getTypeOfTest().contains(lesson); // Filter matches first name.
                });


                SortedList<History> sortedData = new SortedList<>(filteredData);

                // 4. Bind the SortedList comparator to the TableView comparator.
                sortedData.comparatorProperty().bind(tvHistory.comparatorProperty());

                // 5. Add sorted (and filtered) data to the table.
                tvHistory.setItems(sortedData);

                repo.insertHistory(h);


            }

            this.currentLessonChoice = currentLessonChoice;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    String getPositiveWords(String pozitivni) {
        lblPoz.setText(pozitivni);
        lblPoz.setVisible(false);
        return pozitivni;
    }

    String getNegativeWords(String negativni) {
        lblNeg.setText(negativni);
        lblNeg.setVisible(false);
        return negativni;
    }

    String GetUser(String user) {
        txtEmail.setText(user);
        txtEmail.setVisible(false);
        return user;
    }

    String GetN(String lesson) {
        lblN.setText(lesson);
        lblN.setVisible(false);
        return lesson;
    }


    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        lblTN.setText(bundle.getString("lblTN"));
        lblTypingTime.setText(bundle.getString("lblTypingTime"));
        lblSpeed.setText(bundle.getString("lblSpeed"));
        lblAcc.setText(bundle.getString("lblAcc"));
        goBackButton.setText(bundle.getString("btnHome"));
        redoButton.setText(bundle.getString("btnRedo"));
        nextButton.setText(bundle.getString("btnNext"));
        lblNaslov.setText(bundle.getString("lblNaslov"));

    }
}

