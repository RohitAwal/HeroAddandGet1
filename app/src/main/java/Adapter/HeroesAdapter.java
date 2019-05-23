package Adapter;

import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.heroaddandget.R;

import java.util.List;

import HeroModel.HeroModel;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder> {
    private List<HeroModel> heroModelList;
    private Context context;

    public HeroesAdapter(List<HeroModel> heroModelList, Context context) {
        this.heroModelList = heroModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public HeroesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_hero_api, viewGroup, false);
        return new HeroesViewHolder(view);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
