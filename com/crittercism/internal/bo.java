// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.IOException;
import java.io.File;

public class bo
{
    protected File a;
    
    public bo(final File a) {
        this.a = a;
    }
    
    public Object a() {
        try {
            return dz.b(this.a);
        }
        catch (IOException ex) {
            return "";
        }
    }
}
