// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.graphics.drawable.Drawable;
import android.content.Context;

class PlusOneDummyView$c implements PlusOneDummyView$d
{
    private Context mContext;
    
    private PlusOneDummyView$c(final Context mContext) {
        this.mContext = mContext;
    }
    
    @Override
    public Drawable getDrawable(int identifier) {
        String s = null;
        switch (identifier) {
            default: {
                s = "ic_plusone_standard_off_client";
                break;
            }
            case 0: {
                s = "ic_plusone_small_off_client";
                break;
            }
            case 1: {
                s = "ic_plusone_medium_off_client";
                break;
            }
            case 2: {
                s = "ic_plusone_tall_off_client";
                break;
            }
        }
        identifier = this.mContext.getResources().getIdentifier(s, "drawable", this.mContext.getPackageName());
        return this.mContext.getResources().getDrawable(identifier);
    }
    
    @Override
    public boolean isValid() {
        final int identifier = this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName());
        final int identifier2 = this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName());
        final int identifier3 = this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName());
        final int identifier4 = this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName());
        return identifier != 0 && identifier2 != 0 && identifier3 != 0 && identifier4 != 0;
    }
}
