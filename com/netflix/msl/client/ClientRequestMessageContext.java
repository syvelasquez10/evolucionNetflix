// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import com.netflix.msl.msg.MessageOutputStream;
import com.netflix.msl.msg.MessageServiceTokenBuilder;
import com.netflix.msl.msg.MessageContext$ReauthCode;
import com.netflix.msl.tokens.MslUser;
import java.util.Collection;
import java.util.HashSet;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import java.util.Collections;
import com.netflix.msl.crypto.ICryptoContext;
import java.util.Map;
import java.util.Arrays;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.msg.MessageDebugContext;
import com.netflix.msl.msg.MessageContext;

public final class ClientRequestMessageContext implements MessageContext
{
    private static final String USER_ID = "local-userid";
    private final MessageDebugContext debugContext;
    private final boolean encrypted;
    private final boolean integrityProtected;
    private final KeyRequestDataProvider keyRequestDataProvider;
    private final boolean nonReplayable;
    private final byte[] payload;
    private final String recipient;
    private final UserAuthenticationData userAuthData;
    private final String userId;
    
    private ClientRequestMessageContext(final Boolean b, final Boolean b2, final Boolean b3, final String userId, final MessageDebugContext debugContext, final UserAuthenticationData userAuthData, final byte[] payload, final String recipient, KeyRequestDataProvider keyRequestDataProvider) {
        final boolean b4 = false;
        this.encrypted = (b == null || b);
        this.integrityProtected = (b2 == null || b2);
        boolean nonReplayable = false;
        Label_0065: {
            if (b3 != null) {
                nonReplayable = b4;
                if (!b3) {
                    break Label_0065;
                }
            }
            nonReplayable = true;
        }
        this.nonReplayable = nonReplayable;
        this.userId = userId;
        this.debugContext = debugContext;
        this.userAuthData = userAuthData;
        this.payload = payload;
        this.recipient = recipient;
        if (keyRequestDataProvider == null) {
            keyRequestDataProvider = new AsymmetricWrappedKeyRequestProvider();
        }
        this.keyRequestDataProvider = keyRequestDataProvider;
    }
    
