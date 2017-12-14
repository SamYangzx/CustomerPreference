package com.lanmeng.custompreference.fragment;

import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;

import com.lanmeng.custompreference.R;


/**
 * Created by fenghe on 2017/12/13.
 */

public class CustomFragment_v14 extends PreferenceFragment {

    /**
     * Called during {@link #onCreate(Bundle)} to supply the preferences for this fragment.
     * Subclasses are expected to call {@link #setPreferenceScreen(PreferenceScreen)} either
     * directly or via helper methods such as {@link #addPreferencesFromResource(int)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     * @param rootKey            If non-null, this preference fragment should be rooted at the
     *                           {@link PreferenceScreen} with this key.
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加 Preferences XML
        addPreferencesFromResource(R.xml.preferences_v7);

//        CustomFragment_v14.this.set
    }


}
