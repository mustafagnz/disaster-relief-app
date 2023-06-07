package com.test.donemprojesi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class yardimAdapter extends RecyclerView.Adapter<yardimAdapter.yardimHolder>{

    private ArrayList<yardim> yardimArrayList;
    private Context context;

    public yardimAdapter(ArrayList<yardim> yardimArrayList, Context context) {
        this.yardimArrayList = yardimArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public yardimAdapter.yardimHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_yardim, parent, false); // fragment_home.xml dosyasını kullanın
        return new yardimAdapter.yardimHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull yardimAdapter.yardimHolder holder, int position) {
        yardim yardim = yardimArrayList.get(position);
        holder.setData(yardim);
    }

    @Override
    public int getItemCount() {
        return yardimArrayList.size();
    }

    class yardimHolder extends RecyclerView.ViewHolder {

        TextView txtAdSoyad, txtEmail, txtTelefon, txtAdres,txtAciklama;

        public yardimHolder(@NonNull View itemView) {
            super(itemView);

            txtAdSoyad = itemView.findViewById(R.id.adSoyad);
            txtEmail = itemView.findViewById(R.id.email);
            txtTelefon = itemView.findViewById(R.id.telefon);
            txtAdres = itemView.findViewById(R.id.adres);
            txtAciklama = itemView.findViewById(R.id.aciklama);
        }

        public void setData(yardim urun) {
            this.txtAciklama.setText(urun.getAciklama());
            this.txtEmail.setText(urun.getEmail());
            this.txtAdres.setText(urun.getAdres());
            this.txtTelefon.setText(urun.getTelefon());
            this.txtAdSoyad.setText(urun.getAdSoyad());
        }
}}
