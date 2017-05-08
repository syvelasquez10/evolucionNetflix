// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.util.Pair;
import java.util.List;
import org.json.JSONObject;

final class CastPrefetchMessage
{
    private static final String TAG = "CastPrefetchMessage";
    final int BLOCKSIZE_IN_BYTES;
    final String PROP_CIPHERTEXT;
    final String PROP_CONTROLLER_ESN;
    final String PROP_EVENLOP_ID;
    final String PROP_IV;
    final String PROP_KEY_ID;
    final String PROP_MOVIES_ARRAY;
    final String PROP_MOVIE_ID;
    final String PROP_PRIORITY;
    final String PROP_TAGLENGTH;
    final int TAGLENGHT_IN_BIT;
    final byte evenlopeId;
    private JSONObject mMessageJSON;
    
    CastPrefetchMessage(final List<Pair<String, Integer>> p0, final String p1, final String p2, final byte[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: ldc             "movieId"
        //     7: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_MOVIE_ID:Ljava/lang/String;
        //    10: aload_0        
        //    11: ldc             "priority"
        //    13: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_PRIORITY:Ljava/lang/String;
        //    16: aload_0        
        //    17: ldc             "movies"
        //    19: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_MOVIES_ARRAY:Ljava/lang/String;
        //    22: aload_0        
        //    23: ldc             "controllerESN"
        //    25: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_CONTROLLER_ESN:Ljava/lang/String;
        //    28: aload_0        
        //    29: ldc             "evenlopeId"
        //    31: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_EVENLOP_ID:Ljava/lang/String;
        //    34: aload_0        
        //    35: ldc             "keyId"
        //    37: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_KEY_ID:Ljava/lang/String;
        //    40: aload_0        
        //    41: ldc             "iv"
        //    43: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_IV:Ljava/lang/String;
        //    46: aload_0        
        //    47: ldc             "taglength"
        //    49: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_TAGLENGTH:Ljava/lang/String;
        //    52: aload_0        
        //    53: ldc             "ciphertext"
        //    55: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.PROP_CIPHERTEXT:Ljava/lang/String;
        //    58: aload_0        
        //    59: bipush          16
        //    61: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.BLOCKSIZE_IN_BYTES:I
        //    64: aload_0        
        //    65: sipush          128
        //    68: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.TAGLENGHT_IN_BIT:I
        //    71: aload_0        
        //    72: iconst_1       
        //    73: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.evenlopeId:B
        //    76: new             Lorg/json/JSONArray;
        //    79: dup            
        //    80: invokespecial   org/json/JSONArray.<init>:()V
        //    83: astore          5
        //    85: aload_1        
        //    86: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    91: astore_1       
        //    92: aload_1        
        //    93: invokeinterface java/util/Iterator.hasNext:()Z
        //    98: ifeq            208
        //   101: aload_1        
        //   102: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   107: checkcast       Landroid/util/Pair;
        //   110: astore          6
        //   112: new             Lorg/json/JSONObject;
        //   115: dup            
        //   116: invokespecial   org/json/JSONObject.<init>:()V
        //   119: astore          7
        //   121: aload           7
        //   123: ldc             "movieId"
        //   125: aload           6
        //   127: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   130: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   133: ldc             "priority"
        //   135: aload           6
        //   137: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   140: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   143: pop            
        //   144: aload           5
        //   146: aload           7
        //   148: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   151: pop            
        //   152: goto            92
        //   155: astore          7
        //   157: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   160: ifeq            92
        //   163: ldc             "CastPrefetchMessage"
        //   165: new             Ljava/lang/StringBuilder;
        //   168: dup            
        //   169: invokespecial   java/lang/StringBuilder.<init>:()V
        //   172: ldc             ", skip "
        //   174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: aload           6
        //   179: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   182: checkcast       Ljava/lang/String;
        //   185: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: ldc             "has exception, "
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: aload           7
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   198: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   201: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   204: pop            
        //   205: goto            92
        //   208: new             Lorg/json/JSONObject;
        //   211: dup            
        //   212: invokespecial   org/json/JSONObject.<init>:()V
        //   215: astore_1       
        //   216: aload_1        
        //   217: ldc             "movies"
        //   219: aload           5
        //   221: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   224: ldc             "controllerESN"
        //   226: aload_2        
        //   227: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   230: pop            
        //   231: bipush          16
        //   233: newarray        B
        //   235: astore_2       
        //   236: new             Ljava/util/Random;
        //   239: dup            
        //   240: invokestatic    java/lang/System.nanoTime:()J
        //   243: invokespecial   java/util/Random.<init>:(J)V
        //   246: aload_2        
        //   247: invokevirtual   java/util/Random.nextBytes:([B)V
        //   250: new             Ljavax/crypto/spec/GCMParameterSpec;
        //   253: dup            
        //   254: sipush          128
        //   257: aload_2        
        //   258: invokespecial   javax/crypto/spec/GCMParameterSpec.<init>:(I[B)V
        //   261: astore          5
        //   263: new             Ljavax/crypto/spec/SecretKeySpec;
        //   266: dup            
        //   267: aload           4
        //   269: ldc             "AES"
        //   271: invokespecial   javax/crypto/spec/SecretKeySpec.<init>:([BLjava/lang/String;)V
        //   274: astore          4
        //   276: ldc             "AES/GCM/NoPadding"
        //   278: invokestatic    javax/crypto/Cipher.getInstance:(Ljava/lang/String;)Ljavax/crypto/Cipher;
        //   281: astore          6
        //   283: aload           6
        //   285: ifnonnull       340
        //   288: ldc             "CastPrefetchMessage"
        //   290: ldc             "cipher is null"
        //   292: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   295: pop            
        //   296: return         
        //   297: astore_1       
        //   298: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   301: ifeq            296
        //   304: ldc             "CastPrefetchMessage"
        //   306: new             Ljava/lang/StringBuilder;
        //   309: dup            
        //   310: invokespecial   java/lang/StringBuilder.<init>:()V
        //   313: ldc             "exception builing prefetchHints "
        //   315: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   318: aload_1        
        //   319: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   322: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   325: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   328: pop            
        //   329: return         
        //   330: astore_1       
        //   331: ldc             "CastPrefetchMessage"
        //   333: ldc             "NoSuchAlgorithmException or NoSuchPaddingException for GCM encryption"
        //   335: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   338: pop            
        //   339: return         
        //   340: new             Ljava/lang/String;
        //   343: dup            
        //   344: invokespecial   java/lang/String.<init>:()V
        //   347: pop            
        //   348: aload           6
        //   350: iconst_1       
        //   351: aload           4
        //   353: aload           5
        //   355: invokevirtual   javax/crypto/Cipher.init:(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V
        //   358: aload           6
        //   360: aload_1        
        //   361: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   364: invokevirtual   java/lang/String.getBytes:()[B
        //   367: invokevirtual   javax/crypto/Cipher.doFinal:([B)[B
        //   370: invokestatic    com/netflix/mediaclient/util/Base64.encodeBytes:([B)Ljava/lang/String;
        //   373: astore_1       
        //   374: aload_1        
        //   375: invokevirtual   java/lang/String.isEmpty:()Z
        //   378: ifne            296
        //   381: aload_0        
        //   382: new             Lorg/json/JSONObject;
        //   385: dup            
        //   386: invokespecial   org/json/JSONObject.<init>:()V
        //   389: putfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.mMessageJSON:Lorg/json/JSONObject;
        //   392: aload_0        
        //   393: getfield        com/netflix/mediaclient/service/mdx/CastPrefetchMessage.mMessageJSON:Lorg/json/JSONObject;
        //   396: ldc             "evenlopeId"
        //   398: iconst_1       
        //   399: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   402: ldc             "keyId"
        //   404: aload_3        
        //   405: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   408: ldc             "iv"
        //   410: aload_2        
        //   411: invokestatic    com/netflix/mediaclient/util/Base64.encodeBytes:([B)Ljava/lang/String;
        //   414: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   417: ldc             "taglength"
        //   419: sipush          128
        //   422: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   425: ldc             "ciphertext"
        //   427: aload_1        
        //   428: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   431: pop            
        //   432: return         
        //   433: astore_1       
        //   434: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   437: ifeq            296
        //   440: ldc             "CastPrefetchMessage"
        //   442: new             Ljava/lang/StringBuilder;
        //   445: dup            
        //   446: invokespecial   java/lang/StringBuilder.<init>:()V
        //   449: ldc             "construct prefetch message has exception "
        //   451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   454: aload_1        
        //   455: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   458: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   461: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   464: pop            
        //   465: return         
        //   466: astore_1       
        //   467: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   470: ifeq            296
        //   473: ldc             "CastPrefetchMessage"
        //   475: new             Ljava/lang/StringBuilder;
        //   478: dup            
        //   479: invokespecial   java/lang/StringBuilder.<init>:()V
        //   482: ldc             "encryption exception "
        //   484: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   487: aload_1        
        //   488: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   491: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   494: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   497: pop            
        //   498: return         
        //   499: astore_1       
        //   500: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   503: ifeq            296
        //   506: ldc             "CastPrefetchMessage"
        //   508: new             Ljava/lang/StringBuilder;
        //   511: dup            
        //   512: invokespecial   java/lang/StringBuilder.<init>:()V
        //   515: ldc             "Base64 exception "
        //   517: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   520: aload_1        
        //   521: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   524: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   527: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   530: pop            
        //   531: return         
        //   532: astore_1       
        //   533: goto            434
        //   536: astore_1       
        //   537: goto            467
        //   540: astore_1       
        //   541: goto            467
        //   544: astore_1       
        //   545: goto            467
        //   548: astore_1       
        //   549: goto            331
        //    Signature:
        //  (Ljava/util/List<Landroid/util/Pair<Ljava/lang/String;Ljava/lang/Integer;>;>;Ljava/lang/String;Ljava/lang/String;[B)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                              
        //  -----  -----  -----  -----  --------------------------------------------------
        //  121    144    155    208    Lorg/json/JSONException;
        //  216    231    297    330    Lorg/json/JSONException;
        //  276    283    330    331    Ljava/security/NoSuchAlgorithmException;
        //  276    283    548    552    Ljavax/crypto/NoSuchPaddingException;
        //  348    374    540    544    Ljava/security/InvalidKeyException;
        //  348    374    536    540    Ljava/security/InvalidAlgorithmParameterException;
        //  348    374    544    548    Ljavax/crypto/IllegalBlockSizeException;
        //  348    374    466    467    Ljavax/crypto/BadPaddingException;
        //  348    374    499    532    Ljava/io/IOException;
        //  392    432    433    434    Lorg/json/JSONException;
        //  392    432    532    536    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0296:
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
    
    String getMessage() {
        if (this.mMessageJSON != null) {
            return this.mMessageJSON.toString();
        }
        return null;
    }
}
