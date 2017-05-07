// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.internal.eg;
import android.app.PendingIntent;
import android.os.RemoteException;
import android.util.Log;
import android.os.IBinder;
import com.google.android.gms.internal.hq;
import com.google.android.gms.internal.ht;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;

public final class PlusOneButtonWithPopup extends ViewGroup
{
    private View DB;
    private int DC;
    private View$OnClickListener DH;
    private String iH;
    private String jG;
    private int mSize;
    
    public PlusOneButtonWithPopup(final Context context) {
        this(context, null);
    }
    
    public PlusOneButtonWithPopup(final Context context, final AttributeSet set) {
        super(context, set);
        this.mSize = PlusOneButton.getSize(context, set);
        this.DC = PlusOneButton.getAnnotation(context, set);
        this.addView(this.DB = (View)new PlusOneDummyView(context, this.mSize));
    }
    
    private int c(final int n, final int n2) {
        final int mode = View$MeasureSpec.getMode(n);
        switch (mode) {
            default: {
                return n;
            }
            case Integer.MIN_VALUE:
            case 1073741824: {
                return View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(n) - n2, mode);
            }
        }
    }
    
    private void eL() {
        if (this.DB != null) {
            this.removeView(this.DB);
        }
        this.DB = ht.a(this.getContext(), this.mSize, this.DC, this.iH, this.jG);
        if (this.DH != null) {
            this.setOnClickListener(this.DH);
        }
        this.addView(this.DB);
    }
    
    private hq eM() throws RemoteException {
        final hq aw = hq.a.aw((IBinder)this.DB.getTag());
        if (aw == null) {
            if (Log.isLoggable("PlusOneButtonWithPopup", 5)) {
                Log.w("PlusOneButtonWithPopup", "Failed to get PlusOneDelegate");
            }
            throw new RemoteException();
        }
        return aw;
    }
    
    public void cancelClick() {
        if (this.DB == null) {
            return;
        }
        try {
            this.eM().cancelClick();
        }
        catch (RemoteException ex) {}
    }
    
    public PendingIntent getResolution() {
        if (this.DB != null) {
            try {
                return this.eM().getResolution();
            }
            catch (RemoteException ex) {}
        }
        return null;
    }
    
    public void initialize(final String ih, final String jg) {
        eg.b(ih, "Url must not be null");
        this.iH = ih;
        this.jG = jg;
        this.eL();
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.DB.layout(this.getPaddingLeft(), this.getPaddingTop(), n3 - n - this.getPaddingRight(), n4 - n2 - this.getPaddingBottom());
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int n3 = this.getPaddingLeft() + this.getPaddingRight();
        final int n4 = this.getPaddingTop() + this.getPaddingBottom();
        this.DB.measure(this.c(n, n3), this.c(n2, n4));
        this.setMeasuredDimension(n3 + this.DB.getMeasuredWidth(), n4 + this.DB.getMeasuredHeight());
    }
    
    public void reinitialize() {
        if (this.DB == null) {
            return;
        }
        try {
            this.eM().reinitialize();
        }
        catch (RemoteException ex) {}
    }
    
    public void setAnnotation(final int dc) {
        this.DC = dc;
        this.eL();
    }
    
    public void setOnClickListener(final View$OnClickListener view$OnClickListener) {
        this.DH = view$OnClickListener;
        this.DB.setOnClickListener(view$OnClickListener);
    }
    
    public void setSize(final int mSize) {
        this.mSize = mSize;
        this.eL();
    }
}
