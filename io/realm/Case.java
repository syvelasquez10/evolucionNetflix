// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

public enum Case
{
    INSENSITIVE(false), 
    SENSITIVE(true);
    
    private final boolean value;
    
    private Case(final boolean value) {
        this.value = value;
    }
    
    public boolean getValue() {
        return this.value;
    }
}
