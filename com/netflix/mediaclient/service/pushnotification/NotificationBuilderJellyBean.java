// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import android.net.Uri;
import android.app.Notification$BigTextStyle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import android.app.Notification$BigPictureStyle;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.app.Notification$Builder;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
public class NotificationBuilderJellyBean extends NotificationBuilderHoneycomb
{
    private static void addBigPicture(final Context context, final Payload payload, final Notification$BigPictureStyle notification$BigPictureStyle, final int n, final ImageLoader imageLoader) {
        if (!StringUtils.isEmpty(payload.largeIcon) && imageLoader != null) {
            imageLoader.getImg(payload.bigViewPicture, IClientLogging$AssetType.merchStill, 0, 0, new NotificationBuilderJellyBean$2(payload, notification$BigPictureStyle, context, n));
            return;
        }
        Log.d("nf_push", "Large picture view was not set");
        NotificationBuilder.sendNotification(context, notification$BigPictureStyle.build(), n);
    }
    
    private static void addBigView(final Context context, final Payload payload, final Notification$Builder notification$Builder, final int n, final ImageLoader imageLoader) {
        if (!StringUtils.isEmpty(payload.bigViewPicture)) {
            final Notification$BigPictureStyle notification$BigPictureStyle = new Notification$BigPictureStyle(notification$Builder);
            if (!StringUtils.isEmpty(payload.bigViewSummary)) {
                notification$BigPictureStyle.setSummaryText((CharSequence)payload.bigViewSummary);
            }
            if (!StringUtils.isEmpty(payload.bigViewTitle)) {
                notification$BigPictureStyle.setBigContentTitle((CharSequence)payload.bigViewTitle);
            }
            addBigPicture(context, payload, notification$BigPictureStyle, n, imageLoader);
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
            NotificationBuilder.sendNotification(context, notification$BigTextStyle.build(), n);
            return;
        }
        NotificationBuilder.sendNotification(context, notification$Builder.build(), n);
    }
    
    public static void createNotification(final Context context, final Payload payload, final ImageLoader imageLoader, final int n) {
        final long when = payload.getWhen();
        final String title = payload.getTitle(context.getString(2131492992));
        final String ticker = payload.getTicker(title);
        final Notification$Builder notification$Builder = new Notification$Builder(context);
        notification$Builder.setContentIntent(NotificationBuilder.getNotificationOpenedIntent(context, payload));
        notification$Builder.setDeleteIntent(NotificationBuilder.getNotificationCanceledIntent(context, payload));
        notification$Builder.setTicker((CharSequence)ticker);
        notification$Builder.setAutoCancel(true);
        notification$Builder.setContentTitle((CharSequence)title);
        notification$Builder.setContentText((CharSequence)payload.text);
        notification$Builder.setSmallIcon(2130837812);
        notification$Builder.setWhen(when);
        while (true) {
            if (!StringUtils.isNotEmpty(payload.sound) || !NotificationBuilder.isSoundEnabled(context)) {
                break Label_0138;
            }
            try {
                notification$Builder.setSound(NotificationBuilder.getSound(payload.sound), 5);
                notification$Builder.setSubText((CharSequence)payload.subtext);
                final Payload$Action[] actions = payload.getActions();
                for (int length = actions.length, i = 0; i < length; ++i) {
                    final Payload$Action payload$Action = actions[i];
                    final Uri payload2 = payload$Action.getPayload();
                    if (payload2 != null) {
                        notification$Builder.addAction(payload$Action.getIcon(), (CharSequence)payload$Action.text, NotificationBuilder.getNotificationOpenedIntent(context, payload2, payload));
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
            imageLoader.getImg(payload.largeIcon, IClientLogging$AssetType.boxArt, 0, 0, new NotificationBuilderJellyBean$1(payload, notification$Builder, context, n, imageLoader));
            return;
        }
        Log.d("nf_push", "Icon was not set");
        addBigView(context, payload, notification$Builder, n, imageLoader);
    }
}
