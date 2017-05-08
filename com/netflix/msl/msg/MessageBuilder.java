// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import java.util.Collections;
import java.util.Collection;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.MslConstants$CompressionAlgorithm;
import com.netflix.msl.crypto.NullCryptoContext;
import com.netflix.msl.MslMessageException;
import java.util.Arrays;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.keyx.KeyExchangeFactory;
import com.netflix.msl.userauth.UserAuthenticationFactory;
import com.netflix.msl.userauth.UserAuthenticationScheme;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.keyx.KeyResponseData;
import com.netflix.msl.MslException;
import com.netflix.msl.MslUserAuthException;
import java.util.Date;
import java.util.Random;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.MslError;
import java.util.Iterator;
import com.netflix.msl.MslInternalException;
import java.util.HashMap;
import java.util.HashSet;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import java.util.Map;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import com.netflix.msl.keyx.KeyExchangeFactory$KeyExchangeData;
import com.netflix.msl.util.MslContext;

public class MessageBuilder
{
    private static final byte[] EMPTY_DATA;
    private final MessageCapabilities capabilities;
    private final MslContext ctx;
    private boolean handshake;
    private final KeyExchangeFactory$KeyExchangeData keyExchangeData;
    private final Set<KeyRequestData> keyRequestData;
    private MasterToken masterToken;
    private final long messageId;
    private boolean nonReplayable;
    private MasterToken peerMasterToken;
    private final Map<String, ServiceToken> peerServiceTokens;
    private UserIdToken peerUserIdToken;
    private final String recipient;
    private boolean renewable;
    private final Map<String, ServiceToken> serviceTokens;
    private UserAuthenticationData userAuthData;
    private UserIdToken userIdToken;
    
    static {
        EMPTY_DATA = new byte[0];
    }
    
    private MessageBuilder(final MslContext ctx, final String recipient, final long messageId, final MessageCapabilities capabilities, final MasterToken masterToken, final UserIdToken userIdToken, final Set<ServiceToken> set, final MasterToken peerMasterToken, final UserIdToken peerUserIdToken, final Set<ServiceToken> set2, final KeyExchangeFactory$KeyExchangeData keyExchangeData) {
        this.nonReplayable = false;
        this.renewable = false;
        this.handshake = false;
        this.keyRequestData = new HashSet<KeyRequestData>();
        this.userAuthData = null;
        this.userIdToken = null;
        this.serviceTokens = new HashMap<String, ServiceToken>();
        this.peerMasterToken = null;
        this.peerUserIdToken = null;
        this.peerServiceTokens = new HashMap<String, ServiceToken>();
        if (!ctx.isPeerToPeer() && (peerMasterToken != null || peerUserIdToken != null)) {
            throw new MslInternalException("Cannot set peer master token or peer user ID token when not in peer-to-peer mode.");
        }
        this.ctx = ctx;
        this.recipient = recipient;
        this.messageId = messageId;
        this.capabilities = capabilities;
        this.masterToken = masterToken;
        this.userIdToken = userIdToken;
        this.keyExchangeData = keyExchangeData;
        MasterToken masterToken2 = masterToken;
        if (keyExchangeData != null) {
            masterToken2 = masterToken;
            if (!ctx.isPeerToPeer()) {
                masterToken2 = keyExchangeData.keyResponseData.getMasterToken();
            }
        }
        for (final ServiceToken serviceToken : ctx.getMslStore().getServiceTokens(masterToken2, userIdToken)) {
            this.serviceTokens.put(serviceToken.getName(), serviceToken);
        }
        if (set != null) {
            for (final ServiceToken serviceToken2 : set) {
                this.serviceTokens.put(serviceToken2.getName(), serviceToken2);
            }
        }
        if (ctx.isPeerToPeer()) {
            this.peerMasterToken = peerMasterToken;
            this.peerUserIdToken = peerUserIdToken;
            MasterToken masterToken3;
            if (keyExchangeData != null) {
                masterToken3 = keyExchangeData.keyResponseData.getMasterToken();
            }
            else {
                masterToken3 = this.peerMasterToken;
            }
            for (final ServiceToken serviceToken3 : ctx.getMslStore().getServiceTokens(masterToken3, peerUserIdToken)) {
                this.peerServiceTokens.put(serviceToken3.getName(), serviceToken3);
            }
            if (set2 != null) {
                for (final ServiceToken serviceToken4 : set2) {
                    this.peerServiceTokens.put(serviceToken4.getName(), serviceToken4);
                }
            }
        }
    }
    
