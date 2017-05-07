// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.content.Intent;
import android.view.View$OnClickListener;
import com.google.android.gms.internal.fq;
import android.app.Activity;
import com.google.android.gms.plus.internal.g;
import com.google.android.gms.internal.ft;
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
    private View TT;
    private int TU;
    private int TV;
    private OnPlusOneClickListener TW;
    private int mSize;
    private String ro;
    
    public PlusOneButton(final Context context) {
        this(context, null);
    }
    
    public PlusOneButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.mSize = getSize(context, set);
        this.TU = getAnnotation(context, set);
        this.TV = -1;
        this.v(this.getContext());
        if (this.isInEditMode()) {}
    }
    
    protected static int getAnnotation(final Context context, final AttributeSet set) {
        int n = 0;
        final String a = ft.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, set, true, false, "PlusOneButton");
        if ("INLINE".equalsIgnoreCase(a)) {
            n = 2;
        }
        else if (!"NONE".equalsIgnoreCase(a)) {
            return 1;
        }
        return n;
    }
    
    protected static int getSize(final Context context, final AttributeSet set) {
        final String a = ft.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, set, true, false, "PlusOneButton");
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
    
    private void v(final Context context) {
        if (this.TT != null) {
            this.removeView(this.TT);
        }
        this.TT = g.a(context, this.mSize, this.TU, this.ro, this.TV);
        this.setOnPlusOneClickListener(this.TW);
        this.addView(this.TT);
    }
    
    public void initialize(final String ro, final int tv) {
        fq.a(this.getContext() instanceof Activity, (Object)"To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
        this.ro = ro;
        this.TV = tv;
        this.v(this.getContext());
    }
    
    public void initialize(final String ro, final OnPlusOneClickListener onPlusOneClickListener) {
        this.ro = ro;
        this.TV = 0;
        this.v(this.getContext());
        this.setOnPlusOneClickListener(onPlusOneClickListener);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.TT.layout(0, 0, n3 - n, n4 - n2);
    }
    
    protected void onMeasure(final int n, final int n2) {
        final View tt = this.TT;
        this.measureChild(tt, n, n2);
        this.setMeasuredDimension(tt.getMeasuredWidth(), tt.getMeasuredHeight());
    }
    
    public void setAnnotation(final int tu) {
        this.TU = tu;
        this.v(this.getContext());
    }
    
    public void setOnPlusOneClickListener(final OnPlusOneClickListener tw) {
        this.TW = tw;
        this.TT.setOnClickListener((View$OnClickListener)new DefaultOnPlusOneClickListener(tw));
    }
    
    public void setSize(final int mSize) {
        this.mSize = mSize;
        this.v(this.getContext());
    }
    
    protected class DefaultOnPlusOneClickListener implements View$OnClickListener, OnPlusOneClickListener
    {
        private final OnPlusOneClickListener TX;
        
        public DefaultOnPlusOneClickListener(final OnPlusOneClickListener tx) {
            this.TX = tx;
        }
        
        public void onClick(final View view) {
            final Intent intent = (Intent)PlusOneButton.this.TT.getTag();
            if (this.TX != null) {
                this.TX.onPlusOneClick(intent);
                return;
            }
            this.onPlusOneClick(intent);
        }
        
        public void onPlusOneClick(final Intent intent) {
            final Context context = PlusOneButton.this.getContext();
            if (context instanceof Activity && intent != null) {
                ((Activity)context).startActivityForResult(intent, PlusOneButton.this.TV);
            }
        }
    }
    
    public interface OnPlusOneClickListener
    {
        void onPlusOneClick(final Intent p0);
    }
}
