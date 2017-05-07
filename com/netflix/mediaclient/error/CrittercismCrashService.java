// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.error;

import android.os.Build;
import android.os.Build$VERSION;
import android.content.Context;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.crittercism.app.CrittercismConfig;
import com.netflix.mediaclient.Log;
import android.app.Application;
import org.json.JSONObject;

public class CrittercismCrashService implements CrashReportingService
{
    private static final String TAG = "nf_crash";
    
    private void storeNotNull(final JSONObject jsonObject, final String s, final String s2) {
        if (s2 != null) {
            jsonObject.put(s, (Object)s2);
            return;
        }
        jsonObject.put(s, (Object)"N/A");
    }
    
    @Override
    public void init(final Application application) {
        Log.d("nf_crash", "Init Crittercism...");
        final CrittercismConfig crittercismConfig = new CrittercismConfig();
        crittercismConfig.setNdkCrashReportingEnabled(true);
        Crittercism.initialize((Context)application, SecurityRepository.getCrittercismAppId(), crittercismConfig);
        final JSONObject metadata = new JSONObject();
        try {
            metadata.put("android", Build$VERSION.SDK_INT);
            this.storeNotNull(metadata, "oem", Build.MANUFACTURER);
            this.storeNotNull(metadata, "model", Build.MODEL);
            Crittercism.setMetadata(metadata);
            Log.d("nf_crash", "Init Crittercism done.");
        }
        catch (Throwable t) {
            Log.e("nf_crash", "Failed to put esn to crittercism json?", t);
        }
    }
}
