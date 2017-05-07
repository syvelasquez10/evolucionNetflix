// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;

public final class MessageData
{
    private static final String TAG = "nf_push";
    private String mGuid;
    private String mMessageGuid;
    private String mOriginator;
    
    private MessageData(final String mGuid, final String mMessageGuid, final String mOriginator) {
        this.mGuid = mGuid;
        this.mMessageGuid = mMessageGuid;
        this.mOriginator = mOriginator;
    }
    
    public static void addMessageDataToIntent(final Intent intent, final MessageData messageData) {
        if (intent == null || messageData == null) {
            Log.w("nf_push", "Intent or msg is null!");
        }
        else {
            if (Log.isLoggable()) {
                Log.v("nf_push", "Add msg data to intent: " + messageData);
            }
            if (StringUtils.isNotEmpty(messageData.getGuid())) {
                if (Log.isLoggable()) {
                    Log.v("nf_push", "Add GUID to intent: " + messageData.getGuid());
                }
                intent.putExtra("guid", messageData.getGuid());
            }
            if (StringUtils.isNotEmpty(messageData.getMessageGuid())) {
                intent.putExtra("messageGuid", messageData.getMessageGuid());
            }
            if (StringUtils.isNotEmpty(messageData.getOriginator())) {
                intent.putExtra("originator", messageData.getOriginator());
            }
        }
    }
    
    public static MessageData createInstance(final Intent intent) {
        Log.d("nf_push", "MessageData::", intent);
        final String stringExtra = intent.getStringExtra("guid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.w("nf_push", "Guid not found, source is not (proper) push notification");
        }
        final String stringExtra2 = intent.getStringExtra("messageGuid");
        if (StringUtils.isEmpty(stringExtra2)) {
            Log.d("nf_push", "Message guid not found, source is not (proper) push notification");
        }
        final String stringExtra3 = intent.getStringExtra("originator");
        if (StringUtils.isEmpty(stringExtra3)) {
            Log.w("nf_push", "Received notification WITHOUT ORIGINATOR! Pass default!");
        }
        return new MessageData(stringExtra, stringExtra2, stringExtra3);
    }
    
    public String getGuid() {
        return this.mGuid;
    }
    
    public String getMessageGuid() {
        return this.mMessageGuid;
    }
    
    public String getOriginator() {
        return this.mOriginator;
    }
    
    @Override
    public String toString() {
        return "MessageData [mGuid=" + this.mGuid + ", mMessageGuid=" + this.mMessageGuid + ", mOriginator=" + this.mOriginator + "]";
    }
}
