package com.example.food_app.fragment.tab_home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeTab extends Fragment {
    private View myView;
    private BottomNavigationView myBottomNavigation;
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private AppBarConfiguration appBarConfiguration;
    FoodRepository foodRepository;
    private TextView textCartItemCount;
    List<Food> alProduct = new ArrayList<>();
    MainActivity mainActivity;

    ViewFlipper viewFlipper;

    private SearchView searchView;

    public HomeTab() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab_home, container, false);
        /////////search view
        searchView = myView.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filesList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filesList(newText);
                return false;
            }
        });


////////////////
        recyclerView = myView.findViewById(R.id.rcv_home);
        mainActivity = (MainActivity) getContext();
        initData();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        foodAdapter = new FoodAdapter(getContext(), this.foodRepository.getFoodList());
        recyclerView.setAdapter(foodAdapter);

        myBottomNavigation = getActivity().findViewById(R.id.menu_bottom);
        recyclerView.setOnTouchListener(new TranslateAnimation(getContext(), myBottomNavigation));

        foodAdapter.setSnackbarListener(new FoodAdapter.SnackbarListener() {
            @Override
            public void showCartFragment() {
                // Chuyển sang Fragment CartTab
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, new CartTab());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return myView;
    }

    private void filesList(String newText) {
        List<Food> filteredList = new ArrayList<>();
        for(Food user : alProduct){
            if(user.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(user);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(),"no food found", Toast.LENGTH_LONG).show();
            foodAdapter.setFilteredList(filteredList);
        }else{
           foodAdapter.setFilteredList(filteredList);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFlipper = view.findViewById(R.id.vf_flip_home);
        ActionViewFlipper();
    }

    private void initData() {

        for (int i = 0; i < 100; i++) {
            Food p = new Food(i, "ProductName" + i);
            int resID = getResId("ff_" + i%9, R.drawable.class);
            Uri imgUri = getUri(resID);
            p.setImage(imgUri);
            p.setPrice(new Random().nextFloat() * 10000);
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

    private void ActionViewFlipper() {
        int[] quangcao = new int[]{
                R.drawable.flipper1, // tên file drawable của bạn
                R.drawable.flipper2,
                R.drawable.flipper3,
                R.drawable.flipper4,
        };

        for (int image : quangcao) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

}
