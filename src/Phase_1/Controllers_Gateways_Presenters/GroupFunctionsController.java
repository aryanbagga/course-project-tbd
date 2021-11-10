package Phase_1.Controllers_Gateways_Presenters;

import Phase_1.Entity.Group;
import Phase_1.Entity.NormalUser;
import Phase_1.Entity.User;
import Phase_1.UseCaseClass.GroupManager;
import Phase_1.UseCaseClass.TaskManager;
import Phase_1.UseCaseClass.UserGroupManager;
import Phase_1.UseCaseClass.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class GroupFunctionsController {
    UserManager um;
    NormalUser user;
    GroupManager gm;
    UserGroupManager ugm;
    private TaskPagePresenter tpp;
    private TaskManager itm;
    private User currentUser;
    private final JoinGroupPresenter jgp = new JoinGroupPresenter();

    public GroupFunctionsController(NormalUser user, UserManager um, GroupManager gm) {
        this.um = um;
        this.user = user;
        this.gm = gm;
        this.tpp = new TaskPagePresenter();
        this.itm = new TaskManager();
        this.currentUser = currentUser;
        this.ugm = new UserGroupManager();
    }

    public void CreateGroupController() {
        CreateGroupPresenter cgp = new CreateGroupPresenter();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        cgp.Intro();

        try {
            String groupName = reader.readLine();
            while (gm.checkGroupExists(groupName)) {

                cgp.InvalidGroupName(groupName);
                groupName = reader.readLine();
            }
            gm.createGroup(user, groupName);
            ugm.addGroup(user, groupName);
            cgp.CreateSuccess(groupName);
            cgp.lines();
        } catch (IOException e) {
            System.out.println("Please type a valid number");
        }
    }

    public void LeaveGroupController() {
        LeaveGroupPresenter lgp = new LeaveGroupPresenter();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        lgp.askForName();
        try{
            String input = reader.readLine();
            if(!gm.checkGroupExists(input)){
                lgp.noGroupFound();
                lgp.goBack();
                lgp.lines();
            }
            else if (gm.checkIfIn(input, this.user)){
                if (gm.checkIfLeader(input, this.user)){
                    gm.deleteGroup(input, this.user);
                    lgp.leaveSuccess(input);
                    lgp.lines();
                }
                else {
                    gm.removeMember(input, this.user);
//                    um.leaveGroup(this.user, input);
                    lgp.leaveSuccess(input);
                    lgp.lines();
                }


            }
            else {
                lgp.noGroupFound();
                lgp.lines();
            }

        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }

    public void JoinGroupController() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String option = "1";
            while(Objects.equals(option, "1")){
                jgp.askGroupName();
                String groupName = reader.readLine();
                if(!gm.checkGroupExists(groupName)){ // Check whether group name exists if not:-
                    jgp.groupNameNotInMap();
                    option = reader.readLine();
                }
                else if(gm.checkIfIn(groupName, currentUser)){
                    // If group name exists and user in group already
                    jgp.alreadyInGroup(groupName);
                    option = reader.readLine();
                }
                else{
                    // Else if group name exists and user is not in the group. Add to group.
                    gm.addUserToGroup(groupName, currentUser);
                    // um.addGroupToUser(currentUser, gm.getGroupByName(groupName));
                    jgp.joinSuccess(groupName);
                    option = "2";
                }
            }
            jgp.lines();
        } catch (IOException e){
            System.out.println("Please type a valid number");
        }


    }
}

