package Phase_1.Controllers_Gateways_Presenters;

import Phase_1.UseCaseClass.GroupManager;
import Phase_1.UseCaseClass.TaskManager;
import Phase_1.UseCaseClass.UserManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This controller is responsible for showing all the categories/folders inside a given group. It
 * prompts the user for an input to go into a particular folder.
 */
public class ViewFoldersController {
    private final String groupId;
    private final UserManager um;
    private final TaskManager tm;
    private final GroupManager gm;
    private final String userId;
    private final ViewFoldersPresenter vfp;

    public ViewFoldersController(UserManager um, TaskManager tm, GroupManager gm, String userId, String groupId){
        this.groupId = groupId;
        this.userId = userId;
        this.um = um;
        this.gm = gm;
        this.tm = tm;
        this.vfp = new ViewFoldersPresenter(gm, groupId);
    }

    /**
     * This method prints out all the folders inside the current group. And asks for an input to go view contents in
     * each folder
     */
    public void run(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            boolean flag = true;
            String input;

            while(flag){
                vfp.askInput();
                input = reader.readLine();
                if(Integer.parseInt(input) < gm.getGroupById(groupId).getCategories().size()){
                    String categoryName = gm.getGroupById(groupId).
                            getCategories().get(Integer.parseInt(input)).toString();
                    GroupAddTaskController gatc = new GroupAddTaskController(userId, groupId, categoryName, um, tm, gm);
                    gatc.run();
                    flag = false;
                } else{
                    flag = false;
                }

            }
        }
        catch (Exception ignored) {
        }
    }

}
