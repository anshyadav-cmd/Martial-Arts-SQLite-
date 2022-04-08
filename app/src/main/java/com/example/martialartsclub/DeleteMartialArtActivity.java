package com.example.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.martialartsclub.model.DatabaseHandler;
import com.example.martialartsclub.model.MartialArt;

import java.util.ArrayList;

public class DeleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_art);

        mDatabaseHandler = new DatabaseHandler(this);

        updateTheUserInterface();
    }


    /**
     * Creating UI using java code
      */
    private void updateTheUserInterface() {
        ArrayList<MartialArt> allMartialArtObject = mDatabaseHandler.returnAllMartialArtObject();

        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this) ;

        for(MartialArt martialArt : allMartialArtObject) {
            // assigning radiobutton to every martialart object
            RadioButton currentRadioButton = new RadioButton(this) ;
            currentRadioButton.setId(martialArt.getMartialArtID());
            currentRadioButton.setText(martialArt.toString());

            radioGroup.addView(currentRadioButton);
        }

        radioGroup.setOnCheckedChangeListener(this);

        Button btnBack = new Button(this) ;
        btnBack.setText("Go back");
        btnBack.setOnClickListener(this);

        // adding radioGroup to ScrollView
        scrollView.addView(radioGroup);

        /**
         *  specifying width and height of the back button
         *     ********* VERY IMP *************
         */
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0,0,0, 70);

        // Adding button to the relative layout
        relativeLayout.addView(btnBack, buttonParams);

        ScrollView.LayoutParams scrollViewParams = new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT
        );

        relativeLayout.addView(scrollView, scrollViewParams);

        // setting this content View
        setContentView(relativeLayout );
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mDatabaseHandler.deleteMartialArtTupleFromDatabaseByID(checkedId);
        Toast.makeText(this, "Martial Arts object is deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
       this.finish();
    }
}