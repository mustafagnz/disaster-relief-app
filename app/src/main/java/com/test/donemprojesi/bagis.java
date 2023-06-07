package com.test.donemprojesi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.test.donemprojesi.databinding.ActivityBagisBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class bagis extends AppCompatActivity {

    ActivityBagisBinding binding;
    private static final int REUEST_CODE = 22;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBagisBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


         binding.btnCamera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivityForResult(cameraIntent, REUEST_CODE);
             }
         });



    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REUEST_CODE && resultCode == RESULT_OK){
            if (data != null && data.getExtras() != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                binding.imageView.setImageBitmap(photo);
            } else if (data != null && data.getData() != null) {
                try {
                    Uri imageUri = data.getData();
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap photo = BitmapFactory.decodeStream(imageStream);
                    binding.imageView.setImageBitmap(photo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            Toast.makeText(this, "iptal edildi", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode,resultCode,data);
        }
    }





    public void iconButtonClicked(View view) {
        String spinner = binding.spinner.getSelectedItem().toString();
        String cesit = binding.cesitEdt.getText().toString();
        String adet = binding.adetEdt.getText().toString();
        String acıklama = binding.aciklamaEdt.getText().toString();
        String urunGorsel = getBitmapAsString(binding.imageView);

        if (!TextUtils.isEmpty(acıklama) &&
                !TextUtils.isEmpty(cesit) &&
                !TextUtils.isEmpty(adet) &&
                !TextUtils.isEmpty(spinner) &&
                !TextUtils.isEmpty(urunGorsel))
        {
            Intent intent = new Intent(bagis.this, detay.class);
            intent.putExtra("spinner", spinner);
            intent.putExtra("cesit", cesit);
            intent.putExtra("adet", adet);
            intent.putExtra("aciklama", acıklama);
            intent.putExtra("gorsel", urunGorsel);

            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Boş Alan Olamaz", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBitmapAsString(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                return Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
        }
        return "";
    }



}











