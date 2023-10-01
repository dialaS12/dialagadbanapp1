package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class SignInMainActivity2 extends AppCompatActivity {
    private EditText etEmail;
    private EditText etpassword;
    private Button btnSingin;
    private Button btnSingup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main2);

        etEmail= (EditText) findViewById(R.id.etEmail);
    }
}