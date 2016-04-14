package apps.mobile.imanuel.doit.callback;

import apps.mobile.imanuel.doit.model.ModelResponseLogin;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class CallbackLogin {
    private String status;
    private String message;
    private ModelResponseLogin response;

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
    public void setResponse(ModelResponseLogin response){
        this.response = response;
    }
    public ModelResponseLogin getResponse(){
        return this.response;
    }
}

