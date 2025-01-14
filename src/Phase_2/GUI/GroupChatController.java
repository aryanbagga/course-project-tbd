package Phase_2.GUI;

import Phase_2.GUImain;
import Phase_2.Gateways.GroupDataGateWay;
import Phase_2.Gateways.UserDataGateway;
import Phase_2.UseCaseClass.GroupManager;
import Phase_2.UseCaseClass.NotificationManager;
import Phase_2.UseCaseClass.TaskManager;
import Phase_2.UseCaseClass.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class GroupChatController {
    String groupId;
    String userId;
    GroupManager gm;
    UserManager um;
    NotificationManager nm;

    /**
     * Setter methods
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setGm(GroupManager gm) {
        this.gm = gm;
    }
    public void setUm(UserManager um) {
        this.um = um;
    }
    public void setNm(NotificationManager nm) {
        this.nm = nm;
    }

    @FXML
    Button backButton;

    @FXML
    Button sendButton;

    @FXML
    Button refreshButton;

    @FXML
    TextArea displayMessage;

    @FXML
    TextField enterMessage;

    /**
     * This is a method to go back to the previous page
     */
    public void backPush() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/GroupContentController.fxml"));
        Parent root = loader.load();
        GroupContentController gcc = loader.getController();
        TaskManager tm = new TaskManager();
        gcc.setAll(um, gm, tm, userId, groupId, nm);
        Scene scene = new Scene(root);
        GUImain guiMain = new GUImain();
        guiMain.addScene(scene);
    }
    /**
     * This is the method using for display all the messages in the GroupChat
     * This is associated with the Refresh button on the scene and should be pushed
     * first after entering the GroupChat
     */
    public void display() {
        displayMessage.setText("");
        displayMessage.setText(gm.getGroupById(groupId).getGroupChat().toString());
    }
    /**
     * This is the method to add a message into the current GroupChat
     */
    public void enter() {
        String message = enterMessage.getText();
        gm.getGroupById(groupId).getGroupChat().insertMessage(um.getUserById(userId), message);
        displayMessage.setText(gm.getGroupById(groupId).getGroupChat().toString());
        enterMessage.clear();
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
