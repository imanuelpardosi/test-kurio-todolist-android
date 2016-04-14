package apps.mobile.imanuel.doit.callback;

import java.util.List;

import apps.mobile.imanuel.doit.model.ModelCategory;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class CallbackGetCategories {
    private String status;
    private String message;
    private List<ModelCategory> response;

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
    public void setResponse(List<ModelCategory> response){
        this.response = response;
    }
    public List<ModelCategory> getResponse(){
        return this.response;
    }
}
