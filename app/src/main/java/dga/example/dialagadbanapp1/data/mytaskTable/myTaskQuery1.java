package dga.example.dialagadbanapp1.data.mytaskTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface myTaskQuery1
{
    /**
     *واجهه استعلامات
     */
    @Dao
    public interface MyUserQuery {
        @Query("SELECT * FROM MyUser")
        List<MyTask> getAllTasks();
        @Insert
        /**
         * ثلاثه نقاط تعني ادخال مجموعه
         */
        void insertAll(MyTask... t);

        @Query("SELECT * FROM MyUser WHERE keyid IN (:userIds)")
        List<MyTask> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM MyUser WHERE email = :myEmail AND " +
                "passw = :myPassw LIMIT 1")
        MyTask checkEmailPassw(String myEmail, String myPassw);



        @Delete
        void delete(MyTask Task);

        @Query("Delete From MyTask WHERE keyid=:id ")
        void delete(int id);

        @Insert
        void insert(MyTask myUser);
        @Update
        void update(MyTask...values);
    }


}
