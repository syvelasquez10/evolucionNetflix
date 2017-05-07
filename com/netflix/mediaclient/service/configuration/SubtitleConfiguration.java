// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.javabridge.ui.IMedia;

public enum SubtitleConfiguration
{
    public static SubtitleConfiguration DEFAULT;
    
    ENHANCED_XML(IMedia.SubtitleProfile.ENHANCED, IMedia.SubtitleOutputMode.DATA_XML, 1, 2131296663), 
    SIMPLE_EVENTS(IMedia.SubtitleProfile.SIMPLE, IMedia.SubtitleOutputMode.EVENTS, 3, 2131296665), 
    SIMPLE_XML(IMedia.SubtitleProfile.SIMPLE, IMedia.SubtitleOutputMode.DATA_XML, 2, 2131296664);
    
    private static final String TAG = "nf_conf";
    private int mLabelId;
    private int mLookupType;
    private IMedia.SubtitleOutputMode mMode;
    private IMedia.SubtitleProfile mProfile;
    
    static {
        SubtitleConfiguration.DEFAULT = SubtitleConfiguration.ENHANCED_XML;
    }
    
    private SubtitleConfiguration(final IMedia.SubtitleProfile mProfile, final IMedia.SubtitleOutputMode mMode, final int mLookupType, final int mLabelId) {
        this.mProfile = mProfile;
        this.mMode = mMode;
        this.mLookupType = mLookupType;
        this.mLabelId = mLabelId;
    }
    
    public static SubtitleConfiguration clearQaLocalOverride(final Context context) {
        PreferenceUtils.removePref(context, "nf_subtitle_configuraton_QA_local");
        return SubtitleConfiguration.DEFAULT;
    }
    
    public static SubtitleConfiguration clearRecords(final Context context) {
        PreferenceUtils.removePref(context, "nf_subtitle_configuraton");
        return SubtitleConfiguration.ENHANCED_XML;
    }
    
    public static SubtitleConfiguration load(final Context context) {
        final int intPref = PreferenceUtils.getIntPref(context, "nf_subtitle_configuraton", -1);
        if (intPref < 1) {
            Log.d("nf_conf", "No overrides found. Use default");
            return SubtitleConfiguration.DEFAULT;
        }
        if (Log.isLoggable("nf_conf", 3)) {
            Log.d("nf_conf", "Remote override found " + intPref);
        }
        return lookup(intPref);
    }
    
    public static SubtitleConfiguration loadQaLocalOverride(final Context context) {
        final int intPref = PreferenceUtils.getIntPref(context, "nf_subtitle_configuraton_QA_local", -1);
        if (intPref < 1) {
            return SubtitleConfiguration.DEFAULT;
        }
        return lookup(intPref);
    }
    
    public static SubtitleConfiguration lookup(final int n) {
        final SubtitleConfiguration[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final SubtitleConfiguration subtitleConfiguration = values[i];
            if (subtitleConfiguration.mLookupType == n) {
                return subtitleConfiguration;
            }
        }
        if (Log.isLoggable("nf_conf", 6)) {
            Log.e("nf_conf", "Subtitle configuration lookup value " + n + " is not supported. Use default Enhanced + XML");
        }
        return SubtitleConfiguration.ENHANCED_XML;
    }
    
    public static SubtitleConfiguration update(final Context context, final String s) {
        final SubtitleConfiguration enhanced_XML = SubtitleConfiguration.ENHANCED_XML;
        SubtitleConfiguration lookup;
        if (s == null) {
            Log.e("nf_conf", "Subtitle configuration is not available, use default enhanced + XML");
            lookup = enhanced_XML;
        }
        else {
            lookup = lookup(Integer.parseInt(s));
        }
        PreferenceUtils.putIntPref(context, "nf_subtitle_configuraton", lookup.getLookupType());
        return load(context);
    }
    
    public static SubtitleConfiguration updateQaLocalOverride(final Context context, final Integer n) {
        final SubtitleConfiguration default1 = SubtitleConfiguration.DEFAULT;
        SubtitleConfiguration lookup;
        if (n == null) {
            Log.e("nf_conf", "Subtitle configuration is not available, use default enhanced + XML");
            lookup = default1;
        }
        else {
            lookup = lookup(n);
        }
        PreferenceUtils.putIntPref(context, "nf_subtitle_configuraton_QA_local", lookup.getLookupType());
        return lookup;
    }
    
    public int getLookupType() {
        return this.mLookupType;
    }
    
    public IMedia.SubtitleOutputMode getMode() {
        return this.mMode;
    }
    
    public IMedia.SubtitleProfile getProfile() {
        return this.mProfile;
    }
    
    public String getString(final Context context) {
        return context.getString(this.mLabelId);
    }
}
