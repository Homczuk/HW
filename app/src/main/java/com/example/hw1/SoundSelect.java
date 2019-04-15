package com.example.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;



public class SoundSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_select);

        //LinearLayout.LayoutParams parameter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        Spinner sounds_spinner = findViewById(R.id.sound_spinner);
        //List<String> list = new ArrayList<>();
        String[] str = getResources().getStringArray(R.array.Sounds);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,str);
        sounds_spinner.setAdapter(adapter);
    }
    public void onAcceptSound(View view){
        Spinner sounds_spinner = findViewById(R.id.sound_spinner);
        int button_id = sounds_spinner.getSelectedItemPosition();
        if(button_id<0){
            return;
        }
        Intent result = new Intent();
        result.putExtra("SOUND_ID", button_id);
        setResult(2,result);
        finish();
    }
    public void onCancelSound(View view){
        finish();
    }
}
