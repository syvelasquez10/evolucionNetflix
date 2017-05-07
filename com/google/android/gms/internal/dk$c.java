// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewParent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;

@ez
final class dk$c
{
    public final int index;
    public final ViewGroup$LayoutParams rI;
    public final ViewGroup rJ;
    
    public dk$c(final gv gv) {
        this.rI = gv.getLayoutParams();
        final ViewParent parent = gv.getParent();
        if (parent instanceof ViewGroup) {
            this.rJ = (ViewGroup)parent;
            this.index = this.rJ.indexOfChild((View)gv);
            this.rJ.removeView((View)gv);
            gv.x(true);
            return;
        }
        throw new dk$a("Could not get the parent of the WebView for an overlay.");
    }
}
