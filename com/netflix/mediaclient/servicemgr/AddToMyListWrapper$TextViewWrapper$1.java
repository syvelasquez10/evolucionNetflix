// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.view.View;
import android.view.View$OnClickListener;

class AddToMyListWrapper$TextViewWrapper$1 implements View$OnClickListener
{
    final /* synthetic */ AddToMyListWrapper$TextViewWrapper this$1;
    
    AddToMyListWrapper$TextViewWrapper$1(final AddToMyListWrapper$TextViewWrapper this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        String actionToken = null;
        UserActionLogUtils.reportRemoveFromQueueActionStarted((Context)this.this$1.activity, null, this.this$1.activity.getUiScreen());
        if (this.this$1.activity instanceof DetailsActivity) {
            actionToken = ((DetailsActivity)this.this$1.activity).getActionToken();
        }
        this.this$1.this$0.removeVideoFromMyList(this.this$1.videoId, actionToken);
    }
}
