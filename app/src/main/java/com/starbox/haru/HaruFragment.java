package com.starbox.haru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HaruFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    TextView mainTextview;

    ImageView main_bubble;
    ImageView main_bubble_back;
    int spin_speed = 8000;
    int spin_speed_back = 5000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_haru, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        main_bubble = (ImageView) view.findViewById(R.id.main_bubble);
        main_bubble_back = (ImageView) view.findViewById(R.id.main_bubble_back);

        main_bubble.bringToFront();

        // 둘 다 같은 코드
        main_bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatActivity = new Intent(getActivity().getApplicationContext(), ChatActivity.class);
                startActivity(chatActivity);
            }
        });

        main_bubble_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // main_bubble Spin
        RotateAnimation bubbleRotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        bubbleRotateAnimation.setInterpolator(new LinearInterpolator());
        bubbleRotateAnimation.setDuration(spin_speed);
        bubbleRotateAnimation.setRepeatCount(Animation.INFINITE);

        view.findViewById(R.id.main_bubble).startAnimation(bubbleRotateAnimation);

        // main_bubble_back Spin
        RotateAnimation bubblebackRotateAnimation = new RotateAnimation(360f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        bubblebackRotateAnimation.setInterpolator(new LinearInterpolator());
        bubblebackRotateAnimation.setDuration(spin_speed_back);
        bubblebackRotateAnimation.setRepeatCount(Animation.INFINITE);

        view.findViewById(R.id.main_bubble_back).startAnimation(bubblebackRotateAnimation);


        mainTextview = (TextView) view.findViewById(R.id.main_text);

        if (currentUser != null) {
            String temp = currentUser.getDisplayName();
            mainTextview.setText("" + temp + "님, 환영합니다.");
        }


        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity)getActivity()).setTitle("Haru");
        setHasOptionsMenu(true);


        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.action_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_setting:
                Intent settingActivity = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
                startActivity(settingActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
