// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.io.Url;
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
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
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
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.tokens.UserIdToken;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslEncodingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.netflix.android.org.json.JSONObject;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import com.netflix.msl.tokens.MasterToken;
import java.util.List;
import com.netflix.msl.MslErrorResponseException;
import com.netflix.msl.MslConstants;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import java.io.OutputStream;
import java.io.InputStream;
import com.netflix.msl.util.MslContext;
import java.util.concurrent.Callable;

class MslControl$RespondService implements Callable<MslControl$MslChannel>
{
    private final MslContext ctx;
    private final InputStream in;
    private final MessageContext msgCtx;
    private final OutputStream out;
    private final MessageInputStream request;
    final /* synthetic */ MslControl this$0;
    private final int timeout;
    
    public MslControl$RespondService(final MslControl this$0, final MslContext ctx, final MessageContext msgCtx, final InputStream in, final OutputStream out, final MessageInputStream request, final int timeout) {
        this.this$0 = this$0;
        if (request.getErrorHeader() != null) {
            throw new MslInternalException("Respond service created for an error message.");
        }
        this.ctx = ctx;
        this.msgCtx = msgCtx;
        this.in = in;
        this.out = out;
        this.request = request;
        this.timeout = timeout;
    }
    
    private MslControl$MslChannel peerToPeerExecute(MessageContext access$1200, MessageBuilder messageBuilder, int n) {
        final MessageDebugContext debugContext = access$1200.getDebugContext();
        if (n + 2 > 12) {
            this.this$0.releaseMasterToken(this.ctx, messageBuilder.getMasterToken());
            return null;
        }
        if (access$1200.getUser() != null && messageBuilder.getPeerMasterToken() == null && messageBuilder.getKeyExchangeData() == null) {
            this.this$0.releaseMasterToken(this.ctx, messageBuilder.getMasterToken());
            try {
                final ErrorHeader errorResponse = MessageBuilder.createErrorResponse(this.ctx, getIdentity(this.request), MessageBuilder.decrementMessageId(messageBuilder.getMessageId()), MslError.RESPONSE_REQUIRES_MASTERTOKEN, null);
                if (debugContext != null) {
                    debugContext.sentHeader(errorResponse);
                }
                this.this$0.streamFactory.createOutputStream(this.ctx, this.out, MslConstants.DEFAULT_CHARSET, errorResponse).close();
                return null;
            }
            catch (Throwable t) {
                if (MslControl.cancelled(t)) {
                    return null;
                }
                throw new MslErrorResponseException("Response wishes to attach a user ID token but there is no master token.", t, null);
            }
        }
        messageBuilder = (MessageBuilder)this.this$0.sendReceive(this.ctx, access$1200, this.in, this.out, messageBuilder, false, false, this.timeout);
        final MessageInputStream response = ((MslControl$SendReceiveResult)messageBuilder).response;
        n += 2;
        if (response == null) {
            return new MslControl$MslChannel(this.request, ((MslControl$SendReceiveResult)messageBuilder).request);
        }
        final MessageHeader messageHeader = response.getMessageHeader();
        if (messageHeader == null) {
            while (true) {
                try {
                    response.close();
                    access$1200 = (MessageContext)this.this$0.buildErrorResponse(this.ctx, access$1200, (MslControl$SendResult)messageBuilder, response.getErrorHeader());
                    if (access$1200 == null) {
                        return null;
                    }
                }
                catch (Throwable t2) {
                    if (MslControl.cancelled(t2)) {
                        return null;
                    }
                    continue;
                }
                break;
            }
            messageBuilder = ((MslControl$ErrorResult)access$1200).builder;
            return this.peerToPeerExecute(((MslControl$ErrorResult)access$1200).msgCtx, messageBuilder, n);
        }
        if (((MslControl$SendReceiveResult)messageBuilder).handshake) {
            while (true) {
                try {
                    response.close();
                    access$1200 = new MslControl$ResendMessageContext(null, access$1200);
                    return this.peerToPeerExecute(access$1200, this.this$0.buildResponse(this.ctx, access$1200, messageHeader), n);
                }
                catch (Throwable t3) {
                    if (MslControl.cancelled(t3)) {
                        return null;
                    }
                    continue;
                }
                break;
            }
        }
        return new MslControl$MslChannel(((MslControl$SendReceiveResult)messageBuilder).response, ((MslControl$SendReceiveResult)messageBuilder).request);
    }
    
