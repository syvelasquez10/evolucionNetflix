// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.PendingIntent;
import android.graphics.Rect;
import android.annotation.TargetApi;

@TargetApi(24)
class ActivityOptionsCompat$ActivityOptionsImpl24 extends ActivityOptionsCompat
{
    private final ActivityOptionsCompat24 mImpl;
    
    ActivityOptionsCompat$ActivityOptionsImpl24(final ActivityOptionsCompat24 mImpl) {
        this.mImpl = mImpl;
    }
    
    @Override
    public Rect getLaunchBounds() {
        return this.mImpl.getLaunchBounds();
    }
    
    @Override
    public void requestUsageTimeReport(final PendingIntent pendingIntent) {
        this.mImpl.requestUsageTimeReport(pendingIntent);
    }
    
    @Override
    public ActivityOptionsCompat setLaunchBounds(final Rect launchBounds) {
        return new ActivityOptionsCompat$ActivityOptionsImpl24(this.mImpl.setLaunchBounds(launchBounds));
    }
    
    @Override
    public Bundle toBundle() {
        return this.mImpl.toBundle();
    }
    
    @Override
    public void update(final ActivityOptionsCompat activityOptionsCompat) {
        if (activityOptionsCompat instanceof ActivityOptionsCompat$ActivityOptionsImpl24) {
            this.mImpl.update(((ActivityOptionsCompat$ActivityOptionsImpl24)activityOptionsCompat).mImpl);
        }
    }
}
