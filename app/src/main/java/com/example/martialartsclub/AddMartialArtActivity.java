package com.example.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martialartsclub.model.DatabaseHandler;
import com.example.martialartsclub.model.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAddMartialArt , btnGoBack;
    private EditText edtName, edtPrice, edtColor;

    private DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        btnAddMartialArt = findViewById(R.id.btnAddMartialArt);
        btnGoBack = findViewById(R.id.btnGoBack);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtColor = findViewById(R.id.edtColor);

        mDatabaseHandler = new DatabaseHandler(AddMartialArtActivity.this);

       btnAddMartialArt.setOnClickListener(this);
       btnGoBack.setOnClickListener(this);
    }


    private void addMartialArtObjectToDatabase() {
        String nameValue = edtName.getText().toString().trim();
        String priceValue = edtPrice.getText().toString().trim();
        String colorValue = edtColor.getText().toString().trim();

        try{
            double priceDoubleValue = Double.parseDouble(priceValue);

            // Creating and adding data to database using "DatabaseHandler"
            MartialArt martialArt = new MartialArt(0, nameValue, priceDoubleValue, colorValue);
            mDatabaseHandler.addMartialArt(martialArt);
            Toast.makeText(this, martialArt.toString(), Toast.LENGTH_SHORT).show();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnAddMartialArt:
                addMartialArtObjectToDatabase();
                break;
            case R.id.btnGoBack:
                this.finish();
                break;
        }
    }
}