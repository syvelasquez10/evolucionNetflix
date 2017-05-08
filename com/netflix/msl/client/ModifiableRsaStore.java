// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import java.io.InputStream;
import java.security.PrivateKey;
import com.netflix.msl.entityauth.RsaStore;

public interface ModifiableRsaStore extends RsaStore
{
    void addPrivateKey(final String p0, final PrivateKey p1);
    
    void addPublicKey(final String p0, final InputStream p1);
    
    void addPublicKey(final String p0, final String p1);
}
