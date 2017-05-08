// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.mdx.MdxTarget;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class TopPanel$8 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ TopPanel this$0;
    final /* synthetic */ PlayerFragment val$controller;
    final /* synthetic */ boolean val$wasPlaying;
    
    TopPanel$8(final TopPanel this$0, final PlayerFragment val$controller, final boolean val$wasPlaying) {
        this.this$0 = this$0;
        this.val$controller = val$controller;
        this.val$wasPlaying = val$wasPlaying;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int target, final long n) {
        Log.d("screen", "Mdx target clicked: item with id " + n + ", on position " + target);
        this.val$controller.getNetflixActivity().removeVisibleDialog();
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
                if (Log.isLoggable()) {
                    Log.d("screen", "Remote target is selected " + selectedTarget);
                }
                if (MdxUtils.isMdxTargetAvailable(this.val$controller.getNetflixActivity().getServiceManager(), selectedTarget.getUUID())) {
                    Log.d("screen", "Remote target is available, start MDX playback, use local bookmark!");
                    this.val$controller.getNetflixActivity().getServiceManager().getMdx().setCurrentTarget(selectedTarget.getUUID());
                    final Asset currentAsset = this.val$controller.getCurrentAsset();
                    currentAsset.setPlaybackBookmark(this.val$controller.getPlayer().getCurrentPositionMs() / 1000);
                    PlaybackLauncher.startPlaybackAfterPIN(this.val$controller.getNetflixActivity(), currentAsset);
                    this.val$controller.getNetflixActivity().getServiceManager().getMdx().transferPlaybackFromLocal();
                    if (PlaybackLauncher.shouldPlayOnRemoteTarget(this.val$controller.getNetflixActivity().getServiceManager())) {
                        this.val$controller.getNetflixActivity().finish();
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
