// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.Iterator;
import com.netflix.msl.util.JsonUtils;
import com.netflix.android.org.json.JSONException;
import com.netflix.android.org.json.JSONArray;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Collections;
import java.security.PrivateKey;
import java.security.PublicKey;
import com.netflix.msl.MslInternalException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import com.netflix.android.org.json.JSONObject;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.util.Set;
import com.netflix.android.org.json.JSONString;

public class JsonWebKey implements JSONString
{
    private static final String KEY_ALGORITHM = "alg";
    private static final String KEY_EXTRACTABLE = "extractable";
    private static final String KEY_KEY = "k";
    private static final String KEY_KEY_ID = "kid";
    private static final String KEY_KEY_OPS = "key_ops";
    private static final String KEY_MODULUS = "n";
    private static final String KEY_PRIVATE_EXPONENT = "d";
    private static final String KEY_PUBLIC_EXPONENT = "e";
    private static final String KEY_TYPE = "kty";
    private static final String KEY_USAGE = "use";
    private final JsonWebKey$Algorithm algo;
    private final boolean extractable;
    private final String id;
    private final byte[] key;
    private final Set<JsonWebKey$KeyOp> keyOps;
    private final KeyPair keyPair;
    private final SecretKey secretKey;
    private final JsonWebKey$Type type;
    private final JsonWebKey$Usage usage;
    
