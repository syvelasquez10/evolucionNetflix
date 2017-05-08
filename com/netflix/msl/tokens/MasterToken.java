// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.tokens;

import java.nio.charset.Charset;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslConstants;
import com.netflix.msl.MslConstants$SignatureAlgo;
import com.netflix.msl.MslConstants$EncryptionAlgo;
import com.netflix.msl.util.Base64;
import com.netflix.msl.MslInternalException;
import java.util.Date;
import com.netflix.android.org.json.JSONObject;
import javax.crypto.SecretKey;
import com.netflix.msl.util.MslContext;
import com.netflix.android.org.json.JSONString;

public class MasterToken implements JSONString
{
    private static final String KEY_ENCRYPTION_ALGORITHM = "encryptionalgorithm";
    private static final String KEY_ENCRYPTION_KEY = "encryptionkey";
    private static final String KEY_EXPIRATION = "expiration";
    private static final String KEY_HMAC_KEY = "hmackey";
    private static final String KEY_IDENTITY = "identity";
    private static final String KEY_ISSUER_DATA = "issuerdata";
    private static final String KEY_RENEWAL_WINDOW = "renewalwindow";
    private static final String KEY_SEQUENCE_NUMBER = "sequencenumber";
    private static final String KEY_SERIAL_NUMBER = "serialnumber";
    private static final String KEY_SESSIONDATA = "sessiondata";
    private static final String KEY_SIGNATURE = "signature";
    private static final String KEY_SIGNATURE_ALGORITHM = "signaturealgorithm";
    private static final String KEY_SIGNATURE_KEY = "signaturekey";
    private static final String KEY_TOKENDATA = "tokendata";
    private static final long MILLISECONDS_PER_SECOND = 1000L;
    private final MslContext ctx;
    private final SecretKey encryptionKey;
    private final long expiration;
    private final String identity;
    private final JSONObject issuerData;
    private final long renewalWindow;
    private final long sequenceNumber;
    private final long serialNumber;
    private final byte[] sessiondata;
    private final byte[] signature;
    private final SecretKey signatureKey;
    private final byte[] tokendata;
    private final boolean verified;
    
