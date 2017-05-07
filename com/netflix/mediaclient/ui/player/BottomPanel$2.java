// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.ui.mdx.MdxTarget;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class BottomPanel$2 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ BottomPanel this$0;
    final /* synthetic */ PlayerActivity val$controller;
    final /* synthetic */ boolean val$wasPlaying;
    
    BottomPanel$2(final BottomPanel this$0, final PlayerActivity val$controller, final boolean val$wasPlaying) {
        this.this$0 = this$0;
        this.val$controller = val$controller;
        this.val$wasPlaying = val$wasPlaying;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int target, final long n) {
        Log.d("screen", "Mdx target clicked: item with id " + n + ", on position " + target);
        this.val$controller.removeVisibleDialog();
        if (this.this$0.mdxTargetSelector != null) {
            this.this$0.mdxTargetSelector.setTarget(target);
            final MdxTarget selectedTarget = this.this$0.mdxTargetSelector.getSelectedTarget();
            if (selectedTarget == null) {
                Log.e("screen", "Target is NULL, this should NOT happen!");
                if (this.val$wasPlaying) {
                    this.val$controller.doUnpause();
                }
            }
            else if (selectedTarget.isLocal()) {
                Log.d("screen", "Target is local, same as cancel. Do nothing");
                if (this.val$wasPlaying) {
                    this.val$controller.doUnpause();
                }
            }
            else {
                if (Log.isLoggable("screen", 3)) {
                    Log.d("screen", "Remote target is selected " + selectedTarget);
                }
                if (MdxUtils.isMdxTargetAvailable(this.val$controller.getServiceManager(), selectedTarget.getUUID())) {
                    Log.d("screen", "Remote target is available, start MDX playback, use local bookmark!");
                    this.val$controller.getServiceManager().getMdx().setCurrentTarget(selectedTarget.getUUID());
                    final Asset currentAsset = this.val$controller.getCurrentAsset();
                    currentAsset.setPlaybackBookmark(this.val$controller.getPlayer().getCurrentPositionMs() / 1000);
                    PlaybackLauncher.startPlaybackAfterPIN(this.val$controller, currentAsset);
                    if (PlaybackLauncher.shouldPlayOnRemoteTarget(this.val$controller.getServiceManager())) {
                        this.val$controller.finish();
                    }
                }
                else {
                    Log.w("screen", "Remote target is NOT available anymore, continue local plaback");
                    if (this.val$wasPlaying) {
                        this.val$controller.doUnpause();
                    }
                }
            }
        }
    }
}
