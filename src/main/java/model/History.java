package model;

import java.util.Calendar;
import java.util.Date;

public class History {

    private int HistoryID;
    private String HistoryDate;
    private int UsersID;
    private String WPM;
    private String Accuracy;
    private String Language;
    private String KeyboardLayout;
    private String TypeOfTest;
    private String ProgrammingLanguage;

    public String getProgrammingLanguage() {
        return ProgrammingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        ProgrammingLanguage = programmingLanguage;
    }

    public History(String historyDate, int usersID, String wpm, String accuracy, String language,
                   String keyboardLayout, String typeOfTest, String programmingLanguage) {

        HistoryDate = historyDate;
        UsersID = usersID;
        WPM = wpm;
        Accuracy =accuracy;
        Language=language;
        KeyboardLayout=keyboardLayout;
        TypeOfTest=typeOfTest;
        ProgrammingLanguage= programmingLanguage;

    }
    public History(int historyID, String historyDate, int usersID, String wpm, String accuracy, String language,
                   String keyboardLayout, String typeOfTest, String programmingLanguage) {

        HistoryID= historyID;
        HistoryDate = historyDate;
        UsersID = usersID;
        WPM = wpm;
        Accuracy =accuracy;
        Language=language;
        KeyboardLayout=keyboardLayout;
        TypeOfTest=typeOfTest;
        ProgrammingLanguage= programmingLanguage;

    }




    public String toStringHistory(){
        String s="";
        Calendar calendar=Calendar.getInstance();
        Date date=new Date();
        calendar.setTime(date);

        String year=String.valueOf(calendar.get(Calendar.YEAR));
        String month=String.valueOf(calendar.get(Calendar.MONTH));
        String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        s=year+"-"+month+"-"+day;

        return s;
    }


    public int getHistoryID() {
        return HistoryID;
    }

    public void setHistoryID(int historyID) {
        HistoryID = historyID;
    }

    public String getHistoryDate() {
        return HistoryDate;
    }

    public void setHistoryDate(String historyDate) {
        HistoryDate = historyDate;
    }

    public int getUsersID() {
        return UsersID;
    }

    public void setUsersID(int usersID) {
        UsersID = usersID;
    }

    public String getWPM() {
        return WPM;
    }

    public void setWPM(String WPM) {
        this.WPM = WPM;
    }

    public String getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(String accuracy) {
        Accuracy = accuracy;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getKeyboardLayout() {
        return KeyboardLayout;
    }

    public void setKeyboardLayout(String keyboardLayout) {
        KeyboardLayout = keyboardLayout;
    }

    public String getTypeOfTest() {
        return TypeOfTest;
    }

    public void setTypeOfTest(String typeOfTest) {
        TypeOfTest = typeOfTest;
    }

    @Override
    public String toString() {
        return "History{" +
                "HistoryID=" + HistoryID +
                ", HistoryDate='" + HistoryDate + '\'' +
                ", UsersID=" + UsersID +
                ", WPM=" + WPM +
                ", Accuracy=" + Accuracy +
                ", Language='" + Language + '\'' +
                ", KeyboardLayout='" + KeyboardLayout + '\'' +
                ", TypeOfTest='" + TypeOfTest + '\'' +
                '}';
    }
}
