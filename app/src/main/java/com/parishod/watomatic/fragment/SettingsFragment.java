package com.parishod.watomatic.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.parishod.watomatic.R;
import com.parishod.watomatic.activity.main.MainActivity;
import com.parishod.watomatic.model.preferences.PreferencesManager;

public class SettingsFragment extends PreferenceFragmentCompat {
    private ListPreference languagePref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey);

        languagePref = findPreference(getString(R.string.key_pref_app_language));
        languagePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String thisLangStr = PreferencesManager.getPreferencesInstance(getActivity()).getSelectedLanguageStr(null);
                if((thisLangStr != null) && !thisLangStr.equals(newValue)){
                    //switch app language here
                    //Should restart the app for language change to take into account
                    restartApp();
                }
                return true;
            }
        });
    }

    private void restartApp() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finishAffinity();
    }
}
