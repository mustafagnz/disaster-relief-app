package com.test.donemprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DeatilActivity extends AppCompatActivity {
    private String cesit, adet, durum, aciklama;
    private ImageView resim;
    // ImageView'i tanımlayın


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil);

        // ImageView'i bulun ve resim değişkenine atayın
        resim = findViewById(R.id.detailImgVw);



        // ImageView'e Bitmap'i atama


        // Diğer TextView'leri bulun ve değerlerini atayın
        TextView cesitTextView = findViewById(R.id.urunCesidiDetayTxtVw);
        TextView adetTextView = findViewById(R.id.adetDetayTxtVw);
        TextView durumTextView = findViewById(R.id.spinnerDetayTxtVw);
        TextView aciklamaTextView = findViewById(R.id.aciklamaDetayTxtVw);
        //
        //dlsa
        Bundle bundle = getIntent().getExtras();

        cesit = bundle.getString("urunCesit");
        adet = bundle.getString("urunAdet");
        durum = bundle.getString("urunDurum");
        aciklama = bundle.getString("urunAciklama");

        cesitTextView.setText(cesit);
        adetTextView.setText(adet);
        durumTextView.setText(durum);
        aciklamaTextView.setText(aciklama);
    }

    public void sqlBtnClick(View view){
        try {
            TextView txt = findViewById(R.id.adetDetayTxtVw);
            String aciklama = txt.getText().toString();
            SQLiteDatabase database = this.openOrCreateDatabase("products", Context.MODE_PRIVATE, null);

            database.execSQL("DELETE FROM products WHERE  aciklama = ?", new String[]{ aciklama});
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(DeatilActivity.this, girisActivity.class);
        startActivity(intent);

}}
