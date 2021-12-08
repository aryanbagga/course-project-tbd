package Phase_2.GUI;

import Phase_2.UseCaseClass.GroupManager;
import Phase_2.UseCaseClass.UserManager;
import javafx.fxml.FXML;

import javafx.scene.control.*;


public class CreateGroupController {
    GroupManager gm;
    UserManager um;
    String userName;

    @FXML
    public Button create_group;

    @FXML
    public TextField group_name;

    @FXML
    public Label message;


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

    /**
     * Creates a group when pressed. Updates text accordingly when the name of the group is unavailable
     */
    public void create_group_button() {

        String name = group_name.getText();

        if(gm.checkGroupExists(name)){
            message.setText("Group already exists!");
        } else {
            gm.createGroup(um.getUserById(userName), group_name.getText());
            message.setText("Group created!");
        }
    }



}