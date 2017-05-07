// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import android.content.res.Resources;
import android.content.pm.PackageManager$NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.graphics.Point;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.Button;
import android.content.Context;
import android.widget.FrameLayout;

public class PlusOneDummyView extends FrameLayout
{
    public static final String TAG = "PlusOneDummyView";
    
    public PlusOneDummyView(final Context context, final int n) {
        super(context);
        final Button button = new Button(context);
        button.setEnabled(false);
        button.setBackgroundDrawable(this.mY().getDrawable(n));
        final Point ep = this.eP(n);
        this.addView((View)button, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(ep.x, ep.y, 17));
    }
    
    private Point eP(int n) {
        final int n2 = 24;
        int n3 = 20;
        final Point point = new Point();
        switch (n) {
            default: {
                n = 38;
                n3 = 24;
                break;
            }
            case 1: {
                n = 32;
                break;
            }
            case 0: {
                n3 = 14;
                n = n2;
                break;
            }
            case 2: {
                n = 50;
                break;
            }
        }
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final float applyDimension = TypedValue.applyDimension(1, (float)n, displayMetrics);
        final float applyDimension2 = TypedValue.applyDimension(1, (float)n3, displayMetrics);
        point.x = (int)(applyDimension + 0.5);
        point.y = (int)(applyDimension2 + 0.5);
        return point;
    }
    
    private d mY() {
        d d;
        if (!(d = new b(this.getContext())).isValid()) {
            d = new c(this.getContext());
        }
        Object o = d;
        if (!d.isValid()) {
            o = new a(this.getContext());
        }
        return (d)o;
    }
    
    private static class a implements d
    {
        private Context mContext;
        
        private a(final Context mContext) {
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
    
    private static class b implements d
    {
        private Context mContext;
        
        private b(final Context mContext) {
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
    
    private static class c implements d
    {
        private Context mContext;
        
        private c(final Context mContext) {
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
    
    private interface d
    {
        Drawable getDrawable(final int p0);
        
        boolean isValid();
    }
}
