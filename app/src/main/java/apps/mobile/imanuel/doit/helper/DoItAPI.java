package apps.mobile.imanuel.doit.helper;

import java.io.File;

import apps.mobile.imanuel.doit.callback.CallbackGeneral;
import apps.mobile.imanuel.doit.callback.CallbackGetCategories;
import apps.mobile.imanuel.doit.callback.CallbackGetTask;
import apps.mobile.imanuel.doit.callback.CallbackLogin;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
/**
 * Created by Imanuel on 13/04/2016.
 */
public interface DoItAPI {
    @FormUrlEncoded
    @Headers("API-KEY: kVBkcfp3ivy3GqzSn62Dn2sW27X408VN")
    @POST("/login")
    void doLogin(@Field("username")String username, @Field("password")String password,
                 Callback<CallbackLogin> callback);

    @GET("/getTask")
    void getTask(@Header("ACCESS-TOKEN")String access_token, Callback<CallbackGetTask> callback);

    @FormUrlEncoded
    @POST("/addTask")
    void addTask(@Header("ACCESS-TOKEN")String access_token,@Field("category_id")int cat_id, @Field("title")String title,
    @Field("description")String description, @Field("due_date")String due_date, @Field("created_time")String created_time,
    @Field("last_updated")String last_updated, @Field("task_status")String task_status, Callback<CallbackGeneral> callback);

    @FormUrlEncoded
    @POST("/deleteTask")
    void deleteTask(@Header("ACCESS-TOKEN")String access_token,@Field("id")int id, Callback<CallbackGeneral> callback);

}
