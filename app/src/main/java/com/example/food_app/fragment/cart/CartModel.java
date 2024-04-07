package com.example.food_app.fragment.cart;

import com.example.food_app.Pay.Pay;
import com.example.food_app.fragment.food.Food;
import com.example.food_app.fragment.food.FoodRepository;

import java.util.HashMap;
import java.util.Map;

public class CartModel {
    public static Map<Integer, Integer> cartList = new HashMap<>();
    private Object keys[];

    public FoodRepository foodRepository = new FoodRepository();
    private static float  totalPrice;

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addCart (Food food){
        Integer quantity = cartList.getOrDefault(food.getId(), 0);
        if (quantity >= 10) return;
        cartList.put(food.getId(), quantity + 1);
        totalPrice += food.getPrice();
    }

    //return a food follow the position from the cart
    public Food getFoodByOrder(Integer pos){
        keys = cartList.keySet().toArray();
        return foodRepository.getFood(Integer.parseInt(keys[pos].toString()));
    }
    public float getLinePrice (Food p){
        return p.getPrice() * cartList.getOrDefault(p.getId(), 0);
    }
    public float getLinePrice (Integer pid){
        Food p = foodRepository.getFood(pid);
        return cartList.get(pid) * p.getPrice();
    }

    public void removeCart(Food p){
        Integer quantity = cartList.getOrDefault(p.getId(), 0);
        if (quantity <=0) return;
        cartList.put(p.getId(), quantity - 1);
        totalPrice -= p.getPrice();
    }

}
