// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

public final class MessageData
{
    private String mGuid;
    private String mMessageGuid;
    private String mOriginator;
    
    public MessageData(final String mGuid, final String mMessageGuid, final String mOriginator) {
        this.mGuid = mGuid;
        this.mMessageGuid = mMessageGuid;
        this.mOriginator = mOriginator;
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
