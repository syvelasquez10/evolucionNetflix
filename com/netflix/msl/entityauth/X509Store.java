// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import java.security.cert.CertificateFactory;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import com.netflix.msl.util.Base64;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import java.util.Map;

public class X509Store
{
    private final Map<X500Principal, List<X509Certificate>> store;
    
    public X509Store() {
        this.store = new HashMap<X500Principal, List<X509Certificate>>();
    }
    
    private X509Certificate getIssuer(final X509Certificate x509Certificate) {
        final List<X509Certificate> list = this.store.get(x509Certificate.getIssuerX500Principal());
        if (list == null) {
            return null;
        }
        for (final X509Certificate x509Certificate2 : list) {
            try {
                x509Certificate.verify(x509Certificate2.getPublicKey());
                return x509Certificate2;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
        return null;
    }
    
    private List<X509Certificate> getIssuerChain(X509Certificate x509Certificate) {
        final ArrayList<X509Certificate> list = new ArrayList<X509Certificate>();
        while (true) {
            final X509Certificate issuer = this.getIssuer(x509Certificate);
            if (issuer == null) {
                throw new CertificateException("No issuer found for certificate: " + Base64.encode(x509Certificate.getEncoded()));
            }
            list.add(0, issuer);
            if (isSelfSigned(issuer)) {
                return list;
            }
            x509Certificate = issuer;
        }
    }
    
    private boolean isPermittedByIssuer(final X509Certificate x509Certificate) {
        final List<X509Certificate> issuerChain = this.getIssuerChain(x509Certificate);
        if (issuerChain.isEmpty()) {
            return false;
        }
        final Iterator<X509Certificate> iterator = issuerChain.iterator();
        int n = -1;
        while (iterator.hasNext()) {
            final int basicConstraints = iterator.next().getBasicConstraints();
            int n2;
            if (basicConstraints == -1) {
                if (n == -1) {
                    return false;
                }
                n2 = n - 1;
            }
            else {
                n2 = basicConstraints;
                if (n != -1 && (n2 = basicConstraints) > n) {
                    return false;
                }
            }
            if (n2 == 0) {
                return false;
            }
            n = n2;
        }
        final int basicConstraints2 = x509Certificate.getBasicConstraints();
        return basicConstraints2 == -1 || basicConstraints2 <= n;
    }
    
    private static boolean isSelfSigned(final X509Certificate x509Certificate) {
        return x509Certificate.getSubjectX500Principal().equals(x509Certificate.getIssuerX500Principal());
    }
    
    private boolean isVerified(final X509Certificate x509Certificate) {
        return this.getIssuer(x509Certificate) != null;
    }
    
    public void addTrusted(final InputStream inputStream) {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        final CertificateFactory instance = CertificateFactory.getInstance("X.509");
        while (bufferedInputStream.available() > 0) {
            this.addTrusted((X509Certificate)instance.generateCertificate(bufferedInputStream));
        }
    }
    
    public void addTrusted(final X509Certificate x509Certificate) {
        x509Certificate.checkValidity();
        if (x509Certificate.getBasicConstraints() < 0) {
            throw new CertificateException("Certificate is not a CA certificate: " + Base64.encode(x509Certificate.getEncoded()));
        }
        if (isSelfSigned(x509Certificate)) {
            x509Certificate.verify(x509Certificate.getPublicKey());
        }
        else {
            if (!this.isVerified(x509Certificate)) {
                throw new CertificateException("Certificate is not self-signed and not trusted by any known CA certificate: " + Base64.encode(x509Certificate.getEncoded()));
            }
            if (!this.isPermittedByIssuer(x509Certificate)) {
                throw new CertificateException("Certificate appears too far from its issuing CA certificate: " + Base64.encode(x509Certificate.getEncoded()));
            }
        }
        final X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
        if (!this.store.containsKey(subjectX500Principal)) {
            this.store.put(subjectX500Principal, new ArrayList<X509Certificate>());
        }
        final List<X509Certificate> list = this.store.get(subjectX500Principal);
        if (!list.contains(x509Certificate)) {
            list.add(x509Certificate);
        }
    }
    
    public void addTrusted(final List<X509Certificate> list) {
        if (list != null && !list.isEmpty()) {
            X509Certificate x509Certificate = list.get(0);
            if (!isSelfSigned(x509Certificate)) {
                throw new CertificateException("First certificate is not self-signed: " + Base64.encode(x509Certificate.getEncoded()));
            }
            this.addTrusted(x509Certificate);
            X509Certificate x509Certificate2;
            for (int i = 1; i < list.size(); ++i, x509Certificate = x509Certificate2) {
                x509Certificate2 = list.get(i);
                x509Certificate2.verify(x509Certificate.getPublicKey());
                this.addTrusted(x509Certificate2);
            }
        }
    }
    
    public boolean isAccepted(final X509Certificate x509Certificate) {
        x509Certificate.checkValidity();
        return this.isVerified(x509Certificate);
    }
}
