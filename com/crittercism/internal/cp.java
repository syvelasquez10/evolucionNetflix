// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public class cp extends Throwable
{
    public String b;
    
    @Override
    public String toString() {
        final String localizedMessage = this.getLocalizedMessage();
        final String b = this.b;
        if (localizedMessage == null) {
            return b;
        }
        return b + ": " + localizedMessage;
    }
}
