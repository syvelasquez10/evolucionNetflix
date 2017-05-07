// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import java.io.Serializable;
import android.os.Parcelable;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class DetailsActivityLauncher
{
    static final String EXTRA_ACTION = "extra_action";
    static final String EXTRA_ACTION_TOKEN = "extra_action_token";
    static final String EXTRA_EPISODE_ID = "extra_episode_id";
    public static final String EXTRA_PLAY_CONTEXT = "extra_playcontext";
    public static final String EXTRA_SAME_ACTIVITY_TYPE = "extra_same_activity_type";
    public static final String EXTRA_VIDEO_ID = "extra_video_id";
    public static final String EXTRA_VIDEO_TITLE = "extra_video_title";
    public static final String EXTRA_VIDEO_TYPE = "extra_video_type";
    public static final String INTENT_MDP = "com.netflix.mediaclient.intent.action.NOTIFICATION_MOVIE_DETAILS";
    public static final String INTENT_SDP = "com.netflix.mediaclient.intent.action.NOTIFICATION_SHOW_DETAILS";
    private static final String TAG = "DetailsActivityLauncher";
    
    public static Intent getEpisodeDetailsIntent(final NetflixActivity netflixActivity, final String s, final String s2, final PlayContext playContext) {
        return getEpisodeDetailsIntent(netflixActivity, s, s2, playContext, null, null);
    }
    
    private static Intent getEpisodeDetailsIntent(final NetflixActivity netflixActivity, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3) {
        final Class<? extends DetailsActivity> detailsClassTypeForVideo = BrowseExperience.get().getDetailsClassTypeForVideo(VideoType.SHOW);
        if (detailsClassTypeForVideo == null) {
            logInvalidVideoType(netflixActivity, BrowseExperience.get(), s, s2, VideoType.SHOW, playContext, "getEpisodeDetailsIntent");
            return null;
        }
        final Intent putExtra = new Intent((Context)netflixActivity, (Class)detailsClassTypeForVideo).putExtra("extra_video_id", s).putExtra("extra_episode_id", s2).putExtra("extra_playcontext", (Parcelable)playContext);
        if (detailsActivity$Action != null) {
            putExtra.putExtra("extra_action", (Serializable)detailsActivity$Action);
        }
        if (s3 != null) {
            putExtra.putExtra("extra_action_token", s3);
        }
        putExtra.putExtra("extra_video_type", (Serializable)VideoType.SHOW);
        return putExtra;
    }
    
    private static Intent getIntent(final Context context, final Class<?> clazz, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3) {
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
        if (clazz.getName().contains("etails") && context.getClass().getName().contains("etails")) {
            intent.putExtra("extra_same_activity_type", true);
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
    
    private static void logInvalidVideoType(final NetflixActivity netflixActivity, final BrowseExperience browseExperience, final String s, final String s2, final VideoType videoType, final PlayContext playContext, final String s3) {
        Object value;
        if (playContext == null) {
            value = null;
        }
        else {
            value = playContext.getTrackId();
        }
        final String format = String.format("DetailsActivityLauncher - Don't know how to handle parent ID: %s, ep ID: %s, type: %s, trackId: %s, source: %s, experience: %s", s, s2, videoType, value, s3, browseExperience);
        Log.w("DetailsActivityLauncher", format);
        netflixActivity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(format);
    }
    
    public static void show(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext, final String s) {
        show(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext, null, null, s, null, 0);
    }
    
    public static void show(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext, final String s, final int n) {
        show(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext, null, null, s, null, n);
    }
    
    public static void show(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext, final String s, final Bundle bundle) {
        show(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext, null, null, s, bundle, 0);
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3, final String s4) {
        show(netflixActivity, videoType, s, s2, playContext, detailsActivity$Action, s3, s4, null, 0);
    }
    
    private static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3, final String s4, final Bundle bundle, final int n) {
        final BrowseExperience value = BrowseExperience.get();
        final Class<? extends DetailsActivity> detailsClassTypeForVideo = value.getDetailsClassTypeForVideo(videoType);
        if (detailsClassTypeForVideo == null) {
            logInvalidVideoType(netflixActivity, value, s, null, videoType, playContext, s4);
            return;
        }
        if (Log.isLoggable()) {
            Log.v("DetailsActivityLauncher", String.format("Creating details class: %s, videoType: %s, video ID: %s", detailsClassTypeForVideo.getSimpleName(), videoType, s));
        }
        final Intent intent = getIntent((Context)netflixActivity, detailsClassTypeForVideo, videoType, s, s2, playContext, detailsActivity$Action, s3);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (n > 0) {
            intent.addFlags(n);
        }
        netflixActivity.startActivity(intent);
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final String s3) {
        show(netflixActivity, videoType, s, s2, playContext, null, null, s3, null, 0);
    }
    
    public static void showEpisodeDetails(final NetflixActivity netflixActivity, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3) {
        final Intent episodeDetailsIntent = getEpisodeDetailsIntent(netflixActivity, s, s2, playContext, detailsActivity$Action, s3);
        if (episodeDetailsIntent == null) {
            Log.w("DetailsActivityLauncher", "Can't start activity - intent is null");
            return;
        }
        netflixActivity.startActivity(episodeDetailsIntent);
    }
}
