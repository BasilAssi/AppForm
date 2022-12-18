package com.example.appform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtTeam;
    private Button save;
    private boolean savedIns = false;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String NAME = "NAME";
    public static final String TEAM = "TEAM";
    public static final boolean FLAG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpShard();
        checkData();
    }

    private void checkData() {
        boolean f = pref.getBoolean("FLAG" , false);
        if(f){
            String name = pref.getString(NAME,"");
            String team = pref.getString(TEAM,"");
            edtName.setText(name);
            edtTeam.setText(team);
        }
    }


    private void setUpView(){
        edtName = findViewById(R.id.edtName);
        edtTeam = findViewById(R.id.edtTeam);
        save= findViewById(R.id.btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedIns =true;
            }
        });
    }

    private void setUpShard(){
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor= pref.edit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!savedIns){
            String name1 = edtName.getText().toString().trim();
            String team1 = edtTeam.getText().toString().trim();

            if(!name1.isEmpty() && !team1.isEmpty()){
                editor.putString(NAME ,name1);
                editor.putString(TEAM, team1);
                editor.putBoolean("FLAG",FLAG);
                editor.commit();
            }
        }
    }
}