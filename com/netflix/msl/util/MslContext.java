// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import com.netflix.msl.userauth.UserAuthenticationFactory;
import com.netflix.msl.userauth.UserAuthenticationScheme;
import com.netflix.msl.tokens.TokenFactory;
import java.util.Date;
import java.util.Random;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.msg.MessageCapabilities;
import com.netflix.msl.keyx.KeyExchangeScheme;
import com.netflix.msl.keyx.KeyExchangeFactory;
import java.util.SortedSet;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public abstract class MslContext
{
    private static final long MILLISECONDS_PER_SECOND = 1000L;
    private volatile long offset;
    private volatile boolean synced;
    
    public MslContext() {
        this.synced = false;
        this.offset = 0L;
    }
    
    public abstract EntityAuthenticationData getEntityAuthenticationData(final MslContext$ReauthCode p0);
    
    public abstract EntityAuthenticationFactory getEntityAuthenticationFactory(final EntityAuthenticationScheme p0);
    
    public abstract EntityAuthenticationScheme getEntityAuthenticationScheme(final String p0);
    
    public abstract SortedSet<KeyExchangeFactory> getKeyExchangeFactories();
    
    public abstract KeyExchangeFactory getKeyExchangeFactory(final KeyExchangeScheme p0);
    
    public abstract KeyExchangeScheme getKeyExchangeScheme(final String p0);
    
    public abstract MessageCapabilities getMessageCapabilities();
    
    public abstract ICryptoContext getMslCryptoContext();
    
    public abstract MslStore getMslStore();
    
    public abstract Random getRandom();
    
    public final Date getRemoteTime() {
        if (!this.synced) {
            return null;
        }
        return new Date((this.offset + this.getTime() / 1000L) * 1000L);
    }
    
    public abstract long getTime();
    
    public abstract TokenFactory getTokenFactory();
    
    public abstract UserAuthenticationFactory getUserAuthenticationFactory(final UserAuthenticationScheme p0);
    
    public abstract UserAuthenticationScheme getUserAuthenticationScheme(final String p0);
    
    public abstract boolean isPeerToPeer();
    
    public final void updateRemoteTime(final Date date) {
        this.offset = date.getTime() / 1000L - this.getTime() / 1000L;
        this.synced = true;
    }
}
