package sumago.androidipt.day10_b3_sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {

    public static final String KEY_SHARED_PREF_NAME = "UserData";
    public static final String KEY_IS_SIGNED_IN = "isSignedIn";
    public static final String KEY_PASSWORD = "pass";
    public static final String KEY_NAME = "name";
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    public SharedPrefHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void addName(String name){
        spEditor.putString(KEY_NAME, name);
        spEditor.apply();
    }

    public void addPassword(String pass){
        spEditor.putString(KEY_PASSWORD, pass);
        spEditor.apply();
    }

    public String getName(){
        return sharedPreferences.getString(KEY_NAME, "default");
    }

    public String getPassword(){
        return sharedPreferences.getString(KEY_PASSWORD, "default");
    }

    public void addIsSignedIn(boolean b) {
        spEditor.putBoolean(KEY_IS_SIGNED_IN, b);
        spEditor.apply();
    }

    public boolean getIsSignedIn(){
        return sharedPreferences.getBoolean(KEY_IS_SIGNED_IN, false);
    }

    public void clear() {
        spEditor.clear();
        spEditor.apply();
    }
}
