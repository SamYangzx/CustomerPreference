package com.lanmeng.custompreference;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceTestActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_preference);
        addPreferencesFromResource(R.xml.preference);
    }
}
