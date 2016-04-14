package apps.mobile.imanuel.doit.model;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class ModelCategory {
    private int id;
    private String category_name;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setCategory_name(String category_name){
        this.category_name = category_name;
    }
    public String getCategory_name(){
        return this.category_name;
    }
}
