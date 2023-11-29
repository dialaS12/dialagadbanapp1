package dga.example.dialagadbanapp1.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dga.example.dialagadbanapp1.data.mytaskTable.MyTask;
import dga.example.dialagadbanapp1.data.mytaskTable.MyTaskQuery1;
import dga.example.dialagadbanapp1.data.subjectTable.MySubject;
import dga.example.dialagadbanapp1.data.subjectTable.MySubjectQuery1;
import dga.example.dialagadbanapp1.data.usersTable.MyUser;
import dga.example.dialagadbanapp1.data.usersTable.MyUserQuery;

/*
تعريف الجداول ورقم الاصدار
Version
عند تغيير اي شيئ يخص جدول او جداول علينا تغيير رقم الاصدار ليتم بناء قاعدة البيانات من جديد
 */
@Database(entities = {MyUser.class, MySubject.class, MyTask.class},version = 2)

/**
 * الفئة المسؤولة عن بناء قاعدة البيانات بكل جداولها وتوفر لنا كائن نتعامل مع قاعدة البيانات
 */

public abstract class AppDatabase extends RoomDatabase {
    /**
     * كائن للتعامل مع قاعدة البيانات
     */
    private static AppDatabase db;

    /**
     * يعيد مؤشر لعمليات جدول المستعملين
     * @return
     */
    public abstract MyUserQuery getMyUserQuery();

    /**
     * يعيد كتئن لعمليات جدول المواضيع
     * @return
     */
    public abstract MySubjectQuery1 getMySubjectQuery();

    /**
     *  يعيد كتئن لعمليات جدول المهمات
     * @return
     */
    public abstract MyTaskQuery1 getMyTaskQuery();

    /**
     * بناء قاعدة البيانات واعادة كائن يؤشر عليها
     * @param context
     * @return
     */

    public static AppDatabase getDB(Context context)
    {
        if(db==null)
        {
            db = Room.databaseBuilder(context, AppDatabase.class, "database-DialaDataBase")//اسم قاعدة البيانات
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return db;

    }
}


