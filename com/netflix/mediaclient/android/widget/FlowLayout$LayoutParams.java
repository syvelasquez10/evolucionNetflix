// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;

public class FlowLayout$LayoutParams extends ViewGroup$LayoutParams
{
    public boolean breakLine;
    public int horizontalSpacing;
    int x;
    int y;
    
    public FlowLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
    }
    
    public FlowLayout$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
    }
}
