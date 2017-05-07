// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.TextUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MdxReceiver$FetchNextSeriesEpisodeVideoDetailsForMdxCallback extends LoggingManagerCallback
{
    private final NetflixActivity mActivity;
    private boolean processed;
    
    public MdxReceiver$FetchNextSeriesEpisodeVideoDetailsForMdxCallback(final String s, final NetflixActivity mActivity) {
        super(s);
        this.processed = false;
        this.mActivity = mActivity;
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
        super.onPostPlayVideosFetched(list, status);
        if (!this.processed && this.mActivity != null && status.isSucces() && list.size() > 0) {
            final String id = list.get(0).getId();
            if (!TextUtils.isEmpty((CharSequence)id)) {
                this.mActivity.getServiceManager().getBrowse().fetchEpisodeDetails(id, new MdxReceiver$FetchPostPlayForPlaybackCallback("nf_mdx", this.mActivity));
                this.processed = true;
            }
        }
    }
}