    private MslControl$MslChannel trustedNetworkExecute(final MessageBuilder messageBuilder, final int n) {
        MessageDebugContext debugContext;
        while (true) {
            while (true) {
                try {
                    debugContext = this.msgCtx.getDebugContext();
                    if (n + 1 > 12) {
                        return null;
                    }
                    Label_0157: {
                        if (!this.msgCtx.isIntegrityProtected() || messageBuilder.willIntegrityProtectPayloads()) {
                            break Label_0157;
                        }
                        MslError mslError = MslError.RESPONSE_REQUIRES_INTEGRITY_PROTECTION;
                        if (mslError == null) {
                            break;
                        }
                        try {
                            final ErrorHeader errorResponse = MessageBuilder.createErrorResponse(this.ctx, getIdentity(this.request), MessageBuilder.decrementMessageId(messageBuilder.getMessageId()), mslError, null);
                            if (debugContext != null) {
                                debugContext.sentHeader(errorResponse);
                            }
                            this.this$0.streamFactory.createOutputStream(this.ctx, this.out, MslConstants.DEFAULT_CHARSET, errorResponse).close();
                            return null;
                            mslError = MslError.RESPONSE_REQUIRES_ENCRYPTION;
                            continue;
                        }
                        // iftrue(Label_0498:, !this.msgCtx.isEncrypted() || messageBuilder.willEncryptPayloads())
                        catch (Throwable debugContext) {
                            if (MslControl.cancelled((Throwable)debugContext)) {
                                return null;
                            }
                            throw new MslErrorResponseException("Response requires encryption or integrity protection but cannot be protected: " + mslError, (Throwable)debugContext, null);
                        }
                    }
                }
                finally {
                    this.this$0.releaseMasterToken(this.ctx, messageBuilder.getMasterToken());
                }
                break;
                Label_0498: {
                    final MslError mslError = null;
                }
                continue;
            }
        }
        if (this.msgCtx.getUser() != null && messageBuilder.getMasterToken() == null && messageBuilder.getKeyExchangeData() == null) {
            try {
                final ErrorHeader errorResponse2 = MessageBuilder.createErrorResponse(this.ctx, getIdentity(this.request), MessageBuilder.decrementMessageId(messageBuilder.getMessageId()), MslError.RESPONSE_REQUIRES_MASTERTOKEN, null);
                if (debugContext != null) {
                    debugContext.sentHeader(errorResponse2);
                }
                this.this$0.streamFactory.createOutputStream(this.ctx, this.out, MslConstants.DEFAULT_CHARSET, errorResponse2).close();
                this.this$0.releaseMasterToken(this.ctx, messageBuilder.getMasterToken());
                return null;
            }
            catch (Throwable t) {
                if (MslControl.cancelled(t)) {
                    this.this$0.releaseMasterToken(this.ctx, messageBuilder.getMasterToken());
                    return null;
                }
                throw new MslErrorResponseException("Response wishes to attach a user ID token but there is no master token.", t, null);
            }
        }
        messageBuilder.setRenewable(false);
        final MslControl$MslChannel mslControl$MslChannel = new MslControl$MslChannel(this.request, this.this$0.send(this.ctx, this.msgCtx, this.out, messageBuilder, false).request);
        this.this$0.releaseMasterToken(this.ctx, messageBuilder.getMasterToken());
        return mslControl$MslChannel;
    }
    
