package dga.example.dialagadbanapp1;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.usersTable.MyUser;
import dga.example.dialagadbanapp1.data.usersTable.MyUserQuery;

public class SingUpActivity extends AppCompatActivity {
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
        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etpassword = (TextInputEditText) findViewById(R.id.etPassword);
        etRePassword = (TextInputEditText) findViewById(R.id.etRePassword);
        etName = (TextInputEditText) findViewById(R.id.etName);
        etPhone = (TextInputEditText) findViewById(R.id.etPhone);
        btnSave = (Button) findViewById(R.id.btnSave);

    }

    public void onClickSave(View v) {
        checkEPRP();
    }

    public void checkEPRP() {
        boolean isAllOk = true;
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etpassword.getText().toString();
        String repassword = etRePassword.getText().toString();
        String phone = etPhone.getText().toString();


        if (email.length() < 6 || email.contains("@") == false) {
            isAllOk = false;
            etEmail.setError("Wrong Email");
        }
        if (password.length() < 8 || password.contains(" ") == true) {
            isAllOk = false;
            etpassword.setError("Wrong Password");
        }

        if (repassword.length() != password.length() || repassword.equals(password) == false) {
            isAllOk = false;
            etRePassword.setError("Wrong RePassword");
        }

        if (phone.length() < 10 || phone.length() > 10 || phone.length() != 10 || phone.contains(" ") == true) {
            isAllOk = false;
            etPhone.setError("Wrong Phone");
        }
        if (isAllOk) {
            Toast.makeText(this, "All Ok`", Toast.LENGTH_SHORT).show();
            AppDatabase db = AppDatabase.getDB(getApplicationContext());
            MyUserQuery userQuery = db.getMyUserQuery();
            //فحص هل الايميل موجود من قبل ان يتم التسجيل من قبل
            if (userQuery.checkEmail(email) != null) {
                etEmail.setError("found email");
            } else //ان لم يكن الايميل موجودا نقوم ببناء كائن للمستعمل وادخاله في الجدول MyUser للستعملين
            {
                //بناء الكائن
                MyUser myUser = new MyUser();
                //تحديد قيم الصفات بالقيم التي استخرجناها
                myUser.fullName = name;
                myUser.phone = phone;
                myUser.passw = password;
                myUser.email = email;
                //اضافة الكائن الجديد للجدول
                userQuery.insert((myUser));
                //اغلاق الشاشة الحالية
                finish();


            }

        }
    }
            public void onClickSave1 (View v)

            {
                checkEPRP_FB();
            }

            public void checkEPRP_FB()
        {
                boolean isAllOk1 = true;
                String name1 = etName.getText().toString();
                String email1 = etEmail.getText().toString();
                String password1 = etpassword.getText().toString();
                String repassword1 = etRePassword.getText().toString();
                String phone1 = etPhone.getText().toString();


                if (email1.length() < 6 || email1.contains("@") == false) {
                    isAllOk1 = false;
                    etEmail.setError("Wrong Email");
                }
                if (password1.length() < 8 || password1.contains(" ") == true) {
                    isAllOk1 = false;
                    etpassword.setError("Wrong Password");
                }

                if (repassword1.length() != password1.length() || repassword1.equals(password1) == false) {
                    isAllOk1 = false;
                    etRePassword.setError("Wrong RePassword");
                }

                if (phone1.length() < 10 || phone1.length() > 10 || phone1.length() != 10 || phone1.contains(" ") == true) {
                    isAllOk1 = false;
                    etPhone.setError("Wrong Phone");
                }
                if (isAllOk1) {
                   //كائن لعمليه التسجيل
                    FirebaseAuth auth= FirebaseAuth.getInstance();
                    //צירת חשבון בעזרת מיל וסיסמה
                    auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())//אם הפעולה הצליחה
                            {
                                Toast.makeText(SingUpActivity.this, "Signing up Succeeded", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(SingUpActivity.this, "Signing up Failed", Toast.LENGTH_SHORT).show();
                                etEmail.setError(task.getException().getMessage());//הצגת הודעת השיגאה שהקבלה מהענן
                            }
                        }
                    });



                    }


                }


            }







