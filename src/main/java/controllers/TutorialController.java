package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.fxmisc.richtext.InlineCssTextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TutorialController implements Initializable {

    @FXML
    private ComboBox<String> lessonChoiceBox;

    @FXML
    private CheckBox soundCheckBox;

    @FXML
    private Label ETLabel;
    @FXML
    private Label lblPoz;
    @FXML
    private Label lblNeg;

    @FXML
    private InlineCssTextArea displayArea;

    @FXML
    private JFXTextArea textInputArea;

    @FXML
    private Button timerButton;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton goButton;

    @FXML
    private Button goToResultButton;
    @FXML
    private Label lblN;
    @FXML
    private Label txtEmail;
    @FXML
    private ComboBox cbDiff;

    private ObservableList<String> Beginner = FXCollections
            .observableArrayList("Beginner 1", "Beginner 2");
    private ObservableList<String> Advanced = FXCollections
            .observableArrayList("Advanced 1", "Advanced 2");
    private ObservableList<String> Difficulty = FXCollections
            .observableArrayList("Beginner", "Advanced");

    //progress tracking variables

    private int errorCountWithoutBackspace;


    private int errorCountWithBackspace;

    private int totalChar;

    private char expectedKey;

    String timeToComplete;

    private char typedKey;

    int indexOfLine = 0;

    String line;

    int wordCount = -1;


    //Timer variables
    Timeline timeline;

    int mins = 0, secs = 0, millis = 0;

    boolean sos = true;

    boolean timerStarted = false;

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
                Controller controller = loader.getController();
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
    void onPressGo(ActionEvent event) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("F:\\Downloads\\typing-tutor-JavaFX-master\\typing-tutor-JavaFX-master\\JavaProject\\files\\" + lessonChoiceBox.getSelectionModel().getSelectedItem() + ".txt")));
            errorCountWithBackspace = 0;
            errorCountWithoutBackspace = 0;
            totalChar = 0;
            indexOfLine = 0;
            displayArea.setStyle("-fx-font-size: 30px; ");
            displayArea.deleteText(0, displayArea.getLength());//clears the display area.
            displayArea.clearStyle(0, displayArea.getLength());
            textInputArea.setText(""); //clears the inputTextArea.
            textInputArea.requestFocus();//brings focus on the textInputArea.


            textInputArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    if (indexOfLine == -1) {
                        indexOfLine = 0;

                    }

                    textInputArea.setId("normal");
                    expectedKey = line.charAt(indexOfLine);
                    String s = line.substring(indexOfLine);

                    typedKey = event.getCharacter().charAt(0);

                    System.out.println("typed : " + textInputArea.getText() + " index:" + indexOfLine + " words count:" + wordCount + " total char:" + totalChar + " typedKey: " + typedKey + " expected word:" + s);
                    String s1 = displayArea.getText();
                    String s2 = textInputArea.getText();
                    Pattern p = Pattern.compile("[a-zA-Z]*[^\\s]");
                    //(\w+)[^\s]+
                    //[a-zA-Z]+
                    Matcher m1 = p.matcher(s1);
                    Matcher m2 = p.matcher(s2);
                    int pozitvni = 0;
                    int negativni = 0;
             /*       String str = displayArea.getText();
                    String rts = textInputArea.getText();
                    System.out.println("Words from string \"" + s1 + "\" : ");
                    for(int i=0; i<=str.length()-1; i++) {

                        System.out.println(rts.charAt(i));
                    }*/

                        wordCount=s1.split("\\s+").length;
                        System.out.println("RICI:"+wordCount);
                    System.out.println("Words from string \"" + s1 + "\" : ");
                    while (m1.find() && m2.find()) {
                     //   System.out.println("Zadane rici: " + m1.group() + " utipkane rici: " + m2.group());
                        displayArea.setStyle(m1.start(), m1.end(), "-fx-font-weight: bold;");
                        if (m1.group().equals(m2.group())) {
                            pozitvni++;
                            System.out.println("pozitivni:" + pozitvni);
                            lblPoz.setText(String.valueOf(pozitvni));
                        } else if (!(m1.group().equals(m2.group())) && typedKey == ' ') {
                            negativni++;
                            System.out.println("negativni:" + negativni);
                            lblNeg.setText(String.valueOf(negativni));

                        }

                    }



                    //DEBUG STATEMENT System.out.println("expected : " + expectedKey);

                    //check if timer started and start if it is not.
                    if (!timerStarted) {
                        timerStarted = true;
                        timerButton.fire();
                    }

                    //if the last letter of the shown line is typed, read the next line of the lesson
                    if (indexOfLine == line.length() - 1) {
                 //       System.out.println("typed : " + textInputArea.getText() + " index:" + indexOfLine);

                        //DEBUG STATEMENT   System.out.println("typed : " + typedKey);
                     //   System.out.println("typed : " + textInputArea.getText());
                        //check the last character
                        if (typedKey != expectedKey) {

                            textInputArea.setId("warn");//warns the user by setting background red.
                            // errorCountWithBackspace++;
                            errorCountWithoutBackspace++;


                        } else if ((event.getCharacter().equals("\u0008")) && typedKey == expectedKey) {
                            errorCountWithBackspace--;
                        }
                        //DEBUG STATEMENT       System.out.println("false");

                        try {
                            //if file is not completely read
                            if ((line = reader.readLine()) != null) {
                                displayArea.appendText(line);
                                totalChar += line.length();
                                //wordCount += countSpaces(line);
                                textInputArea.setText("");
                            }
                            //end of file reached
                            else {
                                timerButton.fire(); //timer is paused.
                                timeToComplete = ETLabel.getText(); // final time reading is taken and the end of the lesson.
                                //displayArea.setText(Integer.toString(totalChar));
                                //close the lesson file.
                                reader.close();
                                //lesson finished - switch the scene to the result page.
                                goToResultButton.fire();
                            }
                            //In any of the above cases, set the index of line to zero - for the next line or the next lesson.
                            indexOfLine = 0;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    //if not last character and if key pressed is not backspace.
                    else if (!(event.getCharacter().equals("\u0008"))) {
//DEBUG STATEMENT       System.out.println("typed : " + typedKey);
                        if (typedKey != expectedKey) {

                            textInputArea.setId("warn");
                            errorCountWithBackspace++;
                            errorCountWithoutBackspace++;

//DEBUG STATEMENT           System.out.println("false");
                        }
//DEBUG STATEMENT       System.out.println("true");
                        indexOfLine++;
                    }
                    // if backspace pressed, decrements the indexOfLine and only the errorCountWithBackspace.
                    else if (event.getCharacter().equals("\u0008") && textInputArea.getText() != null) {
                        indexOfLine--;
                        if (errorCountWithBackspace > 0)
                            errorCountWithBackspace--;
                    }
                    //call the showKeyPressed method to show key pressed in GUI Keyboard.
                }
            });

            //First line is displayed.
            if ((line = reader.readLine()) != null) {
                displayArea.appendText(line);
                totalChar += line.length();
                // wordCount += countSpaces(line);
//DEBUG STATEMENT System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    int countSpaces(String myLine) {
        int counter = 0;
        for (int i = 0; i < myLine.length(); i++) {
            if (myLine.charAt(i) == ' ') {
                counter++;
            }
        }
        return counter;
    }

    //method to change the text of the Elapsed Time label.
    void change() {
        if (millis == 1000) {
            secs++;
            millis = 0;
        }
        if (secs == 60) {
            mins++;
            secs = 0;
        }
        ETLabel.setText((((mins / 10) == 0) ? "0" : "") + mins + ":"
                + (((secs / 10) == 0) ? "0" : "") + secs);
        millis++;
    }

    void GetUser(String user) {
        txtEmail.setText(user);
        txtEmail.setVisible(false);
    }
    void getPositiveWords(String pozitivni){
        lblPoz.setText(pozitivni);
        lblPoz.setVisible(false);
    }   void getNegativeWords(String pozitivni){
        lblNeg.setText(pozitivni);
        lblNeg.setVisible(false);
    }
    @FXML
    void switchSceneToResultPage(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/LessonResult.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            LessonResultController controller = loader.getController();
            int currentLessonChoice = lessonChoiceBox.getSelectionModel().getSelectedIndex();
            String email = controller.GetUser(txtEmail.getText());
            String n = controller.GetN(lblN.getText());
            String pozitvni = controller.getPositiveWords(lblPoz.getText());
            String negativni = controller.getNegativeWords(lblNeg.getText());
            controller.initializeMyData(totalChar, errorCountWithBackspace, errorCountWithoutBackspace,
                    timeToComplete, wordCount, currentLessonChoice, email, n, pozitvni,negativni);
            Stage theStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //loads the same or next lesson.
    void initializeLessonChoiceAndBegin(int choice) {
        if (choice <= 16 && 0 <= choice) {
            lessonChoiceBox.getSelectionModel().select(choice);
        } else if (choice == 17)
            lessonChoiceBox.getSelectionModel().select(16);
        goButton.fire();
    }

    void getLessonName() {
        String lesson1 = lessonChoiceBox.getSelectionModel().getSelectedItem();
        lblN.setText(lesson1);
        lessonChoiceBox.setOnAction(e -> {
            String lesson = lessonChoiceBox.getSelectionModel().getSelectedItem();
            lblN.setText(lesson);
            lblN.setVisible(false);
            //System.out.println(lesson);

        });


    }
    @FXML
    void lessonChoice() {

        cbDiff.setOnAction(e -> {
            String lesson = (String) cbDiff.getSelectionModel().getSelectedItem();
            System.out.println(lesson);
            if (lesson.equals("Beginner") ) {
               lessonChoiceBox.setValue("Beginner 1");
                lessonChoiceBox.setItems(Beginner);

            } else if (lesson.equals("Advanced")) {
                lessonChoiceBox.setValue("Advanced 1");
                lessonChoiceBox.setItems(Advanced);
            }
            goButton.setDisable(false);
        });
    }
    void setTimer(){
        ETLabel.setText("00:00");

        timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);

        //Action to be performed by timer button on firing.
        timerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sos) {
                    timeline.play();
                    sos = false;
                    timerButton.setText("Stop");
                } else {
                    timeline.pause();
                    sos = true;
                    timerButton.setText("Start");
                }
            }
        });
    }

    //Runs when the scene is loaded.
    public void initialize(URL url, ResourceBundle rb) {
            goButton.setDisable(true);
        //Initializes the choice box with lesson options.

        cbDiff.setItems(Difficulty);
        lessonChoiceBox.getSelectionModel().select("Choose lesson");
        cbDiff.getSelectionModel().select("Choose difficulty");
        lessonChoice();
        getLessonName();
        //Initially Elapsed time is set to 0.
       setTimer();
        lblN.setVisible(false);
        lblPoz.setVisible(false);
        lblNeg.setVisible(false);

    }//Initialize method ends.

}//Controller class ends.
