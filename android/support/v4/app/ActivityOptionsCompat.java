// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.support.v4.util.Pair;
import android.app.Activity;
import android.view.View;
import android.os.Build$VERSION;
import android.content.Context;

public class ActivityOptionsCompat
{
    public static ActivityOptionsCompat makeCustomAnimation(final Context context, final int n, final int n2) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsCompat$ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(context, n, n2));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeScaleUpAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsCompat$ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeSceneTransitionAnimation(final Activity activity, final View view, final String s) {
        if (Build$VERSION.SDK_INT >= 21) {
            return new ActivityOptionsCompat$ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, view, s));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeSceneTransitionAnimation(final Activity activity, final Pair<View, String>... array) {
        final String[] array2 = null;
        if (Build$VERSION.SDK_INT >= 21) {
            View[] array3;
            String[] array5;
            if (array != null) {
                array3 = new View[array.length];
                final String[] array4 = new String[array.length];
                for (int i = 0; i < array.length; ++i) {
                    array3[i] = array[i].first;
                    array4[i] = array[i].second;
                }
                array5 = array4;
            }
            else {
                final View[] array6 = null;
                array5 = array2;
                array3 = array6;
            }
            return new ActivityOptionsCompat$ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, array3, array5));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(final View view, final Bitmap bitmap, final int n, final int n2) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsCompat$ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        return new ActivityOptionsCompat();
    }
    
    public Bundle toBundle() {
        return null;
    }
    
    public void update(final ActivityOptionsCompat activityOptionsCompat) {
    }
}
