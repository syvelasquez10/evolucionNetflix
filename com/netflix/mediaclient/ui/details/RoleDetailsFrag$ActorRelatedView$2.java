// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.model.branches.FalkorVideo;
import android.view.View$OnClickListener;

class RoleDetailsFrag$ActorRelatedView$2 implements View$OnClickListener
{
    final /* synthetic */ RoleDetailsFrag$ActorRelatedView this$1;
    final /* synthetic */ FalkorVideo val$video;
    
    RoleDetailsFrag$ActorRelatedView$2(final RoleDetailsFrag$ActorRelatedView this$1, final FalkorVideo val$video) {
        this.this$1 = this$1;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        DetailsActivityLauncher.showMemento(this.this$1.this$0.getNetflixActivity(), this.val$video.getType(), this.val$video.getId(), this.val$video.getTitle(), PlayContext.NFLX_MDX_CONTEXT);
        UIViewLogUtils.reportUIViewCommand(this.this$1.getContext(), UIViewLogging$UIViewCommandName.mementoRDPRelatedExpandedGoToDP, IClientLogging$ModalView.rdp, this.this$1.this$0.getNetflixActivity().getDataContext());
    }
}
