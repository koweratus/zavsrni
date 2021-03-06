package controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.fxmisc.richtext.InlineCssTextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
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
import utils.Preferences;


public class StartController implements Initializable {

    @FXML
    private ComboBox<String> lessonChoiceBox;

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
    private Label lblElapsed;

    @FXML
    private JFXButton goButton;

    @FXML
    private Button goToResultButton;
    @FXML
    private Label lblN;
    @FXML
    private Label txtEmail;
    @FXML
    private Label lblNaslov;
    @FXML
    private ComboBox cbDiff;

    private ResourceBundle bundle;
    private Locale locale;
    private final String croatia = "Croatian";
    private final String englishUK = "English (UK)";
    private final String englishUS = "English (US)";
    private final String russian = "Russian";
    private final String serbia = "Serbian";
    private final String cSharp = "C#";
    private final String cpp = "C++";
    private final String java = "Java";
    private final String js = "JavaScript";

    private final ObservableList<String> Beginner = FXCollections
            .observableArrayList("Beginner 1", "Beginner 2", "Beginner 3", "Beginner 4", "Beginner 5");
    private final ObservableList<String> BeginnerHR = FXCollections
            .observableArrayList("BeginnerHR 1", "BeginnerHR 2", "BeginnerHR 3", "BeginnerHR 4", "BeginnerHR 5");
    private final ObservableList<String> BeginnerSR = FXCollections
            .observableArrayList("BeginnerSR 1", "BeginnerSR 2", "BeginnerSR 3", "BeginnerSR 4", "BeginnerSR 5");
    private final ObservableList<String> BeginnerRU = FXCollections
            .observableArrayList("BeginnerRU 1", "BeginnerRU 2", "BeginnerRU 3", "BeginnerRU 4", "BeginnerRU 5");
    private final ObservableList<String> BeginnerUS = FXCollections
            .observableArrayList("BeginnerUS 1", "BeginnerUS 2", "BeginnerUS 3", "BeginnerUS 4", "BeginnerUS 5");
    private final ObservableList<String> BeginnerAzerty = FXCollections
            .observableArrayList("BeginnerAzerty 1", "BeginnerAzerty 2", "BeginnerAzerty 3", "BeginnerAzerty 4", "BeginnerAzerty 5");
    private final ObservableList<String> BeginnerDvorak = FXCollections
            .observableArrayList("BeginnerDvorak 1", "BeginnerDvorak 2", "BeginnerDvorak 3", "BeginnerDvorak 4", "BeginnerDvorak 5");
    private final ObservableList<String> BeginnerColemak = FXCollections
            .observableArrayList("BeginnerColemak 1", "BeginnerColemak 2", "BeginnerColemak 3", "BeginnerColemak 4", "BeginnerColemak 5");
    private final ObservableList<String> Advanced = FXCollections
            .observableArrayList("Advanced 1", "Advanced 2", "Advanced 3", "Advanced 4", "Advanced 5");
    private final ObservableList<String> AdvancedHR = FXCollections
            .observableArrayList("AdvancedHR 1", "AdvancedHR 2", "AdvancedHR 3", "AdvancedHR 4", "AdvancedHR 5");
    private final ObservableList<String> AdvancedSR = FXCollections
            .observableArrayList("AdvancedSR 1", "AdvancedSR 2", "AdvancedSR 3", "AdvancedSR 4", "AdvancedSR 5");
    private final ObservableList<String> AdvancedUS = FXCollections
            .observableArrayList("AdvancedUS 1", "AdvancedUS 2", "AdvancedUS 3", "AdvancedUS 4", "AdvancedUS 5");
    private final ObservableList<String> AdvancedAzerty = FXCollections
            .observableArrayList("AdvancedAzerty 1", "AdvancedAzerty 2", "AdvancedAzerty 3", "AdvancedAzerty 4", "AdvancedAzerty 5");
    private final ObservableList<String> AdvancedDvorak = FXCollections
            .observableArrayList("AdvancedDvorak 1", "AdvancedDvorak 2", "AdvancedDvorak 3", "AdvancedDvorak 4", "AdvancedDvorak 5");
    private final ObservableList<String> AdvancedColemak = FXCollections
            .observableArrayList("AdvancedColemak 1", "AdvancedColemak 2", "AdvancedColemak 3", "AdvancedColemak 4", "AdvancedColemak 5");
    private final ObservableList<String> AdvancedRU = FXCollections
            .observableArrayList("AdvancedRU 1", "AdvancedRU 2", "AdvancedRU 3", "AdvancedRU 4", "AdvancedRU 5");
    private final ObservableList<String> Intermediate = FXCollections
            .observableArrayList("Intermediate 1", "Intermediate 2", "Intermediate 3", "Intermediate 4",
                    "Intermediate 5");
    private final ObservableList<String> IntermediateHR = FXCollections
            .observableArrayList("IntermediateHR 1", "IntermediateHR 2", "IntermediateHR 3", "IntermediateHR 4",
                    "IntermediateHR 5");
    private final ObservableList<String> IntermediateSR = FXCollections
            .observableArrayList("IntermediateSR 1", "IntermediateSR 2", "IntermediateSR 3", "IntermediateSR 4",
                    "IntermediateSR 5");
    private final ObservableList<String> IntermediateRU = FXCollections
            .observableArrayList("IntermediateRU 1", "IntermediateRU 2", "IntermediateRU 3", "IntermediateRU 4",
                    "IntermediateRU 5");
    private final ObservableList<String> IntermediateUS = FXCollections
            .observableArrayList("IntermediateUS 1", "IntermediateUS 2", "IntermediateUS 3", "IntermediateUS 4",
                    "IntermediateUS 5");
    private final ObservableList<String> IntermediateAzerty = FXCollections
            .observableArrayList("IntermediateAzerty 1", "IntermediateAzerty 2", "IntermediateAzerty 3", "IntermediateAzerty 4",
                    "IntermediateAzerty 5");
    private final ObservableList<String> IntermediateDvorak = FXCollections
            .observableArrayList("IntermediateDvorak 1", "IntermediateDvorak 2", "IntermediateDvorak 3", "IntermediateDvorak 4",
                    "IntermediateDvorak 5");
    private final ObservableList<String> IntermediateColemak = FXCollections
            .observableArrayList("IntermediateColemak 1", "IntermediateColemak 2", "IntermediateColemak 3", "IntermediateColemak 4",
                    "IntermediateColemak 5");
    private final ObservableList<String> ProgrammerCS = FXCollections
            .observableArrayList("C# 1", "C# 2");
    private final ObservableList<String> ProgrammerCPP = FXCollections
            .observableArrayList("C++ 1", "C++ 2");
    private final ObservableList<String> ProgrammerJava = FXCollections
            .observableArrayList("Java 1", "Java 2");
    private final ObservableList<String> ProgrammerJS = FXCollections
            .observableArrayList("JavaScript 1", "JavaScript 2");
    private final ObservableList<String> Difficulty = FXCollections
            .observableArrayList("Beginner", "Intermediate", "Advanced", "Programmer");


