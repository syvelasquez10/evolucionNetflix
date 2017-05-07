// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class u$2 implements View$OnTouchListener
{
    final /* synthetic */ u lw;
    final /* synthetic */ v lx;
    
    u$2(final u lw, final v lx) {
        this.lw = lw;
        this.lx = lx;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        this.lx.ar();
        return false;
    }
}