    public JsonWebKey(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_3       
        //     2: aconst_null    
        //     3: astore          8
        //     5: aload_0        
        //     6: invokespecial   java/lang/Object.<init>:()V
        //     9: aload_1        
        //    10: ldc             "kty"
        //    12: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    15: astore          9
        //    17: aload_1        
        //    18: ldc             "use"
        //    20: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    23: ifeq            770
        //    26: aload_1        
        //    27: ldc             "use"
        //    29: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    32: astore          4
        //    34: aload_1        
        //    35: ldc             "key_ops"
        //    37: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    40: ifeq            254
        //    43: new             Ljava/util/HashSet;
        //    46: dup            
        //    47: invokespecial   java/util/HashSet.<init>:()V
        //    50: astore          6
        //    52: aload_1        
        //    53: ldc             "key_ops"
        //    55: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONArray;
        //    58: astore          5
        //    60: iconst_0       
        //    61: istore_2       
        //    62: iload_2        
        //    63: aload           5
        //    65: invokevirtual   com/netflix/android/org/json/JSONArray.length:()I
        //    68: if_icmpge       776
        //    71: aload           6
        //    73: aload           5
        //    75: iload_2        
        //    76: invokevirtual   com/netflix/android/org/json/JSONArray.getString:(I)Ljava/lang/String;
        //    79: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    84: pop            
        //    85: iload_2        
        //    86: iconst_1       
        //    87: iadd           
        //    88: istore_2       
        //    89: goto            62
        //    92: aload_1        
        //    93: ldc             "alg"
        //    95: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    98: ifeq            260
        //   101: aload_1        
        //   102: ldc             "alg"
        //   104: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   107: astore          5
        //   109: aload_1        
        //   110: ldc             "extractable"
        //   112: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   115: ifeq            125
        //   118: aload_1        
        //   119: ldc             "extractable"
        //   121: invokevirtual   com/netflix/android/org/json/JSONObject.getBoolean:(Ljava/lang/String;)Z
        //   124: istore_3       
        //   125: aload_0        
        //   126: iload_3        
        //   127: putfield        com/netflix/msl/crypto/JsonWebKey.extractable:Z
        //   130: aload_1        
        //   131: ldc             "kid"
        //   133: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   136: ifeq            266
        //   139: aload_1        
        //   140: ldc             "kid"
        //   142: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   145: astore          7
        //   147: aload_0        
        //   148: aload           7
        //   150: putfield        com/netflix/msl/crypto/JsonWebKey.id:Ljava/lang/String;
        //   153: aload_0        
        //   154: aload           9
        //   156: invokestatic    com/netflix/msl/crypto/JsonWebKey$Type.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/crypto/JsonWebKey$Type;
        //   159: putfield        com/netflix/msl/crypto/JsonWebKey.type:Lcom/netflix/msl/crypto/JsonWebKey$Type;
        //   162: aload           4
        //   164: ifnull          324
        //   167: aload           4
        //   169: invokestatic    com/netflix/msl/crypto/JsonWebKey$Usage.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/crypto/JsonWebKey$Usage;
        //   172: astore          7
        //   174: aload_0        
        //   175: aload           7
        //   177: putfield        com/netflix/msl/crypto/JsonWebKey.usage:Lcom/netflix/msl/crypto/JsonWebKey$Usage;
        //   180: aload           6
        //   182: ifnull          436
        //   185: ldc             Lcom/netflix/msl/crypto/JsonWebKey$KeyOp;.class
        //   187: invokestatic    java/util/EnumSet.noneOf:(Ljava/lang/Class;)Ljava/util/EnumSet;
        //   190: astore          7
        //   192: aload           6
        //   194: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   199: astore          6
        //   201: aload           6
        //   203: invokeinterface java/util/Iterator.hasNext:()Z
        //   208: ifeq            345
        //   211: aload           6
        //   213: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   218: checkcast       Ljava/lang/String;
        //   221: astore          9
        //   223: aload           7
        //   225: aload           9
        //   227: invokestatic    com/netflix/msl/crypto/JsonWebKey$KeyOp.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/crypto/JsonWebKey$KeyOp;
        //   230: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   235: pop            
        //   236: goto            201
        //   239: astore_1       
        //   240: new             Lcom/netflix/msl/MslCryptoException;
        //   243: dup            
        //   244: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_JWK_KEYOP:Lcom/netflix/msl/MslError;
        //   247: aload           4
        //   249: aload_1        
        //   250: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   253: athrow         
        //   254: aconst_null    
        //   255: astore          6
        //   257: goto            92
        //   260: aconst_null    
        //   261: astore          5
        //   263: goto            109
        //   266: aconst_null    
        //   267: astore          7
        //   269: goto            147
        //   272: astore          4
        //   274: new             Lcom/netflix/msl/MslEncodingException;
        //   277: dup            
        //   278: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   281: new             Ljava/lang/StringBuilder;
        //   284: dup            
        //   285: invokespecial   java/lang/StringBuilder.<init>:()V
        //   288: ldc             "jwk "
        //   290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   293: aload_1        
        //   294: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   297: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   300: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   303: aload           4
        //   305: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   308: athrow         
        //   309: astore_1       
        //   310: new             Lcom/netflix/msl/MslCryptoException;
        //   313: dup            
        //   314: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_JWK_TYPE:Lcom/netflix/msl/MslError;
        //   317: aload           9
        //   319: aload_1        
        //   320: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   323: athrow         
        //   324: aconst_null    
        //   325: astore          7
        //   327: goto            174
        //   330: astore_1       
        //   331: new             Lcom/netflix/msl/MslCryptoException;
        //   334: dup            
        //   335: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_JWK_USAGE:Lcom/netflix/msl/MslError;
        //   338: aload           4
        //   340: aload_1        
        //   341: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   344: athrow         
        //   345: aload_0        
        //   346: aload           7
        //   348: invokestatic    java/util/Collections.unmodifiableSet:(Ljava/util/Set;)Ljava/util/Set;
        //   351: putfield        com/netflix/msl/crypto/JsonWebKey.keyOps:Ljava/util/Set;
        //   354: aload           5
        //   356: ifnull          444
        //   359: aload           5
        //   361: invokestatic    com/netflix/msl/crypto/JsonWebKey$Algorithm.fromString:(Ljava/lang/String;)Lcom/netflix/msl/crypto/JsonWebKey$Algorithm;
        //   364: astore          4
        //   366: aload_0        
        //   367: aload           4
        //   369: putfield        com/netflix/msl/crypto/JsonWebKey.algo:Lcom/netflix/msl/crypto/JsonWebKey$Algorithm;
        //   372: aload_0        
        //   373: getfield        com/netflix/msl/crypto/JsonWebKey.type:Lcom/netflix/msl/crypto/JsonWebKey$Type;
        //   376: getstatic       com/netflix/msl/crypto/JsonWebKey$Type.oct:Lcom/netflix/msl/crypto/JsonWebKey$Type;
        //   379: if_acmpne       505
        //   382: aload_0        
        //   383: aload_1        
        //   384: ldc             "k"
        //   386: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   389: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //   392: putfield        com/netflix/msl/crypto/JsonWebKey.key:[B
        //   395: aload_0        
        //   396: getfield        com/netflix/msl/crypto/JsonWebKey.key:[B
        //   399: ifnull          410
        //   402: aload_0        
        //   403: getfield        com/netflix/msl/crypto/JsonWebKey.key:[B
        //   406: arraylength    
        //   407: ifne            465
        //   410: new             Lcom/netflix/msl/MslCryptoException;
        //   413: dup            
        //   414: getstatic       com/netflix/msl/MslError.INVALID_JWK_KEYDATA:Lcom/netflix/msl/MslError;
        //   417: ldc             "symmetric key is empty"
        //   419: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   422: athrow         
        //   423: astore_1       
        //   424: new             Lcom/netflix/msl/MslEncodingException;
        //   427: dup            
        //   428: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   431: aload_1        
        //   432: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   435: athrow         
        //   436: aload_0        
        //   437: aconst_null    
        //   438: putfield        com/netflix/msl/crypto/JsonWebKey.keyOps:Ljava/util/Set;
        //   441: goto            354
        //   444: aconst_null    
        //   445: astore          4
        //   447: goto            366
        //   450: astore_1       
        //   451: new             Lcom/netflix/msl/MslCryptoException;
        //   454: dup            
        //   455: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_JWK_ALGORITHM:Lcom/netflix/msl/MslError;
        //   458: aload           5
        //   460: aload_1        
        //   461: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   464: athrow         
        //   465: aload           8
        //   467: astore_1       
        //   468: aload_0        
        //   469: getfield        com/netflix/msl/crypto/JsonWebKey.algo:Lcom/netflix/msl/crypto/JsonWebKey$Algorithm;
        //   472: ifnull          494
        //   475: new             Ljavax/crypto/spec/SecretKeySpec;
        //   478: dup            
        //   479: aload_0        
        //   480: getfield        com/netflix/msl/crypto/JsonWebKey.key:[B
        //   483: aload_0        
        //   484: getfield        com/netflix/msl/crypto/JsonWebKey.algo:Lcom/netflix/msl/crypto/JsonWebKey$Algorithm;
        //   487: invokevirtual   com/netflix/msl/crypto/JsonWebKey$Algorithm.getJcaAlgorithmName:()Ljava/lang/String;
        //   490: invokespecial   javax/crypto/spec/SecretKeySpec.<init>:([BLjava/lang/String;)V
        //   493: astore_1       
        //   494: aload_0        
        //   495: aload_1        
        //   496: putfield        com/netflix/msl/crypto/JsonWebKey.secretKey:Ljavax/crypto/SecretKey;
        //   499: aload_0        
        //   500: aconst_null    
        //   501: putfield        com/netflix/msl/crypto/JsonWebKey.keyPair:Ljava/security/KeyPair;
        //   504: return         
        //   505: aload_0        
        //   506: aconst_null    
        //   507: putfield        com/netflix/msl/crypto/JsonWebKey.key:[B
        //   510: ldc             "RSA"
        //   512: invokestatic    com/netflix/msl/crypto/CryptoCache.getKeyFactory:(Ljava/lang/String;)Ljava/security/KeyFactory;
        //   515: astore          5
        //   517: aload_1        
        //   518: ldc             "n"
        //   520: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   523: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //   526: astore          4
        //   528: aload           4
        //   530: ifnull          539
        //   533: aload           4
        //   535: arraylength    
        //   536: ifne            565
        //   539: new             Lcom/netflix/msl/MslCryptoException;
        //   542: dup            
        //   543: getstatic       com/netflix/msl/MslError.INVALID_JWK_KEYDATA:Lcom/netflix/msl/MslError;
        //   546: ldc             "modulus is empty"
        //   548: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   551: athrow         
        //   552: astore_1       
        //   553: new             Lcom/netflix/msl/MslCryptoException;
        //   556: dup            
        //   557: getstatic       com/netflix/msl/MslError.UNSUPPORTED_JWK_ALGORITHM:Lcom/netflix/msl/MslError;
        //   560: aload_1        
        //   561: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   564: athrow         
        //   565: new             Ljava/math/BigInteger;
        //   568: dup            
        //   569: iconst_1       
        //   570: aload           4
        //   572: invokespecial   java/math/BigInteger.<init>:(I[B)V
        //   575: astore          6
        //   577: aload_1        
        //   578: ldc             "e"
        //   580: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   583: ifeq            779
        //   586: aload_1        
        //   587: ldc             "e"
        //   589: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   592: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //   595: astore          4
        //   597: aload           4
        //   599: ifnull          608
        //   602: aload           4
        //   604: arraylength    
        //   605: ifne            635
        //   608: new             Lcom/netflix/msl/MslCryptoException;
        //   611: dup            
        //   612: getstatic       com/netflix/msl/MslError.INVALID_JWK_KEYDATA:Lcom/netflix/msl/MslError;
        //   615: ldc_w           "public exponent is empty"
        //   618: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   621: athrow         
        //   622: astore_1       
        //   623: new             Lcom/netflix/msl/MslCryptoException;
        //   626: dup            
        //   627: getstatic       com/netflix/msl/MslError.INVALID_JWK_KEYDATA:Lcom/netflix/msl/MslError;
        //   630: aload_1        
        //   631: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   634: athrow         
        //   635: aload           5
        //   637: new             Ljava/security/spec/RSAPublicKeySpec;
        //   640: dup            
        //   641: aload           6
        //   643: new             Ljava/math/BigInteger;
        //   646: dup            
        //   647: iconst_1       
        //   648: aload           4
        //   650: invokespecial   java/math/BigInteger.<init>:(I[B)V
        //   653: invokespecial   java/security/spec/RSAPublicKeySpec.<init>:(Ljava/math/BigInteger;Ljava/math/BigInteger;)V
        //   656: invokevirtual   java/security/KeyFactory.generatePublic:(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;
        //   659: astore          4
        //   661: aload_1        
        //   662: ldc             "d"
        //   664: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   667: ifeq            785
        //   670: aload_1        
        //   671: ldc             "d"
        //   673: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   676: invokestatic    com/netflix/msl/util/JsonUtils.b64urlDecode:(Ljava/lang/String;)[B
        //   679: astore_1       
        //   680: aload_1        
        //   681: ifnull          689
        //   684: aload_1        
        //   685: arraylength    
        //   686: ifne            703
        //   689: new             Lcom/netflix/msl/MslCryptoException;
        //   692: dup            
        //   693: getstatic       com/netflix/msl/MslError.INVALID_JWK_KEYDATA:Lcom/netflix/msl/MslError;
        //   696: ldc_w           "private exponent is empty"
        //   699: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   702: athrow         
        //   703: aload           5
        //   705: new             Ljava/security/spec/RSAPrivateKeySpec;
        //   708: dup            
        //   709: aload           6
        //   711: new             Ljava/math/BigInteger;
        //   714: dup            
        //   715: iconst_1       
        //   716: aload_1        
        //   717: invokespecial   java/math/BigInteger.<init>:(I[B)V
        //   720: invokespecial   java/security/spec/RSAPrivateKeySpec.<init>:(Ljava/math/BigInteger;Ljava/math/BigInteger;)V
        //   723: invokevirtual   java/security/KeyFactory.generatePrivate:(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;
        //   726: astore_1       
        //   727: aload           4
        //   729: ifnonnull       750
        //   732: aload_1        
        //   733: ifnonnull       750
        //   736: new             Lcom/netflix/msl/MslEncodingException;
        //   739: dup            
        //   740: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   743: ldc_w           "no public or private key"
        //   746: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   749: athrow         
        //   750: aload_0        
        //   751: new             Ljava/security/KeyPair;
        //   754: dup            
        //   755: aload           4
        //   757: aload_1        
        //   758: invokespecial   java/security/KeyPair.<init>:(Ljava/security/PublicKey;Ljava/security/PrivateKey;)V
        //   761: putfield        com/netflix/msl/crypto/JsonWebKey.keyPair:Ljava/security/KeyPair;
        //   764: aload_0        
        //   765: aconst_null    
        //   766: putfield        com/netflix/msl/crypto/JsonWebKey.secretKey:Ljavax/crypto/SecretKey;
        //   769: return         
        //   770: aconst_null    
        //   771: astore          4
        //   773: goto            34
        //   776: goto            92
        //   779: aconst_null    
        //   780: astore          4
        //   782: goto            661
        //   785: aconst_null    
        //   786: astore_1       
        //   787: goto            727
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  9      34     272    309    Lcom/netflix/android/org/json/JSONException;
        //  34     60     272    309    Lcom/netflix/android/org/json/JSONException;
        //  62     85     272    309    Lcom/netflix/android/org/json/JSONException;
        //  92     109    272    309    Lcom/netflix/android/org/json/JSONException;
        //  109    125    272    309    Lcom/netflix/android/org/json/JSONException;
        //  125    147    272    309    Lcom/netflix/android/org/json/JSONException;
        //  147    153    272    309    Lcom/netflix/android/org/json/JSONException;
        //  153    162    309    324    Ljava/lang/IllegalArgumentException;
        //  167    174    330    345    Ljava/lang/IllegalArgumentException;
        //  174    180    330    345    Ljava/lang/IllegalArgumentException;
        //  223    236    239    254    Ljava/lang/IllegalArgumentException;
        //  359    366    450    465    Ljava/lang/IllegalArgumentException;
        //  366    372    450    465    Ljava/lang/IllegalArgumentException;
        //  372    410    423    436    Lcom/netflix/android/org/json/JSONException;
        //  372    410    552    565    Ljava/security/NoSuchAlgorithmException;
        //  372    410    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  410    423    423    436    Lcom/netflix/android/org/json/JSONException;
        //  410    423    552    565    Ljava/security/NoSuchAlgorithmException;
        //  410    423    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  468    494    423    436    Lcom/netflix/android/org/json/JSONException;
        //  468    494    552    565    Ljava/security/NoSuchAlgorithmException;
        //  468    494    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  494    504    423    436    Lcom/netflix/android/org/json/JSONException;
        //  494    504    552    565    Ljava/security/NoSuchAlgorithmException;
        //  494    504    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  505    528    423    436    Lcom/netflix/android/org/json/JSONException;
        //  505    528    552    565    Ljava/security/NoSuchAlgorithmException;
        //  505    528    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  533    539    423    436    Lcom/netflix/android/org/json/JSONException;
        //  533    539    552    565    Ljava/security/NoSuchAlgorithmException;
        //  533    539    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  539    552    423    436    Lcom/netflix/android/org/json/JSONException;
        //  539    552    552    565    Ljava/security/NoSuchAlgorithmException;
        //  539    552    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  565    597    423    436    Lcom/netflix/android/org/json/JSONException;
        //  565    597    552    565    Ljava/security/NoSuchAlgorithmException;
        //  565    597    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  602    608    423    436    Lcom/netflix/android/org/json/JSONException;
        //  602    608    552    565    Ljava/security/NoSuchAlgorithmException;
        //  602    608    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  608    622    423    436    Lcom/netflix/android/org/json/JSONException;
        //  608    622    552    565    Ljava/security/NoSuchAlgorithmException;
        //  608    622    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  635    661    423    436    Lcom/netflix/android/org/json/JSONException;
        //  635    661    552    565    Ljava/security/NoSuchAlgorithmException;
        //  635    661    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  661    680    423    436    Lcom/netflix/android/org/json/JSONException;
        //  661    680    552    565    Ljava/security/NoSuchAlgorithmException;
        //  661    680    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  684    689    423    436    Lcom/netflix/android/org/json/JSONException;
        //  684    689    552    565    Ljava/security/NoSuchAlgorithmException;
        //  684    689    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  689    703    423    436    Lcom/netflix/android/org/json/JSONException;
        //  689    703    552    565    Ljava/security/NoSuchAlgorithmException;
        //  689    703    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  703    727    423    436    Lcom/netflix/android/org/json/JSONException;
        //  703    727    552    565    Ljava/security/NoSuchAlgorithmException;
        //  703    727    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  736    750    423    436    Lcom/netflix/android/org/json/JSONException;
        //  736    750    552    565    Ljava/security/NoSuchAlgorithmException;
        //  736    750    622    635    Ljava/security/spec/InvalidKeySpecException;
        //  750    769    423    436    Lcom/netflix/android/org/json/JSONException;
        //  750    769    552    565    Ljava/security/NoSuchAlgorithmException;
        //  750    769    622    635    Ljava/security/spec/InvalidKeySpecException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 377, Size: 377
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
    
    public JsonWebKey(final JsonWebKey$Usage usage, final JsonWebKey$Algorithm algo, final boolean extractable, final String id, final RSAPublicKey rsaPublicKey, final RSAPrivateKey rsaPrivateKey) {
        if (rsaPublicKey == null && rsaPrivateKey == null) {
            throw new MslInternalException("At least one of the public key or private key must be provided.");
        }
        if (algo != null) {
            switch (JsonWebKey$1.$SwitchMap$com$netflix$msl$crypto$JsonWebKey$Algorithm[algo.ordinal()]) {
                default: {
                    throw new MslInternalException("The algorithm must be an RSA algorithm.");
                }
                case 2:
                case 3: {
                    break;
                }
            }
        }
        this.type = JsonWebKey$Type.rsa;
        this.usage = usage;
        this.keyOps = null;
        this.algo = algo;
        this.extractable = extractable;
        this.id = id;
        this.keyPair = new KeyPair(rsaPublicKey, rsaPrivateKey);
        this.key = null;
        this.secretKey = null;
    }
    
    public JsonWebKey(final JsonWebKey$Usage usage, final JsonWebKey$Algorithm algo, final boolean extractable, final String id, final SecretKey secretKey) {
        if (algo != null) {
            switch (JsonWebKey$1.$SwitchMap$com$netflix$msl$crypto$JsonWebKey$Algorithm[algo.ordinal()]) {
                default: {
                    throw new MslInternalException("The algorithm must be a symmetric key algorithm.");
                }
                case 1:
                case 4:
                case 5: {
                    break;
                }
            }
        }
        this.type = JsonWebKey$Type.oct;
        this.usage = usage;
        this.keyOps = null;
        this.algo = algo;
        this.extractable = extractable;
        this.id = id;
        this.keyPair = null;
        this.key = secretKey.getEncoded();
        this.secretKey = secretKey;
    }
    
    public JsonWebKey(final Set<JsonWebKey$KeyOp> set, final JsonWebKey$Algorithm algo, final boolean extractable, final String id, final RSAPublicKey rsaPublicKey, final RSAPrivateKey rsaPrivateKey) {
        if (rsaPublicKey == null && rsaPrivateKey == null) {
            throw new MslInternalException("At least one of the public key or private key must be provided.");
        }
        if (algo != null) {
            switch (JsonWebKey$1.$SwitchMap$com$netflix$msl$crypto$JsonWebKey$Algorithm[algo.ordinal()]) {
                default: {
                    throw new MslInternalException("The algorithm must be an RSA algorithm.");
                }
                case 2:
                case 3: {
                    break;
                }
            }
        }
        this.type = JsonWebKey$Type.rsa;
        this.usage = null;
        Set<JsonWebKey$KeyOp> unmodifiableSet;
        if (set != null) {
            unmodifiableSet = Collections.unmodifiableSet((Set<? extends JsonWebKey$KeyOp>)set);
        }
        else {
            unmodifiableSet = null;
        }
        this.keyOps = unmodifiableSet;
        this.algo = algo;
        this.extractable = extractable;
        this.id = id;
        this.keyPair = new KeyPair(rsaPublicKey, rsaPrivateKey);
        this.key = null;
        this.secretKey = null;
    }
    
    public JsonWebKey(final Set<JsonWebKey$KeyOp> set, final JsonWebKey$Algorithm algo, final boolean extractable, final String id, final SecretKey secretKey) {
        if (algo != null) {
            switch (JsonWebKey$1.$SwitchMap$com$netflix$msl$crypto$JsonWebKey$Algorithm[algo.ordinal()]) {
                default: {
                    throw new MslInternalException("The algorithm must be a symmetric key algorithm.");
                }
                case 1:
                case 4:
                case 5: {
                    break;
                }
            }
        }
        this.type = JsonWebKey$Type.oct;
        this.usage = null;
        Set<JsonWebKey$KeyOp> unmodifiableSet;
        if (set != null) {
            unmodifiableSet = Collections.unmodifiableSet((Set<? extends JsonWebKey$KeyOp>)set);
        }
        else {
            unmodifiableSet = null;
        }
        this.keyOps = unmodifiableSet;
        this.algo = algo;
        this.extractable = extractable;
        this.id = id;
        this.keyPair = null;
        this.key = secretKey.getEncoded();
        this.secretKey = secretKey;
    }
    
    private static byte[] bi2bytes(final BigInteger bigInteger) {
        final byte[] byteArray = bigInteger.toByteArray();
        return Arrays.copyOfRange(byteArray, byteArray.length - (int)Math.ceil(bigInteger.bitLength() / 8.0), byteArray.length);
    }
    
    public JsonWebKey$Algorithm getAlgorithm() {
        return this.algo;
    }
    
    public String getId() {
        return this.id;
    }
    
    public Set<JsonWebKey$KeyOp> getKeyOps() {
        return this.keyOps;
    }
    
    public KeyPair getRsaKeyPair() {
        return this.keyPair;
    }
    
    public SecretKey getSecretKey() {
        return this.secretKey;
    }
    
    public SecretKey getSecretKey(final String s) {
        if (this.secretKey != null) {
            return this.secretKey;
        }
        if (this.key == null) {
            return null;
        }
        try {
            return new SecretKeySpec(this.key, s);
        }
        catch (IllegalArgumentException ex) {
            throw new MslCryptoException(MslError.INVALID_SYMMETRIC_KEY, ex);
        }
    }
    
    public JsonWebKey$Type getType() {
        return this.type;
    }
    
    public JsonWebKey$Usage getUsage() {
        return this.usage;
    }
    
    public boolean isExtractable() {
        return this.extractable;
    }
    
    @Override
    public String toJSONString() {
        JSONObject jsonObject = null;
        Label_0147: {
            JSONArray jsonArray;
            try {
                jsonObject = new JSONObject();
                jsonObject.put("kty", this.type.name());
                if (this.usage != null) {
                    jsonObject.put("use", this.usage.name());
                }
                if (this.keyOps == null) {
                    break Label_0147;
                }
                jsonArray = new JSONArray();
                final Iterator<JsonWebKey$KeyOp> iterator = this.keyOps.iterator();
                while (iterator.hasNext()) {
                    jsonArray.put(iterator.next().name());
                }
            }
            catch (JSONException ex) {
                throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
            }
            jsonObject.put("key_ops", jsonArray);
        }
        if (this.algo != null) {
            jsonObject.put("alg", this.algo.toString());
        }
        jsonObject.put("extractable", this.extractable);
        if (this.id != null) {
            jsonObject.put("kid", this.id);
        }
        if (this.type == JsonWebKey$Type.oct) {
            jsonObject.put("k", JsonUtils.b64urlEncode(this.key));
        }
        else {
            final RSAPublicKey rsaPublicKey = (RSAPublicKey)this.keyPair.getPublic();
            final RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)this.keyPair.getPrivate();
            BigInteger bigInteger;
            if (rsaPublicKey != null) {
                bigInteger = rsaPublicKey.getModulus();
            }
            else {
                bigInteger = rsaPrivateKey.getModulus();
            }
            jsonObject.put("n", JsonUtils.b64urlEncode(bi2bytes(bigInteger)));
            if (rsaPublicKey != null) {
                jsonObject.put("e", JsonUtils.b64urlEncode(bi2bytes(rsaPublicKey.getPublicExponent())));
            }
            if (rsaPrivateKey != null) {
                jsonObject.put("d", JsonUtils.b64urlEncode(bi2bytes(rsaPrivateKey.getPrivateExponent())));
            }
        }
        return jsonObject.toString();
    }
}
