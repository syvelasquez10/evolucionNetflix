// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.IrisUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.View$OnClickListener;

class VideoDetailsViewGroup_Ab7994$1 implements View$OnClickListener
{
    final /* synthetic */ VideoDetailsViewGroup_Ab7994 this$0;
    final /* synthetic */ VideoDetails val$details;
    
    VideoDetailsViewGroup_Ab7994$1(final VideoDetailsViewGroup_Ab7994 this$0, final VideoDetails val$details) {
        this.this$0 = this$0;
        this.val$details = val$details;
    }
    
    public void onClick(final View view) {
        IrisUtils.startShare(this.this$0.getContext(), this.val$details.getId(), this.val$details.getTitle(), this.val$details.getType());
    }
}
