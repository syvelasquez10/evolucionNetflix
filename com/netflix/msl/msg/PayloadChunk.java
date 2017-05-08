// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.util.Arrays;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslConstants;
import com.netflix.msl.util.Base64;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslUtils;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.MslConstants$CompressionAlgorithm;
import com.netflix.android.org.json.JSONString;

public class PayloadChunk implements JSONString
{
    private static final String KEY_COMPRESSION_ALGORITHM = "compressionalgo";
    private static final String KEY_DATA = "data";
    private static final String KEY_END_OF_MESSAGE = "endofmsg";
    private static final String KEY_MESSAGE_ID = "messageid";
    private static final String KEY_PAYLOAD = "payload";
    private static final String KEY_SEQUENCE_NUMBER = "sequencenumber";
    private static final String KEY_SIGNATURE = "signature";
    private final MslConstants$CompressionAlgorithm compressionAlgo;
    private final byte[] data;
    private final boolean endofmsg;
    private final long messageId;
    private final byte[] payload;
    private final long sequenceNumber;
    private final byte[] signature;
    
    public PayloadChunk(final long sequenceNumber, final long messageId, final boolean endofmsg, final MslConstants$CompressionAlgorithm compressionAlgo, final byte[] data, final ICryptoContext cryptoContext) {
        if (sequenceNumber < 0L || sequenceNumber > 9007199254740992L) {
            throw new MslInternalException("Sequence number " + sequenceNumber + " is outside the valid range.");
        }
        if (messageId < 0L || messageId > 9007199254740992L) {
            throw new MslInternalException("Message ID " + messageId + " is outside the valid range.");
        }
        Label_0288: {
            if (compressionAlgo == null) {
                break Label_0288;
            }
            final byte[] compress = MslUtils.compress(compressionAlgo, data);
            Label_0276: {
                if (compress.length >= data.length) {
                    break Label_0276;
                }
                this.compressionAlgo = compressionAlgo;
                byte[] array = compress;
                while (true) {
                    this.sequenceNumber = sequenceNumber;
                    this.messageId = messageId;
                    this.endofmsg = endofmsg;
                    this.data = data;
                    try {
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("sequencenumber", this.sequenceNumber);
                        jsonObject.put("messageid", this.messageId);
                        if (this.endofmsg) {
                            jsonObject.put("endofmsg", this.endofmsg);
                        }
                        if (this.compressionAlgo != null) {
                            jsonObject.put("compressionalgo", this.compressionAlgo.name());
                        }
                        jsonObject.put("data", Base64.encode(array));
                        this.payload = cryptoContext.encrypt(jsonObject.toString().getBytes(MslConstants.DEFAULT_CHARSET));
                        this.signature = cryptoContext.sign(this.payload);
                        return;
                        this.compressionAlgo = null;
                        array = data;
                        continue;
                        this.compressionAlgo = null;
                        array = data;
                    }
                    catch (JSONException ex) {
                        throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "payloadchunk payload", ex);
                    }
                }
            }
        }
    }
    
    public PayloadChunk(final JSONObject p0, final ICryptoContext p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: iconst_0       
        //     4: istore_3       
        //     5: aload_0        
        //     6: invokespecial   java/lang/Object.<init>:()V
        //     9: aload_0        
        //    10: aload_1        
        //    11: ldc             "payload"
        //    13: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    16: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    19: putfield        com/netflix/msl/msg/PayloadChunk.payload:[B
        //    22: aload_0        
        //    23: aload_1        
        //    24: ldc             "signature"
        //    26: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    29: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    32: putfield        com/netflix/msl/msg/PayloadChunk.signature:[B
        //    35: aload_2        
        //    36: aload_0        
        //    37: getfield        com/netflix/msl/msg/PayloadChunk.payload:[B
        //    40: aload_0        
        //    41: getfield        com/netflix/msl/msg/PayloadChunk.signature:[B
        //    44: invokeinterface com/netflix/msl/crypto/ICryptoContext.verify:([B[B)Z
        //    49: ifne            168
        //    52: new             Lcom/netflix/msl/MslCryptoException;
        //    55: dup            
        //    56: getstatic       com/netflix/msl/MslError.PAYLOAD_VERIFICATION_FAILED:Lcom/netflix/msl/MslError;
        //    59: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;)V
        //    62: athrow         
        //    63: astore_2       
        //    64: new             Lcom/netflix/msl/MslEncodingException;
        //    67: dup            
        //    68: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //    71: new             Ljava/lang/StringBuilder;
        //    74: dup            
        //    75: invokespecial   java/lang/StringBuilder.<init>:()V
        //    78: ldc             "payload chunk "
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload_1        
        //    84: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: aload_2        
        //    94: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    97: athrow         
        //    98: astore_2       
        //    99: new             Lcom/netflix/msl/MslMessageException;
        //   102: dup            
        //   103: getstatic       com/netflix/msl/MslError.PAYLOAD_INVALID:Lcom/netflix/msl/MslError;
        //   106: new             Ljava/lang/StringBuilder;
        //   109: dup            
        //   110: invokespecial   java/lang/StringBuilder.<init>:()V
        //   113: ldc             "payload chunk "
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: aload_1        
        //   119: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   128: aload_2        
        //   129: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   132: athrow         
        //   133: astore_2       
        //   134: new             Lcom/netflix/msl/MslMessageException;
        //   137: dup            
        //   138: getstatic       com/netflix/msl/MslError.PAYLOAD_SIGNATURE_INVALID:Lcom/netflix/msl/MslError;
        //   141: new             Ljava/lang/StringBuilder;
        //   144: dup            
        //   145: invokespecial   java/lang/StringBuilder.<init>:()V
        //   148: ldc             "payload chunk "
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: aload_1        
        //   154: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   163: aload_2        
        //   164: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   167: athrow         
        //   168: new             Ljava/lang/String;
        //   171: dup            
        //   172: aload_2        
        //   173: aload_0        
        //   174: getfield        com/netflix/msl/msg/PayloadChunk.payload:[B
        //   177: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   182: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   185: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   188: astore_2       
        //   189: new             Lcom/netflix/android/org/json/JSONObject;
        //   192: dup            
        //   193: aload_2        
        //   194: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   197: astore          5
        //   199: aload_0        
        //   200: aload           5
        //   202: ldc             "sequencenumber"
        //   204: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   207: putfield        com/netflix/msl/msg/PayloadChunk.sequenceNumber:J
        //   210: aload_0        
        //   211: getfield        com/netflix/msl/msg/PayloadChunk.sequenceNumber:J
        //   214: lconst_0       
        //   215: lcmp           
        //   216: iflt            230
        //   219: aload_0        
        //   220: getfield        com/netflix/msl/msg/PayloadChunk.sequenceNumber:J
        //   223: ldc2_w          9007199254740992
        //   226: lcmp           
        //   227: ifle            292
        //   230: new             Lcom/netflix/msl/MslException;
        //   233: dup            
        //   234: getstatic       com/netflix/msl/MslError.PAYLOAD_SEQUENCE_NUMBER_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   237: new             Ljava/lang/StringBuilder;
        //   240: dup            
        //   241: invokespecial   java/lang/StringBuilder.<init>:()V
        //   244: ldc             "payload chunk payload "
        //   246: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   249: aload_2        
        //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   256: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   259: athrow         
        //   260: astore_1       
        //   261: new             Lcom/netflix/msl/MslEncodingException;
        //   264: dup            
        //   265: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   268: new             Ljava/lang/StringBuilder;
        //   271: dup            
        //   272: invokespecial   java/lang/StringBuilder.<init>:()V
        //   275: ldc             "payload chunk payload "
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: aload_2        
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   287: aload_1        
        //   288: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   291: athrow         
        //   292: aload_0        
        //   293: aload           5
        //   295: ldc             "messageid"
        //   297: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   300: putfield        com/netflix/msl/msg/PayloadChunk.messageId:J
        //   303: aload_0        
        //   304: getfield        com/netflix/msl/msg/PayloadChunk.messageId:J
        //   307: lconst_0       
        //   308: lcmp           
        //   309: iflt            323
        //   312: aload_0        
        //   313: getfield        com/netflix/msl/msg/PayloadChunk.messageId:J
        //   316: ldc2_w          9007199254740992
        //   319: lcmp           
        //   320: ifle            353
        //   323: new             Lcom/netflix/msl/MslException;
        //   326: dup            
        //   327: getstatic       com/netflix/msl/MslError.PAYLOAD_MESSAGE_ID_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   330: new             Ljava/lang/StringBuilder;
        //   333: dup            
        //   334: invokespecial   java/lang/StringBuilder.<init>:()V
        //   337: ldc             "payload chunk payload "
        //   339: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   342: aload_2        
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   349: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   352: athrow         
        //   353: aload           5
        //   355: ldc             "endofmsg"
        //   357: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   360: ifeq            371
        //   363: aload           5
        //   365: ldc             "endofmsg"
        //   367: invokevirtual   com/netflix/android/org/json/JSONObject.getBoolean:(Ljava/lang/String;)Z
        //   370: istore_3       
        //   371: aload_0        
        //   372: iload_3        
        //   373: putfield        com/netflix/msl/msg/PayloadChunk.endofmsg:Z
        //   376: aload           5
        //   378: ldc             "compressionalgo"
        //   380: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   383: ifeq            463
        //   386: aload           5
        //   388: ldc             "compressionalgo"
        //   390: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   393: astore_1       
        //   394: aload_0        
        //   395: aload_1        
        //   396: invokestatic    com/netflix/msl/MslConstants$CompressionAlgorithm.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   399: putfield        com/netflix/msl/msg/PayloadChunk.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   402: aload           5
        //   404: ldc             "data"
        //   406: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   409: astore          5
        //   411: aload           5
        //   413: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   416: astore_1       
        //   417: aload_1        
        //   418: ifnull          426
        //   421: aload_1        
        //   422: arraylength    
        //   423: ifne            499
        //   426: aload           5
        //   428: invokevirtual   java/lang/String.length:()I
        //   431: ifle            471
        //   434: new             Lcom/netflix/msl/MslMessageException;
        //   437: dup            
        //   438: getstatic       com/netflix/msl/MslError.PAYLOAD_DATA_CORRUPT:Lcom/netflix/msl/MslError;
        //   441: aload           5
        //   443: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   446: athrow         
        //   447: astore          4
        //   449: new             Lcom/netflix/msl/MslMessageException;
        //   452: dup            
        //   453: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_COMPRESSION:Lcom/netflix/msl/MslError;
        //   456: aload_1        
        //   457: aload           4
        //   459: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   462: athrow         
        //   463: aload_0        
        //   464: aconst_null    
        //   465: putfield        com/netflix/msl/msg/PayloadChunk.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   468: goto            402
        //   471: aload_0        
        //   472: getfield        com/netflix/msl/msg/PayloadChunk.endofmsg:Z
        //   475: ifne            491
        //   478: new             Lcom/netflix/msl/MslMessageException;
        //   481: dup            
        //   482: getstatic       com/netflix/msl/MslError.PAYLOAD_DATA_MISSING:Lcom/netflix/msl/MslError;
        //   485: aload           5
        //   487: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   490: athrow         
        //   491: aload_0        
        //   492: iconst_0       
        //   493: newarray        B
        //   495: putfield        com/netflix/msl/msg/PayloadChunk.data:[B
        //   498: return         
        //   499: aload_0        
        //   500: getfield        com/netflix/msl/msg/PayloadChunk.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   503: ifnonnull       512
        //   506: aload_0        
        //   507: aload_1        
        //   508: putfield        com/netflix/msl/msg/PayloadChunk.data:[B
        //   511: return         
        //   512: aload_0        
        //   513: aload_0        
        //   514: getfield        com/netflix/msl/msg/PayloadChunk.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   517: aload_1        
        //   518: invokestatic    com/netflix/msl/util/MslUtils.uncompress:(Lcom/netflix/msl/MslConstants$CompressionAlgorithm;[B)[B
        //   521: putfield        com/netflix/msl/msg/PayloadChunk.data:[B
        //   524: return         
        //   525: astore_1       
        //   526: aload           4
        //   528: astore_1       
        //   529: goto            417
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  9      22     98     133    Ljava/lang/IllegalArgumentException;
        //  9      22     63     98     Lcom/netflix/android/org/json/JSONException;
        //  22     35     133    168    Ljava/lang/IllegalArgumentException;
        //  22     35     63     98     Lcom/netflix/android/org/json/JSONException;
        //  35     63     63     98     Lcom/netflix/android/org/json/JSONException;
        //  99     133    63     98     Lcom/netflix/android/org/json/JSONException;
        //  134    168    63     98     Lcom/netflix/android/org/json/JSONException;
        //  189    230    260    292    Lcom/netflix/android/org/json/JSONException;
        //  230    260    260    292    Lcom/netflix/android/org/json/JSONException;
        //  292    323    260    292    Lcom/netflix/android/org/json/JSONException;
        //  323    353    260    292    Lcom/netflix/android/org/json/JSONException;
        //  353    371    260    292    Lcom/netflix/android/org/json/JSONException;
        //  371    394    260    292    Lcom/netflix/android/org/json/JSONException;
        //  394    402    447    463    Ljava/lang/IllegalArgumentException;
        //  394    402    260    292    Lcom/netflix/android/org/json/JSONException;
        //  402    411    260    292    Lcom/netflix/android/org/json/JSONException;
        //  411    417    525    532    Ljava/lang/IllegalArgumentException;
        //  411    417    260    292    Lcom/netflix/android/org/json/JSONException;
        //  421    426    260    292    Lcom/netflix/android/org/json/JSONException;
        //  426    447    260    292    Lcom/netflix/android/org/json/JSONException;
        //  449    463    260    292    Lcom/netflix/android/org/json/JSONException;
        //  463    468    260    292    Lcom/netflix/android/org/json/JSONException;
        //  471    491    260    292    Lcom/netflix/android/org/json/JSONException;
        //  491    498    260    292    Lcom/netflix/android/org/json/JSONException;
        //  499    511    260    292    Lcom/netflix/android/org/json/JSONException;
        //  512    524    260    292    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 251, Size: 251
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
        if (o != this) {
            if (!(o instanceof PayloadChunk)) {
                return false;
            }
            final PayloadChunk payloadChunk = (PayloadChunk)o;
            if (this.sequenceNumber != payloadChunk.sequenceNumber || this.messageId != payloadChunk.messageId || this.endofmsg != payloadChunk.endofmsg || this.compressionAlgo != payloadChunk.compressionAlgo || !Arrays.equals(this.data, payloadChunk.data)) {
                return false;
            }
        }
        return true;
    }
    
    public MslConstants$CompressionAlgorithm getCompressionAlgo() {
        return this.compressionAlgo;
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public long getMessageId() {
        return this.messageId;
    }
    
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = Long.valueOf(this.sequenceNumber).hashCode();
        final int hashCode2 = Long.valueOf(this.messageId).hashCode();
        final int hashCode3 = Boolean.valueOf(this.endofmsg).hashCode();
        int hashCode4;
        if (this.compressionAlgo != null) {
            hashCode4 = this.compressionAlgo.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        return hashCode4 ^ (hashCode3 ^ (hashCode ^ hashCode2)) ^ Arrays.hashCode(this.data);
    }
    
    public boolean isEndOfMessage() {
        return this.endofmsg;
    }
    
    @Override
    public String toJSONString() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("payload", Base64.encode(this.payload));
            jsonObject.put("signature", Base64.encode(this.signature));
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
}
