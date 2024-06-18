package sumago.androidipt.day10_b3_sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    TextView tvName;
    Button btn;
    SharedPrefHelper spHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvName = findViewById(R.id.tvName);
        btn = findViewById(R.id.btnLogout);
        spHelper = new SharedPrefHelper(this);

        tvName.setText(spHelper.getName());
        btn.setOnClickListener(v->{
            spHelper.clear();
            Intent i = new Intent(HomeActivity.this, SignUpActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
    }
}