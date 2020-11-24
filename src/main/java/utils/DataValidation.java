package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static boolean validateEmaill(TextField em,Label lb) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(em.getText());
        if (m.find() && m.group().equals(em.getText())) {
            return true;
        } else {
            lb.setText("Please Enter Valid Email");
            lb.setTextFill(Color.web("#ed2f4c"));
            em.getStyleClass().add("wrong-credentials");
            return false;
        }
    }

    public static boolean validatePassword(TextField pw, Label lb) {
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(pw.getText());
        if (m.matches()) {
            return true;
        } else {

            lb.setText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character)");
            lb.setTextFill(Color.web("#ed2f4c"));
            pw.getStyleClass().add("wrong-credentials");
            return false;
        }
    }

    public static boolean validateName(TextField fn, Label lb) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(fn.getText());
        if (m.find() && m.group().equals(fn.getText()) && !fn.equals("")) {
            return true;
        } else {
            lb.setText("Please Enter Valid Name");
            lb.setTextFill(Color.web("#ed2f4c"));
            fn.getStyleClass().add("wrong-credentials");
            return false;
        }
    }

}
