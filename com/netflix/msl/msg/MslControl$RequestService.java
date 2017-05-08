// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.io.Url;
import java.io.OutputStream;
import java.io.InputStream;
import com.netflix.msl.util.MslContext;
import java.util.concurrent.Callable;

class MslControl$RequestService implements Callable<MslControl$MslChannel>
{
    private MessageBuilder builder;
    private final MslContext ctx;
    private InputStream in;
    private boolean maxMessagesHit;
    private final int msgCount;
    private final MessageContext msgCtx;
    private boolean openedStreams;
    private OutputStream out;
    private final Url remoteEntity;
    final /* synthetic */ MslControl this$0;
    private final int timeout;
    
    public MslControl$RequestService(final MslControl this$0, final MslContext ctx, final MessageContext msgCtx, final Url remoteEntity, final int timeout) {
        this.this$0 = this$0;
        this.maxMessagesHit = false;
        this.ctx = ctx;
        this.msgCtx = msgCtx;
        this.remoteEntity = remoteEntity;
        this.in = null;
        this.out = null;
        this.openedStreams = false;
        this.builder = null;
        this.timeout = timeout;
        this.msgCount = 0;
    }
    
    public MslControl$RequestService(final MslControl this$0, final MslContext ctx, final MessageContext msgCtx, final Url remoteEntity, final MessageBuilder builder, final int timeout, final int msgCount) {
        this.this$0 = this$0;
        this.maxMessagesHit = false;
        this.ctx = ctx;
        this.msgCtx = msgCtx;
        this.remoteEntity = remoteEntity;
        this.in = null;
        this.out = null;
        this.openedStreams = false;
        this.builder = builder;
        this.timeout = timeout;
        this.msgCount = msgCount;
    }
    
    public MslControl$RequestService(final MslControl this$0, final MslContext ctx, final MessageContext msgCtx, final InputStream in, final OutputStream out, final int timeout) {
        this.this$0 = this$0;
        this.maxMessagesHit = false;
        this.ctx = ctx;
        this.msgCtx = msgCtx;
        this.remoteEntity = null;
        this.in = in;
        this.out = out;
        this.openedStreams = false;
        this.builder = null;
        this.timeout = timeout;
        this.msgCount = 0;
    }
    
    public MslControl$RequestService(final MslControl this$0, final MslContext ctx, final MessageContext msgCtx, final InputStream in, final OutputStream out, final MessageBuilder builder, final int timeout, final int msgCount) {
        this.this$0 = this$0;
        this.maxMessagesHit = false;
        this.ctx = ctx;
        this.msgCtx = msgCtx;
        this.remoteEntity = null;
        this.in = in;
        this.out = out;
        this.openedStreams = false;
        this.builder = builder;
        this.timeout = timeout;
        this.msgCount = msgCount;
    }
    
