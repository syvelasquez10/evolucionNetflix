// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.view.View$OnClickListener;
import com.google.android.gms.common.internal.n;
import android.app.Activity;
import com.google.android.gms.common.internal.q;
import com.google.android.gms.plus.internal.g;
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
    private View ala;
    private int alb;
    private int alc;
    private PlusOneButton$OnPlusOneClickListener ald;
    private int mSize;
    private String uR;
    
    public PlusOneButton(final Context context) {
        this(context, null);
    }
    
    public PlusOneButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.mSize = getSize(context, set);
        this.alb = getAnnotation(context, set);
        this.alc = -1;
        this.G(this.getContext());
        if (this.isInEditMode()) {}
    }
    
    private void G(final Context context) {
        if (this.ala != null) {
            this.removeView(this.ala);
        }
        this.ala = g.a(context, this.mSize, this.alb, this.uR, this.alc);
        this.setOnPlusOneClickListener(this.ald);
        this.addView(this.ala);
    }
    
    protected static int getAnnotation(final Context context, final AttributeSet set) {
        int n = 0;
        final String a = q.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, set, true, false, "PlusOneButton");
        if ("INLINE".equalsIgnoreCase(a)) {
            n = 2;
        }
        else if (!"NONE".equalsIgnoreCase(a)) {
            return 1;
        }
        return n;
    }
    
    protected static int getSize(final Context context, final AttributeSet set) {
        final String a = q.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, set, true, false, "PlusOneButton");
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
    
    public void initialize(final String ur, final int alc) {
        n.a(this.getContext() instanceof Activity, (Object)"To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
        this.uR = ur;
        this.alc = alc;
        this.G(this.getContext());
    }
    
    public void initialize(final String ur, final PlusOneButton$OnPlusOneClickListener onPlusOneClickListener) {
        this.uR = ur;
        this.alc = 0;
        this.G(this.getContext());
        this.setOnPlusOneClickListener(onPlusOneClickListener);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.ala.layout(0, 0, n3 - n, n4 - n2);
    }
    
    protected void onMeasure(final int n, final int n2) {
        final View ala = this.ala;
        this.measureChild(ala, n, n2);
        this.setMeasuredDimension(ala.getMeasuredWidth(), ala.getMeasuredHeight());
    }
    
    public void setAnnotation(final int alb) {
        this.alb = alb;
        this.G(this.getContext());
    }
    
    public void setOnPlusOneClickListener(final PlusOneButton$OnPlusOneClickListener ald) {
        this.ald = ald;
        this.ala.setOnClickListener((View$OnClickListener)new PlusOneButton$DefaultOnPlusOneClickListener(this, ald));
    }
    
    public void setSize(final int mSize) {
        this.mSize = mSize;
        this.G(this.getContext());
    }
}
