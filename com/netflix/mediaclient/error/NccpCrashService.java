// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.error;

import com.netflix.mediaclient.javabridge.transport.NativeTransport;
import com.netflix.mediaclient.Log;
import android.app.Application;

public class NccpCrashService implements CrashReportingService
{
    private static final String TAG = "nf_crash";
    
    @Override
    public void init(final Application application) {
        Log.d("nf_crash", "Init NCCP native crash handling...");
        NativeTransport.enableCrashHandler();
        Log.d("nf_crash", "Init NCCP native crash handling done");
    }
}
