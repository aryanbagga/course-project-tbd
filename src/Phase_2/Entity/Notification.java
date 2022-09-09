package Phase_2.Entity;

import java.io.Serializable;

public class Notification implements Serializable {
    private String Title;
    private String Message;
    private boolean IsInvite;

    public Notification(String title, String message, boolean isInvite) {
        this.Title = title;
        this.Message = message;
        this.IsInvite = isInvite;
    }

    public String getTitle(){
        return Title;
    }

    public String getMessage(){
        return Message;
    }

    public boolean getIsInvite(){
        return IsInvite;
    }


}
