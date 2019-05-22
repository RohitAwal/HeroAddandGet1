package Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.heroaddandget.R;

import java.util.List;

import HeroModel.HeroModel;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder> {
    private List<HeroModel>
    @NonNull
    @Override
    public HeroesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesViewHolder heroesViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HeroesViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPhoto;
        TextView tvName,tvDesc;
        public HeroesViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

}
