// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class dr extends ImageView
{
    private Uri oU;
    private int oV;
    private int oW;
    
    public void H(final int ov) {
        this.oV = ov;
    }
    
    public int bE() {
        return this.oV;
    }
    
    public void d(final Uri ou) {
        this.oU = ou;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.oW != 0) {
            canvas.drawColor(this.oW);
        }
    }
}
