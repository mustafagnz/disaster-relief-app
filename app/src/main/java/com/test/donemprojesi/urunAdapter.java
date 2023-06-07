package com.test.donemprojesi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class urunAdapter extends RecyclerView.Adapter<urunAdapter.urunHolder> {

    private ArrayList<Urun> urunArrayList;
    private Context context;

    public urunAdapter(ArrayList<Urun> urunArrayList, Context context) {
        this.urunArrayList = urunArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public urunHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_home, parent, false); // fragment_home.xml dosyasını kullanın
        return new urunHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull urunHolder holder, int position) {
        Urun urun = urunArrayList.get(position);
        holder.setData(urun);
        holder.urunLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeatilActivity.class);
                intent.putExtra("urunCesit", urunArrayList.get(holder.getAdapterPosition()).getUrunCesit());
                intent.putExtra("urunDurum", urunArrayList.get(holder.getAdapterPosition()).getUrunDurum());
                intent.putExtra("urunAdet", urunArrayList.get(holder.getAdapterPosition()).getUrunAdet());
                intent.putExtra("urunAciklama", urunArrayList.get(holder.getAdapterPosition()).getUrunAciklama());
                intent.putExtra("urunFotograf", urunArrayList.get(holder.getAdapterPosition()).getUrunResim());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return urunArrayList.size();
    }

    class urunHolder extends RecyclerView.ViewHolder {

        TextView txtUrunCesidi, txtUrunAdet, txtUrunAciklama, txtUrunDurumu;
        ImageView imgUrunResim;
        LinearLayout urunLayout;

        public urunHolder(@NonNull View itemView) {
            super(itemView);

            txtUrunCesidi = itemView.findViewById(R.id.UrunAdi);
            txtUrunAdet = itemView.findViewById(R.id.urunAdedi);
            txtUrunAciklama = itemView.findViewById(R.id.urunAciklama);
            txtUrunDurumu = itemView.findViewById(R.id.urunDurumu);
            imgUrunResim = itemView.findViewById(R.id.urunResim);
            urunLayout = itemView.findViewById(R.id.urunLayout);
        }

        public void setData(Urun urun) {
            this.txtUrunCesidi.setText(urun.getUrunCesit());
            this.txtUrunAdet.setText(urun.getUrunAdet());
            this.txtUrunAciklama.setText(urun.getUrunAciklama());
            this.txtUrunDurumu.setText(urun.getUrunDurum());
            this.imgUrunResim.setImageBitmap(urun.getUrunResim());




        }


    }
}