    //progress tracking variables

    private int totalChar;

    private char expectedKey;

    String timeToComplete;

    private char typedKey;
    private String[] s;

    int indexOfLine = 0;

    String line;

    int wordCount = 0;


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
            BufferedReader reader =
                    new BufferedReader(new FileReader("src/main/resources/files/" + lessonChoiceBox.getSelectionModel().getSelectedItem().toString() + ".txt"));

            totalChar = 0;
            indexOfLine = 0;
            displayArea.setStyle("-fx-font-size: 30px; ");
            displayArea.deleteText(0, displayArea.getLength());//clears the display area.
            displayArea.clearStyle(0, displayArea.getLength());
            textInputArea.setText(""); //clears the inputTextArea.
            textInputArea.requestFocus();//brings focus on the textInputArea.

            textInputArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
                private int pozitvni = 0;
                private int negativni = 0;

                @Override
                public void handle(KeyEvent event) {

                    if (indexOfLine == -1) {
                        indexOfLine = 0;

                    }

                    textInputArea.setId("normal");
                    expectedKey = line.charAt(indexOfLine);

                    typedKey = event.getCharacter().charAt(0);


                    String s1 = displayArea.getText();
                    String s2 = textInputArea.getText();
                    Pattern p = Pattern.compile("[a-zA-Z]*[^\\s+]");
                    Matcher m1 = p.matcher(s1);
                    Matcher m2 = p.matcher(s2);
                    while (m1.find() && m2.find()) {
                        displayArea.setStyle(m1.start(), m1.end(), "-fx-font-weight: bold;");
                    }

                    if (typedKey != expectedKey) {
                        negativni++;
                        lblNeg.setText(String.valueOf(negativni / 5));
                    } else {
                        pozitvni++;
                        lblPoz.setText(String.valueOf(pozitvni / 5));
                    }


                    //DEBUG STATEMENT System.out.println("expected : " + expectedKey);

                    //check if timer started and start if it is not.
                    if (!timerStarted) {
                        timerStarted = true;
                        timerButton.fire();
                    }

                    //if the last letter of the shown line is typed, read the next line of the lesson
                    if (indexOfLine == line.length() - 1) {

                        //check the last character
                        if (typedKey != expectedKey) {
                            textInputArea.setId("warn");//warns the user by setting background red.
                        }
                        //DEBUG STATEMENT       System.out.println("false");
                        try {
                            //if file is not completely read
                            if ((line = reader.readLine()) != null) {
                                displayArea.deleteText(0, displayArea.getLength());//clears the display area.
                                displayArea.clearStyle(0, displayArea.getLength());
                                displayArea.appendText(line);
                                wordCount += line.split("\\s+").length;
                                totalChar += line.length();

                                //wordCount += countSpaces(line);
                                textInputArea.setText("");
                            }
                            //end of file reached
                            else {
                                timerButton.fire();
                                timeToComplete = ETLabel.getText();

                                reader.close();
                                goToResultButton.fire();
                            }
                            indexOfLine = 0;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    //if not last character and if key pressed is not backspace.
                    else if (!(event.getCharacter().equals("\u0008"))) {
                        if (typedKey != expectedKey) {

                            textInputArea.setId("warn");

                        }

                        indexOfLine++;
                    }
                    else if (event.getCharacter().equals("\u0008") && textInputArea.getText() != null) {
                        indexOfLine--;

                    }
                }
            });

