package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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
        autoEtSubj=findViewById(R.id.autoEtSubj);
        initAutoEtSubject();

    }

    /**
     * استخراج اسماء المواضيع من جدول وعرضه بالحقل من نوعه
     * AutoCompleteTextView
     * "طريقه التعامل معه شبيه بالسبنر"
     */
    private void initAutoEtSubject() {
        //
        AppDatabase db = AppDatabase.getDB(getApplicationContext());
        //
        MySubjectQuery1 subjectQuery1 = db.getMySubjectQuery();
        //
        List<MySubject> allSubject = subjectQuery1.getAll();
        //
        ArrayAdapter<MySubject> adapter = new ArrayAdapter<MySubject>(this, android.R.layout.simple_dropdown_item_1line);
        adapter.addAll(allSubject);//
        autoEtSubj.setAdapter(adapter);//
        //
        autoEtSubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoEtSubj.showDropDown();

            }

        });
    }
    private void checkAndSaveTask()
    {
        boolean isAllOk = true;
        String subjText=etText.getText().toString();

        if (isAllOk)
        {
            AppDatabase db=AppDatabase.getDB(getApplicationContext());
            MySubjectQuery1 subjectQuery1=db.getMySubjectQuery();
            if (subjectQuery1.checkSubject(subjText)==null)//
            {
                MySubject subject=new MySubject();
                subject.title=subjText;
                subjectQuery1.insert(subject);
            }
            //
            MySubject subject=subjectQuery1.checkSubject(subjText);
            //
            MyTask task=new MyTask();
            task.importance= task.importance;
            task.shortTitle=

        }
    }

    }


