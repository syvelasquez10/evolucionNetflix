// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Path;
import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class iy extends ImageView
{
    private Uri Lc;
    private int Ld;
    private int Le;
    private a Lf;
    private int Lg;
    private float Lh;
    
    public void ay(final int ld) {
        this.Ld = ld;
    }
    
    public void g(final Uri lc) {
        this.Lc = lc;
    }
    
    public int gN() {
        return this.Ld;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.Lf != null) {
            canvas.clipPath(this.Lf.g(this.getWidth(), this.getHeight()));
        }
        super.onDraw(canvas);
        if (this.Le != 0) {
            canvas.drawColor(this.Le);
        }
    }
    
    protected void onMeasure(int measuredHeight, int measuredWidth) {
        super.onMeasure(measuredHeight, measuredWidth);
        switch (this.Lg) {
            default: {
                return;
            }
            case 1: {
                measuredHeight = this.getMeasuredHeight();
                measuredWidth = (int)(measuredHeight * this.Lh);
                break;
            }
            case 2: {
                measuredWidth = this.getMeasuredWidth();
                measuredHeight = (int)(measuredWidth / this.Lh);
                break;
            }
        }
        this.setMeasuredDimension(measuredWidth, measuredHeight);
    }
    
    public interface a
    {
        Path g(final int p0, final int p1);
    }
}
