package dga.example.dialagadbanapp1.data.mytaskTable;

import androidx.room.PrimaryKey;
import  androidx.room.Entity;

/**فئه تمثل مهمه
 *
 */
@Entity
public class
MyTask
{
    /**
     * رقم المهمه
     */
     public long keyid;
    /**
     * درجه اهمبه من 1-5
     */
     public int importance;
    /**
     * رقم موضوع المهمه
     */
    public String text;
    /**
     * رقم موضوع المهمه
     */
    @PrimaryKey(autoGenerate = true)
    public long subjId;
    /**
     * رقم المستعمل الذي اضاف المهمه
     */
    public long userId;
    /** زمن بناء المهمه*/
    public long time ;
    /** هل تمت المهمه؟*/
    public boolean isCompleted;
    public String shortTitle;

    @Override
    public String toString() {
        return "MyTask{" +
                "keyid=" + keyid +
                ", importance=" + importance +
                ", text='" + text + '\'' +
                ", subjId=" + subjId +
                ", userId=" + userId +
                ", time=" + time +
                ", isCompleted=" + isCompleted +
                '}';
    }
}






