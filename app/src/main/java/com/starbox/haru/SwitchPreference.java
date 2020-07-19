package com.starbox.haru;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

public class SwitchPreference extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInsatanceState) {
        super.onCreate(savedInsatanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
