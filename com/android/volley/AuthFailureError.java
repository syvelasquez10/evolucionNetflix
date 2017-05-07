// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import android.content.Intent;

public class AuthFailureError extends VolleyError
{
    private Intent mResolutionIntent;
    
    public AuthFailureError() {
    }
    
    public AuthFailureError(final Intent mResolutionIntent) {
        this.mResolutionIntent = mResolutionIntent;
    }
    
    public AuthFailureError(final NetworkResponse networkResponse) {
        super(networkResponse);
    }
    
    public AuthFailureError(final String s) {
        super(s);
    }
    
    public AuthFailureError(final String s, final Exception ex) {
        super(s, ex);
    }
    
    @Override
    public String getMessage() {
        if (this.mResolutionIntent != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
    
    public Intent getResolutionIntent() {
        return this.mResolutionIntent;
    }
}
