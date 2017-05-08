// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.diagnosis.DiagnosisActivity;
import android.preference.Preference$OnPreferenceClickListener;
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.net.Uri;
import android.content.Intent;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;

public class AboutFragment extends PreferenceFragment implements ManagerStatusListener
{
    private static final String TAG = "PreferenceFragment";
    private Activity activity;
    private ServiceManager manager;
    
    public static Fragment create() {
        return (Fragment)new AboutFragment();
    }
    
    private Intent createViewCookiesPolicyIntent() {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("http://www.netflix.com/privacy#cookies"));
    }
    
    private Intent createViewLegalTermsOfUseIntent() {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("http://www.netflix.com/termsofuse"));
    }
    
    private Intent createViewPrivacyPolicyIntent() {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("http://www.netflix.com/privacy"));
    }
    
    private void updateAboutDevice() {
        String s;
        if ((s = AndroidManifestUtils.getVersion(this.activity.getApplicationContext())) == null) {
            s = this.getString(2131231276);
        }
        final int versionCode = AndroidManifestUtils.getVersionCode(this.activity.getApplicationContext());
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getString(2131231274)).append(": ").append(s);
        if (versionCode > 0) {
            sb.append(" (");
            sb.append(this.getString(2131231275)).append(" ").append(versionCode).append("), ");
        }
        sb.append(this.getString(2131231172)).append(": ").append(AndroidUtils.getAndroidVersion());
        sb.append("\n");
        sb.append(this.getString(2131231131)).append(": ").append(Build.MODEL);
        sb.append("\n");
        LocalizationUtils.addMarkerForRtLocale(sb, '\u200f');
        sb.append(this.getString(2131230989)).append(": ").append(Build.DISPLAY);
        if (this.manager != null) {
            sb.append("\n");
            LocalizationUtils.addMarkerForRtLocale(sb, '\u200f');
            sb.append(this.getString(2131231102)).append(": ");
            sb.append(this.manager.getESNProvider().getEsn());
        }
        this.findPreference((CharSequence)"ui.about.device").setSummary((CharSequence)sb.toString());
        if (this.manager != null) {
            this.findPreference((CharSequence)"ui.account").setSummary((CharSequence)(this.getString(2131230958) + ": " + this.manager.getUserEmail()));
            this.findPreference((CharSequence)"ui.account").setSelectable(false);
        }
        this.updateDebugInfo();
    }
    
    private void updateDebugInfo() {
        final PreferenceScreen preferenceScreen = (PreferenceScreen)this.findPreference((CharSequence)"ui.about.prefs.screen");
        final PreferenceCategory preferenceCategory = (PreferenceCategory)this.findPreference((CharSequence)"ui.about.debug.cat");
        if (preferenceCategory != null) {
            preferenceScreen.removePreference((Preference)preferenceCategory);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.activity = this.getActivity();
        this.addPreferencesFromResource(2131099648);
        this.findPreference((CharSequence)this.getString(2131231532)).setIntent(OpenSourceLicensesActivity.create((Context)this.activity));
        final Preference preference = this.findPreference((CharSequence)"pref.privacy");
        preference.setIntent(this.createViewPrivacyPolicyIntent());
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new AboutFragment$1(this));
        final Preference preference2 = this.findPreference((CharSequence)"pref.privacy.cookies");
        preference2.setIntent(this.createViewCookiesPolicyIntent());
        preference2.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new AboutFragment$2(this));
        final Preference preference3 = this.findPreference((CharSequence)"pref.terms");
        preference3.setIntent(this.createViewLegalTermsOfUseIntent());
        preference3.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new AboutFragment$3(this));
        final Preference preference4 = this.findPreference((CharSequence)"ui.diagnosis");
        preference4.setIntent(DiagnosisActivity.createStartIntent((Context)this.activity));
        preference4.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new AboutFragment$4(this));
        this.updateAboutDevice();
    }
    
    public void onManagerReady(final ServiceManager manager, final Status status) {
        this.manager = manager;
        this.updateAboutDevice();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        this.manager = null;
    }
}
