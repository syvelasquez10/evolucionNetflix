// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.kubrick_kids.details.KubrickKidsDetailsActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickMovieDetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import java.io.Serializable;
import android.os.Parcelable;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.app.Activity;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Set;

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
    private static final Set<VideoType> VALID_KUBRICK_KIDS_TYPES;
    private static final Set<VideoType> VALID_KUBRICK_TYPES;
    private static final Set<VideoType> VALID_NON_KUBRICK_TYPES;
    
    static {
        VALID_NON_KUBRICK_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        VALID_KUBRICK_TYPES = DetailsActivityLauncher.VALID_NON_KUBRICK_TYPES;
        VALID_KUBRICK_KIDS_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW, VideoType.CHARACTERS));
    }
    
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
    
    private static void logInvalidVideoType(final NetflixActivity netflixActivity, final BrowseExperience browseExperience, final String s, final VideoType videoType, final PlayContext playContext, final String s2) {
        Object value;
        if (playContext == null) {
            value = null;
        }
        else {
            value = playContext.getTrackId();
        }
        final String format = String.format("DetailsActivityLauncher - Don't know how to handle video ID %s, type: %s, trackId: %s, source: %s, experience: %s", s, videoType, value, s2, browseExperience);
        Log.w("DetailsActivityLauncher", format);
        netflixActivity.getServiceManager().getClientLogging().getErrorLogging().logHandledException(format);
    }
    
    public static void show(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext, final String s) {
        show(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext, null, null, s);
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final DetailsActivity$Action detailsActivity$Action, final String s3, final String s4) {
        final boolean equals = VideoType.MOVIE.equals(videoType);
        final BrowseExperience value = BrowseExperience.get();
        while (true) {
            Label_0197: {
                Serializable s5 = null;
                switch (DetailsActivityLauncher$1.$SwitchMap$com$netflix$mediaclient$ui$experience$BrowseExperience[value.ordinal()]) {
                    default: {
                        if (!DetailsActivityLauncher.VALID_NON_KUBRICK_TYPES.contains(videoType)) {
                            break Label_0197;
                        }
                        if (equals) {
                            s5 = MovieDetailsActivity.class;
                            break;
                        }
                        s5 = ShowDetailsActivity.class;
                        break;
                    }
                    case 1: {
                        if (DetailsActivityLauncher.VALID_KUBRICK_TYPES.contains(videoType)) {
                            if (equals) {
                                s5 = KubrickMovieDetailsActivity.class;
                            }
                            else {
                                s5 = KubrickShowDetailsActivity.class;
                            }
                            break;
                        }
                        break Label_0197;
                    }
                    case 2: {
                        if (DetailsActivityLauncher.VALID_KUBRICK_KIDS_TYPES.contains(videoType)) {
                            s5 = KubrickKidsDetailsActivity.class;
                            break;
                        }
                        break Label_0197;
                    }
                }
                if (s5 == null) {
                    logInvalidVideoType(netflixActivity, value, s, videoType, playContext, s4);
                    return;
                }
                if (Log.isLoggable()) {
                    Log.v("DetailsActivityLauncher", String.format("Creating details class: %s, videoType: %s, video ID: %s", ((Class)s5).getSimpleName(), videoType, s));
                }
                netflixActivity.startActivity(getIntent((Context)netflixActivity, (Class<?>)s5, videoType, s, s2, playContext, detailsActivity$Action, s3));
                return;
            }
            Serializable s5 = null;
            continue;
        }
    }
    
    public static void show(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final PlayContext playContext, final String s3) {
        show(netflixActivity, videoType, s, s2, playContext, null, null, s3);
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
