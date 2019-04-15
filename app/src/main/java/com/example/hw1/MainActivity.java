package com.example.hw1;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    MediaPlayer Player;
    int sound_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton FAB = findViewById(R.id.FAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onPlayButtonClicked();
            }
        });
        TextView first_contact_name = findViewById(R.id.textView);
        TypedArray contacts = getResources().obtainTypedArray(R.array.Contacts);
        TypedArray images = getResources().obtainTypedArray(R.array.Images);
        first_contact_name.setText(contacts.getText(0));

        ImageView first_contact_image = findViewById(R.id.imageView);
        first_contact_image.setImageResource(images.getResourceId(1, 0));
        images.recycle();
        contacts.recycle();


        TypedArray sounds = getResources().obtainTypedArray(R.array.Sounds);
        sound_id = getResources().getIdentifier(sounds.getString(0), "raw", getPackageName());
        Player = MediaPlayer.create(this, R.raw.mario);
        sounds.recycle();
    }

    public void onContactChangeButtonClicked(View view) {
        Intent intentContacts = new Intent(getApplicationContext(), ContactSelect.class);
        startActivityForResult(intentContacts, 1);
    }

    public void onSoundChangeButtonClicked(View view) {
        Intent intentSounds = new Intent(getApplicationContext(), SoundSelect.class);
        startActivityForResult(intentSounds, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (data == null) break;
                int id = data.getIntExtra("CONTACT_ID", 0);
                TypedArray contacts = getResources().obtainTypedArray(R.array.Contacts);
                TypedArray images = getResources().obtainTypedArray(R.array.Images);
                TextView name = findViewById(R.id.textView);
                name.setText(contacts.getText(id));
                ImageView image = findViewById(R.id.imageView);
                Random r = new Random();
                image.setImageResource(images.getResourceId(r.nextInt(images.length() - 1), 0));
                contacts.recycle();
                images.recycle();
                break;
            }
            case 2: {
                if (data == null) break;
                int id = data.getIntExtra("SOUND_ID", 0);
                TypedArray sounds = getResources().obtainTypedArray(R.array.Sounds);
                sound_id = getResources().getIdentifier(sounds.getString(id), "raw", getPackageName());
                Player = MediaPlayer.create(getApplicationContext(), sound_id);
                sounds.recycle();
                break;
            }
        }
    }
    public void onPlayButtonClicked() {
        if(Player.isPlaying()){
            Player.stop();
        }
        else {
            Player = MediaPlayer.create(getApplicationContext(), sound_id);
            Player.start();

        }
    }
}
