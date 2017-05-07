// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp;

public class NccpError
{
    public String actionId;
    public String code;
    public String description;
    
    public NccpError(final String code, final String description, final String actionId) {
        this.code = code;
        this.description = description;
        this.actionId = actionId;
    }
    
    @Override
    public String toString() {
        return "NccpError [code=" + this.code + ", description=" + this.description + ", actionId=" + this.actionId + "]";
    }
}