    public static ErrorHeader createErrorResponse(final MslContext mslContext, final String s, final Long n, final MslError mslError, final String s2) {
        final EntityAuthenticationData entityAuthenticationData = mslContext.getEntityAuthenticationData(null);
        long n2;
        if (n != null) {
            n2 = incrementMessageId(n);
        }
        else {
            final Random random = mslContext.getRandom();
            do {
                n2 = random.nextLong();
            } while (n2 < 0L || n2 > 9007199254740992L);
        }
        return new ErrorHeader(mslContext, entityAuthenticationData, s, n2, mslError.getResponseCode(), mslError.getInternalCode(), mslError.getMessage(), s2);
    }
    
    public static MessageBuilder createRequest(final MslContext mslContext, final MasterToken masterToken, final UserIdToken userIdToken, final String s) {
        final Random random = mslContext.getRandom();
        long nextLong;
        do {
            nextLong = random.nextLong();
        } while (nextLong < 0L || nextLong > 9007199254740992L);
        return new MessageBuilder(mslContext, s, nextLong, mslContext.getMessageCapabilities(), masterToken, userIdToken, null, null, null, null, null);
    }
    
    public static MessageBuilder createRequest(final MslContext mslContext, final MasterToken masterToken, final UserIdToken userIdToken, final String s, final long n) {
        if (n < 0L || n > 9007199254740992L) {
            throw new MslInternalException("Message ID " + n + " is outside the valid range.");
        }
        return new MessageBuilder(mslContext, s, n, mslContext.getMessageCapabilities(), masterToken, userIdToken, null, null, null, null, null);
    }
    
