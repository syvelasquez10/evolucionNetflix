// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

public interface ICryptoContext
{
    byte[] decrypt(final byte[] p0);
    
    byte[] encrypt(final byte[] p0);
    
    byte[] sign(final byte[] p0);
    
    byte[] unwrap(final byte[] p0);
    
    boolean verify(final byte[] p0, final byte[] p1);
    
    byte[] wrap(final byte[] p0);
}
