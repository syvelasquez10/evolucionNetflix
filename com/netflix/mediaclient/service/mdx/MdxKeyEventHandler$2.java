// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.LastKeyEvent;
import com.netflix.mediaclient.ui.mdx.RemotePlayer;

class MdxKeyEventHandler$2 implements Runnable
{
    final /* synthetic */ MdxKeyEventHandler this$0;
    final /* synthetic */ RemotePlayer val$player;
    
    MdxKeyEventHandler$2(final MdxKeyEventHandler this$0, final RemotePlayer val$player) {
        this.this$0 = this$0;
        this.val$player = val$player;
    }
    
    @Override
    public void run() {
        this.val$player.setVolume(this.this$0.callbacks.getVolumeAsPercent() + 10);
        this.this$0.callbacks.onVolumeSet(this.val$player.getVolume());
    }
}
