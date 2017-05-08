// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Parcelable;
import android.os.Bundle;
import android.app.Notification;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$MessagingStyle extends NotificationCompat$Style
{
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    CharSequence mConversationTitle;
    List<NotificationCompat$MessagingStyle$Message> mMessages;
    CharSequence mUserDisplayName;
    
    NotificationCompat$MessagingStyle() {
        this.mMessages = new ArrayList<NotificationCompat$MessagingStyle$Message>();
    }
    
    public NotificationCompat$MessagingStyle(final CharSequence mUserDisplayName) {
        this.mMessages = new ArrayList<NotificationCompat$MessagingStyle$Message>();
        this.mUserDisplayName = mUserDisplayName;
    }
    
    public static NotificationCompat$MessagingStyle extractMessagingStyleFromNotification(final Notification notification) {
        final Bundle extras = NotificationCompat.IMPL.getExtras(notification);
        if (!extras.containsKey("android.selfDisplayName")) {
            return null;
        }
        try {
            final NotificationCompat$MessagingStyle notificationCompat$MessagingStyle = new NotificationCompat$MessagingStyle();
            notificationCompat$MessagingStyle.restoreFromCompatExtras(extras);
            return notificationCompat$MessagingStyle;
        }
        catch (ClassCastException ex) {
            return null;
        }
    }
    
    @Override
    public void addCompatExtras(final Bundle bundle) {
        super.addCompatExtras(bundle);
        if (this.mUserDisplayName != null) {
            bundle.putCharSequence("android.selfDisplayName", this.mUserDisplayName);
        }
        if (this.mConversationTitle != null) {
            bundle.putCharSequence("android.conversationTitle", this.mConversationTitle);
        }
        if (!this.mMessages.isEmpty()) {
            bundle.putParcelableArray("android.messages", (Parcelable[])NotificationCompat$MessagingStyle$Message.getBundleArrayForMessages(this.mMessages));
        }
    }
    
    public NotificationCompat$MessagingStyle addMessage(final NotificationCompat$MessagingStyle$Message notificationCompat$MessagingStyle$Message) {
        this.mMessages.add(notificationCompat$MessagingStyle$Message);
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }
    
    public NotificationCompat$MessagingStyle addMessage(final CharSequence charSequence, final long n, final CharSequence charSequence2) {
        this.mMessages.add(new NotificationCompat$MessagingStyle$Message(charSequence, n, charSequence2));
        if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
        }
        return this;
    }
    
    public CharSequence getConversationTitle() {
        return this.mConversationTitle;
    }
    
    public List<NotificationCompat$MessagingStyle$Message> getMessages() {
        return this.mMessages;
    }
    
    public CharSequence getUserDisplayName() {
        return this.mUserDisplayName;
    }
    
    @Override
    protected void restoreFromCompatExtras(final Bundle bundle) {
        this.mMessages.clear();
        this.mUserDisplayName = bundle.getString("android.selfDisplayName");
        this.mConversationTitle = bundle.getString("android.conversationTitle");
        final Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
        if (parcelableArray != null) {
            this.mMessages = NotificationCompat$MessagingStyle$Message.getMessagesFromBundleArray(parcelableArray);
        }
    }
    
    public NotificationCompat$MessagingStyle setConversationTitle(final CharSequence mConversationTitle) {
        this.mConversationTitle = mConversationTitle;
        return this;
    }
}
