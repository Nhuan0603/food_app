package com.example.food_app.fragment.notification;

import androidx.annotation.NonNull;

import com.example.food_app.fragment.cart.CartModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationModel {
    private CartModel cartModel = new CartModel();
    public static List<String> NameFoodList = new ArrayList<>();
    private static int count;
    public String getNameFood(int index){
        return cartModel.getFoodByOrder(index).getName();
    }
   public void setCount(int count){
        this.count = count;
   }
   public void addNameFood(@NonNull CartModel model){
        for(int index = 0; index< CartModel.cartList.size(); index++){
            this.NameFoodList.add(model.getFoodByOrder(index).getName());
        }
   }
    public static int getCount() {
        return count;
    }
}
