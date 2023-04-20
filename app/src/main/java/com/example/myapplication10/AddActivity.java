package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication10.dal.SQLiteHelper;
import com.example.myapplication10.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private EditText eTitle, ePrice, eDate;
    private Button btUpdate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    private void initViews() {
        spinner = findViewById(R.id.spinner);
        eTitle = findViewById(R.id.edTitle);
        ePrice = findViewById(R.id.edPrice);
        eDate = findViewById(R.id.edDate);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View v) {
        if (v == eDate) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = "";
                    date += (String.format("%02d", dayOfMonth) + "/");
                    date += (String.format("%02d", month + 1) + "/");
                    date += (String.format("%04d", year));
                    eDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        } else if (v == btCancel) {
            finish();
        } else if (v == btUpdate) {
            String title = eTitle.getText().toString();
            String price = ePrice.getText().toString();
            String category = spinner.getSelectedItem().toString();
            String date = eDate.getText().toString();
            if (!title.isEmpty() && price.matches("\\d+")) {
                Item i = new Item(title, category, price, date);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
    }
}