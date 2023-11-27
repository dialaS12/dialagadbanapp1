package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.mytaskTable.MyTask;
import dga.example.dialagadbanapp1.data.subjectTable.MySubject;
import dga.example.dialagadbanapp1.data.subjectTable.MySubjectQuery1;

public class MainActivity3 extends AppCompatActivity {
    //spnr1 تعريف صفه للكائن المرئي
    private Spinner spnrSubject;
    private ListView istvTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        istvTasks= (ListView) findViewById(R.id.istvTasks);
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
        AppDatabase db=AppDatabase.getDB(getApplicationContext());//قاعدة بناء

        List<MyTask>allSubject=allTasks.getAll();//استخراج جميع المواضيع

        ArrayAdapter<String>tasktAdapter=new ArrayAdapter<MyTask>(this,android.R.layout.simple_list_item_1);
        tasktAdapter.add(allTasks);
        lstTasks.setAdapter(tasktAdapter);
    }



    }
