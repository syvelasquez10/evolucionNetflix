// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public final class Plus$PlusOptions implements Api$ApiOptions$Optional
{
    final String akQ;
    final Set<String> akR;
    
    private Plus$PlusOptions() {
        this.akQ = null;
        this.akR = new HashSet<String>();
    }
    
    private Plus$PlusOptions(final Plus$PlusOptions$Builder plus$PlusOptions$Builder) {
        this.akQ = plus$PlusOptions$Builder.akQ;
        this.akR = plus$PlusOptions$Builder.akR;
    }
    
    public static Plus$PlusOptions$Builder builder() {
        return new Plus$PlusOptions$Builder();
    }
}
