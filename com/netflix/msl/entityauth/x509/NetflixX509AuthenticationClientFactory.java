// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth.x509;

import com.netflix.msl.MslInternalException;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.crypto.EccCryptoContext;
import com.netflix.msl.crypto.EccCryptoContext$Mode;
import com.netflix.msl.crypto.RsaCryptoContext;
import com.netflix.msl.crypto.RsaCryptoContext$Mode;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.crypto.ICryptoContext;
import java.security.cert.X509Certificate;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import java.security.PrivateKey;
import com.netflix.msl.util.AuthenticationUtils;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;

public class NetflixX509AuthenticationClientFactory extends EntityAuthenticationFactory
{
    private final AuthenticationUtils authutils;
    PrivateKey privateKey;
    
    public NetflixX509AuthenticationClientFactory(final PrivateKey privateKey, final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.X509);
        this.authutils = authutils;
        this.privateKey = privateKey;
    }
    
    private ICryptoContext createCryptoContext(final MslContext mslContext, final String s, final PrivateKey privateKey, final X509Certificate x509Certificate) {
        final String sigAlgName = x509Certificate.getSigAlgName();
        switch (sigAlgName) {
            default: {
                throw new MslEntityAuthException(MslError.UNIDENTIFIED_ALGORITHM, "Signature Algorithm Name Not Suported: " + x509Certificate.getSigAlgName());
            }
            case "SHA1withRSA":
            case "SHA256withRSA": {
                return new RsaCryptoContext(mslContext, s, privateKey, x509Certificate.getPublicKey(), RsaCryptoContext$Mode.SIGN_VERIFY);
            }
            case "SHA1withECDSA":
            case "SHA256withECDSA": {
                return new EccCryptoContext(s, privateKey, x509Certificate.getPublicKey(), EccCryptoContext$Mode.SIGN_VERIFY);
            }
        }
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new NetflixX509AuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof NetflixX509AuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        final NetflixX509AuthenticationData netflixX509AuthenticationData = (NetflixX509AuthenticationData)entityAuthenticationData;
        final String identity = netflixX509AuthenticationData.getIdentity();
        if (this.authutils.isEntityRevoked(identity)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, netflixX509AuthenticationData.getX509Cert().toString()).setEntityAuthenticationData(netflixX509AuthenticationData);
        }
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme()).setEntityAuthenticationData(netflixX509AuthenticationData);
        }
        return this.createCryptoContext(mslContext, identity, this.privateKey, netflixX509AuthenticationData.getX509Cert());
    }
}
