// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.util.Base64;
import java.util.Date;
import java.util.Map;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import java.util.Iterator;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslConstants;
import com.netflix.msl.crypto.SessionCryptoContext;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslError;
import java.util.Collection;
import com.netflix.msl.util.JsonUtils;
import com.netflix.android.org.json.JSONObject;
import java.util.Collections;
import java.util.HashSet;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.keyx.KeyResponseData;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class MessageHeader extends Header
{
    private static final String KEY_CAPABILITIES = "capabilities";
    private static final String KEY_HANDSHAKE = "handshake";
    private static final String KEY_KEY_REQUEST_DATA = "keyrequestdata";
    private static final String KEY_KEY_RESPONSE_DATA = "keyresponsedata";
    private static final String KEY_MESSAGE_ID = "messageid";
    private static final String KEY_NON_REPLAYABLE = "nonreplayable";
    private static final String KEY_NON_REPLAYABLE_ID = "nonreplayableid";
    private static final String KEY_PEER_MASTER_TOKEN = "peermastertoken";
    private static final String KEY_PEER_SERVICE_TOKENS = "peerservicetokens";
    private static final String KEY_PEER_USER_ID_TOKEN = "peeruseridtoken";
    private static final String KEY_RECIPIENT = "recipient";
    private static final String KEY_RENEWABLE = "renewable";
    private static final String KEY_SENDER = "sender";
    private static final String KEY_SERVICE_TOKENS = "servicetokens";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_USER_AUTHENTICATION_DATA = "userauthdata";
    private static final String KEY_USER_ID_TOKEN = "useridtoken";
    private static final long MILLISECONDS_PER_SECOND = 1000L;
    private final MessageCapabilities capabilities;
    private final EntityAuthenticationData entityAuthData;
    private final boolean handshake;
    private final byte[] headerdata;
    private final Set<KeyRequestData> keyRequestData;
    private final KeyResponseData keyResponseData;
    private final MasterToken masterToken;
    private final ICryptoContext messageCryptoContext;
    private final long messageId;
    private final Long nonReplayableId;
    private final MasterToken peerMasterToken;
    private final Set<ServiceToken> peerServiceTokens;
    private final UserIdToken peerUserIdToken;
    private final byte[] plaintext;
    private final String recipient;
    private final boolean renewable;
    private final String sender;
    private final Set<ServiceToken> serviceTokens;
    private final byte[] signature;
    private final Long timestamp;
    private final MslUser user;
    private final UserAuthenticationData userAuthData;
    private final UserIdToken userIdToken;
    private final boolean verified;
    
    public MessageHeader(final MslContext mslContext, EntityAuthenticationData entityAuthData, MasterToken masterToken, final MessageHeader$HeaderData messageHeader$HeaderData, final MessageHeader$HeaderPeerData messageHeader$HeaderPeerData) {
        if (messageHeader$HeaderData.messageId < 0L || messageHeader$HeaderData.messageId > 9007199254740992L) {
            throw new MslInternalException("Message ID " + messageHeader$HeaderData.messageId + " is out of range.");
        }
        if (entityAuthData == null && masterToken == null) {
            throw new MslInternalException("Message entity authentication data or master token must be provided.");
        }
        final boolean b = masterToken != null || entityAuthData.getScheme().encrypts();
        if (masterToken != null) {
            entityAuthData = null;
        }
        this.entityAuthData = entityAuthData;
        this.masterToken = masterToken;
        this.nonReplayableId = messageHeader$HeaderData.nonReplayableId;
        this.renewable = messageHeader$HeaderData.renewable;
        this.handshake = messageHeader$HeaderData.handshake;
        this.capabilities = messageHeader$HeaderData.capabilities;
        String identity;
        if (this.masterToken != null) {
            identity = mslContext.getEntityAuthenticationData(null).getIdentity();
        }
        else {
            identity = null;
        }
        this.sender = identity;
        String recipient;
        if (b) {
            recipient = messageHeader$HeaderData.recipient;
        }
        else {
            recipient = null;
        }
        this.recipient = recipient;
        this.timestamp = mslContext.getTime() / 1000L;
        this.messageId = messageHeader$HeaderData.messageId;
        Set<KeyRequestData> keyRequestData;
        if (messageHeader$HeaderData.keyRequestData != null) {
            keyRequestData = messageHeader$HeaderData.keyRequestData;
        }
        else {
            keyRequestData = new HashSet<KeyRequestData>();
        }
        this.keyRequestData = (Set<KeyRequestData>)Collections.unmodifiableSet((Set<?>)keyRequestData);
        this.keyResponseData = messageHeader$HeaderData.keyResponseData;
        this.userAuthData = messageHeader$HeaderData.userAuthData;
        this.userIdToken = messageHeader$HeaderData.userIdToken;
        Set<ServiceToken> serviceTokens;
        if (messageHeader$HeaderData.serviceTokens != null) {
            serviceTokens = messageHeader$HeaderData.serviceTokens;
        }
        else {
            serviceTokens = new HashSet<ServiceToken>();
        }
        this.serviceTokens = (Set<ServiceToken>)Collections.unmodifiableSet((Set<?>)serviceTokens);
        if (mslContext.isPeerToPeer()) {
            this.peerMasterToken = messageHeader$HeaderPeerData.peerMasterToken;
            this.peerUserIdToken = messageHeader$HeaderPeerData.peerUserIdToken;
            Set<ServiceToken> peerServiceTokens;
            if (messageHeader$HeaderPeerData.peerServiceTokens != null) {
                peerServiceTokens = messageHeader$HeaderPeerData.peerServiceTokens;
            }
            else {
                peerServiceTokens = new HashSet<ServiceToken>();
            }
            this.peerServiceTokens = (Set<ServiceToken>)Collections.unmodifiableSet((Set<?>)peerServiceTokens);
        }
        else {
            this.peerMasterToken = null;
            this.peerUserIdToken = null;
            this.peerServiceTokens = Collections.emptySet();
        }
        MasterToken masterToken2;
        if (this.keyResponseData != null) {
            if (!mslContext.isPeerToPeer()) {
                masterToken = this.keyResponseData.getMasterToken();
                masterToken2 = this.peerMasterToken;
            }
            else {
                masterToken = this.masterToken;
                masterToken2 = this.keyResponseData.getMasterToken();
            }
        }
        else {
            masterToken = this.masterToken;
            masterToken2 = this.peerMasterToken;
        }
        if (this.userIdToken != null && (masterToken == null || !this.userIdToken.isBoundTo(masterToken))) {
            throw new MslInternalException("User ID token must be bound to a master token.");
        }
        if (this.peerUserIdToken != null && (masterToken2 == null || !this.peerUserIdToken.isBoundTo(masterToken2))) {
            throw new MslInternalException("Peer user ID token must be bound to a peer master token.");
        }
        if (this.userIdToken != null) {
            this.user = this.userIdToken.getUser();
        }
        else {
            this.user = null;
        }
        for (final ServiceToken serviceToken : this.serviceTokens) {
            if (serviceToken.isMasterTokenBound() && (masterToken == null || !serviceToken.isBoundTo(masterToken))) {
                throw new MslInternalException("Master token bound service tokens must be bound to the provided master token.");
            }
            if (serviceToken.isUserIdTokenBound() && (this.userIdToken == null || !serviceToken.isBoundTo(this.userIdToken))) {
                throw new MslInternalException("User ID token bound service tokens must be bound to the provided user ID token.");
            }
        }
        for (final ServiceToken serviceToken2 : this.peerServiceTokens) {
            if (serviceToken2.isMasterTokenBound() && (masterToken2 == null || !serviceToken2.isBoundTo(masterToken2))) {
                throw new MslInternalException("Master token bound peer service tokens must be bound to the provided peer master token.");
            }
            if (serviceToken2.isUserIdTokenBound() && (this.peerUserIdToken == null || !serviceToken2.isBoundTo(this.peerUserIdToken))) {
                throw new MslInternalException("User ID token bound peer service tokens must be bound to the provided peer user ID token.");
            }
        }
        final JSONObject jsonObject = new JSONObject();
        Label_1257: {
            try {
                if (this.sender != null) {
                    jsonObject.put("sender", this.sender);
                }
                if (this.recipient != null) {
                    jsonObject.put("recipient", this.recipient);
                }
                jsonObject.put("timestamp", this.timestamp);
                jsonObject.put("messageid", this.messageId);
                jsonObject.put("nonreplayable", this.nonReplayableId != null);
                if (this.nonReplayableId != null) {
                    jsonObject.put("nonreplayableid", this.nonReplayableId);
                }
                jsonObject.put("renewable", this.renewable);
                jsonObject.put("handshake", this.handshake);
                jsonObject.put("capabilities", this.capabilities);
                if (this.keyRequestData.size() > 0) {
                    jsonObject.put("keyrequestdata", JsonUtils.createArray(this.keyRequestData));
                }
                if (this.keyResponseData != null) {
                    jsonObject.put("keyresponsedata", this.keyResponseData);
                }
                if (this.userAuthData != null) {
                    jsonObject.put("userauthdata", this.userAuthData);
                }
                if (this.userIdToken != null) {
                    jsonObject.put("useridtoken", this.userIdToken);
                }
                if (this.serviceTokens.size() > 0) {
                    jsonObject.put("servicetokens", JsonUtils.createArray(this.serviceTokens));
                }
                if (this.peerMasterToken != null) {
                    jsonObject.put("peermastertoken", this.peerMasterToken);
                }
                if (this.peerUserIdToken != null) {
                    jsonObject.put("peeruseridtoken", this.peerUserIdToken);
                }
                if (this.peerServiceTokens.size() > 0) {
                    jsonObject.put("peerservicetokens", JsonUtils.createArray(this.peerServiceTokens));
                }
                if (this.masterToken == null) {
                    break Label_1257;
                }
                final ICryptoContext cryptoContext = mslContext.getMslStore().getCryptoContext(this.masterToken);
                if (cryptoContext != null) {
                    break Label_1257;
                }
                if (!this.masterToken.isVerified() || !this.masterToken.isDecrypted()) {
                    throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, this.masterToken).setUserIdToken(this.userIdToken).setUserAuthenticationData(this.userAuthData).setMessageId(this.messageId);
                }
            }
            catch (JSONException ex) {
                throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "headerdata", ex).setMasterToken(this.masterToken).setEntityAuthenticationData(this.entityAuthData).setUserIdToken(this.peerUserIdToken).setUserAuthenticationData(this.userAuthData).setMessageId(this.messageId);
            }
            this.messageCryptoContext = new SessionCryptoContext(mslContext, this.masterToken);
            try {
                while (true) {
                    this.plaintext = jsonObject.toString().getBytes(MslConstants.DEFAULT_CHARSET);
                    this.headerdata = this.messageCryptoContext.encrypt(this.plaintext);
                    this.signature = this.messageCryptoContext.sign(this.headerdata);
                    this.verified = true;
                    return;
                    final ICryptoContext cryptoContext;
                    this.messageCryptoContext = cryptoContext;
                    continue;
                }
                try {
                    final EntityAuthenticationScheme scheme = this.entityAuthData.getScheme();
                    if (mslContext.getEntityAuthenticationFactory(scheme) == null) {
                        throw new MslEntityAuthException(MslError.ENTITYAUTH_FACTORY_NOT_FOUND, scheme.name());
                    }
                    goto Label_1339;
                }
                catch (MslCryptoException ex2) {
                    ex2.setEntityAuthenticationData(this.entityAuthData);
                    ex2.setUserIdToken(this.userIdToken);
                    ex2.setUserAuthenticationData(this.userAuthData);
                    ex2.setMessageId(this.messageId);
                    throw ex2;
                }
                catch (MslEntityAuthException ex3) {
                    ex3.setEntityAuthenticationData(this.entityAuthData);
                    ex3.setUserIdToken(this.userIdToken);
                    ex3.setUserAuthenticationData(this.userAuthData);
                    ex3.setMessageId(this.messageId);
                    throw ex3;
                }
            }
            catch (MslCryptoException ex4) {
                ex4.setMasterToken(this.masterToken);
                ex4.setEntityAuthenticationData(this.entityAuthData);
                ex4.setUserIdToken(this.userIdToken);
                ex4.setUserAuthenticationData(this.userAuthData);
                ex4.setMessageId(this.messageId);
                throw ex4;
            }
        }
    }
    
    protected MessageHeader(final MslContext p0, final String p1, final EntityAuthenticationData p2, final MasterToken p3, final byte[] p4, final Map<String, ICryptoContext> p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/netflix/msl/msg/Header.<init>:()V
        //     4: aload           4
        //     6: ifnonnull       66
        //     9: aload_3        
        //    10: astore          10
        //    12: aload_0        
        //    13: aload           10
        //    15: putfield        com/netflix/msl/msg/MessageHeader.entityAuthData:Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //    18: aload_0        
        //    19: aload           4
        //    21: putfield        com/netflix/msl/msg/MessageHeader.masterToken:Lcom/netflix/msl/tokens/MasterToken;
        //    24: aload_0        
        //    25: aload           5
        //    27: putfield        com/netflix/msl/msg/MessageHeader.signature:[B
        //    30: aload_3        
        //    31: ifnonnull       72
        //    34: aload           4
        //    36: ifnonnull       72
        //    39: new             Lcom/netflix/msl/MslMessageException;
        //    42: dup            
        //    43: getstatic       com/netflix/msl/MslError.MESSAGE_ENTITY_NOT_FOUND:Lcom/netflix/msl/MslError;
        //    46: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;)V
        //    49: athrow         
        //    50: astore_1       
        //    51: aload_1        
        //    52: aload           4
        //    54: invokevirtual   com/netflix/msl/MslCryptoException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslCryptoException;
        //    57: pop            
        //    58: aload_1        
        //    59: aload_3        
        //    60: invokevirtual   com/netflix/msl/MslCryptoException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslCryptoException;
        //    63: pop            
        //    64: aload_1        
        //    65: athrow         
        //    66: aconst_null    
        //    67: astore          10
        //    69: goto            12
        //    72: aload           4
        //    74: ifnull          207
        //    77: aload_1        
        //    78: invokevirtual   com/netflix/msl/util/MslContext.getMslStore:()Lcom/netflix/msl/util/MslStore;
        //    81: aload           4
        //    83: invokeinterface com/netflix/msl/util/MslStore.getCryptoContext:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/crypto/ICryptoContext;
        //    88: astore          5
        //    90: aload           5
        //    92: ifnonnull       198
        //    95: aload           4
        //    97: invokevirtual   com/netflix/msl/tokens/MasterToken.isVerified:()Z
        //   100: ifeq            111
        //   103: aload           4
        //   105: invokevirtual   com/netflix/msl/tokens/MasterToken.isDecrypted:()Z
        //   108: ifne            140
        //   111: new             Lcom/netflix/msl/MslMasterTokenException;
        //   114: dup            
        //   115: getstatic       com/netflix/msl/MslError.MASTERTOKEN_UNTRUSTED:Lcom/netflix/msl/MslError;
        //   118: aload           4
        //   120: invokespecial   com/netflix/msl/MslMasterTokenException.<init>:(Lcom/netflix/msl/MslError;Lcom/netflix/msl/tokens/MasterToken;)V
        //   123: athrow         
        //   124: astore_1       
        //   125: aload_1        
        //   126: aload           4
        //   128: invokevirtual   com/netflix/msl/MslEntityAuthException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEntityAuthException;
        //   131: pop            
        //   132: aload_1        
        //   133: aload_3        
        //   134: invokevirtual   com/netflix/msl/MslEntityAuthException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEntityAuthException;
        //   137: pop            
        //   138: aload_1        
        //   139: athrow         
        //   140: aload_0        
        //   141: new             Lcom/netflix/msl/crypto/SessionCryptoContext;
        //   144: dup            
        //   145: aload_1        
        //   146: aload           4
        //   148: invokespecial   com/netflix/msl/crypto/SessionCryptoContext.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   151: putfield        com/netflix/msl/msg/MessageHeader.messageCryptoContext:Lcom/netflix/msl/crypto/ICryptoContext;
        //   154: aload_0        
        //   155: aload_2        
        //   156: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   159: putfield        com/netflix/msl/msg/MessageHeader.headerdata:[B
        //   162: aload_0        
        //   163: getfield        com/netflix/msl/msg/MessageHeader.headerdata:[B
        //   166: ifnull          177
        //   169: aload_0        
        //   170: getfield        com/netflix/msl/msg/MessageHeader.headerdata:[B
        //   173: arraylength    
        //   174: ifne            297
        //   177: new             Lcom/netflix/msl/MslMessageException;
        //   180: dup            
        //   181: getstatic       com/netflix/msl/MslError.HEADER_DATA_MISSING:Lcom/netflix/msl/MslError;
        //   184: aload_2        
        //   185: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   188: aload           4
        //   190: invokevirtual   com/netflix/msl/MslMessageException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslMessageException;
        //   193: aload_3        
        //   194: invokevirtual   com/netflix/msl/MslMessageException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslMessageException;
        //   197: athrow         
        //   198: aload_0        
        //   199: aload           5
        //   201: putfield        com/netflix/msl/msg/MessageHeader.messageCryptoContext:Lcom/netflix/msl/crypto/ICryptoContext;
        //   204: goto            154
        //   207: aload_3        
        //   208: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationData.getScheme:()Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;
        //   211: astore          5
        //   213: aload_1        
        //   214: aload           5
        //   216: invokevirtual   com/netflix/msl/util/MslContext.getEntityAuthenticationFactory:(Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;)Lcom/netflix/msl/entityauth/EntityAuthenticationFactory;
        //   219: astore          10
        //   221: aload           10
        //   223: ifnonnull       251
        //   226: new             Lcom/netflix/msl/MslEntityAuthException;
        //   229: dup            
        //   230: getstatic       com/netflix/msl/MslError.ENTITYAUTH_FACTORY_NOT_FOUND:Lcom/netflix/msl/MslError;
        //   233: aload           5
        //   235: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationScheme.name:()Ljava/lang/String;
        //   238: invokespecial   com/netflix/msl/MslEntityAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   241: athrow         
        //   242: astore_1       
        //   243: aload_1        
        //   244: aload_3        
        //   245: invokevirtual   com/netflix/msl/MslCryptoException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslCryptoException;
        //   248: pop            
        //   249: aload_1        
        //   250: athrow         
        //   251: aload_0        
        //   252: aload           10
        //   254: aload_1        
        //   255: aload_3        
        //   256: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationFactory.getCryptoContext:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/crypto/ICryptoContext;
        //   259: putfield        com/netflix/msl/msg/MessageHeader.messageCryptoContext:Lcom/netflix/msl/crypto/ICryptoContext;
        //   262: goto            154
        //   265: astore_1       
        //   266: aload_1        
        //   267: aload_3        
        //   268: invokevirtual   com/netflix/msl/MslEntityAuthException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEntityAuthException;
        //   271: pop            
        //   272: aload_1        
        //   273: athrow         
        //   274: astore_1       
        //   275: new             Lcom/netflix/msl/MslMessageException;
        //   278: dup            
        //   279: getstatic       com/netflix/msl/MslError.HEADER_DATA_INVALID:Lcom/netflix/msl/MslError;
        //   282: aload_2        
        //   283: aload_1        
        //   284: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   287: aload           4
        //   289: invokevirtual   com/netflix/msl/MslMessageException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslMessageException;
        //   292: aload_3        
        //   293: invokevirtual   com/netflix/msl/MslMessageException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslMessageException;
        //   296: athrow         
        //   297: aload_0        
        //   298: aload_0        
        //   299: getfield        com/netflix/msl/msg/MessageHeader.messageCryptoContext:Lcom/netflix/msl/crypto/ICryptoContext;
        //   302: aload_0        
        //   303: getfield        com/netflix/msl/msg/MessageHeader.headerdata:[B
        //   306: aload_0        
        //   307: getfield        com/netflix/msl/msg/MessageHeader.signature:[B
        //   310: invokeinterface com/netflix/msl/crypto/ICryptoContext.verify:([B[B)Z
        //   315: putfield        com/netflix/msl/msg/MessageHeader.verified:Z
        //   318: aload_0        
        //   319: getfield        com/netflix/msl/msg/MessageHeader.verified:Z
        //   322: ifeq            443
        //   325: aload_0        
        //   326: getfield        com/netflix/msl/msg/MessageHeader.messageCryptoContext:Lcom/netflix/msl/crypto/ICryptoContext;
        //   329: aload_0        
        //   330: getfield        com/netflix/msl/msg/MessageHeader.headerdata:[B
        //   333: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   338: astore_2       
        //   339: aload_0        
        //   340: aload_2        
        //   341: putfield        com/netflix/msl/msg/MessageHeader.plaintext:[B
        //   344: aload_0        
        //   345: getfield        com/netflix/msl/msg/MessageHeader.plaintext:[B
        //   348: ifnonnull       448
        //   351: aload_0        
        //   352: lconst_1       
        //   353: putfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //   356: aload_0        
        //   357: aconst_null    
        //   358: putfield        com/netflix/msl/msg/MessageHeader.sender:Ljava/lang/String;
        //   361: aload_0        
        //   362: aconst_null    
        //   363: putfield        com/netflix/msl/msg/MessageHeader.recipient:Ljava/lang/String;
        //   366: aload_0        
        //   367: aconst_null    
        //   368: putfield        com/netflix/msl/msg/MessageHeader.timestamp:Ljava/lang/Long;
        //   371: aload_0        
        //   372: aconst_null    
        //   373: putfield        com/netflix/msl/msg/MessageHeader.keyResponseData:Lcom/netflix/msl/keyx/KeyResponseData;
        //   376: aload_0        
        //   377: aconst_null    
        //   378: putfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //   381: aload_0        
        //   382: aconst_null    
        //   383: putfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   386: aload_0        
        //   387: aconst_null    
        //   388: putfield        com/netflix/msl/msg/MessageHeader.user:Lcom/netflix/msl/tokens/MslUser;
        //   391: aload_0        
        //   392: invokestatic    java/util/Collections.emptySet:()Ljava/util/Set;
        //   395: putfield        com/netflix/msl/msg/MessageHeader.serviceTokens:Ljava/util/Set;
        //   398: aload_0        
        //   399: aconst_null    
        //   400: putfield        com/netflix/msl/msg/MessageHeader.nonReplayableId:Ljava/lang/Long;
        //   403: aload_0        
        //   404: iconst_0       
        //   405: putfield        com/netflix/msl/msg/MessageHeader.renewable:Z
        //   408: aload_0        
        //   409: iconst_0       
        //   410: putfield        com/netflix/msl/msg/MessageHeader.handshake:Z
        //   413: aload_0        
        //   414: aconst_null    
        //   415: putfield        com/netflix/msl/msg/MessageHeader.capabilities:Lcom/netflix/msl/msg/MessageCapabilities;
        //   418: aload_0        
        //   419: invokestatic    java/util/Collections.emptySet:()Ljava/util/Set;
        //   422: putfield        com/netflix/msl/msg/MessageHeader.keyRequestData:Ljava/util/Set;
        //   425: aload_0        
        //   426: aconst_null    
        //   427: putfield        com/netflix/msl/msg/MessageHeader.peerMasterToken:Lcom/netflix/msl/tokens/MasterToken;
        //   430: aload_0        
        //   431: aconst_null    
        //   432: putfield        com/netflix/msl/msg/MessageHeader.peerUserIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //   435: aload_0        
        //   436: invokestatic    java/util/Collections.emptySet:()Ljava/util/Set;
        //   439: putfield        com/netflix/msl/msg/MessageHeader.peerServiceTokens:Ljava/util/Set;
        //   442: return         
        //   443: aconst_null    
        //   444: astore_2       
        //   445: goto            339
        //   448: new             Ljava/lang/String;
        //   451: dup            
        //   452: aload_0        
        //   453: getfield        com/netflix/msl/msg/MessageHeader.plaintext:[B
        //   456: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   459: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   462: astore          11
        //   464: new             Lcom/netflix/android/org/json/JSONObject;
        //   467: dup            
        //   468: aload           11
        //   470: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   473: astore          10
        //   475: aload_0        
        //   476: aload           10
        //   478: ldc             "messageid"
        //   480: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   483: putfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //   486: aload_0        
        //   487: getfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //   490: lconst_0       
        //   491: lcmp           
        //   492: iflt            506
        //   495: aload_0        
        //   496: getfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //   499: ldc2_w          9007199254740992
        //   502: lcmp           
        //   503: ifle            590
        //   506: new             Lcom/netflix/msl/MslMessageException;
        //   509: dup            
        //   510: getstatic       com/netflix/msl/MslError.MESSAGE_ID_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   513: new             Ljava/lang/StringBuilder;
        //   516: dup            
        //   517: invokespecial   java/lang/StringBuilder.<init>:()V
        //   520: ldc_w           "headerdata "
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: aload           11
        //   528: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   531: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   534: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   537: aload           4
        //   539: invokevirtual   com/netflix/msl/MslMessageException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslMessageException;
        //   542: aload_3        
        //   543: invokevirtual   com/netflix/msl/MslMessageException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslMessageException;
        //   546: athrow         
        //   547: astore_1       
        //   548: new             Lcom/netflix/msl/MslEncodingException;
        //   551: dup            
        //   552: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   555: new             Ljava/lang/StringBuilder;
        //   558: dup            
        //   559: invokespecial   java/lang/StringBuilder.<init>:()V
        //   562: ldc_w           "headerdata "
        //   565: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   568: aload           11
        //   570: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   573: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   576: aload_1        
        //   577: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   580: aload           4
        //   582: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   585: aload_3        
        //   586: invokevirtual   com/netflix/msl/MslEncodingException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEncodingException;
        //   589: athrow         
        //   590: aload_0        
        //   591: getfield        com/netflix/msl/msg/MessageHeader.masterToken:Lcom/netflix/msl/tokens/MasterToken;
        //   594: ifnull          875
        //   597: aload           10
        //   599: ldc             "sender"
        //   601: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   604: astore_2       
        //   605: aload_0        
        //   606: aload_2        
        //   607: putfield        com/netflix/msl/msg/MessageHeader.sender:Ljava/lang/String;
        //   610: aload           10
        //   612: ldc             "recipient"
        //   614: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   617: ifeq            880
        //   620: aload           10
        //   622: ldc             "recipient"
        //   624: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   627: astore_2       
        //   628: aload_0        
        //   629: aload_2        
        //   630: putfield        com/netflix/msl/msg/MessageHeader.recipient:Ljava/lang/String;
        //   633: aload           10
        //   635: ldc             "timestamp"
        //   637: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   640: ifeq            885
        //   643: aload           10
        //   645: ldc             "timestamp"
        //   647: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   650: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   653: astore_2       
        //   654: aload_0        
        //   655: aload_2        
        //   656: putfield        com/netflix/msl/msg/MessageHeader.timestamp:Ljava/lang/Long;
        //   659: aload           10
        //   661: ldc             "keyresponsedata"
        //   663: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   666: ifeq            896
        //   669: aload_0        
        //   670: aload_1        
        //   671: aload           10
        //   673: ldc             "keyresponsedata"
        //   675: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //   678: invokestatic    com/netflix/msl/keyx/KeyResponseData.create:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;)Lcom/netflix/msl/keyx/KeyResponseData;
        //   681: putfield        com/netflix/msl/msg/MessageHeader.keyResponseData:Lcom/netflix/msl/keyx/KeyResponseData;
        //   684: aload_1        
        //   685: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //   688: ifne            890
        //   691: aload_0        
        //   692: getfield        com/netflix/msl/msg/MessageHeader.keyResponseData:Lcom/netflix/msl/keyx/KeyResponseData;
        //   695: invokevirtual   com/netflix/msl/keyx/KeyResponseData.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   698: astore_2       
        //   699: goto            1721
        //   702: aload           10
        //   704: ldc             "useridtoken"
        //   706: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   709: ifeq            1724
        //   712: new             Lcom/netflix/msl/tokens/UserIdToken;
        //   715: dup            
        //   716: aload_1        
        //   717: aload           10
        //   719: ldc             "useridtoken"
        //   721: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //   724: aload_2        
        //   725: invokespecial   com/netflix/msl/tokens/UserIdToken.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;Lcom/netflix/msl/tokens/MasterToken;)V
        //   728: astore          5
        //   730: aload_0        
        //   731: aload           5
        //   733: putfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //   736: aload           10
        //   738: ldc             "userauthdata"
        //   740: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   743: ifeq            1730
        //   746: aload_1        
        //   747: aload_2        
        //   748: aload           10
        //   750: ldc             "userauthdata"
        //   752: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //   755: invokestatic    com/netflix/msl/userauth/UserAuthenticationData.create:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;Lcom/netflix/android/org/json/JSONObject;)Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   758: astore          5
        //   760: aload_0        
        //   761: aload           5
        //   763: putfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   766: aload_0        
        //   767: getfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   770: ifnull          1039
        //   773: aload_0        
        //   774: getfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   777: invokevirtual   com/netflix/msl/userauth/UserAuthenticationData.getScheme:()Lcom/netflix/msl/userauth/UserAuthenticationScheme;
        //   780: astore          5
        //   782: aload_1        
        //   783: aload           5
        //   785: invokevirtual   com/netflix/msl/util/MslContext.getUserAuthenticationFactory:(Lcom/netflix/msl/userauth/UserAuthenticationScheme;)Lcom/netflix/msl/userauth/UserAuthenticationFactory;
        //   788: astore          12
        //   790: aload           12
        //   792: ifnonnull       907
        //   795: new             Lcom/netflix/msl/MslUserAuthException;
        //   798: dup            
        //   799: getstatic       com/netflix/msl/MslError.USERAUTH_FACTORY_NOT_FOUND:Lcom/netflix/msl/MslError;
        //   802: aload           5
        //   804: invokevirtual   com/netflix/msl/userauth/UserAuthenticationScheme.name:()Ljava/lang/String;
        //   807: invokespecial   com/netflix/msl/MslUserAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   810: aload_0        
        //   811: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //   814: invokevirtual   com/netflix/msl/MslUserAuthException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   817: aload_0        
        //   818: getfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   821: invokevirtual   com/netflix/msl/MslException.setUserAuthenticationData:(Lcom/netflix/msl/userauth/UserAuthenticationData;)Lcom/netflix/msl/MslException;
        //   824: athrow         
        //   825: astore_1       
        //   826: new             Lcom/netflix/msl/MslEncodingException;
        //   829: dup            
        //   830: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   833: new             Ljava/lang/StringBuilder;
        //   836: dup            
        //   837: invokespecial   java/lang/StringBuilder.<init>:()V
        //   840: ldc_w           "headerdata "
        //   843: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   846: aload           11
        //   848: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   851: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   854: aload_1        
        //   855: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   858: aload           4
        //   860: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   863: aload_3        
        //   864: invokevirtual   com/netflix/msl/MslEncodingException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEncodingException;
        //   867: aload_0        
        //   868: getfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //   871: invokevirtual   com/netflix/msl/MslEncodingException.setMessageId:(J)Lcom/netflix/msl/MslEncodingException;
        //   874: athrow         
        //   875: aconst_null    
        //   876: astore_2       
        //   877: goto            605
        //   880: aconst_null    
        //   881: astore_2       
        //   882: goto            628
        //   885: aconst_null    
        //   886: astore_2       
        //   887: goto            654
        //   890: aload           4
        //   892: astore_2       
        //   893: goto            1721
        //   896: aload_0        
        //   897: aconst_null    
        //   898: putfield        com/netflix/msl/msg/MessageHeader.keyResponseData:Lcom/netflix/msl/keyx/KeyResponseData;
        //   901: aload           4
        //   903: astore_2       
        //   904: goto            702
        //   907: aload_0        
        //   908: getfield        com/netflix/msl/msg/MessageHeader.masterToken:Lcom/netflix/msl/tokens/MasterToken;
        //   911: ifnull          1027
        //   914: aload_0        
        //   915: getfield        com/netflix/msl/msg/MessageHeader.masterToken:Lcom/netflix/msl/tokens/MasterToken;
        //   918: invokevirtual   com/netflix/msl/tokens/MasterToken.getIdentity:()Ljava/lang/String;
        //   921: astore          5
        //   923: aload_0        
        //   924: aload           12
        //   926: aload_1        
        //   927: aload           5
        //   929: aload_0        
        //   930: getfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   933: aload_0        
        //   934: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //   937: invokevirtual   com/netflix/msl/userauth/UserAuthenticationFactory.authenticate:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Lcom/netflix/msl/userauth/UserAuthenticationData;Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/tokens/MslUser;
        //   940: putfield        com/netflix/msl/msg/MessageHeader.user:Lcom/netflix/msl/tokens/MslUser;
        //   943: new             Ljava/util/HashSet;
        //   946: dup            
        //   947: invokespecial   java/util/HashSet.<init>:()V
        //   950: astore          5
        //   952: aload           10
        //   954: ldc             "servicetokens"
        //   956: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   959: ifeq            1116
        //   962: aload           10
        //   964: ldc             "servicetokens"
        //   966: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONArray;
        //   969: astore          12
        //   971: iconst_0       
        //   972: istore          7
        //   974: aload           12
        //   976: invokevirtual   com/netflix/android/org/json/JSONArray.length:()I
        //   979: istore          8
        //   981: iload           7
        //   983: iload           8
        //   985: if_icmpge       1116
        //   988: aload           5
        //   990: new             Lcom/netflix/msl/tokens/ServiceToken;
        //   993: dup            
        //   994: aload_1        
        //   995: aload           12
        //   997: iload           7
        //   999: invokevirtual   com/netflix/android/org/json/JSONArray.getJSONObject:(I)Lcom/netflix/android/org/json/JSONObject;
        //  1002: aload_2        
        //  1003: aload_0        
        //  1004: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1007: aload           6
        //  1009: invokespecial   com/netflix/msl/tokens/ServiceToken.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;Lcom/netflix/msl/tokens/MasterToken;Lcom/netflix/msl/tokens/UserIdToken;Ljava/util/Map;)V
        //  1012: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //  1017: pop            
        //  1018: iload           7
        //  1020: iconst_1       
        //  1021: iadd           
        //  1022: istore          7
        //  1024: goto            974
        //  1027: aload_0        
        //  1028: getfield        com/netflix/msl/msg/MessageHeader.entityAuthData:Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //  1031: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationData.getIdentity:()Ljava/lang/String;
        //  1034: astore          5
        //  1036: goto            923
        //  1039: aload_0        
        //  1040: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1043: ifnull          1085
        //  1046: aload_0        
        //  1047: aload_0        
        //  1048: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1051: invokevirtual   com/netflix/msl/tokens/UserIdToken.getUser:()Lcom/netflix/msl/tokens/MslUser;
        //  1054: putfield        com/netflix/msl/msg/MessageHeader.user:Lcom/netflix/msl/tokens/MslUser;
        //  1057: goto            943
        //  1060: astore_1       
        //  1061: aload_1        
        //  1062: aload           4
        //  1064: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //  1067: pop            
        //  1068: aload_1        
        //  1069: aload_3        
        //  1070: invokevirtual   com/netflix/msl/MslException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslException;
        //  1073: pop            
        //  1074: aload_1        
        //  1075: aload_0        
        //  1076: getfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //  1079: invokevirtual   com/netflix/msl/MslException.setMessageId:(J)Lcom/netflix/msl/MslException;
        //  1082: pop            
        //  1083: aload_1        
        //  1084: athrow         
        //  1085: aload_0        
        //  1086: aconst_null    
        //  1087: putfield        com/netflix/msl/msg/MessageHeader.user:Lcom/netflix/msl/tokens/MslUser;
        //  1090: goto            943
        //  1093: astore_1       
        //  1094: aload_1        
        //  1095: aload_2        
        //  1096: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //  1099: aload_0        
        //  1100: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1103: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //  1106: aload_0        
        //  1107: getfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //  1110: invokevirtual   com/netflix/msl/MslException.setUserAuthenticationData:(Lcom/netflix/msl/userauth/UserAuthenticationData;)Lcom/netflix/msl/MslException;
        //  1113: pop            
        //  1114: aload_1        
        //  1115: athrow         
        //  1116: aload_0        
        //  1117: aload           5
        //  1119: invokestatic    java/util/Collections.unmodifiableSet:(Ljava/util/Set;)Ljava/util/Set;
        //  1122: putfield        com/netflix/msl/msg/MessageHeader.serviceTokens:Ljava/util/Set;
        //  1125: aload           10
        //  1127: ldc             "nonreplayableid"
        //  1129: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //  1132: ifeq            1319
        //  1135: aload           10
        //  1137: ldc             "nonreplayableid"
        //  1139: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //  1142: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1145: astore_2       
        //  1146: aload_0        
        //  1147: aload_2        
        //  1148: putfield        com/netflix/msl/msg/MessageHeader.nonReplayableId:Ljava/lang/Long;
        //  1151: aload_0        
        //  1152: aload           10
        //  1154: ldc             "renewable"
        //  1156: invokevirtual   com/netflix/android/org/json/JSONObject.getBoolean:(Ljava/lang/String;)Z
        //  1159: putfield        com/netflix/msl/msg/MessageHeader.renewable:Z
        //  1162: aload           10
        //  1164: ldc             "handshake"
        //  1166: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //  1169: ifeq            1324
        //  1172: aload           10
        //  1174: ldc             "handshake"
        //  1176: invokevirtual   com/netflix/android/org/json/JSONObject.getBoolean:(Ljava/lang/String;)Z
        //  1179: istore          9
        //  1181: aload_0        
        //  1182: iload           9
        //  1184: putfield        com/netflix/msl/msg/MessageHeader.handshake:Z
        //  1187: aload_0        
        //  1188: getfield        com/netflix/msl/msg/MessageHeader.nonReplayableId:Ljava/lang/Long;
        //  1191: ifnull          1330
        //  1194: aload_0        
        //  1195: getfield        com/netflix/msl/msg/MessageHeader.nonReplayableId:Ljava/lang/Long;
        //  1198: invokevirtual   java/lang/Long.longValue:()J
        //  1201: lconst_0       
        //  1202: lcmp           
        //  1203: iflt            1220
        //  1206: aload_0        
        //  1207: getfield        com/netflix/msl/msg/MessageHeader.nonReplayableId:Ljava/lang/Long;
        //  1210: invokevirtual   java/lang/Long.longValue:()J
        //  1213: ldc2_w          9007199254740992
        //  1216: lcmp           
        //  1217: ifle            1330
        //  1220: new             Lcom/netflix/msl/MslMessageException;
        //  1223: dup            
        //  1224: getstatic       com/netflix/msl/MslError.NONREPLAYABLE_ID_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //  1227: new             Ljava/lang/StringBuilder;
        //  1230: dup            
        //  1231: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1234: ldc_w           "headerdata "
        //  1237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1240: aload           11
        //  1242: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1245: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1248: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //  1251: athrow         
        //  1252: astore_1       
        //  1253: new             Lcom/netflix/msl/MslEncodingException;
        //  1256: dup            
        //  1257: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //  1260: new             Ljava/lang/StringBuilder;
        //  1263: dup            
        //  1264: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1267: ldc_w           "headerdata "
        //  1270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1273: aload           10
        //  1275: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //  1278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1284: aload_1        
        //  1285: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1288: aload           4
        //  1290: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //  1293: aload_3        
        //  1294: invokevirtual   com/netflix/msl/MslEncodingException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEncodingException;
        //  1297: aload_0        
        //  1298: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1301: invokevirtual   com/netflix/msl/MslEncodingException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslEncodingException;
        //  1304: aload_0        
        //  1305: getfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //  1308: invokevirtual   com/netflix/msl/MslEncodingException.setUserAuthenticationData:(Lcom/netflix/msl/userauth/UserAuthenticationData;)Lcom/netflix/msl/MslEncodingException;
        //  1311: aload_0        
        //  1312: getfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //  1315: invokevirtual   com/netflix/msl/MslEncodingException.setMessageId:(J)Lcom/netflix/msl/MslEncodingException;
        //  1318: athrow         
        //  1319: aconst_null    
        //  1320: astore_2       
        //  1321: goto            1146
        //  1324: iconst_0       
        //  1325: istore          9
        //  1327: goto            1181
        //  1330: aload           10
        //  1332: ldc             "capabilities"
        //  1334: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //  1337: ifeq            1425
        //  1340: aload_0        
        //  1341: new             Lcom/netflix/msl/msg/MessageCapabilities;
        //  1344: dup            
        //  1345: aload           10
        //  1347: ldc             "capabilities"
        //  1349: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //  1352: invokespecial   com/netflix/msl/msg/MessageCapabilities.<init>:(Lcom/netflix/android/org/json/JSONObject;)V
        //  1355: putfield        com/netflix/msl/msg/MessageHeader.capabilities:Lcom/netflix/msl/msg/MessageCapabilities;
        //  1358: new             Ljava/util/HashSet;
        //  1361: dup            
        //  1362: invokespecial   java/util/HashSet.<init>:()V
        //  1365: astore_2       
        //  1366: aload           10
        //  1368: ldc             "keyrequestdata"
        //  1370: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //  1373: ifeq            1476
        //  1376: aload           10
        //  1378: ldc             "keyrequestdata"
        //  1380: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONArray;
        //  1383: astore          5
        //  1385: iconst_0       
        //  1386: istore          7
        //  1388: iload           7
        //  1390: aload           5
        //  1392: invokevirtual   com/netflix/android/org/json/JSONArray.length:()I
        //  1395: if_icmpge       1476
        //  1398: aload_2        
        //  1399: aload_1        
        //  1400: aload           5
        //  1402: iload           7
        //  1404: invokevirtual   com/netflix/android/org/json/JSONArray.getJSONObject:(I)Lcom/netflix/android/org/json/JSONObject;
        //  1407: invokestatic    com/netflix/msl/keyx/KeyRequestData.create:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;)Lcom/netflix/msl/keyx/KeyRequestData;
        //  1410: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //  1415: pop            
        //  1416: iload           7
        //  1418: iconst_1       
        //  1419: iadd           
        //  1420: istore          7
        //  1422: goto            1388
        //  1425: aload_0        
        //  1426: aconst_null    
        //  1427: putfield        com/netflix/msl/msg/MessageHeader.capabilities:Lcom/netflix/msl/msg/MessageCapabilities;
        //  1430: goto            1358
        //  1433: astore_1       
        //  1434: aload_1        
        //  1435: aload           4
        //  1437: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //  1440: pop            
        //  1441: aload_1        
        //  1442: aload_3        
        //  1443: invokevirtual   com/netflix/msl/MslException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslException;
        //  1446: pop            
        //  1447: aload_1        
        //  1448: aload_0        
        //  1449: getfield        com/netflix/msl/msg/MessageHeader.userIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1452: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //  1455: pop            
        //  1456: aload_1        
        //  1457: aload_0        
        //  1458: getfield        com/netflix/msl/msg/MessageHeader.userAuthData:Lcom/netflix/msl/userauth/UserAuthenticationData;
        //  1461: invokevirtual   com/netflix/msl/MslException.setUserAuthenticationData:(Lcom/netflix/msl/userauth/UserAuthenticationData;)Lcom/netflix/msl/MslException;
        //  1464: pop            
        //  1465: aload_1        
        //  1466: aload_0        
        //  1467: getfield        com/netflix/msl/msg/MessageHeader.messageId:J
        //  1470: invokevirtual   com/netflix/msl/MslException.setMessageId:(J)Lcom/netflix/msl/MslException;
        //  1473: pop            
        //  1474: aload_1        
        //  1475: athrow         
        //  1476: aload_0        
        //  1477: aload_2        
        //  1478: invokestatic    java/util/Collections.unmodifiableSet:(Ljava/util/Set;)Ljava/util/Set;
        //  1481: putfield        com/netflix/msl/msg/MessageHeader.keyRequestData:Ljava/util/Set;
        //  1484: aload_1        
        //  1485: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //  1488: ifeq            1703
        //  1491: aload           10
        //  1493: ldc             "peermastertoken"
        //  1495: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //  1498: ifeq            1655
        //  1501: new             Lcom/netflix/msl/tokens/MasterToken;
        //  1504: dup            
        //  1505: aload_1        
        //  1506: aload           10
        //  1508: ldc             "peermastertoken"
        //  1510: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //  1513: invokespecial   com/netflix/msl/tokens/MasterToken.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;)V
        //  1516: astore_2       
        //  1517: aload_0        
        //  1518: aload_2        
        //  1519: putfield        com/netflix/msl/msg/MessageHeader.peerMasterToken:Lcom/netflix/msl/tokens/MasterToken;
        //  1522: aload_0        
        //  1523: getfield        com/netflix/msl/msg/MessageHeader.keyResponseData:Lcom/netflix/msl/keyx/KeyResponseData;
        //  1526: ifnull          1660
        //  1529: aload_0        
        //  1530: getfield        com/netflix/msl/msg/MessageHeader.keyResponseData:Lcom/netflix/msl/keyx/KeyResponseData;
        //  1533: invokevirtual   com/netflix/msl/keyx/KeyResponseData.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //  1536: astore_2       
        //  1537: aload           10
        //  1539: ldc             "peeruseridtoken"
        //  1541: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //  1544: ifeq            1736
        //  1547: new             Lcom/netflix/msl/tokens/UserIdToken;
        //  1550: dup            
        //  1551: aload_1        
        //  1552: aload           10
        //  1554: ldc             "peeruseridtoken"
        //  1556: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //  1559: aload_2        
        //  1560: invokespecial   com/netflix/msl/tokens/UserIdToken.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;Lcom/netflix/msl/tokens/MasterToken;)V
        //  1563: astore          5
        //  1565: aload_0        
        //  1566: aload           5
        //  1568: putfield        com/netflix/msl/msg/MessageHeader.peerUserIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1571: new             Ljava/util/HashSet;
        //  1574: dup            
        //  1575: invokespecial   java/util/HashSet.<init>:()V
        //  1578: astore          5
        //  1580: aload           10
        //  1582: ldc             "peerservicetokens"
        //  1584: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //  1587: ifeq            1693
        //  1590: aload           10
        //  1592: ldc             "peerservicetokens"
        //  1594: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONArray;
        //  1597: astore          11
        //  1599: iconst_0       
        //  1600: istore          7
        //  1602: aload           11
        //  1604: invokevirtual   com/netflix/android/org/json/JSONArray.length:()I
        //  1607: istore          8
        //  1609: iload           7
        //  1611: iload           8
        //  1613: if_icmpge       1693
        //  1616: aload           5
        //  1618: new             Lcom/netflix/msl/tokens/ServiceToken;
        //  1621: dup            
        //  1622: aload_1        
        //  1623: aload           11
        //  1625: iload           7
        //  1627: invokevirtual   com/netflix/android/org/json/JSONArray.getJSONObject:(I)Lcom/netflix/android/org/json/JSONObject;
        //  1630: aload_2        
        //  1631: aload_0        
        //  1632: getfield        com/netflix/msl/msg/MessageHeader.peerUserIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1635: aload           6
        //  1637: invokespecial   com/netflix/msl/tokens/ServiceToken.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;Lcom/netflix/msl/tokens/MasterToken;Lcom/netflix/msl/tokens/UserIdToken;Ljava/util/Map;)V
        //  1640: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //  1645: pop            
        //  1646: iload           7
        //  1648: iconst_1       
        //  1649: iadd           
        //  1650: istore          7
        //  1652: goto            1602
        //  1655: aconst_null    
        //  1656: astore_2       
        //  1657: goto            1517
        //  1660: aload_0        
        //  1661: getfield        com/netflix/msl/msg/MessageHeader.peerMasterToken:Lcom/netflix/msl/tokens/MasterToken;
        //  1664: astore_2       
        //  1665: goto            1537
        //  1668: astore_1       
        //  1669: aload_1        
        //  1670: aload_2        
        //  1671: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //  1674: pop            
        //  1675: aload_1        
        //  1676: athrow         
        //  1677: astore_1       
        //  1678: aload_1        
        //  1679: aload_2        
        //  1680: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //  1683: aload_0        
        //  1684: getfield        com/netflix/msl/msg/MessageHeader.peerUserIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1687: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //  1690: pop            
        //  1691: aload_1        
        //  1692: athrow         
        //  1693: aload_0        
        //  1694: aload           5
        //  1696: invokestatic    java/util/Collections.unmodifiableSet:(Ljava/util/Set;)Ljava/util/Set;
        //  1699: putfield        com/netflix/msl/msg/MessageHeader.peerServiceTokens:Ljava/util/Set;
        //  1702: return         
        //  1703: aload_0        
        //  1704: aconst_null    
        //  1705: putfield        com/netflix/msl/msg/MessageHeader.peerMasterToken:Lcom/netflix/msl/tokens/MasterToken;
        //  1708: aload_0        
        //  1709: aconst_null    
        //  1710: putfield        com/netflix/msl/msg/MessageHeader.peerUserIdToken:Lcom/netflix/msl/tokens/UserIdToken;
        //  1713: aload_0        
        //  1714: invokestatic    java/util/Collections.emptySet:()Ljava/util/Set;
        //  1717: putfield        com/netflix/msl/msg/MessageHeader.peerServiceTokens:Ljava/util/Set;
        //  1720: return         
        //  1721: goto            702
        //  1724: aconst_null    
        //  1725: astore          5
        //  1727: goto            730
        //  1730: aconst_null    
        //  1731: astore          5
        //  1733: goto            760
        //  1736: aconst_null    
        //  1737: astore          5
        //  1739: goto            1565
        //    Signature:
        //  (Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Lcom/netflix/msl/entityauth/EntityAuthenticationData;Lcom/netflix/msl/tokens/MasterToken;[BLjava/util/Map<Ljava/lang/String;Lcom/netflix/msl/crypto/ICryptoContext;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  12     30     50     66     Lcom/netflix/msl/MslCryptoException;
        //  12     30     124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  39     50     50     66     Lcom/netflix/msl/MslCryptoException;
        //  39     50     124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  77     90     50     66     Lcom/netflix/msl/MslCryptoException;
        //  77     90     124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  95     111    50     66     Lcom/netflix/msl/MslCryptoException;
        //  95     111    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  111    124    50     66     Lcom/netflix/msl/MslCryptoException;
        //  111    124    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  140    154    50     66     Lcom/netflix/msl/MslCryptoException;
        //  140    154    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  154    162    274    297    Ljava/lang/IllegalArgumentException;
        //  154    162    50     66     Lcom/netflix/msl/MslCryptoException;
        //  154    162    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  162    177    50     66     Lcom/netflix/msl/MslCryptoException;
        //  162    177    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  177    198    50     66     Lcom/netflix/msl/MslCryptoException;
        //  177    198    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  198    204    50     66     Lcom/netflix/msl/MslCryptoException;
        //  198    204    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  207    221    242    251    Lcom/netflix/msl/MslCryptoException;
        //  207    221    265    274    Lcom/netflix/msl/MslEntityAuthException;
        //  226    242    242    251    Lcom/netflix/msl/MslCryptoException;
        //  226    242    265    274    Lcom/netflix/msl/MslEntityAuthException;
        //  243    251    50     66     Lcom/netflix/msl/MslCryptoException;
        //  243    251    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  251    262    242    251    Lcom/netflix/msl/MslCryptoException;
        //  251    262    265    274    Lcom/netflix/msl/MslEntityAuthException;
        //  266    274    50     66     Lcom/netflix/msl/MslCryptoException;
        //  266    274    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  275    297    50     66     Lcom/netflix/msl/MslCryptoException;
        //  275    297    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  297    339    50     66     Lcom/netflix/msl/MslCryptoException;
        //  297    339    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  339    344    50     66     Lcom/netflix/msl/MslCryptoException;
        //  339    344    124    140    Lcom/netflix/msl/MslEntityAuthException;
        //  464    506    547    590    Lcom/netflix/android/org/json/JSONException;
        //  506    547    547    590    Lcom/netflix/android/org/json/JSONException;
        //  590    605    825    875    Lcom/netflix/android/org/json/JSONException;
        //  590    605    1060   1085   Lcom/netflix/msl/MslException;
        //  605    628    825    875    Lcom/netflix/android/org/json/JSONException;
        //  605    628    1060   1085   Lcom/netflix/msl/MslException;
        //  628    654    825    875    Lcom/netflix/android/org/json/JSONException;
        //  628    654    1060   1085   Lcom/netflix/msl/MslException;
        //  654    699    825    875    Lcom/netflix/android/org/json/JSONException;
        //  654    699    1060   1085   Lcom/netflix/msl/MslException;
        //  702    730    825    875    Lcom/netflix/android/org/json/JSONException;
        //  702    730    1060   1085   Lcom/netflix/msl/MslException;
        //  730    760    825    875    Lcom/netflix/android/org/json/JSONException;
        //  730    760    1060   1085   Lcom/netflix/msl/MslException;
        //  760    790    825    875    Lcom/netflix/android/org/json/JSONException;
        //  760    790    1060   1085   Lcom/netflix/msl/MslException;
        //  795    825    825    875    Lcom/netflix/android/org/json/JSONException;
        //  795    825    1060   1085   Lcom/netflix/msl/MslException;
        //  896    901    825    875    Lcom/netflix/android/org/json/JSONException;
        //  896    901    1060   1085   Lcom/netflix/msl/MslException;
        //  907    923    825    875    Lcom/netflix/android/org/json/JSONException;
        //  907    923    1060   1085   Lcom/netflix/msl/MslException;
        //  923    943    825    875    Lcom/netflix/android/org/json/JSONException;
        //  923    943    1060   1085   Lcom/netflix/msl/MslException;
        //  943    971    825    875    Lcom/netflix/android/org/json/JSONException;
        //  943    971    1060   1085   Lcom/netflix/msl/MslException;
        //  974    981    825    875    Lcom/netflix/android/org/json/JSONException;
        //  974    981    1060   1085   Lcom/netflix/msl/MslException;
        //  988    1018   1093   1116   Lcom/netflix/msl/MslException;
        //  988    1018   825    875    Lcom/netflix/android/org/json/JSONException;
        //  1027   1036   825    875    Lcom/netflix/android/org/json/JSONException;
        //  1027   1036   1060   1085   Lcom/netflix/msl/MslException;
        //  1039   1057   825    875    Lcom/netflix/android/org/json/JSONException;
        //  1039   1057   1060   1085   Lcom/netflix/msl/MslException;
        //  1085   1090   825    875    Lcom/netflix/android/org/json/JSONException;
        //  1085   1090   1060   1085   Lcom/netflix/msl/MslException;
        //  1094   1116   825    875    Lcom/netflix/android/org/json/JSONException;
        //  1094   1116   1060   1085   Lcom/netflix/msl/MslException;
        //  1116   1125   825    875    Lcom/netflix/android/org/json/JSONException;
        //  1116   1125   1060   1085   Lcom/netflix/msl/MslException;
        //  1125   1146   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1125   1146   1433   1476   Lcom/netflix/msl/MslException;
        //  1146   1181   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1146   1181   1433   1476   Lcom/netflix/msl/MslException;
        //  1181   1220   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1181   1220   1433   1476   Lcom/netflix/msl/MslException;
        //  1220   1252   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1220   1252   1433   1476   Lcom/netflix/msl/MslException;
        //  1330   1358   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1330   1358   1433   1476   Lcom/netflix/msl/MslException;
        //  1358   1385   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1358   1385   1433   1476   Lcom/netflix/msl/MslException;
        //  1388   1416   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1388   1416   1433   1476   Lcom/netflix/msl/MslException;
        //  1425   1430   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1425   1430   1433   1476   Lcom/netflix/msl/MslException;
        //  1476   1517   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1476   1517   1433   1476   Lcom/netflix/msl/MslException;
        //  1517   1537   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1517   1537   1433   1476   Lcom/netflix/msl/MslException;
        //  1537   1565   1668   1677   Lcom/netflix/msl/MslException;
        //  1537   1565   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1565   1571   1668   1677   Lcom/netflix/msl/MslException;
        //  1565   1571   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1571   1599   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1571   1599   1433   1476   Lcom/netflix/msl/MslException;
        //  1602   1609   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1602   1609   1433   1476   Lcom/netflix/msl/MslException;
        //  1616   1646   1677   1693   Lcom/netflix/msl/MslException;
        //  1616   1646   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1660   1665   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1660   1665   1433   1476   Lcom/netflix/msl/MslException;
        //  1669   1677   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1669   1677   1433   1476   Lcom/netflix/msl/MslException;
        //  1678   1693   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1678   1693   1433   1476   Lcom/netflix/msl/MslException;
        //  1693   1702   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1693   1702   1433   1476   Lcom/netflix/msl/MslException;
        //  1703   1720   1252   1319   Lcom/netflix/android/org/json/JSONException;
        //  1703   1720   1433   1476   Lcom/netflix/msl/MslException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 845, Size: 845
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof MessageHeader)) {
                return false;
            }
            final MessageHeader messageHeader = (MessageHeader)o;
            if (((this.masterToken == null || !this.masterToken.equals(messageHeader.masterToken)) && (this.entityAuthData == null || !this.entityAuthData.equals(messageHeader.entityAuthData))) || ((this.sender == null || !this.sender.equals(messageHeader.sender)) && this.sender != messageHeader.sender) || ((this.recipient == null || !this.recipient.equals(messageHeader.recipient)) && this.recipient != messageHeader.recipient) || ((this.timestamp == null || !this.timestamp.equals(messageHeader.timestamp)) && (this.timestamp != null || messageHeader.timestamp != null)) || this.messageId != messageHeader.messageId || ((this.nonReplayableId == null || !this.nonReplayableId.equals(messageHeader.nonReplayableId)) && (this.nonReplayableId != null || messageHeader.nonReplayableId != null)) || this.renewable != messageHeader.renewable || this.handshake != messageHeader.handshake || ((this.capabilities == null || !this.capabilities.equals(messageHeader.capabilities)) && this.capabilities != messageHeader.capabilities) || !this.keyRequestData.equals(messageHeader.keyRequestData) || ((this.keyResponseData == null || !this.keyResponseData.equals(messageHeader.keyResponseData)) && this.keyResponseData != messageHeader.keyResponseData) || ((this.userAuthData == null || !this.userAuthData.equals(messageHeader.userAuthData)) && this.userAuthData != messageHeader.userAuthData) || ((this.userIdToken == null || !this.userIdToken.equals(messageHeader.userIdToken)) && this.userIdToken != messageHeader.userIdToken) || !this.serviceTokens.equals(messageHeader.serviceTokens) || ((this.peerMasterToken == null || !this.peerMasterToken.equals(messageHeader.peerMasterToken)) && this.peerMasterToken != messageHeader.peerMasterToken) || ((this.peerUserIdToken == null || !this.peerUserIdToken.equals(messageHeader.peerUserIdToken)) && this.peerUserIdToken != messageHeader.peerUserIdToken) || !this.peerServiceTokens.equals(messageHeader.peerServiceTokens)) {
                return false;
            }
        }
        return true;
    }
    
    public ICryptoContext getCryptoContext() {
        return this.messageCryptoContext;
    }
    
    public EntityAuthenticationData getEntityAuthenticationData() {
        return this.entityAuthData;
    }
    
    public Set<KeyRequestData> getKeyRequestData() {
        return this.keyRequestData;
    }
    
    public KeyResponseData getKeyResponseData() {
        return this.keyResponseData;
    }
    
    public MasterToken getMasterToken() {
        return this.masterToken;
    }
    
    public MessageCapabilities getMessageCapabilities() {
        return this.capabilities;
    }
    
    public long getMessageId() {
        return this.messageId;
    }
    
    public Long getNonReplayableId() {
        return this.nonReplayableId;
    }
    
    public MasterToken getPeerMasterToken() {
        return this.peerMasterToken;
    }
    
    public Set<ServiceToken> getPeerServiceTokens() {
        return this.peerServiceTokens;
    }
    
    public UserIdToken getPeerUserIdToken() {
        return this.peerUserIdToken;
    }
    
    public String getRecipient() {
        return this.recipient;
    }
    
    public String getSender() {
        return this.sender;
    }
    
    public Set<ServiceToken> getServiceTokens() {
        return this.serviceTokens;
    }
    
    public Date getTimestamp() {
        if (this.timestamp != null) {
            return new Date(this.timestamp * 1000L);
        }
        return null;
    }
    
    public MslUser getUser() {
        return this.user;
    }
    
    public UserAuthenticationData getUserAuthenticationData() {
        return this.userAuthData;
    }
    
    public UserIdToken getUserIdToken() {
        return this.userIdToken;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int n;
        if (this.masterToken != null) {
            n = this.masterToken.hashCode();
        }
        else {
            n = this.entityAuthData.hashCode();
        }
        int hashCode2;
        if (this.sender != null) {
            hashCode2 = this.sender.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.recipient != null) {
            hashCode3 = this.recipient.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        int hashCode4;
        if (this.timestamp != null) {
            hashCode4 = this.timestamp.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int hashCode5 = Long.valueOf(this.messageId).hashCode();
        int hashCode6;
        if (this.nonReplayableId != null) {
            hashCode6 = this.nonReplayableId.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        final int hashCode7 = Boolean.valueOf(this.renewable).hashCode();
        final int hashCode8 = Boolean.valueOf(this.handshake).hashCode();
        int hashCode9;
        if (this.capabilities != null) {
            hashCode9 = this.capabilities.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        final int hashCode10 = this.keyRequestData.hashCode();
        int hashCode11;
        if (this.keyResponseData != null) {
            hashCode11 = this.keyResponseData.hashCode();
        }
        else {
            hashCode11 = 0;
        }
        int hashCode12;
        if (this.userAuthData != null) {
            hashCode12 = this.userAuthData.hashCode();
        }
        else {
            hashCode12 = 0;
        }
        int hashCode13;
        if (this.userIdToken != null) {
            hashCode13 = this.userIdToken.hashCode();
        }
        else {
            hashCode13 = 0;
        }
        final int hashCode14 = this.serviceTokens.hashCode();
        int hashCode15;
        if (this.peerMasterToken != null) {
            hashCode15 = this.peerMasterToken.hashCode();
        }
        else {
            hashCode15 = 0;
        }
        if (this.peerUserIdToken != null) {
            hashCode = this.peerUserIdToken.hashCode();
        }
        return hashCode15 ^ (hashCode14 ^ (hashCode13 ^ (hashCode10 ^ (hashCode9 ^ (hashCode8 ^ (hashCode6 ^ (hashCode5 ^ (hashCode4 ^ (hashCode2 ^ n ^ hashCode3))) ^ hashCode7))) ^ hashCode11 ^ hashCode12))) ^ hashCode ^ this.peerServiceTokens.hashCode();
    }
    
    public boolean isDecrypted() {
        return this.plaintext != null;
    }
    
    public boolean isEncrypting() {
        return this.masterToken != null || this.entityAuthData.getScheme().encrypts();
    }
    
    public boolean isHandshake() {
        return this.handshake;
    }
    
    public boolean isRenewable() {
        return this.renewable;
    }
    
    public boolean isVerified() {
        return this.verified;
    }
    
    @Override
    public String toJSONString() {
        try {
            final JSONObject jsonObject = new JSONObject();
            if (this.masterToken != null) {
                jsonObject.put("mastertoken", this.masterToken);
            }
            else {
                jsonObject.put("entityauthdata", this.entityAuthData);
            }
            jsonObject.put("headerdata", Base64.encode(this.headerdata));
            jsonObject.put("signature", Base64.encode(this.signature));
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
}
