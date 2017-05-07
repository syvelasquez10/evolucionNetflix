// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.hardware.display;

import android.os.Build$VERSION;
import android.content.Context;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat
{
    private static final WeakHashMap<Context, DisplayManagerCompat> sInstances;
    
    static {
        sInstances = new WeakHashMap<Context, DisplayManagerCompat>();
    }
    
    public static DisplayManagerCompat getInstance(final Context context) {
        synchronized (DisplayManagerCompat.sInstances) {
            DisplayManagerCompat displayManagerCompat;
            if ((displayManagerCompat = DisplayManagerCompat.sInstances.get(context)) == null) {
                if (Build$VERSION.SDK_INT >= 17) {
                    displayManagerCompat = new DisplayManagerCompat$JellybeanMr1Impl(context);
                }
                else {
                    displayManagerCompat = new DisplayManagerCompat$LegacyImpl(context);
                }
                DisplayManagerCompat.sInstances.put(context, displayManagerCompat);
            }
            return displayManagerCompat;
        }
    }
}
