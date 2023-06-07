package com.test.donemprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class yardimActivitiy extends AppCompatActivity {

    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yardim_activitiy);
    }

    public void kaydetSql(View view) {
        try {

        database = this.openOrCreateDatabase("yardimlar", MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS yardimlar( adSoyad VARCHAR, telefon VARCHAR, email VARCHAR,adres VARCHAR ,aciklama VARCHAR)");

            String adSoyad = ((EditText) findViewById(R.id.adSoyadEdt)).getText().toString();
            String telefon = ((EditText) findViewById(R.id.telefonEdt)).getText().toString();
            String email = ((EditText) findViewById(R.id.emailEdt)).getText().toString();
            String adres = ((EditText) findViewById(R.id.adresEdt)).getText().toString();
            String aciklama = ((EditText) findViewById(R.id.aciklamaEdt)).getText().toString();

            String sqlSorgusu = "INSERT INTO yardimlar(adSoyad, telefon, email, adres, aciklama) VALUES(?, ?, ?, ?, ?)";
            SQLiteStatement statement = database.compileStatement(sqlSorgusu);
            statement.bindString(1, "Ad-Soyad: "+adSoyad);
            statement.bindString(2, "Telefon: "+telefon);
            statement.bindString(3, "E-Mail: "+email);
            statement.bindString(4, "Adres: "+adres);
            statement.bindString(5, "Açıklama: "+aciklama);

        statement.execute();

        Toast.makeText(this, "Kayıt Oluşturuldu", Toast.LENGTH_SHORT).show();
        this.finish();
    }catch (Exception exception){
        exception.printStackTrace();
    }
    }
}