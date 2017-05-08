// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import java.security.cert.CertificateEncodingException;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.Base64;
import com.netflix.android.org.json.JSONObject;
import java.security.cert.X509Certificate;

public class X509AuthenticationData extends EntityAuthenticationData
{
    private static final String KEY_X509_CERT = "x509certificate";
    private final String identity;
    private final X509Certificate x509cert;
    
    X509AuthenticationData(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getstatic       com/netflix/msl/entityauth/EntityAuthenticationScheme.X509:Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;
        //     4: invokespecial   com/netflix/msl/entityauth/EntityAuthenticationData.<init>:(Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;)V
        //     7: aload_1        
        //     8: ldc             "x509certificate"
        //    10: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    13: astore_2       
        //    14: ldc             "X.509"
        //    16: invokestatic    java/security/cert/CertificateFactory.getInstance:(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
        //    19: astore_1       
        //    20: aload_2        
        //    21: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    24: astore_3       
        //    25: aload_0        
        //    26: aload_1        
        //    27: new             Ljava/io/ByteArrayInputStream;
        //    30: dup            
        //    31: aload_3        
        //    32: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    35: invokevirtual   java/security/cert/CertificateFactory.generateCertificate:(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
        //    38: checkcast       Ljava/security/cert/X509Certificate;
        //    41: putfield        com/netflix/msl/entityauth/X509AuthenticationData.x509cert:Ljava/security/cert/X509Certificate;
        //    44: aload_0        
        //    45: aload_0        
        //    46: getfield        com/netflix/msl/entityauth/X509AuthenticationData.x509cert:Ljava/security/cert/X509Certificate;
        //    49: invokevirtual   java/security/cert/X509Certificate.getSubjectX500Principal:()Ljavax/security/auth/x500/X500Principal;
        //    52: invokevirtual   javax/security/auth/x500/X500Principal.getName:()Ljava/lang/String;
        //    55: putfield        com/netflix/msl/entityauth/X509AuthenticationData.identity:Ljava/lang/String;
        //    58: return         
        //    59: astore_2       
        //    60: new             Lcom/netflix/msl/MslEncodingException;
        //    63: dup            
        //    64: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //    67: new             Ljava/lang/StringBuilder;
        //    70: dup            
        //    71: invokespecial   java/lang/StringBuilder.<init>:()V
        //    74: ldc             "X.509 authdata "
        //    76: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    79: aload_1        
        //    80: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    89: aload_2        
        //    90: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    93: athrow         
        //    94: astore_1       
        //    95: new             Lcom/netflix/msl/MslInternalException;
        //    98: dup            
        //    99: ldc             "No certificate X.509 certificate factory."
        //   101: aload_1        
        //   102: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   105: athrow         
        //   106: astore_1       
        //   107: new             Lcom/netflix/msl/MslCryptoException;
        //   110: dup            
        //   111: getstatic       com/netflix/msl/MslError.X509CERT_INVALID:Lcom/netflix/msl/MslError;
        //   114: aload_2        
        //   115: aload_1        
        //   116: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   119: athrow         
        //   120: astore_1       
        //   121: new             Lcom/netflix/msl/MslCryptoException;
        //   124: dup            
        //   125: getstatic       com/netflix/msl/MslError.X509CERT_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   128: aload_2        
        //   129: aload_1        
        //   130: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   133: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  7      14     59     94     Lcom/netflix/android/org/json/JSONException;
        //  14     20     94     106    Ljava/security/cert/CertificateException;
        //  20     25     106    120    Ljava/lang/IllegalArgumentException;
        //  25     58     120    134    Ljava/security/cert/CertificateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 68, Size: 68
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
    
    public X509AuthenticationData(final X509Certificate x509cert) {
        super(EntityAuthenticationScheme.X509);
        this.x509cert = x509cert;
        this.identity = x509cert.getSubjectX500Principal().getName();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof X509AuthenticationData)) {
            return false;
        }
        final X509AuthenticationData x509AuthenticationData = (X509AuthenticationData)o;
        return super.equals(o) && this.identity.equals(x509AuthenticationData.identity);
    }
    
    @Override
    public JSONObject getAuthData() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("x509certificate", Base64.encode(this.x509cert.getEncoded()));
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "X.509 authdata", ex);
        }
        catch (CertificateEncodingException ex2) {
            throw new MslEncodingException(MslError.X509CERT_ENCODE_ERROR, "X.509 authdata", ex2);
        }
    }
    
    @Override
    public String getIdentity() {
        return this.identity;
    }
    
    public X509Certificate getX509Cert() {
        return this.x509cert;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.identity.hashCode();
    }
}
