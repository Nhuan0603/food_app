package com.example.food_app.fragment.history;

import androidx.annotation.NonNull;

import com.example.food_app.fragment.cart.CartModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryModel {
    private CartModel cartModel = new CartModel();
    public static List<String> NameFoodHistoryList = new ArrayList<>();
    public static String DateHistory;
    public String getNameFoodHistory(int index){
        return cartModel.getFoodByOrder(index).getName();
    }
   public void addNameFoodToHistory(@NonNull CartModel model, String date){
        for(int index = 0; index< CartModel.cartList.size(); index++){
            this.NameFoodHistoryList.add(model.getFoodByOrder(index).getName());
        }
        this.DateHistory = date;
   }
}
