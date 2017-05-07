// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class ez extends ImageView
{
    private Uri CO;
    private int CP;
    private int CQ;
    
    public void L(final int cp) {
        this.CP = cp;
    }
    
    public void e(final Uri co) {
        this.CO = co;
    }
    
    public int eB() {
        return this.CP;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.CQ != 0) {
            canvas.drawColor(this.CQ);
        }
    }
}
