// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.util.Set;
import java.util.Iterator;
import com.netflix.msl.MslMessageException;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import com.netflix.msl.MslConstants$CompressionAlgorithm;
import com.netflix.msl.keyx.KeyExchangeFactory$KeyExchangeData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.crypto.ICryptoContext;
import java.util.Map;

public class MessageServiceTokenBuilder
{
    private final MessageBuilder builder;
    private final Map<String, ICryptoContext> cryptoContexts;
    private final MslContext ctx;
    
    public MessageServiceTokenBuilder(final MslContext ctx, final MessageContext messageContext, final MessageBuilder builder) {
        this.ctx = ctx;
        this.cryptoContexts = messageContext.getCryptoContexts();
        this.builder = builder;
    }
    
    private MasterToken getPrimaryMasterToken() {
        final KeyExchangeFactory$KeyExchangeData keyExchangeData = this.builder.getKeyExchangeData();
        if (keyExchangeData != null && !this.ctx.isPeerToPeer()) {
            return keyExchangeData.keyResponseData.getMasterToken();
        }
        return this.builder.getMasterToken();
    }
    
    private static ICryptoContext selectCryptoContext(final String s, final Map<String, ICryptoContext> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        return map.get("");
    }
    
    public boolean addMasterBoundPeerServiceToken(final String s, final byte[] array, final boolean b, final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm) {
        final MasterToken peerMasterToken = this.builder.getPeerMasterToken();
        if (peerMasterToken != null) {
            final ICryptoContext selectCryptoContext = selectCryptoContext(s, this.cryptoContexts);
            if (selectCryptoContext != null) {
                final ServiceToken serviceToken = new ServiceToken(this.ctx, s, array, peerMasterToken, null, b, mslConstants$CompressionAlgorithm, selectCryptoContext);
                try {
                    this.builder.addPeerServiceToken(serviceToken);
                    return true;
                }
                catch (MslMessageException ex) {
                    throw new MslInternalException("Service token bound to incorrect authentication tokens despite setting correct master token.", ex);
                }
            }
        }
        return false;
    }
    
    public boolean addMasterBoundPrimaryServiceToken(final String s, final byte[] array, final boolean b, final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm) {
        final MasterToken primaryMasterToken = this.getPrimaryMasterToken();
        if (primaryMasterToken != null) {
            final ICryptoContext selectCryptoContext = selectCryptoContext(s, this.cryptoContexts);
            if (selectCryptoContext != null) {
                final ServiceToken serviceToken = new ServiceToken(this.ctx, s, array, primaryMasterToken, null, b, mslConstants$CompressionAlgorithm, selectCryptoContext);
                try {
                    this.builder.addServiceToken(serviceToken);
                    return true;
                }
                catch (MslMessageException ex) {
                    throw new MslInternalException("Service token bound to incorrect authentication tokens despite setting correct master token.", ex);
                }
            }
        }
        return false;
    }
    
    public boolean addPeerServiceToken(final ServiceToken serviceToken) {
        try {
            this.builder.addPeerServiceToken(serviceToken);
            return true;
        }
        catch (MslMessageException ex) {
            return false;
        }
    }
    
    public boolean addPrimaryServiceToken(final ServiceToken serviceToken) {
        try {
            this.builder.addServiceToken(serviceToken);
            return true;
        }
        catch (MslMessageException ex) {
            return false;
        }
    }
    
    public boolean addUnboundPeerServiceToken(final String s, final byte[] array, final boolean b, final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm) {
        final ICryptoContext selectCryptoContext = selectCryptoContext(s, this.cryptoContexts);
        if (selectCryptoContext == null) {
            return false;
        }
        final ServiceToken serviceToken = new ServiceToken(this.ctx, s, array, null, null, b, mslConstants$CompressionAlgorithm, selectCryptoContext);
        try {
            this.builder.addPeerServiceToken(serviceToken);
            return true;
        }
        catch (MslMessageException ex) {
            throw new MslInternalException("Service token bound to incorrect authentication tokens despite being unbound.", ex);
        }
    }
    
