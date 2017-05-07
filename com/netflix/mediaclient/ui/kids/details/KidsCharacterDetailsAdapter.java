// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.lolomo.KidsOneToOneVideoView;
import com.netflix.mediaclient.ui.kids.lolomo.KidsHorizontalVideoView;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;

public class KidsCharacterDetailsAdapter extends BaseAdapter
{
    private static final String TAG = "KidsCharacterDetailsAdapter";
    private final NetflixActivity activity;
    private KidsCharacterDetails charDetails;
    private final int contentPadding;
    private final Trackable trackable;
    
    public KidsCharacterDetailsAdapter(final NetflixFrag netflixFrag, final KidsCharacterDetails charDetails) {
        this.activity = netflixFrag.getNetflixActivity();
        this.charDetails = charDetails;
        this.trackable = new TrackableObject(charDetails.getGalleryRequestId(), charDetails.getGalleryTrackId(), 0);
        this.contentPadding = netflixFrag.getResources().getDimensionPixelSize(2131361878);
    }
    
    private VideoType getHeaderType(final int n) {
        return this.getItem(n).getType();
    }
    
    public int getCount() {
        return this.charDetails.getGallery().size();
    }
    
    public Video getItem(final int n) {
        return this.charDetails.getGallery().get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View o, final ViewGroup viewGroup) {
        Log.v("KidsCharacterDetailsAdapter", "Getting view for position: " + n);
        if (o == null) {
            if (KidsUtils.shouldShowHorizontalImages(this.activity)) {
                o = new KidsHorizontalVideoView(this.activity, false);
            }
            else {
                o = new KidsOneToOneVideoView((Context)this.activity, false);
            }
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361978);
            final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361979);
            ((View)o).setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2);
            ((View)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2, false)));
        }
        ((VideoViewGroup$IVideoView)o).update(this.getItem(n), this.trackable, n, false, false);
        return (View)o;
    }
    
    public void updateKidsCharacterDetails(final KidsCharacterDetails charDetails) {
        this.charDetails = charDetails;
    }
}
