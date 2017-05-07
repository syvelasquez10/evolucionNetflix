// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.content.Context;
import android.widget.RelativeLayout;

@ez
final class dk$b extends RelativeLayout
{
    private final gm ly;
    
    public dk$b(final Context context, final String s) {
        super(context);
        this.ly = new gm(context, s);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.ly.c(motionEvent);
        return false;
    }
}
