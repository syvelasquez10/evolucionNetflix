// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.media.session.MediaController;
import android.app.Activity;
import android.app.SharedElementCallback;

class ActivityCompat21
{
    private static SharedElementCallback createCallback(final ActivityCompat21$SharedElementCallback21 activityCompat21$SharedElementCallback21) {
        SharedElementCallback sharedElementCallback = null;
        if (activityCompat21$SharedElementCallback21 != null) {
            sharedElementCallback = new ActivityCompat21$SharedElementCallbackImpl(activityCompat21$SharedElementCallback21);
        }
        return sharedElementCallback;
    }
    
    public static void finishAfterTransition(final Activity activity) {
        activity.finishAfterTransition();
    }
    
    public static void postponeEnterTransition(final Activity activity) {
        activity.postponeEnterTransition();
    }
    
    public static void setEnterSharedElementCallback(final Activity activity, final ActivityCompat21$SharedElementCallback21 activityCompat21$SharedElementCallback21) {
        activity.setEnterSharedElementCallback(createCallback(activityCompat21$SharedElementCallback21));
    }
    
    public static void setExitSharedElementCallback(final Activity activity, final ActivityCompat21$SharedElementCallback21 activityCompat21$SharedElementCallback21) {
        activity.setExitSharedElementCallback(createCallback(activityCompat21$SharedElementCallback21));
    }
    
    public static void setMediaController(final Activity activity, final Object o) {
        activity.setMediaController((MediaController)o);
    }
    
    public static void startPostponedEnterTransition(final Activity activity) {
        activity.startPostponedEnterTransition();
    }
}
