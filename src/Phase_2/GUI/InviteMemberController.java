package Phase_2.GUI;

import Phase_2.Entity.NormalUser;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InviteMemberController {
    private UserManager um;
    private GroupManager gm;
    private TaskManager tm;
    private String userId;
    private String groupId;
    NotificationManager nm;

    @FXML
    Label PostLabel;
    @FXML
    Button Invite;
    @FXML
    TextField NameInv;

    public void setAll(UserManager um, GroupManager gm, TaskManager tm, String userId, String groupId,
                       NotificationManager nm){
        this.um = um;
        this.gm = gm;
        this.tm = tm;
        this.userId = userId;
        this.groupId = groupId;
        this.nm = nm;
        PostLabel.setVisible(false);
    }



    public void InvitePushed() throws IOException {
        String invId = NameInv.getText();
        PostLabel.setVisible(true);
        if (invId.length() == 0) {
            PostLabel.setText("Please enter a username");
        }
        else if (!um.checkIfValid(invId)) {
            gm.InviteMember(groupId, (NormalUser) um.getUserById(invId));
            PostLabel.setText("Successfully sent an invite!");
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
        else  {
            PostLabel.setText("No such user exists");
        }
    }

    public void backPushed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/GroupContentController.fxml"));
        Parent root = loader.load();
        GroupContentController gcc = loader.getController();
        gcc.setAll(um, gm, tm, userId, groupId, nm);
        Scene scene = new Scene(root);
        GUImain guiMain = new GUImain();
        guiMain.addScene(scene);
    }


}
