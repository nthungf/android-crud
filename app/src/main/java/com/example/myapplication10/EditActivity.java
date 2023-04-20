package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private EditText eTitle, ePrice, eDate;
    private Button btUpdate, btRemove, btBack;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initViews();
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btBack.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        eTitle.setText(item.getTitle());
        ePrice.setText(item.getPrice());
        eDate.setText(item.getDate());
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(item.getCategory())) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    private void initViews() {
        spinner = findViewById(R.id.spinner);
        eTitle = findViewById(R.id.edTitle);
        ePrice = findViewById(R.id.edPrice);
        eDate = findViewById(R.id.edDate);
        btUpdate = findViewById(R.id.btUpdate);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if (v == eDate) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        } else if (v == btBack) {
            finish();
        } else if (v == btRemove) {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa '" + item.getTitle() + "' khong?");
            builder.setIcon(R.drawable.ic_baseline_remove_circle_outline_24);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.delete(item.getId());
                    finish();
                }
            });
            builder.setNegativeButton("Khong", null);
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (v == btUpdate) {
            String title = eTitle.getText().toString();
            String price = ePrice.getText().toString();
            String category = spinner.getSelectedItem().toString();
            String date = eDate.getText().toString();
            int id = item.getId();
            if (!title.isEmpty() && price.matches("\\d+")) {
                Item i = new Item(id, title, category, price, date);
                db.update(i);
                finish();
            }
        }
    }
}