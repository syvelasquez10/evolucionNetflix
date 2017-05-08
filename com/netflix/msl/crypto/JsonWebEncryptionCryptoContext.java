// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.MslContext;
import java.nio.charset.Charset;

public class JsonWebEncryptionCryptoContext implements ICryptoContext
{
    private static final int A128_GCM_AT_LENGTH = 128;
    private static final int A128_GCM_IV_LENGTH = 12;
    private static final int A128_GCM_KEY_LENGTH = 16;
    private static final int A256_GCM_AT_LENGTH = 128;
    private static final int A256_GCM_IV_LENGTH = 12;
    private static final int A256_GCM_KEY_LENGTH = 32;
    private static final String KEY_ALGORITHM = "alg";
    private static final String KEY_CIPHERTEXT = "ciphertext";
    private static final String KEY_ENCRYPTED_KEY = "encrypted_key";
    private static final String KEY_ENCRYPTION = "enc";
    private static final String KEY_HEADER = "header";
    private static final String KEY_INITIALIZATION_VECTOR = "initialization_vector";
    private static final String KEY_INTEGRITY_VALUE = "integrity_value";
    private static final String KEY_RECIPIENTS = "recipients";
    private static final Charset UTF_8;
    private final JsonWebEncryptionCryptoContext$Algorithm algo;
    private final ICryptoContext cekCryptoContext;
    private final MslContext ctx;
    private final JsonWebEncryptionCryptoContext$Encryption enc;
    private final JsonWebEncryptionCryptoContext$Format format;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
    }
    
    public JsonWebEncryptionCryptoContext(final MslContext ctx, final JsonWebEncryptionCryptoContext$CekCryptoContext cekCryptoContext, final JsonWebEncryptionCryptoContext$Encryption enc, final JsonWebEncryptionCryptoContext$Format format) {
        this.ctx = ctx;
        this.cekCryptoContext = cekCryptoContext;
        this.algo = cekCryptoContext.getAlgorithm();
        this.enc = enc;
        this.format = format;
    }
    
    @Override
    public byte[] decrypt(final byte[] array) {
        throw new MslCryptoException(MslError.DECRYPT_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] encrypt(final byte[] array) {
        throw new MslCryptoException(MslError.ENCRYPT_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] sign(final byte[] array) {
        throw new MslCryptoException(MslError.SIGN_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] unwrap(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/String;
        //     3: dup            
        //     4: aload_1        
        //     5: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.UTF_8:Ljava/nio/charset/Charset;
        //     8: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //    11: astore          7
        //    13: aload_1        
        //    14: iconst_0       
        //    15: baload         
        //    16: bipush          123
        //    18: if_icmpne       186
        //    21: new             Lcom/netflix/android/org/json/JSONObject;
        //    24: dup            
        //    25: aload           7
        //    27: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    30: astore_1       
        //    31: aload_1        
        //    32: ldc             "initialization_vector"
        //    34: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    37: astore          4
        //    39: aload_1        
        //    40: ldc             "ciphertext"
        //    42: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    45: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //    48: astore_3       
        //    49: aload_1        
        //    50: ldc             "recipients"
        //    52: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONArray;
        //    55: iconst_0       
        //    56: invokevirtual   com/netflix/android/org/json/JSONArray.getJSONObject:(I)Lcom/netflix/android/org/json/JSONObject;
        //    59: astore_1       
        //    60: aload_1        
        //    61: ldc             "header"
        //    63: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    66: astore          6
        //    68: aload_1        
        //    69: ldc             "encrypted_key"
        //    71: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    74: astore          5
        //    76: aload_1        
        //    77: ldc             "integrity_value"
        //    79: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    82: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //    85: astore_1       
        //    86: aload           6
        //    88: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //    91: astore          10
        //    93: aload           5
        //    95: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //    98: astore          9
        //   100: aload           4
        //   102: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //   105: astore          8
        //   107: aload           10
        //   109: ifnull          158
        //   112: aload           10
        //   114: arraylength    
        //   115: ifeq            158
        //   118: aload           9
        //   120: ifnull          158
        //   123: aload           9
        //   125: arraylength    
        //   126: ifeq            158
        //   129: aload           8
        //   131: ifnull          158
        //   134: aload           8
        //   136: arraylength    
        //   137: ifeq            158
        //   140: aload_3        
        //   141: ifnull          158
        //   144: aload_3        
        //   145: arraylength    
        //   146: ifeq            158
        //   149: aload_1        
        //   150: ifnull          158
        //   153: aload_1        
        //   154: arraylength    
        //   155: ifne            245
        //   158: new             Lcom/netflix/msl/MslCryptoException;
        //   161: dup            
        //   162: getstatic       com/netflix/msl/MslError.JWE_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   165: aload           7
        //   167: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   170: athrow         
        //   171: astore_1       
        //   172: new             Lcom/netflix/msl/MslCryptoException;
        //   175: dup            
        //   176: getstatic       com/netflix/msl/MslError.JWE_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   179: aload           7
        //   181: aload_1        
        //   182: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   185: athrow         
        //   186: aload           7
        //   188: ldc             "\\."
        //   190: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   193: astore_1       
        //   194: aload_1        
        //   195: arraylength    
        //   196: iconst_5       
        //   197: if_icmpeq       213
        //   200: new             Lcom/netflix/msl/MslCryptoException;
        //   203: dup            
        //   204: getstatic       com/netflix/msl/MslError.JWE_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   207: aload           7
        //   209: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   212: athrow         
        //   213: aload_1        
        //   214: iconst_0       
        //   215: aaload         
        //   216: astore          6
        //   218: aload_1        
        //   219: iconst_1       
        //   220: aaload         
        //   221: astore          5
        //   223: aload_1        
        //   224: iconst_2       
        //   225: aaload         
        //   226: astore          4
        //   228: aload_1        
        //   229: iconst_3       
        //   230: aaload         
        //   231: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //   234: astore_3       
        //   235: aload_1        
        //   236: iconst_4       
        //   237: aaload         
        //   238: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //   241: astore_1       
        //   242: goto            86
        //   245: new             Ljava/lang/String;
        //   248: dup            
        //   249: aload           10
        //   251: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.UTF_8:Ljava/nio/charset/Charset;
        //   254: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   257: astore          7
        //   259: new             Lcom/netflix/android/org/json/JSONObject;
        //   262: dup            
        //   263: aload           7
        //   265: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   268: astore          12
        //   270: aload           12
        //   272: ldc             "alg"
        //   274: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   277: astore          11
        //   279: aload           11
        //   281: invokestatic    com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Algorithm.fromString:(Ljava/lang/String;)Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Algorithm;
        //   284: astore          10
        //   286: aload           12
        //   288: ldc             "enc"
        //   290: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   293: astore          12
        //   295: aload           12
        //   297: invokestatic    com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   300: astore          11
        //   302: aload_0        
        //   303: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.algo:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Algorithm;
        //   306: aload           10
        //   308: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Algorithm.equals:(Ljava/lang/Object;)Z
        //   311: ifeq            326
        //   314: aload_0        
        //   315: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.enc:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   318: aload           11
        //   320: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.equals:(Ljava/lang/Object;)Z
        //   323: ifne            384
        //   326: new             Lcom/netflix/msl/MslCryptoException;
        //   329: dup            
        //   330: getstatic       com/netflix/msl/MslError.JWE_ALGORITHM_MISMATCH:Lcom/netflix/msl/MslError;
        //   333: aload           7
        //   335: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   338: athrow         
        //   339: astore_1       
        //   340: new             Lcom/netflix/msl/MslCryptoException;
        //   343: dup            
        //   344: getstatic       com/netflix/msl/MslError.JWE_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   347: aload           11
        //   349: aload_1        
        //   350: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   353: athrow         
        //   354: astore_1       
        //   355: new             Lcom/netflix/msl/MslCryptoException;
        //   358: dup            
        //   359: getstatic       com/netflix/msl/MslError.JWE_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   362: aload           7
        //   364: aload_1        
        //   365: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   368: athrow         
        //   369: astore_1       
        //   370: new             Lcom/netflix/msl/MslCryptoException;
        //   373: dup            
        //   374: getstatic       com/netflix/msl/MslError.JWE_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   377: aload           12
        //   379: aload_1        
        //   380: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   383: athrow         
        //   384: new             Lorg/bouncycastle/crypto/params/KeyParameter;
        //   387: dup            
        //   388: aload_0        
        //   389: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.cekCryptoContext:Lcom/netflix/msl/crypto/ICryptoContext;
        //   392: aload           9
        //   394: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   399: invokespecial   org/bouncycastle/crypto/params/KeyParameter.<init>:([B)V
        //   402: astore          7
        //   404: new             Ljava/lang/StringBuilder;
        //   407: dup            
        //   408: invokespecial   java/lang/StringBuilder.<init>:()V
        //   411: aload           6
        //   413: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: ldc             "."
        //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: aload           5
        //   423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   426: ldc             "."
        //   428: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   431: aload           4
        //   433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   436: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   439: astore          5
        //   441: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.A128GCM:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   444: aload           11
        //   446: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.equals:(Ljava/lang/Object;)Z
        //   449: ifeq            513
        //   452: bipush          16
        //   454: istore_2       
        //   455: aload           7
        //   457: invokevirtual   org/bouncycastle/crypto/params/KeyParameter.getKey:()[B
        //   460: arraylength    
        //   461: iload_2        
        //   462: if_icmpeq       546
        //   465: new             Lcom/netflix/msl/MslCryptoException;
        //   468: dup            
        //   469: getstatic       com/netflix/msl/MslError.INVALID_SYMMETRIC_KEY:Lcom/netflix/msl/MslError;
        //   472: new             Ljava/lang/StringBuilder;
        //   475: dup            
        //   476: invokespecial   java/lang/StringBuilder.<init>:()V
        //   479: ldc             "content encryption key length: "
        //   481: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   484: aload           7
        //   486: invokevirtual   org/bouncycastle/crypto/params/KeyParameter.getKey:()[B
        //   489: arraylength    
        //   490: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   493: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   496: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   499: athrow         
        //   500: astore_1       
        //   501: new             Lcom/netflix/msl/MslCryptoException;
        //   504: dup            
        //   505: getstatic       com/netflix/msl/MslError.INVALID_SYMMETRIC_KEY:Lcom/netflix/msl/MslError;
        //   508: aload_1        
        //   509: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   512: athrow         
        //   513: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.A256GCM:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   516: aload           11
        //   518: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.equals:(Ljava/lang/Object;)Z
        //   521: ifeq            530
        //   524: bipush          32
        //   526: istore_2       
        //   527: goto            455
        //   530: new             Lcom/netflix/msl/MslCryptoException;
        //   533: dup            
        //   534: getstatic       com/netflix/msl/MslError.UNSUPPORTED_JWE_ALGORITHM:Lcom/netflix/msl/MslError;
        //   537: aload           11
        //   539: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.name:()Ljava/lang/String;
        //   542: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   545: athrow         
        //   546: aload_1        
        //   547: arraylength    
        //   548: bipush          16
        //   550: if_icmpeq       584
        //   553: new             Lcom/netflix/msl/MslCryptoException;
        //   556: dup            
        //   557: getstatic       com/netflix/msl/MslError.INVALID_ALGORITHM_PARAMS:Lcom/netflix/msl/MslError;
        //   560: new             Ljava/lang/StringBuilder;
        //   563: dup            
        //   564: invokespecial   java/lang/StringBuilder.<init>:()V
        //   567: ldc             "authentication tag length: "
        //   569: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   572: aload_1        
        //   573: arraylength    
        //   574: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   577: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   580: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   583: athrow         
        //   584: new             Lorg/bouncycastle/crypto/modes/GCMBlockCipher;
        //   587: dup            
        //   588: new             Lorg/bouncycastle/crypto/engines/AESEngine;
        //   591: dup            
        //   592: invokespecial   org/bouncycastle/crypto/engines/AESEngine.<init>:()V
        //   595: invokespecial   org/bouncycastle/crypto/modes/GCMBlockCipher.<init>:(Lorg/bouncycastle/crypto/BlockCipher;)V
        //   598: astore          4
        //   600: aload           4
        //   602: iconst_0       
        //   603: new             Lorg/bouncycastle/crypto/params/AEADParameters;
        //   606: dup            
        //   607: aload           7
        //   609: sipush          128
        //   612: aload           8
        //   614: aload           5
        //   616: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.UTF_8:Ljava/nio/charset/Charset;
        //   619: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //   622: invokespecial   org/bouncycastle/crypto/params/AEADParameters.<init>:(Lorg/bouncycastle/crypto/params/KeyParameter;I[B[B)V
        //   625: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.init:(ZLorg/bouncycastle/crypto/CipherParameters;)V
        //   628: aload_3        
        //   629: aload_3        
        //   630: arraylength    
        //   631: aload_1        
        //   632: arraylength    
        //   633: iadd           
        //   634: invokestatic    java/util/Arrays.copyOf:([BI)[B
        //   637: astore          5
        //   639: aload_1        
        //   640: iconst_0       
        //   641: aload           5
        //   643: aload_3        
        //   644: arraylength    
        //   645: aload_1        
        //   646: arraylength    
        //   647: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   650: aload           4
        //   652: aload           5
        //   654: arraylength    
        //   655: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.getOutputSize:(I)I
        //   658: newarray        B
        //   660: astore_1       
        //   661: aload           4
        //   663: aload_1        
        //   664: aload           4
        //   666: aload           5
        //   668: iconst_0       
        //   669: aload           5
        //   671: arraylength    
        //   672: aload_1        
        //   673: iconst_0       
        //   674: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.processBytes:([BII[BI)I
        //   677: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.doFinal:([BI)I
        //   680: pop            
        //   681: aload_1        
        //   682: areturn        
        //   683: astore_1       
        //   684: new             Lcom/netflix/msl/MslCryptoException;
        //   687: dup            
        //   688: getstatic       com/netflix/msl/MslError.UNWRAP_ERROR:Lcom/netflix/msl/MslError;
        //   691: aload_1        
        //   692: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   695: athrow         
        //   696: astore_1       
        //   697: new             Lcom/netflix/msl/MslCryptoException;
        //   700: dup            
        //   701: getstatic       com/netflix/msl/MslError.UNWRAP_ERROR:Lcom/netflix/msl/MslError;
        //   704: aload_1        
        //   705: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   708: athrow         
        //   709: astore_1       
        //   710: new             Lcom/netflix/msl/MslCryptoException;
        //   713: dup            
        //   714: getstatic       com/netflix/msl/MslError.UNWRAP_ERROR:Lcom/netflix/msl/MslError;
        //   717: aload_1        
        //   718: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   721: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                
        //  -----  -----  -----  -----  ----------------------------------------------------
        //  21     86     171    186    Lcom/netflix/android/org/json/JSONException;
        //  259    279    354    369    Lcom/netflix/android/org/json/JSONException;
        //  279    286    339    354    Ljava/lang/IllegalArgumentException;
        //  279    286    354    369    Lcom/netflix/android/org/json/JSONException;
        //  286    295    354    369    Lcom/netflix/android/org/json/JSONException;
        //  295    302    369    384    Ljava/lang/IllegalArgumentException;
        //  295    302    354    369    Lcom/netflix/android/org/json/JSONException;
        //  340    354    354    369    Lcom/netflix/android/org/json/JSONException;
        //  370    384    354    369    Lcom/netflix/android/org/json/JSONException;
        //  384    404    500    513    Ljava/lang/ArrayIndexOutOfBoundsException;
        //  628    681    683    696    Ljava/lang/IllegalStateException;
        //  628    681    696    709    Lorg/bouncycastle/crypto/InvalidCipherTextException;
        //  628    681    709    722    Ljava/lang/ArrayIndexOutOfBoundsException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0326:
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
    public boolean verify(final byte[] array, final byte[] array2) {
        throw new MslCryptoException(MslError.VERIFY_NOT_SUPPORTED);
    }
    
    @Override
    public byte[] wrap(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/netflix/android/org/json/JSONStringer;
        //     3: dup            
        //     4: invokespecial   com/netflix/android/org/json/JSONStringer.<init>:()V
        //     7: invokevirtual   com/netflix/android/org/json/JSONStringer.object:()Lcom/netflix/android/org/json/JSONWriter;
        //    10: ldc             "alg"
        //    12: invokevirtual   com/netflix/android/org/json/JSONWriter.key:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONWriter;
        //    15: aload_0        
        //    16: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.algo:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Algorithm;
        //    19: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Algorithm.toString:()Ljava/lang/String;
        //    22: invokevirtual   com/netflix/android/org/json/JSONWriter.value:(Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONWriter;
        //    25: ldc             "enc"
        //    27: invokevirtual   com/netflix/android/org/json/JSONWriter.key:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONWriter;
        //    30: aload_0        
        //    31: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.enc:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //    34: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.name:()Ljava/lang/String;
        //    37: invokevirtual   com/netflix/android/org/json/JSONWriter.value:(Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONWriter;
        //    40: invokevirtual   com/netflix/android/org/json/JSONWriter.endObject:()Lcom/netflix/android/org/json/JSONWriter;
        //    43: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //    46: astore_3       
        //    47: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.A128GCM:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //    50: aload_0        
        //    51: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.enc:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //    54: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.equals:(Ljava/lang/Object;)Z
        //    57: ifeq            363
        //    60: bipush          16
        //    62: istore_2       
        //    63: aload_0        
        //    64: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.ctx:Lcom/netflix/msl/util/MslContext;
        //    67: invokevirtual   com/netflix/msl/util/MslContext.getRandom:()Ljava/util/Random;
        //    70: astore          4
        //    72: iload_2        
        //    73: newarray        B
        //    75: astore          5
        //    77: aload           4
        //    79: aload           5
        //    81: invokevirtual   java/util/Random.nextBytes:([B)V
        //    84: new             Lorg/bouncycastle/crypto/params/KeyParameter;
        //    87: dup            
        //    88: aload           5
        //    90: invokespecial   org/bouncycastle/crypto/params/KeyParameter.<init>:([B)V
        //    93: astore          7
        //    95: bipush          12
        //    97: newarray        B
        //    99: astore          8
        //   101: aload           4
        //   103: aload           8
        //   105: invokevirtual   java/util/Random.nextBytes:([B)V
        //   108: aload_0        
        //   109: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.cekCryptoContext:Lcom/netflix/msl/crypto/ICryptoContext;
        //   112: aload           7
        //   114: invokevirtual   org/bouncycastle/crypto/params/KeyParameter.getKey:()[B
        //   117: invokeinterface com/netflix/msl/crypto/ICryptoContext.encrypt:([B)[B
        //   122: astore          4
        //   124: aload_3        
        //   125: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.UTF_8:Ljava/nio/charset/Charset;
        //   128: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //   131: invokestatic    com/netflix/msl/util/JsonUtils.b64urlEncode:([B)Ljava/lang/String;
        //   134: astore_3       
        //   135: aload           4
        //   137: invokestatic    com/netflix/msl/util/JsonUtils.b64urlEncode:([B)Ljava/lang/String;
        //   140: astore          5
        //   142: aload           8
        //   144: invokestatic    com/netflix/msl/util/JsonUtils.b64urlEncode:([B)Ljava/lang/String;
        //   147: astore          4
        //   149: new             Ljava/lang/StringBuilder;
        //   152: dup            
        //   153: invokespecial   java/lang/StringBuilder.<init>:()V
        //   156: aload_3        
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: ldc             "."
        //   162: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: aload           5
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: ldc             "."
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: aload           4
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   183: astore          6
        //   185: new             Lorg/bouncycastle/crypto/modes/GCMBlockCipher;
        //   188: dup            
        //   189: new             Lorg/bouncycastle/crypto/engines/AESEngine;
        //   192: dup            
        //   193: invokespecial   org/bouncycastle/crypto/engines/AESEngine.<init>:()V
        //   196: invokespecial   org/bouncycastle/crypto/modes/GCMBlockCipher.<init>:(Lorg/bouncycastle/crypto/BlockCipher;)V
        //   199: astore          9
        //   201: aload           9
        //   203: iconst_1       
        //   204: new             Lorg/bouncycastle/crypto/params/AEADParameters;
        //   207: dup            
        //   208: aload           7
        //   210: sipush          128
        //   213: aload           8
        //   215: aload           6
        //   217: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.UTF_8:Ljava/nio/charset/Charset;
        //   220: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //   223: invokespecial   org/bouncycastle/crypto/params/AEADParameters.<init>:(Lorg/bouncycastle/crypto/params/KeyParameter;I[B[B)V
        //   226: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.init:(ZLorg/bouncycastle/crypto/CipherParameters;)V
        //   229: aload           9
        //   231: aload_1        
        //   232: arraylength    
        //   233: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.getOutputSize:(I)I
        //   236: newarray        B
        //   238: astore          7
        //   240: aload           9
        //   242: aload           7
        //   244: aload           9
        //   246: aload_1        
        //   247: iconst_0       
        //   248: aload_1        
        //   249: arraylength    
        //   250: aload           7
        //   252: iconst_0       
        //   253: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.processBytes:([BII[BI)I
        //   256: invokevirtual   org/bouncycastle/crypto/modes/GCMBlockCipher.doFinal:([BI)I
        //   259: pop            
        //   260: aload           7
        //   262: iconst_0       
        //   263: aload           7
        //   265: arraylength    
        //   266: bipush          16
        //   268: isub           
        //   269: invokestatic    java/util/Arrays.copyOfRange:([BII)[B
        //   272: astore_1       
        //   273: aload           7
        //   275: aload_1        
        //   276: arraylength    
        //   277: aload           7
        //   279: arraylength    
        //   280: invokestatic    java/util/Arrays.copyOfRange:([BII)[B
        //   283: astore          7
        //   285: aload_1        
        //   286: invokestatic    com/netflix/msl/util/JsonUtils.b64urlEncode:([B)Ljava/lang/String;
        //   289: astore_1       
        //   290: aload           7
        //   292: invokestatic    com/netflix/msl/util/JsonUtils.b64urlEncode:([B)Ljava/lang/String;
        //   295: astore          7
        //   297: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$1.$SwitchMap$com$netflix$msl$crypto$JsonWebEncryptionCryptoContext$Format:[I
        //   300: aload_0        
        //   301: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.format:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format;
        //   304: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format.ordinal:()I
        //   307: iaload         
        //   308: tableswitch {
        //                2: 426
        //                3: 467
        //          default: 332
        //        }
        //   332: new             Lcom/netflix/msl/MslCryptoException;
        //   335: dup            
        //   336: getstatic       com/netflix/msl/MslError.UNSUPPORTED_JWE_SERIALIZATION:Lcom/netflix/msl/MslError;
        //   339: aload_0        
        //   340: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.format:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format;
        //   343: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format.name:()Ljava/lang/String;
        //   346: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   349: athrow         
        //   350: astore_1       
        //   351: new             Lcom/netflix/msl/MslCryptoException;
        //   354: dup            
        //   355: getstatic       com/netflix/msl/MslError.JWE_ENCODE_ERROR:Lcom/netflix/msl/MslError;
        //   358: aload_1        
        //   359: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   362: athrow         
        //   363: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.A256GCM:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   366: aload_0        
        //   367: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.enc:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   370: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.equals:(Ljava/lang/Object;)Z
        //   373: ifeq            382
        //   376: bipush          32
        //   378: istore_2       
        //   379: goto            63
        //   382: new             Lcom/netflix/msl/MslCryptoException;
        //   385: dup            
        //   386: getstatic       com/netflix/msl/MslError.UNSUPPORTED_JWE_ALGORITHM:Lcom/netflix/msl/MslError;
        //   389: aload_0        
        //   390: getfield        com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.enc:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   393: invokevirtual   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.name:()Ljava/lang/String;
        //   396: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   399: athrow         
        //   400: astore_1       
        //   401: new             Lcom/netflix/msl/MslCryptoException;
        //   404: dup            
        //   405: getstatic       com/netflix/msl/MslError.WRAP_ERROR:Lcom/netflix/msl/MslError;
        //   408: aload_1        
        //   409: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   412: athrow         
        //   413: astore_1       
        //   414: new             Lcom/netflix/msl/MslInternalException;
        //   417: dup            
        //   418: ldc_w           "Invalid ciphertext not expected when encrypting."
        //   421: aload_1        
        //   422: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   425: athrow         
        //   426: new             Ljava/lang/StringBuilder;
        //   429: dup            
        //   430: invokespecial   java/lang/StringBuilder.<init>:()V
        //   433: aload           6
        //   435: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   438: ldc             "."
        //   440: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   443: aload_1        
        //   444: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   447: ldc             "."
        //   449: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   452: aload           7
        //   454: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   457: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   460: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.UTF_8:Ljava/nio/charset/Charset;
        //   463: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //   466: areturn        
        //   467: new             Lcom/netflix/android/org/json/JSONArray;
        //   470: dup            
        //   471: invokespecial   com/netflix/android/org/json/JSONArray.<init>:()V
        //   474: astore          6
        //   476: new             Lcom/netflix/android/org/json/JSONObject;
        //   479: dup            
        //   480: invokespecial   com/netflix/android/org/json/JSONObject.<init>:()V
        //   483: astore          8
        //   485: aload           8
        //   487: ldc             "header"
        //   489: aload_3        
        //   490: invokevirtual   com/netflix/android/org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONObject;
        //   493: pop            
        //   494: aload           8
        //   496: ldc             "encrypted_key"
        //   498: aload           5
        //   500: invokevirtual   com/netflix/android/org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONObject;
        //   503: pop            
        //   504: aload           8
        //   506: ldc             "integrity_value"
        //   508: aload           7
        //   510: invokevirtual   com/netflix/android/org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONObject;
        //   513: pop            
        //   514: aload           6
        //   516: aload           8
        //   518: invokevirtual   com/netflix/android/org/json/JSONArray.put:(Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONArray;
        //   521: pop            
        //   522: new             Lcom/netflix/android/org/json/JSONObject;
        //   525: dup            
        //   526: invokespecial   com/netflix/android/org/json/JSONObject.<init>:()V
        //   529: astore_3       
        //   530: aload_3        
        //   531: ldc             "recipients"
        //   533: aload           6
        //   535: invokevirtual   com/netflix/android/org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONObject;
        //   538: pop            
        //   539: aload_3        
        //   540: ldc             "initialization_vector"
        //   542: aload           4
        //   544: invokevirtual   com/netflix/android/org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONObject;
        //   547: pop            
        //   548: aload_3        
        //   549: ldc             "ciphertext"
        //   551: aload_1        
        //   552: invokevirtual   com/netflix/android/org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lcom/netflix/android/org/json/JSONObject;
        //   555: pop            
        //   556: aload_3        
        //   557: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   560: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.UTF_8:Ljava/nio/charset/Charset;
        //   563: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //   566: astore_1       
        //   567: aload_1        
        //   568: areturn        
        //   569: astore_1       
        //   570: new             Lcom/netflix/msl/MslCryptoException;
        //   573: dup            
        //   574: getstatic       com/netflix/msl/MslError.JWE_ENCODE_ERROR:Lcom/netflix/msl/MslError;
        //   577: aload_1        
        //   578: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   581: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                
        //  -----  -----  -----  -----  ----------------------------------------------------
        //  0      47     350    363    Lcom/netflix/android/org/json/JSONException;
        //  229    260    400    413    Ljava/lang/IllegalStateException;
        //  229    260    413    426    Lorg/bouncycastle/crypto/InvalidCipherTextException;
        //  467    567    569    582    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0332:
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
