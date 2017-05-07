// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Parcelable;
import java.util.Iterator;
import android.app.Notification;
import android.os.Bundle;
import java.util.ArrayList;
import android.os.Build$VERSION;

public class NotificationCompat
{
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    private static final NotificationCompat$NotificationCompatImpl IMPL;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;
    
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
    
    public static NotificationCompat$Action getAction(final Notification notification, final int n) {
        return NotificationCompat.IMPL.getAction(notification, n);
    }
    
    public static int getActionCount(final Notification notification) {
        return NotificationCompat.IMPL.getActionCount(notification);
    }
    
    public static String getCategory(final Notification notification) {
        return NotificationCompat.IMPL.getCategory(notification);
    }
    
    public static Bundle getExtras(final Notification notification) {
        return NotificationCompat.IMPL.getExtras(notification);
    }
    
    public static String getGroup(final Notification notification) {
        return NotificationCompat.IMPL.getGroup(notification);
    }
    
    public static boolean getLocalOnly(final Notification notification) {
        return NotificationCompat.IMPL.getLocalOnly(notification);
    }
    
    private static Notification[] getNotificationArrayFromBundle(final Bundle bundle, final String s) {
        final Parcelable[] parcelableArray = bundle.getParcelableArray(s);
        if (parcelableArray instanceof Notification[] || parcelableArray == null) {
            return (Notification[])parcelableArray;
        }
        final Notification[] array = new Notification[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; ++i) {
            array[i] = (Notification)parcelableArray[i];
        }
        bundle.putParcelableArray(s, (Parcelable[])array);
        return array;
    }
    
    public static String getSortKey(final Notification notification) {
        return NotificationCompat.IMPL.getSortKey(notification);
    }
    
    public static boolean isGroupSummary(final Notification notification) {
        return NotificationCompat.IMPL.isGroupSummary(notification);
    }
}
