package sumago.androidipt.day10_b3_sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText etName, etPass, etRePass;
    TextInputLayout tilName, tilPass, tilRePass;
    Button btnSignIn;
    SharedPrefHelper spHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        spHelper = new SharedPrefHelper(this);
        if(spHelper.getIsSignedIn()){
            Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.etName);
        etPass = findViewById(R.id.etPassword);
        etRePass = findViewById(R.id.etRePass);
        tilName = findViewById(R.id.tilName);
        tilPass = findViewById(R.id.tilPassword);
        tilRePass = findViewById(R.id.tilRePass);
        btnSignIn = findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(v->{
            if(validateInputs()){
                //save data in sharedPreferences
                spHelper.addName(etName.getText().toString());
                spHelper.addPassword(etPass.getText().toString());

                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    private boolean validateInputs() {

        ArrayList<Boolean> result = new ArrayList<Boolean>();

        if(etName.getText().toString().length()>3){
            result.add(true);
            tilName.setError(null);
        }
        else{
            result.add(false);
            tilName.setError("Please enter valid name");
        }

        if(etPass.getText().toString().length()>3){
            result.add(true);
            tilPass.setError(null);
        }
        else{
            result.add(false);
            tilName.setError("Please enter valid password");
        }

        if(etPass.getText().toString().equals(etRePass.getText().toString())){
            result.add(true);
            tilPass.setError(null);
            tilRePass.setError(null);
        }
        else{
            result.add(false);
            tilRePass.setError("Password does not match");
            tilPass.setError("Password does not match");
        }

        return !result.contains(false);
    }
}