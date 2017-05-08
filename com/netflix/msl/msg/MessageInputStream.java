// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.tokens.MslUser;
import java.util.Iterator;
import com.netflix.msl.keyx.KeyExchangeFactory;
import com.netflix.msl.keyx.KeyExchangeScheme;
import com.netflix.msl.keyx.KeyResponseData;
import com.netflix.msl.MslInternalException;
import java.util.Arrays;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.crypto.SessionCryptoContext;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.TokenFactory;
import com.netflix.msl.tokens.MasterToken;
import java.util.Date;
import com.netflix.msl.MslUserIdTokenException;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslException;
import com.netflix.msl.MslMessageException;
import com.netflix.android.org.json.JSONObject;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import java.nio.charset.Charset;
import com.netflix.msl.util.MslContext;
import com.netflix.android.org.json.JSONTokener;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.io.ByteArrayInputStream;
import com.netflix.msl.crypto.ICryptoContext;
import java.io.InputStream;

public class MessageInputStream extends InputStream
{
    private boolean closeSource;
    private final ICryptoContext cryptoContext;
    private ByteArrayInputStream currentPayload;
    private boolean eom;
    private Boolean handshake;
    private final Header header;
    private final ICryptoContext keyxCryptoContext;
    private ListIterator<ByteArrayInputStream> payloadIterator;
    private long payloadSequenceNumber;
    private final List<ByteArrayInputStream> payloads;
    private IOException readException;
    private final InputStream source;
    private final JSONTokener tokener;
    
