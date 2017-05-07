// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp.response;

import com.netflix.mediaclient.nccp.NccpResponse;

public final class NccpTransactionApplicationError implements NccpResponse
{
    private Throwable cause;
    private String name;
    
    public NccpTransactionApplicationError(final Throwable cause, final String name) {
        this.cause = cause;
        this.name = name;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
    
    @Override
    public boolean getStatus() {
        return false;
    }
    
    @Override
    public String getTransaction() {
        return this.name;
    }
}
