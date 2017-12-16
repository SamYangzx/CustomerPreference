package com.gome.custompreference.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.gome.custompreference.R;


/**
 * Created by fenghe on 2017/12/13.
 */

public class CustomFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加 Preferences XML
        addPreferencesFromResource(R.xml.preferences);

//        CustomFragment_v14.this.set
    }


}
