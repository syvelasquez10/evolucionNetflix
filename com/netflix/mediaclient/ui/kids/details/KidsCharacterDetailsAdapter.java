// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.lolomo.KidsOneToOneVideoView;
import com.netflix.mediaclient.ui.kids.lolomo.KidsHorizontalVideoView;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.Video;
import android.widget.TextView;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.TrackableObject;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.widget.BaseAdapter;

public class KidsCharacterDetailsAdapter extends BaseAdapter implements StickyListHeadersAdapter
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
        this.contentPadding = netflixFrag.getResources().getDimensionPixelSize(2131361835);
    }
    
    private VideoType getHeaderType(final int n) {
        return this.getItem(n).getType();
    }
    
    public int getCount() {
        return this.charDetails.getGallery().size();
    }
    
    public long getHeaderId(final int n) {
        return this.getHeaderType(n).hashCode();
    }
    
    public View getHeaderView(int text, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            Log.v("KidsCharacterDetailsAdapter", "Creating header view for position: " + text);
            inflate = this.activity.getLayoutInflater().inflate(2130903103, (ViewGroup)null);
        }
        int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361926);
        final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361927);
        final int contentPadding = this.contentPadding;
        if (text != 0) {
            dimensionPixelSize = 0;
        }
        final int contentPadding2 = this.contentPadding;
        if (text == 0) {}
        inflate.setPadding(contentPadding, dimensionPixelSize, contentPadding2, dimensionPixelSize2);
        final TextView textView = (TextView)inflate.findViewById(2131165411);
        if (this.getHeaderType(text) == VideoType.MOVIE) {
            text = 2131492956;
        }
        else {
            text = 2131492955;
        }
        textView.setText(text);
        return inflate;
    }
    
    public Video getItem(final int n) {
        return this.charDetails.getGallery().get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        Log.v("KidsCharacterDetailsAdapter", "Getting view for position: " + n);
        Object o = view;
        if (view == null) {
            VideoViewGroup.IVideoView<Video> videoView;
            if (KidsUtils.shouldShowHorizontalImages(this.activity)) {
                videoView = new KidsHorizontalVideoView(this.activity, false);
            }
            else {
                videoView = new KidsOneToOneVideoView((Context)this.activity, false);
            }
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361918);
            final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361919);
            ((View)videoView).setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2);
            ((View)videoView).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2, false)));
            o = videoView;
        }
        ((VideoViewGroup.IVideoView<Video>)o).update(this.getItem(n), this.trackable, n, false);
        return (View)o;
    }
    
    public void updateKidsCharacterDetails(final KidsCharacterDetails charDetails) {
        this.charDetails = charDetails;
    }
}
