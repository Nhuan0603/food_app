package com.example.food_app.fragment.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.fragment.food.Food;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    private CartModel cart;
    private Context mContext;
    public CartTab cartTab;

    public CartAdapter( Context mContext, CartModel cart) {
        this.mContext = mContext;
        this.cart = cart;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Integer pid = cart.getFoodByOrder(position).getId();
        final Food p = cart.foodRepository.getFood(pid);

        String sFoodName = p.getName();
        Integer amount = cart.cartList.get(pid);
        holder.tvName.setText(sFoodName);
        holder.tvPrice.setText(""+p.getPrice());
        holder.imgFood.setImageURI(p.getImage());
        holder.number.setText("" + amount);
        holder.tvTotalPriceItem.setText("" + cart.getLinePrice(p));

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+ p.getName(), Toast.LENGTH_LONG).show();
//            }
//        });

//        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+ p.getName(), Toast.LENGTH_LONG).show();
//                cart.addCart(p);
//            }
//        });


//        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cart.addCart(p);
//                Integer amount = cart.cartList.get(pid);
//                holder.number.setText(""+ amount );
//                holder.tvTotalPriceItem.setText("" + cart.getLinePrice(p));
//                cartTab.updateData();
//            }
//        });
//        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cart.removeCart(p);
//                Integer amount = cart.cartList.get(pid);
//                holder.number.setText(""+ amount );
//                holder.tvTotalPriceItem.setText("" + cart.getLinePrice(p));
//                ((CartTab) mContext).updateData();
//            }
//
//
//        });
    }

    @Override
    public int getItemCount() {
        return cart.cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvTotalPriceItem;
        public EditText number;
        public TextView tvPlus;
        public TextView tvMinus;
        public RelativeLayout relativeLayout;
        public ImageView imgFood;
        public Button btnAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView)itemView.findViewById(R.id.tv_foodName);
            this.tvPrice = (TextView)itemView.findViewById(R.id.tv_price);
            this.imgFood = (ImageView)itemView.findViewById(R.id.img_Food);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rtlCartItem);
            this.number = (EditText) itemView.findViewById(R.id.ed_numberCartItem);
            this.tvTotalPriceItem = (TextView)itemView.findViewById(R.id.tv_TotalPriceItem);
            this.tvPlus = (TextView)itemView.findViewById(R.id.tv_plusCartItem);
            this.tvMinus = (TextView)itemView.findViewById(R.id.tv_minusCartItem);
            this.btnAdd = (Button) itemView.findViewById(R.id.btn_Add);
        }
    }
}
