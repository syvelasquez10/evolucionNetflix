// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Iterator;
import java.util.ArrayList;
import android.os.Build$VERSION;

public class NotificationCompat
{
    private static final NotificationCompat$NotificationCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = new NotificationCompat$NotificationCompatImplApi21();
            return;
        }
        if (Build$VERSION.SDK_INT >= 20) {
            IMPL = new NotificationCompat$NotificationCompatImplApi20();
            return;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = new NotificationCompat$NotificationCompatImplKitKat();
            return;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = new NotificationCompat$NotificationCompatImplJellybean();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new NotificationCompat$NotificationCompatImplIceCreamSandwich();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = new NotificationCompat$NotificationCompatImplHoneycomb();
            return;
        }
        if (Build$VERSION.SDK_INT >= 9) {
            IMPL = new NotificationCompat$NotificationCompatImplGingerbread();
            return;
        }
        IMPL = new NotificationCompat$NotificationCompatImplBase();
    }
    
    private static void addActionsToBuilder(final NotificationBuilderWithActions notificationBuilderWithActions, final ArrayList<NotificationCompat$Action> list) {
        final Iterator<NotificationCompat$Action> iterator = list.iterator();
        while (iterator.hasNext()) {
            notificationBuilderWithActions.addAction(iterator.next());
        }
    }
    
    private static void addStyleToBuilderJellybean(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final NotificationCompat$Style notificationCompat$Style) {
        if (notificationCompat$Style != null) {
            if (notificationCompat$Style instanceof NotificationCompat$BigTextStyle) {
                final NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = (NotificationCompat$BigTextStyle)notificationCompat$Style;
                NotificationCompatJellybean.addBigTextStyle(notificationBuilderWithBuilderAccessor, notificationCompat$BigTextStyle.mBigContentTitle, notificationCompat$BigTextStyle.mSummaryTextSet, notificationCompat$BigTextStyle.mSummaryText, notificationCompat$BigTextStyle.mBigText);
            }
            else {
                if (notificationCompat$Style instanceof NotificationCompat$InboxStyle) {
                    final NotificationCompat$InboxStyle notificationCompat$InboxStyle = (NotificationCompat$InboxStyle)notificationCompat$Style;
                    NotificationCompatJellybean.addInboxStyle(notificationBuilderWithBuilderAccessor, notificationCompat$InboxStyle.mBigContentTitle, notificationCompat$InboxStyle.mSummaryTextSet, notificationCompat$InboxStyle.mSummaryText, notificationCompat$InboxStyle.mTexts);
                    return;
                }
                if (notificationCompat$Style instanceof NotificationCompat$BigPictureStyle) {
                    final NotificationCompat$BigPictureStyle notificationCompat$BigPictureStyle = (NotificationCompat$BigPictureStyle)notificationCompat$Style;
                    NotificationCompatJellybean.addBigPictureStyle(notificationBuilderWithBuilderAccessor, notificationCompat$BigPictureStyle.mBigContentTitle, notificationCompat$BigPictureStyle.mSummaryTextSet, notificationCompat$BigPictureStyle.mSummaryText, notificationCompat$BigPictureStyle.mPicture, notificationCompat$BigPictureStyle.mBigLargeIcon, notificationCompat$BigPictureStyle.mBigLargeIconSet);
                }
            }
        }
    }
}
