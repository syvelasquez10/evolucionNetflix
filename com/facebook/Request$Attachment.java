// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

class Request$Attachment
{
    private final Request request;
    private final Object value;
    
    public Request$Attachment(final Request request, final Object value) {
        this.request = request;
        this.value = value;
    }
    
    public Request getRequest() {
        return this.request;
    }
    
    public Object getValue() {
        return this.value;
    }
}
