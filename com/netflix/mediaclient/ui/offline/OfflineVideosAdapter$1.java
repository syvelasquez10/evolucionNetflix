// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$VideoAndProfileData;
import android.widget.TextView;
import android.text.format.Formatter;
import android.text.TextUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView$ViewHolder;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.view.View$OnClickListener;

class OfflineVideosAdapter$1 implements View$OnClickListener
{
    final /* synthetic */ OfflineVideosAdapter this$0;
    
    OfflineVideosAdapter$1(final OfflineVideosAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.mSkipAdultContent = !this.this$0.mSkipAdultContent;
        this.this$0.refreshUIData();
    }
}
