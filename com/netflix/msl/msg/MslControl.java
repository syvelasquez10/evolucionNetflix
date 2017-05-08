// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.util.concurrent.locks.Lock;
import com.netflix.msl.io.Url;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import com.netflix.msl.keyx.KeyExchangeFactory$KeyExchangeData;
import java.util.Iterator;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.tokens.ServiceToken;
import com.netflix.msl.keyx.KeyResponseData;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.crypto.ICryptoContext;
import java.util.Map;
import com.netflix.msl.MslMessageException;
import com.netflix.msl.MslError;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import com.netflix.msl.MslConstants;
import java.util.Collection;
import java.util.HashSet;
import java.io.IOException;
import java.io.StringWriter;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileLockInterruptionException;
import java.net.SocketTimeoutException;
import java.io.InterruptedIOException;
import com.netflix.msl.MslException;
import com.netflix.msl.util.MslStore;
import com.netflix.msl.MslConstants$ResponseCode;
import java.util.List;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.tokens.UserIdToken;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.io.OutputStream;
import java.io.InputStream;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslInternalException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.netflix.android.org.json.JSONObject;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.BlockingQueue;
import com.netflix.msl.util.MslContext;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import com.netflix.msl.tokens.MasterToken;

public class MslControl
{
    private final MasterToken NULL_MASTER_TOKEN;
    private final ExecutorService executor;
    private FilterStreamFactory filterFactory;
    private final ConcurrentHashMap<MslControl$MslContextMasterTokenKey, ReadWriteLock> masterTokenLocks;
    private final ErrorMessageRegistry messageRegistry;
    private final ConcurrentHashMap<MslContext, BlockingQueue<MasterToken>> renewingContexts;
    private final MessageStreamFactory streamFactory;
    
    public MslControl(final int n) {
        this(n, null, null);
    }
    
