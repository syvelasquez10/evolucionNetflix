// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException
{
    private final int kf;
    
    GooglePlayServicesAvailabilityException(final int kf, final String s, final Intent intent) {
        super(s, intent);
        this.kf = kf;
    }
    
    public int getConnectionStatusCode() {
        return this.kf;
    }
}
