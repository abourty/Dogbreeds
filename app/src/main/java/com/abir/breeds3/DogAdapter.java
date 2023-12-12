package com.abir.breeds3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    private List<Dog> dogs;

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        Dog dog = dogs.get(position);

        holder.nameTextView.setText(dog.getName());
        holder.lifeSpanTextView.setText("Life Span: " + dog.getLife_span());
        holder.originTextView.setText("Origin: " + dog.getOrigin());
        holder.temperamentTextView.setText("Temperament: " + dog.getTemperament());

        // Utilisez Picasso (ou une autre bibliothèque) pour charger l'image dans l'ImageView
        Picasso.get().load("https://cdn2.thedogapi.com/images/" + dog.getImageUrl() + ".jpg").into(holder.dogImageView);
    }

    @Override
    public int getItemCount() {
        return dogs != null ? dogs.size() : 0;
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {
        ImageView dogImageView;
        TextView nameTextView;
        TextView lifeSpanTextView;
        TextView originTextView;
        TextView temperamentTextView;

        DogViewHolder(@NonNull View itemView) {
            super(itemView);
            dogImageView = itemView.findViewById(R.id.dogImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            lifeSpanTextView = itemView.findViewById(R.id.lifeSpanTextView);
            originTextView = itemView.findViewById(R.id.originTextView);
            temperamentTextView = itemView.findViewById(R.id.temperamentTextView);
        }
    }
}
