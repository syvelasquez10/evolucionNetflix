// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.app.Fragment;
import com.netflix.mediaclient.android.app.Status;
import android.view.LayoutInflater;
import android.annotation.SuppressLint;
import java.io.Serializable;
import android.os.Bundle;
import android.view.View;
import com.netflix.falkor.PQL;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.Log;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$OnNetflixRatingBarChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;

class RatingDialogFrag$1 implements NetflixRatingBar$RatingBarDataProvider
{
    final /* synthetic */ RatingDialogFrag this$0;
    
    RatingDialogFrag$1(final RatingDialogFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean destroyed() {
        return this.this$0.isDestroyed();
    }
    
    @Override
    public PlayContext getPlayContext() {
        return PlayContext.EMPTY_CONTEXT;
    }
    
    @Override
    public ServiceManager getServiceManager() {
        return this.this$0.getNetflixActivity().getServiceManager();
    }
    
    @Override
    public String getVideoId() {
        return this.this$0.mVideoId;
    }
    
    @Override
    public VideoType getVideoType() {
        return this.this$0.mVideoType;
    }
}