    public MessageInputStream(final MslContext mslContext, final InputStream source, final Charset charset, final Set<KeyRequestData> set, final Map<String, ICryptoContext> map) {
        this.payloadSequenceNumber = 1L;
        this.eom = false;
        this.handshake = null;
        this.closeSource = false;
        this.payloads = new LinkedList<ByteArrayInputStream>();
        this.payloadIterator = null;
        this.currentPayload = null;
        this.readException = null;
        this.source = source;
        this.tokener = new JSONTokener(new InputStreamReader(source, charset));
        try {
            if (!this.tokener.more()) {
                throw new MslEncodingException(MslError.MESSAGE_DATA_MISSING);
            }
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "header", ex);
        }
        final Object nextValue = this.tokener.nextValue();
        if (!(nextValue instanceof JSONObject)) {
            throw new MslEncodingException(MslError.MESSAGE_FORMAT_ERROR);
        }
        MessageHeader messageHeader = null;
    Label_0320_Outer:
        while (true) {
            this.header = Header.parseHeader(mslContext, (JSONObject)nextValue, map);
            while (true) {
            Label_0564:
                while (true) {
                    try {
                        if (this.header instanceof ErrorHeader) {
                            this.keyxCryptoContext = null;
                            this.cryptoContext = null;
                            return;
                        }
                        messageHeader = (MessageHeader)this.header;
                        this.keyxCryptoContext = getKeyxCryptoContext(mslContext, messageHeader, set);
                        if (mslContext.isPeerToPeer() || this.keyxCryptoContext == null) {
                            this.cryptoContext = messageHeader.getCryptoContext();
                            if (messageHeader.isHandshake() && (!messageHeader.isRenewable() || messageHeader.getKeyRequestData().isEmpty())) {
                                throw new MslMessageException(MslError.HANDSHAKE_DATA_MISSING, messageHeader.toJSONString());
                            }
                            break;
                        }
                    }
                    catch (MslException ex2) {
                        if (this.header instanceof MessageHeader) {
                            final MessageHeader messageHeader2 = (MessageHeader)this.header;
                            ex2.setMasterToken(messageHeader2.getMasterToken());
                            ex2.setEntityAuthenticationData(messageHeader2.getEntityAuthenticationData());
                            ex2.setUserIdToken(messageHeader2.getUserIdToken());
                            ex2.setUserAuthenticationData(messageHeader2.getUserAuthenticationData());
                            ex2.setMessageId(messageHeader2.getMessageId());
                            throw ex2;
                        }
                        break Label_0564;
                    }
                    this.cryptoContext = this.keyxCryptoContext;
                    continue Label_0320_Outer;
                }
                final ErrorHeader errorHeader = (ErrorHeader)this.header;
                final MslException ex2;
                ex2.setEntityAuthenticationData(errorHeader.getEntityAuthenticationData());
                ex2.setMessageId(errorHeader.getMessageId());
                continue;
            }
        }
        final MasterToken masterToken = messageHeader.getMasterToken();
        if (masterToken != null && (mslContext.isPeerToPeer() || masterToken.isVerified())) {
            final TokenFactory tokenFactory = mslContext.getTokenFactory();
            final MslError masterTokenRevoked = tokenFactory.isMasterTokenRevoked(mslContext, masterToken);
            if (masterTokenRevoked != null) {
                throw new MslMasterTokenException(masterTokenRevoked, masterToken);
            }
            final UserIdToken userIdToken = messageHeader.getUserIdToken();
            if (userIdToken != null) {
                final MslError userIdTokenRevoked = tokenFactory.isUserIdTokenRevoked(mslContext, masterToken, userIdToken);
                if (userIdTokenRevoked != null) {
                    throw new MslUserIdTokenException(userIdTokenRevoked, userIdToken);
                }
            }
            if (masterToken.isExpired(null)) {
                if (!messageHeader.isRenewable() || messageHeader.getKeyRequestData().isEmpty()) {
                    throw new MslMessageException(MslError.MESSAGE_EXPIRED, messageHeader.toJSONString());
                }
                final MslError masterTokenRenewable = tokenFactory.isMasterTokenRenewable(mslContext, masterToken);
                if (masterTokenRenewable != null) {
                    throw new MslMessageException(masterTokenRenewable, "Master token is expired and not renewable.");
                }
            }
        }
        final Long nonReplayableId = messageHeader.getNonReplayableId();
        if (nonReplayableId != null) {
            if (masterToken == null) {
                throw new MslMessageException(MslError.INCOMPLETE_NONREPLAYABLE_MESSAGE, messageHeader.toJSONString());
            }
            final MslError acceptNonReplayableId = mslContext.getTokenFactory().acceptNonReplayableId(mslContext, masterToken, nonReplayableId);
            if (acceptNonReplayableId != null) {
                throw new MslMessageException(acceptNonReplayableId, messageHeader.toJSONString());
            }
        }
    }
    
    private static ICryptoContext getKeyxCryptoContext(final MslContext mslContext, final MessageHeader messageHeader, final Set<KeyRequestData> set) {
        final Throwable t = null;
        final MasterToken masterToken = messageHeader.getMasterToken();
        final KeyResponseData keyResponseData = messageHeader.getKeyResponseData();
        if (keyResponseData == null) {
            return null;
        }
        final MasterToken masterToken2 = keyResponseData.getMasterToken();
        if (masterToken2.isDecrypted()) {
            return new SessionCryptoContext(mslContext, masterToken2);
        }
        final KeyExchangeScheme keyExchangeScheme = keyResponseData.getKeyExchangeScheme();
        final KeyExchangeFactory keyExchangeFactory = mslContext.getKeyExchangeFactory(keyExchangeScheme);
        if (keyExchangeFactory == null) {
            throw new MslKeyExchangeException(MslError.KEYX_FACTORY_NOT_FOUND, keyExchangeScheme.name());
        }
        final Iterator<KeyRequestData> iterator = set.iterator();
        Throwable t2 = t;
        while (iterator.hasNext()) {
            final KeyRequestData keyRequestData = iterator.next();
            if (keyExchangeScheme.equals(keyRequestData.getKeyExchangeScheme())) {
                try {
                    return keyExchangeFactory.getCryptoContext(mslContext, keyRequestData, keyResponseData, masterToken);
                }
                catch (MslKeyExchangeException ex) {
                    t2 = ex;
                    if (!iterator.hasNext()) {
                        throw ex;
                    }
                    continue;
                }
                catch (MslEncodingException ex2) {
                    t2 = ex2;
                    if (!iterator.hasNext()) {
                        throw ex2;
                    }
                    continue;
                }
                catch (MslMasterTokenException ex3) {
                    t2 = ex3;
                    if (!iterator.hasNext()) {
                        throw ex3;
                    }
                    continue;
                }
                catch (MslEntityAuthException ex4) {
                    t2 = ex4;
                    if (!iterator.hasNext()) {
                        throw ex4;
                    }
                    continue;
                }
            }
        }
        if (t2 == null) {
            throw new MslKeyExchangeException(MslError.KEYX_RESPONSE_REQUEST_MISMATCH, Arrays.toString(set.toArray()));
        }
        if (t2 instanceof MslKeyExchangeException) {
            throw (MslKeyExchangeException)t2;
        }
        if (t2 instanceof MslEncodingException) {
            throw (MslEncodingException)t2;
        }
        if (t2 instanceof MslMasterTokenException) {
            throw (MslMasterTokenException)t2;
        }
        if (t2 instanceof MslEntityAuthException) {
            throw (MslEntityAuthException)t2;
        }
        throw new MslInternalException("Unexpected exception caught during key exchange.", t2);
    }
    
    @Override
    public int available() {
        int n;
        if (this.currentPayload == null) {
            n = 0;
        }
        else {
            int available = this.currentPayload.available();
            final int index = this.payloads.indexOf(this.currentPayload);
            n = available;
            if (index != -1) {
                n = available;
                if (index < this.payloads.size() - 1) {
                    final ListIterator<ByteArrayInputStream> listIterator = this.payloads.listIterator(index + 1);
                    while (listIterator.hasNext()) {
                        available += ((ByteArrayInputStream)listIterator.next()).available();
                    }
                    return available;
                }
            }
        }
        return n;
    }
    
    @Override
    public void close() {
        if (this.closeSource) {
            this.source.close();
        }
        else {
            try {
                if (!this.isHandshake() && this.getMessageHeader() != null) {
                    while (this.nextData() != null) {}
                }
            }
            catch (MslException ex) {}
        }
    }
    
    public void closeSource(final boolean closeSource) {
        this.closeSource = closeSource;
    }
    
    @Override
    protected void finalize() {
        super.finalize();
    }
    
    public ErrorHeader getErrorHeader() {
        if (this.header instanceof ErrorHeader) {
            return (ErrorHeader)this.header;
        }
        return null;
    }
    
    public String getIdentity() {
        final MessageHeader messageHeader = this.getMessageHeader();
        if (messageHeader == null) {
            return this.getErrorHeader().getEntityAuthenticationData().getIdentity();
        }
        final MasterToken masterToken = messageHeader.getMasterToken();
        if (masterToken != null) {
            return masterToken.getIdentity();
        }
        return messageHeader.getEntityAuthenticationData().getIdentity();
    }
    
    public ICryptoContext getKeyExchangeCryptoContext() {
        return this.keyxCryptoContext;
    }
    
    public MessageHeader getMessageHeader() {
        if (this.header instanceof MessageHeader) {
            return (MessageHeader)this.header;
        }
        return null;
    }
    
    public ICryptoContext getPayloadCryptoContext() {
        return this.cryptoContext;
    }
    
    public MslUser getUser() {
        final MessageHeader messageHeader = this.getMessageHeader();
        if (messageHeader == null) {
            return null;
        }
        return messageHeader.getUser();
    }
    
    public boolean isHandshake() {
        final MessageHeader messageHeader = this.getMessageHeader();
        if (messageHeader == null) {
            return false;
        }
        if (messageHeader.isHandshake()) {
            return true;
        }
        Label_0049: {
            if (this.handshake != null) {
                break Label_0049;
            }
            try {
                this.currentPayload = this.nextData();
                if (this.currentPayload == null) {
                    this.handshake = Boolean.FALSE;
                }
                return this.handshake;
            }
            catch (MslException ex) {
                this.readException = new IOException("Error reading the payload chunk.", ex);
                throw ex;
            }
        }
    }
    
    @Override
    public void mark(final int n) {
        if (this.currentPayload != null) {
            this.payloadIterator = null;
            while (this.payloads.size() > 0 && !this.payloads.get(0).equals(this.currentPayload)) {
                this.payloads.remove(0);
            }
            this.payloadIterator = this.payloads.listIterator();
            (this.currentPayload = this.payloadIterator.next()).mark(n);
            return;
        }
        this.payloadIterator = null;
        this.payloads.clear();
    }
    
    @Override
    public boolean markSupported() {
        return true;
    }
    
    protected ByteArrayInputStream nextData() {
        final MessageHeader messageHeader = this.getMessageHeader();
        if (messageHeader == null) {
            throw new MslInternalException("Read attempted with error message.");
        }
        if (this.payloadIterator != null && this.payloadIterator.hasNext()) {
            return this.payloadIterator.next();
        }
        final JSONObject nextJsonObject = this.nextJsonObject();
        if (nextJsonObject == null) {
            return null;
        }
        final PayloadChunk payloadChunk = new PayloadChunk(nextJsonObject, this.cryptoContext);
        final MasterToken masterToken = messageHeader.getMasterToken();
        final EntityAuthenticationData entityAuthenticationData = messageHeader.getEntityAuthenticationData();
        final UserIdToken userIdToken = messageHeader.getUserIdToken();
        final UserAuthenticationData userAuthenticationData = messageHeader.getUserAuthenticationData();
        if (payloadChunk.getMessageId() != messageHeader.getMessageId()) {
            throw new MslMessageException(MslError.PAYLOAD_MESSAGE_ID_MISMATCH, "payload mid " + payloadChunk.getMessageId() + " header mid " + messageHeader.getMessageId()).setMasterToken(masterToken).setEntityAuthenticationData(entityAuthenticationData).setUserIdToken(userIdToken).setUserAuthenticationData(userAuthenticationData);
        }
        if (payloadChunk.getSequenceNumber() != this.payloadSequenceNumber) {
            throw new MslMessageException(MslError.PAYLOAD_SEQUENCE_NUMBER_MISMATCH, "payload seqno " + payloadChunk.getSequenceNumber() + " expected seqno " + this.payloadSequenceNumber).setMasterToken(masterToken).setEntityAuthenticationData(entityAuthenticationData).setUserIdToken(userIdToken).setUserAuthenticationData(userAuthenticationData);
        }
        ++this.payloadSequenceNumber;
        if (this.handshake == null) {
            this.handshake = (messageHeader.isRenewable() && !messageHeader.getKeyRequestData().isEmpty() && payloadChunk.isEndOfMessage() && payloadChunk.getData().length == 0);
        }
        if (payloadChunk.isEndOfMessage()) {
            this.eom = true;
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payloadChunk.getData());
        this.payloads.add(byteArrayInputStream);
        this.payloadIterator = null;
        return byteArrayInputStream;
    }
    
    protected JSONObject nextJsonObject() {
        if (this.getMessageHeader() == null) {
            throw new MslInternalException("Read attempted with error message.");
        }
        if (this.eom) {
            return null;
        }
        try {
            if (!this.tokener.more()) {
                this.eom = true;
                return null;
            }
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "payloadchunk", ex);
        }
        final Object nextValue = this.tokener.nextValue();
        if (!(nextValue instanceof JSONObject)) {
            throw new MslEncodingException(MslError.MESSAGE_FORMAT_ERROR);
        }
        return (JSONObject)nextValue;
    }
    
    @Override
    public int read() {
        final byte[] array = { 0 };
        if (this.read(array) == -1) {
            return -1;
        }
        return array[0];
    }
    
    @Override
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        if (this.readException != null) {
            final IOException readException = this.readException;
            this.readException = null;
            throw readException;
        }
        while (true) {
            try {
                if (this.isHandshake()) {
                    return -1;
                }
            }
            catch (MslException ex) {
                this.readException = null;
                throw new IOException("Error reading the payload chunk.", ex);
            }
            int n3 = 0;
            while (true) {
                Label_0125: {
                    if (n3 >= n2) {
                        break Label_0125;
                    }
                    int read;
                    if (this.currentPayload != null) {
                        read = this.currentPayload.read(array, n + n3, n2 - n3);
                    }
                    else {
                        read = -1;
                    }
                    if (read != -1) {
                        n3 += read;
                        continue;
                    }
                    try {
                        this.currentPayload = this.nextData();
                        if (this.currentPayload != null) {
                            continue;
                        }
                        if (n3 != 0 || n2 <= 0) {
                            return n3;
                        }
                        return -1;
                    }
                    catch (MslException ex2) {
                        final IOException readException2 = new IOException("Error reading the payload chunk.", ex2);
                        if (n3 > 0) {
                            this.readException = readException2;
                            return n3;
                        }
                        throw readException2;
                    }
                }
            }
        }
    }
    
    @Override
    public void reset() {
        final Iterator<ByteArrayInputStream> iterator = this.payloads.iterator();
        while (iterator.hasNext()) {
            iterator.next().reset();
        }
        this.payloadIterator = this.payloads.listIterator();
        if (this.payloadIterator.hasNext()) {
            this.currentPayload = this.payloadIterator.next();
            return;
        }
        this.currentPayload = null;
    }
    
    @Override
    public long skip(final long n) {
        int n2 = 0;
        while (true) {
            Label_0071: {
                if (n2 >= n) {
                    break Label_0071;
                }
                long skip;
                if (this.currentPayload != null) {
                    skip = this.currentPayload.skip(n - n2);
                }
                else {
                    skip = 0L;
                }
                if (skip != 0L) {
                    n2 += (int)skip;
                    continue;
                }
                try {
                    this.currentPayload = this.nextData();
                    if (this.currentPayload == null) {
                        return n2;
                    }
                    continue;
                }
                catch (MslInternalException ex) {
                    throw new IOException("Cannot skip data off an error message.", ex);
                }
                catch (MslException ex2) {
                    throw new IOException("Error skipping in the payload chunk.", ex2);
                }
            }
        }
    }
}
