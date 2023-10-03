package dga.example.dialagadbanapp1.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dga.example.dialagadbanapp1.data.mytaskTable.MyTask;
import dga.example.dialagadbanapp1.data.mytaskTable.myTaskQuery1;
import dga.example.dialagadbanapp1.data.subjectTable.MySubject;
import dga.example.dialagadbanapp1.data.subjectTable.MySubjectQuery1;
import dga.example.dialagadbanapp1.data.usersTable.MyUser;
import dga.example.dialagadbanapp1.data.usersTable.MyUserQuery;
@Database(entities = {MyUser.class, MySubject.class, MyTask.class},version = 5)

public abstract class AppDatabase extends RoomDatabase

{

    private static AppDatabase db;
    public abstract MyUserQuery getMyUserQuery();
    public abstract MySubjectQuery1 getMySubjectQuery();
    public abstract myTaskQuery1 getMyTaskQuery();

    public static AppDatabase getDB(Context context)
    {
        if(db==null)
        {
            db = Room.databaseBuilder(context, AppDatabase.class, "database-name")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }
}


