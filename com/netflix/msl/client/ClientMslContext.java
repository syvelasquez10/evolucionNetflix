// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import com.netflix.msl.userauth.UserAuthenticationFactory;
import com.netflix.msl.userauth.UserAuthenticationScheme;
import java.util.List;
import java.util.Set;
import com.netflix.msl.MslConstants$CompressionAlgorithm;
import java.util.HashSet;
import com.netflix.msl.msg.MessageCapabilities;
import java.util.Collection;
import java.util.TreeSet;
import java.util.SortedSet;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.tokens.ClientTokenFactory;
import com.netflix.msl.crypto.ClientMslCryptoContext;
import java.security.SecureRandom;
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
import com.netflix.msl.util.MslContext;

public class ClientMslContext extends MslContext
{
    private final ClientMslContext$ClockProvider clock;
    private final EntityAuthenticationData entityAuthData;
    private final Map<EntityAuthenticationScheme, EntityAuthenticationFactory> entityAuthFactories;
    private final Map<KeyExchangeScheme, KeyExchangeFactory> keyxFactories;
    private final ICryptoContext mslCryptoContext;
    private final MslStore mslStore;
    private final Random random;
    private final TokenFactory tokenFactory;
    
    public ClientMslContext(ClientMslContext$ClockProvider clock, Random random, ICryptoContext mslCryptoContext, TokenFactory tokenFactory, final EntityAuthenticationData entityAuthData, final Map<EntityAuthenticationScheme, EntityAuthenticationFactory> entityAuthFactories, final Map<KeyExchangeScheme, KeyExchangeFactory> keyxFactories, final MslStore mslStore) {
        if (clock == null) {
            clock = new ClientMslContext$SystemClockProvider();
        }
        this.clock = clock;
        if (random == null) {
            random = new SecureRandom();
        }
        this.random = random;
        if (mslCryptoContext == null) {
            mslCryptoContext = new ClientMslCryptoContext();
        }
        this.mslCryptoContext = mslCryptoContext;
        if (tokenFactory == null) {
            tokenFactory = new ClientTokenFactory();
        }
        this.tokenFactory = tokenFactory;
        this.entityAuthData = entityAuthData;
        this.entityAuthFactories = entityAuthFactories;
        this.keyxFactories = keyxFactories;
        this.mslStore = mslStore;
    }
    
    public static ClientMslContext$ClientMslContextBuilder builder() {
        return new ClientMslContext$ClientMslContextBuilder();
    }
    
    public EntityAuthenticationData getEntityAuthData() {
        return this.entityAuthData;
    }
    
    @Override
    public EntityAuthenticationData getEntityAuthenticationData(final MslContext$ReauthCode mslContext$ReauthCode) {
        return this.entityAuthData;
    }
    
    @Override
    public EntityAuthenticationFactory getEntityAuthenticationFactory(final EntityAuthenticationScheme entityAuthenticationScheme) {
        return this.entityAuthFactories.get(entityAuthenticationScheme);
    }
    
    @Override
    public EntityAuthenticationScheme getEntityAuthenticationScheme(final String s) {
        return EntityAuthenticationScheme.getScheme(s);
    }
    
    @Override
    public SortedSet<KeyExchangeFactory> getKeyExchangeFactories() {
        return new TreeSet<KeyExchangeFactory>(this.keyxFactories.values());
    }
    
    @Override
    public KeyExchangeFactory getKeyExchangeFactory(final KeyExchangeScheme keyExchangeScheme) {
        return this.keyxFactories.get(keyExchangeScheme);
    }
    
    @Override
    public KeyExchangeScheme getKeyExchangeScheme(final String s) {
        return KeyExchangeScheme.getScheme(s);
    }
    
    @Override
    public MessageCapabilities getMessageCapabilities() {
        final HashSet<MslConstants$CompressionAlgorithm> set = new HashSet<MslConstants$CompressionAlgorithm>();
        set.add(MslConstants$CompressionAlgorithm.GZIP);
        set.add(MslConstants$CompressionAlgorithm.LZW);
        return new MessageCapabilities(set, null);
    }
    
    @Override
    public ICryptoContext getMslCryptoContext() {
        return this.mslCryptoContext;
    }
    
    @Override
    public MslStore getMslStore() {
        return this.mslStore;
    }
    
    @Override
    public Random getRandom() {
        return this.random;
    }
    
    @Override
    public long getTime() {
        return this.clock.currentTimeMillis();
    }
    
    @Override
    public TokenFactory getTokenFactory() {
        return this.tokenFactory;
    }
    
    @Override
    public UserAuthenticationFactory getUserAuthenticationFactory(final UserAuthenticationScheme userAuthenticationScheme) {
        return null;
    }
    
    @Override
    public UserAuthenticationScheme getUserAuthenticationScheme(final String s) {
        return UserAuthenticationScheme.getScheme(s);
    }
    
    @Override
    public boolean isPeerToPeer() {
        return false;
    }
}
