// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.ui.mdx.RemotePlayer;
import com.netflix.mediaclient.ui.mdx.MdxTarget;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.AdapterView$OnItemClickListener;

final class MdxUtils$1 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ MdxUtils$MdxTargetSelectionDialogInterface val$callbacks;
    final /* synthetic */ MdxTargetSelection val$mdxTargetSelector;
    final /* synthetic */ ServiceManager val$serviceManager;
    
    MdxUtils$1(final NetflixActivity val$activity, final ServiceManager val$serviceManager, final MdxTargetSelection val$mdxTargetSelector, final MdxUtils$MdxTargetSelectionDialogInterface val$callbacks) {
        this.val$activity = val$activity;
        this.val$serviceManager = val$serviceManager;
        this.val$mdxTargetSelector = val$mdxTargetSelector;
        this.val$callbacks = val$callbacks;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, int positionInSeconds, final long n) {
        final int n2 = 0;
        Log.d("MdxUtils", "Mdx target clicked: item with id " + n + ", on position " + positionInSeconds);
        this.val$activity.removeVisibleDialog();
        if (this.val$serviceManager == null || !this.val$serviceManager.isReady()) {
            Log.w("MdxUtils", "Service not ready - bailing early");
            return;
        }
        this.val$mdxTargetSelector.setTarget(positionInSeconds);
        final MdxTarget selectedTarget = this.val$mdxTargetSelector.getSelectedTarget();
        if (selectedTarget == null) {
            Log.e("MdxUtils", "Target is NULL, this should NOT happen!");
        }
        else if (selectedTarget.getUUID() != null && selectedTarget.getUUID().equals(this.val$serviceManager.getMdx().getCurrentTarget())) {
            if (Log.isLoggable("MdxUtils", 3)) {
                Log.d("MdxUtils", "Same MDX target selected. Do nothing and dismiss dialog");
            }
        }
        else if (selectedTarget.isLocal()) {
            if (this.val$callbacks.isPlayingRemotely()) {
                Log.d("MdxUtils", "We were playing remotely - switching to playback locally");
                this.val$serviceManager.getMdx().switchPlaybackFromTarget(null, 0);
                final Asset create = Asset.create(this.val$callbacks.getVideoDetails(), this.val$callbacks.getPlayContext(), PlayerActivity.PIN_VERIFIED);
                create.setPlaybackBookmark((int)(this.val$callbacks.getCurrentPositionMs() / 1000L));
                PlaybackLauncher.startPlaybackForceLocal(this.val$activity, create);
                this.val$callbacks.notifyPlayingBackLocal();
            }
            else {
                Log.d("MdxUtils", "Target is local. Remove current target from MDX agent.");
                this.val$serviceManager.getMdx().setCurrentTarget(null);
            }
        }
        else if (MdxUtils.isMdxTargetAvailable(this.val$serviceManager, selectedTarget.getUUID())) {
            if (this.val$callbacks.isPlayingLocally() || this.val$callbacks.isPlayingRemotely()) {
                if (Log.isLoggable("MdxUtils", 3)) {
                    Log.d("MdxUtils", "Remote target is available, switching playback to: " + selectedTarget.getUUID());
                }
                final RemotePlayer player = this.val$callbacks.getPlayer();
                if (player != null) {
                    final int n3 = positionInSeconds = player.getPositionInSeconds();
                    if (Log.isLoggable("MdxUtils", 3)) {
                        Log.d("MdxUtils", "Start remote playback from position [sec] " + n3);
                        positionInSeconds = n3;
                    }
                }
                else {
                    Log.e("MdxUtils", "Remote player is null. This should not happen!");
                    positionInSeconds = n2;
                }
                this.val$serviceManager.getMdx().switchPlaybackFromTarget(selectedTarget.getUUID(), positionInSeconds);
                this.val$callbacks.notifyPlayingBackRemote();
            }
            else {
                if (Log.isLoggable("MdxUtils", 3)) {
                    Log.d("MdxUtils", "Target is remote. Setting new current target to: " + selectedTarget.getUUID());
                }
                this.val$serviceManager.getMdx().setCurrentTarget(selectedTarget.getUUID());
            }
        }
        else {
            Log.w("MdxUtils", "Remote target is NOT available, stay and dismiss dialog");
        }
        this.val$activity.invalidateOptionsMenu();
    }
}
