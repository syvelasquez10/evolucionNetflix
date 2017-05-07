// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable$ConstantState;

final class iw$b extends Drawable$ConstantState
{
    int La;
    int Lb;
    
    iw$b(final iw$b iw$b) {
        if (iw$b != null) {
            this.La = iw$b.La;
            this.Lb = iw$b.Lb;
        }
    }
    
    public int getChangingConfigurations() {
        return this.La;
    }
    
    public Drawable newDrawable() {
        return new iw(this);
    }
}
