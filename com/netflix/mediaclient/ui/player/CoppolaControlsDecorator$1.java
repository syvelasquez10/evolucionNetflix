// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import android.view.View$OnClickListener;

class CoppolaControlsDecorator$1 implements View$OnClickListener
{
    final /* synthetic */ CoppolaControlsDecorator this$0;
    
    CoppolaControlsDecorator$1(final CoppolaControlsDecorator this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (!this.this$0.getController().isMDXTargetSelected()) {
            this.this$0.updatePlaybackStatus(this.this$0.getController().getPlayer().isPlaying());
            this.this$0.getController().getPlayPauseListener().onClick(view);
        }
    }
}
