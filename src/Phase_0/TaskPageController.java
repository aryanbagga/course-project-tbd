package Phase_0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TaskPageController {
    private NormalUser user;
    private TaskPagePresenter tpp;
    UserManager um;

    public TaskPageController(NormalUser user, UserManager um){
        this.user = user;
        this.tpp = new TaskPagePresenter(user);
        this.um = um;
    }
    public void run() throws IOException{
        tpp.availableOptions();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(um.displayTask(user));
        String input = reader.readLine();
        while (!input.equals("1")){
        if (input.equals("2")){
            tpp.giveNewTaskName();
            String taskTitle = reader.readLine();
            tpp.giveTaskDetail();
            String taskDetail = reader.readLine();
            Task task = new Task(taskTitle, taskDetail);
            um.addTask(user, task);
            tpp.taskAdd();
            tpp.availableOptions();
        }else if(input.equals("3")){
            break;



        }
    }}
}
