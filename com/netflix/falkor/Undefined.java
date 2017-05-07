// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

public class Undefined extends Sentinel<Object>
{
    private static final Undefined instance;
    
    static {
        instance = new Undefined();
    }
    
    private Undefined() {
        super(null);
    }
    
    public static Undefined getInstance() {
        return Undefined.instance;
    }
}
