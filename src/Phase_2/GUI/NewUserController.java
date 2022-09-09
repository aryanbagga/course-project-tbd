package Phase_2.GUI;
import Phase_2.GUImain;
import Phase_2.Gateways.UserDataGateway;
import Phase_2.UseCaseClass.GroupManager;
import Phase_2.UseCaseClass.UserManager;
import javafx.fxml.FXML;

import java.io.IOException;


import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class NewUserController{
    GroupManager gm;
    UserManager um;
    @FXML
    Button signUpButton;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    TextField sQ;
    @FXML
    TextField sA;
    @FXML
    Hyperlink back;
    @FXML
    Label takenUserName;
    @FXML
    Label success;
    @FXML
    ImageView W1;
    @FXML
    ImageView W2;
    @FXML
    ImageView W3;
    @FXML
    ImageView W4;
    @FXML
    ImageView W5;
    @FXML
    ImageView C1;
    @FXML
    ImageView C2;
    @FXML
    ImageView C3;
    @FXML
    ImageView C4;
    @FXML
    ImageView C5;



    /**
     * Setter methods
     */
    public void setGm(GroupManager gm) {
        this.gm = gm;
    }

    public void setUm(UserManager um) {
        this.um = um;
    }

    /**
     * Updates the password restrictions column as the password_filed is updated
     */
    public void passChanging(){
        String pass = password.getText();
        if (!pass.toLowerCase().equals(pass)) {
            W1.setVisible(false);
            C1.setVisible(true);
        }
        else {
            W1.setVisible(true);
            C1.setVisible(false);
        }

        if (!pass.toUpperCase().equals(pass)) {
            W2.setVisible(false);
            C2.setVisible(true);
        }
        else {
            W2.setVisible(true);
            C2.setVisible(false);
        }

        if (hasNumber(pass)) {
            W3.setVisible(false);
            C3.setVisible(true);
        }
        else {
            W3.setVisible(true);
            C3.setVisible(false);
        }

        if (hasSpecial(pass)) {
            W4.setVisible(false);
            C4.setVisible(true);
        }
        else {
            W4.setVisible(true);
            C4.setVisible(false);
        }

        if (pass.length() >= 8) {
            W5.setVisible(false);
            C5.setVisible(true);
        }
        else {
            W5.setVisible(true);
            C5.setVisible(false);
        }
    }

    /**
     * Creates a user with the given username and password
     */
    public void signUpButtonPushed(){
        takenUserName.setText("");
        takenUserName.setTooltip(null);
        success.setText("");

        String userId = username.getText();
        String pass = password.getText();
        if (sQ.getText().isEmpty() && sA.getText().isEmpty()){
            success.setText("Security Question or Answer empty. Try again.");
        }
        else if (!um.checkIfValid(userId)){
            takenUserName.setText("Username " + userId + " is already taken");
            takenUserName.setTooltip(new Tooltip("Please try a different username"));
        }
//      else if (pass.length() < 8 || pass.toLowerCase().equals(pass) || pass.toUpperCase().equals(pass) ||
//                !hasNumber(pass)) {
        else if (W1.isVisible() || W2.isVisible() || W3.isVisible() || W4.isVisible() || W5.isVisible()) {
            takenUserName.setText("Password does not adhere by the security restrictions");
            takenUserName.setTooltip(new Tooltip("Password must have at least:\n- 8 characters\n- One UpperCase " +
                    "Character\n- One LowerCase Character\n- One number\n- One Special Character"));
        }
        else {
            um.createNormalUser(userId, password.getText(), sQ.getText(), sA.getText());
            success.setText("User " + userId +" created");
            UserDataGateway udg = new UserDataGateway();
            try{udg.saveToFile(um.getAllUsers());
            loginpage();}
            catch (IOException ioException){
                System.out.println("Unexpected Error");
            }


    } }

    public boolean hasNumber(String x){
        int i = 0;
        while (i < x.length()) {
            if (Character.isDigit(x.charAt(i)))
            {return true;}
            i++;}
        return false;}

    public boolean hasSpecial(String x){
        String specialCharactersString = " !@#$%&*()'+,-./:;<=>?[]^_`{|}~" + Character.toString('"');
        specialCharactersString = specialCharactersString + Character.toString('\\');

        int i = 0;
        while (i < x.length()) {
            char temp = x.charAt(i);
            if (specialCharactersString.contains(Character.toString(temp)))
            {return true;}
            i++;}
        return false;}



//    public boolean hasSpecial(String x){
//        int i = 0;
//        while (i < x.length()) {
//            if (Character.
//            {return true;}
//            i++;}
//        return false;}

    /**
     * Goes back to the previous page
     */
    public void backPushed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/MainPage.fxml"));
        Parent root = loader.load();
        MainPageController mpc = loader.getController();
        mpc.setUm(um);
        mpc.setGm(gm);
        Scene scene = new Scene(root);
        GUImain guiMain = new GUImain();
        guiMain.addScene(scene);
    }

    /**
     * Goes to the login page after successful user creation
     */
    public void loginpage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/UserLogin.fxml"));
        Parent root = loader.load();
        UserLoginController mpc1 = loader.getController();
        mpc1.setUm(um);
        mpc1.setGm(gm);
        mpc1.WrongLogin.setText("User Successfully Created :)");
        Scene scene = new Scene(root);
        GUImain guiMain = new GUImain();
        guiMain.addScene(scene);
    }

}