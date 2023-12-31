package dga.example.dialagadbanapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.mytaskTable.MyTask;
import dga.example.dialagadbanapp1.data.subjectTable.MySubject;
import dga.example.dialagadbanapp1.data.subjectTable.MySubjectQuery1;

public class AddTaskActivity1 extends AppCompatActivity {
    private Button btnSaveTask;
    private Button btnCancelTask;
    private TextView tvlmportance;
    private SeekBar skbrlmportance;
    private TextInputEditText etShortTitle;
    private TextInputEditText etText;
    private AutoCompleteTextView autoEtSubj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task1);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancelTask = findViewById(R.id.btnCancelTask);
        tvlmportance = findViewById(R.id.tvlmportance);
        skbrlmportance = findViewById(R.id.skbrlmportance);
        etShortTitle = findViewById(R.id.etShortTitle);
        etText = findViewById(R.id.etText);
        autoEtSubj =findViewById(R.id.autoEtSubj);
        initAutoEtSubject();

    }

    /**
     * استخراج اسماء المواضيع من جدول وعرضه بالحقل من نوعه
     * AutoCompleteTextView
     * "طريقه التعامل معه شبيه بالسبنر"
     */
    private void initAutoEtSubject() {
        //مؤشر لقاعدة البيانات
        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        //مؤشر لواجهه استعمالات جدول المواضيع
        MySubjectQuery1 subjectQuery1 = db.getMySubjectQuery();
        //مصدر معطيات: استخراج جميع المواضيع من الجدول
        List<MySubject> allSubject = subjectQuery1.getAll();
        //تجهيز الوسيط
        ArrayAdapter<MySubject> adapter = new ArrayAdapter<MySubject>(this, android.R.layout.simple_dropdown_item_1line);
        adapter.addAll(allSubject);//اضافة جميع المعطيات للوسيط
        autoEtSubj.setAdapter(adapter);//ربط الحقل بالوسيط
        //معالجه حدث لعرض المواضيع عند الضغط على الحقل
        autoEtSubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoEtSubj.showDropDown();//لكي عندما يفتح السبنر يبينو لتحت كلهن

            }

        });
    }

    private void checkAndSaveTask() {
        String subjText = etText.getText().toString();
        String shortTitle = etShortTitle.getText().toString();
        String autoSubj = autoEtSubj.getText().toString();
        String tvlmportance1 = tvlmportance.getText().toString();
        int importance = skbrlmportance.getProgress();
        boolean isAllOk = true;
        if (shortTitle.length() == 0) {
            isAllOk = false;
            etShortTitle.setError("ShortTitle Not Found");
        }

        if (isAllOk) {
            AppDatabase db = AppDatabase.getDB(getApplicationContext());
            MySubjectQuery1 subjectQuery1 = db.getMySubjectQuery();
            if (subjectQuery1.checkSubject(subjText) == null)//فحص هل الموضوع موجود من قبل بالجدول
            {//بناء موضوع جديد واضافته
                MySubject subject = new MySubject();
                subject.title = subjText;
                subjectQuery1.insert(subject);
            }
            // ااستخراج id الموضوع لاننا بحاجة لرقمه التسلسلي
            MySubject subject = subjectQuery1.checkSubject(subjText);
            // بناء مهمه جديدة وتحديدة صفاتها
            MyTask task = new MyTask();
            task.importance = importance;
            task.shortTitle = shortTitle;
            task.text = subjText;
            task.subjId = subject.key_id;//تحديد رقم موضوع المهمه
            db.getMyTaskQuery().insertAll(task);//اضافة المهمه للجدول
            finish();//اغلاق الشاشة

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;

        }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.itmSettings)
        {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.itmSignOut)
        {
            Intent i = new Intent(AddTaskActivity1.this,SignInMainActivity2.class);
            startActivity(i);

        }
        return true;
    }



}


