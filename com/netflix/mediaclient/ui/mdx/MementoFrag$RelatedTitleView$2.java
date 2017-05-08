// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.view.View;
import com.netflix.model.branches.MementoVideoSwatch;
import android.view.View$OnClickListener;

class MementoFrag$RelatedTitleView$2 implements View$OnClickListener
{
    final /* synthetic */ MementoFrag$RelatedTitleView this$1;
    final /* synthetic */ MementoVideoSwatch val$swatch;
    
    MementoFrag$RelatedTitleView$2(final MementoFrag$RelatedTitleView this$1, final MementoVideoSwatch val$swatch) {
        this.this$1 = this$1;
        this.val$swatch = val$swatch;
    }
    
    public void onClick(final View view) {
        DetailsActivityLauncher.showMemento(this.this$1.this$0.getNetflixActivity(), VideoType.create(this.val$swatch.firstVideoType), this.val$swatch.firstVideoId, this.val$swatch.firstVideoTitle, PlayContext.NFLX_MDX_CONTEXT);
        if (this.this$1.this$0.isActivityValid()) {
            UIViewLogUtils.reportUIViewCommand((Context)this.this$1.this$0.getActivity(), UIViewLogging$UIViewCommandName.mementoRelatedHero, IClientLogging$ModalView.memento, this.this$1.this$0.getNetflixActivity().getDataContext());
        }
    }
}
