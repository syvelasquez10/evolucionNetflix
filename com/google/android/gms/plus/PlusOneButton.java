// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.content.Intent;
import android.view.View$OnClickListener;
import com.google.android.gms.internal.eg;
import android.app.Activity;
import com.google.android.gms.internal.ht;
import com.google.android.gms.internal.ej;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public final class PlusOneButton extends FrameLayout
{
    public static final int ANNOTATION_BUBBLE = 1;
    public static final int ANNOTATION_INLINE = 2;
    public static final int ANNOTATION_NONE = 0;
    public static final int DEFAULT_ACTIVITY_REQUEST_CODE = -1;
    public static final int SIZE_MEDIUM = 1;
    public static final int SIZE_SMALL = 0;
    public static final int SIZE_STANDARD = 3;
    public static final int SIZE_TALL = 2;
    private View DB;
    private int DC;
    private int DD;
    private OnPlusOneClickListener DE;
    private String iH;
    private int mSize;
    
    public PlusOneButton(final Context context) {
        this(context, null);
    }
    
    public PlusOneButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.mSize = getSize(context, set);
        this.DC = getAnnotation(context, set);
        this.DD = -1;
        this.p(this.getContext());
        if (this.isInEditMode()) {}
    }
    
    protected static int getAnnotation(final Context context, final AttributeSet set) {
        int n = 0;
        final String a = ej.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, set, true, false, "PlusOneButton");
        if ("INLINE".equalsIgnoreCase(a)) {
            n = 2;
        }
        else if (!"NONE".equalsIgnoreCase(a)) {
            return 1;
        }
        return n;
    }
    
    protected static int getSize(final Context context, final AttributeSet set) {
        final String a = ej.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, set, true, false, "PlusOneButton");
        if ("SMALL".equalsIgnoreCase(a)) {
            return 0;
        }
        if ("MEDIUM".equalsIgnoreCase(a)) {
            return 1;
        }
        if ("TALL".equalsIgnoreCase(a)) {
            return 2;
        }
        return 3;
    }
    
    private void p(final Context context) {
        if (this.DB != null) {
            this.removeView(this.DB);
        }
        this.DB = ht.a(context, this.mSize, this.DC, this.iH, this.DD);
        this.setOnPlusOneClickListener(this.DE);
        this.addView(this.DB);
    }
    
    public void initialize(final String ih, final int dd) {
        eg.a(this.getContext() instanceof Activity, (Object)"To use this method, the PlusOneButton must be placed in an Activity. Use initialize(PlusClient, String, OnPlusOneClickListener).");
        this.iH = ih;
        this.DD = dd;
        this.p(this.getContext());
    }
    
    public void initialize(final String ih, final OnPlusOneClickListener onPlusOneClickListener) {
        this.iH = ih;
        this.DD = 0;
        this.p(this.getContext());
        this.setOnPlusOneClickListener(onPlusOneClickListener);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.DB.layout(0, 0, n3 - n, n4 - n2);
    }
    
    protected void onMeasure(final int n, final int n2) {
        final View db = this.DB;
        this.measureChild(db, n, n2);
        this.setMeasuredDimension(db.getMeasuredWidth(), db.getMeasuredHeight());
    }
    
    public void setAnnotation(final int dc) {
        this.DC = dc;
        this.p(this.getContext());
    }
    
    public void setOnPlusOneClickListener(final OnPlusOneClickListener de) {
        this.DE = de;
        this.DB.setOnClickListener((View$OnClickListener)new DefaultOnPlusOneClickListener(de));
    }
    
    public void setSize(final int mSize) {
        this.mSize = mSize;
        this.p(this.getContext());
    }
    
    protected class DefaultOnPlusOneClickListener implements View$OnClickListener, OnPlusOneClickListener
    {
        private final OnPlusOneClickListener DF;
        
        public DefaultOnPlusOneClickListener(final OnPlusOneClickListener df) {
            this.DF = df;
        }
        
        public void onClick(final View view) {
            final Intent intent = (Intent)PlusOneButton.this.DB.getTag();
            if (this.DF != null) {
                this.DF.onPlusOneClick(intent);
                return;
            }
            this.onPlusOneClick(intent);
        }
        
        public void onPlusOneClick(final Intent intent) {
            final Context context = PlusOneButton.this.getContext();
            if (context instanceof Activity && intent != null) {
                ((Activity)context).startActivityForResult(intent, PlusOneButton.this.DD);
            }
        }
    }
    
    public interface OnPlusOneClickListener
    {
        void onPlusOneClick(final Intent p0);
    }
}