    public static MessageBuilder createResponse(final MslContext mslContext, final MessageHeader messageHeader) {
        final MasterToken masterToken = messageHeader.getMasterToken();
        final EntityAuthenticationData entityAuthenticationData = messageHeader.getEntityAuthenticationData();
        final UserIdToken userIdToken = messageHeader.getUserIdToken();
        final UserAuthenticationData userAuthenticationData = messageHeader.getUserAuthenticationData();
        String s = null;
        long incrementMessageId = 0L;
        KeyExchangeFactory$KeyExchangeData keyExchangeFactory$KeyExchangeData = null;
        UserIdToken userIdToken3 = null;
        MessageCapabilities intersection = null;
        KeyResponseData keyResponseData = null;
        Set<ServiceToken> serviceTokens = null;
        Label_0342: {
            if (masterToken == null) {
                break Label_0342;
            }
            s = masterToken.getIdentity();
            long messageId;
            UserIdToken userIdToken2;
            Set<KeyRequestData> keyRequestData;
            MasterToken masterToken2 = null;
            MasterToken masterToken3;
            MslUser mslUser = null;
            UserAuthenticationScheme scheme;
            UserAuthenticationFactory userAuthenticationFactory = null;
            Label_0128_Outer:Label_0147_Outer:
            while (true) {
                messageId = messageHeader.getMessageId();
                incrementMessageId = incrementMessageId(messageId);
                userIdToken2 = userIdToken;
                while (true) {
                    Label_0641:Label_0303_Outer:
                    while (true) {
                        Label_0635: {
                            while (true) {
                            Label_0570:
                                while (true) {
                                    Label_0547: {
                                        Label_0527: {
                                            try {
                                                keyRequestData = messageHeader.getKeyRequestData();
                                                userIdToken2 = userIdToken;
                                                if (!messageHeader.isRenewable()) {
                                                    break Label_0635;
                                                }
                                                userIdToken2 = userIdToken;
                                                if (keyRequestData.isEmpty()) {
                                                    break Label_0635;
                                                }
                                                if (masterToken != null) {
                                                    userIdToken2 = userIdToken;
                                                    if (!masterToken.isRenewable(null)) {
                                                        userIdToken2 = userIdToken;
                                                        if (!masterToken.isExpired(null)) {
                                                            keyExchangeFactory$KeyExchangeData = null;
                                                            break Label_0128;
                                                        }
                                                    }
                                                    userIdToken2 = userIdToken;
                                                    keyExchangeFactory$KeyExchangeData = issueMasterToken(mslContext, keyRequestData, masterToken, null);
                                                }
                                                else {
                                                    userIdToken2 = userIdToken;
                                                    keyExchangeFactory$KeyExchangeData = issueMasterToken(mslContext, keyRequestData, null, entityAuthenticationData);
                                                }
                                                if (keyExchangeFactory$KeyExchangeData == null) {
                                                    break Label_0641;
                                                }
                                                userIdToken2 = userIdToken;
                                                masterToken2 = keyExchangeFactory$KeyExchangeData.keyResponseData.getMasterToken();
                                                Label_0376: {
                                                    if (userIdToken == null) {
                                                        break Label_0376;
                                                    }
                                                    userIdToken2 = userIdToken;
                                                    if (!userIdToken.isVerified()) {
                                                        break Label_0376;
                                                    }
                                                    userIdToken2 = userIdToken;
                                                    Label_0219: {
                                                        if (userIdToken.isRenewable(null)) {
                                                            userIdToken2 = userIdToken;
                                                            if (messageHeader.isRenewable()) {
                                                                break Label_0219;
                                                            }
                                                        }
                                                        userIdToken2 = userIdToken;
                                                        if (!userIdToken.isExpired(null)) {
                                                            userIdToken3 = userIdToken;
                                                            userIdToken2 = userIdToken;
                                                            if (userIdToken.isBoundTo(masterToken2)) {
                                                                break Label_0239;
                                                            }
                                                        }
                                                    }
                                                    userIdToken2 = userIdToken;
                                                    userIdToken3 = mslContext.getTokenFactory().renewUserIdToken(mslContext, userIdToken, masterToken2);
                                                    userIdToken2 = userIdToken3;
                                                    intersection = MessageCapabilities.intersection(messageHeader.getMessageCapabilities(), mslContext.getMessageCapabilities());
                                                    userIdToken2 = userIdToken3;
                                                    keyResponseData = messageHeader.getKeyResponseData();
                                                    userIdToken2 = userIdToken3;
                                                    serviceTokens = messageHeader.getServiceTokens();
                                                    userIdToken2 = userIdToken3;
                                                    if (!mslContext.isPeerToPeer()) {
                                                        break;
                                                    }
                                                    if (keyResponseData != null) {
                                                        userIdToken2 = userIdToken3;
                                                        masterToken3 = keyResponseData.getMasterToken();
                                                        userIdToken2 = userIdToken3;
                                                        return new MessageBuilder(mslContext, s, incrementMessageId, intersection, masterToken3, messageHeader.getPeerUserIdToken(), messageHeader.getPeerServiceTokens(), masterToken, userIdToken3, serviceTokens, keyExchangeFactory$KeyExchangeData);
                                                    }
                                                    break Label_0570;
                                                }
                                                userIdToken3 = userIdToken;
                                                userIdToken2 = userIdToken;
                                                if (!messageHeader.isRenewable()) {
                                                    continue Label_0303_Outer;
                                                }
                                                userIdToken3 = userIdToken;
                                                if (masterToken2 == null) {
                                                    continue Label_0303_Outer;
                                                }
                                                userIdToken3 = userIdToken;
                                                if (userAuthenticationData == null) {
                                                    continue Label_0303_Outer;
                                                }
                                                userIdToken2 = userIdToken;
                                                if ((mslUser = messageHeader.getUser()) != null) {
                                                    break Label_0547;
                                                }
                                                userIdToken2 = userIdToken;
                                                scheme = userAuthenticationData.getScheme();
                                                userIdToken2 = userIdToken;
                                                userAuthenticationFactory = mslContext.getUserAuthenticationFactory(scheme);
                                                if (userAuthenticationFactory == null) {
                                                    userIdToken2 = userIdToken;
                                                    throw new MslUserAuthException(MslError.USERAUTH_FACTORY_NOT_FOUND, scheme.name()).setMasterToken(masterToken).setUserAuthenticationData(userAuthenticationData).setMessageId(messageId);
                                                }
                                                break Label_0527;
                                                s = entityAuthenticationData.getIdentity();
                                                continue Label_0128_Outer;
                                            }
                                            catch (MslException ex) {
                                                ex.setMasterToken(masterToken);
                                                ex.setEntityAuthenticationData(entityAuthenticationData);
                                                ex.setUserIdToken(userIdToken2);
                                                ex.setUserAuthenticationData(userAuthenticationData);
                                                ex.setMessageId(messageId);
                                                throw ex;
                                            }
                                        }
                                        mslUser = userAuthenticationFactory.authenticate(mslContext, masterToken2.getIdentity(), userAuthenticationData, null);
                                    }
                                    userIdToken2 = userIdToken;
                                    userIdToken3 = mslContext.getTokenFactory().createUserIdToken(mslContext, mslUser, masterToken2);
                                    continue Label_0303_Outer;
                                }
                                userIdToken2 = userIdToken3;
                                masterToken3 = messageHeader.getPeerMasterToken();
                                continue;
                            }
                        }
                        keyExchangeFactory$KeyExchangeData = null;
                        continue Label_0147_Outer;
                    }
                    masterToken2 = masterToken;
                    continue;
                }
            }
        }
        MasterToken masterToken4;
        if (keyResponseData != null) {
            masterToken4 = keyResponseData.getMasterToken();
        }
        else {
            masterToken4 = masterToken;
        }
        return new MessageBuilder(mslContext, s, incrementMessageId, intersection, masterToken4, userIdToken3, serviceTokens, null, null, null, keyExchangeFactory$KeyExchangeData);
    }
    