    public boolean addUnboundPrimaryServiceToken(final String s, final byte[] array, final boolean b, final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm) {
        final ICryptoContext selectCryptoContext = selectCryptoContext(s, this.cryptoContexts);
        if (selectCryptoContext == null) {
            return false;
        }
        final ServiceToken serviceToken = new ServiceToken(this.ctx, s, array, null, null, b, mslConstants$CompressionAlgorithm, selectCryptoContext);
        try {
            this.builder.addServiceToken(serviceToken);
            return true;
        }
        catch (MslMessageException ex) {
            throw new MslInternalException("Service token bound to incorrect authentication tokens despite being unbound.", ex);
        }
    }
    
    public boolean addUserBoundPeerServiceToken(final String s, final byte[] array, final boolean b, final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm) {
        final MasterToken peerMasterToken = this.builder.getPeerMasterToken();
        if (peerMasterToken != null) {
            final UserIdToken peerUserIdToken = this.builder.getPeerUserIdToken();
            if (peerUserIdToken != null) {
                final ICryptoContext selectCryptoContext = selectCryptoContext(s, this.cryptoContexts);
                if (selectCryptoContext != null) {
                    final ServiceToken serviceToken = new ServiceToken(this.ctx, s, array, peerMasterToken, peerUserIdToken, b, mslConstants$CompressionAlgorithm, selectCryptoContext);
                    try {
                        this.builder.addPeerServiceToken(serviceToken);
                        return true;
                    }
                    catch (MslMessageException ex) {
                        throw new MslInternalException("Service token bound to incorrect authentication tokens despite setting correct master token and user ID token.", ex);
                    }
                }
            }
        }
        return false;
    }
    
    public boolean addUserBoundPrimaryServiceToken(final String s, final byte[] array, final boolean b, final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm) {
        final MasterToken primaryMasterToken = this.getPrimaryMasterToken();
        if (primaryMasterToken != null) {
            final UserIdToken userIdToken = this.builder.getUserIdToken();
            if (userIdToken != null) {
                final ICryptoContext selectCryptoContext = selectCryptoContext(s, this.cryptoContexts);
                if (selectCryptoContext != null) {
                    final ServiceToken serviceToken = new ServiceToken(this.ctx, s, array, primaryMasterToken, userIdToken, b, mslConstants$CompressionAlgorithm, selectCryptoContext);
                    try {
                        this.builder.addServiceToken(serviceToken);
                        return true;
                    }
                    catch (MslMessageException ex) {
                        throw new MslInternalException("Service token bound to incorrect authentication tokens despite setting correct master token and user ID token.", ex);
                    }
                }
            }
        }
        return false;
    }
    
    public boolean deletePeerServiceToken(final String s) {
        final Iterator<ServiceToken> iterator = this.builder.getPeerServiceTokens().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(s)) {
                this.builder.deletePeerServiceToken(s);
                return true;
            }
        }
        return false;
    }
    
    public boolean deletePrimaryServiceToken(final String s) {
        final Iterator<ServiceToken> iterator = this.builder.getServiceTokens().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(s)) {
                this.builder.deleteServiceToken(s);
                return true;
            }
        }
        return false;
    }
    
    public boolean excludePeerServiceToken(final String s) {
        final Iterator<ServiceToken> iterator = this.builder.getPeerServiceTokens().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(s)) {
                this.builder.excludePeerServiceToken(s);
                return true;
            }
        }
        return false;
    }
    
    public boolean excludePrimaryServiceToken(final String s) {
        final Iterator<ServiceToken> iterator = this.builder.getServiceTokens().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(s)) {
                this.builder.excludeServiceToken(s);
                return true;
            }
        }
        return false;
    }
    
    public Set<ServiceToken> getPeerServiceTokens() {
        return this.builder.getPeerServiceTokens();
    }
    
    public Set<ServiceToken> getPrimaryServiceTokens() {
        return this.builder.getServiceTokens();
    }
    
    public boolean isPeerMasterTokenAvailable() {
        return this.builder.getPeerMasterToken() != null;
    }
    
    public boolean isPeerUserIdTokenAvailable() {
        return this.builder.getPeerUserIdToken() != null;
    }
    
    public boolean isPrimaryMasterTokenAvailable() {
        return this.getPrimaryMasterToken() != null;
    }
    
    public boolean isPrimaryUserIdTokenAvailable() {
        return this.builder.getUserIdToken() != null;
    }
}
