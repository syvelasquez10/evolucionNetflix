// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.AbsListView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.widget.GridView;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.View;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class KubrickShowDetailsFrag$HeroSlideshow$2 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ KubrickShowDetailsFrag$HeroSlideshow this$1;
    final /* synthetic */ AdvancedImageView val$horzDispImg;
    
    KubrickShowDetailsFrag$HeroSlideshow$2(final KubrickShowDetailsFrag$HeroSlideshow this$1, final AdvancedImageView val$horzDispImg) {
        this.this$1 = this$1;
        this.val$horzDispImg = val$horzDispImg;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        if (Log.isLoggable("KubrickShowDetailsFrag", 6)) {
            Log.e("KubrickShowDetailsFrag", "HeroSlideshow: " + s);
        }
    }
    
    @Override
    public void onResponse(final Bitmap imageBitmap, final String s) {
        if (imageBitmap != null) {
            this.val$horzDispImg.setImageBitmap(imageBitmap);
            this.this$1.this$0.gridView.invalidateViews();
        }
    }
}
