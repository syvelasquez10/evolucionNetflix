// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp.response;

import com.netflix.mediaclient.nccp.NccpResponse;

public final class NccpTransactionHttpError implements NccpResponse
{
    private int responseErrorCode;
    private String transaction;
    
    public NccpTransactionHttpError(final int responseErrorCode, final String transaction) {
        this.responseErrorCode = responseErrorCode;
        this.transaction = transaction;
    }
    
    public int getResponseErrorCode() {
        return this.responseErrorCode;
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
