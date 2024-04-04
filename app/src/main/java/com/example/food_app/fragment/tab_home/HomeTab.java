package com.example.food_app.fragment.tab_home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.MainActivity;
import com.example.food_app.R;
import com.example.food_app.fragment.food.Food;
import com.example.food_app.fragment.food.FoodAdapter;
import com.example.food_app.fragment.food.FoodRepository;
import com.example.food_app.fragment.cart.CartTab;
import com.example.food_app.scroll.TranslateAnimation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class HomeTab extends Fragment {
    private View myView;
    private BottomNavigationView myBottomNavigation;
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private AppBarConfiguration appBarConfiguration;
    FoodRepository foodRepository;
    private CartTab cart = new CartTab();

    private TextView textCartItemCount;
    MainActivity mainActivity;

    ViewFlipper v_flipper;

    public HomeTab() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab_home, container, false);

        recyclerView = myView.findViewById(R.id.rcv_home);
        mainActivity = (MainActivity) getContext();
        initData();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        foodAdapter = new FoodAdapter(getContext(), this.foodRepository.getFoodList());
        recyclerView.setAdapter(foodAdapter);

        myBottomNavigation = getActivity().findViewById(R.id.menu_bottom);
        recyclerView.setOnTouchListener(new TranslateAnimation(getContext(), myBottomNavigation));

        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int image[] = {R.drawable.flipper1, R.drawable.flipper2, R.drawable.flipper3, R.drawable.flipper4};
        v_flipper = view.findViewById(R.id.vf_flip_home);
        for(int images: image){
            flipperImage(images);
        }
    }

    private void initData() {
        ArrayList<Food> alProduct = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Food p = new Food(i, "ProductName" + i);
            int resID = getResId("ff_" + i%9, R.drawable.class);
            Uri imgUri = getUri(resID);
            p.setImage(imgUri);
            p.setPrice(new Random().nextFloat() * 1000);
            alProduct.add(p);
        }
        this.foodRepository = new FoodRepository(alProduct);

    }

    private Uri getUri(int resId) {
        return Uri.parse("android.resource://"  + getContext().getPackageName().toString()+ "/" + resId);
    }

    private int getResId(String resName, Class<?> d) {
        try {
            Field idField = d.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
    }


}
