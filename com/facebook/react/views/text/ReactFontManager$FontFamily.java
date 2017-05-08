// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.graphics.Typeface;
import android.util.SparseArray;

class ReactFontManager$FontFamily
{
    private SparseArray<Typeface> mTypefaceSparseArray;
    
    private ReactFontManager$FontFamily() {
        this.mTypefaceSparseArray = (SparseArray<Typeface>)new SparseArray(4);
    }
    
    public Typeface getTypeface(final int n) {
        return (Typeface)this.mTypefaceSparseArray.get(n);
    }
    
    public void setTypeface(final int n, final Typeface typeface) {
        this.mTypefaceSparseArray.put(n, (Object)typeface);
    }
}
