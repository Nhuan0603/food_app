package com.example.food_app.Pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.R;

public class Method extends AppCompatActivity {
    private ImageView imgBackToPay;
    private RelativeLayout rlbank;
    private RelativeLayout rlCard;
    private RelativeLayout rlCash;
    private RelativeLayout rlbankHide;
    private TextView tvMBbank;
    private TextView tvbank;
    private TextView tvVPbank;
    private TextView tvViettinbank;
    private LinearLayout llMB;
    private LinearLayout llVP;
    private LinearLayout llVT;
    private TextView tvCard;
    private TextView tvCash;
    private Button btn_accept;
    private ImageView arrow_down;
    private ImageView arrow_up;
    private RelativeLayout selectedLayout;
    private LinearLayout selectedLinearLayout;
    private static final int My_REQUSET_CODE = 10;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method_pay);

        Data();
        ChangeBackgroundColor();
        BackToPay();
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentToPay();
            }
        });

    }

    private void ChangeBackgroundColor() {
        rlbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground(rlbank);
                int isVisible = rlbankHide.getVisibility();
                if(isVisible == View.VISIBLE){
                    rlbankHide.setVisibility(View.GONE);
                    arrow_up.setVisibility(View.GONE);
                    arrow_down.setVisibility(View.VISIBLE);
                }else {
                    rlbankHide.setVisibility(View.VISIBLE);
                    arrow_up.setVisibility(View.VISIBLE);
                    arrow_down.setVisibility(View.GONE);
                }
            }
        });

        rlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground(rlCard);
            }
        });

        rlCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground(rlCash);
            }
        });
        llMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLinearLayoutBackground(llMB);
            }
        });

        llVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLinearLayoutBackground(llVP);
            }
        });

        llVT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLinearLayoutBackground(llVT);
            }
        });
    }
    private void changeLinearLayoutBackground(LinearLayout linearLayout) {
        // Đặt màu nền của LinearLayout được chọn thành màu xám
        linearLayout.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

        // Đặt màu nền của LinearLayout trước đó (nếu có) về màu mặc định
        if (selectedLinearLayout != null && selectedLinearLayout != linearLayout) {
            selectedLinearLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        // Lưu trữ LinearLayout được chọn hiện tại
        selectedLinearLayout = linearLayout;
    }

    private void changeBackground(RelativeLayout layout) {
        // Đặt màu nền của RelativeLayout được chọn thành màu xám
        layout.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

        // Đặt màu nền của RelativeLayout trước đó (nếu có) về màu mặc định
        if (selectedLayout != null && selectedLayout != layout) {
            selectedLayout.setBackground(null);
        }
        // Lưu trữ RelativeLayout được chọn hiện tại
        selectedLayout = layout;
    }

    private void BackToPay() {
        imgBackToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void sentToPay() {
        if (selectedLayout != null && selectedLinearLayout != null) {
            String title = getTitleFromLayout(selectedLayout, selectedLinearLayout); // Lấy tiêu đề từ RelativeLayout và LinearLayout được chọn
            MethodData methodData = new MethodData(title);

            Intent returnintent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_MethodPay", methodData);
            returnintent.putExtras(bundle);

            setResult(Activity.RESULT_OK, returnintent);
            finish();
        } else {
            // Xử lý trường hợp không có RelativeLayout hoặc LinearLayout nào được chọn
        }
    }


    private String getTitleFromLayout(RelativeLayout layout, LinearLayout linearLayout) {
        // Lấy tiêu đề từ RelativeLayout và LinearLayout và trả về
        if (layout == rlbank) {
            if(linearLayout == llMB){
                return "Thanh toán qua ngân hàng: "+tvMBbank.getText().toString().trim();
            } else if (linearLayout == llVP) {
                return "Thanh toán qua ngân hàng: "+ tvVPbank.getText().toString().trim();
            }else {
                return "Thanh toán qua ngân hàng: "+ tvViettinbank.getText().toString().trim();
            }
        } else if (layout == rlCard) {
            return tvCard.getText().toString().trim();
        } else if (layout == rlCash) {
            return tvCash.getText().toString().trim();
        }
        return ""; // Trường hợp không tìm thấy tiêu đề
    }

    private void Data() {
        imgBackToPay = findViewById(R.id.img_backToPay);
        rlbank = (RelativeLayout) findViewById(R.id.rl_bank);
        rlCash = (RelativeLayout) findViewById(R.id.rl_cash);
        rlCard = (RelativeLayout) findViewById(R.id.rl_Card);
        rlbankHide = (RelativeLayout) findViewById(R.id.rl_hideshow);
        tvMBbank = (TextView) findViewById(R.id.tv_MBbank);
        tvVPbank = (TextView) findViewById(R.id.tv_VPbank);
        tvbank = (TextView) findViewById(R.id.tv_bank);
        tvCash = (TextView) findViewById(R.id.tv_titleMehtodPay);
        tvCard = (TextView) findViewById(R.id.tv_card);
        tvViettinbank = (TextView) findViewById(R.id.tv_ViettinBank);
        llMB = findViewById(R.id.ll_MB_bank);
        llVP = findViewById(R.id.ll_VPbank);
        llVT = findViewById(R.id.ll_ViettinBank);
        btn_accept = findViewById(R.id.btn_oke);
        arrow_down = (ImageView) findViewById(R.id.img_arrowDownBank);
        arrow_up = (ImageView) findViewById(R.id.img_arrow_up);
    }
}
