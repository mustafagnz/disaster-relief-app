package com.test.donemprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.test.donemprojesi.databinding.ActivityGirisBinding;

public class girisActivity extends AppCompatActivity {

    ActivityGirisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGirisBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }

    public void sentToMain(View view){

        //Aktiviteler arası bağlantıyı sağlamak için intent oluşturulur
        Intent intent = new Intent(girisActivity.this, MainActivity.class);
        startActivity(intent);


    }

    public void sentToBagis(View view){

        Intent intent = new Intent(girisActivity.this, bagis.class);
        startActivity(intent);


    }

    public void sentToLinkler(View view){

        Intent intent = new Intent(girisActivity.this, linkler.class);
        startActivity(intent);


    }

    public void sentToYardim(View view){
        Intent intent = new Intent(girisActivity.this, yardimActivitiy.class);
        startActivity(intent);
    }

}