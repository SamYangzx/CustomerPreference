package com.lanmeng.custompreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lanmeng.custompreference.fragment.CustomFragment;

public class FragmentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        getFragmentManager().beginTransaction().replace(R.id.main_content, new CustomFragment()).commit();
    }
}