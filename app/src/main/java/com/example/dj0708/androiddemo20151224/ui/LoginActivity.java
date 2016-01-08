package com.example.dj0708.androiddemo20151224.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.dj0708.androiddemo20151224.R;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.os.Handler;

/**
 * Created by dj0708 on 16/1/4.
 */
public class LoginActivity extends Activity {
    private EditText       userNameEditText;
    private EditText       passwordEditText;
    private Button         loginBtn;

    private final String         KEY_LOGIN_RESPONSE = "loginResponse";

    public final static String          LOGIN_URL_STR = "https://www.pj.com/oauth/token";
    public final static String          REQUEST_CHARSET_TYPE = "UTF-8";
    public final static int             REQUEST_TIME_OUT = 10000;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Bundle dataBundle = message.getData();
            String responseStr = dataBundle.getString("loginResponse");
            System.out.print(responseStr);
        }
    };

    Runnable requestLoginRunable = new Runnable() {
        @Override
        public void run() {
            try {
                String userNameParameter = URLEncoder.encode("test", "UTF-8");
                String passwordParameter = URLEncoder.encode("123456", "UTF-8");
                String bodyStr = String.format("username=%s&password=%s&grant_type=password", userNameParameter, passwordParameter);
                URL url = new URL("http://192.168.1.169:18080/oauth/token");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Accept-Charset", "application/json");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                connection.setRequestProperty("Authorization", "Basic MzUzYjMwMmM0NDU3NGY1NjUwNDU2ODdlNTM0ZTdkNmE6Mjg2OTI0Njk3ZTYxNWE2NzJhNjQ2YTQ5MzU0NTY0NmM=");
                connection.setConnectTimeout(10000);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(bodyStr.getBytes("UTF-8"));
                InputStream inputStream = connection.getInputStream();

                int status = connection.getResponseCode();
                System.out.println(status);

                Message message = new Message();
                Bundle dataBundle = new Bundle();
                dataBundle.putString("loginResponse", "testtest message");
                message.setData(dataBundle);
                handler.sendMessage(message);
            } catch (Exception exception) {
                System.out.println("testtest out print");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEditText = (EditText)findViewById(R.id.userNameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        new Thread(requestLoginRunable).start();
    }

    /**
     * request login api with user name and password;
     * if request success provide message;
     * if failed provide message;
     * the client users see success or failed message lasts three seconds;
     * @userName not null;
     * @passWord not null;
     */
}
