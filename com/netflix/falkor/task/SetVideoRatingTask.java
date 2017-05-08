// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import android.content.Context;
import com.netflix.mediaclient.ui.rating.Ratings;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.RatingInfo;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public class SetVideoRatingTask extends CmpTask
{
    private final String id;
    private final int newRating;
    private final int trackId;
    private final VideoType type;
    
    public SetVideoRatingTask(final CachedModelProxy cachedModelProxy, final String id, final VideoType type, final int newRating, final int trackId, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.id = id;
        this.type = type;
        this.newRating = newRating;
        this.trackId = trackId;
    }
    
    private void notifyUserRatingChanged(final RatingInfo ratingInfo) {
        if (ratingInfo == null) {
            Log.d("CachedModelProxy", "null user rating - can't notify listeners");
        }
        else {
            final Context context = this.modelProxy.getContext();
            if (context == null) {
                Log.d("CachedModelProxy", "null service - can't notify listeners");
                return;
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.falkor.ACTION_NOTIFY_OF_RATINGS_CHANGE").putExtra("extra_video_id", this.id).putExtra("extra_user_rating", ratingInfo.getUserRating()).putExtra("extra_user_thumb_rating", ratingInfo.getUserThumbRating()));
            if (Log.isLoggable()) {
                final StringBuilder append = new StringBuilder().append("sent notification of video ratings change, video: ").append(this.id).append(", rating: ");
                float userRating;
                if (!Ratings.isAndroidThumbActive()) {
                    userRating = ratingInfo.getUserRating();
                }
                else {
                    userRating = ratingInfo.getUserThumbRating();
                }
                Log.d("CachedModelProxy", append.append(userRating).toString());
            }
        }
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        if (Ratings.isAndroidThumbActive()) {
            list.add(PQL.create(this.type.getValue(), this.id, "setThumbRating"));
            return;
        }
        list.add(PQL.create(this.type.getValue(), this.id, "setRating"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onVideoRatingSet(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final RatingInfo ratingInfo = (RatingInfo)this.modelProxy.getVideo(PQL.create(this.type.getValue(), this.id, "summary"));
        browseAgentCallback.onVideoRatingSet(ratingInfo, CommonStatus.OK);
        this.notifyUserRatingChanged(ratingInfo);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(2);
        list.add(new DataUtil$StringPair("param", String.valueOf(this.newRating)));
        list.add(new DataUtil$StringPair("param", String.valueOf(this.trackId)));
        return list;
    }
    
    @Override
    protected boolean shouldUseCallMethod() {
        return true;
    }
}
