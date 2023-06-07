package com.test.donemprojesi;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Urun {
    private String urunCesit, urunDurum, urunAdet, urunAciklama;
    private Bitmap urunResim;
    public Urun(String urunCesit, String urunDurum, String urunAdet, String urunAciklama, Bitmap urunResim) {
        this.urunCesit = urunCesit;
        this.urunDurum = urunDurum;
        this.urunAdet = urunAdet;
        this.urunAciklama = urunAciklama;
        this.urunResim = urunResim;
    }



    public Urun(){}

    public String getUrunAciklama() {
        return urunAciklama;
    }

    public void setUrunAciklama(String urunAciklama) {
        this.urunAciklama = urunAciklama;
    }

    public String getUrunCesit() {
        return urunCesit;
    }

    public void setUrunCesit(String urunCesit) {
        this.urunCesit = urunCesit;
    }

    public String getUrunDurum() {
        return urunDurum;
    }

    public void setUrunDurum(String urunDurum) {
        this.urunDurum = urunDurum;
    }

    public String getUrunAdet() {
        return urunAdet;
    }

    public void setUrunAdet(String urunAdet) {
        this.urunAdet = urunAdet;
    }

    public Bitmap getUrunResim() {
        return urunResim;
    }

    public void setUrunResim(Bitmap urunResim) {
        this.urunResim = urunResim;
    }

    static public ArrayList<Urun> getData(Context context){
        ArrayList<Urun> urunList = new ArrayList<>();

        ArrayList<String> urunCesidiList = new ArrayList<>();
        ArrayList<String> urunAciklamaList = new ArrayList<>();
        ArrayList<String> urunAdetList = new ArrayList<>();
        ArrayList<String> urunDurumList = new ArrayList<>();
        ArrayList<Bitmap> urunResimList = new ArrayList<>();

        try {
            SQLiteDatabase database = context.openOrCreateDatabase("urunler", Context.MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM urunler", null);


            int urunCesidiIndex = cursor.getColumnIndex("cesit");
            int urunDurumIndex = cursor.getColumnIndex("durum");
            int urunAciklamaIndex = cursor.getColumnIndex("aciklama");
            int urunAdetIndex = cursor.getColumnIndex("adet");
            int urunResimIndex = cursor.getColumnIndex("fotograf");

            while (cursor.moveToNext()){
                urunCesidiList.add(cursor.getString(urunCesidiIndex));
                urunAdetList.add(cursor.getString(urunAdetIndex));
                urunDurumList.add(cursor.getString(urunDurumIndex));
                urunAciklamaList.add(cursor.getString(urunAciklamaIndex));

                byte[] gelenResimByte = cursor.getBlob(urunResimIndex);
                Bitmap gelenResim = BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length);
                urunResimList.add(gelenResim);
            }

            cursor.close();

            for (int i = 0; i < urunCesidiList.size(); i++){
                Urun urun = new Urun();
                urun.setUrunCesit(urunCesidiList.get(i));
                urun.setUrunDurum(urunDurumList.get(i));
                urun.setUrunAdet(urunAciklamaList.get(i));
                urun.setUrunResim(urunResimList.get(i));

                urunList.add(urun);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return urunList;
    }
}

