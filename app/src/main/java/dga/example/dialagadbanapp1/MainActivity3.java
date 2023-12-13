package dga.example.dialagadbanapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);//الربط بين فئة الجافا وملف التنسيق
        //تحديد مؤشر

        istvTasks = findViewById(R.id.istvTasks);
        spnrSubject = (Spinner) findViewById(R.id.spnrSubject);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            //داله معالجة الحدث (تتلقلى برامتر من نوع view وتبدا ب public void
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, AddTaskActivity1.class);
                startActivity(i);

            }
        });

        //spnr2 وضع مؤشر الصفه على الكائن المرئي الموجود بواجهه المستعمل
        spnrSubject = findViewById(R.id.spnrSubject);
        //spnr3 بناء الوسيط وتحديد واجهه تنسيق لمعطى واحد
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spnr4 data sourceمصدر معطيات (ممكن ان يكون قائمة من قاعدة بيانات مثلا)
        String[] ar = {"Math", "CS", "Phs", "Arb", "Eng"};
        //spnr5تحديد المعطيات للوسيط
        adapter.addAll(ar);
        //spnr6ربط الكائن المرئي بالوسيط
        spnrSubject.setAdapter(adapter);

    }

    /**
     * عملية تجهيز السبنر بالمواضيع
     */

    private void initSubjectSpnr() {

        AppDatabase db = AppDatabase.getDB(getApplicationContext());// قاعدة بناء
        MySubjectQuery1 subjectQuery = db.getMySubjectQuery();//  عمليات جدول المواضيع
        List<MySubject> allSubjects = subjectQuery.getAll();// استخراج جميع المواضيع
        // تجهيز الوسيط
        ArrayAdapter<String> SubjectAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line);
        SubjectAdapter.add("ALL");//ستظهر اولا بالسبنر تعني عرض جميع المهمات
        for (MySubject subject : allSubjects)//  اضافة المواضبع للوسيط
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
                    initAllListView();//استدعاء الداله التي  تجهيز قائمة جميع المهمهات وعرضها ب list view
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

    @Override//داله لكي يبين التلت نقاط في الشاشه
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    /**
     * تجهيز قائمة جميع المهمهات وعرضها ب list view
     */
    private void initAllListView() {

        AppDatabase db = AppDatabase.getDB(getApplicationContext());// قاعدة بناء

        MyTaskQuery1 taskQuery1 = db.getMyTaskQuery();
        List<MyTask> allTasks = taskQuery1.getAllTasks();
        ArrayAdapter<MyTask> TaskAdapter = new ArrayAdapter<MyTask>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line);

        TaskAdapter.addAll(allTasks);
        istvTasks.setAdapter(TaskAdapter);
        istvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPopUpMenu(view,TaskAdapter.getItem(i));
            }
        });


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
     *
     * @param v
     */
    public void showMenu(View v) {
        // بناء قائمة popup menu
        PopupMenu popup = new PopupMenu(this, v);//الكائن الذي سبب فتح القائمة v
        popup.inflate(R.menu.pupupmenu);//ملف القائمة
        popup.show();//فتح وعرض القائمة

    }

    public void onClick(View v) {
        showMenu(v);
    }

    /**
     * داله مساعدة لفتح قائمة تتلقى بارمتر للكائن الذي سبب فتح القائمة
     *
     * @param v
     * @param item
     */
    public void showPopUpMenu(View v, MyTask item) {
        //بناء القائمة popup menu
        PopupMenu popup = new PopupMenu(this, v);//لكائن الذي سبب فتح القائمة v
        //ملف القائمة
        popup.inflate(R.menu.pupupmenu);
        //اضافة معالج حدث لاختيار عنصر من القائمة
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.mnComplete) {
                    //هنا نكتب ردة فعل لاختيار هذا العنصر من القائمة
                    Toast.makeText(MainActivity3.this, "Complete", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity3.this, AddTaskActivity1.class);
                    startActivity(i);
                }
                if (menuItem.getItemId() == R.id.mnDelete) {
                    Toast.makeText(MainActivity3.this, "Delete", Toast.LENGTH_SHORT).show();
                }
                if (menuItem.getItemId() == R.id.mnEdit) {
                    Toast.makeText(MainActivity3.this, "Edit", Toast.LENGTH_SHORT).show();

                }
                return true;
            }


        });
        popup.show();//فتح وعرض القائمة
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId()==R.id.itmSettings)
        {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();

        }
        if (item.getItemId()==R.id.itmSignOut) {
            Toast.makeText(this, "SignOut", Toast.LENGTH_SHORT).show();
            showYesNoDialog();

        }
        if (item.getItemId()==R.id.itemAddTask)
        {
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
            //to open new activity from current to next activity
            Intent i = new Intent(MainActivity3.this, AddTaskActivity1.class);
            startActivity(i);
        }
        return true;
        }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("HA","onRestart");
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("HA","onStart");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HA","onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        initSubjectSpnr();
        initAllListView();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("HA","onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HA","onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("HA","onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    /**
     * بناء ديالوج
     */
    public void showYesNoDialog()
        {
            //جهيز بناء شباك حوار "دايلوغ"يتلقى برامتر مؤشر للنشاط(اكتيففتي)الحالي
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Log Out");//تحديد العوان
            builder.setMessage("Are You Sure?");//تحديد فحوى شباك الحوار
            //الضغط على الزر ومعالج الحدث
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //معالجة حدث للموافقه
                    Toast.makeText(MainActivity3.this, "Signing Out", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            //الضغط على الزر ومعالج الحدث
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //معالجة حدث للموافقه
                    Toast.makeText(MainActivity3.this, "Signing Out", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog=builder.create();//بناء شباك الحوار -دايالوج
            dialog.show();//عرض الشباك
        }
    //kjkljk







    }












