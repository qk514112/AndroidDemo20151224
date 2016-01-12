package com.example.dj0708.androiddemo20151224.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
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
import android.widget.Toast;

/**
 * Created by dj0708 on 16/1/4.
 */
public class LoginActivity extends Activity {
    private EditText       userNameEditText;
    private EditText       passwordEditText;
    private EditText       responseEditText;
    private Button         loginBtn;

    private final static String          KEY_LOGIN_RESPONSE = "loginResponse";

    private final static String          LOGIN_URL_STR = "https://www.pj.com/oauth/token";
//private final static String          LOGIN_URL_STR = "http://www.baidu.com";

    private final static String          REQUEST_CHARSET_TYPE = "UTF-8";
    private final static String          BASIC_TOKEN_STR = "Basic MzUzYjMwMmM0NDU3NGY1NjUwNDU2ODdlNTM0ZTdkNmE6Mjg2OTI0Njk3ZTYxNWE2NzJhNjQ2YTQ5MzU0NTY0NmM=";
    private final static String          JSON_REQUEST_PROPERTY = "application/json";
    private final static String          WWW_FORM_REQUEST_PROPERTY = "application/x-www-form-urlencoded;charset=UTF-8";
    private final static String          ACCEPT_CHARSET = "Accept-Charset";
    private final static String          AUTHORIZATION = "Authorization";
    private final static String          CONTENT_TYPE = "Content-Type";
    private final static int             REQUEST_TIME_OUT = 10000;

    private final static String          TEST_USER_NAME = "test";
    private final static String          TEST_PASSWORD = "123456";
    private final static String          TEST_OUT_MESSAGE = "test test test";

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Bundle dataBundle = message.getData();
            String responseStr = dataBundle.getString(KEY_LOGIN_RESPONSE);
            responseEditText.setText(responseStr);
        }
    };

    Runnable requestLoginRunable = new Runnable() {
        @Override
        public void run() {
            try {
                String userNameParameter = URLEncoder.encode(TEST_USER_NAME, REQUEST_CHARSET_TYPE);
                String passwordParameter = URLEncoder.encode(TEST_PASSWORD, REQUEST_CHARSET_TYPE);
                String bodyStr = String.format("username=%s&password=%s&grant_type=password", userNameParameter, passwordParameter);
                URL url = new URL(LOGIN_URL_STR);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty(ACCEPT_CHARSET, JSON_REQUEST_PROPERTY);
                connection.setRequestProperty(CONTENT_TYPE, WWW_FORM_REQUEST_PROPERTY);
                connection.setRequestProperty(AUTHORIZATION, BASIC_TOKEN_STR);
                connection.setConnectTimeout(REQUEST_TIME_OUT);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(bodyStr.getBytes(REQUEST_CHARSET_TYPE));
                InputStream inputStream = connection.getInputStream();

                int status = connection.getResponseCode();
                System.out.println(status);

                Message message = new Message();
                Bundle dataBundle = new Bundle();
                dataBundle.putString(KEY_LOGIN_RESPONSE, TEST_OUT_MESSAGE);
                message.setData(dataBundle);
                handler.sendMessage(message);
            } catch (Exception exception) {
                Message message = new Message();
                Bundle dataBundle = new Bundle();
                dataBundle.putString(KEY_LOGIN_RESPONSE, exception.toString());
                message.setData(dataBundle);
                handler.sendMessage(message);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEditText = (EditText)findViewById(R.id.userNameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        responseEditText = (EditText)findViewById(R.id.responseEditText);
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
