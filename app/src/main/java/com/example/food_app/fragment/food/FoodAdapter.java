package com.example.food_app.fragment.food;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.fragment.cart.CartModel;
import com.example.food_app.fragment.cart.CartTab;
import com.example.food_app.fragment.tab_home.HomeTab;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context mContext;
    private ArrayList<Food> foods;

    public CartModel cartModel = new CartModel();

    public FoodAdapter(Context mContext, ArrayList<Food> foods) {
        this.mContext = mContext;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_food, parent, false);
        FoodViewHolder viewHolder = new FoodViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final  Food p = foods.get(position);
        String sProductName = p.getName();
        holder.tvName.setText(sProductName);
        holder.tvPrice.setText(""+p.getPrice());
        holder.imgFood.setImageURI(p.getImage());

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+ p.getName(), Toast.LENGTH_LONG).show();
//            }
//        });

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButtonClick(view, p);
            }
        });
    }
    private void addButtonClick(View view, Food p) {
        cartModel.addCart(p);
        showSnackbar(view, mContext.getString(R.string.add_product) + p.getName(), Snackbar.LENGTH_SHORT);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFood;
        private TextView tvName;

        private RelativeLayout relativeLayout;
        private TextView tvPrice;
        private Button btnBuy;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = (ImageView) itemView.findViewById(R.id.img_Food);
            tvName = (TextView) itemView.findViewById(R.id.tv_foodName);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            btnBuy = (Button) itemView.findViewById(R.id.btn_Add);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rtlFoodItem);

        }
    }
    public void showSnackbar(View view, String message, int duration)
    {
        Snackbar.make(view, message, duration).setAction("Giỏ hàng", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CartTab.class);
                mContext.startActivity(intent);
            }
        }).show();

    }
}
