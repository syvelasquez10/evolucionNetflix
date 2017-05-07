// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp.response;

import com.netflix.mediaclient.nccp.NccpResponse;

public final class EmptyReponse implements NccpResponse
{
    private String transaction;
    
    public EmptyReponse(final String transaction) {
        this.transaction = transaction;
    }
    
    @Override
    public boolean getStatus() {
        return false;
    }
    
    @Override
    public String getTransaction() {
        return this.transaction;
    }
}
