package apps.mobile.imanuel.doit.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import apps.mobile.imanuel.doit.R;
import apps.mobile.imanuel.doit.adapter.CustomTaskAdapter;
import apps.mobile.imanuel.doit.callback.CallbackGeneral;
import apps.mobile.imanuel.doit.callback.CallbackGetTask;
import apps.mobile.imanuel.doit.helper.GeneralHelper;
import apps.mobile.imanuel.doit.helper.RESTClient;
import apps.mobile.imanuel.doit.helper.UserLogInfo;
import apps.mobile.imanuel.doit.model.ModelTask;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private List<ModelTask> tasks;
    TextView greeting;

    private ArrayList<ModelTask> data;
    ProgressDialog progressDialog;
    List<ModelTask> list_Task;

    ListView listView;
    CustomTaskAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Fetching data...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        greeting = (TextView) findViewById(R.id.tv_greeting);
        greeting.setText("Hi " + UserLogInfo.getUsername(getApplicationContext()) + " !");

        Log.i("greeting", "");

        listView = (ListView) findViewById(R.id.lv_Tasks);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //Toast.makeText(MainActivity.this, list_Task.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                // TODO Auto-generated method stub

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Delete task?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteTask(UserLogInfo.getAccessToken(getApplicationContext()),pos);
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, AddTask.class);
                startActivity(intent);
            }
        });

        RESTClient.getRestClient(getApplicationContext()).getTask(UserLogInfo.getAccessToken(getApplicationContext()), new Callback<CallbackGetTask>() {
            @Override
            public void success(CallbackGetTask callbackGetTask, Response response) {
                list_Task = callbackGetTask.getResponse();
                Log.i("size", String.valueOf(list_Task.size()));

                m_adapter = new CustomTaskAdapter(getApplicationContext(), list_Task);
                listView.setAdapter(m_adapter);


                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("error", error.getMessage());
                progressDialog.dismiss();
            }
        });

    }

    public void deleteTask(String access_token, int id){
        RESTClient.getRestClient(getApplicationContext()).deleteTask(UserLogInfo.getAccessToken(getApplicationContext()), id, new Callback<CallbackGeneral>() {
            @Override
            public void success(CallbackGeneral callbackGeneral, Response response) {
                if (callbackGeneral.getMessage().equals("delete task success")) {
                    Toast.makeText(MainActivity.this, "Delete task success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Delete task failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("error", error.getMessage());
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
