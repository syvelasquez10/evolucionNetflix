// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.List;
import android.support.v4.app.Fragment;
import android.app.Activity;

public final class Session$OpenRequest extends Session$AuthorizationRequest
{
    public Session$OpenRequest(final Activity activity) {
        super(activity);
    }
    
    public Session$OpenRequest(final Fragment fragment) {
        super(fragment);
    }
    
    public final Session$OpenRequest setCallback(final Session$StatusCallback callback) {
        super.setCallback(callback);
        return this;
    }
    
    public final Session$OpenRequest setDefaultAudience(final SessionDefaultAudience defaultAudience) {
        super.setDefaultAudience(defaultAudience);
        return this;
    }
    
    public final Session$OpenRequest setLoginBehavior(final SessionLoginBehavior loginBehavior) {
        super.setLoginBehavior(loginBehavior);
        return this;
    }
    
    public final Session$OpenRequest setPermissions(final List<String> permissions) {
        super.setPermissions(permissions);
        return this;
    }
    
    public final Session$OpenRequest setRequestCode(final int requestCode) {
        super.setRequestCode(requestCode);
        return this;
    }
}
