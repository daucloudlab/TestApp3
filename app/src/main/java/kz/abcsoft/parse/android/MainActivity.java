package kz.abcsoft.parse.android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends ActionBarActivity {

    private String mPassword;
    private String mUsername;

    private EditText mPasswordEditText;
    private EditText mUsernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ParseAnalytics.trackAppOpenedInBackground(getIntent()) ;

        if (!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            ParseUser user = ParseUser.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(MainActivity.this, SecretActivity.class);
                startActivity(intent);
                finish();
            }
        }

        mUsernameEditText = (EditText) findViewById(R.id.editUserName);
        mPasswordEditText = (EditText) findViewById(R.id.editPassword);

        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        Button signupButton = (Button) findViewById(R.id.buttonSignup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUsername = mUsernameEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();
                ParseUser user = ParseUser.getCurrentUser() ;

                ParseUser.logInInBackground(mUsername, mPassword, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            Intent intent = new Intent(MainActivity.this, SecretActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Вход разрешен", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Такого пользователя не существует. Зарегистрируетесь!",
                                    Toast.LENGTH_LONG).show();


                        }
                    }
                }) ;

            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = mUsernameEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();

                if (mUsername.equals("") && mPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Заполните все поля",
                            Toast.LENGTH_LONG).show();
                } else {
                    ParseUser user = new ParseUser();
                    user.setUsername(mUsername);
                    user.setPassword(mPassword);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getApplicationContext(), "Вы зарегистрированы. Войдите в систему",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Ошибка при регистрации. Попробуйте еще раз",
                                        Toast.LENGTH_LONG).show();
                                ;

                            }
                        }
                    });
                }
            }
        });


    }
}
