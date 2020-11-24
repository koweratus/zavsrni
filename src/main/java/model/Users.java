package model;

import javafx.scene.image.Image;

public class Users {
    private int IDUsers;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;



    public Users(String firstName, String lastName , String email, String password){
        FirstName = firstName;
        LastName = lastName;
        Email = email;
       Password = password;
    }
    public Users(int idUsers,String firstName, String lastName , String email, String password
    ) {
        IDUsers= idUsers;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;

    }



    public int getUsersId() {
        return IDUsers;
    }

    public void setUsersId(int usersID) {
        IDUsers = usersID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "User id=" + IDUsers +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
