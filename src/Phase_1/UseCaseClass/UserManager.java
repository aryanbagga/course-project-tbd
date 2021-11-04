package Phase_1.UseCaseClass;

import Phase_1.Entity.*;

import java.util.ArrayList;
import java.util.Objects;

public class UserManager {
    public ArrayList<User> allUsers;
    TaskManager itm = new TaskManager();

    public UserManager(ArrayList<User> user_list) {
        this.allUsers = user_list;
    }

    public String displayUserDetail(User user) {
        return ("Username: " + user.getUsername() + "\n"
                + "Password: " + user.getPassword());
    }


    /**
     * Creates a normal user and add it to the arraylist of all the users.
     */
    public void createNormalUser(String username, String password) {
        this.allUsers.add(new NormalUser(username, password));
    }


    /**
     * Check if the attempted username is not in the pool of all usernames.
     */
    public boolean checkIfValid(String UserId) {
        for (User user : allUsers) {
            if (user.getUsername().equals(UserId)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks if the attempted username and password is in the user pool,
     * if yes then return true, otherwise return false.
     */
    public boolean login(String ausername, String apassword) {
        for (User user : allUsers) {
            if (Objects.equals(user.getUsername(), ausername) && Objects.equals(user.getPassword(), apassword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the user when given its username or null if not found
     */
    public User getUserById(String ID) {
        for (User user : allUsers) {
            if (user.getUsername().equals(ID)) {
                return user;
            }
        }
        return null;
    }

    //    public ArrayList<Task> unfinishedTaskList(NormalUser user) {
//        ArrayList<Task> unfinished  = new ArrayList<>();
//        for (Category category : user.myCategories) {
//            for (Task task : category.getTasks()) {
//                if (!task.status) {
//                    unfinished.add(task);
//                }
//            }
//        }
//        return unfinished;
//    }
    public String getUserName(User user) {
        return user.getUsername();
    }

    public void removeGroup(User user, Group group) {
        user.removeGroup(group);
    }

    public void addGroup(User user, Group group) {
        user.addGroup(group);
    }

    public void addCategory(User user, Category c) {
        user.addNewCategory(c);
    }

    public void addTask(User user, Task task) {
        user.addTask(task);
    }


    public String displayTask(User user) {
        StringBuilder s = new StringBuilder();
        for (Task t : user.getMyTasks()) {
            s.append(t.toString()).append("\n");
        }
        return s.toString();
    }

    public boolean checkTask(User user, Task t) {
        return user.getMyTasks().contains(t);
    }

    public Task getTaskByName(User user, String taskName) {
        for (Task t : user.getMyTasks()) {
            if (itm.checkTaskByName(t, taskName)) {
                return t;
            }

        }
        return null;
    }

        public String displayCategories(User user) {
        StringBuilder s = new StringBuilder();
        for (Category c : user.getMyCategories()) {
            s.append(c.toString()).append("\n");
            }
        return s.toString();
    }
}
