package com.example.train_food_order_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.train_food_order_app.Models.OrderInfo;
import com.example.train_food_order_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<OrderInfo> orderInfoList;

    public OrderAdapter(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        OrderInfo orderInfo = orderInfoList.get(position);
        holder.name.setText(orderInfo.getItemName());
        holder.price.setText(String.format(Locale.US,"$ %.2f",orderInfo.getTotalAmount()));
        //holder.qrt.setText(orderInfo.getQuantity());
        holder.qrt.setText(String.valueOf(orderInfo.getQuantity()));
        holder.status.setText(String.format(orderInfo.isAccepted()));
        Picasso.get().load(orderInfo.getImageUrl()).into(holder.imageView);



    }
    @Override
    public int getItemCount() {
        return orderInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,price, status,qrt;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodNameOrderList);
            price = itemView.findViewById(R.id.foodPriceOrderList);
            qrt = itemView.findViewById(R.id.qrtOrderList);
            status = itemView.findViewById(R.id.statusOrderList);
            imageView = itemView.findViewById(R.id.imageOrderList);
        }
    }
}
