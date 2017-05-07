// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.Asset;
import android.app.Notification;
import android.text.TextUtils;
import android.app.Notification$Builder;
import android.support.v4.app.NotificationCompat$Builder;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.NotificationManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class PlayerSuspendNotification$1 extends SimpleManagerCallback
{
    final /* synthetic */ PlayerSuspendNotification this$0;
    
    PlayerSuspendNotification$1(final PlayerSuspendNotification this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.fetchImageWithLoader(episodeDetails.getHighResolutionPortraitBoxArtUrl());
        }
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.fetchImageWithLoader(movieDetails.getHighResolutionPortraitBoxArtUrl());
        }
    }
}