    public static long decrementMessageId(final long n) {
        if (n < 0L || n > 9007199254740992L) {
            throw new MslInternalException("Message ID " + n + " is outside the valid range.");
        }
        if (n == 0L) {
            return 9007199254740992L;
        }
        return n - 1L;
    }
    
    public static long incrementMessageId(final long n) {
        if (n < 0L || n > 9007199254740992L) {
            throw new MslInternalException("Message ID " + n + " is outside the valid range.");
        }
        if (n == 9007199254740992L) {
            return 0L;
        }
        return 1L + n;
    }
    
    private static KeyExchangeFactory$KeyExchangeData issueMasterToken(final MslContext mslContext, final Set<KeyRequestData> set, final MasterToken masterToken, final EntityAuthenticationData entityAuthenticationData) {
        MslException ex = null;
        final Iterator<Object> iterator = mslContext.getKeyExchangeFactories().iterator();
        while (iterator.hasNext()) {
            final KeyExchangeFactory keyExchangeFactory = iterator.next();
            final Iterator<KeyRequestData> iterator2 = set.iterator();
            MslException ex2 = ex;
            while (true) {
                ex = ex2;
                if (!iterator2.hasNext()) {
                    break;
                }
                final KeyRequestData keyRequestData = iterator2.next();
                if (!keyExchangeFactory.getScheme().equals(keyRequestData.getKeyExchangeScheme())) {
                    continue;
                }
                Label_0104: {
                    if (masterToken == null) {
                        break Label_0104;
                    }
                    try {
                        return keyExchangeFactory.generateResponse(mslContext, keyRequestData, masterToken);
                        return keyExchangeFactory.generateResponse(mslContext, keyRequestData, entityAuthenticationData);
                    }
                    catch (MslCryptoException ex3) {
                        ex2 = ex3;
                        if (!iterator.hasNext()) {
                            throw ex3;
                        }
                        continue;
                    }
                    catch (MslKeyExchangeException ex4) {
                        ex2 = ex4;
                        if (!iterator.hasNext()) {
                            throw ex4;
                        }
                        continue;
                    }
                    catch (MslEncodingException ex5) {
                        ex2 = ex5;
                        if (!iterator.hasNext()) {
                            throw ex5;
                        }
                        continue;
                    }
                    catch (MslMasterTokenException ex6) {
                        ex2 = ex6;
                        if (!iterator.hasNext()) {
                            throw ex6;
                        }
                        continue;
                    }
                    catch (MslEntityAuthException ex7) {
                        ex2 = ex7;
                        if (!iterator.hasNext()) {
                            throw ex7;
                        }
                        continue;
                    }
                }
            }
        }
        if (ex == null) {
            throw new MslKeyExchangeException(MslError.KEYX_FACTORY_NOT_FOUND, Arrays.toString(set.toArray()));
        }
        if (ex instanceof MslCryptoException) {
            throw (MslCryptoException)ex;
        }
        if (ex instanceof MslKeyExchangeException) {
            throw (MslKeyExchangeException)ex;
        }
        if (ex instanceof MslEncodingException) {
            throw (MslEncodingException)ex;
        }
        if (ex instanceof MslMasterTokenException) {
            throw (MslMasterTokenException)ex;
        }
        if (ex instanceof MslEntityAuthException) {
            throw (MslEntityAuthException)ex;
        }
        throw new MslInternalException("Unexpected exception caught during key exchange.", ex);
    }
    
