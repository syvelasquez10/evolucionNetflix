// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.graphics.Point;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.Button;
import android.content.Context;
import android.widget.FrameLayout;

public class PlusOneDummyView extends FrameLayout
{
    public static final String TAG = "PlusOneDummyView";
    
    public PlusOneDummyView(final Context context, final int n) {
        super(context);
        final Button button = new Button(context);
        button.setEnabled(false);
        button.setBackgroundDrawable(this.mY().getDrawable(n));
        final Point ep = this.eP(n);
        this.addView((View)button, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(ep.x, ep.y, 17));
    }
    
    private Point eP(int n) {
        final int n2 = 24;
        int n3 = 20;
        final Point point = new Point();
        switch (n) {
            default: {
                n = 38;
                n3 = 24;
                break;
            }
            case 1: {
                n = 32;
                break;
            }
            case 0: {
                n3 = 14;
                n = n2;
                break;
            }
            case 2: {
                n = 50;
                break;
            }
        }
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final float applyDimension = TypedValue.applyDimension(1, (float)n, displayMetrics);
        final float applyDimension2 = TypedValue.applyDimension(1, (float)n3, displayMetrics);
        point.x = (int)(applyDimension + 0.5);
        point.y = (int)(applyDimension2 + 0.5);
        return point;
    }
    
    private PlusOneDummyView$d mY() {
        PlusOneDummyView$d plusOneDummyView$d;
        if (!(plusOneDummyView$d = new PlusOneDummyView$b(this.getContext(), null)).isValid()) {
            plusOneDummyView$d = new PlusOneDummyView$c(this.getContext(), null);
        }
        PlusOneDummyView$d plusOneDummyView$d2 = plusOneDummyView$d;
        if (!plusOneDummyView$d.isValid()) {
            plusOneDummyView$d2 = new PlusOneDummyView$a(this.getContext(), null);
        }
        return plusOneDummyView$d2;
    }
}