    @Override
    public MslControl$MslChannel call() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_0        
        //     3: getfield        com/netflix/msl/msg/MslControl$RespondService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //     6: invokeinterface com/netflix/msl/msg/MessageContext.getDebugContext:()Lcom/netflix/msl/msg/MessageDebugContext;
        //    11: astore          4
        //    13: aload_0        
        //    14: getfield        com/netflix/msl/msg/MslControl$RespondService.request:Lcom/netflix/msl/msg/MessageInputStream;
        //    17: invokevirtual   com/netflix/msl/msg/MessageInputStream.getMessageHeader:()Lcom/netflix/msl/msg/MessageHeader;
        //    20: astore          5
        //    22: aload_0        
        //    23: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //    26: aload_0        
        //    27: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //    30: aload_0        
        //    31: getfield        com/netflix/msl/msg/MslControl$RespondService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //    34: aload           5
        //    36: invokestatic    com/netflix/msl/msg/MslControl.access$700:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageHeader;)Lcom/netflix/msl/msg/MessageBuilder;
        //    39: astore          6
        //    41: aload_0        
        //    42: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //    45: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //    48: ifne            314
        //    51: aload_0        
        //    52: aload           6
        //    54: iconst_3       
        //    55: invokespecial   com/netflix/msl/msg/MslControl$RespondService.trustedNetworkExecute:(Lcom/netflix/msl/msg/MessageBuilder;I)Lcom/netflix/msl/msg/MslControl$MslChannel;
        //    58: astore_3       
        //    59: aload_3        
        //    60: ifnull          70
        //    63: aload_3        
        //    64: getfield        com/netflix/msl/msg/MslControl$MslChannel.output:Lcom/netflix/msl/msg/MessageOutputStream;
        //    67: invokevirtual   com/netflix/msl/msg/MessageOutputStream.stopCaching:()V
        //    70: aload_3        
        //    71: areturn        
        //    72: astore_3       
        //    73: aconst_null    
        //    74: areturn        
        //    75: astore          6
        //    77: aload           6
        //    79: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //    82: ifeq            87
        //    85: aconst_null    
        //    86: areturn        
        //    87: aload_0        
        //    88: getfield        com/netflix/msl/msg/MslControl$RespondService.request:Lcom/netflix/msl/msg/MessageInputStream;
        //    91: invokestatic    com/netflix/msl/msg/MslControl.access$1000:(Lcom/netflix/msl/msg/MessageInputStream;)Ljava/lang/String;
        //    94: astore          7
        //    96: aload           6
        //    98: invokevirtual   com/netflix/msl/MslException.getError:()Lcom/netflix/msl/MslError;
        //   101: astore          8
        //   103: aload           5
        //   105: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageCapabilities:()Lcom/netflix/msl/msg/MessageCapabilities;
        //   108: astore          5
        //   110: aload           5
        //   112: ifnull          121
        //   115: aload           5
        //   117: invokevirtual   com/netflix/msl/msg/MessageCapabilities.getLanguages:()Ljava/util/List;
        //   120: astore_3       
        //   121: aload_0        
        //   122: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   125: invokestatic    com/netflix/msl/msg/MslControl.access$500:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/ErrorMessageRegistry;
        //   128: aload           8
        //   130: aload_3        
        //   131: invokeinterface com/netflix/msl/msg/ErrorMessageRegistry.getUserMessage:(Lcom/netflix/msl/MslError;Ljava/util/List;)Ljava/lang/String;
        //   136: astore_3       
        //   137: aload_0        
        //   138: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   141: aload           7
        //   143: aload           6
        //   145: invokevirtual   com/netflix/msl/MslException.getMessageId:()Ljava/lang/Long;
        //   148: aload           8
        //   150: aload_3        
        //   151: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   154: astore_3       
        //   155: aload           4
        //   157: ifnull          168
        //   160: aload           4
        //   162: aload_3        
        //   163: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   168: aload_0        
        //   169: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   172: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   175: aload_0        
        //   176: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   179: aload_0        
        //   180: getfield        com/netflix/msl/msg/MslControl$RespondService.out:Ljava/io/OutputStream;
        //   183: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   186: aload_3        
        //   187: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   190: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   193: aload           6
        //   195: athrow         
        //   196: astore_3       
        //   197: new             Lcom/netflix/msl/MslErrorResponseException;
        //   200: dup            
        //   201: ldc_w           "Error building the response."
        //   204: aload_3        
        //   205: aload           6
        //   207: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   210: athrow         
        //   211: astore_3       
        //   212: aload_3        
        //   213: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   216: ifeq            221
        //   219: aconst_null    
        //   220: areturn        
        //   221: aload_0        
        //   222: getfield        com/netflix/msl/msg/MslControl$RespondService.request:Lcom/netflix/msl/msg/MessageInputStream;
        //   225: invokestatic    com/netflix/msl/msg/MslControl.access$1000:(Lcom/netflix/msl/msg/MessageInputStream;)Ljava/lang/String;
        //   228: astore          5
        //   230: aload_0        
        //   231: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   234: aload           5
        //   236: aconst_null    
        //   237: getstatic       com/netflix/msl/MslError.INTERNAL_EXCEPTION:Lcom/netflix/msl/MslError;
        //   240: aconst_null    
        //   241: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   244: astore          5
        //   246: aload           4
        //   248: ifnull          260
        //   251: aload           4
        //   253: aload           5
        //   255: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   260: aload_0        
        //   261: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   264: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   267: aload_0        
        //   268: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   271: aload_0        
        //   272: getfield        com/netflix/msl/msg/MslControl$RespondService.out:Ljava/io/OutputStream;
        //   275: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   278: aload           5
        //   280: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   283: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   286: new             Lcom/netflix/msl/MslInternalException;
        //   289: dup            
        //   290: ldc_w           "Error building the response."
        //   293: aload_3        
        //   294: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   297: athrow         
        //   298: astore          4
        //   300: new             Lcom/netflix/msl/MslErrorResponseException;
        //   303: dup            
        //   304: ldc_w           "Error building the response."
        //   307: aload           4
        //   309: aload_3        
        //   310: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   313: athrow         
        //   314: aload_0        
        //   315: aload_0        
        //   316: getfield        com/netflix/msl/msg/MslControl$RespondService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //   319: aload           6
        //   321: iconst_3       
        //   322: invokespecial   com/netflix/msl/msg/MslControl$RespondService.peerToPeerExecute:(Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageBuilder;I)Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   325: astore_3       
        //   326: goto            59
        //   329: astore_3       
        //   330: aconst_null    
        //   331: areturn        
        //   332: astore_3       
        //   333: aload_3        
        //   334: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   337: ifeq            342
        //   340: aconst_null    
        //   341: areturn        
        //   342: aload_0        
        //   343: getfield        com/netflix/msl/msg/MslControl$RespondService.request:Lcom/netflix/msl/msg/MessageInputStream;
        //   346: invokestatic    com/netflix/msl/msg/MslControl.access$1000:(Lcom/netflix/msl/msg/MessageInputStream;)Ljava/lang/String;
        //   349: astore          5
        //   351: aload           6
        //   353: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMessageId:()J
        //   356: invokestatic    com/netflix/msl/msg/MessageBuilder.decrementMessageId:(J)J
        //   359: lstore_1       
        //   360: aload_0        
        //   361: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   364: aload           5
        //   366: lload_1        
        //   367: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   370: getstatic       com/netflix/msl/MslError.MSL_COMMS_FAILURE:Lcom/netflix/msl/MslError;
        //   373: aconst_null    
        //   374: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   377: astore          5
        //   379: aload           4
        //   381: ifnull          393
        //   384: aload           4
        //   386: aload           5
        //   388: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   393: aload_0        
        //   394: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   397: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   400: aload_0        
        //   401: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   404: aload_0        
        //   405: getfield        com/netflix/msl/msg/MslControl$RespondService.out:Ljava/io/OutputStream;
        //   408: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   411: aload           5
        //   413: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   416: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   419: aload_3        
        //   420: athrow         
        //   421: astore          4
        //   423: aload           4
        //   425: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   428: ifeq            433
        //   431: aconst_null    
        //   432: areturn        
        //   433: new             Lcom/netflix/msl/MslErrorResponseException;
        //   436: dup            
        //   437: ldc_w           "Error sending the response."
        //   440: aload           4
        //   442: aload_3        
        //   443: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   446: athrow         
        //   447: astore          7
        //   449: aload           7
        //   451: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   454: ifeq            459
        //   457: aconst_null    
        //   458: areturn        
        //   459: aload_0        
        //   460: getfield        com/netflix/msl/msg/MslControl$RespondService.request:Lcom/netflix/msl/msg/MessageInputStream;
        //   463: invokestatic    com/netflix/msl/msg/MslControl.access$1000:(Lcom/netflix/msl/msg/MessageInputStream;)Ljava/lang/String;
        //   466: astore          8
        //   468: aload           6
        //   470: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMessageId:()J
        //   473: invokestatic    com/netflix/msl/msg/MessageBuilder.decrementMessageId:(J)J
        //   476: lstore_1       
        //   477: aload           7
        //   479: invokevirtual   com/netflix/msl/MslException.getError:()Lcom/netflix/msl/MslError;
        //   482: astore          6
        //   484: aload           5
        //   486: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageCapabilities:()Lcom/netflix/msl/msg/MessageCapabilities;
        //   489: astore_3       
        //   490: aload_3        
        //   491: ifnull          573
        //   494: aload_3        
        //   495: invokevirtual   com/netflix/msl/msg/MessageCapabilities.getLanguages:()Ljava/util/List;
        //   498: astore_3       
        //   499: aload_0        
        //   500: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   503: invokestatic    com/netflix/msl/msg/MslControl.access$500:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/ErrorMessageRegistry;
        //   506: aload           6
        //   508: aload_3        
        //   509: invokeinterface com/netflix/msl/msg/ErrorMessageRegistry.getUserMessage:(Lcom/netflix/msl/MslError;Ljava/util/List;)Ljava/lang/String;
        //   514: astore_3       
        //   515: aload_0        
        //   516: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   519: aload           8
        //   521: lload_1        
        //   522: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   525: aload           6
        //   527: aload_3        
        //   528: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   531: astore_3       
        //   532: aload           4
        //   534: ifnull          545
        //   537: aload           4
        //   539: aload_3        
        //   540: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   545: aload_0        
        //   546: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   549: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   552: aload_0        
        //   553: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   556: aload_0        
        //   557: getfield        com/netflix/msl/msg/MslControl$RespondService.out:Ljava/io/OutputStream;
        //   560: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   563: aload_3        
        //   564: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   567: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   570: aload           7
        //   572: athrow         
        //   573: aconst_null    
        //   574: astore_3       
        //   575: goto            499
        //   578: astore_3       
        //   579: aload_3        
        //   580: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   583: ifeq            588
        //   586: aconst_null    
        //   587: areturn        
        //   588: new             Lcom/netflix/msl/MslErrorResponseException;
        //   591: dup            
        //   592: ldc_w           "Error sending the response."
        //   595: aload_3        
        //   596: aload           7
        //   598: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   601: athrow         
        //   602: astore_3       
        //   603: aload_3        
        //   604: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   607: ifeq            612
        //   610: aconst_null    
        //   611: areturn        
        //   612: aload_0        
        //   613: getfield        com/netflix/msl/msg/MslControl$RespondService.request:Lcom/netflix/msl/msg/MessageInputStream;
        //   616: invokestatic    com/netflix/msl/msg/MslControl.access$1000:(Lcom/netflix/msl/msg/MessageInputStream;)Ljava/lang/String;
        //   619: astore          5
        //   621: aload           6
        //   623: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMessageId:()J
        //   626: invokestatic    com/netflix/msl/msg/MessageBuilder.decrementMessageId:(J)J
        //   629: lstore_1       
        //   630: aload_0        
        //   631: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   634: aload           5
        //   636: lload_1        
        //   637: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   640: getstatic       com/netflix/msl/MslError.INTERNAL_EXCEPTION:Lcom/netflix/msl/MslError;
        //   643: aconst_null    
        //   644: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   647: astore          5
        //   649: aload           4
        //   651: ifnull          663
        //   654: aload           4
        //   656: aload           5
        //   658: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   663: aload_0        
        //   664: getfield        com/netflix/msl/msg/MslControl$RespondService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   667: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   670: aload_0        
        //   671: getfield        com/netflix/msl/msg/MslControl$RespondService.ctx:Lcom/netflix/msl/util/MslContext;
        //   674: aload_0        
        //   675: getfield        com/netflix/msl/msg/MslControl$RespondService.out:Ljava/io/OutputStream;
        //   678: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   681: aload           5
        //   683: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   686: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   689: new             Lcom/netflix/msl/MslInternalException;
        //   692: dup            
        //   693: ldc_w           "Error sending the response."
        //   696: aload_3        
        //   697: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   700: athrow         
        //   701: astore          4
        //   703: aload           4
        //   705: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   708: ifeq            713
        //   711: aconst_null    
        //   712: areturn        
        //   713: new             Lcom/netflix/msl/MslErrorResponseException;
        //   716: dup            
        //   717: ldc_w           "Error sending the response."
        //   720: aload           4
        //   722: aload_3        
        //   723: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   726: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  22     41     72     75     Ljava/lang/InterruptedException;
        //  22     41     75     211    Lcom/netflix/msl/MslException;
        //  22     41     211    314    Ljava/lang/Throwable;
        //  41     59     329    332    Ljava/lang/InterruptedException;
        //  41     59     332    447    Ljava/io/IOException;
        //  41     59     447    602    Lcom/netflix/msl/MslException;
        //  41     59     602    727    Ljava/lang/Throwable;
        //  63     70     329    332    Ljava/lang/InterruptedException;
        //  63     70     332    447    Ljava/io/IOException;
        //  63     70     447    602    Lcom/netflix/msl/MslException;
        //  63     70     602    727    Ljava/lang/Throwable;
        //  87     110    196    211    Ljava/lang/Throwable;
        //  115    121    196    211    Ljava/lang/Throwable;
        //  121    155    196    211    Ljava/lang/Throwable;
        //  160    168    196    211    Ljava/lang/Throwable;
        //  168    193    196    211    Ljava/lang/Throwable;
        //  221    246    298    314    Ljava/lang/Throwable;
        //  251    260    298    314    Ljava/lang/Throwable;
        //  260    286    298    314    Ljava/lang/Throwable;
        //  314    326    329    332    Ljava/lang/InterruptedException;
        //  314    326    332    447    Ljava/io/IOException;
        //  314    326    447    602    Lcom/netflix/msl/MslException;
        //  314    326    602    727    Ljava/lang/Throwable;
        //  342    379    421    447    Ljava/lang/Throwable;
        //  384    393    421    447    Ljava/lang/Throwable;
        //  393    419    421    447    Ljava/lang/Throwable;
        //  459    490    578    602    Ljava/lang/Throwable;
        //  494    499    578    602    Ljava/lang/Throwable;
        //  499    532    578    602    Ljava/lang/Throwable;
        //  537    545    578    602    Ljava/lang/Throwable;
        //  545    570    578    602    Ljava/lang/Throwable;
        //  612    649    701    727    Ljava/lang/Throwable;
        //  654    663    701    727    Ljava/lang/Throwable;
        //  663    689    701    727    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
}