    private MslControl$MslChannel execute(final MessageContext p0, final MessageBuilder p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload           4
        //     2: iconst_2       
        //     3: iadd           
        //     4: bipush          12
        //     6: if_icmple       33
        //     9: aload_0        
        //    10: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //    13: aload_0        
        //    14: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //    17: aload_2        
        //    18: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //    21: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //    24: aload_0        
        //    25: iconst_1       
        //    26: putfield        com/netflix/msl/msg/MslControl$RequestService.maxMessagesHit:Z
        //    29: aconst_null    
        //    30: astore_2       
        //    31: aload_2        
        //    32: areturn        
        //    33: aload_0        
        //    34: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //    37: aload_0        
        //    38: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //    41: aload_1        
        //    42: aload_0        
        //    43: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //    46: aload_0        
        //    47: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //    50: aload_2        
        //    51: iconst_1       
        //    52: aload_0        
        //    53: getfield        com/netflix/msl/msg/MslControl$RequestService.openedStreams:Z
        //    56: iload_3        
        //    57: invokestatic    com/netflix/msl/msg/MslControl.access$1100:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/io/InputStream;Ljava/io/OutputStream;Lcom/netflix/msl/msg/MessageBuilder;ZZI)Lcom/netflix/msl/msg/MslControl$SendReceiveResult;
        //    60: astore          7
        //    62: aload           7
        //    64: getfield        com/netflix/msl/msg/MslControl$SendReceiveResult.request:Lcom/netflix/msl/msg/MessageOutputStream;
        //    67: astore          8
        //    69: aload           7
        //    71: getfield        com/netflix/msl/msg/MslControl$SendReceiveResult.response:Lcom/netflix/msl/msg/MessageInputStream;
        //    74: astore          6
        //    76: iload           4
        //    78: iconst_2       
        //    79: iadd           
        //    80: istore          4
        //    82: aload           6
        //    84: invokevirtual   com/netflix/msl/msg/MessageInputStream.getMessageHeader:()Lcom/netflix/msl/msg/MessageHeader;
        //    87: astore_2       
        //    88: aload_2        
        //    89: ifnonnull       263
        //    92: aload           8
        //    94: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //    97: aload           6
        //    99: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   102: aload           6
        //   104: invokevirtual   com/netflix/msl/msg/MessageInputStream.getErrorHeader:()Lcom/netflix/msl/msg/ErrorHeader;
        //   107: astore_2       
        //   108: aload_0        
        //   109: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   112: aload_0        
        //   113: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   116: aload_1        
        //   117: aload           7
        //   119: aload_2        
        //   120: invokestatic    com/netflix/msl/msg/MslControl.access$1200:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MslControl$SendResult;Lcom/netflix/msl/msg/ErrorHeader;)Lcom/netflix/msl/msg/MslControl$ErrorResult;
        //   123: astore_2       
        //   124: aload_2        
        //   125: ifnonnull       159
        //   128: new             Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   131: dup            
        //   132: aload           6
        //   134: aconst_null    
        //   135: invokespecial   com/netflix/msl/msg/MslControl$MslChannel.<init>:(Lcom/netflix/msl/msg/MessageInputStream;Lcom/netflix/msl/msg/MessageOutputStream;)V
        //   138: areturn        
        //   139: astore_2       
        //   140: aload_2        
        //   141: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   144: ifeq            97
        //   147: aconst_null    
        //   148: areturn        
        //   149: astore_2       
        //   150: aload_2        
        //   151: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   154: ifeq            102
        //   157: aconst_null    
        //   158: areturn        
        //   159: aload_2        
        //   160: getfield        com/netflix/msl/msg/MslControl$ErrorResult.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //   163: astore_1       
        //   164: aload_2        
        //   165: getfield        com/netflix/msl/msg/MslControl$ErrorResult.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //   168: astore_2       
        //   169: aload_0        
        //   170: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   173: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //   176: ifne            250
        //   179: new             Lcom/netflix/msl/msg/MslControl$RequestService;
        //   182: dup            
        //   183: aload_0        
        //   184: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   187: aload_0        
        //   188: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   191: aload_2        
        //   192: aload_0        
        //   193: getfield        com/netflix/msl/msg/MslControl$RequestService.remoteEntity:Lcom/netflix/msl/io/Url;
        //   196: aload_1        
        //   197: iload_3        
        //   198: iload           4
        //   200: invokespecial   com/netflix/msl/msg/MslControl$RequestService.<init>:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/io/Url;Lcom/netflix/msl/msg/MessageBuilder;II)V
        //   203: astore_2       
        //   204: aload_2        
        //   205: invokevirtual   com/netflix/msl/msg/MslControl$RequestService.call:()Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   208: astore_1       
        //   209: aload_0        
        //   210: aload_2        
        //   211: getfield        com/netflix/msl/msg/MslControl$RequestService.maxMessagesHit:Z
        //   214: putfield        com/netflix/msl/msg/MslControl$RequestService.maxMessagesHit:Z
        //   217: aload_0        
        //   218: getfield        com/netflix/msl/msg/MslControl$RequestService.maxMessagesHit:Z
        //   221: ifne            239
        //   224: aload_1        
        //   225: astore_2       
        //   226: aload_1        
        //   227: ifnull          31
        //   230: aload_1        
        //   231: astore_2       
        //   232: aload_1        
        //   233: getfield        com/netflix/msl/msg/MslControl$MslChannel.input:Lcom/netflix/msl/msg/MessageInputStream;
        //   236: ifnonnull       31
        //   239: new             Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   242: dup            
        //   243: aload           6
        //   245: aconst_null    
        //   246: invokespecial   com/netflix/msl/msg/MslControl$MslChannel.<init>:(Lcom/netflix/msl/msg/MessageInputStream;Lcom/netflix/msl/msg/MessageOutputStream;)V
        //   249: areturn        
        //   250: aload_0        
        //   251: aload_2        
        //   252: aload_1        
        //   253: iload_3        
        //   254: iload           4
        //   256: invokespecial   com/netflix/msl/msg/MslControl$RequestService.execute:(Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageBuilder;II)Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   259: astore_1       
        //   260: goto            217
        //   263: aload_0        
        //   264: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   267: invokevirtual   com/netflix/msl/util/MslContext.isPeerToPeer:()Z
        //   270: ifne            381
        //   273: aload           7
        //   275: getfield        com/netflix/msl/msg/MslControl$SendReceiveResult.handshake:Z
        //   278: ifne            293
        //   281: new             Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   284: dup            
        //   285: aload           6
        //   287: aload           8
        //   289: invokespecial   com/netflix/msl/msg/MslControl$MslChannel.<init>:(Lcom/netflix/msl/msg/MessageInputStream;Lcom/netflix/msl/msg/MessageOutputStream;)V
        //   292: areturn        
        //   293: aload           8
        //   295: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   298: aload           6
        //   300: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   303: new             Lcom/netflix/msl/msg/MslControl$ResendMessageContext;
        //   306: dup            
        //   307: aconst_null    
        //   308: aload_1        
        //   309: invokespecial   com/netflix/msl/msg/MslControl$ResendMessageContext.<init>:(Ljava/util/List;Lcom/netflix/msl/msg/MessageContext;)V
        //   312: astore          6
        //   314: aload_0        
        //   315: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   318: aload_0        
        //   319: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   322: aload_1        
        //   323: aload_2        
        //   324: invokestatic    com/netflix/msl/msg/MslControl.access$700:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageHeader;)Lcom/netflix/msl/msg/MessageBuilder;
        //   327: astore_1       
        //   328: new             Lcom/netflix/msl/msg/MslControl$RequestService;
        //   331: dup            
        //   332: aload_0        
        //   333: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   336: aload_0        
        //   337: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   340: aload           6
        //   342: aload_0        
        //   343: getfield        com/netflix/msl/msg/MslControl$RequestService.remoteEntity:Lcom/netflix/msl/io/Url;
        //   346: aload_1        
        //   347: iload_3        
        //   348: iload           4
        //   350: invokespecial   com/netflix/msl/msg/MslControl$RequestService.<init>:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/io/Url;Lcom/netflix/msl/msg/MessageBuilder;II)V
        //   353: invokevirtual   com/netflix/msl/msg/MslControl$RequestService.call:()Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   356: areturn        
        //   357: astore          7
        //   359: aload           7
        //   361: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   364: ifeq            298
        //   367: aconst_null    
        //   368: areturn        
        //   369: astore          6
        //   371: aload           6
        //   373: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   376: ifeq            303
        //   379: aconst_null    
        //   380: areturn        
        //   381: aload           7
        //   383: getfield        com/netflix/msl/msg/MslControl$SendReceiveResult.handshake:Z
        //   386: ifeq            453
        //   389: aload           8
        //   391: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   394: aload           6
        //   396: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   399: aload_0        
        //   400: new             Lcom/netflix/msl/msg/MslControl$ResendMessageContext;
        //   403: dup            
        //   404: aconst_null    
        //   405: aload_1        
        //   406: invokespecial   com/netflix/msl/msg/MslControl$ResendMessageContext.<init>:(Ljava/util/List;Lcom/netflix/msl/msg/MessageContext;)V
        //   409: aload_0        
        //   410: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   413: aload_0        
        //   414: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   417: aload_1        
        //   418: aload_2        
        //   419: invokestatic    com/netflix/msl/msg/MslControl.access$700:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageHeader;)Lcom/netflix/msl/msg/MessageBuilder;
        //   422: iload_3        
        //   423: iload           4
        //   425: invokespecial   com/netflix/msl/msg/MslControl$RequestService.execute:(Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageBuilder;II)Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   428: areturn        
        //   429: astore          7
        //   431: aload           7
        //   433: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   436: ifeq            394
        //   439: aconst_null    
        //   440: areturn        
        //   441: astore          6
        //   443: aload           6
        //   445: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   448: ifeq            399
        //   451: aconst_null    
        //   452: areturn        
        //   453: aload_2        
        //   454: invokevirtual   com/netflix/msl/msg/MessageHeader.getKeyRequestData:()Ljava/util/Set;
        //   457: invokeinterface java/util/Set.isEmpty:()Z
        //   462: ifeq            486
        //   465: aload_2        
        //   466: invokevirtual   com/netflix/msl/msg/MessageHeader.isRenewable:()Z
        //   469: ifeq            729
        //   472: aload_2        
        //   473: invokevirtual   com/netflix/msl/msg/MessageHeader.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   476: ifnull          729
        //   479: aload_2        
        //   480: invokevirtual   com/netflix/msl/msg/MessageHeader.getUserAuthenticationData:()Lcom/netflix/msl/userauth/UserAuthenticationData;
        //   483: ifnull          729
        //   486: new             Lcom/netflix/msl/msg/MslControl$KeyxResponseMessageContext;
        //   489: dup            
        //   490: aload_1        
        //   491: invokespecial   com/netflix/msl/msg/MslControl$KeyxResponseMessageContext.<init>:(Lcom/netflix/msl/msg/MessageContext;)V
        //   494: astore          7
        //   496: aload_0        
        //   497: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   500: aload_0        
        //   501: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   504: aload           7
        //   506: aload_2        
        //   507: invokestatic    com/netflix/msl/msg/MslControl.access$700:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageHeader;)Lcom/netflix/msl/msg/MessageBuilder;
        //   510: astore_1       
        //   511: aload           6
        //   513: invokevirtual   com/netflix/msl/msg/MessageInputStream.isHandshake:()Z
        //   516: istore          5
        //   518: iload           5
        //   520: ifne            615
        //   523: aload           8
        //   525: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   528: aload_1        
        //   529: iconst_0       
        //   530: invokevirtual   com/netflix/msl/msg/MessageBuilder.setRenewable:(Z)Lcom/netflix/msl/msg/MessageBuilder;
        //   533: pop            
        //   534: new             Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   537: dup            
        //   538: aload           6
        //   540: aload_0        
        //   541: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   544: aload_0        
        //   545: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   548: aload           7
        //   550: aload_0        
        //   551: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   554: aload_1        
        //   555: aload_0        
        //   556: getfield        com/netflix/msl/msg/MslControl$RequestService.openedStreams:Z
        //   559: invokestatic    com/netflix/msl/msg/MslControl.access$800:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;Ljava/io/OutputStream;Lcom/netflix/msl/msg/MessageBuilder;Z)Lcom/netflix/msl/msg/MslControl$SendResult;
        //   562: getfield        com/netflix/msl/msg/MslControl$SendResult.request:Lcom/netflix/msl/msg/MessageOutputStream;
        //   565: invokespecial   com/netflix/msl/msg/MslControl$MslChannel.<init>:(Lcom/netflix/msl/msg/MessageInputStream;Lcom/netflix/msl/msg/MessageOutputStream;)V
        //   568: astore_2       
        //   569: aload_0        
        //   570: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   573: aload_0        
        //   574: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   577: aload_1        
        //   578: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   581: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   584: aload_2        
        //   585: areturn        
        //   586: astore_2       
        //   587: aload_2        
        //   588: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   591: istore          5
        //   593: iload           5
        //   595: ifeq            528
        //   598: aload_0        
        //   599: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   602: aload_0        
        //   603: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   606: aload_1        
        //   607: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   610: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   613: aconst_null    
        //   614: areturn        
        //   615: aload           8
        //   617: invokevirtual   com/netflix/msl/msg/MessageOutputStream.close:()V
        //   620: aload           6
        //   622: invokevirtual   com/netflix/msl/msg/MessageInputStream.close:()V
        //   625: aload_0        
        //   626: aload           7
        //   628: aload_1        
        //   629: iload_3        
        //   630: iload           4
        //   632: invokespecial   com/netflix/msl/msg/MslControl$RequestService.execute:(Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageBuilder;II)Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   635: astore_2       
        //   636: aload_0        
        //   637: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   640: aload_0        
        //   641: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   644: aload_1        
        //   645: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   648: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   651: aload_2        
        //   652: areturn        
        //   653: astore_2       
        //   654: aload_2        
        //   655: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   658: istore          5
        //   660: iload           5
        //   662: ifeq            620
        //   665: aload_0        
        //   666: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   669: aload_0        
        //   670: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   673: aload_1        
        //   674: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   677: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   680: aconst_null    
        //   681: areturn        
        //   682: astore_2       
        //   683: aload_2        
        //   684: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   687: istore          5
        //   689: iload           5
        //   691: ifeq            625
        //   694: aload_0        
        //   695: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   698: aload_0        
        //   699: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   702: aload_1        
        //   703: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   706: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   709: aconst_null    
        //   710: areturn        
        //   711: astore_2       
        //   712: aload_0        
        //   713: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   716: aload_0        
        //   717: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   720: aload_1        
        //   721: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   724: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   727: aload_2        
        //   728: athrow         
        //   729: new             Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   732: dup            
        //   733: aload           6
        //   735: aload           8
        //   737: invokespecial   com/netflix/msl/msg/MslControl$MslChannel.<init>:(Lcom/netflix/msl/msg/MessageInputStream;Lcom/netflix/msl/msg/MessageOutputStream;)V
        //   740: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  92     97     139    149    Ljava/io/IOException;
        //  97     102    149    159    Ljava/io/IOException;
        //  293    298    357    369    Ljava/io/IOException;
        //  298    303    369    381    Ljava/io/IOException;
        //  389    394    429    441    Ljava/io/IOException;
        //  394    399    441    453    Ljava/io/IOException;
        //  511    518    711    729    Any
        //  523    528    586    615    Ljava/io/IOException;
        //  523    528    711    729    Any
        //  528    569    711    729    Any
        //  587    593    711    729    Any
        //  615    620    653    682    Ljava/io/IOException;
        //  615    620    711    729    Any
        //  620    625    682    711    Ljava/io/IOException;
        //  620    625    711    729    Any
        //  625    636    711    729    Any
        //  654    660    711    729    Any
        //  683    689    711    729    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
    
    @Override
    public MslControl$MslChannel call() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //     4: ifnull          14
        //     7: aload_0        
        //     8: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //    11: ifnonnull       279
        //    14: aload_0        
        //    15: getfield        com/netflix/msl/msg/MslControl$RequestService.remoteEntity:Lcom/netflix/msl/io/Url;
        //    18: aload_0        
        //    19: getfield        com/netflix/msl/msg/MslControl$RequestService.timeout:I
        //    22: invokeinterface com/netflix/msl/io/Url.setTimeout:(I)V
        //    27: invokestatic    java/lang/System.currentTimeMillis:()J
        //    30: lstore_2       
        //    31: aload_0        
        //    32: getfield        com/netflix/msl/msg/MslControl$RequestService.remoteEntity:Lcom/netflix/msl/io/Url;
        //    35: invokeinterface com/netflix/msl/io/Url.openConnection:()Lcom/netflix/msl/io/Url$Connection;
        //    40: astore          4
        //    42: aload_0        
        //    43: aload           4
        //    45: invokeinterface com/netflix/msl/io/Url$Connection.getOutputStream:()Ljava/io/OutputStream;
        //    50: putfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //    53: aload_0        
        //    54: new             Lcom/netflix/msl/msg/MslControl$RequestService$DelayedInputStream;
        //    57: dup            
        //    58: aload_0        
        //    59: aload           4
        //    61: invokespecial   com/netflix/msl/msg/MslControl$RequestService$DelayedInputStream.<init>:(Lcom/netflix/msl/msg/MslControl$RequestService;Lcom/netflix/msl/io/Url$Connection;)V
        //    64: putfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //    67: aload_0        
        //    68: getfield        com/netflix/msl/msg/MslControl$RequestService.timeout:I
        //    71: invokestatic    java/lang/System.currentTimeMillis:()J
        //    74: lload_2        
        //    75: lsub           
        //    76: l2i            
        //    77: isub           
        //    78: istore_1       
        //    79: aload_0        
        //    80: iconst_1       
        //    81: putfield        com/netflix/msl/msg/MslControl$RequestService.openedStreams:Z
        //    84: aload_0        
        //    85: getfield        com/netflix/msl/msg/MslControl$RequestService.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //    88: ifnonnull       110
        //    91: aload_0        
        //    92: aload_0        
        //    93: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //    96: aload_0        
        //    97: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   100: aload_0        
        //   101: getfield        com/netflix/msl/msg/MslControl$RequestService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //   104: invokestatic    com/netflix/msl/msg/MslControl.access$1300:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/msg/MessageContext;)Lcom/netflix/msl/msg/MessageBuilder;
        //   107: putfield        com/netflix/msl/msg/MslControl$RequestService.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //   110: aload_0        
        //   111: aload_0        
        //   112: getfield        com/netflix/msl/msg/MslControl$RequestService.msgCtx:Lcom/netflix/msl/msg/MessageContext;
        //   115: aload_0        
        //   116: getfield        com/netflix/msl/msg/MslControl$RequestService.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //   119: iload_1        
        //   120: aload_0        
        //   121: getfield        com/netflix/msl/msg/MslControl$RequestService.msgCount:I
        //   124: invokespecial   com/netflix/msl/msg/MslControl$RequestService.execute:(Lcom/netflix/msl/msg/MessageContext;Lcom/netflix/msl/msg/MessageBuilder;II)Lcom/netflix/msl/msg/MslControl$MslChannel;
        //   127: astore          4
        //   129: aload           4
        //   131: ifnull          150
        //   134: aload           4
        //   136: getfield        com/netflix/msl/msg/MslControl$MslChannel.output:Lcom/netflix/msl/msg/MessageOutputStream;
        //   139: ifnull          150
        //   142: aload           4
        //   144: getfield        com/netflix/msl/msg/MslControl$MslChannel.output:Lcom/netflix/msl/msg/MessageOutputStream;
        //   147: invokevirtual   com/netflix/msl/msg/MessageOutputStream.stopCaching:()V
        //   150: aload           4
        //   152: areturn        
        //   153: astore          4
        //   155: aload_0        
        //   156: getfield        com/netflix/msl/msg/MslControl$RequestService.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //   159: ifnull          180
        //   162: aload_0        
        //   163: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   166: aload_0        
        //   167: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   170: aload_0        
        //   171: getfield        com/netflix/msl/msg/MslControl$RequestService.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //   174: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   177: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   180: aload_0        
        //   181: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   184: ifnull          194
        //   187: aload_0        
        //   188: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   191: invokevirtual   java/io/OutputStream.close:()V
        //   194: aload_0        
        //   195: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //   198: ifnull          208
        //   201: aload_0        
        //   202: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //   205: invokevirtual   java/io/InputStream.close:()V
        //   208: aload           4
        //   210: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   213: ifeq            218
        //   216: aconst_null    
        //   217: areturn        
        //   218: aload           4
        //   220: athrow         
        //   221: astore          4
        //   223: aload_0        
        //   224: getfield        com/netflix/msl/msg/MslControl$RequestService.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //   227: ifnull          248
        //   230: aload_0        
        //   231: getfield        com/netflix/msl/msg/MslControl$RequestService.this$0:Lcom/netflix/msl/msg/MslControl;
        //   234: aload_0        
        //   235: getfield        com/netflix/msl/msg/MslControl$RequestService.ctx:Lcom/netflix/msl/util/MslContext;
        //   238: aload_0        
        //   239: getfield        com/netflix/msl/msg/MslControl$RequestService.builder:Lcom/netflix/msl/msg/MessageBuilder;
        //   242: invokevirtual   com/netflix/msl/msg/MessageBuilder.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   245: invokestatic    com/netflix/msl/msg/MslControl.access$900:(Lcom/netflix/msl/msg/MslControl;Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   248: aload_0        
        //   249: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   252: ifnull          262
        //   255: aload_0        
        //   256: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   259: invokevirtual   java/io/OutputStream.close:()V
        //   262: aload_0        
        //   263: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //   266: ifnull          276
        //   269: aload_0        
        //   270: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //   273: invokevirtual   java/io/InputStream.close:()V
        //   276: aload           4
        //   278: athrow         
        //   279: aload_0        
        //   280: getfield        com/netflix/msl/msg/MslControl$RequestService.timeout:I
        //   283: istore_1       
        //   284: goto            84
        //   287: astore          4
        //   289: aload_0        
        //   290: getfield        com/netflix/msl/msg/MslControl$RequestService.openedStreams:Z
        //   293: ifeq            310
        //   296: aload_0        
        //   297: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   300: invokevirtual   java/io/OutputStream.close:()V
        //   303: aload_0        
        //   304: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //   307: invokevirtual   java/io/InputStream.close:()V
        //   310: aconst_null    
        //   311: areturn        
        //   312: astore          4
        //   314: aload_0        
        //   315: getfield        com/netflix/msl/msg/MslControl$RequestService.openedStreams:Z
        //   318: ifeq            335
        //   321: aload_0        
        //   322: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   325: invokevirtual   java/io/OutputStream.close:()V
        //   328: aload_0        
        //   329: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //   332: invokevirtual   java/io/InputStream.close:()V
        //   335: aconst_null    
        //   336: areturn        
        //   337: astore          4
        //   339: aload_0        
        //   340: getfield        com/netflix/msl/msg/MslControl$RequestService.openedStreams:Z
        //   343: ifeq            360
        //   346: aload_0        
        //   347: getfield        com/netflix/msl/msg/MslControl$RequestService.out:Ljava/io/OutputStream;
        //   350: invokevirtual   java/io/OutputStream.close:()V
        //   353: aload_0        
        //   354: getfield        com/netflix/msl/msg/MslControl$RequestService.in:Ljava/io/InputStream;
        //   357: invokevirtual   java/io/InputStream.close:()V
        //   360: aload           4
        //   362: invokestatic    com/netflix/msl/msg/MslControl.cancelled:(Ljava/lang/Throwable;)Z
        //   365: ifeq            370
        //   368: aconst_null    
        //   369: areturn        
        //   370: aload           4
        //   372: athrow         
        //   373: astore          5
        //   375: goto            360
        //   378: astore          5
        //   380: goto            353
        //   383: astore          4
        //   385: goto            339
        //   388: astore          4
        //   390: goto            339
        //   393: astore          4
        //   395: goto            339
        //   398: astore          4
        //   400: goto            335
        //   403: astore          4
        //   405: goto            328
        //   408: astore          4
        //   410: goto            310
        //   413: astore          4
        //   415: goto            303
        //   418: astore          5
        //   420: goto            276
        //   423: astore          5
        //   425: goto            262
        //   428: astore          5
        //   430: goto            208
        //   433: astore          5
        //   435: goto            194
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  14     84     153    438    Ljava/io/IOException;
        //  14     84     221    428    Ljava/lang/RuntimeException;
        //  91     110    287    418    Ljava/lang/InterruptedException;
        //  110    129    312    408    Ljava/lang/InterruptedException;
        //  110    129    388    393    Lcom/netflix/msl/MslException;
        //  110    129    383    388    Ljava/io/IOException;
        //  110    129    337    339    Ljava/lang/RuntimeException;
        //  110    129    393    398    Ljava/util/concurrent/TimeoutException;
        //  134    150    312    408    Ljava/lang/InterruptedException;
        //  134    150    388    393    Lcom/netflix/msl/MslException;
        //  134    150    383    388    Ljava/io/IOException;
        //  134    150    337    339    Ljava/lang/RuntimeException;
        //  134    150    393    398    Ljava/util/concurrent/TimeoutException;
        //  187    194    433    438    Ljava/io/IOException;
        //  201    208    428    433    Ljava/io/IOException;
        //  255    262    423    428    Ljava/io/IOException;
        //  269    276    418    423    Ljava/io/IOException;
        //  296    303    413    418    Ljava/io/IOException;
        //  303    310    408    413    Ljava/io/IOException;
        //  321    328    403    408    Ljava/io/IOException;
        //  328    335    398    403    Ljava/io/IOException;
        //  346    353    378    383    Ljava/io/IOException;
        //  353    360    373    378    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 199, Size: 199
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
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
