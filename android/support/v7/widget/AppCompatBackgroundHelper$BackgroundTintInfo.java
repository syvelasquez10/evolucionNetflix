// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.ColorStateList;

class AppCompatBackgroundHelper$BackgroundTintInfo extends TintInfo
{
    public ColorStateList mOriginalTintList;
    
    @Override
    void clear() {
        super.clear();
        this.mOriginalTintList = null;
    }
}
