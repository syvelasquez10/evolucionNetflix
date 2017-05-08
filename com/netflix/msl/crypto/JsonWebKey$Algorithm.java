// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslInternalException;

public enum JsonWebKey$Algorithm
{
    A128CBC("A128CBC"), 
    A128KW("A128KW"), 
    HS256("HS256"), 
    RSA1_5("RSA1_5"), 
    RSA_OAEP("RSA-OAEP");
    
    private final String name;
    
    private JsonWebKey$Algorithm(final String name) {
        this.name = name;
    }
    
    public static JsonWebKey$Algorithm fromString(final String s) {
        final JsonWebKey$Algorithm[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final JsonWebKey$Algorithm jsonWebKey$Algorithm = values[i];
            if (jsonWebKey$Algorithm.toString().equals(s)) {
                return jsonWebKey$Algorithm;
            }
        }
        throw new IllegalArgumentException("Algorithm " + s + " is unknown.");
    }
    
    public String getJcaAlgorithmName() {
        switch (JsonWebKey$1.$SwitchMap$com$netflix$msl$crypto$JsonWebKey$Algorithm[this.ordinal()]) {
            default: {
                throw new MslInternalException("No JCA standard algorithm name defined for " + this + ".");
            }
            case 1: {
                return "HmacSHA256";
            }
            case 2:
            case 3: {
                return "RSA";
            }
            case 4:
            case 5: {
                return "AES";
            }
        }
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
