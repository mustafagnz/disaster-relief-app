package com.test.donemprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.view.View;

public class linkler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkler);
    }

    public void sentToAfetBilgi(View view){

        String url = "https://www.afetbilgi.com";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }
    public void sentToafetHaritasi(View view){

        String url = "https://afetharita.com";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

    public void sentToYakinimiBul(View view){

        String url = "https://yakinimibul.net";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

    public void sentToTrafik(View view){

        String url = "https://www.kgm.gov.tr/Sayfalar/KGM/SiteTr/YolDanisma/TrafigeKapaliYollar.aspx";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

    public void sentToCozum(View view){

        String url = "https://afetcozum.com/?fbclid=PAAabkFMq7oD5sCj6j39SF0zgINNhEXdBpRBuwDLfE4rgQsShTHJgRhS-4Hng";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

    public void sentToAnasayfa(View view){

        Intent intent = new Intent(linkler.this, girisActivity.class);
        startActivity(intent);


    }
}