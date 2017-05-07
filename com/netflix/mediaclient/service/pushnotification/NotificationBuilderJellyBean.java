// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import android.net.Uri;
import android.app.Notification$BigTextStyle;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.StringUtils;
import android.app.Notification$BigPictureStyle;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.app.Notification$Builder;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
public class NotificationBuilderJellyBean extends NotificationBuilderHoneycomb
{
    private static void addBigPicture(final Context context, final Payload payload, final Notification$BigPictureStyle notification$BigPictureStyle, final int n, final ImageLoader imageLoader, final ErrorLogging errorLogging) {
        if (!StringUtils.isEmpty(payload.largeIcon) && imageLoader != null) {
            imageLoader.getImg(payload.bigViewPicture, IClientLogging.AssetType.merchStill, 0, 0, (ImageLoader.ImageLoaderListener)new ImageLoader.ImageLoaderListener() {
                @Override
                public void onErrorResponse(final String s) {
                    if (Log.isLoggable("nf_push", 6)) {
                        Log.e("nf_push", "Failed to downlod " + payload.largeIcon + ". Reason: " + s);
                    }
                    NotificationBuilder.sendNotification(context, notification$BigPictureStyle.build(), n, errorLogging);
                }
                
                @Override
                public void onResponse(final Bitmap bitmap, final String s) {
                    if (Log.isLoggable("nf_push", 3)) {
                        Log.d("nf_push", "Image is downloaded " + payload.bigViewPicture + " from " + s);
                    }
                    if (bitmap != null) {
                        notification$BigPictureStyle.bigPicture(bitmap);
                    }
                    Log.d("nf_push", "Large icon image set!");
                    NotificationBuilder.sendNotification(context, notification$BigPictureStyle.build(), n, errorLogging);
                }
            });
            return;
        }
        Log.d("nf_push", "Large picture view was not set");
        NotificationBuilder.sendNotification(context, notification$BigPictureStyle.build(), n, errorLogging);
    }
    
    private static void addBigView(final Context context, final Payload payload, final Notification$Builder notification$Builder, final int n, final ImageLoader imageLoader, final ErrorLogging errorLogging) {
        if (!StringUtils.isEmpty(payload.bigViewPicture)) {
            final Notification$BigPictureStyle notification$BigPictureStyle = new Notification$BigPictureStyle(notification$Builder);
            if (!StringUtils.isEmpty(payload.bigViewSummary)) {
                notification$BigPictureStyle.setSummaryText((CharSequence)payload.bigViewSummary);
            }
            if (!StringUtils.isEmpty(payload.bigViewTitle)) {
                notification$BigPictureStyle.setBigContentTitle((CharSequence)payload.bigViewTitle);
            }
            addBigPicture(context, payload, notification$BigPictureStyle, n, imageLoader, errorLogging);
            return;
        }
        if (!StringUtils.isEmpty(payload.bigViewText)) {
            final Notification$BigTextStyle notification$BigTextStyle = new Notification$BigTextStyle(notification$Builder);
            notification$BigTextStyle.bigText((CharSequence)payload.bigViewText);
            if (!StringUtils.isEmpty(payload.bigViewSummary)) {
                notification$BigTextStyle.setSummaryText((CharSequence)payload.bigViewSummary);
            }
            if (!StringUtils.isEmpty(payload.bigViewTitle)) {
                notification$BigTextStyle.setBigContentTitle((CharSequence)payload.bigViewTitle);
            }
            NotificationBuilder.sendNotification(context, notification$BigTextStyle.build(), n, errorLogging);
            return;
        }
        NotificationBuilder.sendNotification(context, notification$Builder.build(), n, errorLogging);
    }
    
    public static void createNotification(final Context context, final Payload payload, final ImageLoader imageLoader, final int n, final ErrorLogging errorLogging) {
        final long when = payload.getWhen();
        final String title = payload.getTitle(context.getString(2131492964));
        final String ticker = payload.getTicker(title);
        final Notification$Builder notification$Builder = new Notification$Builder(context);
        notification$Builder.setContentIntent(NotificationBuilder.getNotificationOpenedIntent(context, payload));
        notification$Builder.setDeleteIntent(NotificationBuilder.getNotificationCanceledIntent(context, payload));
        notification$Builder.setTicker((CharSequence)ticker);
        notification$Builder.setAutoCancel(true);
        notification$Builder.setContentTitle((CharSequence)title);
        notification$Builder.setContentText((CharSequence)payload.text);
        notification$Builder.setSmallIcon(2130837801);
        notification$Builder.setWhen(when);
        while (true) {
            if (!StringUtils.isNotEmpty(payload.sound) || !NotificationBuilder.isSoundEnabled(context)) {
                break Label_0138;
            }
            try {
                notification$Builder.setSound(NotificationBuilder.getSound(payload.sound), 5);
                notification$Builder.setSubText((CharSequence)payload.subtext);
                final Payload.Action[] actions = payload.getActions();
                for (int length = actions.length, i = 0; i < length; ++i) {
                    final Payload.Action action = actions[i];
                    final Uri payload2 = action.getPayload();
                    if (payload2 != null) {
                        notification$Builder.addAction(action.getIcon(), (CharSequence)action.text, NotificationBuilder.getNotificationOpenedIntent(context, payload2, payload));
                    }
                }
            }
            catch (Throwable t) {
                Log.e("nf_push", "Failed to get notification sound URL!", t);
                continue;
            }
            break;
        }
        if (!StringUtils.isEmpty(payload.largeIcon) && imageLoader != null) {
            imageLoader.getImg(payload.largeIcon, IClientLogging.AssetType.boxArt, 0, 0, (ImageLoader.ImageLoaderListener)new ImageLoader.ImageLoaderListener() {
                @Override
                public void onErrorResponse(final String s) {
                    if (Log.isLoggable("nf_push", 6)) {
                        Log.e("nf_push", "Failed to downlod " + payload.largeIcon + ". Reason: " + s);
                    }
                    addBigView(context, payload, notification$Builder, n, imageLoader, errorLogging);
                }
                
                @Override
                public void onResponse(final Bitmap largeIcon, final String s) {
                    if (Log.isLoggable("nf_push", 3)) {
                        Log.d("nf_push", "Image is downloaded " + payload.largeIcon + " from " + s);
                    }
                    if (largeIcon != null) {
                        notification$Builder.setLargeIcon(largeIcon);
                    }
                    addBigView(context, payload, notification$Builder, n, imageLoader, errorLogging);
                    Log.d("nf_push", "Large icon image set!");
                }
            });
            return;
        }
        Log.d("nf_push", "Icon was not set");
        addBigView(context, payload, notification$Builder, n, imageLoader, errorLogging);
    }
}