    public static ClientRequestMessageContext$ClientRequestMessageContextBuilder builder() {
        return new ClientRequestMessageContext$ClientRequestMessageContextBuilder();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ClientRequestMessageContext)) {
                return false;
            }
            final ClientRequestMessageContext clientRequestMessageContext = (ClientRequestMessageContext)o;
            if (this.isEncrypted() != clientRequestMessageContext.isEncrypted()) {
                return false;
            }
            if (this.isIntegrityProtected() != clientRequestMessageContext.isIntegrityProtected()) {
                return false;
            }
            if (this.isNonReplayable() != clientRequestMessageContext.isNonReplayable()) {
                return false;
            }
            final String recipient = this.getRecipient();
            final String recipient2 = clientRequestMessageContext.getRecipient();
            Label_0088: {
                if (recipient == null) {
                    if (recipient2 == null) {
                        break Label_0088;
                    }
                }
                else if (recipient.equals(recipient2)) {
                    break Label_0088;
                }
                return false;
            }
            final MessageDebugContext debugContext = this.getDebugContext();
            final MessageDebugContext debugContext2 = clientRequestMessageContext.getDebugContext();
            Label_0116: {
                if (debugContext == null) {
                    if (debugContext2 == null) {
                        break Label_0116;
                    }
                }
                else if (debugContext.equals(debugContext2)) {
                    break Label_0116;
                }
                return false;
            }
            final String userId = this.getUserId();
            final String userId2 = clientRequestMessageContext.getUserId();
            Label_0144: {
                if (userId == null) {
                    if (userId2 == null) {
                        break Label_0144;
                    }
                }
                else if (userId.equals(userId2)) {
                    break Label_0144;
                }
                return false;
            }
            final UserAuthenticationData userAuthData = this.getUserAuthData();
            final UserAuthenticationData userAuthData2 = clientRequestMessageContext.getUserAuthData();
            Label_0172: {
                if (userAuthData == null) {
                    if (userAuthData2 == null) {
                        break Label_0172;
                    }
                }
                else if (userAuthData.equals(userAuthData2)) {
                    break Label_0172;
                }
                return false;
            }
            if (!Arrays.equals(this.getPayload(), clientRequestMessageContext.getPayload())) {
                return false;
            }
            final KeyRequestDataProvider keyRequestDataProvider = this.getKeyRequestDataProvider();
            final KeyRequestDataProvider keyRequestDataProvider2 = clientRequestMessageContext.getKeyRequestDataProvider();
            if (keyRequestDataProvider == null) {
                if (keyRequestDataProvider2 == null) {
                    return true;
                }
            }
            else if (keyRequestDataProvider.equals(keyRequestDataProvider2)) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public Map<String, ICryptoContext> getCryptoContexts() {
        return Collections.emptyMap();
    }
    
    @Override
    public MessageDebugContext getDebugContext() {
        return this.debugContext;
    }
    
    @Override
    public Set<KeyRequestData> getKeyRequestData() {
        return new HashSet<KeyRequestData>(Arrays.asList(this.keyRequestDataProvider.get()));
    }
    
    public KeyRequestDataProvider getKeyRequestDataProvider() {
        return this.keyRequestDataProvider;
    }
    
    public byte[] getPayload() {
        return this.payload;
    }
    
    @Override
    public String getRecipient() {
        return null;
    }
    
    @Override
    public MslUser getUser() {
        return null;
    }
    
    public UserAuthenticationData getUserAuthData() {
        return this.userAuthData;
    }
    
    @Override
    public UserAuthenticationData getUserAuthData(final MessageContext$ReauthCode messageContext$ReauthCode, final boolean b, final boolean b2) {
        if (messageContext$ReauthCode == null && b2) {
            return this.userAuthData;
        }
        return null;
    }
    
    @Override
    public String getUserId() {
        return this.userId;
    }
    
    @Override
    public int hashCode() {
        int n = 79;
        int hashCode = 43;
        int n2;
        if (this.isEncrypted()) {
            n2 = 79;
        }
        else {
            n2 = 97;
        }
        int n3;
        if (this.isIntegrityProtected()) {
            n3 = 79;
        }
        else {
            n3 = 97;
        }
        if (!this.isNonReplayable()) {
            n = 97;
        }
        final String recipient = this.getRecipient();
        int hashCode2;
        if (recipient == null) {
            hashCode2 = 43;
        }
        else {
            hashCode2 = recipient.hashCode();
        }
        final MessageDebugContext debugContext = this.getDebugContext();
        int hashCode3;
        if (debugContext == null) {
            hashCode3 = 43;
        }
        else {
            hashCode3 = debugContext.hashCode();
        }
        final String userId = this.getUserId();
        int hashCode4;
        if (userId == null) {
            hashCode4 = 43;
        }
        else {
            hashCode4 = userId.hashCode();
        }
        final UserAuthenticationData userAuthData = this.getUserAuthData();
        int hashCode5;
        if (userAuthData == null) {
            hashCode5 = 43;
        }
        else {
            hashCode5 = userAuthData.hashCode();
        }
        final int hashCode6 = Arrays.hashCode(this.getPayload());
        final KeyRequestDataProvider keyRequestDataProvider = this.getKeyRequestDataProvider();
        if (keyRequestDataProvider != null) {
            hashCode = keyRequestDataProvider.hashCode();
        }
        return ((hashCode5 + (hashCode4 + (hashCode3 + (hashCode2 + ((n3 + (n2 + 59) * 59) * 59 + n) * 59) * 59) * 59) * 59) * 59 + hashCode6) * 59 + hashCode;
    }
    
    @Override
    public boolean isEncrypted() {
        return this.encrypted;
    }
    
    @Override
    public boolean isIntegrityProtected() {
        return this.integrityProtected;
    }
    
    @Override
    public boolean isNonReplayable() {
        return this.nonReplayable;
    }
    
    @Override
    public boolean isRequestingTokens() {
        return false;
    }
    
    @Override
    public String toString() {
        return "ClientRequestMessageContext(encrypted=" + this.isEncrypted() + ", integrityProtected=" + this.isIntegrityProtected() + ", nonReplayable=" + this.isNonReplayable() + ", recipient=" + this.getRecipient() + ", debugContext=" + this.getDebugContext() + ", userId=" + this.getUserId() + ", userAuthData=" + this.getUserAuthData() + ", payload=" + Arrays.toString(this.getPayload()) + ", keyRequestDataProvider=" + this.getKeyRequestDataProvider() + ")";
    }
    
    @Override
    public void updateServiceTokens(final MessageServiceTokenBuilder messageServiceTokenBuilder, final boolean b) {
    }
    
    @Override
    public void write(final MessageOutputStream messageOutputStream) {
        if (this.payload != null) {
            messageOutputStream.write(this.payload);
            messageOutputStream.flush();
            messageOutputStream.close();
        }
    }
}
