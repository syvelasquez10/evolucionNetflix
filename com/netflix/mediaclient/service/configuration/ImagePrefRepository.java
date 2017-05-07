// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class ImagePrefRepository
{
    private static final String PREF_NAME = "image_pref";
    private static final String TAG = "nf_service_configuration_imagepref";
    private String mImagePref;
    
    public ImagePrefRepository(final Context context) {
        this.mImagePref = null;
        this.mImagePref = PreferenceUtils.getStringPref(context, "image_pref", null);
        if (StringUtils.isEmpty(this.mImagePref)) {
            Log.d("nf_service_configuration_imagepref", "Image preference override not found.");
        }
        else if (Log.isLoggable("nf_service_configuration_imagepref", 3)) {
            Log.d("nf_service_configuration_imagepref", "Image preference override found:" + this.mImagePref);
        }
    }
    
    public static void clearRecords(final Context context) {
        PreferenceUtils.removePref(context, "image_pref");
    }
    
    public String getImgPreference() {
        return this.mImagePref;
    }
    
    public void update(final Context context, final String s) {
        Log.d("nf_service_configuration_imagepref", String.format("override device image pref to %s", s));
        if (s != null) {
            PreferenceUtils.putStringPref(context, "image_pref", s);
            return;
        }
        clearRecords(context);
    }
}
