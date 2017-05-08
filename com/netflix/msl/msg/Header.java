// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.crypto.ICryptoContext;
import java.util.Map;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.android.org.json.JSONString;

public abstract class Header implements JSONString
{
    public static Header parseHeader(final MslContext p0, final JSONObject p1, final Map<String, ICryptoContext> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: aload_1        
        //     4: ldc             "entityauthdata"
        //     6: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //     9: ifeq            132
        //    12: aload_0        
        //    13: aload_1        
        //    14: ldc             "entityauthdata"
        //    16: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //    19: invokestatic    com/netflix/msl/entityauth/EntityAuthenticationData.create:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;)Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //    22: astore_3       
        //    23: aload_1        
        //    24: ldc             "mastertoken"
        //    26: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    29: ifeq            48
        //    32: new             Lcom/netflix/msl/tokens/MasterToken;
        //    35: dup            
        //    36: aload_0        
        //    37: aload_1        
        //    38: ldc             "mastertoken"
        //    40: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //    43: invokespecial   com/netflix/msl/tokens/MasterToken.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;)V
        //    46: astore          4
        //    48: aload_1        
        //    49: ldc             "signature"
        //    51: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    54: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    57: astore          5
        //    59: aload           5
        //    61: ifnonnull       171
        //    64: new             Lcom/netflix/msl/MslMessageException;
        //    67: dup            
        //    68: getstatic       com/netflix/msl/MslError.HEADER_SIGNATURE_INVALID:Lcom/netflix/msl/MslError;
        //    71: new             Ljava/lang/StringBuilder;
        //    74: dup            
        //    75: invokespecial   java/lang/StringBuilder.<init>:()V
        //    78: ldc             "header/errormsg "
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload_1        
        //    84: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    96: athrow         
        //    97: astore_0       
        //    98: new             Lcom/netflix/msl/MslEncodingException;
        //   101: dup            
        //   102: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   105: new             Ljava/lang/StringBuilder;
        //   108: dup            
        //   109: invokespecial   java/lang/StringBuilder.<init>:()V
        //   112: ldc             "header/errormsg "
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: aload_1        
        //   118: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: aload_0        
        //   128: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   131: athrow         
        //   132: aconst_null    
        //   133: astore_3       
        //   134: goto            23
        //   137: astore_0       
        //   138: new             Lcom/netflix/msl/MslMessageException;
        //   141: dup            
        //   142: getstatic       com/netflix/msl/MslError.HEADER_SIGNATURE_INVALID:Lcom/netflix/msl/MslError;
        //   145: new             Ljava/lang/StringBuilder;
        //   148: dup            
        //   149: invokespecial   java/lang/StringBuilder.<init>:()V
        //   152: ldc             "header/errormsg "
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: aload_1        
        //   158: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   161: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   167: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   170: athrow         
        //   171: aload_1        
        //   172: ldc             "headerdata"
        //   174: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   177: ifeq            281
        //   180: new             Lcom/netflix/msl/msg/MessageHeader;
        //   183: dup            
        //   184: aload_0        
        //   185: aload_1        
        //   186: ldc             "headerdata"
        //   188: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   191: aload_3        
        //   192: aload           4
        //   194: aload           5
        //   196: aload_2        
        //   197: invokespecial   com/netflix/msl/msg/MessageHeader.<init>:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Lcom/netflix/msl/entityauth/EntityAuthenticationData;Lcom/netflix/msl/tokens/MasterToken;[BLjava/util/Map;)V
        //   200: astore_2       
        //   201: aload_2        
        //   202: astore_0       
        //   203: aload_2        
        //   204: invokevirtual   com/netflix/msl/msg/MessageHeader.isDecrypted:()Z
        //   207: ifne            308
        //   210: aload           4
        //   212: ifnull          266
        //   215: new             Lcom/netflix/msl/MslCryptoException;
        //   218: dup            
        //   219: getstatic       com/netflix/msl/MslError.MESSAGE_MASTERTOKENBASED_VERIFICATION_FAILED:Lcom/netflix/msl/MslError;
        //   222: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;)V
        //   225: aload           4
        //   227: invokevirtual   com/netflix/msl/MslCryptoException.setMasterToken:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/MslCryptoException;
        //   230: athrow         
        //   231: astore_0       
        //   232: new             Lcom/netflix/msl/MslEncodingException;
        //   235: dup            
        //   236: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   239: new             Ljava/lang/StringBuilder;
        //   242: dup            
        //   243: invokespecial   java/lang/StringBuilder.<init>:()V
        //   246: ldc             "header/errormsg "
        //   248: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   251: aload_1        
        //   252: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   255: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   258: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   261: aload_0        
        //   262: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   265: athrow         
        //   266: new             Lcom/netflix/msl/MslCryptoException;
        //   269: dup            
        //   270: getstatic       com/netflix/msl/MslError.MESSAGE_ENTITYDATABASED_VERIFICATION_FAILED:Lcom/netflix/msl/MslError;
        //   273: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;)V
        //   276: aload_3        
        //   277: invokevirtual   com/netflix/msl/MslCryptoException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslCryptoException;
        //   280: athrow         
        //   281: aload_1        
        //   282: ldc             "errordata"
        //   284: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   287: ifeq            310
        //   290: new             Lcom/netflix/msl/msg/ErrorHeader;
        //   293: dup            
        //   294: aload_0        
        //   295: aload_1        
        //   296: ldc             "errordata"
        //   298: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   301: aload_3        
        //   302: aload           5
        //   304: invokespecial   com/netflix/msl/msg/ErrorHeader.<init>:(Lcom/netflix/msl/util/MslContext;Ljava/lang/String;Lcom/netflix/msl/entityauth/EntityAuthenticationData;[B)V
        //   307: astore_0       
        //   308: aload_0        
        //   309: areturn        
        //   310: new             Lcom/netflix/msl/MslEncodingException;
        //   313: dup            
        //   314: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   317: aload_1        
        //   318: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   321: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   324: athrow         
        //    Signature:
        //  (Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;Ljava/util/Map<Ljava/lang/String;Lcom/netflix/msl/crypto/ICryptoContext;>;)Lcom/netflix/msl/msg/Header;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  3      23     97     132    Lcom/netflix/android/org/json/JSONException;
        //  23     48     97     132    Lcom/netflix/android/org/json/JSONException;
        //  48     59     137    171    Ljava/lang/IllegalArgumentException;
        //  48     59     97     132    Lcom/netflix/android/org/json/JSONException;
        //  64     97     97     132    Lcom/netflix/android/org/json/JSONException;
        //  138    171    97     132    Lcom/netflix/android/org/json/JSONException;
        //  171    201    231    266    Lcom/netflix/android/org/json/JSONException;
        //  203    210    231    266    Lcom/netflix/android/org/json/JSONException;
        //  215    231    231    266    Lcom/netflix/android/org/json/JSONException;
        //  266    281    231    266    Lcom/netflix/android/org/json/JSONException;
        //  281    308    231    266    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