    public MessageBuilder addKeyRequestData(final KeyRequestData keyRequestData) {
        this.keyRequestData.add(keyRequestData);
        return this;
    }
    
    public MessageBuilder addPeerServiceToken(final ServiceToken serviceToken) {
        if (!this.ctx.isPeerToPeer()) {
            throw new MslInternalException("Cannot set peer service tokens when not in peer-to-peer mode.");
        }
        if (serviceToken.isMasterTokenBound() && !serviceToken.isBoundTo(this.peerMasterToken)) {
            throw new MslMessageException(MslError.SERVICETOKEN_MASTERTOKEN_MISMATCH, "st " + serviceToken + "; mt " + this.peerMasterToken).setMasterToken(this.peerMasterToken);
        }
        if (serviceToken.isUserIdTokenBound() && !serviceToken.isBoundTo(this.peerUserIdToken)) {
            throw new MslMessageException(MslError.SERVICETOKEN_USERIDTOKEN_MISMATCH, "st " + serviceToken + "; uit " + this.peerUserIdToken).setMasterToken(this.peerMasterToken).setUserIdToken(this.peerUserIdToken);
        }
        this.peerServiceTokens.put(serviceToken.getName(), serviceToken);
        return this;
    }
    
    public MessageBuilder addPeerServiceTokenIfAbsent(final ServiceToken serviceToken) {
        if (!this.peerServiceTokens.containsKey(serviceToken.getName())) {
            this.addPeerServiceToken(serviceToken);
        }
        return this;
    }
    
    public MessageBuilder addServiceToken(final ServiceToken serviceToken) {
        MasterToken masterToken;
        if (this.keyExchangeData != null && !this.ctx.isPeerToPeer()) {
            masterToken = this.keyExchangeData.keyResponseData.getMasterToken();
        }
        else {
            masterToken = this.masterToken;
        }
        if (serviceToken.isMasterTokenBound() && !serviceToken.isBoundTo(masterToken)) {
            throw new MslMessageException(MslError.SERVICETOKEN_MASTERTOKEN_MISMATCH, "st " + serviceToken + "; mt " + masterToken).setMasterToken(masterToken);
        }
        if (serviceToken.isUserIdTokenBound() && !serviceToken.isBoundTo(this.userIdToken)) {
            throw new MslMessageException(MslError.SERVICETOKEN_USERIDTOKEN_MISMATCH, "st " + serviceToken + "; uit " + this.userIdToken).setMasterToken(masterToken).setUserIdToken(this.userIdToken);
        }
        this.serviceTokens.put(serviceToken.getName(), serviceToken);
        return this;
    }
    
