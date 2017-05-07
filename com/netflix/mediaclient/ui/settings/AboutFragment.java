// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.ui.diagnosis.DiagnosisActivity;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.preference.Preference;
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
        final String string = this.getString(2131493180);
        int n = 0;
        Serializable s = string;
        while (true) {
            try {
                final PackageInfo packageInfo = this.activity.getPackageManager().getPackageInfo(this.activity.getPackageName(), 0);
                s = string;
                final String s2 = (String)(s = packageInfo.versionName);
                final int versionCode = packageInfo.versionCode;
                s = s2;
                n = versionCode;
                s = new StringBuilder().append((String)s);
                if (n > 0) {
                    ((StringBuilder)s).append(" (code ").append(n).append(")").append(", ");
                }
                ((StringBuilder)s).append("OS API: ").append(AndroidUtils.getAndroidVersion()).append("\n").append("model: ").append(Build.MODEL).append(", ").append("build: ").append(Build.DISPLAY);
                this.findPreference((CharSequence)"ui.about").setSummary((CharSequence)((StringBuilder)s).toString());
                this.findPreference((CharSequence)"ui.about").setSelectable(false);
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.handleException("PreferenceFragment", (Exception)ex);
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
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new Preference$OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference) {
                ApmLogUtils.reportUiModalViewChanged((Context)AboutFragment.this.activity, IClientLogging.ModalView.privacyPolicy);
                return false;
            }
        });
        final Preference preference2 = this.findPreference((CharSequence)"pref.terms");
        preference2.setIntent(this.createViewLegalTermsOfUseIntent());
        preference2.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new Preference$OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference) {
                ApmLogUtils.reportUiModalViewChanged((Context)AboutFragment.this.activity, IClientLogging.ModalView.legalTerms);
                return false;
            }
        });
        final Preference preference3 = this.findPreference((CharSequence)"ui.diagnosis");
        preference3.setIntent(DiagnosisActivity.createStartIntent((Context)this.activity));
        preference3.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new Preference$OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference) {
                ApmLogUtils.reportUiModalViewChanged((Context)AboutFragment.this.activity, IClientLogging.ModalView.customerService);
                return false;
            }
        });
        this.updateAboutDevice();
    }
}
