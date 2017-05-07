// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.content.res.Resources;
import android.content.pm.PackageManager$NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.content.Context;

class PlusOneDummyView$b implements PlusOneDummyView$d
{
    private Context mContext;
    
    private PlusOneDummyView$b(final Context mContext) {
        this.mContext = mContext;
    }
    
    @Override
    public Drawable getDrawable(final int n) {
        while (true) {
            while (true) {
                Label_0075: {
                    Label_0069: {
                        try {
                            final Resources resources = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
                            switch (n) {
                                case 2: {
                                    final String s = "ic_plusone_tall";
                                    return resources.getDrawable(resources.getIdentifier(s, "drawable", "com.google.android.gms"));
                                }
                                case 0: {
                                    break Label_0069;
                                }
                                case 1: {
                                    break Label_0075;
                                }
                            }
                        }
                        catch (PackageManager$NameNotFoundException ex) {
                            return null;
                        }
                        final String s = "ic_plusone_standard";
                        continue;
                    }
                    final String s = "ic_plusone_small";
                    continue;
                }
                final String s = "ic_plusone_medium";
                continue;
            }
        }
    }
    
    @Override
    public boolean isValid() {
        try {
            this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
            return true;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
}
