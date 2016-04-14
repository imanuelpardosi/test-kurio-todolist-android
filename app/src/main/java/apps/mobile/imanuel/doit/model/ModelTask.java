package apps.mobile.imanuel.doit.model;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class ModelTask {
    int id;
    int user_id;
    int category_id;
    String title;
    String description;
    String due_date;
    String created_time;
    String last_updated;
    String task_status;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUser_id(int user_id){
        this.user_id = user_id;
    }
    public int getUser_id(){
        return this.user_id;
    }
    public void setCategory_id(int category_id){
        this.category_id = category_id;
    }
    public int getCategory_id(){
        return this.category_id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDue_date(String due_date){
        this.due_date = due_date;
    }
    public String getDue_date(){
        return this.due_date;
    }
    public void setCreated_time(String created_time){
        this.created_time = created_time;
    }
    public String getCreated_time(){
        return this.created_time;
    }
    public void setLast_updated(String last_updated){
        this.last_updated = last_updated;
    }
    public String getLast_updated(){
        return this.last_updated;
    }
    public void setTask_status(String task_status){
        this.task_status = task_status;
    }
    public String getTask_status(){
        return this.task_status;
    }
}
