package apps.mobile.imanuel.doit.helper;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class RESTClient {
    private static DoItAPI REST_CLIENT;
    public static String preURL = "ip/api-v1.doit.xyz";    //change this to access address of API
    public static String port = "";
    public static String postUrl = "";
    public static String URL = "http://"+preURL+port+postUrl;
    private static Context mContext;
    private static long SIZE_OF_CACHE = 50 * 1024 * 1024; // 50 MB

    static {
        setRestClient();
    }

    private RESTClient(){}
    public static DoItAPI getRestClient(Context context){
        mContext = context;

        // Create Cache
        Cache cache = null;
        cache = new Cache(new File(mContext.getCacheDir(), "http"), SIZE_OF_CACHE);

        return REST_CLIENT;
    }

    //setup resclient
    //set timeout  = 30 s
    //using OkHttpClient we can customize access method of restclient
    private static void setRestClient(){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);

        /*
        try{
            Cache cache = null;
            cache = new Cache(getDirectory(), 1024 * 1024 * 10);
            okHttpClient.setCache(cache);
        }catch (Exception ex){
        }
*/
        //      okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        RestAdapter restBuilder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(URL)
                .build();

        REST_CLIENT = restBuilder.create(DoItAPI.class);
    }

    //create interceptor request for caching purpose
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", String.format("max-age=%d, only-if-cached, max-stale=%d", 240, 0))
                    .build();
        }
    };

    //returns the file to store cached details
    static File getDirectory() {
        return new File("java.io.tempdir");
    }
}
