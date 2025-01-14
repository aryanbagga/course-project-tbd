package Phase_2.GUI;

import Phase_2.Entity.AdminUser;
import Phase_2.Entity.NormalUser;
import Phase_2.GUImain;
import Phase_2.Gateways.GroupDataGateWay;
import Phase_2.Gateways.UserDataGateway;
import Phase_2.Entity.Group;
import Phase_2.Entity.User;
import Phase_2.UseCaseClass.GroupManager;
import Phase_2.UseCaseClass.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;




public class WelcomePageController implements Initializable {
    GroupManager gm;
    UserManager um;
    UserDataGateway udg;
    GroupDataGateWay gdw;
    ArrayList<User> users;
    HashMap<String, Group> groups;


    @FXML
    public Button welcomeButton;

    @FXML
    public Label tbd;

    @FXML Button saveButton;


    /**
     * Setter methods
     */
    public void setUm(UserManager um) {
        this.um = um;
    }

    public void setGm(GroupManager gm) {
        this.gm = gm;
    }

    /**
     * Saves user and group data into database when pressed
     */
    public void saveButtonPushed() {
       try{udg.saveToFile(um.getAllUsers());}
        catch (IOException ioException){
            System.out.println("No user data is stored in database");
        }
        try{gdw.saveToFile(gm.getMaps());}
        catch (IOException ioException){
            System.out.println("No group data is stored in database");}
        tbd.setText("Data saved, we hope to see you again!");
    }

    /**
     * Goes to the main page
     */
    public void buttonPushed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/MainPage.fxml"));
        Parent root = loader.load();
        MainPageController mpc1 = loader.getController();
        mpc1.setUm(um);
        mpc1.setGm(gm);
        Scene scene = new Scene(root);
        GUImain guiMain = new GUImain();
        guiMain.addScene(scene);
        welcomeButton.setText("Hope you enjoy our app :)");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeButton.setText("Click here to begin!");
        users = new ArrayList<>();
        groups = new HashMap<>();
        udg = new UserDataGateway("userData.ser");
        gdw = new GroupDataGateWay("groupData.ser");
        try{users = udg.readFromFile();}
        catch (IOException | ClassNotFoundException ioException){
            System.out.println("No User stored in the file");
        }
        try {
            groups = gdw.readFromFile();
        }catch (IOException | ClassNotFoundException ioException){
            System.out.println("No Group stored in the file");
        }
      //  AdminUser a = new AdminUser("admin", "admin");
      //  users.add(a);
        um = new UserManager(users);
        gm = new GroupManager(groups);





    }
}