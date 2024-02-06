package dga.example.dialagadbanapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.usersTable.MyUser;
import dga.example.dialagadbanapp1.data.usersTable.MyUserQuery;


public class SignInMainActivity2 extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etpassword;
    private Button btnSingin;
    private Button btnSingup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main2);

        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etpassword = (TextInputEditText) findViewById(R.id.etPassword);
        btnSingin = (Button) findViewById(R.id.btnSingin);
        btnSingup = (Button) findViewById(R.id.btnSingup);

    }

    public void onClickSingUp(View v) {
        //to open new activity from current to next
        Intent i = new Intent(SignInMainActivity2.this, SingUpActivity.class);
        startActivity(i);

    }

    public void onClickSingincheck(View v) {
        checkEmailPassw();
    }

    public void checkEmailPassw() {
        boolean isAllOk = true;//يحوي نتيجة فحص الحقول ان كانت سليمه
        String email = etEmail.getText().toString();//استخراج النص من حقل الايميل
        String password = etpassword.getText().toString(); //استخراج نص كلمه المرور

        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي @ فهو خطا
        if (email.length() < 6 || email.contains("@") == false)
        {
            //تعديل المتغير ليدل على ان الفحص يعطي نتيجة خاطئه
            isAllOk = false;
            //عرض ملاحظة على الشاشة داخل حقل البريد
            etEmail.setError("Wrong Email");
        }
        if (password.length() < 8 || password.contains(" ") == true)
        {
            isAllOk = false;
            etpassword.setError("Wrong Password");
        }
        if (isAllOk) {
            Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();
            //بناء قاعدة البيانات وارجاع مؤشر عليها
            AppDatabase db = AppDatabase.getDB(getApplicationContext());
            // مؤشر لكائن عمليات الجدول
            MyUserQuery userQuery = db.getMyUserQuery();
            // ان لم يكن موجود ,استعداء العملية التي تنفذ الاستعلام الذي يفحص البريد وكلمه المرور ويعيد كائنا ان كان موجودا او لا
            MyUser myUser = userQuery.checkEmailPassw(email, password);
            if (myUser == null) {
                Toast.makeText(this, "Wrong Email Or Password`", Toast.LENGTH_LONG).show();


            } else {

                //ان كان هناالك حساب الايمل والبورد ننتقل الى شاشه الرئيسيه
                Intent i = new Intent(SignInMainActivity2.this, MainActivity3.class);
                startActivity(i);
                finish();


            }

        }


    }
    public void onClickSingincheck_FB(View v) {
        checkEmailPassw_FB();
    }

    public void checkEmailPassw_FB() {
        boolean isAllOk = true;//يحوي نتيجة فحص الحقول ان كانت سليمه
        String email1 = etEmail.getText().toString();//استخراج النص من حقل الايميل
        String password1 = etpassword.getText().toString(); //استخراج نص كلمه المرور

        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي @ فهو خطا
        if (email1.length() < 6 || email1.contains("@") == false)
        {
            //تعديل المتغير ليدل على ان الفحص يعطي نتيجة خاطئه
            isAllOk = false;
            //عرض ملاحظة على الشاشة داخل حقل البريد
            etEmail.setError("Wrong Email");
        }
        if (password1.length() < 8 || password1.contains(" ") == true)
        {
            isAllOk = false;
            etpassword.setError("Wrong Password");
        }
        if (isAllOk)
        {
            //עצם לביצוע רישום
            FirebaseAuth auth=FirebaseAuth.getInstance();
            //כניסה לחשבון בעזרת מיל וסיסמה
            auth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override//התגובה שמתקבל מהענן מניסיון הכניסה בענן
                public void onComplete(@NonNull Task<AuthResult> task) //הפרמטר מכיל מידע מהרשת על תוצאת הבקשה לרישום
                {
                    if (task.isSuccessful())//אם הפעולה הצליחה
                    {
                        Toast.makeText(SignInMainActivity2.this,"Signing in Succeeded",Toast.LENGTH_SHORT).show();
                        //מעבר למסך הראשי
                        Intent i=new Intent(SignInMainActivity2.this,MainActivity3.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(SignInMainActivity2.this,"Signing in Failed",Toast.LENGTH_SHORT).show();
                        etEmail.setError(task.getException().getMessage());//

                    }
                }

            });
            FirebaseAuth.getInstance().signOut();//לביצוע רישום יציאה
        }
    }

    }