// 
// Decompiled by Procyon v0.5.30
// 

package android.support.multidex;

import java.io.File;
import java.io.FileFilter;

final class MultiDexExtractor$1 implements FileFilter
{
    final /* synthetic */ String val$extractedFilePrefix;
    
    MultiDexExtractor$1(final String val$extractedFilePrefix) {
        this.val$extractedFilePrefix = val$extractedFilePrefix;
    }
    
    @Override
    public boolean accept(final File file) {
        return !file.getName().startsWith(this.val$extractedFilePrefix);
    }
}
