// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

final class iw$a extends Drawable
{
    private static final iw$a KY;
    private static final iw$a$a KZ;
    
    static {
        KY = new iw$a();
        KZ = new iw$a$a(null);
    }
    
    public void draw(final Canvas canvas) {
    }
    
    public Drawable$ConstantState getConstantState() {
        return iw$a.KZ;
    }
    
    public int getOpacity() {
        return -2;
    }
    
    public void setAlpha(final int n) {
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
}
