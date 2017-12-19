/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gome.preference.support;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.gome.preference.R;

/**
 * A {@link CustomPreference} that provides a two-state toggleable option.
 * <p>
 * This preference will store a boolean into the SharedPreferences.
 *
 * @attr ref android.R.styleable#SwitchPreference_summaryOff
 * @attr ref android.R.styleable#SwitchPreference_summaryOn
 * @attr ref android.R.styleable#SwitchPreference_switchTextOff
 * @attr ref android.R.styleable#SwitchPreference_switchTextOn
 * @attr ref android.R.styleable#SwitchPreference_disableDependentsState
 */
public class SwitchPreference extends TwoStatePreference {
    private static final String TAG = SwitchPreference.class.getSimpleName();
    private final Listener mListener = new Listener();

    // Switch text for on and off states
    private CharSequence mSwitchOn;
    private CharSequence mSwitchOff;

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!callChangeListener(isChecked)) {
                // Listener didn't like it, change it back.
                // CompoundButton will make sure we don't recurse.
                buttonView.setChecked(!isChecked);
                return;
            }

            SwitchPreference.this.setChecked(isChecked);
        }
    }

    /**
     * Construct a new SwitchPreference with the given style options.
     *
     * @param context      The Context that will style this preference
     * @param attrs        Style attributes that differ from the default
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @param defStyleRes  A resource identifier of a style resource that
     *                     supplies default values for the view, used only if
     *                     defStyleAttr is 0 or can not be found in the theme. Can be 0
     *                     to not look for defaults.
     */
    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

//        TypedArray a = context.obtainStyledAttributes(attrs,
//                com.android.internal.R.styleable.SwitchPreference, defStyleAttr, defStyleRes);
//        setSummaryOn(a.getString(com.android.internal.R.styleable.SwitchPreference_summaryOn));
//        setSummaryOff(a.getString(com.android.internal.R.styleable.SwitchPreference_summaryOff));
//        setSwitchTextOn(a.getString(
//                com.android.internal.R.styleable.SwitchPreference_switchTextOn));
//        setSwitchTextOff(a.getString(
//                com.android.internal.R.styleable.SwitchPreference_switchTextOff));
//        setDisableDependentsState(a.getBoolean(
//                com.android.internal.R.styleable.SwitchPreference_disableDependentsState, false));
//        a.recycle();
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Construct a new SwitchPreference with the given style options.
     *
     * @param context      The Context that will style this preference
     * @param attrs        Style attributes that differ from the default
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     */
    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    /**
     * Construct a new SwitchPreference with the given style options.
     *
     * @param context The Context that will style this preference
     * @param attrs   Style attributes that differ from the default
     */
    public SwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    /**
     * Construct a new SwitchPreference with default style options.
     *
     * @param context The Context that will style this preference
     */
    public SwitchPreference(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, new int[]{
                android.R.attr.summaryOn, android.R.attr.summaryOff, android.R.attr.disableDependentsState
        }, defStyleAttr, defStyleRes);

        setSummaryOn(typedArray.getString(0));
        setSummaryOff(typedArray.getString(1));
        setDisableDependentsState(typedArray.getBoolean(2, false));

        typedArray.recycle();
        Log.d(TAG, "setWidgetLayoutResource");
        setWidgetLayoutResource(R.layout.preference_widget_switch);
    }


    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        this.syncSummaryView(holder);
        this.syncSwitchView(holder);
    }

    private void syncSwitchView(PreferenceViewHolder holder) {
//        View switchView = holder.findViewById(R.id.switch_widget);
        View switchView = holder.findViewById(R.id.custom_switch_widget);
        this.syncSwitchView(switchView);
    }

    private void syncSwitchView(View view) {
        Log.d(TAG, "view: " + view);
        Log.d(TAG, "mChecked: " + mChecked);
        if (view instanceof Checkable) {
            final Checkable checkable = (Checkable) view;
            final boolean isChecked = checkable.isChecked();
            if (isChecked == mChecked) return;

            if (view instanceof SwitchCompat) {
                SwitchCompat switchView = (SwitchCompat) view;
                switchView.setTextOn(this.mSwitchOn);
                switchView.setTextOff(this.mSwitchOff);
                switchView.setChecked(isChecked);
                switchView.setOnCheckedChangeListener(null);
            } else if (view instanceof Switch) {
                Switch switchView = (Switch) view;
                switchView.setTextOn(this.mSwitchOn);
                switchView.setTextOff(this.mSwitchOff);
                switchView.setChecked(isChecked);
                switchView.setOnCheckedChangeListener(null);
            }

            checkable.toggle();

            if (view instanceof SwitchCompat) {
                SwitchCompat switchView = (SwitchCompat) view;
                switchView.setOnCheckedChangeListener(mListener);
            } else if (view instanceof Switch) {
                Switch switchView = (Switch) view;
                switchView.setOnCheckedChangeListener(mListener);
            }
        }
    }


//    @Override
//    protected void onBindView(View view) {
//        super.onBindView(view);
//
////        View checkableView = view.findViewById(com.android.internal.R.id.switch_widget);
//        View checkableView = view.findViewById(R.id.switch_widget);
//        if (checkableView != null && checkableView instanceof Checkable) {
//            //TODO 这里到时要使用GomeSwitch
//            if (checkableView instanceof Switch) {
//                final Switch switchView = (Switch) checkableView;
//                switchView.setOnCheckedChangeListener(null);
//            }
//
//            ((Checkable) checkableView).setChecked(mChecked);
//
//            if (checkableView instanceof Switch) {
//                final Switch switchView = (Switch) checkableView;
//                switchView.setTextOn(mSwitchOn);
//                switchView.setTextOff(mSwitchOff);
//                switchView.setOnCheckedChangeListener(mListener);
//            }
//        }
//
//        syncSummaryView(view);
//    }

    /**
     * Set the text displayed on the switch widget in the on state.
     * This should be a very short string; one word if possible.
     *
     * @param onText Text to display in the on state
     */
    public void setSwitchTextOn(CharSequence onText) {
        mSwitchOn = onText;
        notifyChanged();
    }

    /**
     * Set the text displayed on the switch widget in the off state.
     * This should be a very short string; one word if possible.
     *
     * @param offText Text to display in the off state
     */
    public void setSwitchTextOff(CharSequence offText) {
        mSwitchOff = offText;
        notifyChanged();
    }

    /**
     * Set the text displayed on the switch widget in the on state.
     * This should be a very short string; one word if possible.
     *
     * @param resId The text as a string resource ID
     */
    public void setSwitchTextOn(@StringRes int resId) {
        setSwitchTextOn(getContext().getString(resId));
    }

    /**
     * Set the text displayed on the switch widget in the off state.
     * This should be a very short string; one word if possible.
     *
     * @param resId The text as a string resource ID
     */
    public void setSwitchTextOff(@StringRes int resId) {
        setSwitchTextOff(getContext().getString(resId));
    }

    /**
     * @return The text that will be displayed on the switch widget in the on state
     */
    public CharSequence getSwitchTextOn() {
        return mSwitchOn;
    }

    /**
     * @return The text that will be displayed on the switch widget in the off state
     */
    public CharSequence getSwitchTextOff() {
        return mSwitchOff;
    }
}
