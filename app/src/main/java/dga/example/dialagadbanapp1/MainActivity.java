package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import dga.example.dialagadbanapp1.data.AppDatabase;
import dga.example.dialagadbanapp1.data.subjectTable.MySubject;
import dga.example.dialagadbanapp1.data.subjectTable.MySubjectQuery1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("fi","on create");
        Toast.makeText(this, "on create", Toast.LENGTH_SHORT).show();
        //1 - بناء قاعدة بيانات وارجاع موشر عبليها
        AppDatabase db =AppDatabase.getDB(getApplicationContext());
        //2 - مؤشر لكائن عمليات الجدول
        MySubjectQuery1 subjectQuery = db.getMySubjectQuery();
        // مثال لاستعمال جدول البيانات
        // 3- بناء كائن من نوع جدول وتحديد قيم الصفات
        MySubject s1=new MySubject();
        s1.title="computer";
        MySubject s2=new MySubject();
        s2.title="math";
        //4- اضافة كائن للجدول.
        subjectQuery.insert(s1);
        subjectQuery.insert(s2);
        //فحص هل تم حفظ ما سبق -5
        // استخراج وطباعة جميع معطيات جدول المواضيع
        List<MySubject> all = subjectQuery.getAll();
        for (MySubject s:all)
              {
                  Log.d("SM",s.title);
                  Toast.makeText(this,s.title,Toast.LENGTH_LONG);

        }



    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("fi","onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("fi","onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("fi","onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("fi","onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}