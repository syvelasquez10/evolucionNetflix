// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.file;

import java.io.FileNotFoundException;
import com.facebook.common.internal.Preconditions;
import java.io.File;

public class FileUtils
{
    public static void mkdirs(final File file) {
        Label_0045: {
            if (!file.exists()) {
                break Label_0045;
            }
            if (!file.isDirectory()) {
                if (!file.delete()) {
                    throw new FileUtils$CreateDirectoryException(file.getAbsolutePath(), new FileUtils$FileDeleteException(file.getAbsolutePath()));
                }
                break Label_0045;
            }
            return;
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new FileUtils$CreateDirectoryException(file.getAbsolutePath());
        }
    }
    
    public static void rename(final File file, final File file2) {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        file2.delete();
        if (file.renameTo(file2)) {
            return;
        }
        Throwable t = null;
        if (file2.exists()) {
            t = new FileUtils$FileDeleteException(file2.getAbsolutePath());
        }
        else if (!file.getParentFile().exists()) {
            t = new FileUtils$ParentDirNotFoundException(file.getAbsolutePath());
        }
        else if (!file.exists()) {
            t = new FileNotFoundException(file.getAbsolutePath());
        }
        throw new FileUtils$RenameException("Unknown error renaming " + file.getAbsolutePath() + " to " + file2.getAbsolutePath(), t);
    }
}
