// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.file;

import java.io.File;

public class FileTree
{
    public static boolean deleteContents(final File file) {
        final File[] listFiles = file.listFiles();
        boolean b = true;
        boolean b2 = true;
        if (listFiles != null) {
            final int length = listFiles.length;
            int n = 0;
            while (true) {
                b = b2;
                if (n >= length) {
                    break;
                }
                final boolean deleteRecursively = deleteRecursively(listFiles[n]);
                ++n;
                b2 &= deleteRecursively;
            }
        }
        return b;
    }
    
    public static boolean deleteRecursively(final File file) {
        if (file.isDirectory()) {
            deleteContents(file);
        }
        return file.delete();
    }
    
    public static void walkFileTree(final File file, final FileTreeVisitor fileTreeVisitor) {
        fileTreeVisitor.preVisitDirectory(file);
        final File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                final File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    walkFileTree(file2, fileTreeVisitor);
                }
                else {
                    fileTreeVisitor.visitFile(file2);
                }
            }
        }
        fileTreeVisitor.postVisitDirectory(file);
    }
}
