// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.javabridge.invoke.media.AuthorizationParams;
import com.netflix.mediaclient.util.ConnectivityUtils;
import java.util.List;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.util.Pair;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Playable;

public class ServiceManagerUtils
{
    private static final String TAG = "ServiceManagerUtils";
    
    public static void castPrefetchAndCacheManifestIfEnabled(final ServiceManager serviceManager, final Playable playable, final PlayContext playContext) {
        if (serviceManager == null) {
            Log.w("ServiceManagerUtils", "manager is null - can't cache manifest");
        }
        else {
            if (!playable.isAvailableToStream()) {
                Log.w("ServiceManagerUtils", "video is not available for streaming - can't cache manifest");
                return;
            }
            if (!serviceManager.getConfiguration().isDisableCastFaststart()) {
                final ArrayList<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>();
                list.add((Pair<String, Integer>)Pair.create((Object)playable.getPlayableId(), (Object)0));
                MdxAgent$Utils.attemptMdxPrefetch(serviceManager, list);
            }
            else {
                Log.d("ServiceManagerUtils", "CastFaststart is disabled");
            }
            if (serviceManager.getPlayer() == null) {
                Log.w("ServiceManagerUtils", "player is null - can't cache manifest");
                return;
            }
            if (serviceManager.getPlayer().isManifestCacheEnabled()) {
                if (Log.isLoggable()) {
                    Log.d("ServiceManagerUtils", "schedule manifest pre-fectiion for " + playable);
                }
                try {
                    serviceManager.getPlayer().getManifestCache().cacheSchedule(new IManifestCache$CacheScheduleRequest[] { new IManifestCache$CacheScheduleRequest(Integer.parseInt(playable.getPlayableId()), 0L, 1L) }, new AuthorizationParams(serviceManager.getContext(), playContext, ConnectivityUtils.getCurrentNetType(serviceManager.getContext()), serviceManager.getConfiguration().isPreviewContentEnabled()));
                }
                catch (NumberFormatException ex) {
                    Log.w("ServiceManagerUtils", "schedule manifest pre-fectiion gets invalid playableId, ignored");
                }
            }
        }
    }
    
    public static String getCurrentDeviceFriendlyName(final ServiceManager serviceManager) {
        if (!isMdxAgentAvailable(serviceManager)) {
            Log.e("ServiceManagerUtils", "Service manager is not available!");
            return "";
        }
        final String currentTarget = serviceManager.getMdx().getCurrentTarget();
        final Pair<String, String>[] targetList = serviceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1 || currentTarget == null) {
            if (Log.isLoggable()) {
                Log.e("ServiceManagerUtils", "No devices, current device " + currentTarget);
            }
            return "";
        }
        for (int i = 0; i < targetList.length; ++i) {
            if (currentTarget.equals(targetList[i].first)) {
                if (Log.isLoggable()) {
                    Log.d("ServiceManagerUtils", "Friendly name " + (String)targetList[i].second + " found for current device " + currentTarget);
                }
                return (String)targetList[i].second;
            }
        }
        if (Log.isLoggable()) {
            Log.e("ServiceManagerUtils", "No match found for current device " + currentTarget);
        }
        return "";
    }
    
    public static boolean isMdxAgentAvailable(final ServiceManager serviceManager) {
        return serviceManager != null && serviceManager.isReady() && serviceManager.getMdx() != null;
    }
}