    public MessageBuilder addServiceTokenIfAbsent(final ServiceToken serviceToken) {
        if (!this.serviceTokens.containsKey(serviceToken.getName())) {
            this.addServiceToken(serviceToken);
        }
        return this;
    }
    
    public MessageBuilder deletePeerServiceToken(final String s) {
        final ServiceToken serviceToken = this.peerServiceTokens.get(s);
        if (serviceToken == null) {
            return this;
        }
        Label_0077: {
            if (!serviceToken.isMasterTokenBound()) {
                break Label_0077;
            }
            MasterToken peerMasterToken = this.peerMasterToken;
            while (true) {
                Label_0082: {
                    if (!serviceToken.isUserIdTokenBound()) {
                        break Label_0082;
                    }
                    UserIdToken peerUserIdToken = this.peerUserIdToken;
                    try {
                        return this.addPeerServiceToken(new ServiceToken(this.ctx, s, MessageBuilder.EMPTY_DATA, peerMasterToken, peerUserIdToken, false, null, new NullCryptoContext()));
                        peerUserIdToken = null;
                        return this.addPeerServiceToken(new ServiceToken(this.ctx, s, MessageBuilder.EMPTY_DATA, peerMasterToken, peerUserIdToken, false, null, new NullCryptoContext()));
                        peerMasterToken = null;
                        continue;
                    }
                    catch (MslException ex) {
                        throw new MslInternalException("Failed to create and add empty peer service token to message.", ex);
                    }
                }
                break;
            }
        }
    }
    
    public MessageBuilder deleteServiceToken(final String s) {
        final ServiceToken serviceToken = this.serviceTokens.get(s);
        if (serviceToken == null) {
            return this;
        }
        Label_0077: {
            if (!serviceToken.isMasterTokenBound()) {
                break Label_0077;
            }
            MasterToken masterToken = this.masterToken;
            while (true) {
                Label_0082: {
                    if (!serviceToken.isUserIdTokenBound()) {
                        break Label_0082;
                    }
                    UserIdToken userIdToken = this.userIdToken;
                    try {
                        return this.addServiceToken(new ServiceToken(this.ctx, s, MessageBuilder.EMPTY_DATA, masterToken, userIdToken, false, null, new NullCryptoContext()));
                        masterToken = null;
                        continue;
                        userIdToken = null;
                        return this.addServiceToken(new ServiceToken(this.ctx, s, MessageBuilder.EMPTY_DATA, masterToken, userIdToken, false, null, new NullCryptoContext()));
                    }
                    catch (MslException ex) {
                        throw new MslInternalException("Failed to create and add empty service token to message.", ex);
                    }
                }
                break;
            }
        }
    }
    
    public MessageBuilder excludePeerServiceToken(final String s) {
        this.peerServiceTokens.remove(s);
        return this;
    }
    
    public MessageBuilder excludeServiceToken(final String s) {
        this.serviceTokens.remove(s);
        return this;
    }
    
    public MessageHeader getHeader() {
        KeyResponseData keyResponseData;
        if (this.keyExchangeData != null) {
            keyResponseData = this.keyExchangeData.keyResponseData;
        }
        else {
            keyResponseData = null;
        }
        final HashSet<ServiceToken> set = new HashSet<ServiceToken>(this.serviceTokens.values());
        Long value;
        if (this.nonReplayable) {
            if (this.masterToken == null) {
                throw new MslMessageException(MslError.NONREPLAYABLE_MESSAGE_REQUIRES_MASTERTOKEN);
            }
            value = this.ctx.getMslStore().getNonReplayableId(this.masterToken);
        }
        else {
            value = null;
        }
        return new MessageHeader(this.ctx, this.ctx.getEntityAuthenticationData(null), this.masterToken, new MessageHeader$HeaderData(this.recipient, this.messageId, value, this.renewable, this.handshake, this.capabilities, this.keyRequestData, keyResponseData, this.userAuthData, this.userIdToken, set), new MessageHeader$HeaderPeerData(this.peerMasterToken, this.peerUserIdToken, new HashSet<ServiceToken>(this.peerServiceTokens.values())));
    }
    
