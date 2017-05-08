// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import com.netflix.msl.crypto.ICryptoContext;
import java.util.Map;

class MslControl$FilterMessageContext implements MessageContext
{
    protected final MessageContext appCtx;
    
    protected MslControl$FilterMessageContext(final MessageContext appCtx) {
        this.appCtx = appCtx;
    }
    
    @Override
    public Map<String, ICryptoContext> getCryptoContexts() {
        return this.appCtx.getCryptoContexts();
    }
    
    @Override
    public MessageDebugContext getDebugContext() {
        return this.appCtx.getDebugContext();
    }
    
    @Override
    public Set<KeyRequestData> getKeyRequestData() {
        return this.appCtx.getKeyRequestData();
    }
    
    @Override
    public String getRecipient() {
        return this.appCtx.getRecipient();
    }
    
    @Override
    public MslUser getUser() {
        return this.appCtx.getUser();
    }
    
    @Override
    public UserAuthenticationData getUserAuthData(final MessageContext$ReauthCode messageContext$ReauthCode, final boolean b, final boolean b2) {
        return this.appCtx.getUserAuthData(messageContext$ReauthCode, b, b2);
    }
    
    @Override
    public String getUserId() {
        return this.appCtx.getUserId();
    }
    
    @Override
    public boolean isEncrypted() {
        return this.appCtx.isEncrypted();
    }
    
    @Override
    public boolean isIntegrityProtected() {
        return this.appCtx.isIntegrityProtected();
    }
    
    @Override
    public boolean isNonReplayable() {
        return this.appCtx.isNonReplayable();
    }
    
    @Override
    public boolean isRequestingTokens() {
        return this.appCtx.isRequestingTokens();
    }
    
    @Override
    public void updateServiceTokens(final MessageServiceTokenBuilder messageServiceTokenBuilder, final boolean b) {
        this.appCtx.updateServiceTokens(messageServiceTokenBuilder, b);
    }
    
    @Override
    public void write(final MessageOutputStream messageOutputStream) {
        this.appCtx.write(messageOutputStream);
    }
}
