package com.example.train_food_order_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.train_food_order_app.Models.MenuItems;
import com.example.train_food_order_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<MenuItems> menuItems;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(MenuItems item);
    }

    public MenuAdapter(List<MenuItems> menuItems, OnItemClickListener itemClickListener) {

        this.menuItems = menuItems;
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItems menuItem = menuItems.get(position);

        holder.name.setText(menuItem.getItemName());
        holder.description.setText(menuItem.getDescription());
        holder.price.setText(String.format(Locale.US,"$ %.2f", menuItem.getPrice()));

        //holder.imageView.setImageResource(menuItem.getImageResourceId());
        Picasso.get().load(menuItem.getImageUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(menuItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,description,price;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewItemName);
            description = itemView.findViewById(R.id.textViewItemDescription);
            price = itemView.findViewById(R.id.textViewItemPrice);
            imageView = itemView.findViewById(R.id.textViewItemImage);
        }
    }
}
