// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.os.Bundle;
import android.content.Intent;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.content.Context;

public class ContextCompat
{
    public static final Drawable getDrawable(final Context context, final int n) {
        if (Build$VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getDrawable(context, n);
        }
        return context.getResources().getDrawable(n);
    }
    
    public static boolean startActivities(final Context context, final Intent[] array, final Bundle bundle) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 16) {
            ContextCompatJellybean.startActivities(context, array, bundle);
            return true;
        }
        if (sdk_INT >= 11) {
            ContextCompatHoneycomb.startActivities(context, array);
            return true;
        }
        return false;
    }
}
