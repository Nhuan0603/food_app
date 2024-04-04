package com.example.food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.food_app.fragment.AccountFragment;
import com.example.food_app.fragment.ContactFragment;
import com.example.food_app.fragment.HistoryFragment;
import com.example.food_app.fragment.cart.CartTab;
import com.example.food_app.fragment.tab_home.HomeTab;
import com.example.food_app.fragment.notification.NoticeTab;
import com.example.food_app.scroll.TranslateAnimation;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout myDrawerLayout;
    private static final int TAB_HOME = 0;
    private static final int FRAGMENT_ACCOUNT = 1;
    private static final int FRAGMENT_HISTORY = 2;
    private static final int FRAGMENT_CONTACT = 3;
    private static final int TAB_CART = 4;
    public static final int TAB_NOTICE = 5;
    private int myCurrentFragment =  TAB_HOME;
    private NavigationView myNavigationView;
    private BottomNavigationView myBottomnavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDrawerLayout = findViewById(R.id.drawer_layout);

        myNavigationView = findViewById(R.id.navigation_view);
        myNavigationView.setNavigationItemSelectedListener(this);

//menu home bottom
        myBottomnavigationView = findViewById(R.id.menu_bottom);
        replaceFragment(new HomeTab());
        myBottomnavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);

        BadgeDrawable myBadgeDrawable = myBottomnavigationView.getOrCreateBadge(R.id.bottom_notice);
        myBadgeDrawable.setVisible(true);
        myBadgeDrawable.setNumber(10);

        myBottomnavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if( id == R.id.bottom_home){
                    if(myCurrentFragment != TAB_HOME){
                        replaceFragment(new HomeTab());
                        myCurrentFragment = TAB_HOME;
                    }
                }else if (id == R.id.bottom_cart){
                    if(myCurrentFragment != TAB_CART){
                        replaceFragment(new CartTab());
                        myCurrentFragment = TAB_CART;
                    }
                } else if (id == R.id.bottom_notice){
                    if(myCurrentFragment != TAB_NOTICE){
                        replaceFragment(new NoticeTab());
                        myCurrentFragment = TAB_NOTICE;
                    }
                }
                return true;
            }
        });
        //event scroll an/ hien menu bottom
        myDrawerLayout.setOnTouchListener(new TranslateAnimation(this, myBottomnavigationView));

    }
//mo menu main drawer(icon user goc tren ben phai)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }
//chon item trong menu toolbar->hien ra drawer nav
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_main_toolbar){
            myDrawerLayout.openDrawer(GravityCompat.END);
        }
        return true;
    }
//sk chon cac item trong nav bar drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_account) {
            if(myCurrentFragment != FRAGMENT_ACCOUNT){
                replaceFragment(new AccountFragment());
                myCurrentFragment = FRAGMENT_ACCOUNT;
            }
        } else if (id == R.id.nav_history) {
            if(myCurrentFragment != FRAGMENT_HISTORY){
                replaceFragment(new HistoryFragment());
                myCurrentFragment = FRAGMENT_HISTORY;
            }
        }  else if (id == R.id.nav_contact) {
            if(myCurrentFragment != FRAGMENT_CONTACT){
                replaceFragment(new ContactFragment());
                myCurrentFragment = FRAGMENT_CONTACT;
            }
        }
        myDrawerLayout.closeDrawer(GravityCompat.END);
        return true;
    }
//sk dong drawer khi bam back cua phone
    @Override
    public void onBackPressed() {
        if (myDrawerLayout.isDrawerOpen(GravityCompat.END)){
            myDrawerLayout.closeDrawer(GravityCompat.END);
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}