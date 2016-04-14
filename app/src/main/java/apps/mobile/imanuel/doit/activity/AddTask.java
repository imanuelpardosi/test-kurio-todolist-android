package apps.mobile.imanuel.doit.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import apps.mobile.imanuel.doit.R;
import apps.mobile.imanuel.doit.callback.CallbackGeneral;
import apps.mobile.imanuel.doit.callback.CallbackGetTask;
import apps.mobile.imanuel.doit.callback.CallbackLogin;
import apps.mobile.imanuel.doit.helper.GeneralHelper;
import apps.mobile.imanuel.doit.helper.RESTClient;
import apps.mobile.imanuel.doit.helper.UserLogInfo;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.Header;

public class AddTask extends AppCompatActivity {
    public Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText et_title;
    EditText et_description;
    TextView tv_date;
    TextView tv_time;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_time = (TextView) findViewById(R.id.tv_time);

        et_title = (EditText) findViewById(R.id.et_title);
        et_description = (EditText) findViewById(R.id.et_description);

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTask.this, "VIEW ALL", Toast.LENGTH_SHORT).show();
                new DatePickerDialog(AddTask.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTask.this, "VIEW ALL", Toast.LENGTH_SHORT).show();
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tv_time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        Button btn_add_task = (Button) findViewById(R.id.btn_add_task);


        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralHelper.makeToast(getApplicationContext(), UserLogInfo.getAccessToken(getApplicationContext()) + " " + et_title.getText().toString(), true);
                sendJSONRequestAddTask(UserLogInfo.getAccessToken(getApplicationContext()), 1, et_title.getText().toString(),
                        et_description.getText().toString(), "", "", "", "0");

                //Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                // nextScreen.putExtra("name", username.getText().toString());
                //startActivity(nextScreen);
            }
        });

    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tv_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void sendJSONRequestAddTask(String access_token, int category_id, String title, String description,
                                        String due_date, String created_time, String last_updated, String task_status) {
        Log.i("", "messagenya wooii");
        progressDialog = new ProgressDialog(AddTask.this);
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        RESTClient.getRestClient(getApplicationContext()).addTask(access_token, category_id, title, description,
                due_date, created_time, last_updated, task_status, new Callback<CallbackGeneral>() {

                    @Override
                    public void success(CallbackGeneral callbackGeneral, Response response) {
                        progressDialog.dismiss();
                        Log.i(callbackGeneral.getMessage(), "messagenya");

                        if (callbackGeneral.getMessage().equals("add task success")) {
                            GeneralHelper.makeToast(getApplicationContext(), "Add task berhasil", true);
                            startActivity(new Intent(AddTask.this, MainActivity.class));
                            AddTask.this.finish();
                        } else {
                            GeneralHelper.makeToast(getApplicationContext(), "Periksa kembali username/password anda", true);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        GeneralHelper.makeToast(getApplicationContext(), "Periksa koneksi anda, error: " + error, true);
                    }
                });
    }
}
