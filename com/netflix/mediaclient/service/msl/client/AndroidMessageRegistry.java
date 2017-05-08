// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.mediaclient.Log;
import java.util.List;
import com.netflix.msl.MslError;
import android.content.Context;
import com.netflix.msl.msg.ErrorMessageRegistry;

public final class AndroidMessageRegistry implements ErrorMessageRegistry
{
    private static final String TAG = "nf_msl_error";
    private Context context;
    
    public AndroidMessageRegistry(final Context context) {
        this.context = context;
    }
    
    public String getUserMessage(final MslError mslError, final List<String> list) {
        String s;
        if (mslError == null) {
            Log.e("nf_msl_error", "getUserMessage:: NULL");
            s = this.context.getString(2131297117);
        }
        else {
            final String s2 = s = mslError.getInternalCode() + " " + mslError.getResponseCode().name() + " " + mslError.getMessage();
            if (Log.isLoggable()) {
                Log.e("nf_msl_error", "getUserMessage::" + s2);
                return s2;
            }
        }
        return s;
    }
    
    public String getUserMessage(final Throwable t, final List<String> list) {
        if (Log.isLoggable()) {
            Log.e("nf_msl_error", t, "getUserMessage::", new Object[0]);
        }
        if (t == null) {
            return this.context.getString(2131297117);
        }
        return t.getMessage();
    }
}