    public KeyExchangeFactory$KeyExchangeData getKeyExchangeData() {
        return this.keyExchangeData;
    }
    
    public MasterToken getMasterToken() {
        return this.masterToken;
    }
    
    public long getMessageId() {
        return this.messageId;
    }
    
    public MasterToken getPeerMasterToken() {
        return this.peerMasterToken;
    }
    
    public Set<ServiceToken> getPeerServiceTokens() {
        return Collections.unmodifiableSet((Set<? extends ServiceToken>)new HashSet<ServiceToken>(this.peerServiceTokens.values()));
    }
    
    public UserIdToken getPeerUserIdToken() {
        return this.peerUserIdToken;
    }
    
    public Set<ServiceToken> getServiceTokens() {
        return Collections.unmodifiableSet((Set<? extends ServiceToken>)new HashSet<ServiceToken>(this.serviceTokens.values()));
    }
    
    public UserIdToken getUserIdToken() {
        return this.userIdToken;
    }
    
    public boolean isHandshake() {
        return this.handshake;
    }
    
    public boolean isNonReplayable() {
        return this.nonReplayable;
    }
    
    public boolean isRenewable() {
        return this.renewable;
    }
    
    public MessageBuilder removeKeyRequestData(final KeyRequestData keyRequestData) {
        this.keyRequestData.remove(keyRequestData);
        return this;
    }
    
    public void setAuthTokens(final MasterToken masterToken, final UserIdToken userIdToken) {
        if (userIdToken != null && !userIdToken.isBoundTo(masterToken)) {
            throw new MslInternalException("User ID token must be bound to master token.");
        }
        if (this.keyExchangeData != null && !this.ctx.isPeerToPeer()) {
            throw new MslInternalException("Attempt to set message builder master token when key exchange data exists as a trusted network server.");
        }
        Set<ServiceToken> serviceTokens;
        try {
            serviceTokens = this.ctx.getMslStore().getServiceTokens(masterToken, userIdToken);
            for (final ServiceToken serviceToken : this.serviceTokens.values()) {
                if ((serviceToken.isUserIdTokenBound() && !serviceToken.isBoundTo(userIdToken)) || (serviceToken.isMasterTokenBound() && !serviceToken.isBoundTo(masterToken))) {
                    this.serviceTokens.remove(serviceToken.getName());
                }
            }
        }
        catch (MslException ex) {
            throw new MslInternalException("Invalid master token and user ID token combination despite checking above.", ex);
        }
        for (final ServiceToken serviceToken2 : serviceTokens) {
            this.serviceTokens.put(serviceToken2.getName(), serviceToken2);
        }
        this.masterToken = masterToken;
        this.userIdToken = userIdToken;
        if (this.userIdToken != null) {
            this.userAuthData = null;
        }
    }
    
    public MessageBuilder setHandshake(final boolean handshake) {
        this.handshake = handshake;
        if (this.handshake) {
            this.nonReplayable = false;
            this.renewable = true;
        }
        return this;
    }
    
    public MessageBuilder setNonReplayable(final boolean nonReplayable) {
        this.nonReplayable = nonReplayable;
        if (this.nonReplayable) {
            this.handshake = false;
        }
        return this;
    }
    
