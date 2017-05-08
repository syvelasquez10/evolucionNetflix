// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.file;

import java.io.File;

public interface FileTreeVisitor
{
    void postVisitDirectory(final File p0);
    
    void preVisitDirectory(final File p0);
    
    void visitFile(final File p0);
}
