// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.Intent;
import android.app.Activity;

interface AuthorizationClient$StartActivityDelegate
{
    Activity getActivityContext();
    
    void startActivityForResult(final Intent p0, final int p1);
}
