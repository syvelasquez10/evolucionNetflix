// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.Set;

public interface RsaStore
{
    Set<String> getIdentities();
    
    PrivateKey getPrivateKey(final String p0);
    
    PublicKey getPublicKey(final String p0);
}
