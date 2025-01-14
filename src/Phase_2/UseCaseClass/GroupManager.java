package Phase_2.UseCaseClass;

import Phase_2.Entity.*;

import java.util.ArrayList;

import java.util.HashMap;

public class GroupManager{
    public HashMap<String, Group> maps;
    UserGroupManager ugm = new UserGroupManager();
    TaskManager tm = new TaskManager();

    /**
     * Construct a TBD.GroupManager, giving them the given maps
     * attribute with User as keys and ArrayList contains Group as
     * values
     * @param maps the HasMap that contains all the groups in the system
     *             and their names
     */
    public GroupManager(HashMap<String, Group> maps) {
        this.maps = maps;
    }

    /**
     * This is the method to create a new group given a user
     * and the name of the group
     * @param user the leader of the group
     * @param name name of the group
     */
    public void createGroup(User user, String name, boolean priv) {
        int count = 0;
        for (Group g: maps.values()) {
           String b = g.getgroupName();
           int c = b.length() - 1;
           while (c != -1) {
           if (b.charAt(c) == '#'){
               b = b.substring(c+1);
               c = 0;}
           c -= 1;}
            if (count <= Integer.parseInt(b)){
                count = Integer.parseInt(b) + 1;
            }}
        String groupId =  "#" + count;
        Group group = new Group(user, name + groupId, priv);
        this.maps.put(name+groupId, group);
        ugm.addGroup(user, group.getgroupName());
    }

    public boolean checkIfPrivate(String groupName) {
     return this.maps.get(groupName).getIsPrivate();
    }

    /**
     * This is the method to delete a group given
     * the wanted group object
     * @param groupName name of the wanted to delete group
     * @param leader leader of the group
     */
    public void deleteGroup(String groupName, User leader) {
        ugm.removeGroup(leader, groupName);
        for (User user: this.maps.get(groupName).getUsers()){
            ugm.removeGroup(user, groupName);
        }
        this.maps.remove(groupName);
    }

    /**
     * This method returns a List of User in a given group name
     * @param name name of the wanted group
     * @return List of User in the wanted group
     */
    public ArrayList<User> memberList(String name) {
        Group group = this.maps.get(name);
        return group.getUsers();
    }

    /**
     * This method checks whether a given user is in a
     * group
     * @param groupname the name of a group
     * @param user the given user
     * @return true if the given user is in the group with the
     * given name and false otherwise
     */
    public boolean checkIfIn(String groupname, User user) {
        Group group = this.maps.get(groupname);
        for (User i: group.getUsers()) {
            if (i.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return group.getgroupLeader().getUsername().equals(user.getUsername());
    }

    /**
     * This method checks whether the given group name
     * has been taken
     * @param groupname the wanted group name
     * @return true if there exists a group with
     * the same name and false otherwise
     */
    public boolean checkGroupExists(String groupname) {
        for (String name: this.maps.keySet()) {
            if (groupname.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks whether a given user is the leader
     * of a given group
     * @param groupname the name of a group
     * @param user the given user
     * @return true if the given user is the leader of
     *  the group with the given name and false otherwise
     */
    public boolean checkIfLeader(String groupname, User user) {
        Group group = this.maps.get(groupname);
        User leader = group.getgroupLeader();
        return user.getUsername().equals(leader.getUsername());
    }

    /**
     * This method adds a given group to the user's
     * group list
     * @param groupname the group that we want to add
     * @param user given user
     */
    public void addUserToGroup(String groupname, User user) {
        Group group = this.maps.get(groupname);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        group.addUsers(users);
        ugm.addGroup(user, groupname);
        group.addCategory(user.getUsername());
    }

    /**
     * This method removes a user from the given group
     * @param groupname the given group
     * @param user the member that is removed from group
     */
    public void removeMember(String groupname, User user) {
        Group group = this.maps.get(groupname);
        group.removeUser(user);
        ugm.removeGroup(user, groupname);
        group.getCategories().removeIf(c -> c.getCategoryName().equals(user.getUsername()));
    }

    /**
     * This is the method to create a new group given a user
     * and the name of the group
     * @param user the leader of the group
     * @param groupname name of the group
     */
    public void InviteMember(String groupname, NormalUser user) {
        String title = "You have received an invitation to join "+groupname;
        String inv = "You have been invited to join group " +groupname+".\n Please accept or decline this invite!";
        NotificationManager nm = new NotificationManager(user);
        nm.AddNotification(title, inv, true);
    }

    /**
     * This method returns an instance of Group that matches the
     * given groupId
     * @param groupId the given Id of the wanted group
     * @return the wanted Group of the given Id
     */
    public Group getGroupById(String groupId){
        return maps.get(groupId);
    }

    /**
     * This method returns groups stored in GroupManager
     * @return groups stored
     */
    public HashMap<String, Group> getMaps(){
        return maps;
    }
}
