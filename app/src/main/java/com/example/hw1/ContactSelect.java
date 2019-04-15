package com.example.hw1;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class ContactSelect extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TypedArray contacts = getResources().obtainTypedArray(R.array.Contacts);
        LinearLayout.LayoutParams parameter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        RadioGroup contacts_group = findViewById(R.id.contacts_group);
        contacts_group.removeAllViews();
        for(int i = 0; i <contacts.length();i++){
            RadioButton contact_button = new RadioButton(getApplicationContext());
            contact_button.setId(i);
            contact_button.setText(contacts.getText(i));
            contact_button.setLayoutParams(parameter);
            contacts_group.addView(contact_button);
        }
        contacts.recycle();
    }
    public void onAcceptContact(View view){
        RadioGroup contact_button = findViewById(R.id.contacts_group);
        int button_id = contact_button.getCheckedRadioButtonId();
        if(button_id<0)return;
        Intent result = new Intent();
        result.putExtra("CONTACT_ID", button_id);
        setResult(1,result);
        finish();
    }
    public void onCancelContact(View view){
        finish();
    }
}
