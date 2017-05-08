// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.util.AttributeSet;
import android.content.Context;

public class NetflixRatingBarSmall extends NetflixRatingBar
{
    public NetflixRatingBarSmall(final Context context) {
        super(context);
    }
    
    public NetflixRatingBarSmall(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public NetflixRatingBarSmall(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    @Override
    protected int getNetflixStarRatingDrawableId() {
        return 2130838011;
    }
    
    @Override
    protected int getUserStarRatingDrawableId() {
        return 2130838014;
    }
}
