// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.graphics.drawable.Drawable;
import android.content.Context;

class PlusOneDummyView$a implements PlusOneDummyView$d
{
    private Context mContext;
    
    private PlusOneDummyView$a(final Context mContext) {
        this.mContext = mContext;
    }
    
    @Override
    public Drawable getDrawable(final int n) {
        return this.mContext.getResources().getDrawable(17301508);
    }
    
    @Override
    public boolean isValid() {
        return true;
    }
}
