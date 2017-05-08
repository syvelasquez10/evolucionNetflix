// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

enum JsonWebEncryptionCryptoContext$Algorithm
{
    A128KW("A128KW"), 
    RSA_OAEP("RSA-OAEP");
    
    private final String name;
    
    private JsonWebEncryptionCryptoContext$Algorithm(final String name) {
        this.name = name;
    }
    
    public static JsonWebEncryptionCryptoContext$Algorithm fromString(final String s) {
        final JsonWebEncryptionCryptoContext$Algorithm[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final JsonWebEncryptionCryptoContext$Algorithm jsonWebEncryptionCryptoContext$Algorithm = values[i];
            if (jsonWebEncryptionCryptoContext$Algorithm.toString().equals(s)) {
                return jsonWebEncryptionCryptoContext$Algorithm;
            }
        }
        throw new IllegalArgumentException("Algorithm " + s + " is unknown.");
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
