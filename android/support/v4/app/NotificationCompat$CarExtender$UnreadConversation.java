// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.PendingIntent;

public class NotificationCompat$CarExtender$UnreadConversation extends NotificationCompatBase$UnreadConversation
{
    static final NotificationCompatBase$UnreadConversation$Factory FACTORY;
    private final long mLatestTimestamp;
    private final String[] mMessages;
    private final String[] mParticipants;
    private final PendingIntent mReadPendingIntent;
    private final RemoteInput mRemoteInput;
    private final PendingIntent mReplyPendingIntent;
    
    static {
        FACTORY = new NotificationCompat$CarExtender$UnreadConversation$1();
    }
    
    NotificationCompat$CarExtender$UnreadConversation(final String[] mMessages, final RemoteInput mRemoteInput, final PendingIntent mReplyPendingIntent, final PendingIntent mReadPendingIntent, final String[] mParticipants, final long mLatestTimestamp) {
        this.mMessages = mMessages;
        this.mRemoteInput = mRemoteInput;
        this.mReadPendingIntent = mReadPendingIntent;
        this.mReplyPendingIntent = mReplyPendingIntent;
        this.mParticipants = mParticipants;
        this.mLatestTimestamp = mLatestTimestamp;
    }
    
    @Override
    long getLatestTimestamp() {
        return this.mLatestTimestamp;
    }
    
    @Override
    String[] getMessages() {
        return this.mMessages;
    }
    
    @Override
    String getParticipant() {
        if (this.mParticipants.length > 0) {
            return this.mParticipants[0];
        }
        return null;
    }
    
    @Override
    String[] getParticipants() {
        return this.mParticipants;
    }
    
    @Override
    PendingIntent getReadPendingIntent() {
        return this.mReadPendingIntent;
    }
    
    @Override
    RemoteInput getRemoteInput() {
        return this.mRemoteInput;
    }
    
    @Override
    PendingIntent getReplyPendingIntent() {
        return this.mReplyPendingIntent;
    }
}
