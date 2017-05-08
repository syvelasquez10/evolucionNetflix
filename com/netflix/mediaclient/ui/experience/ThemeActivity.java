// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.app.Fragment;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils$Theme;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class ThemeActivity extends FragmentHostActivity
{
    public static final String PREF_KEY = "theme_pref";
    
    public static void clearPrefs(final Context context) {
        PreferenceUtils.putStringPref(context, "theme_pref", "");
    }
    
    public static KidsUtils$Theme getSavedTheme(final Context context) {
        try {
            return KidsUtils$Theme.valueOf(PreferenceUtils.getStringPref(context, "theme_pref", ""));
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
    public static void saveTheme(final Context context, final KidsUtils$Theme kidsUtils$Theme) {
        PreferenceUtils.putStringPref(context, "theme_pref", kidsUtils$Theme.toString());
    }
    
    public static void start(final Activity activity) {
        activity.startActivity(new Intent((Context)activity, (Class)ThemeActivity.class));
    }
    
    @Override
    protected NetflixFrag createPrimaryFrag() {
        return new ThemeActivity$ThemeFragment();
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return null;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
}
