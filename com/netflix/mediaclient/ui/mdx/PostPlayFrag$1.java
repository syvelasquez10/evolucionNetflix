// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.Html;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.BroadcastReceiver;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.util.TimeUtils$CountdownTimer;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.View;
import android.view.View$OnClickListener;

class PostPlayFrag$1 implements View$OnClickListener
{
    final /* synthetic */ PostPlayFrag this$0;
    
    PostPlayFrag$1(final PostPlayFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.handlePlayNow();
    }
}
