package dga.example.dialagadbanapp1;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.usersTable.MyUser;
import dga.example.dialagadbanapp1.data.usersTable.MyUserQuery;

public class SingUpActivity extends AppCompatActivity
{
    private TextInputEditText etEmail;
    private TextInputEditText etpassword;
    private TextInputEditText etRePassword;
    private TextInputEditText etName;
    private TextInputEditText etPhone;
    private Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        etEmail= (TextInputEditText) findViewById(R.id.etEmail);
        etpassword= (TextInputEditText) findViewById(R.id.etPassword);
        etRePassword= (TextInputEditText) findViewById(R.id.etRePassword);
        etName= (TextInputEditText) findViewById(R.id.etName);
        etPhone= (TextInputEditText) findViewById(R.id.etPhone);
        btnSave= (Button) findViewById(R.id.btnSave);

    }
    public void onClickSave(View v)

    {
        checkEPRP();
    }

    public void checkEPRP() {
        boolean isAllOk = true;
        String name=etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etpassword.getText().toString();
        String repassword = etRePassword.getText().toString();
        String phone=etPhone.getText().toString();


        if (email.length() < 6 || email.contains("@") == false)
        {
            isAllOk = false;
            etEmail.setError("Wrong Email");
        }
        if (password.length() < 8 || password.contains(" ") == true)
        {
            isAllOk = false;
            etpassword.setError("Wrong Password");
        }

        if (repassword.length() !=password.length() || repassword.equals(password) == false)
        {
            isAllOk=false;
            etRePassword.setError("Wrong RePassword");
        }

        if (phone.length()<10||phone.length()>10||phone.length()!=10||phone.contains(" ")==true)
        {
            isAllOk=false;
            etPhone.setError("Wrong Phone");
        }
        if (isAllOk)
        {
            Toast.makeText(this, "All Ok`", Toast.LENGTH_SHORT).show();
            AppDatabase db=AppDatabase.getDB(getApplicationContext());
            MyUserQuery userQuery=db.getMyUserQuery();
            //فحص هل الايميل موجود من قبل ان يتم التسجيل من قبل
            if (userQuery.checkEmail(email)!=null)
           {
                etEmail.setError("found email");
            }
            else //ان لم يكن الايميل موجودا نقوم ببناء كائن للمستعمل وادخاله في الجدول MyUser للستعملين
            {
                //بناء الكائن
                MyUser myUser=new MyUser();
                //تحديد قيم الصفات بالقيم التي استخرجناها
                myUser.fullName=name;
                myUser.phone=phone;
                myUser.passw=password;
                myUser.email=email;
                //اضافة الكائن الجديد للجدول
                userQuery.insert((myUser));
                //اغلاق الشاشة الحالية
                finish();



            }


        }

    }
}

