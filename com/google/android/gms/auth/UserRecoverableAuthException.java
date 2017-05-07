// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.content.Intent;

public class UserRecoverableAuthException extends GoogleAuthException
{
    private final Intent mIntent;
    
    public UserRecoverableAuthException(final String s, final Intent mIntent) {
        super(s);
        this.mIntent = mIntent;
    }
    
    public Intent getIntent() {
        if (this.mIntent == null) {
            return null;
        }
        return new Intent(this.mIntent);
    }
}
