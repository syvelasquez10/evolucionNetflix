// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

public class PartnerRequest
{
    private int idx;
    private ResponseListener listener;
    private PartnerRequestType requestType;
    private String service;
    private String userId;
    
    public PartnerRequest(final PartnerRequestType requestType, final String service, final String userId, final int idx, final ResponseListener listener) {
        this.service = service;
        this.userId = userId;
        this.idx = idx;
        this.listener = listener;
        this.requestType = requestType;
    }
    
    public String getId() {
        return String.valueOf(this.idx);
    }
    
    public int getIdx() {
        return this.idx;
    }
    
    public ResponseListener getListener() {
        return this.listener;
    }
    
    public PartnerRequestType getRequestType() {
        return this.requestType;
    }
    
    public String getService() {
        return this.service;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    @Override
    public String toString() {
        return "PartnerRequest [service=" + this.service + ", userId=" + this.userId + ", idx=" + this.idx + ", listener=" + this.listener + ", requestType=" + this.requestType + "]";
    }
}
