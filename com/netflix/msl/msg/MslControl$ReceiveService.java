// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.io.OutputStream;
import java.io.InputStream;
import com.netflix.msl.util.MslContext;
import java.util.concurrent.Callable;

class MslControl$ReceiveService implements Callable<MessageInputStream>
{
    private final MslContext ctx;
    private final InputStream in;
    private final MessageContext msgCtx;
    private final OutputStream out;
    final /* synthetic */ MslControl this$0;
    private final int timeout;
    
    public MslControl$ReceiveService(final MslControl this$0, final MslContext ctx, final MessageContext msgCtx, final InputStream in, final OutputStream out, final int timeout) {
        this.this$0 = this$0;
        this.ctx = ctx;
        this.msgCtx = msgCtx;
        this.in = in;
        this.out = out;
        this.timeout = timeout;
    }
    
    @Override
    public MessageInputStream call() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/msl/msg/MslControl$ReceiveService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //     4: invokeinterface com/netflix/msl/msg/MessageContext.getDebugContext:()Lcom/netflix/msl/msg/MessageDebugContext;
        //     9: astore          5
        //    11: aload_0        
        //    12: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //    15: aload_0        
        //    16: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //    19: aload_0        
        //    20: getfield        com/netflix/msl/msg/MslControl$ReceiveService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //    23: aload_0        
        //    24: getfield        com/netflix/msl/msg/MslControl$ReceiveService.in:Ljava/io/InputStream;
        //    27: aconst_null    
        //    28: invokestatic    com/netflix/msl/msg/MslControl.access$400:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/io/InputStream;Lcom/netflix/msl/msg/MessageHeader;)Lcom/netflix/msl/msg/MessageInputStream;
        //    31: astore          6
        //    33: aload           6
        //    35: invokevirtual   com/netflix/msl/msg/MessageInputStream.getMessageHeader:()Lcom/netflix/msl/msg/MessageHeader;
        //    38: astore          4
        //    40: aload           4
        //    42: ifnonnull       329
        //    45: aload           6
        //    47: areturn        
        //    48: astore          4
        //    50: aconst_null    
        //    51: areturn        
        //    52: astore          6
        //    54: aload           6
        //    56: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //    59: ifeq            64
        //    62: aconst_null    
        //    63: areturn        
        //    64: aload           6
        //    66: invokevirtual   com/netflix/msl/MslException.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //    69: astore          4
        //    71: aload           6
        //    73: invokevirtual   com/netflix/msl/MslException.getEntityAuthenticationData:()Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //    76: astore          7
        //    78: aload           4
        //    80: ifnull          177
        //    83: aload           4
        //    85: invokevirtual   com/netflix/msl/tokens/MasterToken.getIdentity:()Ljava/lang/String;
        //    88: astore          4
        //    90: aload           6
        //    92: invokevirtual   com/netflix/msl/MslException.getError:()Lcom/netflix/msl/MslError;
        //    95: astore          7
        //    97: aload_0        
        //    98: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   101: invokestatic    com/netflix/msl/msg/MslControl.access$500:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/ErrorMessageRegistry;
        //   104: aload           7
        //   106: aconst_null    
        //   107: invokeinterface com/netflix/msl/msg/ErrorMessageRegistry.getUserMessage:(Lcom/netflix/msl/MslError;Ljava/util/List;)Ljava/lang/String;
        //   112: astore          8
        //   114: aload_0        
        //   115: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   118: aload           4
        //   120: aload           6
        //   122: invokevirtual   com/netflix/msl/MslException.getMessageId:()Ljava/lang/Long;
        //   125: aload           7
        //   127: aload           8
        //   129: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   132: astore          4
        //   134: aload           5
        //   136: ifnull          148
        //   139: aload           5
        //   141: aload           4
        //   143: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   148: aload_0        
        //   149: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   152: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   155: aload_0        
        //   156: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   159: aload_0        
        //   160: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //   163: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   166: aload           4
        //   168: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   171: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   174: aload           6
        //   176: athrow         
        //   177: aload           7
        //   179: ifnull          192
        //   182: aload           7
        //   184: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationData.getIdentity:()Ljava/lang/String;
        //   187: astore          4
        //   189: goto            90
        //   192: aconst_null    
        //   193: astore          4
        //   195: goto            90
        //   198: astore          4
        //   200: aload           4
        //   202: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   205: ifeq            210
        //   208: aconst_null    
        //   209: areturn        
        //   210: new             Lcom/netflix/msl/MslErrorResponseException;
        //   213: dup            
        //   214: ldc             "Error receiving the message header."
        //   216: aload           4
        //   218: aload           6
        //   220: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   223: athrow         
        //   224: astore          4
        //   226: aload           4
        //   228: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   231: ifeq            236
        //   234: aconst_null    
        //   235: areturn        
        //   236: aload_0        
        //   237: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   240: aconst_null    
        //   241: aconst_null    
        //   242: getstatic       com/netflix/msl/MslError.INTERNAL_EXCEPTION:Lcom/netflix/msl/MslError;
        //   245: aconst_null    
        //   246: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   249: astore          6
        //   251: aload           5
        //   253: ifnull          265
        //   256: aload           5
        //   258: aload           6
        //   260: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   265: aload_0        
        //   266: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   269: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   272: aload_0        
        //   273: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   276: aload_0        
        //   277: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //   280: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   283: aload           6
        //   285: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   288: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   291: new             Lcom/netflix/msl/MslInternalException;
        //   294: dup            
        //   295: ldc             "Error receiving the message header."
        //   297: aload           4
        //   299: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   302: athrow         
        //   303: astore          5
        //   305: aload           5
        //   307: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   310: ifeq            315
        //   313: aconst_null    
        //   314: areturn        
        //   315: new             Lcom/netflix/msl/MslErrorResponseException;
        //   318: dup            
        //   319: ldc             "Error receiving the message header."
        //   321: aload           5
        //   323: aload           4
        //   325: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   328: athrow         
        //   329: aload           6
        //   331: invokevirtual   com/netflix/msl/msg/MessageInputStream.isHandshake:()Z
        //   334: istore_1       
        //   335: iload_1        
        //   336: ifne            464
        //   339: aload           6
        //   341: areturn        
        //   342: astore          7
        //   344: aload           7
        //   346: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   349: ifeq            354
        //   352: aconst_null    
        //   353: areturn        
        //   354: aload           6
        //   356: invokevirtual   com/netflix/msl/msg/MessageInputStream.getIdentity:()Ljava/lang/String;
        //   359: astore          6
        //   361: aload           4
        //   363: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageId:()J
        //   366: lstore_2       
        //   367: aload_0        
        //   368: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   371: aload           6
        //   373: lload_2        
        //   374: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   377: getstatic       com/netflix/msl/MslError.INTERNAL_EXCEPTION:Lcom/netflix/msl/MslError;
        //   380: aconst_null    
        //   381: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   384: astore          4
        //   386: aload           5
        //   388: ifnull          400
        //   391: aload           5
        //   393: aload           4
        //   395: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   400: aload_0        
        //   401: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   404: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   407: aload_0        
        //   408: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   411: aload_0        
        //   412: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //   415: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   418: aload           4
        //   420: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   423: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   426: new             Lcom/netflix/msl/MslInternalException;
        //   429: dup            
        //   430: ldc             "Error peeking into the message payloads."
        //   432: aload           7
        //   434: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   437: athrow         
        //   438: astore          4
        //   440: aload           4
        //   442: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   445: ifeq            450
        //   448: aconst_null    
        //   449: areturn        
        //   450: new             Lcom/netflix/msl/MslErrorResponseException;
        //   453: dup            
        //   454: ldc             "Error peeking into the message payloads."
        //   456: aload           4
        //   458: aload           7
        //   460: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   463: athrow         
        //   464: aload_0        
        //   465: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   468: aload_0        
        //   469: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   472: aload_0        
        //   473: getfield        com/netflix/msl/msg/MslControl$ReceiveService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //   476: aload           6
        //   478: invokevirtual   com/netflix/msl/msg/MessageInputStream.getMessageHeader:()Lcom/netflix/msl/msg/MessageHeader;
        //   481: invokestatic    com/netflix/msl/msg/MslControl.access$700:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageHeader;)Lcom/netflix/msl/msg/MessageBuilder;
        //   484: astore          7
        //   486: aload           6
        //   488: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   491: new             Lcom/netflix/msl/msg/MslControl$KeyxResponseMessageContext;
        //   494: dup            
        //   495: aload_0        
        //   496: getfield        com/netflix/msl/msg/MslControl$ReceiveService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //   499: invokespecial   com/netflix/msl/msg/MslControl$KeyxResponseMessageContext.<init>:(Lcom/netflix/msl/msg/MessageContext;)V
        //   502: astore          8
        //   504: aload_0        
        //   505: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   508: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //   511: ifne            1524
        //   514: aload           7
        //   516: iconst_0       
        //   517: invokevirtual   com/netflix/msl/msg/MessageBuilder.setRenewable:(Z)Lcom/netflix/msl/msg/MessageBuilder;
        //   520: pop            
        //   521: aload_0        
        //   522: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   525: aload_0        
        //   526: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   529: aload           8
        //   531: aload_0        
        //   532: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //   535: aload           7
        //   537: iconst_0       
        //   538: invokestatic    com/netflix/msl/msg/MslControl.access$800:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/io/OutputStream;Lcom/netflix/msl/msg/MessageBuilder;Z)Lcom/netflix/msl/msg/MslControl$SendResult;
        //   541: pop            
        //   542: aload_0        
        //   543: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   546: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //   549: ifeq            568
        //   552: aload_0        
        //   553: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   556: aload_0        
        //   557: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   560: aload           7
        //   562: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   565: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   568: aconst_null    
        //   569: areturn        
        //   570: astore          4
        //   572: aload           6
        //   574: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   577: aconst_null    
        //   578: areturn        
        //   579: astore          7
        //   581: aload           7
        //   583: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   586: istore_1       
        //   587: iload_1        
        //   588: ifeq            598
        //   591: aload           6
        //   593: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   596: aconst_null    
        //   597: areturn        
        //   598: aload           6
        //   600: invokevirtual   com/netflix/msl/msg/MessageInputStream.getIdentity:()Ljava/lang/String;
        //   603: astore          8
        //   605: aload           7
        //   607: invokevirtual   com/netflix/msl/MslException.getError:()Lcom/netflix/msl/MslError;
        //   610: astore          9
        //   612: aload           4
        //   614: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageCapabilities:()Lcom/netflix/msl/msg/MessageCapabilities;
        //   617: astore          4
        //   619: aload           4
        //   621: ifnull          722
        //   624: aload           4
        //   626: invokevirtual   com/netflix/msl/msg/MessageCapabilities.getLanguages:()Ljava/util/List;
        //   629: astore          4
        //   631: aload_0        
        //   632: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   635: invokestatic    com/netflix/msl/msg/MslControl.access$500:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/ErrorMessageRegistry;
        //   638: aload           9
        //   640: aload           4
        //   642: invokeinterface com/netflix/msl/msg/ErrorMessageRegistry.getUserMessage:(Lcom/netflix/msl/MslError;Ljava/util/List;)Ljava/lang/String;
        //   647: astore          4
        //   649: aload_0        
        //   650: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   653: aload           8
        //   655: aload           7
        //   657: invokevirtual   com/netflix/msl/MslException.getMessageId:()Ljava/lang/Long;
        //   660: aload           9
        //   662: aload           4
        //   664: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   667: astore          4
        //   669: aload           5
        //   671: ifnull          683
        //   674: aload           5
        //   676: aload           4
        //   678: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   683: aload_0        
        //   684: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   687: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   690: aload_0        
        //   691: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   694: aload_0        
        //   695: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //   698: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   701: aload           4
        //   703: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   706: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   709: aload           7
        //   711: athrow         
        //   712: astore          4
        //   714: aload           6
        //   716: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   719: aload           4
        //   721: athrow         
        //   722: aconst_null    
        //   723: astore          4
        //   725: goto            631
        //   728: astore          4
        //   730: aload           4
        //   732: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   735: istore_1       
        //   736: iload_1        
        //   737: ifeq            747
        //   740: aload           6
        //   742: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   745: aconst_null    
        //   746: areturn        
        //   747: new             Lcom/netflix/msl/MslErrorResponseException;
        //   750: dup            
        //   751: ldc             "Error creating an automatic handshake response."
        //   753: aload           4
        //   755: aload           7
        //   757: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   760: athrow         
        //   761: astore          7
        //   763: aload           7
        //   765: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   768: istore_1       
        //   769: iload_1        
        //   770: ifeq            780
        //   773: aload           6
        //   775: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   778: aconst_null    
        //   779: areturn        
        //   780: aload           6
        //   782: invokevirtual   com/netflix/msl/msg/MessageInputStream.getIdentity:()Ljava/lang/String;
        //   785: astore          8
        //   787: aload           4
        //   789: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageId:()J
        //   792: lstore_2       
        //   793: aload_0        
        //   794: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   797: aload           8
        //   799: lload_2        
        //   800: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   803: getstatic       com/netflix/msl/MslError.INTERNAL_EXCEPTION:Lcom/netflix/msl/MslError;
        //   806: aconst_null    
        //   807: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //   810: astore          4
        //   812: aload           5
        //   814: ifnull          826
        //   817: aload           5
        //   819: aload           4
        //   821: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //   826: aload_0        
        //   827: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   830: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //   833: aload_0        
        //   834: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   837: aload_0        
        //   838: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //   841: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   844: aload           4
        //   846: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //   849: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   852: new             Lcom/netflix/msl/MslInternalException;
        //   855: dup            
        //   856: ldc             "Error creating an automatic handshake response."
        //   858: aload           7
        //   860: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   863: athrow         
        //   864: astore          4
        //   866: aload           4
        //   868: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   871: istore_1       
        //   872: iload_1        
        //   873: ifeq            883
        //   876: aload           6
        //   878: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   881: aconst_null    
        //   882: areturn        
        //   883: new             Lcom/netflix/msl/MslErrorResponseException;
        //   886: dup            
        //   887: ldc             "Error creating an automatic handshake response."
        //   889: aload           4
        //   891: aload           7
        //   893: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //   896: athrow         
        //   897: astore          4
        //   899: aload_0        
        //   900: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   903: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //   906: ifeq            925
        //   909: aload_0        
        //   910: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   913: aload_0        
        //   914: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   917: aload           7
        //   919: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   922: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   925: aconst_null    
        //   926: areturn        
        //   927: astore          8
        //   929: aload           8
        //   931: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   934: istore_1       
        //   935: iload_1        
        //   936: ifeq            967
        //   939: aload_0        
        //   940: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   943: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //   946: ifeq            965
        //   949: aload_0        
        //   950: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   953: aload_0        
        //   954: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //   957: aload           7
        //   959: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   962: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   965: aconst_null    
        //   966: areturn        
        //   967: aload           6
        //   969: invokevirtual   com/netflix/msl/msg/MessageInputStream.getIdentity:()Ljava/lang/String;
        //   972: astore          6
        //   974: aload           4
        //   976: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageId:()J
        //   979: lstore_2       
        //   980: aload           8
        //   982: invokevirtual   com/netflix/msl/MslException.getError:()Lcom/netflix/msl/MslError;
        //   985: astore          9
        //   987: aload           4
        //   989: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageCapabilities:()Lcom/netflix/msl/msg/MessageCapabilities;
        //   992: astore          4
        //   994: aload           4
        //   996: ifnull          1117
        //   999: aload           4
        //  1001: invokevirtual   com/netflix/msl/msg/MessageCapabilities.getLanguages:()Ljava/util/List;
        //  1004: astore          4
        //  1006: aload_0        
        //  1007: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1010: invokestatic    com/netflix/msl/msg/MslControl.access$500:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/ErrorMessageRegistry;
        //  1013: aload           9
        //  1015: aload           4
        //  1017: invokeinterface com/netflix/msl/msg/ErrorMessageRegistry.getUserMessage:(Lcom/netflix/msl/MslError;Ljava/util/List;)Ljava/lang/String;
        //  1022: astore          4
        //  1024: aload_0        
        //  1025: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1028: aload           6
        //  1030: lload_2        
        //  1031: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1034: aload           9
        //  1036: aload           4
        //  1038: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //  1041: astore          4
        //  1043: aload           5
        //  1045: ifnull          1057
        //  1048: aload           5
        //  1050: aload           4
        //  1052: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //  1057: aload_0        
        //  1058: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1061: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //  1064: aload_0        
        //  1065: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1068: aload_0        
        //  1069: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //  1072: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //  1075: aload           4
        //  1077: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //  1080: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //  1083: aload           8
        //  1085: athrow         
        //  1086: astore          4
        //  1088: aload_0        
        //  1089: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1092: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //  1095: ifeq            1114
        //  1098: aload_0        
        //  1099: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1102: aload_0        
        //  1103: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1106: aload           7
        //  1108: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //  1111: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //  1114: aload           4
        //  1116: athrow         
        //  1117: aconst_null    
        //  1118: astore          4
        //  1120: goto            1006
        //  1123: astore          4
        //  1125: aload           4
        //  1127: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //  1130: istore_1       
        //  1131: iload_1        
        //  1132: ifeq            1163
        //  1135: aload_0        
        //  1136: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1139: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //  1142: ifeq            1161
        //  1145: aload_0        
        //  1146: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1149: aload_0        
        //  1150: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1153: aload           7
        //  1155: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //  1158: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //  1161: aconst_null    
        //  1162: areturn        
        //  1163: new             Lcom/netflix/msl/MslErrorResponseException;
        //  1166: dup            
        //  1167: ldc             "Error sending an automatic handshake response."
        //  1169: aload           4
        //  1171: aload           8
        //  1173: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //  1176: athrow         
        //  1177: astore          8
        //  1179: aload           8
        //  1181: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //  1184: istore_1       
        //  1185: iload_1        
        //  1186: ifeq            1217
        //  1189: aload_0        
        //  1190: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1193: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //  1196: ifeq            1215
        //  1199: aload_0        
        //  1200: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1203: aload_0        
        //  1204: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1207: aload           7
        //  1209: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //  1212: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //  1215: aconst_null    
        //  1216: areturn        
        //  1217: aload           6
        //  1219: invokevirtual   com/netflix/msl/msg/MessageInputStream.getIdentity:()Ljava/lang/String;
        //  1222: astore          6
        //  1224: aload           4
        //  1226: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageId:()J
        //  1229: lstore_2       
        //  1230: aload_0        
        //  1231: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1234: aload           6
        //  1236: lload_2        
        //  1237: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1240: getstatic       com/netflix/msl/MslError.MSL_COMMS_FAILURE:Lcom/netflix/msl/MslError;
        //  1243: aconst_null    
        //  1244: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //  1247: astore          4
        //  1249: aload           5
        //  1251: ifnull          1263
        //  1254: aload           5
        //  1256: aload           4
        //  1258: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //  1263: aload_0        
        //  1264: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1267: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //  1270: aload_0        
        //  1271: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1274: aload_0        
        //  1275: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //  1278: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //  1281: aload           4
        //  1283: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //  1286: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //  1289: aload           8
        //  1291: athrow         
        //  1292: astore          4
        //  1294: aload           4
        //  1296: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //  1299: istore_1       
        //  1300: iload_1        
        //  1301: ifeq            1332
        //  1304: aload_0        
        //  1305: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1308: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //  1311: ifeq            1330
        //  1314: aload_0        
        //  1315: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1318: aload_0        
        //  1319: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1322: aload           7
        //  1324: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //  1327: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //  1330: aconst_null    
        //  1331: areturn        
        //  1332: new             Lcom/netflix/msl/MslErrorResponseException;
        //  1335: dup            
        //  1336: ldc             "Error sending an automatic handshake response."
        //  1338: aload           4
        //  1340: aload           8
        //  1342: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //  1345: athrow         
        //  1346: astore          8
        //  1348: aload           8
        //  1350: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //  1353: istore_1       
        //  1354: iload_1        
        //  1355: ifeq            1386
        //  1358: aload_0        
        //  1359: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1362: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //  1365: ifeq            1384
        //  1368: aload_0        
        //  1369: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1372: aload_0        
        //  1373: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1376: aload           7
        //  1378: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //  1381: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //  1384: aconst_null    
        //  1385: areturn        
        //  1386: aload           6
        //  1388: invokevirtual   com/netflix/msl/msg/MessageInputStream.getIdentity:()Ljava/lang/String;
        //  1391: astore          6
        //  1393: aload           4
        //  1395: invokevirtual   com/netflix/msl/msg/MessageHeader.getMessageId:()J
        //  1398: lstore_2       
        //  1399: aload_0        
        //  1400: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1403: aload           6
        //  1405: lload_2        
        //  1406: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1409: getstatic       com/netflix/msl/MslError.INTERNAL_EXCEPTION:Lcom/netflix/msl/MslError;
        //  1412: aconst_null    
        //  1413: invokestatic    com/netflix/msl/msg/MessageBuilder.createErrorResponse:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Ljava/lang/Long;Lcom/netflix/msl/MslError;Ljava/lang/String;)Lcom/netflix/msl/msg/ErrorHeader;
        //  1416: astore          4
        //  1418: aload           5
        //  1420: ifnull          1432
        //  1423: aload           5
        //  1425: aload           4
        //  1427: invokeinterface com/netflix/msl/msg/MessageDebugContext.sentHeader:(Lcom/netflix/msl/msg/Header;)V
        //  1432: aload_0        
        //  1433: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1436: invokestatic    com/netflix/msl/msg/MslControl.access$600:(Lcom/netflix/msl/msg/MslControl;)Lcom/netflix/msl/msg/MessageStreamFactory;
        //  1439: aload_0        
        //  1440: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1443: aload_0        
        //  1444: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //  1447: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //  1450: aload           4
        //  1452: invokevirtual   com/netflix/msl/msg/MessageStreamFactory.createOutputStream:(Lcom/netflix/msl/util/MslContext;Ljava/io/OutputStream;Ljava/nio/charset/Charset;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MessageOutputStream;
        //  1455: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //  1458: new             Lcom/netflix/msl/MslInternalException;
        //  1461: dup            
        //  1462: ldc             "Error sending an automatic handshake response."
        //  1464: aload           8
        //  1466: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1469: athrow         
        //  1470: astore          4
        //  1472: aload           4
        //  1474: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //  1477: istore_1       
        //  1478: iload_1        
        //  1479: ifeq            1510
        //  1482: aload_0        
        //  1483: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1486: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //  1489: ifeq            1508
        //  1492: aload_0        
        //  1493: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1496: aload_0        
        //  1497: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1500: aload           7
        //  1502: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //  1505: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //  1508: aconst_null    
        //  1509: areturn        
        //  1510: new             Lcom/netflix/msl/MslErrorResponseException;
        //  1513: dup            
        //  1514: ldc             "Error sending an automatic handshake response."
        //  1516: aload           4
        //  1518: aload           8
        //  1520: invokespecial   com/netflix/msl/MslErrorResponseException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Throwable;)V
        //  1523: athrow         
        //  1524: new             Lcom/netflix/msl/msg/MslControl$RequestService;
        //  1527: dup            
        //  1528: aload_0        
        //  1529: getfield        com/netflix/msl/msg/MslControl$ReceiveService.this$0:Lcom/netflix/msl/msg/MslControl;
        //  1532: aload_0        
        //  1533: getfield        com/netflix/msl/msg/MslControl$ReceiveService.ctx:Lcom/netflix/msl/util/MslContext;
        //  1536: aload           8
        //  1538: aload_0        
        //  1539: getfield        com/netflix/msl/msg/MslControl$ReceiveService.in:Ljava/io/InputStream;
        //  1542: aload_0        
        //  1543: getfield        com/netflix/msl/msg/MslControl$ReceiveService.out:Ljava/io/OutputStream;
        //  1546: aload           7
        //  1548: aload_0        
        //  1549: getfield        com/netflix/msl/msg/MslControl$ReceiveService.timeout:I
        //  1552: iconst_1       
        //  1553: invokespecial   com/netflix/msl/msg/MslControl$RequestService.<init>:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/io/InputStream;Ljava/io/OutputStream;Lcom/netflix/msl/msg/MessageBuilder;II)V
        //  1556: invokevirtual   com/netflix/msl/msg/MslControl$RequestService.call:()Lcom/netflix/msl/msg/MslControl$MslChannel;
        //  1559: astore          4
        //  1561: aload           4
        //  1563: ifnull          1572
        //  1566: aload           4
        //  1568: getfield        com/netflix/msl/msg/MslControl$MslChannel.input:Lcom/netflix/msl/msg/MessageInputStream;
        //  1571: areturn        
        //  1572: aconst_null    
        //  1573: areturn        
        //  1574: astore          8
        //  1576: goto            491
        //  1579: astore          4
        //  1581: goto            577
        //  1584: astore          4
        //  1586: goto            596
        //  1589: astore          4
        //  1591: goto            745
        //  1594: astore          4
        //  1596: goto            778
        //  1599: astore          4
        //  1601: goto            881
        //  1604: astore          5
        //  1606: goto            719
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  11     33     48     52     Ljava/lang/InterruptedException;
        //  11     33     52     224    Lcom/netflix/msl/MslException;
        //  11     33     224    329    Ljava/lang/Throwable;
        //  64     78     198    224    Ljava/lang/Throwable;
        //  83     90     198    224    Ljava/lang/Throwable;
        //  90     134    198    224    Ljava/lang/Throwable;
        //  139    148    198    224    Ljava/lang/Throwable;
        //  148    174    198    224    Ljava/lang/Throwable;
        //  182    189    198    224    Ljava/lang/Throwable;
        //  236    251    303    329    Ljava/lang/Throwable;
        //  256    265    303    329    Ljava/lang/Throwable;
        //  265    291    303    329    Ljava/lang/Throwable;
        //  329    335    342    464    Ljava/lang/Throwable;
        //  354    386    438    464    Ljava/lang/Throwable;
        //  391    400    438    464    Ljava/lang/Throwable;
        //  400    426    438    464    Ljava/lang/Throwable;
        //  464    486    570    579    Ljava/lang/InterruptedException;
        //  464    486    579    1594   Lcom/netflix/msl/MslException;
        //  464    486    761    1604   Ljava/lang/Throwable;
        //  464    486    712    722    Any
        //  486    491    1574   1579   Ljava/io/IOException;
        //  514    542    897    927    Ljava/lang/InterruptedException;
        //  514    542    927    1177   Lcom/netflix/msl/MslException;
        //  514    542    1177   1346   Ljava/io/IOException;
        //  514    542    1346   1524   Ljava/lang/Throwable;
        //  514    542    1086   1117   Any
        //  572    577    1579   1584   Ljava/io/IOException;
        //  581    587    712    722    Any
        //  591    596    1584   1589   Ljava/io/IOException;
        //  598    619    728    761    Ljava/lang/Throwable;
        //  598    619    712    722    Any
        //  624    631    728    761    Ljava/lang/Throwable;
        //  624    631    712    722    Any
        //  631    669    728    761    Ljava/lang/Throwable;
        //  631    669    712    722    Any
        //  674    683    728    761    Ljava/lang/Throwable;
        //  674    683    712    722    Any
        //  683    709    728    761    Ljava/lang/Throwable;
        //  683    709    712    722    Any
        //  709    712    712    722    Any
        //  714    719    1604   1609   Ljava/io/IOException;
        //  730    736    712    722    Any
        //  740    745    1589   1594   Ljava/io/IOException;
        //  747    761    712    722    Any
        //  763    769    712    722    Any
        //  773    778    1594   1599   Ljava/io/IOException;
        //  780    812    864    897    Ljava/lang/Throwable;
        //  780    812    712    722    Any
        //  817    826    864    897    Ljava/lang/Throwable;
        //  817    826    712    722    Any
        //  826    852    864    897    Ljava/lang/Throwable;
        //  826    852    712    722    Any
        //  852    864    712    722    Any
        //  866    872    712    722    Any
        //  876    881    1599   1604   Ljava/io/IOException;
        //  883    897    712    722    Any
        //  929    935    1086   1117   Any
        //  967    994    1123   1177   Ljava/lang/Throwable;
        //  967    994    1086   1117   Any
        //  999    1006   1123   1177   Ljava/lang/Throwable;
        //  999    1006   1086   1117   Any
        //  1006   1043   1123   1177   Ljava/lang/Throwable;
        //  1006   1043   1086   1117   Any
        //  1048   1057   1123   1177   Ljava/lang/Throwable;
        //  1048   1057   1086   1117   Any
        //  1057   1083   1123   1177   Ljava/lang/Throwable;
        //  1057   1083   1086   1117   Any
        //  1083   1086   1086   1117   Any
        //  1125   1131   1086   1117   Any
        //  1163   1177   1086   1117   Any
        //  1179   1185   1086   1117   Any
        //  1217   1249   1292   1346   Ljava/lang/Throwable;
        //  1217   1249   1086   1117   Any
        //  1254   1263   1292   1346   Ljava/lang/Throwable;
        //  1254   1263   1086   1117   Any
        //  1263   1289   1292   1346   Ljava/lang/Throwable;
        //  1263   1289   1086   1117   Any
        //  1289   1292   1086   1117   Any
        //  1294   1300   1086   1117   Any
        //  1332   1346   1086   1117   Any
        //  1348   1354   1086   1117   Any
        //  1386   1418   1470   1524   Ljava/lang/Throwable;
        //  1386   1418   1086   1117   Any
        //  1423   1432   1470   1524   Ljava/lang/Throwable;
        //  1423   1432   1086   1117   Any
        //  1432   1458   1470   1524   Ljava/lang/Throwable;
        //  1432   1458   1086   1117   Any
        //  1458   1470   1086   1117   Any
        //  1472   1478   1086   1117   Any
        //  1510   1524   1086   1117   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 741, Size: 741
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
