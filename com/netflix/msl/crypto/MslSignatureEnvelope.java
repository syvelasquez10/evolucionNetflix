// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.util.Base64;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.MslConstants;
import com.netflix.msl.MslConstants$SignatureAlgo;

public class MslSignatureEnvelope
{
    private static final String KEY_ALGORITHM = "algorithm";
    private static final String KEY_SIGNATURE = "signature";
    private static final String KEY_VERSION = "version";
    private final MslConstants$SignatureAlgo algorithm;
    private final byte[] signature;
    private final MslSignatureEnvelope$Version version;
    
    public MslSignatureEnvelope(final MslConstants$SignatureAlgo algorithm, final byte[] signature) {
        this.version = MslSignatureEnvelope$Version.V2;
        this.algorithm = algorithm;
        this.signature = signature;
    }
    
    public MslSignatureEnvelope(final byte[] signature) {
        this.version = MslSignatureEnvelope$Version.V1;
        this.algorithm = null;
        this.signature = signature;
    }
    
    public static MslSignatureEnvelope parse(final byte[] array) {
    Label_0036_Outer:
        while (true) {
            MslConstants$SignatureAlgo fromString = null;
            byte[] decode = null;
            Label_0145: {
                JSONObject jsonObject;
                while (true) {
                    while (true) {
                        try {
                            jsonObject = new JSONObject(new String(array, MslConstants.DEFAULT_CHARSET));
                            if (jsonObject == null || !jsonObject.has("version")) {
                                final MslSignatureEnvelope$Version mslSignatureEnvelope$Version = MslSignatureEnvelope$Version.V1;
                                switch (MslSignatureEnvelope$1.$SwitchMap$com$netflix$msl$crypto$MslSignatureEnvelope$Version[mslSignatureEnvelope$Version.ordinal()]) {
                                    default: {
                                        throw new MslCryptoException(MslError.UNSUPPORTED_SIGNATURE_ENVELOPE, "signature envelope " + Base64.encode(array));
                                    }
                                    case 1: {
                                        return new MslSignatureEnvelope(array);
                                    }
                                    case 2: {
                                        break Label_0145;
                                    }
                                }
                            }
                        }
                        catch (JSONException ex) {
                            jsonObject = null;
                            continue Label_0036_Outer;
                        }
                        break;
                    }
                    try {
                        final MslSignatureEnvelope$Version mslSignatureEnvelope$Version = MslSignatureEnvelope$Version.valueOf(jsonObject.getInt("version"));
                        continue;
                    }
                    catch (JSONException ex2) {
                        final MslSignatureEnvelope$Version mslSignatureEnvelope$Version = MslSignatureEnvelope$Version.V1;
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        final MslSignatureEnvelope$Version mslSignatureEnvelope$Version = MslSignatureEnvelope$Version.V1;
                        continue;
                    }
                    continue;
                }
                try {
                    fromString = MslConstants$SignatureAlgo.fromString(jsonObject.getString("algorithm"));
                    decode = Base64.decode(jsonObject.getString("signature"));
                    if (decode == null) {
                        return new MslSignatureEnvelope(array);
                    }
                }
                catch (JSONException ex4) {
                    return new MslSignatureEnvelope(array);
                }
                catch (IllegalArgumentException ex5) {
                    return new MslSignatureEnvelope(array);
                }
            }
            return new MslSignatureEnvelope(fromString, decode);
        }
    }
    
