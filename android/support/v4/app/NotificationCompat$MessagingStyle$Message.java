// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.ArrayList;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.List;
import android.net.Uri;

public final class NotificationCompat$MessagingStyle$Message
{
    static final String KEY_DATA_MIME_TYPE = "type";
    static final String KEY_DATA_URI = "uri";
    static final String KEY_SENDER = "sender";
    static final String KEY_TEXT = "text";
    static final String KEY_TIMESTAMP = "time";
    private String mDataMimeType;
    private Uri mDataUri;
    private final CharSequence mSender;
    private final CharSequence mText;
    private final long mTimestamp;
    
    public NotificationCompat$MessagingStyle$Message(final CharSequence mText, final long mTimestamp, final CharSequence mSender) {
        this.mText = mText;
        this.mTimestamp = mTimestamp;
        this.mSender = mSender;
    }
    
    static Bundle[] getBundleArrayForMessages(final List<NotificationCompat$MessagingStyle$Message> list) {
        final Bundle[] array = new Bundle[list.size()];
        for (int size = list.size(), i = 0; i < size; ++i) {
            array[i] = list.get(i).toBundle();
        }
        return array;
    }
    
    static NotificationCompat$MessagingStyle$Message getMessageFromBundle(final Bundle bundle) {
        try {
            if (bundle.containsKey("text")) {
                if (bundle.containsKey("time")) {
                    final NotificationCompat$MessagingStyle$Message notificationCompat$MessagingStyle$Message = new NotificationCompat$MessagingStyle$Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
                    if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                        notificationCompat$MessagingStyle$Message.setData(bundle.getString("type"), (Uri)bundle.getParcelable("uri"));
                    }
                    return notificationCompat$MessagingStyle$Message;
                }
            }
        }
        catch (ClassCastException ex) {
            return null;
        }
        return null;
    }
    
    static List<NotificationCompat$MessagingStyle$Message> getMessagesFromBundleArray(final Parcelable[] array) {
        final ArrayList<NotificationCompat$MessagingStyle$Message> list = new ArrayList<NotificationCompat$MessagingStyle$Message>(array.length);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof Bundle) {
                final NotificationCompat$MessagingStyle$Message messageFromBundle = getMessageFromBundle((Bundle)array[i]);
                if (messageFromBundle != null) {
                    list.add(messageFromBundle);
                }
            }
        }
        return list;
    }
    
    private Bundle toBundle() {
        final Bundle bundle = new Bundle();
        if (this.mText != null) {
            bundle.putCharSequence("text", this.mText);
        }
        bundle.putLong("time", this.mTimestamp);
        if (this.mSender != null) {
            bundle.putCharSequence("sender", this.mSender);
        }
        if (this.mDataMimeType != null) {
            bundle.putString("type", this.mDataMimeType);
        }
        if (this.mDataUri != null) {
            bundle.putParcelable("uri", (Parcelable)this.mDataUri);
        }
        return bundle;
    }
    
    public String getDataMimeType() {
        return this.mDataMimeType;
    }
    
    public Uri getDataUri() {
        return this.mDataUri;
    }
    
    public CharSequence getSender() {
        return this.mSender;
    }
    
    public CharSequence getText() {
        return this.mText;
    }
    
    public long getTimestamp() {
        return this.mTimestamp;
    }
    
    public NotificationCompat$MessagingStyle$Message setData(final String mDataMimeType, final Uri mDataUri) {
        this.mDataMimeType = mDataMimeType;
        this.mDataUri = mDataUri;
        return this;
    }
}
