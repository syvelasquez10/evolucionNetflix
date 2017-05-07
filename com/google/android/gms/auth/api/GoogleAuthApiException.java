// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;

public class GoogleAuthApiException extends Exception
{
    private Status CM;
    private PendingIntent mPendingIntent;
    
    public GoogleAuthApiException(final String s, final Status cm) {
        super(s);
        this.CM = cm;
    }
    
    public GoogleAuthApiException(final String s, final Status cm, final PendingIntent mPendingIntent) {
        super(s);
        this.CM = cm;
        this.mPendingIntent = mPendingIntent;
    }
    
    public PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }
    
    public Status getStatus() {
        return this.CM;
    }
    
    public boolean isUserRecoverable() {
        return this.mPendingIntent != null;
    }
}
