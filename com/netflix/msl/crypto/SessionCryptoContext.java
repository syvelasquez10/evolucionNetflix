// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import javax.crypto.SecretKey;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslError;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.util.MslContext;

public class SessionCryptoContext extends SymmetricCryptoContext
{
    public SessionCryptoContext(final MslContext mslContext, final MasterToken masterToken) {
        this(mslContext, masterToken, masterToken.getIdentity(), masterToken.getEncryptionKey(), masterToken.getSignatureKey());
        if (!masterToken.isDecrypted()) {
            throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, masterToken);
        }
    }
    
    public SessionCryptoContext(final MslContext mslContext, final MasterToken masterToken, final String s, final SecretKey secretKey, final SecretKey secretKey2) {
        String s2;
        if (s != null) {
            s2 = s + "_" + masterToken.getSequenceNumber();
        }
        else {
            s2 = Long.toString(masterToken.getSequenceNumber());
        }
        super(mslContext, s2, secretKey, secretKey2, null);
    }
}
