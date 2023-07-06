package sg.edu.rp.c346.id22016845.exercise1;

import androidx.appcompat.app.AppCompatActivity;

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
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView list;
    ArrayAdapter<Task>adapter;
    ArrayList<Task>listing;
    EditText taskName;
    EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert=findViewById(R.id.button);
        btnGetTasks=findViewById(R.id.button2);
        tvResults=findViewById(R.id.textView);
        list=findViewById(R.id.listView);
        taskName=findViewById(R.id.editTextText);
        date=findViewById(R.id.editTextText2);
        listing=new ArrayList<>();

        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listing);
        list.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db= new DBHelper(MainActivity.this);
                db.insertTask(taskName.getText().toString(),date.getText().toString());


            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                listing.addAll(db.getTasks());
                adapter.notifyDataSetChanged();
                db.close();


                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

            }
        });
    }
}