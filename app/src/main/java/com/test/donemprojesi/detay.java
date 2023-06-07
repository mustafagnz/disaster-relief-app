package com.test.donemprojesi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import java.io.IOException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.test.donemprojesi.databinding.ActivityBagisBinding;
import com.test.donemprojesi.databinding.ActivityDetayBinding;
import com.test.donemprojesi.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;

public class detay extends AppCompatActivity {
    private SQLiteDatabase database;
    private Bitmap bitmap;
    ActivityDetayBinding binding;
    ActivityBagisBinding bindingBagis;
    private static final int REUEST_CODE = 22;
    public String urunGorsel;
    private Bitmap decodedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityDetayBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent=getIntent();


        String spinnerTxtVw = intent.getStringExtra("spinner");
        String urunCesidiDetayTxtVw = intent.getStringExtra("cesit");
        String adetDetayTxtVw = intent.getStringExtra("adet");
        String aciklamaDetayTxtVw= intent.getStringExtra("aciklama");
        urunGorsel = getIntent().getStringExtra("gorsel");

        binding.spinnerDetayTxtVw.setText("DURUMU: " + spinnerTxtVw);
        binding.urunCesidiDetayTxtVw.setText("ÇEŞİT:"+ urunCesidiDetayTxtVw.toString());
        binding.adetDetayTxtVw.setText("ADET: "+ adetDetayTxtVw.toString());
        binding.aciklamaDetayTxtVw.setText("DETAY:"+ aciklamaDetayTxtVw.toString());


        // Base64 kodlamasını çözerek Bitmap oluşturma
        byte[] decodedString = Base64.decode(urunGorsel, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // ImageView'e Bitmap'i atama
        binding.detailImgVw.setImageBitmap(decodedBitmap);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                try {
                    Uri imageUri = data.getData();
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    bitmap = BitmapFactory.decodeStream(imageStream);
                    // Bitmap'i ImageView'e atama vb. işlemler yapılabilir
                    binding.detailImgVw.setImageBitmap(bitmap); // Eklendi
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(this, "Fotoğraf çekilmedi", Toast.LENGTH_SHORT).show();
        }
    }


    public void sqlBtnClick(View view){

        switch (view.getId()){
            case R.id.kaydet:
                try {
                    byte[] decodedString = Base64.decode(urunGorsel, Base64.DEFAULT);
                    Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    database = this.openOrCreateDatabase("urunler", MODE_PRIVATE,null);
                    database.execSQL("CREATE TABLE IF NOT EXISTS urunler( durum VARCHAR, cesit VARCHAR, adet VARCHAR, aciklama VARCHAR, fotograf BLOB)");

                    String sqlSorgusu = "INSERT INTO urunler(durum, cesit, adet, aciklama, fotograf) VALUES(?, ?, ?, ?, ?)";
                    SQLiteStatement statement = database.compileStatement(sqlSorgusu);
                    statement.bindString(1, String.valueOf(binding.spinnerDetayTxtVw.getText()));
                    statement.bindString(2, String.valueOf(binding.urunCesidiDetayTxtVw.getText()));
                    statement.bindString(3, String.valueOf(binding.adetDetayTxtVw.getText()));
                    statement.bindString(4, String.valueOf(binding.aciklamaDetayTxtVw.getText()));
                    statement.bindBlob(5, decodedString);
                    statement.execute();

                    Toast.makeText(this, "Kayıt Oluşturuldu", Toast.LENGTH_SHORT).show();
                    this.finish();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                break;
        }
    }
}