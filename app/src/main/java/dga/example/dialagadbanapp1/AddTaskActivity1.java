package dga.example.dialagadbanapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddTaskActivity1 extends AppCompatActivity {
    private Button btnSaveTask;
    private Button btnCancelTask;
    private TextView tvlmportance;
    private SeekBar skbrlmportance;
    private TextInputEditText etShortTitle;
    private TextInputEditText etText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task1);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancelTask = findViewById(R.id.btnCancelTask);
        tvlmportance = findViewById(R.id.tvlmportance);
        skbrlmportance = findViewById(R.id.skbrlmportance);
        etShortTitle = findViewById(R.id.etShortTitle);
        etText = findViewById(R.id.etText);

    }

    public void checkTextAndShortTitle()
    {
        boolean isAllOk = true;
        String text = etText.getText().toString();
        String shortTitle = etShortTitle.getText().toString();
        if (text.length() < 0) {
            isAllOk = false;
            etText.setError("Wrong text");
        }
        if (shortTitle.length() < 0) {
            isAllOk = false;
            etShortTitle.setError("Wrong shortTitle");
        }
        if (isAllOk) {
            Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();
        }
    }
}
