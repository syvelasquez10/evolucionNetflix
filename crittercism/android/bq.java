// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.IOException;
import java.io.File;

public class bq
{
    protected File a;
    
    public bq(final File a) {
        this.a = a;
    }
    
    public Object a() {
        try {
            return ec.b(this.a);
        }
        catch (IOException ex) {
            return "";
        }
    }
}
