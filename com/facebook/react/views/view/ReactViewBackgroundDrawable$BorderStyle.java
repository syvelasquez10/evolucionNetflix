// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

import android.graphics.DashPathEffect;
import android.graphics.PathEffect;

enum ReactViewBackgroundDrawable$BorderStyle
{
    DASHED, 
    DOTTED, 
    SOLID;
    
    public PathEffect getPathEffect(final float n) {
        switch (ReactViewBackgroundDrawable$1.$SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[this.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return null;
            }
            case 2: {
                return (PathEffect)new DashPathEffect(new float[] { n * 3.0f, n * 3.0f, n * 3.0f, 3.0f * n }, 0.0f);
            }
            case 3: {
                return (PathEffect)new DashPathEffect(new float[] { n, n, n, n }, 0.0f);
            }
        }
    }
}
