// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceCategory;

public class DeviceRepository
{
    private static final String PREF_NAME = "nf_device_category";
    private static final String TAG = "nf_device";
    private DeviceCategory category;
    
    public DeviceRepository(final Context context) {
        final String stringPref = PreferenceUtils.getStringPref(context, "nf_device_category", null);
        if (stringPref == null) {
            Log.d("nf_device", "Device category override not found.");
        }
        else if (Log.isLoggable()) {
            Log.d("nf_device", "Device category override found:" + stringPref);
        }
        this.category = DeviceCategory.find(stringPref);
        if (Log.isLoggable()) {
            Log.d("nf_device", "Device category override is:" + this.category);
        }
    }
    
    public DeviceCategory getCategory() {
        return this.category;
    }
    
    public void update(final Context context, final String s) {
        Log.d("nf_device", "Device category update start");
        if (this.category == null && s == null) {
            Log.d("nf_device", "Both new and old category are null! Do nothing.");
        }
        else {
            if (this.category != null && s == null) {
                if (Log.isLoggable()) {
                    Log.d("nf_device", "Old category was " + this.category + " and new category is null! Remove saved category!");
                }
                this.category = null;
                PreferenceUtils.removePref(context, "nf_device_category");
                return;
            }
            final DeviceCategory find = DeviceCategory.find(s);
            if (!find.equals(this.category)) {
                if (Log.isLoggable()) {
                    Log.d("nf_device", "Old category was " + this.category + " and new category is now " + find);
                }
                this.category = find;
                PreferenceUtils.putStringPref(context, "nf_device_category", find.getValue());
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_device", "Both new and old category have the same value: " + find + ". Do NOT update.");
            }
        }
    }
}
