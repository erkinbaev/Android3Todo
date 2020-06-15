package com.geektech.android3todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geektech.android3todo.fragments.Fragment1;
import com.geektech.android3todo.fragments.Fragment2;
import com.geektech.android3todo.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Button next, skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        next = findViewById(R.id.btn_Next);
        skip = findViewById(R.id.btn_Skip);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pos;
                pos = position;
                if (pos < 2) {
                    next.setVisibility(View.VISIBLE);
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openNextPage(v);
                        }
                    });
                    skip.setVisibility(View.VISIBLE);
                } else {
                    skip.setVisibility(View.GONE);
                    next.setText("Start");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void openNextPage(View view) {
        viewPager.setCurrentItem(getItem(+1), true);
    }

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }
}