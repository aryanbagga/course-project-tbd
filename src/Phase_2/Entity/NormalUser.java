package Phase_2.Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class NormalUser extends User implements Serializable {

    private final String username;
    private String password;
    private String sq;
    private String sq_ans;

    private final ArrayList<String> myGroups = new ArrayList<>();

    private final ArrayList<Category> myCategories = new ArrayList<>();
    private final ArrayList<Task> myTasks = new ArrayList<>();
    private final ArrayList<Notification> myNotifications = new ArrayList<>();

    /**
     * Constructs a simplified version of task with only a task name, this constructor is for testing purposes
     * only, and should not be called inside the main program
     *
     * @param username the name of the user
     * @param password the password of the user
     */
    public NormalUser(String username, String password, String sq, String sq_ans) {
        this.username = username;
        this.password = password;
        this.sq = sq;
        this.sq_ans = sq_ans;
        Category all = new Category("General");
        this.addNewCategory(all);
        String title = "Greetings";
        String detail = "Greetings!\n"
                + "\nThis is a productivity app in which you can "
                + "\ncreate tasks that you want to work on, work \n in groups and chat with you group members. \n"
                + "You even have the option to set an alarm for \nyour task if you want to get reminded! "
                + "\nEnjoy your adventure with our app!\n\n"
                + "Group TBD";
        Notification n = new Notification(title, detail, false);
        this.myNotifications.add(n);
    }

    /**
     * Constructs a simplified version of task with only a task name, this constructor is for testing purposes
     * only, and should not be called inside the main program
     *
     * @param username the name of the user
     * @param password the password of the user
     */

    public NormalUser(String username, String password) {
        this.username = username;
        this.password = password;
        Category all = new Category("All Tasks");
        this.addNewCategory(all);
        String title = "Greetings";
        String detail = "Greetings!\n"
                + "\nThis is a productivity app in which you can "
                + "\ncreate tasks that you want to work on, work \n in groups and chat with you group members. \n"
                + "You even have the option to set an alarm for \nyour task if you want to get reminded! "
                + "\nEnjoy your adventure with our app!\n\n"
                + "Group TBD";
        Notification n = new Notification(title, detail, false);
        this.myNotifications.add(n);
    }

    /**
     * A method that returns username and password. PS someone please change the format to make it look better
     */
    public String displayUserDetail() {
        return ("Username: " + this.username + "\n"
                + "Password: " + this.password);
    }

    /**
     * Add group to this normal user
     */
    public void addGroup(String groupId) {
        this.myGroups.add(groupId);
    }

    /**
     * Add notification for this normal user
     */
    public void addNotification(Notification n) {
        this.myNotifications.add(0, n);
    }

    /**
     * Removes notification for this normal user
     */
    public void removeNotification(String title) {
        this.myNotifications.removeIf(n -> n.getTitle().equals(title));
    }

    /**
     * Remove group from this normal user
     */
    @Override
    public void removeGroup(String group) {
        this.myGroups.remove(group);
    }

    /**
     * Add task to category c
     */
    public void addTasktoCategory(Task task, Category c) {
        c.getTasks().add(task);
    }

    /**
     * Create a new category to this normal user
     */
    public void addNewCategory(Category newCategory) {
        this.myCategories.add(newCategory);
    }

    /**
     * Returns all tasks from this user
     */
    public ArrayList<Task> getMyTasks() {
        return myTasks;
    }

    /**
     * Returns username of this normal user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns password of this normal user
     */
    public String getPassword() {
        return password;
    }

    /**
     * returns security question of this normal user
     */
    public String getSQ() {
        return sq;
    }

    /**
     * returns security questions answer of this normal user
     */
    public String getSQ_Ans() {
        return sq_ans;
    }

    /**
     * resets the password of this normal user
     */
    public void setPassword(String pass) {
        this.password = pass;
    }

    /**
     * Returns all groups of this normal user
     */
    @Override
    public ArrayList<String> getMyGroups() {
        return myGroups;
    }

    /**
     * returns all categories of this normal user
     */
    public ArrayList<Category> getMyCategories() {
        return myCategories;
    }

    /**
     * returns all notifications of this normal user
     */
    public ArrayList<Notification> getMyNotifications() {
        return myNotifications;
    }

    /**
     *
     * @param nu the other user
     * @return true if two user is the same
     */
    @Override
    public boolean equals(User nu){
        return Objects.equals(nu.getUsername(), this.getUsername());
    }
}