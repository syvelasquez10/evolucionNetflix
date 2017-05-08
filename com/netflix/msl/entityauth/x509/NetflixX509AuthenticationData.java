// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth.x509;

import java.security.cert.CertificateEncodingException;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import java.util.Iterator;
import com.netflix.msl.util.Base64;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import com.netflix.android.org.json.JSONObject;
import java.security.cert.X509Certificate;
import java.util.List;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class NetflixX509AuthenticationData extends EntityAuthenticationData
{
    public static final String KEY_X509_CERT = "x509certificate";
    public static final String KEY_X509_CHAIN = "x509chain";
    public static final String KEY_X509_CHAIN_IDENTITY = "identity";
    private NetflixX509AuthenticationData$CertificateProvided certType;
    private final List<X509Certificate> certs;
    private String identity;
    
    NetflixX509AuthenticationData(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getstatic       com/netflix/msl/entityauth/EntityAuthenticationScheme.X509:Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;
        //     4: invokespecial   com/netflix/msl/entityauth/EntityAuthenticationData.<init>:(Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;)V
        //     7: ldc             "X.509"
        //     9: invokestatic    java/security/cert/CertificateFactory.getInstance:(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
        //    12: astore_3       
        //    13: aload_1        
        //    14: ldc             "x509certificate"
        //    16: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    19: ifeq            168
        //    22: aload_1        
        //    23: ldc             "x509certificate"
        //    25: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    28: astore_2       
        //    29: new             Ljava/lang/StringBuilder;
        //    32: dup            
        //    33: invokespecial   java/lang/StringBuilder.<init>:()V
        //    36: ldc             "-----BEGIN CERTIFICATE-----\n"
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: aload_2        
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    45: ldc             "-----END CERTIFICATE-----"
        //    47: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    50: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    53: astore_2       
        //    54: aload_0        
        //    55: getstatic       com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData$CertificateProvided.SINGLE:Lcom/netflix/msl/entityauth/x509/NetflixX509AuthenticationData$CertificateProvided;
        //    58: putfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.certType:Lcom/netflix/msl/entityauth/x509/NetflixX509AuthenticationData$CertificateProvided;
        //    61: aload_2        
        //    62: astore_1       
        //    63: aload_0        
        //    64: new             Ljava/util/ArrayList;
        //    67: dup            
        //    68: invokespecial   java/util/ArrayList.<init>:()V
        //    71: putfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.certs:Ljava/util/List;
        //    74: new             Ljava/io/BufferedInputStream;
        //    77: dup            
        //    78: new             Ljava/io/ByteArrayInputStream;
        //    81: dup            
        //    82: aload_1        
        //    83: getstatic       java/nio/charset/StandardCharsets.UTF_8:Ljava/nio/charset/Charset;
        //    86: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //    89: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    92: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    95: astore_2       
        //    96: aload_2        
        //    97: invokevirtual   java/io/BufferedInputStream.available:()I
        //   100: ifle            289
        //   103: aload_3        
        //   104: aload_2        
        //   105: invokevirtual   java/security/cert/CertificateFactory.generateCertificate:(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
        //   108: checkcast       Ljava/security/cert/X509Certificate;
        //   111: astore          4
        //   113: aload_0        
        //   114: getfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.certs:Ljava/util/List;
        //   117: aload           4
        //   119: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   124: pop            
        //   125: goto            96
        //   128: astore_2       
        //   129: new             Lcom/netflix/msl/MslCryptoException;
        //   132: dup            
        //   133: getstatic       com/netflix/msl/MslError.X509CERT_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   136: aload_1        
        //   137: aload_2        
        //   138: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   141: athrow         
        //   142: astore_2       
        //   143: new             Lcom/netflix/msl/MslCryptoException;
        //   146: dup            
        //   147: getstatic       com/netflix/msl/MslError.X509CERT_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   150: aload_1        
        //   151: aload_2        
        //   152: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   155: athrow         
        //   156: astore_1       
        //   157: new             Lcom/netflix/msl/MslInternalException;
        //   160: dup            
        //   161: ldc             "No certificate X.509 certificate factory."
        //   163: aload_1        
        //   164: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   167: athrow         
        //   168: aload_1        
        //   169: ldc             "x509chain"
        //   171: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   174: ifeq            220
        //   177: aload_1        
        //   178: ldc             "x509chain"
        //   180: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   183: ldc             "-----BEGIN CERTIFICATE-----(?!\n)"
        //   185: ldc             "-----BEGIN CERTIFICATE-----\n"
        //   187: invokevirtual   java/lang/String.replaceAll:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   190: ldc             "-----END CERTIFICATE-----(?!\n)"
        //   192: ldc             "-----END CERTIFICATE-----\n"
        //   194: invokevirtual   java/lang/String.replaceAll:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   197: astore_2       
        //   198: aload_0        
        //   199: aload_1        
        //   200: ldc             "identity"
        //   202: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   205: putfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.identity:Ljava/lang/String;
        //   208: aload_0        
        //   209: getstatic       com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData$CertificateProvided.CHAIN:Lcom/netflix/msl/entityauth/x509/NetflixX509AuthenticationData$CertificateProvided;
        //   212: putfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.certType:Lcom/netflix/msl/entityauth/x509/NetflixX509AuthenticationData$CertificateProvided;
        //   215: aload_2        
        //   216: astore_1       
        //   217: goto            63
        //   220: new             Lcom/netflix/msl/MslEncodingException;
        //   223: dup            
        //   224: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   227: new             Ljava/lang/StringBuilder;
        //   230: dup            
        //   231: invokespecial   java/lang/StringBuilder.<init>:()V
        //   234: ldc             "No x509certificate and no x509chain key found in X.509 authdata JSON object "
        //   236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   239: aload_1        
        //   240: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   246: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   249: aconst_null    
        //   250: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   253: athrow         
        //   254: astore_2       
        //   255: new             Lcom/netflix/msl/MslEncodingException;
        //   258: dup            
        //   259: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   262: new             Ljava/lang/StringBuilder;
        //   265: dup            
        //   266: invokespecial   java/lang/StringBuilder.<init>:()V
        //   269: ldc             "X.509 authdata "
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: aload_1        
        //   275: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   284: aload_2        
        //   285: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   288: athrow         
        //   289: aload_0        
        //   290: getfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.certs:Ljava/util/List;
        //   293: invokeinterface java/util/List.isEmpty:()Z
        //   298: ifeq            314
        //   301: new             Lcom/netflix/msl/MslCryptoException;
        //   304: dup            
        //   305: getstatic       com/netflix/msl/MslError.X509CERT_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   308: aload_1        
        //   309: aconst_null    
        //   310: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   313: athrow         
        //   314: aload_0        
        //   315: getfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.identity:Ljava/lang/String;
        //   318: ifnonnull       335
        //   321: aload_0        
        //   322: aload_0        
        //   323: invokevirtual   com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.getX509Cert:()Ljava/security/cert/X509Certificate;
        //   326: invokevirtual   java/security/cert/X509Certificate.getSubjectX500Principal:()Ljavax/security/auth/x500/X500Principal;
        //   329: invokevirtual   javax/security/auth/x500/X500Principal.getName:()Ljava/lang/String;
        //   332: putfield        com/netflix/msl/entityauth/x509/NetflixX509AuthenticationData.identity:Ljava/lang/String;
        //   335: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  7      13     156    168    Ljava/security/cert/CertificateException;
        //  13     61     254    289    Lcom/netflix/android/org/json/JSONException;
        //  63     96     142    156    Ljava/security/cert/CertificateException;
        //  96     125    128    142    Ljava/io/IOException;
        //  96     125    142    156    Ljava/security/cert/CertificateException;
        //  129    142    142    156    Ljava/security/cert/CertificateException;
        //  168    215    254    289    Lcom/netflix/android/org/json/JSONException;
        //  220    254    254    289    Lcom/netflix/android/org/json/JSONException;
        //  289    314    142    156    Ljava/security/cert/CertificateException;
        //  314    335    142    156    Ljava/security/cert/CertificateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    
    NetflixX509AuthenticationData(final X509Certificate x509Certificate) {
        super(EntityAuthenticationScheme.X509);
        this.certs = Collections.singletonList(x509Certificate);
        this.identity = x509Certificate.getSubjectX500Principal().getName();
        this.certType = NetflixX509AuthenticationData$CertificateProvided.SINGLE;
    }
    
    public NetflixX509AuthenticationData(final Collection<X509Certificate> collection, final String identity) {
        super(EntityAuthenticationScheme.X509);
        final ArrayList<Object> certs = new ArrayList<Object>();
        certs.addAll(collection);
        this.certs = (List<X509Certificate>)certs;
        this.identity = identity;
        this.certType = NetflixX509AuthenticationData$CertificateProvided.CHAIN;
    }
    
    public static void pemEncodeCert(final boolean b, final StringBuilder sb, final byte[] array) {
        sb.append("-----BEGIN CERTIFICATE-----");
        if (b) {
            sb.append("\n");
        }
        final String encode = Base64.encode(array);
        if (b) {
            sb.append(encode.replaceAll("(.{64})", "$1\n"));
            sb.append("\n");
        }
        else {
            sb.append(encode);
        }
        sb.append("-----END CERTIFICATE-----");
        if (b) {
            sb.append("\n");
        }
    }
    
    static String pemEncodeCerts(final List<X509Certificate> list, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<X509Certificate> iterator = list.iterator();
        while (iterator.hasNext()) {
            pemEncodeCert(b, sb, iterator.next().getEncoded());
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NetflixX509AuthenticationData)) {
            return false;
        }
        final NetflixX509AuthenticationData netflixX509AuthenticationData = (NetflixX509AuthenticationData)o;
        return super.equals(o) && this.identity.equals(netflixX509AuthenticationData.identity);
    }
    
    @Override
    public JSONObject getAuthData() {
        final JSONObject jsonObject = new JSONObject();
        try {
            if (this.certType == NetflixX509AuthenticationData$CertificateProvided.SINGLE) {
                jsonObject.put("x509certificate", Base64.encode(this.getX509Cert().getEncoded()));
                return jsonObject;
            }
            if (this.certType == NetflixX509AuthenticationData$CertificateProvided.CHAIN) {
                jsonObject.put("x509chain", pemEncodeCerts(this.certs, false));
                jsonObject.put("identity", this.identity);
                return jsonObject;
            }
            goto Label_0090;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "X.509 authdata", ex);
        }
        catch (CertificateEncodingException ex2) {
            throw new MslEncodingException(MslError.X509CERT_ENCODE_ERROR, "X.509 authdata", ex2);
        }
    }
    
    public String getCertType() {
        return this.certType.name();
    }
    
    @Override
    public String getIdentity() {
        return this.identity;
    }
    
    public int getSize() {
        return this.certs.size();
    }
    
    public X509Certificate getX509Cert() {
        return this.certs.iterator().next();
    }
    
    public List<X509Certificate> getX509Certs() {
        return this.certs;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.identity.hashCode();
    }
}
