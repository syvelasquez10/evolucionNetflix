// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

public final class MessageData
{
    private String mGuid;
    private String mMessageGuid;
    
    public MessageData(final String mGuid, final String mMessageGuid) {
        this.mGuid = mGuid;
        this.mMessageGuid = mMessageGuid;
    }
    
    public String getGuid() {
        return this.mGuid;
    }
    
    public String getMessageGuid() {
        return this.mMessageGuid;
    }
    
    @Override
    public String toString() {
        return "MessageData [mGuid=" + this.mGuid + ", mMessageGuid=" + this.mMessageGuid + "]";
    }
}
