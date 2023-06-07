package com.test.donemprojesi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class yardim {
    private String adSoyad;
    private String telefon;
    private String email;
    private String adres;
    private String aciklama;

    static public ArrayList<yardim> getData(Context context){
        ArrayList<yardim> list = new ArrayList<>();

        ArrayList<String> adSoyadList = new ArrayList<>();
        ArrayList<String> telefonList = new ArrayList<>();
        ArrayList<String> emailList = new ArrayList<>();
        ArrayList<String> adresList = new ArrayList<>();
        ArrayList<String> aciklamaList = new ArrayList<>();

        try {
            SQLiteDatabase database = context.openOrCreateDatabase("yardimlar", Context.MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM yardimlar", null);


            int adSoyadIndex = cursor.getColumnIndex("adSoyad");
            int telefonIndex = cursor.getColumnIndex("telefon");
            int emailIndex = cursor.getColumnIndex("email");
            int adresIndex = cursor.getColumnIndex("adres");
            int aciklamaIndex = cursor.getColumnIndex("aciklama");

            while (cursor.moveToNext()){
                adSoyadList.add(cursor.getString(adSoyadIndex));
                adresList.add(cursor.getString(adresIndex));
                telefonList.add(cursor.getString(telefonIndex));
                emailList.add(cursor.getString(emailIndex));
                aciklamaList.add(cursor.getString(aciklamaIndex));
            }

            cursor.close();

            for (int i = 0; i < adSoyadList.size(); i++){
                yardim yardim = new yardim();
                yardim.setAciklama(aciklamaList.get(i));
                yardim.setAdres(adresList.get(i));
                yardim.setAdSoyad(adSoyadList.get(i));
                yardim.setTelefon(telefonList.get(i));
                yardim.setEmail(emailList.get(i));


                list.add(yardim);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public yardim() {
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }



    public yardim(String adSoyad, String telefon, String email, String adres, String aciklama) {
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.email = email;
        this.adres = adres;
        this.aciklama = aciklama;
    }


}
