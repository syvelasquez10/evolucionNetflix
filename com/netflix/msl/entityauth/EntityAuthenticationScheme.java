// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntityAuthenticationScheme
{
    public static final EntityAuthenticationScheme MT_PROTECTED;
    public static final EntityAuthenticationScheme NONE;
    public static final EntityAuthenticationScheme NONE_SUFFIXED;
    public static final EntityAuthenticationScheme PROVISIONED;
    public static final EntityAuthenticationScheme PSK;
    public static final EntityAuthenticationScheme PSK_PROFILE;
    public static final EntityAuthenticationScheme RSA;
    public static final EntityAuthenticationScheme X509;
    private static Map<String, EntityAuthenticationScheme> schemes;
    private final boolean encrypts;
    private final String name;
    private final boolean protects;
    
    static {
        EntityAuthenticationScheme.schemes = new HashMap<String, EntityAuthenticationScheme>();
        PSK = new EntityAuthenticationScheme("PSK", true, true);
        PSK_PROFILE = new EntityAuthenticationScheme("PSK_PROFILE", true, true);
        X509 = new EntityAuthenticationScheme("X509", false, true);
        RSA = new EntityAuthenticationScheme("RSA", false, true);
        NONE = new EntityAuthenticationScheme("NONE", false, false);
        NONE_SUFFIXED = new EntityAuthenticationScheme("NONE_SUFFIXED", false, false);
        MT_PROTECTED = new EntityAuthenticationScheme("MT_PROTECTED", false, false);
        PROVISIONED = new EntityAuthenticationScheme("PROVISIONED", false, false);
    }
    
    protected EntityAuthenticationScheme(final String name, final boolean encrypts, final boolean protects) {
        this.name = name;
        this.encrypts = encrypts;
        this.protects = protects;
        synchronized (EntityAuthenticationScheme.schemes) {
            EntityAuthenticationScheme.schemes.put(name, this);
        }
    }
    
    public static EntityAuthenticationScheme getScheme(final String s) {
        return EntityAuthenticationScheme.schemes.get(s);
    }
    
    public static Collection<EntityAuthenticationScheme> values() {
        return EntityAuthenticationScheme.schemes.values();
    }
    
    public boolean encrypts() {
        return this.encrypts;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof EntityAuthenticationScheme && this.name.equals(((EntityAuthenticationScheme)o).name));
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public String name() {
        return this.name;
    }
    
    public boolean protectsIntegrity() {
        return this.protects;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
}
