// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import java.io.File;
import java.util.List;
import java.io.FilenameFilter;

final class ExportDebugData$2 implements FilenameFilter
{
    final /* synthetic */ List val$files;
    
    ExportDebugData$2(final List val$files) {
        this.val$files = val$files;
    }
    
    @Override
    public boolean accept(File file, final String s) {
        file = new File(file, s);
        if (file.isFile() && file.length() < 5242880L) {
            this.val$files.add(file);
        }
        return false;
    }
}
