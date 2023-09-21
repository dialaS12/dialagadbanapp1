package dga.example.dialagadbanapp1.data.mytaskTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface myTaskQuery1 {
    /**
     * *اعادة جميع معطيات جدول المهمات
     *
     * @return * قائمة من المهمات
     */
    @Query("SELECT * FROM  MyTask")
    List<MyTask> getAllTasks();

    /**
     * -مرتبه حسب الوقت- استخراج المهمات حسب المستعمل
     *
     * @param useri_p
     * @return
     */
    @Query("SELECT * FROM  MyTask WHERE userId=:useri_p ORDER BY time DESC")
    List<MyTask> getAllTaskOrderBy(Long useri_p);

    /**
     * استخراج جميع المهمات حسب المستعمل-وهل تمت ام لا ؟ -مرتبه حسب الاهميه
     *
     * @param useri_p*     رقم المستعمل
     * @param iscomplte_p* هل تمت ام لا
     * @return
     */
    @Query("SELECT * FROM  MyTask WHERE userId=:useri_p AND isCompleted=:iscomplte_p ORDER BY importance DESC")
    List<MyTask> getAllTaskOrderBy(Long useri_p, boolean iscomplte_p);

    /**
     * ادخال مهمات
     *
     * @param t*مجموعة مهمات
     */
    @Insert
    /**
     * ثلاثه نقاط تعني ادخال مجموعه
     */
    void insertAll(MyTask... t);

    /**
     * *تعديل المهمات
     *
     * @param task *مجموعه مهمات للتعديل ىحسب المفتاح الرئيسي
     */
    @Update
    void update(MyTask... task);

    /**
     * حذف مهمات او مهمه
     *
     * @param tasks
     */
    @Delete
    void deleteTask(MyTask... tasks);

    @Query("DELETE FROM MYTASK WHERE keyid=:id")
    void deletelTask(Long id);
}









