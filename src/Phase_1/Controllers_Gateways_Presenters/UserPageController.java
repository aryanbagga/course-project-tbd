package Phase_1.Controllers_Gateways_Presenters;

import Phase_1.Alarm.AlarmStarter;
import Phase_1.UseCaseClass.GroupManager;
import Phase_1.UseCaseClass.NotificationManager;
import Phase_1.UseCaseClass.UserManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Objects;

public class UserPageController {
    private final UserPagePresenter upp;
    private final GroupPageController gpc;
    private final CategoryPageController cpc;
    private final NotificationPageController npc;

    public UserPageController(UserManager um, String userId, GroupManager gm) {
        this.upp = new UserPagePresenter(um.getUserById(userId).getUsername());
        this.gpc = new GroupPageController(userId, um, gm);
        NotificationManager nm = new NotificationManager();
        this.cpc = new CategoryPageController(userId, um, nm);

        this.npc = new NotificationPageController(nm);

        nm.setAlarmMenu(new AlarmStarter());
        Thread notificationSystem = new Thread(nm);
        notificationSystem.start();
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        while (!Objects.equals(input, "4")) {
            upp.userProfilePage();
            upp.availableOptions();
            input = reader.readLine();

            if (input.equals("1")) {      // My Group
                gpc.run();
            }
            if (input.equals("2")) {      // My Category
                cpc.run();
            }
            if (input.equals("3")) {      // My Category
                npc.run();
            }
        }

    }

}
