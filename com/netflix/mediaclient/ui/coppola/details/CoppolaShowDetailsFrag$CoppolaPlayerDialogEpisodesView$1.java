// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.coppola.details;

import com.netflix.mediaclient.ui.details.AbsEpisodeView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.TextView;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag;
import android.content.Context;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag$BarkerPlayerDialogEpisodesView;
import android.annotation.TargetApi;
import android.view.View;
import android.view.View$OnClickListener;

class CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView$1 implements View$OnClickListener
{
    final /* synthetic */ CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView this$1;
    final /* synthetic */ CoppolaShowDetailsFrag val$this$0;
    
    CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView$1(final CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView this$1, final CoppolaShowDetailsFrag val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @TargetApi(16)
    public void onClick(final View view) {
        if (this.this$1.synopsis.getMaxLines() == 4) {
            this.this$1.synopsis.setMaxLines(20);
            this.this$1.title.setMaxLines(4);
            return;
        }
        this.this$1.synopsis.setMaxLines(4);
        this.this$1.title.setMaxLines(1);
    }
}
