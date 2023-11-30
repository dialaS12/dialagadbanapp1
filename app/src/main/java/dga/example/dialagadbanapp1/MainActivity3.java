package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.security.Key;
import java.util.List;

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.mytaskTable.MyTask;
import dga.example.dialagadbanapp1.data.mytaskTable.MyTaskQuery1;
import dga.example.dialagadbanapp1.data.subjectTable.MySubject;
import dga.example.dialagadbanapp1.data.subjectTable.MySubjectQuery1;

public class MainActivity3 extends AppCompatActivity {
    //spnr1 تعريف صفه للكائن المرئي
    private Spinner spnrSubject;
    private ListView istvTasks;
    private FloatingActionButton fabAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        istvTasks= (ListView) findViewById(R.id.istvTasks);
        spnrSubject= (Spinner) findViewById(R.id.spnrSubject);
        fabAdd= (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //spnr2 وضع مؤشر الصفه على الكائن المرئي الموجود بواجهه المستعمل
        spnrSubject=findViewById(R.id.spnrSubject);
        //spnr3 بناء الوسيط وتحديد واجهه تنسيق لمعطى واحد
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spnr4 data sourceمصدر معطيات (ممكن ان يكون قائمة من قاعدة بيانات مثلا)
        String[]ar={"Math","CS","Phs","Arb","Eng"};
        //spnr5تحديد المعطيات للوسيط
        adapter.addAll(ar);
        //spnr6ربط الكائن المرئي بالوسيط
        spnrSubject.setAdapter(adapter);

    }
    private void initSubjectSpnr()
    {

        AppDatabase db=AppDatabase.getDB(getApplicationContext());// قاعدة بناء
        MySubjectQuery1 subjectQuery=db.getMySubjectQuery();//  عمليات جدول المواضيع
        List<MySubject> allSubjects=subjectQuery.getAll();// استخراج// جميع المواضيع
        // قاعدة بناء
        ArrayAdapter <String> SubjectAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line);

        SubjectAdapter.add("ALL");//ستظهر اولا بالسبنر تعني عرض جميع المهمات
        for (MySubject subject:allSubjects)//  اضافة المواضبع للوسيط
        {
            SubjectAdapter.addAll(subject.title);
        }

        spnrSubject.setAdapter(SubjectAdapter);//ربط السبنر بالوسيط

    }
    private void initAllListView()
    {

        AppDatabase db=AppDatabase.getDB(getApplicationContext());// قاعدة بناء

        MyTaskQuery1 taskQuery1=db.getMyTaskQuery();
        List<MyTask> allTasks=taskQuery1.getAllTasks();
        ArrayAdapter <MyTask>TaskAdapter=new ArrayAdapter<MyTask>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line);

        TaskAdapter.addAll(allTasks);
        istvTasks.setAdapter(TaskAdapter);

    }
    /**
     * تجهيز قا~مة المهمات حسب رقم الموضوع
     * رقم الموضوع param key_id
     */
    private void initListViewBySubjId(long key_id)
    {
        AppDatabase db=AppDatabase.getDB(getApplicationContext());
        MyTaskQuery1 taskQuery1=db.getMyTaskQuery();
        // يجب اضافة عملية تعيد جميع المهمات حسب رقم الموضوغ
        List<MyTask>allTasks=taskQuery1.getTasksBySubjId(key_id);
        ArrayAdapter <MyTask>TaskAdapter=new ArrayAdapter<MyTask>(this, android.R.layout.simple_list_item_1);
        TaskAdapter.addAll(allTasks);
        istvTasks.setAdapter(TaskAdapter);

    }

    }




//..