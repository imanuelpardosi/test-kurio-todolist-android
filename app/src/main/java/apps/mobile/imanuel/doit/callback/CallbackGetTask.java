package apps.mobile.imanuel.doit.callback;

import java.util.List;

import apps.mobile.imanuel.doit.model.ModelTask;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class CallbackGetTask {
    private String status;
    private String message;
    private List<ModelTask> response;

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
    public void setResponse(List<ModelTask> response){
        this.response = response;
    }
    public List<ModelTask> getResponse(){
        return this.response;
    }

}
