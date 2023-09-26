package dga.example.dialagadbanapp1.data.subjectTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MySubject
{
    @PrimaryKey(autoGenerate = true)//تحديد الصفة كمفتاح رئيسي والذي ينتج بشكل اوتماتي
    public long key_id;
    public String title;

    @Override
    public String toString() {
        return "MySubject{" +
                "key_id=" + key_id +
                ", fullName='" + title + '\'' +
                '}';
    }
}
