package Phase_2.GUI;

import Phase_2.Entity.NormalUser;
import Phase_2.Entity.User;
import Phase_2.GUImain;
import Phase_2.UseCaseClass.AlarmStarter;
import Phase_2.UseCaseClass.GroupManager;
import Phase_2.UseCaseClass.NotificationManager;
import Phase_2.UseCaseClass.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginController{
//implements Initializable
    GroupManager gm;
    UserManager um;

    @FXML
    Button LoginButton;
    @FXML
    Label WrongLogin;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label SuccessLogin;
    @FXML
    Hyperlink ResetPassword;


    /**
     * Setter methods
     */
    public void setGm(GroupManager gm) {
        this.gm = gm;
    }

    public void setUm(UserManager um) {
        this.um = um;
    }

    public void LoginButtonPushed() throws IOException {
        GUImain guiMain = new GUImain();

        WrongLogin.setText("");
        SuccessLogin.setText("");

        String userId = username.getText();
        String passwordId = password.getText();

        if (username.getText().isEmpty() && password.getText().isEmpty()) {
            WrongLogin.setText("Please enter your data.");
        }
        else if (um.AdminLogin(userId, passwordId)){
            SuccessLogin.setText("Success!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/AdminAccess.fxml"));
            Parent root = loader.load();
            AdminAccessController aac = loader.getController();
            aac.setAll(gm, um);
            aac.loadUsers();
            Scene scene = new Scene(root);
            guiMain.addScene(scene);
        }
        else if (um.login(userId, passwordId)){
            SuccessLogin.setText("Success!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/UserPageController.fxml"));
            Parent root = loader.load();
            UserPageController mpc = loader.getController();
            mpc.setUm(um);
            mpc.setGm(gm);
            NotificationManager notificationManager = new NotificationManager((NormalUser)um.getUserById(userId));
            mpc.setNotificationManager(notificationManager);
            mpc.setUserName(userId);
            Scene scene = new Scene(root);
            guiMain.addScene(scene);
        }
        else if (um.checkIfValid(userId)) {
            WrongLogin.setText("This userId does not exist. Try Again!");
            username.clear();
            password.clear();
        }
        else
        {
            WrongLogin.setText("Incorrect Password. Try Again or Reset password!");
            password.clear();
        }
    }

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

    public void ResetPasswordonClick() throws IOException{
        WrongLogin.setText("");
        SuccessLogin.setText("");

        String userId = username.getText();
        if (userId.isEmpty()){
            WrongLogin.setText("Enter username before resetting password");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/ResetPassword.fxml"));
            Parent root = loader.load();
            ResetPasswordController rpc = loader.getController();
            rpc.setUm(um);
            rpc.setGm(gm);
            rpc.setUserId(userId);
            rpc.SecurityQues.setText(((NormalUser) um.getUserById(userId)).getSQ());
            Scene scene = new Scene(root);
            GUImain guiMain = new GUImain();
            guiMain.addScene(scene);
        }
    }

//    @Override
 //   public void initialize(URL url, ResourceBundle resourceBundle) {
        // this is added by Owen, contact if this troubles you
  //      notificationManager.setAlarmMenu(new AlarmStarter());
   //     Thread notificationSystem = new Thread(notificationManager);
  //      notificationSystem.start();
        // --------------------------------------------------------
  //  }

}
