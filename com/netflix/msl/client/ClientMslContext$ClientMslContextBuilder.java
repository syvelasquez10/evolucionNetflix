// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import com.netflix.msl.tokens.TokenFactory;
import java.util.Random;
import com.netflix.msl.util.MslStore;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.keyx.KeyExchangeFactory;
import com.netflix.msl.keyx.KeyExchangeScheme;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import java.util.Map;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class ClientMslContext$ClientMslContextBuilder
{
    private ClientMslContext$ClockProvider clock;
    private EntityAuthenticationData entityAuthData;
    private Map<EntityAuthenticationScheme, EntityAuthenticationFactory> entityAuthFactories;
    private Map<KeyExchangeScheme, KeyExchangeFactory> keyxFactories;
    private ICryptoContext mslCryptoContext;
    private MslStore mslStore;
    private Random random;
    private TokenFactory tokenFactory;
    
    public ClientMslContext build() {
        return new ClientMslContext(this.clock, this.random, this.mslCryptoContext, this.tokenFactory, this.entityAuthData, this.entityAuthFactories, this.keyxFactories, this.mslStore);
    }
    
    public ClientMslContext$ClientMslContextBuilder clock(final ClientMslContext$ClockProvider clock) {
        this.clock = clock;
        return this;
    }
    
    public ClientMslContext$ClientMslContextBuilder entityAuthData(final EntityAuthenticationData entityAuthData) {
        this.entityAuthData = entityAuthData;
        return this;
    }
    
    public ClientMslContext$ClientMslContextBuilder entityAuthFactories(final Map<EntityAuthenticationScheme, EntityAuthenticationFactory> entityAuthFactories) {
        this.entityAuthFactories = entityAuthFactories;
        return this;
    }
    
    public ClientMslContext$ClientMslContextBuilder keyxFactories(final Map<KeyExchangeScheme, KeyExchangeFactory> keyxFactories) {
        this.keyxFactories = keyxFactories;
        return this;
    }
    
    public ClientMslContext$ClientMslContextBuilder mslCryptoContext(final ICryptoContext mslCryptoContext) {
        this.mslCryptoContext = mslCryptoContext;
        return this;
    }
    
    public ClientMslContext$ClientMslContextBuilder mslStore(final MslStore mslStore) {
        this.mslStore = mslStore;
        return this;
    }
    
    public ClientMslContext$ClientMslContextBuilder random(final Random random) {
        this.random = random;
        return this;
    }
    
    @Override
    public String toString() {
        return "ClientMslContext.ClientMslContextBuilder(clock=" + this.clock + ", random=" + this.random + ", mslCryptoContext=" + this.mslCryptoContext + ", tokenFactory=" + this.tokenFactory + ", entityAuthData=" + this.entityAuthData + ", entityAuthFactories=" + this.entityAuthFactories + ", keyxFactories=" + this.keyxFactories + ", mslStore=" + this.mslStore + ")";
    }
    
    public ClientMslContext$ClientMslContextBuilder tokenFactory(final TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
        return this;
    }
}
