// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.ArrayList;
import java.util.List;
import android.media.MediaRouter$RouteCategory;
import android.content.Context;

public final class MediaRouterJellybean$RouteCategory
{
    public static CharSequence getName(final Object o, final Context context) {
        return ((MediaRouter$RouteCategory)o).getName(context);
    }
    
    public static List getRoutes(final Object o) {
        final ArrayList list = new ArrayList();
        ((MediaRouter$RouteCategory)o).getRoutes((List)list);
        return list;
    }
    
    public static int getSupportedTypes(final Object o) {
        return ((MediaRouter$RouteCategory)o).getSupportedTypes();
    }
    
    public static boolean isGroupable(final Object o) {
        return ((MediaRouter$RouteCategory)o).isGroupable();
    }
}
