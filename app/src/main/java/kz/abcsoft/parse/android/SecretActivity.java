package kz.abcsoft.parse.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;


public class SecretActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_secret);

        ParseUser user = ParseUser.getCurrentUser() ;
        String username = user.getUsername().toString() ;

        TextView userTextView = (TextView) findViewById(R.id.textViewUser_Secret);
        userTextView.setText("Вы вошли как " + username);


        Button logoutButton = (Button) findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ParseUser.logOut();
                finish();
            }
        });

    }
}
