package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

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
            public void onClick(View v)
            {
                Intent i= new Intent(MainActivity3.this, AddTaskActivity1.class);
                startActivity(i);

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
        spnrSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // استخراج الموضوع حسب رقمه الترتيبي i
                String item = SubjectAdapter.getItem(i);
                if (item.equals("ALL"))//هذا يعني اعترض جميع المهام
                {
                    initAllListView();
                } else {
                    //استخراج كائن الموضوع الذي اخترناه لاسنخراج رقمه id
                    MySubject subject = subjectQuery.checkSubject(item);
                    //استدعاء العملية التي تجهز القائمة حسب رقم الموضوع
                    initListViewBySubjId(subject.key_id);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


            private void initAllListView() {

                AppDatabase db = AppDatabase.getDB(getApplicationContext());// قاعدة بناء

                MyTaskQuery1 taskQuery1 = db.getMyTaskQuery();
                List<MyTask> allTasks = taskQuery1.getAllTasks();
                ArrayAdapter<MyTask> TaskAdapter = new ArrayAdapter<MyTask>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line);

                TaskAdapter.addAll(allTasks);
                istvTasks.setAdapter(TaskAdapter);

            }

            /**
             * تجهيز قا~مة المهمات حسب رقم الموضوع
             * رقم الموضوع param key_id
             */
            private void initListViewBySubjId(long key_id) {
                AppDatabase db = AppDatabase.getDB(getApplicationContext());
                MyTaskQuery1 taskQuery1 = db.getMyTaskQuery();
                // يجب اضافة عملية تعيد جميع المهمات حسب رقم الموضوغ
                List<MyTask> allTasks = taskQuery1.getTasksBySubjId(key_id);
                ArrayAdapter<MyTask> TaskAdapter = new ArrayAdapter<MyTask>(this, android.R.layout.simple_list_item_1);
                TaskAdapter.addAll(allTasks);
                istvTasks.setAdapter(TaskAdapter);

            }
    /**
     * داله مساعدة لفنح قائمة تتلقى بارمتر للكائن الذي سبب فتح القائمة
     * @param v
     */
    public void showPopUpMenu(View v) {
        // بناء قائمة popup menu
        PopupMenu popup = new PopupMenu(this, v);//visilel|
        popup.inflate (R.menu.pupupmenu);
        popup.show();

    }
    public  void onClick(View v)
    {
        showPopUpMenu(v);
    }

    /**
     * داله مساعدة لفتح قائمة تتلقى بارمتر للكائن الذي سبب فتح القائمة
     * @param v
     * @param item
     */
    public void showPopUpMenu(View v,MyTask item)
    {
        //بناء القائمة popup menu
        PopupMenu popup=new PopupMenu(this,v);//لكائن الذي سبب فتح القائمة v
        //
        popup.inflate(R.menu.pupupmenu);
        //
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                return true;
            }
        });
    }
    public boolean onMenultemClick(MenuItem menultem)
    {
        if (menultem.getItemId()==R.id.mnAddTask)
        {
            Toast.makeText(MainActivity3.this,"Add",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity3.this,AddTaskActivity1.class);
            startActivity(i);

            if(menultem.getItemId()==R.id.mnDelete) {
                Toast.makeText(MainActivity3.this, "Delete", Toast.LENGTH_SHORT).show();

                if (menultem.getItemId() == R.id.mnEdit) {
                    Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            popup.show;





            }





