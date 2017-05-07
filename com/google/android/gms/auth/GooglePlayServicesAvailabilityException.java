// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException
{
    private final int Dr;
    
    GooglePlayServicesAvailabilityException(final int dr, final String s, final Intent intent) {
        super(s, intent);
        this.Dr = dr;
    }
    
    public int getConnectionStatusCode() {
        return this.Dr;
    }
}