    public MslControl(final int n, MessageStreamFactory streamFactory, ErrorMessageRegistry messageRegistry) {
        this.filterFactory = null;
        this.renewingContexts = new ConcurrentHashMap<MslContext, BlockingQueue<MasterToken>>();
        this.masterTokenLocks = new ConcurrentHashMap<MslControl$MslContextMasterTokenKey, ReadWriteLock>();
        if (n < 0) {
            throw new IllegalArgumentException("Number of threads must be non-negative.");
        }
        Label_0156: {
            if (streamFactory == null) {
                break Label_0156;
            }
        Label_0058_Outer:
            while (true) {
                this.streamFactory = streamFactory;
                Label_0167: {
                    if (messageRegistry == null) {
                        break Label_0167;
                    }
                Label_0075_Outer:
                    while (true) {
                        this.messageRegistry = messageRegistry;
                        Label_0179: {
                            if (n <= 0) {
                                break Label_0179;
                            }
                            this.executor = Executors.newFixedThreadPool(n);
                            try {
                                while (true) {
                                    final MslControl$DummyMslContext mslControl$DummyMslContext = new MslControl$DummyMslContext(null);
                                    final byte[] array = new byte[16];
                                    this.NULL_MASTER_TOKEN = new MasterToken(mslControl$DummyMslContext, new Date(), new Date(), 1L, 1L, new JSONObject(), "dummy", new SecretKeySpec(array, "AES"), new SecretKeySpec(array, "HmacSHA256"));
                                    return;
                                    streamFactory = new MessageStreamFactory();
                                    continue Label_0058_Outer;
                                    this.executor = new MslControl$SynchronousExecutor(null);
                                    continue;
                                }
                                messageRegistry = new MslControl$DummyMessageRegistry(null);
                                continue Label_0075_Outer;
                            }
                            catch (MslEncodingException ex) {
                                throw new MslInternalException("Unexpected exception when constructing dummy master token.", ex);
                            }
                            catch (MslCryptoException ex2) {
                                throw new MslInternalException("Unexpected exception when constructing dummy master token.", ex2);
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    private boolean acquireRenewalLock(final MslContext mslContext, final MessageContext messageContext, final BlockingQueue<MasterToken> blockingQueue, final MessageBuilder messageBuilder, final long n) {
        final MasterToken masterToken = messageBuilder.getMasterToken();
        final UserIdToken userIdToken = messageBuilder.getUserIdToken();
        final String userId = messageContext.getUserId();
        final Date remoteTime = mslContext.getRemoteTime();
        UserIdToken userIdToken2 = null;
        MasterToken masterToken2 = null;
        Label_0443: {
            if ((!messageContext.isEncrypted() || messageBuilder.willEncryptPayloads()) && (!messageContext.isIntegrityProtected() || messageBuilder.willIntegrityProtectPayloads()) && !messageBuilder.isRenewable() && (masterToken != null || !messageContext.isNonReplayable()) && (masterToken == null || !masterToken.isExpired(remoteTime)) && (userIdToken != null || userId == null || (messageBuilder.willEncryptHeader() && messageBuilder.willIntegrityProtectHeader()))) {
                userIdToken2 = userIdToken;
                masterToken2 = masterToken;
                if (!messageContext.isRequestingTokens()) {
                    break Label_0443;
                }
                if (masterToken != null) {
                    userIdToken2 = userIdToken;
                    masterToken2 = masterToken;
                    if (userId == null) {
                        break Label_0443;
                    }
                    userIdToken2 = userIdToken;
                    masterToken2 = masterToken;
                    if (userIdToken != null) {
                        break Label_0443;
                    }
                }
            }
            MasterToken masterToken3 = masterToken;
            UserIdToken userIdToken3 = userIdToken;
            while (true) {
                final BlockingQueue<MasterToken> blockingQueue2 = this.renewingContexts.putIfAbsent(mslContext, blockingQueue);
                if (blockingQueue2 == null) {
                    return true;
                }
                final MasterToken masterToken4 = blockingQueue2.poll(n, TimeUnit.MILLISECONDS);
                if (masterToken4 == null) {
                    throw new TimeoutException("acquireRenewalLock timed out.");
                }
                blockingQueue2.add(masterToken4);
                if (masterToken4 == this.NULL_MASTER_TOKEN) {
                    continue;
                }
                MasterToken newestMasterToken;
                if (masterToken3 == null || !masterToken3.equals(masterToken4)) {
                    this.releaseMasterToken(mslContext, masterToken3);
                    newestMasterToken = this.getNewestMasterToken(mslContext);
                    if (newestMasterToken == null) {
                        masterToken3 = newestMasterToken;
                        continue;
                    }
                }
                else {
                    newestMasterToken = masterToken3;
                }
                if ((userId != null && userIdToken3 == null) || (userIdToken3 != null && !userIdToken3.isBoundTo(newestMasterToken))) {
                    userIdToken3 = mslContext.getMslStore().getUserIdToken(userId);
                    if (userIdToken3 == null || !userIdToken3.isBoundTo(newestMasterToken)) {
                        userIdToken3 = null;
                    }
                }
                messageBuilder.setAuthTokens(newestMasterToken, userIdToken3);
                if (newestMasterToken.isExpired(mslContext.getRemoteTime())) {
                    masterToken3 = newestMasterToken;
                }
                else if (messageBuilder.isRenewable() && newestMasterToken.equals(masterToken3)) {
                    masterToken3 = newestMasterToken;
                }
                else {
                    userIdToken2 = userIdToken3;
                    masterToken2 = newestMasterToken;
                    if (!messageContext.isRequestingTokens()) {
                        break;
                    }
                    userIdToken2 = userIdToken3;
                    masterToken2 = newestMasterToken;
                    if (userIdToken3 != null) {
                        break;
                    }
                    masterToken3 = newestMasterToken;
                }
            }
        }
        final Date remoteTime2 = mslContext.getRemoteTime();
        return (masterToken2 == null || masterToken2.isRenewable(remoteTime2) || (userIdToken2 == null && messageContext.getUserId() != null) || (userIdToken2 != null && userIdToken2.isRenewable(remoteTime2))) && this.renewingContexts.putIfAbsent(mslContext, blockingQueue) == null;
    }
    
    private MslControl$ErrorResult buildErrorResponse(final MslContext mslContext, final MessageContext messageContext, final MslControl$SendResult mslControl$SendResult, final ErrorHeader errorHeader) {
        final UserIdToken userIdToken = null;
        final UserIdToken userIdToken2 = null;
        final MessageHeader messageHeader = mslControl$SendResult.request.getMessageHeader();
        final List<PayloadChunk> payloads = mslControl$SendResult.request.getPayloads();
        final MslConstants$ResponseCode errorCode = errorHeader.getErrorCode();
        Label_0207: {
            switch (MslControl$1.$SwitchMap$com$netflix$msl$MslConstants$ResponseCode[errorCode.ordinal()]) {
                case 5: {
                    break Label_0207;
                }
                case 3:
                case 4: {
                    try {
                        if (messageContext.getUserAuthData(MessageContext$ReauthCode.valueOf(errorCode), false, true) != null) {
                            final MasterToken newestMasterToken = this.getNewestMasterToken(mslContext);
                            final long incrementMessageId3 = MessageBuilder.incrementMessageId(errorHeader.getMessageId());
                            final MslControl$ResendMessageContext mslControl$ResendMessageContext3 = new MslControl$ResendMessageContext(payloads, messageContext);
                            final MessageBuilder request3 = MessageBuilder.createRequest(mslContext, newestMasterToken, null, mslControl$ResendMessageContext3.getRecipient(), incrementMessageId3);
                            if (mslContext.isPeerToPeer()) {
                                request3.setPeerAuthTokens(messageHeader.getPeerMasterToken(), messageHeader.getPeerUserIdToken());
                            }
                            request3.setNonReplayable(mslControl$ResendMessageContext3.isNonReplayable());
                            return new MslControl$ErrorResult(request3, mslControl$ResendMessageContext3);
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw new MslInternalException("Unsupported response code mapping onto user re-authentication codes.", ex2);
                    }
                }
                case 6: {
                    final long incrementMessageId2 = MessageBuilder.incrementMessageId(errorHeader.getMessageId());
                    final MslControl$ResendMessageContext mslControl$ResendMessageContext2 = new MslControl$ResendMessageContext(payloads, messageContext);
                    final MessageBuilder request2 = MessageBuilder.createRequest(mslContext, null, null, mslControl$ResendMessageContext2.getRecipient(), incrementMessageId2);
                    if (mslContext.isPeerToPeer()) {
                        request2.setPeerAuthTokens(messageHeader.getPeerMasterToken(), messageHeader.getPeerUserIdToken());
                    }
                    request2.setRenewable(true);
                    request2.setNonReplayable(mslControl$ResendMessageContext2.isNonReplayable());
                    return new MslControl$ErrorResult(request2, mslControl$ResendMessageContext2);
                }
                case 1:
                case 2: {
                    try {
                        if (mslContext.getEntityAuthenticationData(MslContext$ReauthCode.valueOf(errorCode)) != null) {
                            final long incrementMessageId = MessageBuilder.incrementMessageId(errorHeader.getMessageId());
                            final MslControl$ResendMessageContext mslControl$ResendMessageContext = new MslControl$ResendMessageContext(payloads, messageContext);
                            final MessageBuilder request = MessageBuilder.createRequest(mslContext, null, null, mslControl$ResendMessageContext.getRecipient(), incrementMessageId);
                            if (mslContext.isPeerToPeer()) {
                                request.setPeerAuthTokens(messageHeader.getPeerMasterToken(), messageHeader.getPeerUserIdToken());
                            }
                            request.setNonReplayable(mslControl$ResendMessageContext.isNonReplayable());
                            return new MslControl$ErrorResult(request, mslControl$ResendMessageContext);
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex) {
                        throw new MslInternalException("Unsupported response code mapping onto entity re-authentication codes.", ex);
                    }
                }
                case 7: {
                    final MasterToken newestMasterToken2 = this.getNewestMasterToken(mslContext);
                    UserIdToken userIdToken5;
                    if (newestMasterToken2 != null) {
                        final String userId = messageContext.getUserId();
                        final MslStore mslStore = mslContext.getMslStore();
                        UserIdToken userIdToken3;
                        if (userId != null) {
                            userIdToken3 = mslStore.getUserIdToken(userId);
                        }
                        else {
                            userIdToken3 = null;
                        }
                        UserIdToken userIdToken4 = userIdToken2;
                        if (userIdToken3 != null) {
                            userIdToken4 = userIdToken2;
                            if (userIdToken3.isBoundTo(newestMasterToken2)) {
                                userIdToken4 = userIdToken3;
                            }
                        }
                        userIdToken5 = userIdToken4;
                    }
                    else {
                        userIdToken5 = null;
                    }
                    final long incrementMessageId4 = MessageBuilder.incrementMessageId(errorHeader.getMessageId());
                    final MslControl$ResendMessageContext mslControl$ResendMessageContext4 = new MslControl$ResendMessageContext(payloads, messageContext);
                    final MessageBuilder request4 = MessageBuilder.createRequest(mslContext, newestMasterToken2, userIdToken5, mslControl$ResendMessageContext4.getRecipient(), incrementMessageId4);
                    if (mslContext.isPeerToPeer()) {
                        request4.setPeerAuthTokens(messageHeader.getPeerMasterToken(), messageHeader.getPeerUserIdToken());
                    }
                    final MasterToken masterToken = messageHeader.getMasterToken();
                    if (masterToken == null || masterToken.equals(newestMasterToken2)) {
                        request4.setRenewable(true);
                    }
                    request4.setNonReplayable(mslControl$ResendMessageContext4.isNonReplayable());
                    return new MslControl$ErrorResult(request4, mslControl$ResendMessageContext4);
                }
                case 8: {
                    final MasterToken newestMasterToken3 = this.getNewestMasterToken(mslContext);
                    UserIdToken userIdToken8;
                    if (newestMasterToken3 != null) {
                        final String userId2 = messageContext.getUserId();
                        final MslStore mslStore2 = mslContext.getMslStore();
                        UserIdToken userIdToken6;
                        if (userId2 != null) {
                            userIdToken6 = mslStore2.getUserIdToken(userId2);
                        }
                        else {
                            userIdToken6 = null;
                        }
                        UserIdToken userIdToken7 = userIdToken;
                        if (userIdToken6 != null) {
                            userIdToken7 = userIdToken;
                            if (userIdToken6.isBoundTo(newestMasterToken3)) {
                                userIdToken7 = userIdToken6;
                            }
                        }
                        userIdToken8 = userIdToken7;
                    }
                    else {
                        userIdToken8 = null;
                    }
                    final long incrementMessageId5 = MessageBuilder.incrementMessageId(errorHeader.getMessageId());
                    final MslControl$ResendMessageContext mslControl$ResendMessageContext5 = new MslControl$ResendMessageContext(payloads, messageContext);
                    final MessageBuilder request5 = MessageBuilder.createRequest(mslContext, newestMasterToken3, userIdToken8, mslControl$ResendMessageContext5.getRecipient(), incrementMessageId5);
                    if (mslContext.isPeerToPeer()) {
                        request5.setPeerAuthTokens(messageHeader.getPeerMasterToken(), messageHeader.getPeerUserIdToken());
                    }
                    request5.setNonReplayable(mslControl$ResendMessageContext5.isNonReplayable());
                    return new MslControl$ErrorResult(request5, mslControl$ResendMessageContext5);
                }
            }
        }
        return null;
    }
    
    private MessageBuilder buildRequest(final MslContext mslContext, final MessageContext messageContext) {
        final MslStore mslStore = mslContext.getMslStore();
        final MasterToken newestMasterToken = this.getNewestMasterToken(mslContext);
        Label_0089: {
            if (newestMasterToken == null) {
                break Label_0089;
            }
            final String userId = messageContext.getUserId();
            Label_0079: {
                if (userId == null) {
                    break Label_0079;
                }
                UserIdToken userIdToken = mslStore.getUserIdToken(userId);
            Label_0052_Outer:
                while (true) {
                    Label_0084: {
                        if (userIdToken == null || !userIdToken.isBoundTo(newestMasterToken)) {
                            break Label_0084;
                        }
                        try {
                            while (true) {
                                final MessageBuilder request = MessageBuilder.createRequest(mslContext, newestMasterToken, userIdToken, messageContext.getRecipient());
                                request.setNonReplayable(messageContext.isNonReplayable());
                                return request;
                                userIdToken = null;
                                continue;
                                userIdToken = null;
                                continue Label_0052_Outer;
                                userIdToken = null;
                                continue;
                            }
                        }
                        catch (MslException ex) {
                            this.releaseMasterToken(mslContext, newestMasterToken);
                            throw new MslInternalException("User ID token not bound to master token despite internal check.", ex);
                        }
                        catch (RuntimeException ex2) {
                            this.releaseMasterToken(mslContext, newestMasterToken);
                            throw ex2;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private MessageBuilder buildResponse(final MslContext mslContext, final MessageContext messageContext, final MessageHeader messageHeader) {
        final MessageBuilder response = MessageBuilder.createResponse(mslContext, messageHeader);
        response.setNonReplayable(messageContext.isNonReplayable());
        if (!mslContext.isPeerToPeer() && messageHeader.getKeyResponseData() == null) {
            return response;
        }
        final MasterToken newestMasterToken = this.getNewestMasterToken(mslContext);
        UserIdToken userIdToken;
        if (newestMasterToken != null) {
            final String userId = messageContext.getUserId();
            final MslStore mslStore = mslContext.getMslStore();
            if (userId != null) {
                userIdToken = mslStore.getUserIdToken(userId);
            }
            else {
                userIdToken = null;
            }
            if (userIdToken == null || !userIdToken.isBoundTo(newestMasterToken)) {
                userIdToken = null;
            }
        }
        else {
            userIdToken = null;
        }
        response.setAuthTokens(newestMasterToken, userIdToken);
        return response;
    }
    
    protected static boolean cancelled(Throwable cause) {
        if (!Thread.interrupted()) {
            while (cause != null) {
                if (cause instanceof InterruptedException || (cause instanceof InterruptedIOException && !(cause instanceof SocketTimeoutException)) || cause instanceof FileLockInterruptionException) {
                    return true;
                }
                if (cause instanceof ClosedByInterruptException) {
                    return true;
                }
                cause = cause.getCause();
            }
            return false;
        }
        return true;
    }
    
    private void cleanupContext(final MslContext mslContext, final MessageHeader messageHeader, final ErrorHeader errorHeader) {
        switch (MslControl$1.$SwitchMap$com$netflix$msl$MslConstants$ResponseCode[errorHeader.getErrorCode().ordinal()]) {
            case 1:
            case 2: {
                this.deleteMasterToken(mslContext, messageHeader.getMasterToken());
            }
            case 3:
            case 5: {
                final MasterToken masterToken = messageHeader.getMasterToken();
                final UserIdToken userIdToken = messageHeader.getUserIdToken();
                if (masterToken != null && userIdToken != null) {
                    mslContext.getMslStore().removeUserIdToken(userIdToken);
                    return;
                }
                break;
            }
        }
    }
    
    private void deleteMasterToken(final MslContext mslContext, final MasterToken masterToken) {
        if (masterToken == null) {
            return;
        }
        final MslControl$MslContextMasterTokenKey mslControl$MslContextMasterTokenKey = new MslControl$MslContextMasterTokenKey(mslContext, masterToken);
        Object o = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock reentrantReadWriteLock = this.masterTokenLocks.putIfAbsent(mslControl$MslContextMasterTokenKey, (ReentrantReadWriteLock)o);
        Label_0097: {
            if (reentrantReadWriteLock == null) {
                break Label_0097;
            }
            reentrantReadWriteLock.readLock().unlock();
            o = reentrantReadWriteLock.writeLock();
            while (true) {
                ((Lock)o).lockInterruptibly();
                try {
                    mslContext.getMslStore().removeCryptoContext(masterToken);
                    return;
                    o = ((ReadWriteLock)o).writeLock();
                }
                finally {
                    this.masterTokenLocks.remove(mslControl$MslContextMasterTokenKey);
                    ((Lock)o).unlock();
                }
            }
        }
    }
    
    private static String getIdentity(final MessageInputStream messageInputStream) {
        final MessageHeader messageHeader = messageInputStream.getMessageHeader();
        if (messageHeader == null) {
            throw new MslInternalException("This method should not be called with an error message.");
        }
        final MasterToken masterToken = messageHeader.getMasterToken();
        if (masterToken != null) {
            return masterToken.getIdentity();
        }
        return messageHeader.getEntityAuthenticationData().getIdentity();
    }
    
    private MasterToken getNewestMasterToken(final MslContext mslContext) {
        while (true) {
            final MslStore mslStore = mslContext.getMslStore();
            final MasterToken masterToken = mslStore.getMasterToken();
            if (masterToken == null) {
                return null;
            }
            final MslControl$MslContextMasterTokenKey mslControl$MslContextMasterTokenKey = new MslControl$MslContextMasterTokenKey(mslContext, masterToken);
            ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            final ReadWriteLock readWriteLock2 = this.masterTokenLocks.putIfAbsent(mslControl$MslContextMasterTokenKey, readWriteLock);
            if (readWriteLock2 != null) {
                readWriteLock = readWriteLock2;
            }
            readWriteLock.readLock().lockInterruptibly();
            if (masterToken.equals(mslStore.getMasterToken())) {
                return masterToken;
            }
            readWriteLock.readLock().unlock();
            readWriteLock.writeLock().lockInterruptibly();
            this.masterTokenLocks.remove(mslControl$MslContextMasterTokenKey);
            readWriteLock.writeLock().unlock();
        }
    }
    
    public static String getStringFromInputStream(final InputStream inputStream, final String s) {
        StringWriter stringWriter;
        try {
            final char[] array = new char[4096];
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, s);
            stringWriter = new StringWriter();
            while (true) {
                final int read = inputStreamReader.read(array);
                if (-1 == read) {
                    break;
                }
                stringWriter.write(array, 0, read);
            }
        }
        catch (IOException ex) {
            return "IOException!";
        }
        return stringWriter.toString();
    }
    
    private MessageInputStream receive(final MslContext mslContext, final MessageContext messageContext, InputStream inputStream, final MessageHeader messageHeader) {
        final HashSet<Object> set = new HashSet<Object>();
        if (messageHeader != null) {
            set.addAll(messageHeader.getKeyRequestData());
        }
        final Map<String, ICryptoContext> cryptoContexts = messageContext.getCryptoContexts();
        MessageInputStream messageInputStream = null;
        MessageHeader messageHeader2 = null;
        ErrorHeader errorHeader = null;
        MasterToken masterToken = null;
        EntityAuthenticationData entityAuthenticationData = null;
        Label_0300: {
            if (this.filterFactory == null) {
                break Label_0300;
            }
            inputStream = this.filterFactory.getInputStream(inputStream);
            MessageDebugContext debugContext;
            MessageHeader messageHeader3;
            UserIdToken userIdToken;
            UserAuthenticationData userAuthenticationData;
            MslConstants$ResponseCode errorCode;
            long n;
            long incrementMessageId;
            Label_0147_Outer:Label_0163_Outer:Label_0208_Outer:
            while (true) {
                while (true) {
                Label_0336:
                    while (true) {
                    Label_0331:
                        while (true) {
                        Label_0312:
                            while (true) {
                                try {
                                    while (true) {
                                        messageInputStream = new MessageInputStream(mslContext, inputStream, MslConstants.DEFAULT_CHARSET, (Set<KeyRequestData>)set, cryptoContexts);
                                        messageHeader2 = messageInputStream.getMessageHeader();
                                        errorHeader = messageInputStream.getErrorHeader();
                                        debugContext = messageContext.getDebugContext();
                                        if (debugContext != null) {
                                            if (messageHeader2 == null) {
                                                break;
                                            }
                                            messageHeader3 = messageHeader2;
                                            debugContext.receivedHeader(messageHeader3);
                                        }
                                        if (messageHeader2 == null) {
                                            break Label_0312;
                                        }
                                        masterToken = messageHeader2.getMasterToken();
                                        entityAuthenticationData = messageHeader2.getEntityAuthenticationData();
                                        userIdToken = messageHeader2.getUserIdToken();
                                        userAuthenticationData = messageHeader2.getUserAuthenticationData();
                                        if (messageHeader == null) {
                                            break Label_0300;
                                        }
                                        if (errorHeader == null) {
                                            break Label_0331;
                                        }
                                        try {
                                            errorCode = errorHeader.getErrorCode();
                                            if (messageHeader2 == null && (errorCode == MslConstants$ResponseCode.FAIL || errorCode == MslConstants$ResponseCode.TRANSIENT_FAILURE || errorCode == MslConstants$ResponseCode.ENTITY_REAUTH || errorCode == MslConstants$ResponseCode.ENTITYDATA_REAUTH)) {
                                                break Label_0300;
                                            }
                                            if (messageHeader2 == null) {
                                                break Label_0336;
                                            }
                                            n = messageHeader2.getMessageId();
                                            incrementMessageId = MessageBuilder.incrementMessageId(messageHeader.getMessageId());
                                            if (n != incrementMessageId) {
                                                throw new MslMessageException(MslError.UNEXPECTED_RESPONSE_MESSAGE_ID, "expected " + incrementMessageId + "; received " + n);
                                            }
                                            break Label_0300;
                                        }
                                        catch (MslException ex) {
                                            ex.setMasterToken(masterToken);
                                            ex.setEntityAuthenticationData(entityAuthenticationData);
                                            ex.setUserIdToken(userIdToken);
                                            ex.setUserAuthenticationData(userAuthenticationData);
                                            throw ex;
                                        }
                                    }
                                }
                                catch (Throwable t) {
                                    throw t;
                                }
                                messageHeader3 = (MessageHeader)errorHeader;
                                continue Label_0147_Outer;
                            }
                            entityAuthenticationData = errorHeader.getEntityAuthenticationData();
                            userIdToken = null;
                            masterToken = null;
                            userAuthenticationData = null;
                            continue Label_0163_Outer;
                        }
                        errorCode = null;
                        continue Label_0208_Outer;
                    }
                    n = errorHeader.getMessageId();
                    continue;
                }
            }
        }
        final String identity = mslContext.getEntityAuthenticationData(null).getIdentity();
        if (messageHeader2 != null) {
            String s;
            if (masterToken != null) {
                s = messageHeader2.getSender();
            }
            else {
                s = entityAuthenticationData.getIdentity();
            }
            if ((masterToken != null && masterToken.isDecrypted() && !masterToken.getIdentity().equals(s)) || identity.equals(s)) {
                throw new MslMessageException(MslError.UNEXPECTED_MESSAGE_SENDER, s);
            }
            final String recipient = messageHeader2.getRecipient();
            if (recipient != null && !recipient.equals(identity)) {
                throw new MslMessageException(MslError.MESSAGE_RECIPIENT_MISMATCH, recipient + " != " + identity);
            }
            if (messageHeader != null) {
                this.updateCryptoContexts(mslContext, messageHeader, messageInputStream);
            }
            final KeyResponseData keyResponseData = messageHeader2.getKeyResponseData();
            MasterToken masterToken2;
            UserIdToken userIdToken2;
            Set<ServiceToken> set2;
            if (!mslContext.isPeerToPeer()) {
                if (keyResponseData != null) {
                    masterToken2 = keyResponseData.getMasterToken();
                }
                else {
                    masterToken2 = messageHeader2.getMasterToken();
                }
                userIdToken2 = messageHeader2.getUserIdToken();
                set2 = messageHeader2.getServiceTokens();
            }
            else {
                if (keyResponseData != null) {
                    masterToken2 = keyResponseData.getMasterToken();
                }
                else {
                    masterToken2 = messageHeader2.getPeerMasterToken();
                }
                userIdToken2 = messageHeader2.getPeerUserIdToken();
                set2 = messageHeader2.getPeerServiceTokens();
            }
            final String userId = messageContext.getUserId();
            if (userId != null && userIdToken2 != null && !userIdToken2.isVerified()) {
                mslContext.getMslStore().addUserIdToken(userId, userIdToken2);
            }
            storeServiceTokens(mslContext, masterToken2, userIdToken2, set2);
        }
        else {
            final String identity2 = errorHeader.getEntityAuthenticationData().getIdentity();
            if (identity.equals(identity2)) {
                throw new MslMessageException(MslError.UNEXPECTED_MESSAGE_SENDER, identity2);
            }
        }
        Date date;
        if (messageHeader2 != null) {
            date = messageHeader2.getTimestamp();
        }
        else {
            date = errorHeader.getTimestamp();
        }
        if (date != null && (messageHeader != null || mslContext.isPeerToPeer())) {
            mslContext.updateRemoteTime(date);
            return messageInputStream;
        }
        return messageInputStream;
    }
    
    private void releaseMasterToken(final MslContext mslContext, final MasterToken masterToken) {
        if (masterToken != null) {
            final ReadWriteLock readWriteLock = this.masterTokenLocks.get(new MslControl$MslContextMasterTokenKey(mslContext, masterToken));
            if (readWriteLock != null) {
                readWriteLock.readLock().unlock();
            }
        }
    }
    
    private void releaseRenewalLock(final MslContext mslContext, final BlockingQueue<MasterToken> blockingQueue, final MessageInputStream messageInputStream) {
        if (this.renewingContexts.get(mslContext) != blockingQueue) {
            throw new IllegalStateException("Attempt to release renewal lock that is not owned by this queue.");
        }
        if (messageInputStream == null) {
            blockingQueue.add(this.NULL_MASTER_TOKEN);
            this.renewingContexts.remove(mslContext);
            return;
        }
        final MessageHeader messageHeader = messageInputStream.getMessageHeader();
        if (messageHeader == null) {
            blockingQueue.add(this.NULL_MASTER_TOKEN);
            this.renewingContexts.remove(mslContext);
            return;
        }
        final KeyResponseData keyResponseData = messageHeader.getKeyResponseData();
        if (keyResponseData != null) {
            blockingQueue.add(keyResponseData.getMasterToken());
        }
        else if (!mslContext.isPeerToPeer()) {
            final MasterToken masterToken = messageHeader.getMasterToken();
            if (masterToken != null) {
                blockingQueue.add(masterToken);
            }
            else {
                blockingQueue.add(this.NULL_MASTER_TOKEN);
            }
        }
        else {
            final MasterToken peerMasterToken = messageHeader.getPeerMasterToken();
            if (peerMasterToken != null) {
                blockingQueue.add(peerMasterToken);
            }
            else {
                blockingQueue.add(this.NULL_MASTER_TOKEN);
            }
        }
        this.renewingContexts.remove(mslContext);
    }
    
    private MslControl$SendResult send(final MslContext mslContext, final MessageContext messageContext, OutputStream outputStream, final MessageBuilder messageBuilder, final boolean b) {
        final MasterToken masterToken = messageBuilder.getMasterToken();
        UserIdToken userIdToken = messageBuilder.getUserIdToken();
        final UserIdToken peerUserIdToken = messageBuilder.getPeerUserIdToken();
        while (true) {
            Label_0539: {
                if (messageContext.getUserId() == null) {
                    break Label_0539;
                }
                final UserAuthenticationData userAuthData = messageContext.getUserAuthData(null, messageBuilder.isRenewable(), userIdToken == null);
                if (userAuthData == null) {
                    break Label_0539;
                }
                int n;
                if (messageBuilder.willEncryptHeader() && messageBuilder.willIntegrityProtectHeader()) {
                    messageBuilder.setUserAuthenticationData(userAuthData);
                    n = 0;
                }
                else {
                    n = 1;
                }
                if ((!mslContext.isPeerToPeer() && userIdToken == null) || (mslContext.isPeerToPeer() && peerUserIdToken == null)) {
                    final MslUser user = messageContext.getUser();
                    if (user != null) {
                        messageBuilder.setUser(user);
                        userIdToken = messageBuilder.getUserIdToken();
                    }
                }
                while (true) {
                    boolean b2;
                    if (n == 0 && (!messageContext.isEncrypted() || messageBuilder.willEncryptPayloads()) && (!messageContext.isIntegrityProtected() || messageBuilder.willIntegrityProtectPayloads()) && (!messageContext.isNonReplayable() || (messageBuilder.isNonReplayable() && masterToken != null))) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    final boolean handshake = !b2;
                    messageBuilder.setHandshake(handshake);
                    final HashSet<KeyRequestData> set = new HashSet<KeyRequestData>();
                    if (messageBuilder.isRenewable()) {
                        final Date remoteTime = mslContext.getRemoteTime();
                        if (masterToken == null || masterToken.isRenewable(remoteTime) || messageContext.isNonReplayable()) {
                            set.addAll((Collection<?>)messageContext.getKeyRequestData());
                            final Iterator<Object> iterator = set.iterator();
                            while (iterator.hasNext()) {
                                messageBuilder.addKeyRequestData(iterator.next());
                            }
                        }
                    }
                    messageContext.updateServiceTokens(new MessageServiceTokenBuilder(mslContext, messageContext, messageBuilder), handshake);
                    final MessageHeader header = messageBuilder.getHeader();
                    final MessageDebugContext debugContext = messageContext.getDebugContext();
                    if (debugContext != null) {
                        debugContext.sentHeader(header);
                    }
                    final KeyExchangeFactory$KeyExchangeData keyExchangeData = messageBuilder.getKeyExchangeData();
                    this.updateCryptoContexts(mslContext, header, keyExchangeData);
                    MasterToken masterToken2;
                    if (keyExchangeData != null) {
                        masterToken2 = keyExchangeData.keyResponseData.getMasterToken();
                    }
                    else {
                        masterToken2 = masterToken;
                    }
                    storeServiceTokens(mslContext, masterToken2, userIdToken, header.getServiceTokens());
                    ICryptoContext cryptoContext;
                    if (!mslContext.isPeerToPeer() && keyExchangeData != null) {
                        cryptoContext = keyExchangeData.cryptoContext;
                    }
                    else {
                        cryptoContext = header.getCryptoContext();
                    }
                    if (this.filterFactory != null) {
                        outputStream = this.filterFactory.getOutputStream(outputStream);
                    }
                    final MessageOutputStream messageOutputStream = new MessageOutputStream(mslContext, outputStream, MslConstants.DEFAULT_CHARSET, header, cryptoContext);
                    messageOutputStream.closeDestination(b);
                    if (!handshake) {
                        messageContext.write(messageOutputStream);
                    }
                    return new MslControl$SendResult(messageOutputStream, handshake, null);
                    continue;
                }
            }
            int n = 0;
            continue;
        }
    }
    
    private MslControl$SendReceiveResult sendReceive(final MslContext p0, final MessageContext p1, final InputStream p2, final OutputStream p3, final MessageBuilder p4, final boolean p5, final boolean p6, final int p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/concurrent/ArrayBlockingQueue;
        //     3: dup            
        //     4: iconst_1       
        //     5: iconst_1       
        //     6: invokespecial   java/util/concurrent/ArrayBlockingQueue.<init>:(IZ)V
        //     9: astore          15
        //    11: iload           8
        //    13: i2l            
        //    14: lstore          10
        //    16: aload_0        
        //    17: aload_1        
        //    18: aload_2        
        //    19: aload           15
        //    21: aload           5
        //    23: lload           10
        //    25: invokespecial   com/netflix/msl/msg/MslControl.acquireRenewalLock:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/util/concurrent/BlockingQueue;Lcom/netflix/msl/msg/MessageBuilder;J)Z
        //    28: istore          9
        //    30: aconst_null    
        //    31: astore          14
        //    33: aconst_null    
        //    34: astore          13
        //    36: aload           14
        //    38: astore          12
        //    40: aload           5
        //    42: iload           9
        //    44: invokevirtual   com/netflix/msl/msg/MessageBuilder.setRenewable:(Z)Lcom/netflix/msl/msg/MessageBuilder;
        //    47: pop            
        //    48: aload           14
        //    50: astore          12
        //    52: aload_0        
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload           4
        //    57: aload           5
        //    59: iload           7
        //    61: invokespecial   com/netflix/msl/msg/MslControl.send:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/io/OutputStream;Lcom/netflix/msl/msg/MessageBuilder;Z)Lcom/netflix/msl/msg/MslControl$SendResult;
        //    64: astore          16
        //    66: aload           14
        //    68: astore          12
        //    70: aload           16
        //    72: getfield        com/netflix/msl/msg/MslControl$SendResult.request:Lcom/netflix/msl/msg/MessageOutputStream;
        //    75: invokevirtual   com/netflix/msl/msg/MessageOutputStream.getMessageHeader:()Lcom/netflix/msl/msg/MessageHeader;
        //    78: astore          17
        //    80: aload           14
        //    82: astore          12
        //    84: aload           17
        //    86: invokevirtual   com/netflix/msl/msg/MessageHeader.getKeyRequestData:()Ljava/util/Set;
        //    89: astore          4
        //    91: iload           6
        //    93: ifne            170
        //    96: aload           14
        //    98: astore          12
        //   100: aload           16
        //   102: getfield        com/netflix/msl/msg/MslControl$SendResult.handshake:Z
        //   105: ifne            170
        //   108: aload           14
        //   110: astore          12
        //   112: aload           4
        //   114: invokeinterface java/util/Set.isEmpty:()Z
        //   119: ifeq            170
        //   122: aload           13
        //   124: astore          4
        //   126: aload           14
        //   128: astore          12
        //   130: aload           17
        //   132: invokevirtual   com/netflix/msl/msg/MessageHeader.isRenewable:()Z
        //   135: ifeq            222
        //   138: aload           13
        //   140: astore          4
        //   142: aload           14
        //   144: astore          12
        //   146: aload           17
        //   148: invokevirtual   com/netflix/msl/msg/MessageHeader.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   151: ifnull          222
        //   154: aload           13
        //   156: astore          4
        //   158: aload           14
        //   160: astore          12
        //   162: aload           17
        //   164: invokevirtual   com/netflix/msl/msg/MessageHeader.getUserAuthenticationData:()Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   167: ifnull          222
        //   170: aload           14
        //   172: astore          12
        //   174: aload_0        
        //   175: aload_1        
        //   176: aload_2        
        //   177: aload_3        
        //   178: aload           17
        //   180: invokespecial   com/netflix/msl/msg/MslControl.receive:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/io/InputStream;Lcom/netflix/msl/msg/MessageHeader;)Lcom/netflix/msl/msg/MessageInputStream;
        //   183: astore_2       
        //   184: aload_2        
        //   185: astore          12
        //   187: aload_2        
        //   188: iload           7
        //   190: invokevirtual   com/netflix/msl/msg/MessageInputStream.closeSource:(Z)V
        //   193: aload_2        
        //   194: astore          12
        //   196: aload_2        
        //   197: invokevirtual   com/netflix/msl/msg/MessageInputStream.getErrorHeader:()Lcom/netflix/msl/msg/ErrorHeader;
        //   200: astore_3       
        //   201: aload_2        
        //   202: astore          4
        //   204: aload_3        
        //   205: ifnull          222
        //   208: aload_2        
        //   209: astore          12
        //   211: aload_0        
        //   212: aload_1        
        //   213: aload           17
        //   215: aload_3        
        //   216: invokespecial   com/netflix/msl/msg/MslControl.cleanupContext:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageHeader;Lcom/netflix/msl/msg/ErrorHeader;)V
        //   219: aload_2        
        //   220: astore          4
        //   222: iload           9
        //   224: ifeq            236
        //   227: aload_0        
        //   228: aload_1        
        //   229: aload           15
        //   231: aload           4
        //   233: invokespecial   com/netflix/msl/msg/MslControl.releaseRenewalLock:(Lcom/netflix/msl/util/MslContext;Ljava/util/concurrent/BlockingQueue;Lcom/netflix/msl/msg/MessageInputStream;)V
        //   236: aload_0        
        //   237: aload_1        
        //   238: aload           5
        //   240: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   243: invokespecial   com/netflix/msl/msg/MslControl.releaseMasterToken:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   246: new             Lcom/netflix/msl/msg/MslControl$SendReceiveResult;
        //   249: dup            
        //   250: aload           4
        //   252: aload           16
        //   254: invokespecial   com/netflix/msl/msg/MslControl$SendReceiveResult.<init>:(Lcom/netflix/msl/msg/MessageInputStream;Lcom/netflix/msl/msg/MslControl$SendResult;)V
        //   257: areturn        
        //   258: astore_2       
        //   259: aload_0        
        //   260: aload_1        
        //   261: aload           5
        //   263: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   266: invokespecial   com/netflix/msl/msg/MslControl.releaseMasterToken:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   269: aconst_null    
        //   270: areturn        
        //   271: astore_2       
        //   272: aload_0        
        //   273: aload_1        
        //   274: aload           5
        //   276: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   279: invokespecial   com/netflix/msl/msg/MslControl.releaseMasterToken:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   282: aload_2        
        //   283: athrow         
        //   284: astore_2       
        //   285: iload           9
        //   287: ifeq            299
        //   290: aload_0        
        //   291: aload_1        
        //   292: aload           15
        //   294: aload           12
        //   296: invokespecial   com/netflix/msl/msg/MslControl.releaseRenewalLock:(Lcom/netflix/msl/util/MslContext;Ljava/util/concurrent/BlockingQueue;Lcom/netflix/msl/msg/MessageInputStream;)V
        //   299: aload_0        
        //   300: aload_1        
        //   301: aload           5
        //   303: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   306: invokespecial   com/netflix/msl/msg/MslControl.releaseMasterToken:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   309: aload_2        
        //   310: athrow         
        //   311: astore_2       
        //   312: goto            272
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  16     30     258    271    Ljava/lang/InterruptedException;
        //  16     30     271    272    Ljava/util/concurrent/TimeoutException;
        //  16     30     311    315    Ljava/lang/RuntimeException;
        //  40     48     284    311    Any
        //  52     66     284    311    Any
        //  70     80     284    311    Any
        //  84     91     284    311    Any
        //  100    108    284    311    Any
        //  112    122    284    311    Any
        //  130    138    284    311    Any
        //  146    154    284    311    Any
        //  162    170    284    311    Any
        //  174    184    284    311    Any
        //  187    193    284    311    Any
        //  196    201    284    311    Any
        //  211    219    284    311    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0170:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
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
    
    private static void storeServiceTokens(final MslContext mslContext, final MasterToken masterToken, final UserIdToken userIdToken, final Set<ServiceToken> set) {
        final MslStore mslStore = mslContext.getMslStore();
        final HashSet<ServiceToken> set2 = new HashSet<ServiceToken>();
        for (final ServiceToken serviceToken : set) {
            if (!serviceToken.isBoundTo(masterToken) || !masterToken.isVerified()) {
                final byte[] data = serviceToken.getData();
                if (data != null && data.length == 0) {
                    final String name = serviceToken.getName();
                    MasterToken masterToken2;
                    if (serviceToken.isMasterTokenBound()) {
                        masterToken2 = masterToken;
                    }
                    else {
                        masterToken2 = null;
                    }
                    UserIdToken userIdToken2;
                    if (serviceToken.isUserIdTokenBound()) {
                        userIdToken2 = userIdToken;
                    }
                    else {
                        userIdToken2 = null;
                    }
                    mslStore.removeServiceTokens(name, masterToken2, userIdToken2);
                }
                else {
                    set2.add(serviceToken);
                }
            }
        }
        mslStore.addServiceTokens(set2);
    }
    
    private void updateCryptoContexts(final MslContext mslContext, final MessageHeader messageHeader, final KeyExchangeFactory$KeyExchangeData keyExchangeFactory$KeyExchangeData) {
        final MslStore mslStore = mslContext.getMslStore();
        if (!mslContext.isPeerToPeer() && keyExchangeFactory$KeyExchangeData != null) {
            mslStore.setCryptoContext(keyExchangeFactory$KeyExchangeData.keyResponseData.getMasterToken(), keyExchangeFactory$KeyExchangeData.cryptoContext);
            this.deleteMasterToken(mslContext, messageHeader.getMasterToken());
        }
    }
    
    private void updateCryptoContexts(final MslContext mslContext, final MessageHeader messageHeader, final MessageInputStream messageInputStream) {
        final MessageHeader messageHeader2 = messageInputStream.getMessageHeader();
        if (messageHeader2 != null) {
            final MslStore mslStore = mslContext.getMslStore();
            final KeyResponseData keyResponseData = messageHeader2.getKeyResponseData();
            if (keyResponseData != null) {
                mslStore.setCryptoContext(keyResponseData.getMasterToken(), messageInputStream.getKeyExchangeCryptoContext());
                this.deleteMasterToken(mslContext, messageHeader.getMasterToken());
            }
        }
    }
    
    public Future<Boolean> error(final MslContext mslContext, final MessageContext messageContext, final MslControl$ApplicationError mslControl$ApplicationError, final OutputStream outputStream, final MessageInputStream messageInputStream) {
        if (messageInputStream.getErrorHeader() != null) {
            throw new IllegalArgumentException("Request message input stream cannot be for an error message.");
        }
        return this.executor.submit((Callable<Boolean>)new MslControl$ErrorService(this, mslContext, messageContext, mslControl$ApplicationError, outputStream, messageInputStream));
    }
    
    @Override
    protected void finalize() {
        this.executor.shutdownNow();
        super.finalize();
    }
    
    public Future<MessageInputStream> receive(final MslContext mslContext, final MessageContext messageContext, final InputStream inputStream, final OutputStream outputStream, final int n) {
        return this.executor.submit((Callable<MessageInputStream>)new MslControl$ReceiveService(this, mslContext, messageContext, inputStream, outputStream, n));
    }
    
    public Future<MslControl$MslChannel> request(final MslContext mslContext, final MessageContext messageContext, final Url url, final int n) {
        if (mslContext.isPeerToPeer()) {
            throw new IllegalStateException("This method cannot be used in peer-to-peer mode.");
        }
        return this.executor.submit((Callable<MslControl$MslChannel>)new MslControl$RequestService(this, mslContext, messageContext, url, n));
    }
    
    public Future<MslControl$MslChannel> request(final MslContext mslContext, final MessageContext messageContext, final InputStream inputStream, final OutputStream outputStream, final int n) {
        if (!mslContext.isPeerToPeer()) {
            throw new IllegalStateException("This method cannot be used in trusted network mode.");
        }
        return this.executor.submit((Callable<MslControl$MslChannel>)new MslControl$RequestService(this, mslContext, messageContext, inputStream, outputStream, n));
    }
    
    public Future<MslControl$MslChannel> respond(final MslContext mslContext, final MessageContext messageContext, final InputStream inputStream, final OutputStream outputStream, final MessageInputStream messageInputStream, final int n) {
        if (messageInputStream.getErrorHeader() != null) {
            throw new IllegalArgumentException("Request message input stream cannot be for an error message.");
        }
        return this.executor.submit((Callable<MslControl$MslChannel>)new MslControl$RespondService(this, mslContext, messageContext, inputStream, outputStream, messageInputStream, n));
    }
    
    public void setFilterFactory(final FilterStreamFactory filterFactory) {
        this.filterFactory = filterFactory;
    }
    
    public void shutdown() {
        this.executor.shutdown();
    }
}