            if ((line = reader.readLine()) != null) {
                wordCount += line.split("\\s+").length;
                s = line.split("\\s+");
                displayArea.appendText(line);
                totalChar += line.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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

    @FXML
    void switchSceneToResultPage(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/EndScreen.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            EndScreenController controller = loader.getController();
            int currentLessonChoice = lessonChoiceBox.getSelectionModel().getSelectedIndex();
            String email = controller.GetUser(txtEmail.getText());
            String n = controller.GetN(lblN.getText());
            String pozitvni = controller.getPositiveWords(lblPoz.getText());
            String negativni = controller.getNegativeWords(lblNeg.getText());
            controller.initializeMyData(totalChar,timeToComplete, wordCount, currentLessonChoice, email, n, pozitvni, negativni);
            Stage theStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            theStage.setScene(scene);
            theStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    void initializeLessonChoiceAndBegin(int choice) {
        if (choice <= 6 && 0 <= choice) {
            lessonChoiceBox.getSelectionModel().select(choice);
        }
        goButton.fire();
    }

    void getLessonName() {
        String lesson1 = lessonChoiceBox.getSelectionModel().getSelectedItem();
        lblN.setText(lesson1);
        lessonChoiceBox.setOnAction(e -> {
            String lesson = lessonChoiceBox.getSelectionModel().getSelectedItem();
            lblN.setText(lesson);
            lblN.setVisible(false);

        });


    }

    void lessonChoice(String lang, String plang) {
        Preferences preferences = Preferences.getPreferences();

        cbDiff.setOnAction(e -> {
            String lesson = (String) cbDiff.getSelectionModel().getSelectedItem();
            System.out.println(lesson);
            if (lesson.equals("Beginner")) {
                if (lang.equals(croatia)) {
                    lessonChoiceBox.setValue("BeginnerHR 1");
                    lessonChoiceBox.setItems(BeginnerHR);
                } else if (lang.equals(englishUK)) {
                    if (preferences.getKeyboardLayout().equals("AZERTY")) {
                        lessonChoiceBox.setValue("BeginnerAzerty 1");
                        lessonChoiceBox.setItems(BeginnerAzerty);
                    } else {
                        lessonChoiceBox.setValue("Beginner 1");
                        lessonChoiceBox.setItems(Beginner);
                    }
                } else if (lang.equals(serbia)) {
                    lessonChoiceBox.setValue("BeginnerSR 1");
                    lessonChoiceBox.setItems(BeginnerSR);
                } else if (lang.equals(russian)) {
                    lessonChoiceBox.setValue("BeginnerRU 1");
                    lessonChoiceBox.setItems(BeginnerRU);
                } else if (lang.equals(englishUS)) {
                    if (preferences.getKeyboardLayout().equals("Colemak")) {
                        lessonChoiceBox.setValue("BeginnerColemak 1");
                        lessonChoiceBox.setItems(BeginnerColemak);
                    } else if (preferences.getKeyboardLayout().equals("Dvorak")) {
                        lessonChoiceBox.setValue("BeginnerDvorak 1");
                        lessonChoiceBox.setItems(BeginnerDvorak);

                    } else {
                        lessonChoiceBox.setValue("BeginnerUS 1");
                        lessonChoiceBox.setItems(BeginnerUS);
                    }
                }
            } else if (lesson.equals("Advanced")) {
                if (lang.equals(croatia)) {
                    lessonChoiceBox.setValue("AdvancedHR 1");
                    lessonChoiceBox.setItems(AdvancedHR);
                } else if (lang.equals(englishUK)) {
                    if (preferences.getKeyboardLayout().equals("AZERTY")) {
                        lessonChoiceBox.setValue("AdvancedAzerty 1");
                        lessonChoiceBox.setItems(AdvancedAzerty);
                    } else {
                        lessonChoiceBox.setValue("Advanced 1");
                        lessonChoiceBox.setItems(Advanced);
                    }
                } else if (lang.equals(serbia)) {
                    lessonChoiceBox.setValue("AdvancedSR 1");
                    lessonChoiceBox.setItems(AdvancedSR);
                } else if (lang.equals(russian)) {
                    lessonChoiceBox.setValue("AdvancedRU 1");
                    lessonChoiceBox.setItems(AdvancedRU);
                } else if (lang.equals(englishUS)) {
                    if (preferences.getKeyboardLayout().equals("Colemak")) {
                        lessonChoiceBox.setValue("AdvancedColemak 1");
                        lessonChoiceBox.setItems(AdvancedColemak);
                    } else if (preferences.getKeyboardLayout().equals("Dvorak")) {
                        lessonChoiceBox.setValue("AdvancedDvorak 1");
                        lessonChoiceBox.setItems(AdvancedDvorak);

                    } else {
                        lessonChoiceBox.setValue("AdvancedUS 1");
                        lessonChoiceBox.setItems(AdvancedUS);
                    }

                }
            } else if (lesson.equals("Intermediate")) {
                if (lang.equals(croatia)) {
                    lessonChoiceBox.setValue("IntermediateHR 1");
                    lessonChoiceBox.setItems(IntermediateHR);
                } else if (lang.equals(englishUK)) {
                    if (preferences.getKeyboardLayout().equals("AZERTY")) {
                        lessonChoiceBox.setValue("IntermediateAzerty 1");
                        lessonChoiceBox.setItems(IntermediateAzerty);
                    } else {
                        lessonChoiceBox.setValue("Intermediate 1");
                        lessonChoiceBox.setItems(Intermediate);
                    }
                } else if (lang.equals(serbia)) {
                    lessonChoiceBox.setValue("IntermediateSR 1");
                    lessonChoiceBox.setItems(IntermediateSR);
                } else if (lang.equals(russian)) {
                    lessonChoiceBox.setValue("IntermediateRU 1");
                    lessonChoiceBox.setItems(IntermediateRU);
                } else if (lang.equals(englishUS)) {
                    if (preferences.getKeyboardLayout().equals("Colemak")) {
                        lessonChoiceBox.setValue("IntermediateColemak 1");
                        lessonChoiceBox.setItems(IntermediateColemak);
                    } else if (preferences.getKeyboardLayout().equals("Dvorak")) {
                        lessonChoiceBox.setValue("IntermediateDvorak 1");
                        lessonChoiceBox.setItems(IntermediateDvorak);

                    } else {
                        lessonChoiceBox.setValue("IntermediateUS 1");
                        lessonChoiceBox.setItems(IntermediateUS);
                    }
                }
            } else if (lesson.equals("Programmer")) {
                if (plang.equals(java)) {
                    lessonChoiceBox.setValue("Java 1");
                    lessonChoiceBox.setItems(ProgrammerJava);

                }
                if (plang.equals(cSharp)) {
                    lessonChoiceBox.setValue("C# 1");
                    lessonChoiceBox.setItems(ProgrammerCS);

                }
                if (plang.equals(cpp)) {
                    lessonChoiceBox.setValue("C++ 1");
                    lessonChoiceBox.setItems(ProgrammerCPP);

                }
                if (plang.equals(js)) {
                    lessonChoiceBox.setValue("JavaScript 1");
                    lessonChoiceBox.setItems(ProgrammerJS);

                }
            }
            goButton.setDisable(false);
        });
    }

    void setTimer() {
        ETLabel.setText("00:00");

        timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);

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
        Preferences preferences = Preferences.getPreferences();
        if (preferences.getLanguage().equals(croatia)) {
            loadLang("hr");
            lessonChoice(croatia, preferences.getProgrammingLanguage());
        }
        if (preferences.getLanguage().equals(englishUK)) {
            loadLang("en");
            lessonChoice(englishUK, preferences.getProgrammingLanguage());
        }
        if (preferences.getLanguage().equals(russian)) {
            loadLang("ru");
            lessonChoice(russian, preferences.getProgrammingLanguage());
        }
        if (preferences.getLanguage().equals(serbia)) {
            loadLang("sr");
            lessonChoice(serbia, preferences.getProgrammingLanguage());
        }
        if (preferences.getLanguage().equals(englishUS)) {
            loadLang("en");
            lessonChoice(englishUS, preferences.getProgrammingLanguage());
        }
        if (preferences.getProgrammingLanguage().equals(cpp)) {
            lessonChoice(preferences.getLanguage(), preferences.getProgrammingLanguage());
        }
        if (preferences.getProgrammingLanguage().equals(cSharp)) {
            lessonChoice(preferences.getLanguage(), preferences.getProgrammingLanguage());
        }
        if (preferences.getProgrammingLanguage().equals(java)) {
            lessonChoice(preferences.getLanguage(), preferences.getProgrammingLanguage());
        }
        if (preferences.getProgrammingLanguage().equals(js)) {
            lessonChoice(preferences.getLanguage(), preferences.getProgrammingLanguage());
        }
        goButton.setDisable(true);

        cbDiff.setItems(Difficulty);
        lessonChoiceBox.getSelectionModel().select("Choose lesson");
        cbDiff.getSelectionModel().select("Choose difficulty");
        getLessonName();
        setTimer();
        lblN.setVisible(false);
        lblPoz.setVisible(false);
        lblNeg.setVisible(false);

    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("/lang/lang", locale);
        goButton.setText(bundle.getString("btnGo"));
        HomeButton.setText(bundle.getString("btnHome"));
        lblElapsed.setText(bundle.getString("lblTypingTime"));
        lblNaslov.setText(bundle.getString("lblNaslov"));


    }
}
