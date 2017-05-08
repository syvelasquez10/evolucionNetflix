// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.net.Uri;
import android.content.IntentSender;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Build$VERSION;
import android.app.Activity;
import android.support.v4.content.ContextCompat;

public class ActivityCompat extends ContextCompat
{
    private static ActivityCompat21$SharedElementCallback21 createCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat21$SharedElementCallback21 activityCompat21$SharedElementCallback21 = null;
        if (sharedElementCallback != null) {
            activityCompat21$SharedElementCallback21 = new ActivityCompat$SharedElementCallback21Impl(sharedElementCallback);
        }
        return activityCompat21$SharedElementCallback21;
    }
    
    private static ActivityCompatApi23$SharedElementCallback23 createCallback23(final SharedElementCallback sharedElementCallback) {
        ActivityCompatApi23$SharedElementCallback23 activityCompatApi23$SharedElementCallback23 = null;
        if (sharedElementCallback != null) {
            activityCompatApi23$SharedElementCallback23 = new ActivityCompat$SharedElementCallback23Impl(sharedElementCallback);
        }
        return activityCompatApi23$SharedElementCallback23;
    }
    
    public static void finishAffinity(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.finishAffinity(activity);
            return;
        }
        activity.finish();
    }
    
    public static void finishAfterTransition(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.finishAfterTransition(activity);
            return;
        }
        activity.finish();
    }
    
    public static boolean invalidateOptionsMenu(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(activity);
            return true;
        }
        return false;
    }
    
    public static void postponeEnterTransition(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.postponeEnterTransition(activity);
        }
    }
    
    public static void requestPermissions(final Activity activity, final String[] array, final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            ActivityCompatApi23.requestPermissions(activity, array, n);
        }
        else if (activity instanceof ActivityCompat$OnRequestPermissionsResultCallback) {
            new Handler(Looper.getMainLooper()).post((Runnable)new ActivityCompat$1(array, activity, n));
        }
    }
    
    public static void setEnterSharedElementCallback(final Activity activity, final SharedElementCallback sharedElementCallback) {
        if (Build$VERSION.SDK_INT >= 23) {
            ActivityCompatApi23.setEnterSharedElementCallback(activity, createCallback23(sharedElementCallback));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.setEnterSharedElementCallback(activity, createCallback(sharedElementCallback));
        }
    }
    
    public static void setExitSharedElementCallback(final Activity activity, final SharedElementCallback sharedElementCallback) {
        if (Build$VERSION.SDK_INT >= 23) {
            ActivityCompatApi23.setExitSharedElementCallback(activity, createCallback23(sharedElementCallback));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.setExitSharedElementCallback(activity, createCallback(sharedElementCallback));
        }
    }
    
    public static boolean shouldShowRequestPermissionRationale(final Activity activity, final String s) {
        return Build$VERSION.SDK_INT >= 23 && ActivityCompatApi23.shouldShowRequestPermissionRationale(activity, s);
    }
    
    public static void startActivity(final Activity activity, final Intent intent, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startActivity((Context)activity, intent, bundle);
            return;
        }
        activity.startActivity(intent);
    }
    
    public static void startActivityForResult(final Activity activity, final Intent intent, final int n, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startActivityForResult(activity, intent, n, bundle);
            return;
        }
        activity.startActivityForResult(intent, n);
    }
    
    public static void startIntentSenderForResult(final Activity activity, final IntentSender intentSender, final int n, final Intent intent, final int n2, final int n3, final int n4, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startIntentSenderForResult(activity, intentSender, n, intent, n2, n3, n4, bundle);
            return;
        }
        activity.startIntentSenderForResult(intentSender, n, intent, n2, n3, n4);
    }
    
    public static void startPostponedEnterTransition(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.startPostponedEnterTransition(activity);
        }
    }
    
    @Deprecated
    public Uri getReferrer(final Activity activity) {
        Uri referrer;
        if (Build$VERSION.SDK_INT >= 22) {
            referrer = ActivityCompat22.getReferrer(activity);
        }
        else {
            final Intent intent = activity.getIntent();
            if ((referrer = (Uri)intent.getParcelableExtra("android.intent.extra.REFERRER")) == null) {
                final String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                if (stringExtra != null) {
                    return Uri.parse(stringExtra);
                }
                return null;
            }
        }
        return referrer;
    }
}