    public MasterToken(final MslContext p0, final JSONObject p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_0        
        //     3: invokespecial   java/lang/Object.<init>:()V
        //     6: aload_0        
        //     7: aload_1        
        //     8: putfield        com/netflix/msl/tokens/MasterToken.ctx:Lcom/netflix/msl/util/MslContext;
        //    11: aload_1        
        //    12: invokevirtual   com/netflix/msl/util/MslContext.getMslCryptoContext:()Lcom/netflix/msl/crypto/ICryptoContext;
        //    15: astore_1       
        //    16: aload_0        
        //    17: aload_2        
        //    18: ldc             "tokendata"
        //    20: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    23: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    26: putfield        com/netflix/msl/tokens/MasterToken.tokendata:[B
        //    29: aload_0        
        //    30: getfield        com/netflix/msl/tokens/MasterToken.tokendata:[B
        //    33: ifnull          44
        //    36: aload_0        
        //    37: getfield        com/netflix/msl/tokens/MasterToken.tokendata:[B
        //    40: arraylength    
        //    41: ifne            147
        //    44: new             Lcom/netflix/msl/MslEncodingException;
        //    47: dup            
        //    48: getstatic       com/netflix/msl/MslError.MASTERTOKEN_TOKENDATA_MISSING:Lcom/netflix/msl/MslError;
        //    51: new             Ljava/lang/StringBuilder;
        //    54: dup            
        //    55: invokespecial   java/lang/StringBuilder.<init>:()V
        //    58: ldc             "mastertoken "
        //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    63: aload_2        
        //    64: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    73: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    76: athrow         
        //    77: astore_1       
        //    78: new             Lcom/netflix/msl/MslEncodingException;
        //    81: dup            
        //    82: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: ldc             "mastertoken "
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: aload_2        
        //    98: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   107: aload_1        
        //   108: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   111: athrow         
        //   112: astore_1       
        //   113: new             Lcom/netflix/msl/MslEncodingException;
        //   116: dup            
        //   117: getstatic       com/netflix/msl/MslError.MASTERTOKEN_TOKENDATA_INVALID:Lcom/netflix/msl/MslError;
        //   120: new             Ljava/lang/StringBuilder;
        //   123: dup            
        //   124: invokespecial   java/lang/StringBuilder.<init>:()V
        //   127: ldc             "mastertoken "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload_2        
        //   133: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   142: aload_1        
        //   143: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   146: athrow         
        //   147: aload_0        
        //   148: aload_2        
        //   149: ldc             "signature"
        //   151: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   154: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   157: putfield        com/netflix/msl/tokens/MasterToken.signature:[B
        //   160: aload_0        
        //   161: aload_1        
        //   162: aload_0        
        //   163: getfield        com/netflix/msl/tokens/MasterToken.tokendata:[B
        //   166: aload_0        
        //   167: getfield        com/netflix/msl/tokens/MasterToken.signature:[B
        //   170: invokeinterface com/netflix/msl/crypto/ICryptoContext.verify:([B[B)Z
        //   175: putfield        com/netflix/msl/tokens/MasterToken.verified:Z
        //   178: new             Ljava/lang/String;
        //   181: dup            
        //   182: aload_0        
        //   183: getfield        com/netflix/msl/tokens/MasterToken.tokendata:[B
        //   186: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   189: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   192: astore_2       
        //   193: new             Lcom/netflix/android/org/json/JSONObject;
        //   196: dup            
        //   197: aload_2        
        //   198: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   201: astore          4
        //   203: aload_0        
        //   204: aload           4
        //   206: ldc             "renewalwindow"
        //   208: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   211: putfield        com/netflix/msl/tokens/MasterToken.renewalWindow:J
        //   214: aload_0        
        //   215: aload           4
        //   217: ldc             "expiration"
        //   219: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   222: putfield        com/netflix/msl/tokens/MasterToken.expiration:J
        //   225: aload_0        
        //   226: getfield        com/netflix/msl/tokens/MasterToken.expiration:J
        //   229: aload_0        
        //   230: getfield        com/netflix/msl/tokens/MasterToken.renewalWindow:J
        //   233: lcmp           
        //   234: ifge            334
        //   237: new             Lcom/netflix/msl/MslException;
        //   240: dup            
        //   241: getstatic       com/netflix/msl/MslError.MASTERTOKEN_EXPIRES_BEFORE_RENEWAL:Lcom/netflix/msl/MslError;
        //   244: new             Ljava/lang/StringBuilder;
        //   247: dup            
        //   248: invokespecial   java/lang/StringBuilder.<init>:()V
        //   251: ldc             "mastertokendata "
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: aload_2        
        //   257: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   260: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   263: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   266: athrow         
        //   267: astore_1       
        //   268: new             Lcom/netflix/msl/MslEncodingException;
        //   271: dup            
        //   272: getstatic       com/netflix/msl/MslError.MASTERTOKEN_TOKENDATA_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   275: new             Ljava/lang/StringBuilder;
        //   278: dup            
        //   279: invokespecial   java/lang/StringBuilder.<init>:()V
        //   282: ldc             "mastertokendata "
        //   284: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   287: aload_2        
        //   288: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   294: aload_1        
        //   295: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   298: athrow         
        //   299: astore_1       
        //   300: new             Lcom/netflix/msl/MslEncodingException;
        //   303: dup            
        //   304: getstatic       com/netflix/msl/MslError.MASTERTOKEN_SIGNATURE_INVALID:Lcom/netflix/msl/MslError;
        //   307: new             Ljava/lang/StringBuilder;
        //   310: dup            
        //   311: invokespecial   java/lang/StringBuilder.<init>:()V
        //   314: ldc             "mastertoken "
        //   316: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   319: aload_2        
        //   320: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   326: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   329: aload_1        
        //   330: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   333: athrow         
        //   334: aload_0        
        //   335: aload           4
        //   337: ldc             "sequencenumber"
        //   339: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   342: putfield        com/netflix/msl/tokens/MasterToken.sequenceNumber:J
        //   345: aload_0        
        //   346: getfield        com/netflix/msl/tokens/MasterToken.sequenceNumber:J
        //   349: lconst_0       
        //   350: lcmp           
        //   351: iflt            365
        //   354: aload_0        
        //   355: getfield        com/netflix/msl/tokens/MasterToken.sequenceNumber:J
        //   358: ldc2_w          9007199254740992
        //   361: lcmp           
        //   362: ifle            395
        //   365: new             Lcom/netflix/msl/MslException;
        //   368: dup            
        //   369: getstatic       com/netflix/msl/MslError.MASTERTOKEN_SEQUENCE_NUMBER_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   372: new             Ljava/lang/StringBuilder;
        //   375: dup            
        //   376: invokespecial   java/lang/StringBuilder.<init>:()V
        //   379: ldc             "mastertokendata "
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: aload_2        
        //   385: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   388: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   391: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   394: athrow         
        //   395: aload_0        
        //   396: aload           4
        //   398: ldc             "serialnumber"
        //   400: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   403: putfield        com/netflix/msl/tokens/MasterToken.serialNumber:J
        //   406: aload_0        
        //   407: getfield        com/netflix/msl/tokens/MasterToken.serialNumber:J
        //   410: lconst_0       
        //   411: lcmp           
        //   412: iflt            426
        //   415: aload_0        
        //   416: getfield        com/netflix/msl/tokens/MasterToken.serialNumber:J
        //   419: ldc2_w          9007199254740992
        //   422: lcmp           
        //   423: ifle            456
        //   426: new             Lcom/netflix/msl/MslException;
        //   429: dup            
        //   430: getstatic       com/netflix/msl/MslError.MASTERTOKEN_SERIAL_NUMBER_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   433: new             Ljava/lang/StringBuilder;
        //   436: dup            
        //   437: invokespecial   java/lang/StringBuilder.<init>:()V
        //   440: ldc             "mastertokendata "
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   445: aload_2        
        //   446: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   449: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   452: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   455: athrow         
        //   456: aload           4
        //   458: ldc             "sessiondata"
        //   460: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   463: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   466: astore          5
        //   468: aload           5
        //   470: ifnull          479
        //   473: aload           5
        //   475: arraylength    
        //   476: ifne            516
        //   479: new             Lcom/netflix/msl/MslEncodingException;
        //   482: dup            
        //   483: getstatic       com/netflix/msl/MslError.MASTERTOKEN_SESSIONDATA_MISSING:Lcom/netflix/msl/MslError;
        //   486: aload           4
        //   488: ldc             "sessiondata"
        //   490: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   493: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   496: athrow         
        //   497: astore_1       
        //   498: new             Lcom/netflix/msl/MslEncodingException;
        //   501: dup            
        //   502: getstatic       com/netflix/msl/MslError.MASTERTOKEN_SESSIONDATA_INVALID:Lcom/netflix/msl/MslError;
        //   505: aload           4
        //   507: ldc             "sessiondata"
        //   509: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   512: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   515: athrow         
        //   516: aload_0        
        //   517: getfield        com/netflix/msl/tokens/MasterToken.verified:Z
        //   520: ifeq            706
        //   523: aload_1        
        //   524: aload           5
        //   526: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   531: astore_1       
        //   532: aload_0        
        //   533: aload_1        
        //   534: putfield        com/netflix/msl/tokens/MasterToken.sessiondata:[B
        //   537: aload_0        
        //   538: getfield        com/netflix/msl/tokens/MasterToken.sessiondata:[B
        //   541: ifnull          810
        //   544: new             Ljava/lang/String;
        //   547: dup            
        //   548: aload_0        
        //   549: getfield        com/netflix/msl/tokens/MasterToken.sessiondata:[B
        //   552: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   555: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   558: astore_2       
        //   559: new             Lcom/netflix/android/org/json/JSONObject;
        //   562: dup            
        //   563: aload_2        
        //   564: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   567: astore          4
        //   569: aload_3        
        //   570: astore_1       
        //   571: aload           4
        //   573: ldc             "issuerdata"
        //   575: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   578: ifeq            589
        //   581: aload           4
        //   583: ldc             "issuerdata"
        //   585: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //   588: astore_1       
        //   589: aload_0        
        //   590: aload_1        
        //   591: putfield        com/netflix/msl/tokens/MasterToken.issuerData:Lcom/netflix/android/org/json/JSONObject;
        //   594: aload_0        
        //   595: aload           4
        //   597: ldc             "identity"
        //   599: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   602: putfield        com/netflix/msl/tokens/MasterToken.identity:Ljava/lang/String;
        //   605: aload           4
        //   607: ldc             "encryptionkey"
        //   609: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   612: astore          5
        //   614: aload           4
        //   616: ldc             "encryptionalgorithm"
        //   618: ldc             "AES"
        //   620: invokevirtual   com/netflix/android/org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   623: astore_3       
        //   624: aload           4
        //   626: ldc             "signaturekey"
        //   628: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   631: ifeq            711
        //   634: aload           4
        //   636: ldc             "signaturekey"
        //   638: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   641: astore_1       
        //   642: aload           4
        //   644: ldc             "signaturealgorithm"
        //   646: ldc             "HmacSHA256"
        //   648: invokevirtual   com/netflix/android/org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   651: astore          4
        //   653: aload_3        
        //   654: invokestatic    com/netflix/msl/MslConstants$EncryptionAlgo.fromString:(Ljava/lang/String;)Lcom/netflix/msl/MslConstants$EncryptionAlgo;
        //   657: invokevirtual   com/netflix/msl/MslConstants$EncryptionAlgo.toString:()Ljava/lang/String;
        //   660: astore_2       
        //   661: aload           4
        //   663: invokestatic    com/netflix/msl/MslConstants$SignatureAlgo.fromString:(Ljava/lang/String;)Lcom/netflix/msl/MslConstants$SignatureAlgo;
        //   666: invokevirtual   com/netflix/msl/MslConstants$SignatureAlgo.toString:()Ljava/lang/String;
        //   669: astore          6
        //   671: aload_0        
        //   672: new             Ljavax/crypto/spec/SecretKeySpec;
        //   675: dup            
        //   676: aload           5
        //   678: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   681: aload_2        
        //   682: invokespecial   javax/crypto/spec/SecretKeySpec.<init>:([BLjava/lang/String;)V
        //   685: putfield        com/netflix/msl/tokens/MasterToken.encryptionKey:Ljavax/crypto/SecretKey;
        //   688: aload_0        
        //   689: new             Ljavax/crypto/spec/SecretKeySpec;
        //   692: dup            
        //   693: aload_1        
        //   694: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   697: aload           6
        //   699: invokespecial   javax/crypto/spec/SecretKeySpec.<init>:([BLjava/lang/String;)V
        //   702: putfield        com/netflix/msl/tokens/MasterToken.signatureKey:Ljavax/crypto/SecretKey;
        //   705: return         
        //   706: aconst_null    
        //   707: astore_1       
        //   708: goto            532
        //   711: aload           4
        //   713: ldc             "hmackey"
        //   715: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   718: astore_1       
        //   719: goto            642
        //   722: astore_1       
        //   723: new             Lcom/netflix/msl/MslEncodingException;
        //   726: dup            
        //   727: getstatic       com/netflix/msl/MslError.MASTERTOKEN_SESSIONDATA_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   730: new             Ljava/lang/StringBuilder;
        //   733: dup            
        //   734: invokespecial   java/lang/StringBuilder.<init>:()V
        //   737: ldc             "sessiondata "
        //   739: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   742: aload_2        
        //   743: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   746: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   749: aload_1        
        //   750: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   753: athrow         
        //   754: astore_1       
        //   755: new             Lcom/netflix/msl/MslCryptoException;
        //   758: dup            
        //   759: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_ALGORITHM:Lcom/netflix/msl/MslError;
        //   762: new             Ljava/lang/StringBuilder;
        //   765: dup            
        //   766: invokespecial   java/lang/StringBuilder.<init>:()V
        //   769: ldc             "encryption algorithm: "
        //   771: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   774: aload_3        
        //   775: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   778: ldc_w           "; signature algorithm"
        //   781: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   784: aload           4
        //   786: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   789: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   792: aload_1        
        //   793: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   796: athrow         
        //   797: astore_1       
        //   798: new             Lcom/netflix/msl/MslCryptoException;
        //   801: dup            
        //   802: getstatic       com/netflix/msl/MslError.MASTERTOKEN_KEY_CREATION_ERROR:Lcom/netflix/msl/MslError;
        //   805: aload_1        
        //   806: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   809: athrow         
        //   810: aload_0        
        //   811: aconst_null    
        //   812: putfield        com/netflix/msl/tokens/MasterToken.issuerData:Lcom/netflix/android/org/json/JSONObject;
        //   815: aload_0        
        //   816: aconst_null    
        //   817: putfield        com/netflix/msl/tokens/MasterToken.identity:Ljava/lang/String;
        //   820: aload_0        
        //   821: aconst_null    
        //   822: putfield        com/netflix/msl/tokens/MasterToken.encryptionKey:Ljavax/crypto/SecretKey;
        //   825: aload_0        
        //   826: aconst_null    
        //   827: putfield        com/netflix/msl/tokens/MasterToken.signatureKey:Ljavax/crypto/SecretKey;
        //   830: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  16     29     112    147    Ljava/lang/IllegalArgumentException;
        //  16     29     77     112    Lcom/netflix/android/org/json/JSONException;
        //  29     44     77     112    Lcom/netflix/android/org/json/JSONException;
        //  44     77     77     112    Lcom/netflix/android/org/json/JSONException;
        //  113    147    77     112    Lcom/netflix/android/org/json/JSONException;
        //  147    160    299    334    Ljava/lang/IllegalArgumentException;
        //  147    160    77     112    Lcom/netflix/android/org/json/JSONException;
        //  160    178    77     112    Lcom/netflix/android/org/json/JSONException;
        //  193    267    267    299    Lcom/netflix/android/org/json/JSONException;
        //  300    334    77     112    Lcom/netflix/android/org/json/JSONException;
        //  334    365    267    299    Lcom/netflix/android/org/json/JSONException;
        //  365    395    267    299    Lcom/netflix/android/org/json/JSONException;
        //  395    426    267    299    Lcom/netflix/android/org/json/JSONException;
        //  426    456    267    299    Lcom/netflix/android/org/json/JSONException;
        //  456    468    497    516    Ljava/lang/IllegalArgumentException;
        //  456    468    267    299    Lcom/netflix/android/org/json/JSONException;
        //  473    479    267    299    Lcom/netflix/android/org/json/JSONException;
        //  479    497    267    299    Lcom/netflix/android/org/json/JSONException;
        //  498    516    267    299    Lcom/netflix/android/org/json/JSONException;
        //  516    532    267    299    Lcom/netflix/android/org/json/JSONException;
        //  532    537    267    299    Lcom/netflix/android/org/json/JSONException;
        //  559    569    722    754    Lcom/netflix/android/org/json/JSONException;
        //  571    589    722    754    Lcom/netflix/android/org/json/JSONException;
        //  589    642    722    754    Lcom/netflix/android/org/json/JSONException;
        //  642    653    722    754    Lcom/netflix/android/org/json/JSONException;
        //  653    671    754    797    Ljava/lang/IllegalArgumentException;
        //  671    705    797    810    Ljava/lang/IllegalArgumentException;
        //  711    719    722    754    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 390, Size: 390
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
    
    public MasterToken(final MslContext ctx, final Date date, final Date date2, final long sequenceNumber, final long serialNumber, final JSONObject issuerData, String encode, final SecretKey encryptionKey, final SecretKey signatureKey) {
        if (date2.before(date)) {
            throw new MslInternalException("Cannot construct a master token that expires before its renewal window opens.");
        }
        if (sequenceNumber < 0L || sequenceNumber > 9007199254740992L) {
            throw new MslInternalException("Sequence number " + sequenceNumber + " is outside the valid range.");
        }
        if (serialNumber < 0L || serialNumber > 9007199254740992L) {
            throw new MslInternalException("Serial number " + serialNumber + " is outside the valid range.");
        }
        this.ctx = ctx;
        this.renewalWindow = date.getTime() / 1000L;
        this.expiration = date2.getTime() / 1000L;
        this.sequenceNumber = sequenceNumber;
        this.serialNumber = serialNumber;
        this.issuerData = issuerData;
        this.identity = encode;
        this.encryptionKey = encryptionKey;
        this.signatureKey = signatureKey;
        final JSONObject jsonObject = new JSONObject();
        ICryptoContext mslCryptoContext;
        byte[] encrypt;
        try {
            final String encode2 = Base64.encode(this.encryptionKey.getEncoded());
            final MslConstants$EncryptionAlgo fromString = MslConstants$EncryptionAlgo.fromString(this.encryptionKey.getAlgorithm());
            encode = Base64.encode(this.signatureKey.getEncoded());
            final MslConstants$SignatureAlgo fromString2 = MslConstants$SignatureAlgo.fromString(this.signatureKey.getAlgorithm());
            if (this.issuerData != null) {
                jsonObject.put("issuerdata", this.issuerData);
            }
            jsonObject.put("identity", this.identity);
            jsonObject.put("encryptionkey", encode2);
            jsonObject.put("encryptionalgorithm", fromString);
            jsonObject.put("hmackey", encode);
            jsonObject.put("signaturekey", encode);
            jsonObject.put("signaturealgorithm", fromString2);
            this.sessiondata = jsonObject.toString().getBytes(MslConstants.DEFAULT_CHARSET);
            mslCryptoContext = ctx.getMslCryptoContext();
            encrypt = mslCryptoContext.encrypt(this.sessiondata);
            final JSONObject jsonObject2 = new JSONObject();
            final JSONObject jsonObject4;
            final JSONObject jsonObject3 = jsonObject4 = jsonObject2;
            final String s = "renewalwindow";
            final MasterToken masterToken = this;
            final long n = masterToken.renewalWindow;
            jsonObject4.put(s, n);
            final JSONObject jsonObject5 = jsonObject3;
            final String s2 = "expiration";
            final MasterToken masterToken2 = this;
            final long n2 = masterToken2.expiration;
            jsonObject5.put(s2, n2);
            final JSONObject jsonObject6 = jsonObject3;
            final String s3 = "sequencenumber";
            final MasterToken masterToken3 = this;
            final long n3 = masterToken3.sequenceNumber;
            jsonObject6.put(s3, n3);
            final JSONObject jsonObject7 = jsonObject3;
            final String s4 = "serialnumber";
            final MasterToken masterToken4 = this;
            final long n4 = masterToken4.serialNumber;
            jsonObject7.put(s4, n4);
            final JSONObject jsonObject8 = jsonObject3;
            final String s5 = "sessiondata";
            final byte[] array = encrypt;
            final String s6 = Base64.encode(array);
            jsonObject8.put(s5, s6);
            final MasterToken masterToken5 = this;
            final JSONObject jsonObject9 = jsonObject3;
            final String s7 = jsonObject9.toString();
            final Charset charset = MslConstants.DEFAULT_CHARSET;
            final byte[] array2 = s7.getBytes(charset);
            masterToken5.tokendata = array2;
            final MasterToken masterToken6 = this;
            final ICryptoContext cryptoContext = mslCryptoContext;
            final MasterToken masterToken7 = this;
            final byte[] array3 = masterToken7.tokendata;
            final byte[] array4 = cryptoContext.sign(array3);
            masterToken6.signature = array4;
            final MasterToken masterToken8 = this;
            final boolean b = true;
            masterToken8.verified = b;
            return;
        }
        catch (IllegalArgumentException ex) {
            throw new MslCryptoException(MslError.UNIDENTIFIED_ALGORITHM, "encryption algorithm: " + this.encryptionKey.getAlgorithm() + "; signature algorithm: " + this.signatureKey.getAlgorithm(), ex);
        }
        catch (JSONException ex2) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "sessiondata", ex2);
        }
        try {
            final JSONObject jsonObject2 = new JSONObject();
            final JSONObject jsonObject4;
            final JSONObject jsonObject3 = jsonObject4 = jsonObject2;
            final String s = "renewalwindow";
            final MasterToken masterToken = this;
            final long n = masterToken.renewalWindow;
            jsonObject4.put(s, n);
            final JSONObject jsonObject5 = jsonObject3;
            final String s2 = "expiration";
            final MasterToken masterToken2 = this;
            final long n2 = masterToken2.expiration;
            jsonObject5.put(s2, n2);
            final JSONObject jsonObject6 = jsonObject3;
            final String s3 = "sequencenumber";
            final MasterToken masterToken3 = this;
            final long n3 = masterToken3.sequenceNumber;
            jsonObject6.put(s3, n3);
            final JSONObject jsonObject7 = jsonObject3;
            final String s4 = "serialnumber";
            final MasterToken masterToken4 = this;
            final long n4 = masterToken4.serialNumber;
            jsonObject7.put(s4, n4);
            final JSONObject jsonObject8 = jsonObject3;
            final String s5 = "sessiondata";
            final byte[] array = encrypt;
            final String s6 = Base64.encode(array);
            jsonObject8.put(s5, s6);
            final MasterToken masterToken5 = this;
            final JSONObject jsonObject9 = jsonObject3;
            final String s7 = jsonObject9.toString();
            final Charset charset = MslConstants.DEFAULT_CHARSET;
            final byte[] array2 = s7.getBytes(charset);
            masterToken5.tokendata = array2;
            final MasterToken masterToken6 = this;
            final ICryptoContext cryptoContext = mslCryptoContext;
            final MasterToken masterToken7 = this;
            final byte[] array3 = masterToken7.tokendata;
            final byte[] array4 = cryptoContext.sign(array3);
            masterToken6.signature = array4;
            final MasterToken masterToken8 = this;
            final boolean b = true;
            masterToken8.verified = b;
        }
        catch (JSONException ex3) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "mastertokendata", ex3);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof MasterToken)) {
                return false;
            }
            final MasterToken masterToken = (MasterToken)o;
            if (this.serialNumber != masterToken.serialNumber || this.sequenceNumber != masterToken.sequenceNumber || this.expiration != masterToken.expiration) {
                return false;
            }
        }
        return true;
    }
    
    public SecretKey getEncryptionKey() {
        return this.encryptionKey;
    }
    
    public Date getExpiration() {
        return new Date(this.expiration * 1000L);
    }
    
    public String getIdentity() {
        return this.identity;
    }
    
    public JSONObject getIssuerData() {
        return this.issuerData;
    }
    
    public Date getRenewalWindow() {
        return new Date(this.renewalWindow * 1000L);
    }
    
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }
    
    public long getSerialNumber() {
        return this.serialNumber;
    }
    
    public SecretKey getSignatureKey() {
        return this.signatureKey;
    }
    
    @Override
    public int hashCode() {
        return (String.valueOf(this.serialNumber) + ":" + String.valueOf(this.sequenceNumber) + ":" + String.valueOf(this.expiration)).hashCode();
    }
    
    public boolean isDecrypted() {
        return this.sessiondata != null;
    }
    
    public boolean isExpired(final Date date) {
        if (date != null) {
            if (this.expiration * 1000L > date.getTime()) {
                return false;
            }
        }
        else {
            if (!this.isVerified()) {
                return false;
            }
            if (this.expiration * 1000L > this.ctx.getTime()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isNewerThan(final MasterToken masterToken) {
        if (this.sequenceNumber == masterToken.sequenceNumber) {
            if (this.expiration <= masterToken.expiration) {
                return false;
            }
        }
        else if (this.sequenceNumber > masterToken.sequenceNumber) {
            if (masterToken.sequenceNumber < this.sequenceNumber - 9007199254740992L + 127L) {
                return false;
            }
        }
        else if (this.sequenceNumber >= masterToken.sequenceNumber - 9007199254740992L + 127L) {
            return false;
        }
        return true;
    }
    
    public boolean isRenewable(final Date date) {
        if (date != null) {
            if (this.renewalWindow * 1000L > date.getTime()) {
                return false;
            }
        }
        else if (this.isVerified() && this.renewalWindow * 1000L > this.ctx.getTime()) {
            return false;
        }
        return true;
    }
    
    public boolean isVerified() {
        return this.verified;
    }
    
    @Override
    public String toJSONString() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("tokendata", Base64.encode(this.tokendata));
            jsonObject.put("signature", Base64.encode(this.signature));
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
    
    @Override
    public String toString() {
        try {
            JSONObject jsonObject;
            if (this.isDecrypted()) {
                jsonObject = new JSONObject();
                if (this.issuerData != null) {
                    jsonObject.put("issuerdata", this.issuerData);
                }
                jsonObject.put("identity", this.identity);
                jsonObject.put("encryptionkey", this.encryptionKey);
                jsonObject.put("encryptionalgorithm", this.encryptionKey.getAlgorithm());
                jsonObject.put("hmackey", this.signatureKey);
                jsonObject.put("signaturekey", this.signatureKey);
                jsonObject.put("signaturealgorithm", this.signatureKey.getAlgorithm());
            }
            else {
                jsonObject = null;
            }
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("renewalwindow", this.renewalWindow);
            jsonObject2.put("expiration", this.expiration);
            jsonObject2.put("sequencenumber", this.sequenceNumber);
            jsonObject2.put("serialnumber", this.serialNumber);
            jsonObject2.put("sessiondata", jsonObject);
            final JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("tokendata", jsonObject2);
            jsonObject3.put("signature", Base64.encode(this.signature));
            return jsonObject3.toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
}
