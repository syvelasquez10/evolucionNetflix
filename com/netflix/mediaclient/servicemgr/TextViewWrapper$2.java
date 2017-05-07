// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.widget.Toast;
import android.view.View;
import android.view.View$OnClickListener;

class TextViewWrapper$2 implements View$OnClickListener
{
    final /* synthetic */ TextViewWrapper this$0;
    
    TextViewWrapper$2(final TextViewWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        String actionToken = null;
        this.this$0.setAsInList();
        Toast.makeText(this.this$0.textView.getContext(), (CharSequence)this.this$0.textView.getContext().getString(2131493223), 0).show();
        UserActionLogUtils.reportAddToQueueActionStarted((Context)this.this$0.activity, null, this.this$0.activity.getUiScreen());
        if (this.this$0.activity instanceof DetailsActivity) {
            actionToken = ((DetailsActivity)this.this$0.activity).getActionToken();
        }
        this.this$0.addToMyListWrapper.addVideoToMyList(this.this$0.videoId, this.this$0.videoType, this.this$0.trackId, actionToken);
    }
}
