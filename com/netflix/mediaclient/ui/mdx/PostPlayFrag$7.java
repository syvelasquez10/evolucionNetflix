// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.os.Bundle;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.util.TimeUtils$CountdownTimer;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PostPlayFrag$7 extends BroadcastReceiver
{
    final /* synthetic */ PostPlayFrag this$0;
    
    PostPlayFrag$7(final PostPlayFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.v("PostPlayFrag", "Received null intent - ignoring");
        }
        else {
            final String stringExtra = intent.getStringExtra("extra_video_id");
            final float floatExtra = intent.getFloatExtra("extra_user_rating", -1.0f);
            if (this.this$0.video != null && StringUtils.safeEquals(this.this$0.video.getId(), stringExtra) && this.this$0.rating != null) {
                this.this$0.rating.setRating(floatExtra);
            }
        }
    }
}
