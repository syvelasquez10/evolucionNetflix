// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
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
        int n = 0;
        View inflate = view;
        if (view == null) {
            Log.v("KidsCharacterDetailsAdapter", "Creating header view for position: " + text);
            inflate = this.activity.getLayoutInflater().inflate(2130903097, (ViewGroup)null);
        }
        final int contentPadding = this.contentPadding;
        int contentPadding2;
        if (text > 0) {
            contentPadding2 = 0;
        }
        else {
            contentPadding2 = this.contentPadding;
        }
        final int contentPadding3 = this.contentPadding;
        if (text > 0) {
            n = this.contentPadding / 2;
        }
        inflate.setPadding(contentPadding, contentPadding2, contentPadding3, n);
        final TextView textView = (TextView)inflate.findViewById(2131165397);
        if (this.getHeaderType(text) == VideoType.MOVIE) {
            text = 2131492957;
        }
        else {
            text = 2131492956;
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
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361835);
            ((View)videoView).setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            o = videoView;
        }
        ((VideoViewGroup.IVideoView<Video>)o).update(this.getItem(n), this.trackable, n, false);
        return (View)o;
    }
    
    public void updateKidsCharacterDetails(final KidsCharacterDetails charDetails) {
        this.charDetails = charDetails;
    }
}
