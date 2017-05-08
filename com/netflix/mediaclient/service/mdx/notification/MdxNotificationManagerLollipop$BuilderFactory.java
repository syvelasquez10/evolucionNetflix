// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.util.Pair;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.service.NetflixService;
import android.app.PendingIntent;
import android.app.Notification$MediaStyle;
import android.app.NotificationManager;
import android.app.Notification;
import com.netflix.mediaclient.service.mdx.MediaSessionController;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.content.Context;
import android.graphics.Bitmap;
import android.annotation.TargetApi;
import android.annotation.SuppressLint;
import android.app.Notification$Style;
import com.netflix.mediaclient.Log;
import android.app.Notification$Builder;

class MdxNotificationManagerLollipop$BuilderFactory
{
    private static final String PAUSE = "Pause";
    private static final String PLAY = "Play";
    private static final String REWIND = "Rewind";
    private static final int SKIPBACK_SECONDS = -30;
    private static final String STOP = "Stop";
    final /* synthetic */ MdxNotificationManagerLollipop this$0;
    
    private MdxNotificationManagerLollipop$BuilderFactory(final MdxNotificationManagerLollipop this$0) {
        this.this$0 = this$0;
    }
    
    @SuppressLint({ "InlinedApi" })
    private Notification$Builder createPlayerBuilder() {
        Log.i("nf_mdxnotification", "createPlayerBuilder");
        return new Notification$Builder(this.this$0.context).setOngoing(true).setVisibility(1).setOnlyAlertOnce(true).setShowWhen(false).setSmallIcon(2130837935).setStyle((Notification$Style)this.this$0.getStyle()).addAction(2130838061, (CharSequence)"Rewind", this.this$0.mdxAgent.getSkipbackIntent(-30)).addAction(2130838065, (CharSequence)"Pause", this.this$0.mdxAgent.getResumeIntent()).addAction(2130838067, (CharSequence)"Stop", this.this$0.mdxAgent.getStopIntent());
    }
    
    @SuppressLint({ "InlinedApi" })
    private Notification$Builder createPlayerPausedBuilder() {
        Log.i("nf_mdxnotification", "createPlayerPausedBuilder");
        return new Notification$Builder(this.this$0.context).setOngoing(true).setVisibility(1).setOnlyAlertOnce(true).setShowWhen(false).setSmallIcon(2130837935).setStyle((Notification$Style)this.this$0.getStyle()).addAction(2130838061, (CharSequence)"Rewind", this.this$0.mdxAgent.getSkipbackIntent(-30)).addAction(2130838063, (CharSequence)"Play", this.this$0.mdxAgent.getPauseIntent()).addAction(2130838067, (CharSequence)"Stop", this.this$0.mdxAgent.getStopIntent());
    }
    
    @SuppressLint({ "InlinedApi" })
    private Notification$Builder createPostPlayerBuilder() {
        return new Notification$Builder(this.this$0.context).setOngoing(true).setVisibility(1).setShowWhen(false).setOnlyAlertOnce(true).setSmallIcon(2130837935).setStyle((Notification$Style)this.this$0.getStyle()).addAction(2130838065, (CharSequence)"Play", this.this$0.mdxAgent.getPlayNextIntent()).addAction(2130838067, (CharSequence)"Stop", this.this$0.mdxAgent.getStopIntent());
    }
    
    Notification$Builder getBuilder(final boolean b, final boolean b2) {
        if (b) {
            return this.createPostPlayerBuilder();
        }
        if (b2) {
            return this.createPlayerBuilder();
        }
        return this.createPlayerPausedBuilder();
    }
}
