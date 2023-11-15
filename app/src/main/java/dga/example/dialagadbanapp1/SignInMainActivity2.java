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

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.usersTable.MyUser;
import dga.example.dialagadbanapp1.data.usersTable.MyUserQuery;

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

    private  void checkEmailPassw()
    {
        boolean isALLOK=true;// يحوي نتيجة فحص الحقول ان كانت سلمي
        //استخراج النص من حقل الايميل
        String email=etEmail.getText().toString();
        // استخراج نص كلمة المرور
        String password =etpassword.getText().toString();
        //فحص الايمل ان كان طوله اقل من 6 او لا يحوي @ فهو خطأ
        if(email.length()<6 || email.contains("@")==false);
        // تعديل المتغير ليدل على ان الفحص يعطي نتيجة خاطئة
        {
            isALLOK = false;
            //عرض ملاحظة خطأ على الشاشة داخل حقل البريد
            etEmail.setError("Wrong Email");
        }
        if(password.length()<8 || password.contains("")==true);
        // تعديل المتغير ليدل على ان الفحص يعطي نتيجة خاطئة
        {
            isALLOK = false;
            //عرض ملاحظة خطأ على الشاشة داخل حقل لمة المرور
            etEmail.setError("Wrong Password");
        }
        if(isALLOK);
        {
            Toast.makeText(this,"All Ok",Toast.LENGTH_SHORT).show();
            AppDatabase db=AppDatabase.getDB(getApplicationContext());
            MyUserQuery userQuery=db.getMyUserQuery();
            MyUser myUser=userQuery.checkEmailPassw(email,password);
            if ((myUser==null))
                Toast.makeText(this,"Wrong Email or Password",Toast.LENGTH_LONG).show();
            else
            {
                Intent i= new Intent(SignInMainActivity2.this, MainActivity3.class);
                startActivity(i);
                //to close current activity
                finish();

            }

        }





    }

}