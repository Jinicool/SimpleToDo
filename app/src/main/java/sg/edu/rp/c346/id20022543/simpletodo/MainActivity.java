package sg.edu.rp.c346.id20022543.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd,btnClear,btnDelete;
    ListView lvItems;
    ArrayList<String> alItems;
    ArrayAdapter<String> aaItem;
    Spinner spnAddRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvItems = findViewById(R.id.listViewItems);
        spnAddRemove = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        alItems = new ArrayList<>();

        aaItem = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alItems);
        lvItems.setAdapter(aaItem);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = etTask.getText().toString();
                alItems.add(newItem);
                aaItem.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alItems.clear();
                aaItem.notifyDataSetChanged();
            }
        });

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etTask.getText().toString());

                if(alItems.size() == 0) {
                    Toast.makeText(MainActivity.this,"Your don't have any task to remove",Toast.LENGTH_LONG).show();

                }
                else if(pos > alItems.size()) {
                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_LONG).show();
                }
                else {
                    alItems.remove(pos);
                    aaItem.notifyDataSetChanged();

                }
            }
        });

    }

}