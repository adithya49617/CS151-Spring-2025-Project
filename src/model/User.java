package model;

public class User {
    private String userId;
    private String userDOB;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userGender;

    public User(String userId, String userDOB, String userFirstName, String userLastName, String userEmail, String userGender) {
        this.userId = userId;
        this.userDOB = userDOB;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userGender = userGender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }


    @Override
    public String toString() {
        return userId +
                "," + userDOB +
                "," + userFirstName +
                "," + userLastName +
                "," + userEmail +
                "," + userGender +
                '}';
    }
}
