package apps.mobile.imanuel.doit.callback;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class CallbackGeneral {
    private String status;
    private String message;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
