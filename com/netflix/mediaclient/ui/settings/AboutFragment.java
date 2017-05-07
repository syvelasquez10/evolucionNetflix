// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.preference.Preference;
import com.netflix.mediaclient.ui.diagnosis.DiagnosisActivity;
import android.preference.Preference$OnPreferenceClickListener;
import android.content.Context;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import java.io.Serializable;
import android.content.pm.PackageManager$NameNotFoundException;
import com.netflix.mediaclient.Log;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidUtils;
import android.net.Uri;
import android.content.Intent;
import android.app.Fragment;
import android.app.Activity;
import android.preference.PreferenceFragment;

public class AboutFragment extends PreferenceFragment
{
    private static final String TAG = "PreferenceFragment";
    private Activity activity;
    
    public static Fragment create() {
        return (Fragment)new AboutFragment();
    }
    
    private Intent createViewLegalTermsOfUseIntent() {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.netflix.com/TermsOfUse"));
    }
    
    private Intent createViewPrivacyPolicyIntent() {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("https://signup.netflix.com/PrivacyPolicy"));
    }
    
    private void updateAboutDevice() {
        Serializable s2;
        final String s = (String)(s2 = this.getString(2131493146));
        while (true) {
            try {
                final PackageInfo packageInfo = this.activity.getPackageManager().getPackageInfo(this.activity.getPackageName(), 0);
                s2 = s;
                final Serializable s3 = s2 = packageInfo.versionName;
                final int versionCode = packageInfo.versionCode;
                s2 = new StringBuilder().append((String)s3);
                if (versionCode > 0) {
                    ((StringBuilder)s2).append(" (code ").append(versionCode).append(")").append(", ");
                }
                ((StringBuilder)s2).append("OS API: ").append(AndroidUtils.getAndroidVersion()).append("\n").append("model: ").append(Build.MODEL).append(", ").append("build: ").append(Build.DISPLAY);
                this.findPreference((CharSequence)"ui.about").setSummary((CharSequence)((StringBuilder)s2).toString());
                this.findPreference((CharSequence)"ui.about").setSelectable(false);
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.handleException("PreferenceFragment", (Exception)ex);
                final int versionCode = 0;
                final Serializable s3 = s2;
                continue;
            }
            break;
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.activity = this.getActivity();
        this.addPreferencesFromResource(2131034112);
        this.findPreference((CharSequence)this.getString(2131492951)).setIntent(OpenSourceLicensesActivity.create((Context)this.activity));
        final Preference preference = this.findPreference((CharSequence)"pref.privacy");
        preference.setIntent(this.createViewPrivacyPolicyIntent());
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new AboutFragment$1(this));
        final Preference preference2 = this.findPreference((CharSequence)"pref.terms");
        preference2.setIntent(this.createViewLegalTermsOfUseIntent());
        preference2.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new AboutFragment$2(this));
        final Preference preference3 = this.findPreference((CharSequence)"ui.diagnosis");
        preference3.setIntent(DiagnosisActivity.createStartIntent((Context)this.activity));
        preference3.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new AboutFragment$3(this));
        this.updateAboutDevice();
    }
}
