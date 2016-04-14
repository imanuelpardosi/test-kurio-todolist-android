package apps.mobile.imanuel.doit.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.Response;

import apps.mobile.imanuel.doit.R;
import apps.mobile.imanuel.doit.callback.CallbackLogin;
import apps.mobile.imanuel.doit.helper.GeneralHelper;
import apps.mobile.imanuel.doit.helper.RESTClient;
import apps.mobile.imanuel.doit.helper.UserLogInfo;
import retrofit.Callback;
import retrofit.RetrofitError;

public class Login extends AppCompatActivity {
    EditText username, password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendJSONRequestDoLogin(username.getText().toString(), password.getText().toString());
                //Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                // nextScreen.putExtra("name", username.getText().toString());
                //startActivity(nextScreen);
            }
        });
    }

    private void sendJSONRequestDoLogin(final String username, final String password) {
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        RESTClient.getRestClient(getApplicationContext()).doLogin(username, password, new Callback<CallbackLogin>() {
            @Override
            public void success(CallbackLogin callbackLogin, retrofit.client.Response response) {
                progressDialog.dismiss();
                Log.i(username, "uname");
                Log.i(password,"pw");

                if (callbackLogin.getMessage().equals("login berhasil")) {
                    GeneralHelper.makeToast(getApplicationContext(), "Login berhasil", true);
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Login.this.finish();

                    UserLogInfo.setAccessToken(getApplicationContext(), callbackLogin.getResponse().getAccess_token());
                    UserLogInfo.setUserId(getApplicationContext(), callbackLogin.getResponse().getId());
                    UserLogInfo.setUsername(getApplicationContext(), callbackLogin.getResponse().getUsername());
                } else {
                    GeneralHelper.makeToast(getApplicationContext(), "Periksa kembali username/password anda", true);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                GeneralHelper.makeToast(getApplicationContext(), "Periksa koneksi anda", true);
            }
        });
    }
}
