// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class KeyExchangeScheme
{
    public static final KeyExchangeScheme ASYMMETRIC_WRAPPED;
    public static final KeyExchangeScheme DIFFIE_HELLMAN;
    public static final KeyExchangeScheme JWE_LADDER;
    public static final KeyExchangeScheme JWK_LADDER;
    public static final KeyExchangeScheme SYMMETRIC_WRAPPED;
    private static Map<String, KeyExchangeScheme> schemes;
    private final String name;
    
    static {
        KeyExchangeScheme.schemes = new HashMap<String, KeyExchangeScheme>();
        ASYMMETRIC_WRAPPED = new KeyExchangeScheme("ASYMMETRIC_WRAPPED");
        DIFFIE_HELLMAN = new KeyExchangeScheme("DIFFIE_HELLMAN");
        JWE_LADDER = new KeyExchangeScheme("JWE_LADDER");
        JWK_LADDER = new KeyExchangeScheme("JWK_LADDER");
        SYMMETRIC_WRAPPED = new KeyExchangeScheme("SYMMETRIC_WRAPPED");
    }
    
    protected KeyExchangeScheme(final String name) {
        this.name = name;
        synchronized (KeyExchangeScheme.schemes) {
            KeyExchangeScheme.schemes.put(name, this);
        }
    }
    
    public static KeyExchangeScheme getScheme(final String s) {
        return KeyExchangeScheme.schemes.get(s);
    }
    
    public static Collection<KeyExchangeScheme> values() {
        return KeyExchangeScheme.schemes.values();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof KeyExchangeScheme && this.name.equals(((KeyExchangeScheme)o).name));
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public String name() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
}
