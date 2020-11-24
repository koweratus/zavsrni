package controllers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
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


/**
 * FXML Controller class
 *
 * @author Anay
 */
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
    }

    @FXML
    void redoLesson(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Tutorial.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage theStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();

            TutorialController controller = loader.getController();
            controller.initializeLessonChoiceAndBegin(currentLessonChoice);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    void initializeMyData(int totalChar, int errorCountWithBackspace, int errorCountWithoutBackspace,
                          String timeToComplete, int wordCount,
                          int currentLessonChoice, String email, String lesson, String pozitivni,String negativni) {
        try {
            System.out.println(pozitivni);

            Double timeInMin = (Double.parseDouble(timeToComplete.substring(0, 2)) + (Double.parseDouble(timeToComplete.substring(3, 5)) / 60.0));
            double tacc = (double) (103 - (errorCountWithoutBackspace * 100) / totalChar);
           // double acc = (double) (103 - (errorCountWithBackspace * 100) / totalChar);

            double acc= ((Double.parseDouble(String.valueOf(pozitivni)))/wordCount)*100;
            positiveWords.setText(String.valueOf(pozitivni));
            negativeWords.setText(String.valueOf(negativni));
          //  speedWPM.setText(String.format("%.0f", ((wordCount / 5) / timeInMin)));
            speedWPM.setText(String.format("%.0f", ((wordCount / timeInMin)-(Double.parseDouble(negativni)))));
            trueAccuracy.setText(String.format("%.1f", tacc));
            accuracy.setText(String.format("%.1f", acc) + "%");
            timeSpent.setText(timeToComplete);
            IRepo repo = RepoFactory.getRepo();
            Preferences preferences = Preferences.getPreferences();
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
                History h = new History(date, firstName, accuracy.getText(), speedWPM.getText(), lang, keyboard, pLang, lesson);

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
        return negativni;    }
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
}

