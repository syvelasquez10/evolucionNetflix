// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException
{
    private final int wQ;
    
    GooglePlayServicesAvailabilityException(final int wq, final String s, final Intent intent) {
        super(s, intent);
        this.wQ = wq;
    }
    
    public int getConnectionStatusCode() {
        return this.wQ;
    }
}
