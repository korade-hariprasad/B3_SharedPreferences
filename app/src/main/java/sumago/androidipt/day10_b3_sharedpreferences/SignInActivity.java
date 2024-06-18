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

public class SignInActivity extends AppCompatActivity {

    TextInputLayout tilName, tilPass;
    TextInputEditText etName, etPass;
    Button btnSignIn;
    SharedPrefHelper spHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        spHelper = new SharedPrefHelper(this);

        setContentView(R.layout.activity_sign_in);

        etName = findViewById(R.id.etName);
        etPass = findViewById(R.id.etPassword);
        tilName = findViewById(R.id.tilName);
        tilPass = findViewById(R.id.tilPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(v->{
            if(loginSuccess()){
                spHelper.addIsSignedIn(true);
                Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }else{
                Toast.makeText(this, "User Credentials does not match", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean loginSuccess() {

        ArrayList<Boolean> result = new ArrayList<>();
        if(etName.getText().toString().equals(spHelper.getName())){
            result.add(true);
            tilName.setError(null);
        }else{
            result.add(false);
            tilName.setError("No user found with this name");
        }
        if(etPass.getText().toString().equals(spHelper.getPassword())){
            result.add(true);
            tilPass.setError(null);
        }else{
            result.add(false);
            tilPass.setError("Incorrect Password");
        }

        return !result.contains(false);
    }
}