    public void setPeerAuthTokens(final MasterToken masterToken, final UserIdToken userIdToken) {
        if (!this.ctx.isPeerToPeer()) {
            throw new MslInternalException("Cannot set peer master token or peer user ID token when not in peer-to-peer mode.");
        }
        if (userIdToken != null && masterToken == null) {
            throw new MslInternalException("Peer master token cannot be null when setting peer user ID token.");
        }
        if (userIdToken != null && !userIdToken.isBoundTo(masterToken)) {
            throw new MslMessageException(MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH, "uit " + userIdToken + "; mt " + masterToken).setMasterToken(masterToken).setUserIdToken(userIdToken);
        }
        Set<ServiceToken> serviceTokens;
        while (true) {
            while (true) {
                ServiceToken serviceToken = null;
                Label_0201: {
                    try {
                        serviceTokens = this.ctx.getMslStore().getServiceTokens(masterToken, userIdToken);
                        final Iterator<ServiceToken> iterator = this.peerServiceTokens.values().iterator();
                        while (iterator.hasNext()) {
                            serviceToken = iterator.next();
                            if (!serviceToken.isUserIdTokenBound() || serviceToken.isBoundTo(userIdToken)) {
                                break Label_0201;
                            }
                            this.peerServiceTokens.remove(serviceToken.getName());
                        }
                        break;
                    }
                    catch (MslException ex) {
                        throw new MslInternalException("Invalid peer master token and user ID token combination despite proper check.", ex);
                    }
                }
                if (serviceToken.isMasterTokenBound() && !serviceToken.isBoundTo(masterToken)) {
                    this.peerServiceTokens.remove(serviceToken.getName());
                    continue;
                }
                continue;
            }
        }
        for (final ServiceToken serviceToken2 : serviceTokens) {
            if (!this.peerServiceTokens.containsKey(serviceToken2.getName())) {
                this.peerServiceTokens.put(serviceToken2.getName(), serviceToken2);
            }
        }
        this.peerUserIdToken = userIdToken;
        this.peerMasterToken = masterToken;
    }
    
    public MessageBuilder setRenewable(final boolean renewable) {
        if (!(this.renewable = renewable)) {
            this.handshake = false;
        }
        return this;
    }
    
    public void setUser(final MslUser mslUser) {
        if ((!this.ctx.isPeerToPeer() && this.userIdToken != null) || (this.ctx.isPeerToPeer() && this.peerUserIdToken != null)) {
            throw new MslInternalException("User ID token or peer user ID token already exists for the remote user.");
        }
        MasterToken masterToken;
        if (this.keyExchangeData != null) {
            masterToken = this.keyExchangeData.keyResponseData.getMasterToken();
        }
        else if (!this.ctx.isPeerToPeer()) {
            masterToken = this.masterToken;
        }
        else {
            masterToken = this.peerMasterToken;
        }
        if (masterToken == null) {
            throw new MslInternalException("User ID token or peer user ID token cannot be created because no corresponding master token exists.");
        }
        final UserIdToken userIdToken = this.ctx.getTokenFactory().createUserIdToken(this.ctx, mslUser, masterToken);
        if (!this.ctx.isPeerToPeer()) {
            this.userIdToken = userIdToken;
            this.userAuthData = null;
            return;
        }
        this.peerUserIdToken = userIdToken;
    }
    
    public MessageBuilder setUserAuthenticationData(final UserAuthenticationData userAuthData) {
        this.userAuthData = userAuthData;
        return this;
    }
    
    public boolean willEncryptHeader() {
        final EntityAuthenticationScheme scheme = this.ctx.getEntityAuthenticationData(null).getScheme();
        return this.masterToken != null || scheme.encrypts();
    }
    
    public boolean willEncryptPayloads() {
        final EntityAuthenticationScheme scheme = this.ctx.getEntityAuthenticationData(null).getScheme();
        return this.masterToken != null || (!this.ctx.isPeerToPeer() && this.keyExchangeData != null) || scheme.encrypts();
    }
    
    public boolean willIntegrityProtectHeader() {
        final EntityAuthenticationScheme scheme = this.ctx.getEntityAuthenticationData(null).getScheme();
        return this.masterToken != null || scheme.protectsIntegrity();
    }
    
    public boolean willIntegrityProtectPayloads() {
        final EntityAuthenticationScheme scheme = this.ctx.getEntityAuthenticationData(null).getScheme();
        return this.masterToken != null || (!this.ctx.isPeerToPeer() && this.keyExchangeData != null) || scheme.protectsIntegrity();
    }
}
