// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.view.View;
import android.os.Build$VERSION;
import android.content.Context;

public class ActivityOptionsCompat
{
    public static ActivityOptionsCompat makeCustomAnimation(final Context context, final int n, final int n2) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(context, n, n2));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeScaleUpAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(final View view, final Bitmap bitmap, final int n, final int n2) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        return new ActivityOptionsCompat();
    }
    
    public Bundle toBundle() {
        return null;
    }
    
    public void update(final ActivityOptionsCompat activityOptionsCompat) {
    }
    
    private static class ActivityOptionsImplJB extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompatJB mImpl;
        
        ActivityOptionsImplJB(final ActivityOptionsCompatJB mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImplJB) {
                this.mImpl.update(((ActivityOptionsImplJB)activityOptionsCompat).mImpl);
            }
        }
    }
}
