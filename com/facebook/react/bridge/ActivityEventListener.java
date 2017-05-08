// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.content.Intent;
import android.app.Activity;

public interface ActivityEventListener
{
    void onActivityResult(final Activity p0, final int p1, final int p2, final Intent p3);
    
    void onNewIntent(final Intent p0);
}
