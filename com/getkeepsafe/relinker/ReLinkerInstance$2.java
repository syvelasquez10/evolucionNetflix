// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker;

import java.io.File;
import java.io.FilenameFilter;

class ReLinkerInstance$2 implements FilenameFilter
{
    final /* synthetic */ ReLinkerInstance this$0;
    final /* synthetic */ String val$mappedLibraryName;
    
    ReLinkerInstance$2(final ReLinkerInstance this$0, final String val$mappedLibraryName) {
        this.this$0 = this$0;
        this.val$mappedLibraryName = val$mappedLibraryName;
    }
    
    @Override
    public boolean accept(final File file, final String s) {
        return s.startsWith(this.val$mappedLibraryName);
    }
}
