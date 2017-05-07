// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.animation.Transformation;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.TranslateAnimation;

public class CurrentTimeAnimation extends TranslateAnimation
{
    public CurrentTimeAnimation(final float n, final float n2, final float n3, final float n4) {
        super(n, n2, n3, n4);
    }
    
    public CurrentTimeAnimation(final int n, final float n2, final int n3, final float n4, final int n5, final float n6, final int n7, final float n8) {
        super(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public CurrentTimeAnimation(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        super.applyTransformation(n, transformation);
    }
}
