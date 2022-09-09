package Phase_2.GUI;

import Phase_2.Gateways.GroupDataGateWay;
import Phase_2.Gateways.UserDataGateway;
import Phase_2.UseCaseClass.GroupManager;
import Phase_2.UseCaseClass.UserManager;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.io.IOException;


public class JoinGroupController {

    GroupManager gm;
    UserManager um;
    String userName;

    /**
     * Setter methods
     */
    public void setGm(GroupManager gm) {
        this.gm = gm;
    }

    public void setUm(UserManager um) {
        this.um = um;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @FXML
    public Button join_group;

    @FXML
    public TextField group_name;

    @FXML
    public Label message;

    /**
     * Join the group presented by the user
     */
    @FXML
    public void join_group(){
        String name = group_name.getText();
        if(!gm.checkGroupExists(name)){
            message.setText("Group doesn't exist!");
        } else if (gm.checkIfIn(name, um.getUserById(userName))) {
            message.setText("You're already in this group!");
        } else if (gm.checkIfPrivate(name)) {
            message.setText("Sorry, you are unable to join to group since it is invite access only.");
        }
        else {
            gm.addUserToGroup(name, um.getUserById(userName));
            message.setText("Group joined successfully!");
            UserDataGateway udg = new UserDataGateway("userData.ser");
            GroupDataGateWay gdw = new GroupDataGateWay("groupData.ser");
            try{udg.saveToFile(um.getAllUsers());}
            catch (IOException ioException){
                System.out.println("Unknown Error");
            }
            try{gdw.saveToFile(gm.getMaps());}
            catch (IOException ioException){
                System.out.println("Unknown Error");}
        }
    }

    public void group_name() {
    }
}