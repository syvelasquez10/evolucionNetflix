// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.PendingIntent;
import android.app.Notification;
import android.os.Parcelable;
import android.os.Bundle;
import android.app.RemoteInput$Builder;
import android.app.RemoteInput;

class NotificationCompatApi21
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
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_MESSAGES = "messages";
    private static final String KEY_ON_READ = "on_read";
    private static final String KEY_ON_REPLY = "on_reply";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_REMOTE_INPUT = "remote_input";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TIMESTAMP = "timestamp";
    
    private static RemoteInput fromCompatRemoteInput(final RemoteInputCompatBase$RemoteInput remoteInputCompatBase$RemoteInput) {
        return new RemoteInput$Builder(remoteInputCompatBase$RemoteInput.getResultKey()).setLabel(remoteInputCompatBase$RemoteInput.getLabel()).setChoices(remoteInputCompatBase$RemoteInput.getChoices()).setAllowFreeFormInput(remoteInputCompatBase$RemoteInput.getAllowFreeFormInput()).addExtras(remoteInputCompatBase$RemoteInput.getExtras()).build();
    }
    
    static Bundle getBundleForUnreadConversation(final NotificationCompatBase$UnreadConversation notificationCompatBase$UnreadConversation) {
        final String s = null;
        int i = 0;
        if (notificationCompatBase$UnreadConversation == null) {
            return null;
        }
        final Bundle bundle = new Bundle();
        String s2 = s;
        if (notificationCompatBase$UnreadConversation.getParticipants() != null) {
            s2 = s;
            if (notificationCompatBase$UnreadConversation.getParticipants().length > 1) {
                s2 = notificationCompatBase$UnreadConversation.getParticipants()[0];
            }
        }
        Parcelable[] array;
        for (array = new Parcelable[notificationCompatBase$UnreadConversation.getMessages().length]; i < array.length; ++i) {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("text", notificationCompatBase$UnreadConversation.getMessages()[i]);
            bundle2.putString("author", s2);
            array[i] = (Parcelable)bundle2;
        }
        bundle.putParcelableArray("messages", array);
        final RemoteInputCompatBase$RemoteInput remoteInput = notificationCompatBase$UnreadConversation.getRemoteInput();
        if (remoteInput != null) {
            bundle.putParcelable("remote_input", (Parcelable)fromCompatRemoteInput(remoteInput));
        }
        bundle.putParcelable("on_reply", (Parcelable)notificationCompatBase$UnreadConversation.getReplyPendingIntent());
        bundle.putParcelable("on_read", (Parcelable)notificationCompatBase$UnreadConversation.getReadPendingIntent());
        bundle.putStringArray("participants", notificationCompatBase$UnreadConversation.getParticipants());
        bundle.putLong("timestamp", notificationCompatBase$UnreadConversation.getLatestTimestamp());
        return bundle;
    }
    
    public static String getCategory(final Notification notification) {
        return notification.category;
    }
    
    static NotificationCompatBase$UnreadConversation getUnreadConversationFromBundle(final Bundle bundle, final NotificationCompatBase$UnreadConversation$Factory notificationCompatBase$UnreadConversation$Factory, final RemoteInputCompatBase$RemoteInput$Factory remoteInputCompatBase$RemoteInput$Factory) {
        final boolean b = false;
        if (bundle != null) {
            final Parcelable[] parcelableArray = bundle.getParcelableArray("messages");
            String[] array = null;
            Label_0058: {
                if (parcelableArray != null) {
                    array = new String[parcelableArray.length];
                    int i = 0;
                    while (true) {
                        while (i < array.length) {
                            int n;
                            if (!(parcelableArray[i] instanceof Bundle)) {
                                n = (b ? 1 : 0);
                            }
                            else {
                                array[i] = ((Bundle)parcelableArray[i]).getString("text");
                                n = (b ? 1 : 0);
                                if (array[i] != null) {
                                    ++i;
                                    continue;
                                }
                            }
                            if (n != 0) {
                                break Label_0058;
                            }
                            return null;
                        }
                        int n = 1;
                        continue;
                    }
                }
                array = null;
            }
            final PendingIntent pendingIntent = (PendingIntent)bundle.getParcelable("on_read");
            final PendingIntent pendingIntent2 = (PendingIntent)bundle.getParcelable("on_reply");
            final RemoteInput remoteInput = (RemoteInput)bundle.getParcelable("remote_input");
            final String[] stringArray = bundle.getStringArray("participants");
            if (stringArray != null && stringArray.length == 1) {
                RemoteInputCompatBase$RemoteInput compatRemoteInput;
                if (remoteInput != null) {
                    compatRemoteInput = toCompatRemoteInput(remoteInput, remoteInputCompatBase$RemoteInput$Factory);
                }
                else {
                    compatRemoteInput = null;
                }
                return notificationCompatBase$UnreadConversation$Factory.build(array, compatRemoteInput, pendingIntent2, pendingIntent, stringArray, bundle.getLong("timestamp"));
            }
        }
        return null;
    }
    
    private static RemoteInputCompatBase$RemoteInput toCompatRemoteInput(final RemoteInput remoteInput, final RemoteInputCompatBase$RemoteInput$Factory remoteInputCompatBase$RemoteInput$Factory) {
        return remoteInputCompatBase$RemoteInput$Factory.build(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras());
    }
}
