// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.widget.SeekBar;

class PlayerFragment$VideoPositionListener$1 implements Runnable
{
    final /* synthetic */ PlayerFragment$VideoPositionListener this$1;
    final /* synthetic */ int val$progress;
    final /* synthetic */ SeekBar val$seekBar;
    
    PlayerFragment$VideoPositionListener$1(final PlayerFragment$VideoPositionListener this$1, final SeekBar val$seekBar, final int val$progress) {
        this.this$1 = this$1;
        this.val$seekBar = val$seekBar;
        this.val$progress = val$progress;
    }
    
    @Override
    public void run() {
        this.this$1.onProgressChanged(this.val$seekBar, this.val$progress, true);
    }
}
