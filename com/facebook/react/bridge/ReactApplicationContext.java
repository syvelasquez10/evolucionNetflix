// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.content.Context;

public class ReactApplicationContext extends ReactContext
{
    public ReactApplicationContext(final Context context) {
        super(context.getApplicationContext());
    }
}
