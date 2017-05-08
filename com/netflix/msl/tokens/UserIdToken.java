// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.tokens;

import java.nio.charset.Charset;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.MslCryptoException;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.Base64;
import com.netflix.msl.MslConstants;
import com.netflix.msl.MslInternalException;
import java.util.Date;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.android.org.json.JSONString;

public class UserIdToken implements JSONString
{
    private static final String KEY_EXPIRATION = "expiration";
    private static final String KEY_IDENTITY = "identity";
    private static final String KEY_ISSUER_DATA = "issuerdata";
    private static final String KEY_MASTER_TOKEN_SERIAL_NUMBER = "mtserialnumber";
    private static final String KEY_RENEWAL_WINDOW = "renewalwindow";
    private static final String KEY_SERIAL_NUMBER = "serialnumber";
    private static final String KEY_SIGNATURE = "signature";
    private static final String KEY_TOKENDATA = "tokendata";
    private static final String KEY_USERDATA = "userdata";
    private static final long MILLISECONDS_PER_SECOND = 1000L;
    private final MslContext ctx;
    private final long expiration;
    private final JSONObject issuerData;
    private final long mtSerialNumber;
    private final long renewalWindow;
    private final long serialNumber;
    private final byte[] signature;
    private final byte[] tokendata;
    private final MslUser user;
    private final byte[] userdata;
    private final boolean verified;
    
