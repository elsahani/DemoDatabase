package sg.edu.rp.c346.id20023841.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTask;
    TextView tvResult;
    EditText etDesc, etDate;
    ListView lv;

    ArrayList<Task> alTasks;
    ArrayAdapter<String> aa;
    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTask = findViewById(R.id.btnGetTask);
        tvResult = findViewById(R.id.tvResults);
        etDate = findViewById(R.id.editTextDate);
        etDesc = findViewById(R.id.editTextDesc);
        lv = findViewById(R.id.lv);

        alTasks = new ArrayList<>();
        aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alTasks);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etDesc.getText().toString(), etDate.getText().toString());

                etDate.setText(null);
                etDesc.setText(null);

            }
        });

        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResult.setText(txt);

                alTasks.clear();
                alTasks.addAll(db.getTasks(asc));

                asc = !asc;
                aa.notifyDataSetChanged();

                etDesc.setText(null);
                etDate.setText(null);
            }
        });



    }
}