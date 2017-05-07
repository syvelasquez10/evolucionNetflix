// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.File;
import java.io.FilenameFilter;

public final class v$a implements FilenameFilter
{
    private String a;
    
    public v$a(final String a) {
        this.a = new String();
        if (a != null) {
            this.a = a;
        }
    }
    
    @Override
    public final boolean accept(final File file, final String s) {
        return s.endsWith(this.a);
    }
}
