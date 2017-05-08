// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.tokens;

import com.netflix.msl.MslCryptoException;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslConstants;
import com.netflix.msl.util.Base64;
import com.netflix.msl.util.MslUtils;
import com.netflix.msl.MslInternalException;
import java.util.Map;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.MslConstants$CompressionAlgorithm;
import com.netflix.android.org.json.JSONString;

public class ServiceToken implements JSONString
{
    private static final String KEY_COMPRESSION_ALGORITHM = "compressionalgo";
    private static final String KEY_ENCRYPTED = "encrypted";
    private static final String KEY_MASTER_TOKEN_SERIAL_NUMBER = "mtserialnumber";
    private static final String KEY_NAME = "name";
    private static final String KEY_SERVICEDATA = "servicedata";
    private static final String KEY_SIGNATURE = "signature";
    private static final String KEY_TOKENDATA = "tokendata";
    private static final String KEY_USER_ID_TOKEN_SERIAL_NUMBER = "uitserialnumber";
    private final MslConstants$CompressionAlgorithm compressionAlgo;
    private final boolean encrypted;
    private final long mtSerialNumber;
    private final String name;
    private final byte[] servicedata;
    private final byte[] signature;
    private final byte[] tokendata;
    private final long uitSerialNumber;
    private final boolean verified;
    
