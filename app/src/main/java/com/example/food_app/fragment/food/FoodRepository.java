package com.example.food_app.fragment.food;

import java.util.ArrayList;

public class FoodRepository {
    private static ArrayList<Food> foodList = new ArrayList<>();

    public FoodRepository(ArrayList<Food> lst) {
        for (Food p: lst){
            this.foodList.add(p);
        }
    }
    public FoodRepository(){

    }

    public static ArrayList<Food> getFoodList() {
        return foodList;
    }

    public static void setFoodList(ArrayList<Food> foodList) {
        FoodRepository.foodList = foodList;
    }

    public void addFood(Food p){
        this.foodList.add(p);
    }

    public Food getFood(Integer id){
        Food result;
        for ( Food p : foodList) {
            if (id == p.getId())
                return p;
        }
        return  null;
    }
}
