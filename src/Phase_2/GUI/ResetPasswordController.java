package Phase_2.GUI;

import Phase_2.Entity.NormalUser;
import Phase_2.GUImain;
import Phase_2.Gateways.UserDataGateway;
import Phase_2.UseCaseClass.GroupManager;
import Phase_2.UseCaseClass.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;


public class ResetPasswordController{
    GroupManager gm;
    UserManager um;
    String userId;

    @FXML
    Label SecurityQues;
    @FXML
    TextField SecurityAnswer;
    @FXML
    Label SuccessReset;
    @FXML
    Label WrongSA;
    @FXML
    Button ResetButton;
    @FXML
    PasswordField NewPassword;

    /**
     * Setter methods
     */
    public void setGm(GroupManager gm) {
        this.gm = gm;
    }

    public void setUm(UserManager um) {
        this.um = um;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Resets the security answers and questions
     */
    public void ResetButtonPushed() {
        SuccessReset.setText("");
        WrongSA.setText("");

        if (SecurityAnswer.getText().isEmpty() && NewPassword.getText().isEmpty()){
            SuccessReset.setText("Answer or password empty. Try Again!");
        } else if (((NormalUser) um.getUserById(userId)).getSQ_Ans().equals(SecurityAnswer.getText())){
            String pass = NewPassword.getText();
            if (pass.length() < 8 || pass.toLowerCase().equals(pass) || pass.toUpperCase().equals(pass) ||
                    !hasNumber(pass) || !hasSpecial(pass)) {
                SuccessReset.setText("Password does not adhere by the security restrictions");
                SuccessReset.setTooltip(new Tooltip("Password must have at least:\n- 8 characters\n- One UPPERCASE "+
                        "Character\n- One lowercase Character\n- 1 number\n- One exquisite character"));}
            else {
            ((NormalUser) um.getUserById(userId)).setPassword(NewPassword.getText());
            SuccessReset.setText("Password has been reset successfully!");
            UserDataGateway udg = new UserDataGateway();
            try{udg.saveToFile(um.getAllUsers());}
            catch (IOException ioException) {
                System.out.println("Unexpected Error");
            }}
        } else {
            WrongSA.setText("Security Answer incorrect. Try again or return to login page.");
        }
    }

    /**
     * Go back to the previous page
     */
    public void backPushed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/UserLogin.fxml"));
        Parent root = loader.load();
        UserLoginController upc = loader.getController();
        upc.setUm(um);
        upc.setGm(gm);
        Scene scene = new Scene(root);
        GUImain guiMain = new GUImain();
        guiMain.addScene(scene);
    }

    public boolean hasNumber(String x){
        int i = 0;
        while (i < x.length()) {
            if (Character.isDigit(x.charAt(i)))
            {return true;}
            i++;}
        return false;}

    public boolean hasSpecial(String x){
        String specialCharactersString = " !@#$%&*()'+,-./:;<=>?[]^_`{|}~" + '"';
        specialCharactersString = specialCharactersString + '\\';

        int i = 0;
        while (i < x.length()) {
            char temp = x.charAt(i);
            if (specialCharactersString.contains(Character.toString(temp)))
            {return true;}
            i++;}
        return false;}


}
