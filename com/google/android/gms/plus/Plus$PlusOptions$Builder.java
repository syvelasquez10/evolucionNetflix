// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.internal.n;
import java.util.HashSet;
import java.util.Set;

public final class Plus$PlusOptions$Builder
{
    String akQ;
    final Set<String> akR;
    
    public Plus$PlusOptions$Builder() {
        this.akR = new HashSet<String>();
    }
    
    public Plus$PlusOptions$Builder addActivityTypes(final String... array) {
        n.b(array, "activityTypes may not be null.");
        for (int i = 0; i < array.length; ++i) {
            this.akR.add(array[i]);
        }
        return this;
    }
    
    public Plus$PlusOptions build() {
        return new Plus$PlusOptions(this, null);
    }
    
    public Plus$PlusOptions$Builder setServerClientId(final String akQ) {
        this.akQ = akQ;
        return this;
    }
}
