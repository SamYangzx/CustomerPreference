package com.gome.custompreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gome.custompreference.fragment.CustomFragment;
import com.gome.custompreference.fragment.CustomFragment_v14;

public class FragmentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        if (Constant.V7_PREFERENCE) {
            getFragmentManager().beginTransaction().replace(R.id.main_content, new CustomFragment_v14()).commit();
        } else {
            getFragmentManager().beginTransaction().replace(R.id.main_content, new CustomFragment()).commit();
        }

    }
}
