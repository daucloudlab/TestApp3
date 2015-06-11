package kz.abcsoft.parse.android.app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "tlLma6OBWC8Ws67MMNgGMhokqrkpdnJaxezjOjmW", "qyz1ZABZ5AfVEoHkc1QxUYAY5dkVeCPNwFViYwka");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL() ;
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
