// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.lolomo.KidsHorizontalVideoView;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;

public class KidsCharacterDetailsAdapter extends BaseAdapter
{
    private static final String TAG = "KidsCharacterDetailsAdapter";
    private final NetflixActivity activity;
    private KidsCharacterDetails charDetails;
    
    public KidsCharacterDetailsAdapter(final NetflixFrag netflixFrag, final KidsCharacterDetails charDetails) {
        this.activity = netflixFrag.getNetflixActivity();
        this.charDetails = charDetails;
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
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        Log.v("KidsCharacterDetailsAdapter", "Getting view for position: " + n);
        Object o = view;
        if (view == null) {
            o = new KidsHorizontalVideoView((Context)this.activity, false);
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361835);
            ((View)o).setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        }
        final PlayContext playContext = ((PlayContextProvider)this.activity).getPlayContext();
        ((KidsHorizontalVideoView)o).update(this.getItem(n), playContext, playContext.getVideoPos(), false);
        return (View)o;
    }
    
    public void updateKidsCharacterDetails(final KidsCharacterDetails charDetails) {
        this.charDetails = charDetails;
    }
}