    public static MslSignatureEnvelope parse(final byte[] p0, final MslSignatureEnvelope$Version p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/netflix/msl/crypto/MslSignatureEnvelope$1.$SwitchMap$com$netflix$msl$crypto$MslSignatureEnvelope$Version:[I
        //     3: aload_1        
        //     4: invokevirtual   com/netflix/msl/crypto/MslSignatureEnvelope$Version.ordinal:()I
        //     7: iaload         
        //     8: tableswitch {
        //                2: 65
        //                3: 74
        //          default: 32
        //        }
        //    32: new             Lcom/netflix/msl/MslCryptoException;
        //    35: dup            
        //    36: getstatic       com/netflix/msl/MslError.UNSUPPORTED_SIGNATURE_ENVELOPE:Lcom/netflix/msl/MslError;
        //    39: new             Ljava/lang/StringBuilder;
        //    42: dup            
        //    43: invokespecial   java/lang/StringBuilder.<init>:()V
        //    46: ldc             "signature envelope "
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: aload_0        
        //    52: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //    55: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    58: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    61: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    64: athrow         
        //    65: new             Lcom/netflix/msl/crypto/MslSignatureEnvelope;
        //    68: dup            
        //    69: aload_0        
        //    70: invokespecial   com/netflix/msl/crypto/MslSignatureEnvelope.<init>:([B)V
        //    73: areturn        
        //    74: new             Lcom/netflix/android/org/json/JSONObject;
        //    77: dup            
        //    78: new             Ljava/lang/String;
        //    81: dup            
        //    82: aload_0        
        //    83: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //    86: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //    89: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    92: astore_1       
        //    93: aload_1        
        //    94: ldc             "version"
        //    96: invokevirtual   com/netflix/android/org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //    99: invokestatic    com/netflix/msl/crypto/MslSignatureEnvelope$Version.valueOf:(I)Lcom/netflix/msl/crypto/MslSignatureEnvelope$Version;
        //   102: astore_2       
        //   103: getstatic       com/netflix/msl/crypto/MslSignatureEnvelope$Version.V2:Lcom/netflix/msl/crypto/MslSignatureEnvelope$Version;
        //   106: aload_2        
        //   107: invokevirtual   com/netflix/msl/crypto/MslSignatureEnvelope$Version.equals:(Ljava/lang/Object;)Z
        //   110: ifne            216
        //   113: new             Lcom/netflix/msl/MslCryptoException;
        //   116: dup            
        //   117: getstatic       com/netflix/msl/MslError.UNSUPPORTED_SIGNATURE_ENVELOPE:Lcom/netflix/msl/MslError;
        //   120: new             Ljava/lang/StringBuilder;
        //   123: dup            
        //   124: invokespecial   java/lang/StringBuilder.<init>:()V
        //   127: ldc             "signature envelope "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload_0        
        //   133: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   142: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   145: athrow         
        //   146: astore_1       
        //   147: new             Lcom/netflix/msl/MslCryptoException;
        //   150: dup            
        //   151: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_SIGNATURE_ENVELOPE:Lcom/netflix/msl/MslError;
        //   154: new             Ljava/lang/StringBuilder;
        //   157: dup            
        //   158: invokespecial   java/lang/StringBuilder.<init>:()V
        //   161: ldc             "signature envelope "
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: aload_0        
        //   167: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   176: aload_1        
        //   177: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   180: athrow         
        //   181: astore_1       
        //   182: new             Lcom/netflix/msl/MslEncodingException;
        //   185: dup            
        //   186: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   189: new             Ljava/lang/StringBuilder;
        //   192: dup            
        //   193: invokespecial   java/lang/StringBuilder.<init>:()V
        //   196: ldc             "signature envelope "
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: aload_0        
        //   202: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   211: aload_1        
        //   212: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   215: athrow         
        //   216: aload_1        
        //   217: ldc             "algorithm"
        //   219: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   222: invokestatic    com/netflix/msl/MslConstants$SignatureAlgo.fromString:(Ljava/lang/String;)Lcom/netflix/msl/MslConstants$SignatureAlgo;
        //   225: astore_2       
        //   226: aload_1        
        //   227: ldc             "signature"
        //   229: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   232: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   235: astore_1       
        //   236: aload_1        
        //   237: ifnonnull       342
        //   240: new             Lcom/netflix/msl/MslCryptoException;
        //   243: dup            
        //   244: getstatic       com/netflix/msl/MslError.INVALID_SIGNATURE:Lcom/netflix/msl/MslError;
        //   247: new             Ljava/lang/StringBuilder;
        //   250: dup            
        //   251: invokespecial   java/lang/StringBuilder.<init>:()V
        //   254: ldc             "signature envelope "
        //   256: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   259: aload_0        
        //   260: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   269: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   272: athrow         
        //   273: astore_1       
        //   274: new             Lcom/netflix/msl/MslCryptoException;
        //   277: dup            
        //   278: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_ALGORITHM:Lcom/netflix/msl/MslError;
        //   281: new             Ljava/lang/StringBuilder;
        //   284: dup            
        //   285: invokespecial   java/lang/StringBuilder.<init>:()V
        //   288: ldc             "signature envelope "
        //   290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   293: aload_0        
        //   294: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   297: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   300: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   303: aload_1        
        //   304: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   307: athrow         
        //   308: astore_1       
        //   309: new             Lcom/netflix/msl/MslCryptoException;
        //   312: dup            
        //   313: getstatic       com/netflix/msl/MslError.INVALID_SIGNATURE:Lcom/netflix/msl/MslError;
        //   316: new             Ljava/lang/StringBuilder;
        //   319: dup            
        //   320: invokespecial   java/lang/StringBuilder.<init>:()V
        //   323: ldc             "signature envelope "
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: aload_0        
        //   329: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   338: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   341: athrow         
        //   342: new             Lcom/netflix/msl/crypto/MslSignatureEnvelope;
        //   345: dup            
        //   346: aload_2        
        //   347: aload_1        
        //   348: invokespecial   com/netflix/msl/crypto/MslSignatureEnvelope.<init>:(Lcom/netflix/msl/MslConstants$SignatureAlgo;[B)V
        //   351: astore_1       
        //   352: aload_1        
        //   353: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  74     93     181    216    Lcom/netflix/android/org/json/JSONException;
        //  93     146    146    181    Ljava/lang/IllegalArgumentException;
        //  93     146    181    216    Lcom/netflix/android/org/json/JSONException;
        //  147    181    181    216    Lcom/netflix/android/org/json/JSONException;
        //  216    226    273    308    Ljava/lang/IllegalArgumentException;
        //  216    226    181    216    Lcom/netflix/android/org/json/JSONException;
        //  226    236    308    342    Ljava/lang/IllegalArgumentException;
        //  226    236    181    216    Lcom/netflix/android/org/json/JSONException;
        //  240    273    181    216    Lcom/netflix/android/org/json/JSONException;
        //  274    308    181    216    Lcom/netflix/android/org/json/JSONException;
        //  309    342    181    216    Lcom/netflix/android/org/json/JSONException;
        //  342    352    181    216    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 153, Size: 153
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
    
    public MslConstants$SignatureAlgo getAlgorithm() {
        return this.algorithm;
    }
    
    public byte[] getBytes() {
        switch (MslSignatureEnvelope$1.$SwitchMap$com$netflix$msl$crypto$MslSignatureEnvelope$Version[this.version.ordinal()]) {
            default: {
                throw new MslInternalException("Signature envelope version " + this.version + " encoding unsupported.");
            }
            case 1: {
                return this.signature;
            }
            case 2: {
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("version", this.version.intValue());
                    jsonObject.put("algorithm", this.algorithm.toString());
                    jsonObject.put("signature", Base64.encode(this.signature));
                    return jsonObject.toString().getBytes(MslConstants.DEFAULT_CHARSET);
                }
                catch (JSONException ex) {
                    throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
                }
                break;
            }
        }
    }
    
    public byte[] getSignature() {
        return this.signature;
    }
}
