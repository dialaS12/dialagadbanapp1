package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignInMainActivity2 extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etpassword;
    private Button btnSingin;
    private Button btnSingup;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main2);

        etEmail= (TextInputEditText) findViewById(R.id.etEmail);
        etpassword= (TextInputEditText) findViewById(R.id.etPassword);
        btnSingin= (Button) findViewById(R.id.btnSingin);
        btnSingup= (Button) findViewById(R.id.btnSingup);

    }
    public void onClickSingUp(View v)
    {
        //to open new activity from current to next
        Intent i= new Intent(SignInMainActivity2.this, SingUpActivity.class);
        startActivity(i);
        //to close current activity
       finish();
    }
    public void onClickSingincheck(View v)
    {
        checkEmailPassw();
    }
    public void checkEmailPassw()
    {
        boolean isAllOk=true;
        String email=etEmail.getText().toString();
        String password=etpassword.getText().toString();
        if (email.length()<6 ||email.contains("@")==false);
        {
            isAllOk=false;
            etEmail.setError("Wrong Email");
        }
        if (password.length()<8||password.contains("")==true);
        {
            isAllOk=false;
            etpassword.setError("Wrong Password");
        }
        if (isAllOk)
        {
            Toast.makeText(this,"All Ok",Toast.LENGTH_SHORT).show();
        }

    }








}