package apps.mobile.imanuel.doit.model;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class ModelResponseLogin {
    private int id;
    private String username;
    private String access_token;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setAccess_token(String access_token){
        this.access_token = access_token;
    }
    public String getAccess_token(){
        return this.access_token;
    }
}
