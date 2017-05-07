// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.RemotePlayer;
import com.netflix.mediaclient.ui.mdx.MdxTarget;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import android.app.Activity;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog;
import android.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class MdxUtils
{
    private static final String TAG = "MdxUtils";
    
    public static AlertDialog createMdxTargetSelectionDialog(final NetflixActivity netflixActivity, final MdxTargetSelectionDialogInterface mdxTargetSelectionDialogInterface) {
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        final MdxTargetSelection targetSelection = mdxTargetSelectionDialogInterface.getTargetSelection();
        final int devicePositionByUUID = targetSelection.getDevicePositionByUUID(serviceManager.getMdx().getCurrentTarget());
        targetSelection.setTarget(devicePositionByUUID);
        final MdxTargetSelectionDialog.Builder builder = new MdxTargetSelectionDialog.Builder(netflixActivity);
        builder.setCancelable(true);
        builder.setTitle(2131493087);
        builder.setAdapterData(targetSelection.getTargets((Context)netflixActivity));
        String format = "";
        if (mdxTargetSelectionDialogInterface.getVideoDetails() != null) {
            format = format;
            if (StringUtils.isNotEmpty(mdxTargetSelectionDialogInterface.getVideoDetails().getTitle())) {
                format = String.format(netflixActivity.getString(2131493178), mdxTargetSelectionDialogInterface.getVideoDetails().getTitle());
            }
        }
        builder.setSelection(devicePositionByUUID, format);
        builder.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, int positionInSeconds, final long n) {
                Log.d("MdxUtils", "Mdx target clicked: item with id " + n + ", on position " + positionInSeconds);
                netflixActivity.removeVisibleDialog();
                if (serviceManager == null || !serviceManager.isReady()) {
                    Log.w("MdxUtils", "Service not ready - bailing early");
                    return;
                }
                targetSelection.setTarget(positionInSeconds);
                final MdxTarget selectedTarget = targetSelection.getSelectedTarget();
                if (selectedTarget == null) {
                    Log.e("MdxUtils", "Target is NULL, this should NOT happen!");
                }
                else if (selectedTarget.getUUID() != null && selectedTarget.getUUID().equals(serviceManager.getMdx().getCurrentTarget())) {
                    if (Log.isLoggable("MdxUtils", 3)) {
                        Log.d("MdxUtils", "Same MDX target selected. Do nothing and dismiss dialog");
                    }
                }
                else if (selectedTarget.isLocal()) {
                    if (mdxTargetSelectionDialogInterface.isPlayingRemotely()) {
                        Log.d("MdxUtils", "We were playing remotely - switching to playback locally");
                        serviceManager.getMdx().switchPlaybackFromTarget(null, 0);
                        final Asset create = Asset.create(mdxTargetSelectionDialogInterface.getVideoDetails(), mdxTargetSelectionDialogInterface.getPlayContext());
                        create.setPlaybackBookmark((int)(mdxTargetSelectionDialogInterface.getCurrentPositionMs() / 1000L));
                        PlayerActivity.playVideo(netflixActivity, create);
                        mdxTargetSelectionDialogInterface.notifyPlayingBackLocal();
                    }
                    else {
                        Log.d("MdxUtils", "Target is local. Remove current target from MDX agent.");
                        serviceManager.getMdx().setCurrentTarget(null);
                    }
                }
                else if (MdxUtils.isMdxTargetAvailable(serviceManager, selectedTarget.getUUID())) {
                    if (mdxTargetSelectionDialogInterface.isPlayingLocally() || mdxTargetSelectionDialogInterface.isPlayingRemotely()) {
                        if (Log.isLoggable("MdxUtils", 3)) {
                            Log.d("MdxUtils", "Remote target is available, switching playback to: " + selectedTarget.getUUID());
                        }
                        final RemotePlayer player = mdxTargetSelectionDialogInterface.getPlayer();
                        positionInSeconds = 0;
                        if (player != null) {
                            final int n2 = positionInSeconds = player.getPositionInSeconds();
                            if (Log.isLoggable("MdxUtils", 3)) {
                                Log.d("MdxUtils", "Start remote playback from position [sec] " + n2);
                                positionInSeconds = n2;
                            }
                        }
                        else {
                            Log.e("MdxUtils", "Remote player is null. This should not happen!");
                        }
                        serviceManager.getMdx().switchPlaybackFromTarget(selectedTarget.getUUID(), positionInSeconds);
                        mdxTargetSelectionDialogInterface.notifyPlayingBackRemote();
                    }
                    else {
                        if (Log.isLoggable("MdxUtils", 3)) {
                            Log.d("MdxUtils", "Target is remote. Setting new current target to: " + selectedTarget.getUUID());
                        }
                        serviceManager.getMdx().setCurrentTarget(selectedTarget.getUUID());
                    }
                }
                else {
                    Log.w("MdxUtils", "Remote target is NOT available, stay and dismiss dialog");
                }
                netflixActivity.invalidateOptionsMenu();
            }
        });
        return builder.create();
    }
    
    public static boolean isCurrentMdxTargetAvailable(final ServiceManager serviceManager) {
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null || !serviceManager.getMdx().isReady()) {
            Log.d("MdxUtils", "MDX service is NOT ready");
            return false;
        }
        return isMdxTargetAvailable(serviceManager, serviceManager.getMdx().getCurrentTarget());
    }
    
    public static boolean isMdxTargetAvailable(final ServiceManager serviceManager, final String s) {
        if (Log.isLoggable("MdxUtils", 3)) {
            Log.d("MdxUtils", "Check if MDX remote target exist in target list: " + s);
        }
        if (StringUtils.isEmpty(s)) {
            Log.d("MdxUtils", "uuid is empty");
            return false;
        }
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null || !serviceManager.getMdx().isReady()) {
            Log.d("MdxUtils", "MDX service is NOT ready");
            return false;
        }
        final Pair<String, String>[] targetList = serviceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1) {
            Log.w("MdxUtils", "No MDX remote targets found");
            return false;
        }
        for (int i = 0; i < targetList.length; ++i) {
            if (s.equals(targetList[i].first)) {
                Log.d("MdxUtils", "Target found");
                return true;
            }
        }
        Log.w("MdxUtils", "Target NOT found!");
        return false;
    }
    
    public interface MdxTargetSelectionDialogInterface
    {
        long getCurrentPositionMs();
        
        PlayContext getPlayContext();
        
        RemotePlayer getPlayer();
        
        MdxTargetSelection getTargetSelection();
        
        Playable getVideoDetails();
        
        boolean isPlayingLocally();
        
        boolean isPlayingRemotely();
        
        void notifyPlayingBackLocal();
        
        void notifyPlayingBackRemote();
    }
}