    public ServiceToken(final MslContext p0, final JSONObject p1, final MasterToken p2, final UserIdToken p3, final ICryptoContext p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: aload_2        
        //     6: ldc             "tokendata"
        //     8: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    11: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    14: putfield        com/netflix/msl/tokens/ServiceToken.tokendata:[B
        //    17: aload_0        
        //    18: getfield        com/netflix/msl/tokens/ServiceToken.tokendata:[B
        //    21: ifnull          32
        //    24: aload_0        
        //    25: getfield        com/netflix/msl/tokens/ServiceToken.tokendata:[B
        //    28: arraylength    
        //    29: ifne            171
        //    32: new             Lcom/netflix/msl/MslEncodingException;
        //    35: dup            
        //    36: getstatic       com/netflix/msl/MslError.SERVICETOKEN_TOKENDATA_MISSING:Lcom/netflix/msl/MslError;
        //    39: new             Ljava/lang/StringBuilder;
        //    42: dup            
        //    43: invokespecial   java/lang/StringBuilder.<init>:()V
        //    46: ldc             "servicetoken "
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: aload_2        
        //    52: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    55: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    58: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    61: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    64: aload_3        
        //    65: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //    68: aload           4
        //    70: invokevirtual   com/netflix/msl/MslEncodingException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslEncodingException;
        //    73: athrow         
        //    74: astore_1       
        //    75: new             Lcom/netflix/msl/MslEncodingException;
        //    78: dup            
        //    79: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //    82: new             Ljava/lang/StringBuilder;
        //    85: dup            
        //    86: invokespecial   java/lang/StringBuilder.<init>:()V
        //    89: ldc             "servicetoken "
        //    91: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    94: aload_2        
        //    95: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   104: aload_1        
        //   105: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   108: aload_3        
        //   109: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   112: aload           4
        //   114: invokevirtual   com/netflix/msl/MslEncodingException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslEncodingException;
        //   117: athrow         
        //   118: astore_1       
        //   119: new             Lcom/netflix/msl/MslEncodingException;
        //   122: dup            
        //   123: getstatic       com/netflix/msl/MslError.SERVICETOKEN_TOKENDATA_INVALID:Lcom/netflix/msl/MslError;
        //   126: new             Ljava/lang/StringBuilder;
        //   129: dup            
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: ldc             "servicetoken "
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: aload_2        
        //   139: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   148: aload_1        
        //   149: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   152: aload_3        
        //   153: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   156: aload           4
        //   158: invokevirtual   com/netflix/msl/MslEncodingException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslEncodingException;
        //   161: athrow         
        //   162: astore_1       
        //   163: aload_1        
        //   164: aload_3        
        //   165: invokevirtual   com/netflix/msl/MslCryptoException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslCryptoException;
        //   168: pop            
        //   169: aload_1        
        //   170: athrow         
        //   171: aload_0        
        //   172: aload_2        
        //   173: ldc             "signature"
        //   175: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   178: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   181: putfield        com/netflix/msl/tokens/ServiceToken.signature:[B
        //   184: aload           5
        //   186: ifnull          413
        //   189: aload           5
        //   191: aload_0        
        //   192: getfield        com/netflix/msl/tokens/ServiceToken.tokendata:[B
        //   195: aload_0        
        //   196: getfield        com/netflix/msl/tokens/ServiceToken.signature:[B
        //   199: invokeinterface com/netflix/msl/crypto/ICryptoContext.verify:([B[B)Z
        //   204: istore          6
        //   206: aload_0        
        //   207: iload           6
        //   209: putfield        com/netflix/msl/tokens/ServiceToken.verified:Z
        //   212: new             Ljava/lang/String;
        //   215: dup            
        //   216: aload_0        
        //   217: getfield        com/netflix/msl/tokens/ServiceToken.tokendata:[B
        //   220: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   223: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   226: astore          7
        //   228: new             Lcom/netflix/android/org/json/JSONObject;
        //   231: dup            
        //   232: aload           7
        //   234: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   237: astore_2       
        //   238: aload_0        
        //   239: aload_2        
        //   240: ldc             "name"
        //   242: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   245: putfield        com/netflix/msl/tokens/ServiceToken.name:Ljava/lang/String;
        //   248: aload_2        
        //   249: ldc             "mtserialnumber"
        //   251: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   254: ifeq            419
        //   257: aload_0        
        //   258: aload_2        
        //   259: ldc             "mtserialnumber"
        //   261: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   264: putfield        com/netflix/msl/tokens/ServiceToken.mtSerialNumber:J
        //   267: aload_0        
        //   268: getfield        com/netflix/msl/tokens/ServiceToken.mtSerialNumber:J
        //   271: lconst_0       
        //   272: lcmp           
        //   273: iflt            287
        //   276: aload_0        
        //   277: getfield        com/netflix/msl/tokens/ServiceToken.mtSerialNumber:J
        //   280: ldc2_w          9007199254740992
        //   283: lcmp           
        //   284: ifle            426
        //   287: new             Lcom/netflix/msl/MslException;
        //   290: dup            
        //   291: getstatic       com/netflix/msl/MslError.SERVICETOKEN_MASTERTOKEN_SERIAL_NUMBER_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   294: new             Ljava/lang/StringBuilder;
        //   297: dup            
        //   298: invokespecial   java/lang/StringBuilder.<init>:()V
        //   301: ldc             "servicetokendata "
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: aload           7
        //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   311: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   314: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   317: aload_3        
        //   318: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   321: aload           4
        //   323: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   326: athrow         
        //   327: astore_1       
        //   328: new             Lcom/netflix/msl/MslEncodingException;
        //   331: dup            
        //   332: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   335: new             Ljava/lang/StringBuilder;
        //   338: dup            
        //   339: invokespecial   java/lang/StringBuilder.<init>:()V
        //   342: ldc             "servicetokendata "
        //   344: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   347: aload           7
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   355: aload_1        
        //   356: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   359: aload_3        
        //   360: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   363: aload           4
        //   365: invokevirtual   com/netflix/msl/MslEncodingException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslEncodingException;
        //   368: athrow         
        //   369: astore_1       
        //   370: new             Lcom/netflix/msl/MslEncodingException;
        //   373: dup            
        //   374: getstatic       com/netflix/msl/MslError.SERVICETOKEN_SIGNATURE_INVALID:Lcom/netflix/msl/MslError;
        //   377: new             Ljava/lang/StringBuilder;
        //   380: dup            
        //   381: invokespecial   java/lang/StringBuilder.<init>:()V
        //   384: ldc             "servicetoken "
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: aload_2        
        //   390: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   393: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   396: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   399: aload_1        
        //   400: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   403: aload_3        
        //   404: invokevirtual   com/netflix/msl/MslEncodingException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslEncodingException;
        //   407: aload           4
        //   409: invokevirtual   com/netflix/msl/MslEncodingException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslEncodingException;
        //   412: athrow         
        //   413: iconst_0       
        //   414: istore          6
        //   416: goto            206
        //   419: aload_0        
        //   420: ldc2_w          -1
        //   423: putfield        com/netflix/msl/tokens/ServiceToken.mtSerialNumber:J
        //   426: aload_2        
        //   427: ldc             "uitserialnumber"
        //   429: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   432: ifeq            521
        //   435: aload_0        
        //   436: aload_2        
        //   437: ldc             "uitserialnumber"
        //   439: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   442: putfield        com/netflix/msl/tokens/ServiceToken.uitSerialNumber:J
        //   445: aload_0        
        //   446: getfield        com/netflix/msl/tokens/ServiceToken.uitSerialNumber:J
        //   449: lconst_0       
        //   450: lcmp           
        //   451: iflt            465
        //   454: aload_0        
        //   455: getfield        com/netflix/msl/tokens/ServiceToken.uitSerialNumber:J
        //   458: ldc2_w          9007199254740992
        //   461: lcmp           
        //   462: ifle            528
        //   465: new             Lcom/netflix/msl/MslException;
        //   468: dup            
        //   469: getstatic       com/netflix/msl/MslError.SERVICETOKEN_USERIDTOKEN_SERIAL_NUMBER_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   472: new             Ljava/lang/StringBuilder;
        //   475: dup            
        //   476: invokespecial   java/lang/StringBuilder.<init>:()V
        //   479: ldc             "servicetokendata "
        //   481: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   484: aload           7
        //   486: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   489: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   492: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   495: aload_3        
        //   496: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   499: aload           4
        //   501: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   504: athrow         
        //   505: astore_1       
        //   506: aload_1        
        //   507: aload_3        
        //   508: invokevirtual   com/netflix/msl/MslCryptoException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslCryptoException;
        //   511: pop            
        //   512: aload_1        
        //   513: aload           4
        //   515: invokevirtual   com/netflix/msl/MslCryptoException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   518: pop            
        //   519: aload_1        
        //   520: athrow         
        //   521: aload_0        
        //   522: ldc2_w          -1
        //   525: putfield        com/netflix/msl/tokens/ServiceToken.uitSerialNumber:J
        //   528: aload_0        
        //   529: aload_2        
        //   530: ldc             "encrypted"
        //   532: invokevirtual   com/netflix/android/org/json/JSONObject.getBoolean:(Ljava/lang/String;)Z
        //   535: putfield        com/netflix/msl/tokens/ServiceToken.encrypted:Z
        //   538: aload_2        
        //   539: ldc             "compressionalgo"
        //   541: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   544: ifeq            643
        //   547: aload_2        
        //   548: ldc             "compressionalgo"
        //   550: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   553: astore_1       
        //   554: aload_0        
        //   555: aload_1        
        //   556: invokestatic    com/netflix/msl/MslConstants$CompressionAlgorithm.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   559: putfield        com/netflix/msl/tokens/ServiceToken.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   562: aload_2        
        //   563: ldc             "servicedata"
        //   565: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   568: astore_1       
        //   569: aload_0        
        //   570: getfield        com/netflix/msl/tokens/ServiceToken.verified:Z
        //   573: istore          6
        //   575: iload           6
        //   577: ifeq            818
        //   580: aload_1        
        //   581: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   584: astore_2       
        //   585: aload_2        
        //   586: ifnonnull       692
        //   589: new             Lcom/netflix/msl/MslException;
        //   592: dup            
        //   593: getstatic       com/netflix/msl/MslError.SERVICETOKEN_SERVICEDATA_INVALID:Lcom/netflix/msl/MslError;
        //   596: new             Ljava/lang/StringBuilder;
        //   599: dup            
        //   600: invokespecial   java/lang/StringBuilder.<init>:()V
        //   603: ldc             "servicetokendata "
        //   605: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   608: aload           7
        //   610: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   613: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   616: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   619: aload_3        
        //   620: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   623: aload           4
        //   625: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   628: athrow         
        //   629: astore_2       
        //   630: new             Lcom/netflix/msl/MslException;
        //   633: dup            
        //   634: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_COMPRESSION:Lcom/netflix/msl/MslError;
        //   637: aload_1        
        //   638: aload_2        
        //   639: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   642: athrow         
        //   643: aload_0        
        //   644: aconst_null    
        //   645: putfield        com/netflix/msl/tokens/ServiceToken.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   648: goto            562
        //   651: astore_1       
        //   652: new             Lcom/netflix/msl/MslException;
        //   655: dup            
        //   656: getstatic       com/netflix/msl/MslError.SERVICETOKEN_SERVICEDATA_INVALID:Lcom/netflix/msl/MslError;
        //   659: new             Ljava/lang/StringBuilder;
        //   662: dup            
        //   663: invokespecial   java/lang/StringBuilder.<init>:()V
        //   666: ldc             "servicetokendata "
        //   668: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   671: aload           7
        //   673: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   676: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   679: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   682: aload_3        
        //   683: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   686: aload           4
        //   688: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   691: athrow         
        //   692: aload_2        
        //   693: astore_1       
        //   694: aload_0        
        //   695: getfield        com/netflix/msl/tokens/ServiceToken.encrypted:Z
        //   698: ifeq            717
        //   701: aload_2        
        //   702: astore_1       
        //   703: aload_2        
        //   704: arraylength    
        //   705: ifle            717
        //   708: aload           5
        //   710: aload_2        
        //   711: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   716: astore_1       
        //   717: aload_1        
        //   718: astore_2       
        //   719: aload_0        
        //   720: getfield        com/netflix/msl/tokens/ServiceToken.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   723: ifnull          735
        //   726: aload_0        
        //   727: getfield        com/netflix/msl/tokens/ServiceToken.compressionAlgo:Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //   730: aload_1        
        //   731: invokestatic    com/netflix/msl/util/MslUtils.uncompress:(Lcom/netflix/msl/MslConstants$CompressionAlgorithm;[B)[B
        //   734: astore_2       
        //   735: aload_0        
        //   736: aload_2        
        //   737: putfield        com/netflix/msl/tokens/ServiceToken.servicedata:[B
        //   740: aload_0        
        //   741: getfield        com/netflix/msl/tokens/ServiceToken.mtSerialNumber:J
        //   744: ldc2_w          -1
        //   747: lcmp           
        //   748: ifeq            842
        //   751: aload_3        
        //   752: ifnull          767
        //   755: aload_0        
        //   756: getfield        com/netflix/msl/tokens/ServiceToken.mtSerialNumber:J
        //   759: aload_3        
        //   760: invokevirtual   com/netflix/msl/tokens/MasterToken.getSerialNumber:()J
        //   763: lcmp           
        //   764: ifeq            842
        //   767: new             Lcom/netflix/msl/MslException;
        //   770: dup            
        //   771: getstatic       com/netflix/msl/MslError.SERVICETOKEN_MASTERTOKEN_MISMATCH:Lcom/netflix/msl/MslError;
        //   774: new             Ljava/lang/StringBuilder;
        //   777: dup            
        //   778: invokespecial   java/lang/StringBuilder.<init>:()V
        //   781: ldc             "st mtserialnumber "
        //   783: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   786: aload_0        
        //   787: getfield        com/netflix/msl/tokens/ServiceToken.mtSerialNumber:J
        //   790: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   793: ldc             "; mt "
        //   795: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   798: aload_3        
        //   799: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   802: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   805: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   808: aload_3        
        //   809: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   812: aload           4
        //   814: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   817: athrow         
        //   818: aload_1        
        //   819: invokevirtual   java/lang/String.isEmpty:()Z
        //   822: ifeq            837
        //   825: iconst_0       
        //   826: newarray        B
        //   828: astore_1       
        //   829: aload_0        
        //   830: aload_1        
        //   831: putfield        com/netflix/msl/tokens/ServiceToken.servicedata:[B
        //   834: goto            740
        //   837: aconst_null    
        //   838: astore_1       
        //   839: goto            829
        //   842: aload_0        
        //   843: getfield        com/netflix/msl/tokens/ServiceToken.uitSerialNumber:J
        //   846: ldc2_w          -1
        //   849: lcmp           
        //   850: ifeq            923
        //   853: aload           4
        //   855: ifnull          871
        //   858: aload_0        
        //   859: getfield        com/netflix/msl/tokens/ServiceToken.uitSerialNumber:J
        //   862: aload           4
        //   864: invokevirtual   com/netflix/msl/tokens/UserIdToken.getSerialNumber:()J
        //   867: lcmp           
        //   868: ifeq            923
        //   871: new             Lcom/netflix/msl/MslException;
        //   874: dup            
        //   875: getstatic       com/netflix/msl/MslError.SERVICETOKEN_USERIDTOKEN_MISMATCH:Lcom/netflix/msl/MslError;
        //   878: new             Ljava/lang/StringBuilder;
        //   881: dup            
        //   882: invokespecial   java/lang/StringBuilder.<init>:()V
        //   885: ldc             "st uitserialnumber "
        //   887: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   890: aload_0        
        //   891: getfield        com/netflix/msl/tokens/ServiceToken.uitSerialNumber:J
        //   894: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   897: ldc             "; uit "
        //   899: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   902: aload           4
        //   904: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   907: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   910: invokespecial   com/netflix/msl/MslException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   913: aload_3        
        //   914: invokevirtual   com/netflix/msl/MslException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslException;
        //   917: aload           4
        //   919: invokevirtual   com/netflix/msl/MslException.setUserIdToken:(Lcom/netflix/msl/tokens/UserIdToken;)Lcom/netflix/msl/MslException;
        //   922: athrow         
        //   923: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  4      17     118    162    Ljava/lang/IllegalArgumentException;
        //  4      17     74     118    Lcom/netflix/android/org/json/JSONException;
        //  4      17     162    171    Lcom/netflix/msl/MslCryptoException;
        //  17     32     74     118    Lcom/netflix/android/org/json/JSONException;
        //  17     32     162    171    Lcom/netflix/msl/MslCryptoException;
        //  32     74     74     118    Lcom/netflix/android/org/json/JSONException;
        //  32     74     162    171    Lcom/netflix/msl/MslCryptoException;
        //  119    162    74     118    Lcom/netflix/android/org/json/JSONException;
        //  119    162    162    171    Lcom/netflix/msl/MslCryptoException;
        //  171    184    369    413    Ljava/lang/IllegalArgumentException;
        //  171    184    74     118    Lcom/netflix/android/org/json/JSONException;
        //  171    184    162    171    Lcom/netflix/msl/MslCryptoException;
        //  189    206    74     118    Lcom/netflix/android/org/json/JSONException;
        //  189    206    162    171    Lcom/netflix/msl/MslCryptoException;
        //  206    212    74     118    Lcom/netflix/android/org/json/JSONException;
        //  206    212    162    171    Lcom/netflix/msl/MslCryptoException;
        //  228    287    327    369    Lcom/netflix/android/org/json/JSONException;
        //  228    287    505    521    Lcom/netflix/msl/MslCryptoException;
        //  287    327    327    369    Lcom/netflix/android/org/json/JSONException;
        //  287    327    505    521    Lcom/netflix/msl/MslCryptoException;
        //  370    413    74     118    Lcom/netflix/android/org/json/JSONException;
        //  370    413    162    171    Lcom/netflix/msl/MslCryptoException;
        //  419    426    327    369    Lcom/netflix/android/org/json/JSONException;
        //  419    426    505    521    Lcom/netflix/msl/MslCryptoException;
        //  426    465    327    369    Lcom/netflix/android/org/json/JSONException;
        //  426    465    505    521    Lcom/netflix/msl/MslCryptoException;
        //  465    505    327    369    Lcom/netflix/android/org/json/JSONException;
        //  465    505    505    521    Lcom/netflix/msl/MslCryptoException;
        //  521    528    327    369    Lcom/netflix/android/org/json/JSONException;
        //  521    528    505    521    Lcom/netflix/msl/MslCryptoException;
        //  528    554    327    369    Lcom/netflix/android/org/json/JSONException;
        //  528    554    505    521    Lcom/netflix/msl/MslCryptoException;
        //  554    562    629    643    Ljava/lang/IllegalArgumentException;
        //  554    562    327    369    Lcom/netflix/android/org/json/JSONException;
        //  554    562    505    521    Lcom/netflix/msl/MslCryptoException;
        //  562    575    327    369    Lcom/netflix/android/org/json/JSONException;
        //  562    575    505    521    Lcom/netflix/msl/MslCryptoException;
        //  580    585    651    692    Ljava/lang/IllegalArgumentException;
        //  580    585    327    369    Lcom/netflix/android/org/json/JSONException;
        //  580    585    505    521    Lcom/netflix/msl/MslCryptoException;
        //  589    629    327    369    Lcom/netflix/android/org/json/JSONException;
        //  589    629    505    521    Lcom/netflix/msl/MslCryptoException;
        //  630    643    327    369    Lcom/netflix/android/org/json/JSONException;
        //  630    643    505    521    Lcom/netflix/msl/MslCryptoException;
        //  643    648    327    369    Lcom/netflix/android/org/json/JSONException;
        //  643    648    505    521    Lcom/netflix/msl/MslCryptoException;
        //  652    692    327    369    Lcom/netflix/android/org/json/JSONException;
        //  652    692    505    521    Lcom/netflix/msl/MslCryptoException;
        //  694    701    327    369    Lcom/netflix/android/org/json/JSONException;
        //  694    701    505    521    Lcom/netflix/msl/MslCryptoException;
        //  703    717    327    369    Lcom/netflix/android/org/json/JSONException;
        //  703    717    505    521    Lcom/netflix/msl/MslCryptoException;
        //  719    735    327    369    Lcom/netflix/android/org/json/JSONException;
        //  719    735    505    521    Lcom/netflix/msl/MslCryptoException;
        //  735    740    327    369    Lcom/netflix/android/org/json/JSONException;
        //  735    740    505    521    Lcom/netflix/msl/MslCryptoException;
        //  818    829    327    369    Lcom/netflix/android/org/json/JSONException;
        //  818    829    505    521    Lcom/netflix/msl/MslCryptoException;
        //  829    834    327    369    Lcom/netflix/android/org/json/JSONException;
        //  829    834    505    521    Lcom/netflix/msl/MslCryptoException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 435, Size: 435
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
    
    public ServiceToken(final MslContext mslContext, final JSONObject jsonObject, final MasterToken masterToken, final UserIdToken userIdToken, final Map<String, ICryptoContext> map) {
        this(mslContext, jsonObject, masterToken, userIdToken, selectCryptoContext(jsonObject, map));
    }
    
    public ServiceToken(final MslContext mslContext, final String name, final byte[] servicedata, final MasterToken masterToken, final UserIdToken userIdToken, final boolean encrypted, final MslConstants$CompressionAlgorithm compressionAlgo, final ICryptoContext cryptoContext) {
        if (masterToken != null && userIdToken != null && !userIdToken.isBoundTo(masterToken)) {
            throw new MslInternalException("Cannot construct a service token bound to a master token and user ID token where the user ID token is not bound to the same master token.");
        }
        Label_0286: {
            if (compressionAlgo == null) {
                break Label_0286;
            }
            byte[] compress = MslUtils.compress(compressionAlgo, servicedata);
            Label_0276: {
                if (compress.length >= servicedata.length) {
                    break Label_0276;
                }
                this.compressionAlgo = compressionAlgo;
            Label_0077_Outer:
                while (true) {
                    this.name = name;
                    Label_0296: {
                        if (masterToken == null) {
                            break Label_0296;
                        }
                        long serialNumber = masterToken.getSerialNumber();
                        while (true) {
                            this.mtSerialNumber = serialNumber;
                            Label_0304: {
                                if (userIdToken == null) {
                                    break Label_0304;
                                }
                                long serialNumber2 = userIdToken.getSerialNumber();
                                this.uitSerialNumber = serialNumber2;
                                this.servicedata = servicedata;
                                this.encrypted = encrypted;
                                byte[] encrypt = compress;
                                Label_0135: {
                                    if (!encrypted) {
                                        break Label_0135;
                                    }
                                    encrypt = compress;
                                    try {
                                        if (compress.length > 0) {
                                            encrypt = cryptoContext.encrypt(compress);
                                        }
                                        try {
                                            final JSONObject jsonObject = new JSONObject();
                                            jsonObject.put("name", this.name);
                                            if (this.mtSerialNumber != -1L) {
                                                jsonObject.put("mtserialnumber", this.mtSerialNumber);
                                            }
                                            if (this.uitSerialNumber != -1L) {
                                                jsonObject.put("uitserialnumber", this.uitSerialNumber);
                                            }
                                            jsonObject.put("encrypted", this.encrypted);
                                            if (this.compressionAlgo != null) {
                                                jsonObject.put("compressionalgo", this.compressionAlgo.name());
                                            }
                                            jsonObject.put("servicedata", Base64.encode(encrypt));
                                            this.tokendata = jsonObject.toString().getBytes(MslConstants.DEFAULT_CHARSET);
                                            this.signature = cryptoContext.sign(this.tokendata);
                                            this.verified = true;
                                            return;
                                            this.compressionAlgo = null;
                                            compress = servicedata;
                                            continue Label_0077_Outer;
                                            this.compressionAlgo = null;
                                            compress = servicedata;
                                            continue Label_0077_Outer;
                                            serialNumber = -1L;
                                            continue;
                                            serialNumber2 = -1L;
                                        }
                                        catch (JSONException ex) {
                                            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "servicetoken", ex).setMasterToken(masterToken).setUserIdToken(userIdToken);
                                        }
                                    }
                                    catch (MslCryptoException ex2) {
                                        ex2.setMasterToken(masterToken);
                                        ex2.setUserIdToken(userIdToken);
                                        throw ex2;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private static ICryptoContext selectCryptoContext(final JSONObject jsonObject, final Map<String, ICryptoContext> map) {
        byte[] decode;
        try {
            try {
                decode = Base64.decode(jsonObject.getString("tokendata"));
                if (decode == null || decode.length == 0) {
                    throw new MslEncodingException(MslError.SERVICETOKEN_TOKENDATA_MISSING, "servicetoken " + jsonObject.toString());
                }
            }
            catch (JSONException ex) {
                throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "servicetoken " + jsonObject.toString(), ex);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw new MslEncodingException(MslError.SERVICETOKEN_TOKENDATA_INVALID, "servicetoken " + jsonObject.toString(), ex2);
        }
        final String string = new JSONObject(new String(decode, MslConstants.DEFAULT_CHARSET)).getString("name");
        if (map.containsKey(string)) {
            return map.get(string);
        }
        return map.get("");
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ServiceToken)) {
                return false;
            }
            final ServiceToken serviceToken = (ServiceToken)o;
            if (!this.name.equals(serviceToken.name) || this.mtSerialNumber != serviceToken.mtSerialNumber || this.uitSerialNumber != serviceToken.uitSerialNumber) {
                return false;
            }
        }
        return true;
    }
    
    public MslConstants$CompressionAlgorithm getCompressionAlgo() {
        return this.compressionAlgo;
    }
    
    public byte[] getData() {
        return this.servicedata;
    }
    
    public long getMasterTokenSerialNumber() {
        return this.mtSerialNumber;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getUserIdTokenSerialNumber() {
        return this.uitSerialNumber;
    }
    
    @Override
    public int hashCode() {
        return (this.name + ":" + String.valueOf(this.mtSerialNumber) + ":" + String.valueOf(this.uitSerialNumber)).hashCode();
    }
    
    public boolean isBoundTo(final MasterToken masterToken) {
        return masterToken != null && masterToken.getSerialNumber() == this.mtSerialNumber;
    }
    
    public boolean isBoundTo(final UserIdToken userIdToken) {
        return userIdToken != null && userIdToken.getSerialNumber() == this.uitSerialNumber;
    }
    
    public boolean isDecrypted() {
        return this.servicedata != null;
    }
    
    public boolean isDeleted() {
        return this.servicedata != null && this.servicedata.length == 0;
    }
    
    public boolean isEncrypted() {
        return this.encrypted;
    }
    
    public boolean isMasterTokenBound() {
        return this.mtSerialNumber != -1L;
    }
    
    public boolean isUnbound() {
        return this.mtSerialNumber == -1L && this.uitSerialNumber == -1L;
    }
    
    public boolean isUserIdTokenBound() {
        return this.uitSerialNumber != -1L;
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
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", this.name);
            jsonObject.put("mtserialnumber", this.mtSerialNumber);
            jsonObject.put("uitserialnumber", this.uitSerialNumber);
            jsonObject.put("servicedata", "(redacted)");
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