    public UserIdToken(final MslContext p0, final JSONObject p1, final MasterToken p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: aload_0        
        //     4: invokespecial   java/lang/Object.<init>:()V
        //     7: aload_0        
        //     8: aload_1        
        //     9: putfield        com/netflix/msl/tokens/UserIdToken.ctx:Lcom/netflix/msl/util/MslContext;
        //    12: aload_1        
        //    13: invokevirtual   com/netflix/msl/util/MslContext.getMslCryptoContext:()Lcom/netflix/msl/crypto/ICryptoContext;
        //    16: astore          6
        //    18: aload_0        
        //    19: aload_2        
        //    20: ldc             "tokendata"
        //    22: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    25: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    28: putfield        com/netflix/msl/tokens/UserIdToken.tokendata:[B
        //    31: aload_0        
        //    32: getfield        com/netflix/msl/tokens/UserIdToken.tokendata:[B
        //    35: ifnull          46
        //    38: aload_0        
        //    39: getfield        com/netflix/msl/tokens/UserIdToken.tokendata:[B
        //    42: arraylength    
        //    43: ifne            161
        //    46: new             Lcom/netflix/msl/MslEncodingException;
        //    49: dup            
        //    50: getstatic       com/netflix/msl/MslError.USERIDTOKEN_TOKENDATA_MISSING:Lcom/netflix/msl/MslError;
        //    53: new             Ljava/lang/StringBuilder;
        //    56: dup            
        //    57: invokespecial   java/lang/StringBuilder.<init>:()V
        //    60: ldc             "useridtoken "
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: aload_2        
        //    66: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    75: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    78: aload_3        
        //    79: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //    82: athrow         
        //    83: astore_1       
        //    84: new             Lcom/netflix/msl/MslEncodingException;
        //    87: dup            
        //    88: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //    91: new             Ljava/lang/StringBuilder;
        //    94: dup            
        //    95: invokespecial   java/lang/StringBuilder.<init>:()V
        //    98: ldc             "useridtoken "
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: aload_2        
        //   104: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: aload_1        
        //   114: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   117: aload_3        
        //   118: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   121: athrow         
        //   122: astore_1       
        //   123: new             Lcom/netflix/msl/MslEncodingException;
        //   126: dup            
        //   127: getstatic       com/netflix/msl/MslError.USERIDTOKEN_TOKENDATA_INVALID:Lcom/netflix/msl/MslError;
        //   130: new             Ljava/lang/StringBuilder;
        //   133: dup            
        //   134: invokespecial   java/lang/StringBuilder.<init>:()V
        //   137: ldc             "useridtoken "
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: aload_2        
        //   143: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   152: aload_1        
        //   153: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   156: aload_3        
        //   157: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   160: athrow         
        //   161: aload_0        
        //   162: aload_2        
        //   163: ldc             "signature"
        //   165: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   168: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   171: putfield        com/netflix/msl/tokens/UserIdToken.signature:[B
        //   174: aload_0        
        //   175: aload           6
        //   177: aload_0        
        //   178: getfield        com/netflix/msl/tokens/UserIdToken.tokendata:[B
        //   181: aload_0        
        //   182: getfield        com/netflix/msl/tokens/UserIdToken.signature:[B
        //   185: invokeinterface com/netflix/msl/crypto/ICryptoContext.verify:([B[B)Z
        //   190: putfield        com/netflix/msl/tokens/UserIdToken.verified:Z
        //   193: new             Ljava/lang/String;
        //   196: dup            
        //   197: aload_0        
        //   198: getfield        com/netflix/msl/tokens/UserIdToken.tokendata:[B
        //   201: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   204: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   207: astore          5
        //   209: new             Lcom/netflix/android/org/json/JSONObject;
        //   212: dup            
        //   213: aload           5
        //   215: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   218: astore_2       
        //   219: aload_0        
        //   220: aload_2        
        //   221: ldc             "renewalwindow"
        //   223: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   226: putfield        com/netflix/msl/tokens/UserIdToken.renewalWindow:J
        //   229: aload_0        
        //   230: aload_2        
        //   231: ldc             "expiration"
        //   233: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   236: putfield        com/netflix/msl/tokens/UserIdToken.expiration:J
        //   239: aload_0        
        //   240: getfield        com/netflix/msl/tokens/UserIdToken.expiration:J
        //   243: aload_0        
        //   244: getfield        com/netflix/msl/tokens/UserIdToken.renewalWindow:J
        //   247: lcmp           
        //   248: ifge            362
        //   251: new             Lcom/netflix/msl/MslException;
        //   254: dup            
        //   255: getstatic       com/netflix/msl/MslError.USERIDTOKEN_EXPIRES_BEFORE_RENEWAL:Lcom/netflix/msl/MslError;
        //   258: new             Ljava/lang/StringBuilder;
        //   261: dup            
        //   262: invokespecial   java/lang/StringBuilder.<init>:()V
        //   265: ldc             "usertokendata "
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: aload           5
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   278: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   281: aload_3        
        //   282: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   285: athrow         
        //   286: astore_1       
        //   287: new             Lcom/netflix/msl/MslEncodingException;
        //   290: dup            
        //   291: getstatic       com/netflix/msl/MslError.USERIDTOKEN_TOKENDATA_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   294: new             Ljava/lang/StringBuilder;
        //   297: dup            
        //   298: invokespecial   java/lang/StringBuilder.<init>:()V
        //   301: ldc             "usertokendata "
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: aload           5
        //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   311: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   314: aload_1        
        //   315: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   318: aload_3        
        //   319: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   322: athrow         
        //   323: astore_1       
        //   324: new             Lcom/netflix/msl/MslEncodingException;
        //   327: dup            
        //   328: getstatic       com/netflix/msl/MslError.USERIDTOKEN_SIGNATURE_INVALID:Lcom/netflix/msl/MslError;
        //   331: new             Ljava/lang/StringBuilder;
        //   334: dup            
        //   335: invokespecial   java/lang/StringBuilder.<init>:()V
        //   338: ldc             "useridtoken "
        //   340: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: aload_2        
        //   344: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   347: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   350: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   353: aload_1        
        //   354: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   357: aload_3        
        //   358: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   361: athrow         
        //   362: aload_0        
        //   363: aload_2        
        //   364: ldc             "mtserialnumber"
        //   366: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   369: putfield        com/netflix/msl/tokens/UserIdToken.mtSerialNumber:J
        //   372: aload_0        
        //   373: getfield        com/netflix/msl/tokens/UserIdToken.mtSerialNumber:J
        //   376: lconst_0       
        //   377: lcmp           
        //   378: iflt            392
        //   381: aload_0        
        //   382: getfield        com/netflix/msl/tokens/UserIdToken.mtSerialNumber:J
        //   385: ldc2_w          9007199254740992
        //   388: lcmp           
        //   389: ifle            436
        //   392: new             Lcom/netflix/msl/MslException;
        //   395: dup            
        //   396: getstatic       com/netflix/msl/MslError.USERIDTOKEN_MASTERTOKEN_SERIAL_NUMBER_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   399: new             Ljava/lang/StringBuilder;
        //   402: dup            
        //   403: invokespecial   java/lang/StringBuilder.<init>:()V
        //   406: ldc             "usertokendata "
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: aload           5
        //   413: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   419: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   422: aload_3        
        //   423: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   426: athrow         
        //   427: astore_1       
        //   428: aload_1        
        //   429: aload_3        
        //   430: invokevirtual   com/netflix/msl/MslCryptoException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslCryptoException;
        //   433: pop            
        //   434: aload_1        
        //   435: athrow         
        //   436: aload_0        
        //   437: aload_2        
        //   438: ldc             "serialnumber"
        //   440: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   443: putfield        com/netflix/msl/tokens/UserIdToken.serialNumber:J
        //   446: aload_0        
        //   447: getfield        com/netflix/msl/tokens/UserIdToken.serialNumber:J
        //   450: lconst_0       
        //   451: lcmp           
        //   452: iflt            466
        //   455: aload_0        
        //   456: getfield        com/netflix/msl/tokens/UserIdToken.serialNumber:J
        //   459: ldc2_w          9007199254740992
        //   462: lcmp           
        //   463: ifle            501
        //   466: new             Lcom/netflix/msl/MslException;
        //   469: dup            
        //   470: getstatic       com/netflix/msl/MslError.USERIDTOKEN_SERIAL_NUMBER_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   473: new             Ljava/lang/StringBuilder;
        //   476: dup            
        //   477: invokespecial   java/lang/StringBuilder.<init>:()V
        //   480: ldc             "usertokendata "
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: aload           5
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   493: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   496: aload_3        
        //   497: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   500: athrow         
        //   501: aload_2        
        //   502: ldc             "userdata"
        //   504: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   507: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   510: astore          7
        //   512: aload           7
        //   514: ifnull          523
        //   517: aload           7
        //   519: arraylength    
        //   520: ifne            566
        //   523: new             Lcom/netflix/msl/MslException;
        //   526: dup            
        //   527: getstatic       com/netflix/msl/MslError.USERIDTOKEN_USERDATA_MISSING:Lcom/netflix/msl/MslError;
        //   530: aload_2        
        //   531: ldc             "userdata"
        //   533: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   536: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   539: aload_3        
        //   540: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   543: athrow         
        //   544: astore_1       
        //   545: new             Lcom/netflix/msl/MslException;
        //   548: dup            
        //   549: getstatic       com/netflix/msl/MslError.USERIDTOKEN_USERDATA_INVALID:Lcom/netflix/msl/MslError;
        //   552: aload_2        
        //   553: ldc             "userdata"
        //   555: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   558: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   561: aload_3        
        //   562: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   565: athrow         
        //   566: aload_0        
        //   567: getfield        com/netflix/msl/tokens/UserIdToken.verified:Z
        //   570: ifeq            739
        //   573: aload           6
        //   575: aload           7
        //   577: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   582: astore_2       
        //   583: aload_0        
        //   584: aload_2        
        //   585: putfield        com/netflix/msl/tokens/UserIdToken.userdata:[B
        //   588: aload_0        
        //   589: getfield        com/netflix/msl/tokens/UserIdToken.userdata:[B
        //   592: ifnull          776
        //   595: new             Ljava/lang/String;
        //   598: dup            
        //   599: aload_0        
        //   600: getfield        com/netflix/msl/tokens/UserIdToken.userdata:[B
        //   603: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   606: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   609: astore          5
        //   611: new             Lcom/netflix/android/org/json/JSONObject;
        //   614: dup            
        //   615: aload           5
        //   617: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   620: astore          6
        //   622: aload           4
        //   624: astore_2       
        //   625: aload           6
        //   627: ldc             "issuerdata"
        //   629: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   632: ifeq            643
        //   635: aload           6
        //   637: ldc             "issuerdata"
        //   639: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //   642: astore_2       
        //   643: aload_0        
        //   644: aload_2        
        //   645: putfield        com/netflix/msl/tokens/UserIdToken.issuerData:Lcom/netflix/android/org/json/JSONObject;
        //   648: aload           6
        //   650: ldc             "identity"
        //   652: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   655: astore_2       
        //   656: aload_2        
        //   657: ifnull          667
        //   660: aload_2        
        //   661: invokevirtual   java/lang/String.length:()I
        //   664: ifne            744
        //   667: new             Lcom/netflix/msl/MslException;
        //   670: dup            
        //   671: getstatic       com/netflix/msl/MslError.USERIDTOKEN_IDENTITY_INVALID:Lcom/netflix/msl/MslError;
        //   674: new             Ljava/lang/StringBuilder;
        //   677: dup            
        //   678: invokespecial   java/lang/StringBuilder.<init>:()V
        //   681: ldc             "userdata "
        //   683: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   686: aload           5
        //   688: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   691: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   694: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   697: aload_3        
        //   698: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   701: athrow         
        //   702: astore_1       
        //   703: new             Lcom/netflix/msl/MslEncodingException;
        //   706: dup            
        //   707: getstatic       com/netflix/msl/MslError.USERIDTOKEN_USERDATA_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   710: new             Ljava/lang/StringBuilder;
        //   713: dup            
        //   714: invokespecial   java/lang/StringBuilder.<init>:()V
        //   717: ldc             "userdata "
        //   719: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   722: aload           5
        //   724: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   727: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   730: aload_1        
        //   731: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   734: aload_3        
        //   735: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   738: athrow         
        //   739: aconst_null    
        //   740: astore_2       
        //   741: goto            583
        //   744: aload_0        
        //   745: aload_1        
        //   746: invokevirtual   com/netflix/msl/util/MslContext.getTokenFactory:()Lcom/netflix/msl/tokens/TokenFactory;
        //   749: aload_1        
        //   750: aload_2        
        //   751: invokeinterface com/netflix/msl/tokens/TokenFactory.createUser:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;)Lcom/netflix/msl/tokens/MslUser;
        //   756: putfield        com/netflix/msl/tokens/UserIdToken.user:Lcom/netflix/msl/tokens/MslUser;
        //   759: aload_0        
        //   760: getfield        com/netflix/msl/tokens/UserIdToken.user:Lcom/netflix/msl/tokens/MslUser;
        //   763: ifnonnull       786
        //   766: new             Lcom/netflix/msl/MslInternalException;
        //   769: dup            
        //   770: ldc             "TokenFactory.createUser() returned null in violation of the interface contract."
        //   772: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;)V
        //   775: athrow         
        //   776: aload_0        
        //   777: aconst_null    
        //   778: putfield        com/netflix/msl/tokens/UserIdToken.issuerData:Lcom/netflix/android/org/json/JSONObject;
        //   781: aload_0        
        //   782: aconst_null    
        //   783: putfield        com/netflix/msl/tokens/UserIdToken.user:Lcom/netflix/msl/tokens/MslUser;
        //   786: aload_3        
        //   787: ifnull          802
        //   790: aload_0        
        //   791: getfield        com/netflix/msl/tokens/UserIdToken.mtSerialNumber:J
        //   794: aload_3        
        //   795: invokevirtual   com/netflix/msl/tokens/MasterToken.getSerialNumber:()J
        //   798: lcmp           
        //   799: ifeq            848
        //   802: new             Lcom/netflix/msl/MslException;
        //   805: dup            
        //   806: getstatic       com/netflix/msl/MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH:Lcom/netflix/msl/MslError;
        //   809: new             Ljava/lang/StringBuilder;
        //   812: dup            
        //   813: invokespecial   java/lang/StringBuilder.<init>:()V
        //   816: ldc             "uit mtserialnumber "
        //   818: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   821: aload_0        
        //   822: getfield        com/netflix/msl/tokens/UserIdToken.mtSerialNumber:J
        //   825: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   828: ldc             "; mt "
        //   830: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   833: aload_3        
        //   834: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   837: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   840: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   843: aload_3        
        //   844: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   847: athrow         
        //   848: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  18     31     122    161    Ljava/lang/IllegalArgumentException;
        //  18     31     83     122    Lcom/netflix/android/org/json/JSONException;
        //  31     46     83     122    Lcom/netflix/android/org/json/JSONException;
        //  46     83     83     122    Lcom/netflix/android/org/json/JSONException;
        //  123    161    83     122    Lcom/netflix/android/org/json/JSONException;
        //  161    174    323    362    Ljava/lang/IllegalArgumentException;
        //  161    174    83     122    Lcom/netflix/android/org/json/JSONException;
        //  174    193    83     122    Lcom/netflix/android/org/json/JSONException;
        //  209    286    286    323    Lcom/netflix/android/org/json/JSONException;
        //  209    286    427    436    Lcom/netflix/msl/MslCryptoException;
        //  324    362    83     122    Lcom/netflix/android/org/json/JSONException;
        //  362    392    286    323    Lcom/netflix/android/org/json/JSONException;
        //  362    392    427    436    Lcom/netflix/msl/MslCryptoException;
        //  392    427    286    323    Lcom/netflix/android/org/json/JSONException;
        //  392    427    427    436    Lcom/netflix/msl/MslCryptoException;
        //  436    466    286    323    Lcom/netflix/android/org/json/JSONException;
        //  436    466    427    436    Lcom/netflix/msl/MslCryptoException;
        //  466    501    286    323    Lcom/netflix/android/org/json/JSONException;
        //  466    501    427    436    Lcom/netflix/msl/MslCryptoException;
        //  501    512    544    566    Ljava/lang/IllegalArgumentException;
        //  501    512    286    323    Lcom/netflix/android/org/json/JSONException;
        //  501    512    427    436    Lcom/netflix/msl/MslCryptoException;
        //  517    523    286    323    Lcom/netflix/android/org/json/JSONException;
        //  517    523    427    436    Lcom/netflix/msl/MslCryptoException;
        //  523    544    286    323    Lcom/netflix/android/org/json/JSONException;
        //  523    544    427    436    Lcom/netflix/msl/MslCryptoException;
        //  545    566    286    323    Lcom/netflix/android/org/json/JSONException;
        //  545    566    427    436    Lcom/netflix/msl/MslCryptoException;
        //  566    583    286    323    Lcom/netflix/android/org/json/JSONException;
        //  566    583    427    436    Lcom/netflix/msl/MslCryptoException;
        //  583    588    286    323    Lcom/netflix/android/org/json/JSONException;
        //  583    588    427    436    Lcom/netflix/msl/MslCryptoException;
        //  611    622    702    739    Lcom/netflix/android/org/json/JSONException;
        //  625    643    702    739    Lcom/netflix/android/org/json/JSONException;
        //  643    656    702    739    Lcom/netflix/android/org/json/JSONException;
        //  660    667    702    739    Lcom/netflix/android/org/json/JSONException;
        //  667    702    702    739    Lcom/netflix/android/org/json/JSONException;
        //  744    776    702    739    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0501:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public UserIdToken(final MslContext ctx, final Date date, final Date date2, final MasterToken masterToken, final long serialNumber, final JSONObject issuerData, final MslUser user) {
        if (date2.before(date)) {
            throw new MslInternalException("Cannot construct a user ID token that expires before its renewal window opens.");
        }
        if (masterToken == null) {
            throw new MslInternalException("Cannot construct a user ID token without a master token.");
        }
        if (serialNumber < 0L || serialNumber > 9007199254740992L) {
            throw new MslInternalException("Serial number " + serialNumber + " is outside the valid range.");
        }
        this.ctx = ctx;
        this.renewalWindow = date.getTime() / 1000L;
        this.expiration = date2.getTime() / 1000L;
        this.mtSerialNumber = masterToken.getSerialNumber();
        this.serialNumber = serialNumber;
        this.issuerData = issuerData;
        this.user = user;
        final JSONObject jsonObject = new JSONObject();
        try {
            if (this.issuerData != null) {
                jsonObject.put("issuerdata", this.issuerData);
            }
            jsonObject.put("identity", user.getEncoded());
            this.userdata = jsonObject.toString().getBytes(MslConstants.DEFAULT_CHARSET);
            final MslContext mslContext = ctx;
            final ICryptoContext cryptoContext = mslContext.getMslCryptoContext();
            final ICryptoContext cryptoContext3;
            final ICryptoContext cryptoContext2 = cryptoContext3 = cryptoContext;
            final UserIdToken userIdToken = this;
            final byte[] array = userIdToken.userdata;
            final byte[] array2 = cryptoContext3.encrypt(array);
            final JSONObject jsonObject2 = new JSONObject();
            final JSONObject jsonObject4;
            final JSONObject jsonObject3 = jsonObject4 = jsonObject2;
            final String s = "renewalwindow";
            final UserIdToken userIdToken2 = this;
            final long n = userIdToken2.renewalWindow;
            jsonObject4.put(s, n);
            final JSONObject jsonObject5 = jsonObject3;
            final String s2 = "expiration";
            final UserIdToken userIdToken3 = this;
            final long n2 = userIdToken3.expiration;
            jsonObject5.put(s2, n2);
            final JSONObject jsonObject6 = jsonObject3;
            final String s3 = "mtserialnumber";
            final UserIdToken userIdToken4 = this;
            final long n3 = userIdToken4.mtSerialNumber;
            jsonObject6.put(s3, n3);
            final JSONObject jsonObject7 = jsonObject3;
            final String s4 = "serialnumber";
            final UserIdToken userIdToken5 = this;
            final long n4 = userIdToken5.serialNumber;
            jsonObject7.put(s4, n4);
            final JSONObject jsonObject8 = jsonObject3;
            final String s5 = "userdata";
            final byte[] array3 = array2;
            final String s6 = Base64.encode(array3);
            jsonObject8.put(s5, s6);
            final UserIdToken userIdToken6 = this;
            final JSONObject jsonObject9 = jsonObject3;
            final String s7 = jsonObject9.toString();
            final Charset charset = MslConstants.DEFAULT_CHARSET;
            final byte[] array4 = s7.getBytes(charset);
            userIdToken6.tokendata = array4;
            final UserIdToken userIdToken7 = this;
            final ICryptoContext cryptoContext4 = cryptoContext2;
            final UserIdToken userIdToken8 = this;
            final byte[] array5 = userIdToken8.tokendata;
            final byte[] array6 = cryptoContext4.sign(array5);
            userIdToken7.signature = array6;
            final UserIdToken userIdToken9 = this;
            final boolean b = true;
            userIdToken9.verified = b;
            return;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "userdata", ex);
        }
        try {
            final MslContext mslContext = ctx;
            final ICryptoContext cryptoContext = mslContext.getMslCryptoContext();
            final ICryptoContext cryptoContext3;
            final ICryptoContext cryptoContext2 = cryptoContext3 = cryptoContext;
            final UserIdToken userIdToken = this;
            final byte[] array = userIdToken.userdata;
            final byte[] array2 = cryptoContext3.encrypt(array);
            try {
                final JSONObject jsonObject2 = new JSONObject();
                final JSONObject jsonObject4;
                final JSONObject jsonObject3 = jsonObject4 = jsonObject2;
                final String s = "renewalwindow";
                final UserIdToken userIdToken2 = this;
                final long n = userIdToken2.renewalWindow;
                jsonObject4.put(s, n);
                final JSONObject jsonObject5 = jsonObject3;
                final String s2 = "expiration";
                final UserIdToken userIdToken3 = this;
                final long n2 = userIdToken3.expiration;
                jsonObject5.put(s2, n2);
                final JSONObject jsonObject6 = jsonObject3;
                final String s3 = "mtserialnumber";
                final UserIdToken userIdToken4 = this;
                final long n3 = userIdToken4.mtSerialNumber;
                jsonObject6.put(s3, n3);
                final JSONObject jsonObject7 = jsonObject3;
                final String s4 = "serialnumber";
                final UserIdToken userIdToken5 = this;
                final long n4 = userIdToken5.serialNumber;
                jsonObject7.put(s4, n4);
                final JSONObject jsonObject8 = jsonObject3;
                final String s5 = "userdata";
                final byte[] array3 = array2;
                final String s6 = Base64.encode(array3);
                jsonObject8.put(s5, s6);
                final UserIdToken userIdToken6 = this;
                final JSONObject jsonObject9 = jsonObject3;
                final String s7 = jsonObject9.toString();
                final Charset charset = MslConstants.DEFAULT_CHARSET;
                final byte[] array4 = s7.getBytes(charset);
                userIdToken6.tokendata = array4;
                final UserIdToken userIdToken7 = this;
                final ICryptoContext cryptoContext4 = cryptoContext2;
                final UserIdToken userIdToken8 = this;
                final byte[] array5 = userIdToken8.tokendata;
                final byte[] array6 = cryptoContext4.sign(array5);
                userIdToken7.signature = array6;
                final UserIdToken userIdToken9 = this;
                final boolean b = true;
                userIdToken9.verified = b;
            }
            catch (JSONException ex2) {
                throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "usertokendata", ex2).setMasterToken(masterToken);
            }
        }
        catch (MslCryptoException ex3) {
            ex3.setMasterToken(masterToken);
            throw ex3;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof UserIdToken)) {
                return false;
            }
            final UserIdToken userIdToken = (UserIdToken)o;
            if (this.serialNumber != userIdToken.serialNumber || this.mtSerialNumber != userIdToken.mtSerialNumber) {
                return false;
            }
        }
        return true;
    }
    
    public Date getExpiration() {
        return new Date(this.expiration * 1000L);
    }
    
    public JSONObject getIssuerData() {
        return this.issuerData;
    }
    
    public long getMasterTokenSerialNumber() {
        return this.mtSerialNumber;
    }
    
    public Date getRenewalWindow() {
        return new Date(this.renewalWindow * 1000L);
    }
    
    public long getSerialNumber() {
        return this.serialNumber;
    }
    
    public MslUser getUser() {
        return this.user;
    }
    
    @Override
    public int hashCode() {
        return (String.valueOf(this.serialNumber) + ":" + String.valueOf(this.mtSerialNumber)).hashCode();
    }
    
    public boolean isBoundTo(final MasterToken masterToken) {
        return masterToken != null && masterToken.getSerialNumber() == this.mtSerialNumber;
    }
    
    public boolean isDecrypted() {
        return this.user != null;
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
    public final String toJSONString() {
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
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("renewalwindow", this.renewalWindow);
            jsonObject.put("expiration", this.expiration);
            jsonObject.put("mtserialnumber", this.mtSerialNumber);
            jsonObject.put("serialnumber", this.serialNumber);
            jsonObject.put("userdata", "(redacted)");
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("tokendata", jsonObject);
            jsonObject2.put("signature", Base64.encode(this.signature));
            return jsonObject2.toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
}
