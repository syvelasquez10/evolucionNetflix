// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.HashSet;
import java.util.Collections;
import java.util.Locale;
import android.util.Log;
import android.support.v4.app.ActivityManagerCompat;
import android.app.ActivityManager;
import java.util.HashMap;
import android.support.v4.util.Pair;
import android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class MediaRouter$RouteGroup extends MediaRouter$RouteInfo
{
    private List<MediaRouter$RouteInfo> mRoutes;
    
    MediaRouter$RouteGroup(final MediaRouter$ProviderInfo mediaRouter$ProviderInfo, final String s, final String s2) {
        super(mediaRouter$ProviderInfo, s, s2);
        this.mRoutes = new ArrayList<MediaRouter$RouteInfo>();
    }
    
    public List<MediaRouter$RouteInfo> getRoutes() {
        return this.mRoutes;
    }
    
    @Override
    int maybeUpdateDescriptor(final MediaRouteDescriptor mDescriptor) {
        final boolean b = true;
        int n2;
        if (this.mDescriptor != mDescriptor && (this.mDescriptor = mDescriptor) != null) {
            final List<String> groupMemberIds = mDescriptor.getGroupMemberIds();
            final ArrayList<MediaRouter$RouteInfo> mRoutes = new ArrayList<MediaRouter$RouteInfo>();
            int n;
            if (groupMemberIds.size() != this.mRoutes.size()) {
                n = 1;
            }
            else {
                n = 0;
            }
            final Iterator<String> iterator = groupMemberIds.iterator();
        Label_0146_Outer:
            while (iterator.hasNext()) {
                final MediaRouter$RouteInfo route = MediaRouter.sGlobal.getRoute(MediaRouter.sGlobal.getUniqueId(this.getProvider(), iterator.next()));
                if (route == null) {
                    continue Label_0146_Outer;
                }
                mRoutes.add(route);
                if (n != 0 || this.mRoutes.contains(route)) {
                    continue Label_0146_Outer;
                }
                n = 1;
                while (true) {
                    continue Label_0146_Outer;
                    continue;
                }
            }
            if ((n2 = n) != 0) {
                this.mRoutes = mRoutes;
                n2 = n;
            }
        }
        else {
            n2 = 0;
        }
        return super.updateDescriptor(mDescriptor) | ((n2 != 0 && b) ? 1 : 0);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append('[');
        for (int size = this.mRoutes.size(), i = 0; i < size; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.mRoutes.get(i));
        }
        sb.append(']');
        return sb.toString();
    }
}
