// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.content.Context;
import android.widget.ViewSwitcher;

@ez
final class u$a extends ViewSwitcher
{
    private final gm ly;
    
    public u$a(final Context context) {
        super(context);
        this.ly = new gm(context);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.ly.c(motionEvent);
        return false;
    }
}
