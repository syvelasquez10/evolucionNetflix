// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickMovieDetailsActivity;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.ui.kids.details.KidsDetailsActivity;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.io.Serializable;
import android.os.Parcelable;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.app.Activity;

public class DetailsActivityLauncher
{
    static final String EXTRA_ACTION = "extra_action";
    static final String EXTRA_ACTION_TOKEN = "extra_action_token";
    static final String EXTRA_EPISODE_ID = "extra_episode_id";
    public static final String EXTRA_PLAY_CONTEXT = "extra_playcontext";
    public static final String EXTRA_VIDEO_ID = "extra_video_id";
    public static final String EXTRA_VIDEO_TITLE = "extra_video_title";
    public static final String EXTRA_VIDEO_TYPE = "extra_video_type";
    public static final String INTENT_MDP = "com.netflix.mediaclient.intent.action.NOTIFICATION_MOVIE_DETAILS";
    public static final String INTENT_SDP = "com.netflix.mediaclient.intent.action.NOTIFICATION_SHOW_DETAILS";
    private static final String TAG = "DetailsActivityLauncher";
    
    public static Intent getEpisodeDetailsIntent(final Activity activity, final String s, final String s2, final PlayContext playContext) {
        return getEpisodeDetailsIntent(activity, s, s2, playContext, null, null);
    }
    
    public static Intent getEpisodeDetailsIntent(final Activity activity, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3) {
        final Intent putExtra = new Intent((Context)activity, (Class)ShowDetailsActivity.class).putExtra("extra_video_id", s).putExtra("extra_episode_id", s2).putExtra("extra_playcontext", (Parcelable)playContext);
        if (detailsActivity$Action != null) {
            putExtra.putExtra("extra_action", (Serializable)detailsActivity$Action);
        }
        if (s3 != null) {
            putExtra.putExtra("extra_action_token", s3);
        }
        putExtra.putExtra("extra_video_type", (Serializable)VideoType.SHOW);
        return putExtra;
    }
    
    public static Intent getIntent(final Context context, final Class<?> clazz, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3) {
        final Intent intent = new Intent(context, (Class)clazz);
        intent.putExtra("extra_video_id", s);
        intent.putExtra("extra_video_title", s2);
        intent.putExtra("extra_video_type", (Serializable)videoType);
        intent.putExtra("extra_playcontext", (Parcelable)playContext);
        if (detailsActivity$Action != null) {
            intent.putExtra("extra_action", (Serializable)detailsActivity$Action);
        }
        if (s3 != null) {
            intent.putExtra("extra_action_token", s3);
        }
        return intent;
    }
    
    public static Intent getIntentForBroadcastReceiver(final VideoType videoType, final String s, final String s2, final String s3, final PlayContext playContext, final String s4, final MessageData messageData) {
        String s5;
        if (videoType == VideoType.MOVIE) {
            s5 = "com.netflix.mediaclient.intent.action.NOTIFICATION_MOVIE_DETAILS";
        }
        else {
            s5 = "com.netflix.mediaclient.intent.action.NOTIFICATION_SHOW_DETAILS";
        }
        final Intent intent = new Intent(s5);
        intent.putExtra("extra_video_id", s2);
        intent.putExtra("extra_video_title", s3);
        intent.putExtra("extra_video_type", (Serializable)videoType);
        intent.putExtra("extra_playcontext", (Parcelable)playContext);
        intent.putExtra("g", s);
        if (s4 != null) {
            intent.putExtra("extra_action_token", s4);
        }
        MessageData.addMessageDataToIntent(intent, messageData);
        return intent;
    }
    
    public static void show(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        show(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext, null, null);
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext) {
        show(netflixActivity, videoType, s, s2, playContext, null, null);
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3) {
        Class<?> clazz = null;
        final boolean forKids = netflixActivity.isForKids();
        final boolean equals = VideoType.MOVIE.equals(videoType);
        if (forKids) {
            clazz = KidsDetailsActivity.class;
        }
        else if (equals) {
            if (KubrickUtils.shouldShowKubrickExperience(netflixActivity)) {
                clazz = KubrickMovieDetailsActivity.class;
            }
            else {
                clazz = MovieDetailsActivity.class;
            }
        }
        else if (VideoType.SHOW.equals(videoType)) {
            if (KubrickUtils.shouldShowKubrickExperience(netflixActivity)) {
                clazz = KubrickShowDetailsActivity.class;
            }
            else {
                clazz = ShowDetailsActivity.class;
            }
        }
        else if (videoType.isSocialVideoType()) {
            Log.w("DetailsActivityLauncher", "Asked to show details for a social video type - shouldn't happen");
        }
        else {
            netflixActivity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(new IllegalStateException(String.format("Don't know how to handle %s type: %s, playContext:%s", s, videoType, playContext)));
        }
        if (clazz != null) {
            netflixActivity.startActivity(getIntent((Context)netflixActivity, clazz, videoType, s, s2, playContext, detailsActivity$Action, s3));
        }
    }
    
    public static void showEpisodeDetails(final Activity activity, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3) {
        final Intent putExtra = new Intent((Context)activity, (Class)ShowDetailsActivity.class).putExtra("extra_video_id", s).putExtra("extra_episode_id", s2).putExtra("extra_playcontext", (Parcelable)playContext);
        if (detailsActivity$Action != null) {
            putExtra.putExtra("extra_action", (Serializable)detailsActivity$Action);
        }
        if (s3 != null) {
            putExtra.putExtra("extra_action_token", s3);
        }
        putExtra.putExtra("extra_video_type", (Serializable)VideoType.SHOW);
        activity.startActivity(putExtra);
    }
}
