// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.userauth.UserAuthenticationFactory;
import com.netflix.msl.userauth.UserAuthenticationScheme;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.tokens.TokenFactory;
import java.util.Random;
import com.netflix.msl.util.NullMslStore;
import com.netflix.msl.util.MslStore;
import com.netflix.msl.crypto.NullCryptoContext;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.keyx.KeyExchangeScheme;
import java.util.TreeSet;
import com.netflix.msl.keyx.KeyExchangeFactory;
import java.util.SortedSet;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import com.netflix.msl.entityauth.UnauthenticatedAuthenticationData;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.util.MslContext;

class MslControl$DummyMslContext extends MslContext
{
    @Override
    public EntityAuthenticationData getEntityAuthenticationData(final MslContext$ReauthCode mslContext$ReauthCode) {
        return new UnauthenticatedAuthenticationData("dummy");
    }
    
    @Override
    public EntityAuthenticationFactory getEntityAuthenticationFactory(final EntityAuthenticationScheme entityAuthenticationScheme) {
        return null;
    }
    
    @Override
    public EntityAuthenticationScheme getEntityAuthenticationScheme(final String s) {
        return EntityAuthenticationScheme.getScheme(s);
    }
    
    @Override
    public SortedSet<KeyExchangeFactory> getKeyExchangeFactories() {
        return new TreeSet<KeyExchangeFactory>();
    }
    
    @Override
    public KeyExchangeFactory getKeyExchangeFactory(final KeyExchangeScheme keyExchangeScheme) {
        return null;
    }
    
    @Override
    public KeyExchangeScheme getKeyExchangeScheme(final String s) {
        return KeyExchangeScheme.getScheme(s);
    }
    
    @Override
    public MessageCapabilities getMessageCapabilities() {
        return null;
    }
    
    @Override
    public ICryptoContext getMslCryptoContext() {
        return new NullCryptoContext();
    }
    
    @Override
    public MslStore getMslStore() {
        return new NullMslStore();
    }
    
    @Override
    public Random getRandom() {
        return new Random();
    }
    
    @Override
    public long getTime() {
        return System.currentTimeMillis();
    }
    
    @Override
    public TokenFactory getTokenFactory() {
        throw new MslInternalException("Dummy token factory should never actually get used.");
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
