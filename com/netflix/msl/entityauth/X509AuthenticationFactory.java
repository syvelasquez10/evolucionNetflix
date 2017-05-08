// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.PrivateKey;
import com.netflix.msl.crypto.RsaCryptoContext;
import com.netflix.msl.crypto.RsaCryptoContext$Mode;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateExpiredException;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;

public class X509AuthenticationFactory extends EntityAuthenticationFactory
{
    private final AuthenticationUtils authutils;
    private final X509Store caStore;
    
    public X509AuthenticationFactory(final X509Store caStore, final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.X509);
        this.caStore = caStore;
        this.authutils = authutils;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new X509AuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof X509AuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        entityAuthenticationData = entityAuthenticationData;
        final X509Certificate x509Cert = ((X509AuthenticationData)entityAuthenticationData).getX509Cert();
        final String name = x509Cert.getSubjectX500Principal().getName();
        final PublicKey publicKey = x509Cert.getPublicKey();
        if (this.authutils.isEntityRevoked(name)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, x509Cert.toString()).setEntityAuthenticationData(entityAuthenticationData);
        }
        if (!this.authutils.isSchemePermitted(name, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + name + ":" + this.getScheme()).setEntityAuthenticationData(entityAuthenticationData);
        }
        try {
            if (!this.caStore.isAccepted(x509Cert)) {
                throw new MslEntityAuthException(MslError.X509CERT_VERIFICATION_FAILED, x509Cert.toString()).setEntityAuthenticationData(entityAuthenticationData);
            }
        }
        catch (CertificateExpiredException ex) {
            throw new MslEntityAuthException(MslError.X509CERT_EXPIRED, x509Cert.toString(), ex).setEntityAuthenticationData(entityAuthenticationData);
        }
        catch (CertificateNotYetValidException ex2) {
            throw new MslEntityAuthException(MslError.X509CERT_NOT_YET_VALID, x509Cert.toString(), ex2).setEntityAuthenticationData(entityAuthenticationData);
        }
        return new RsaCryptoContext(mslContext, name, null, publicKey, RsaCryptoContext$Mode.SIGN_VERIFY);
    }
}
