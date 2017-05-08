// 
// Decompiled by Procyon v0.5.30
// 

package android.support.multidex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.os.Build$VERSION;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import java.util.Arrays;
import java.lang.reflect.Array;
import android.util.Log;
import android.content.Context;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.ListIterator;
import java.lang.reflect.Field;
import dalvik.system.DexFile;
import java.util.zip.ZipFile;
import java.io.File;
import java.util.List;

final class MultiDex$V4
{
    private static void install(final ClassLoader classLoader, final List<File> list) {
        final int size = list.size();
        final Field access$300 = findField(classLoader, "path");
        final StringBuilder sb = new StringBuilder((String)access$300.get(classLoader));
        final String[] array = new String[size];
        final File[] array2 = new File[size];
        final ZipFile[] array3 = new ZipFile[size];
        final DexFile[] array4 = new DexFile[size];
        final ListIterator<File> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            final File file = listIterator.next();
            final String absolutePath = file.getAbsolutePath();
            sb.append(':').append(absolutePath);
            final int previousIndex = listIterator.previousIndex();
            array[previousIndex] = absolutePath;
            array2[previousIndex] = file;
            array3[previousIndex] = new ZipFile(file);
            array4[previousIndex] = DexFile.loadDex(absolutePath, absolutePath + ".dex", 0);
        }
        access$300.set(classLoader, sb.toString());
        expandFieldArray(classLoader, "mPaths", array);
        expandFieldArray(classLoader, "mFiles", array2);
        expandFieldArray(classLoader, "mZips", array3);
        expandFieldArray(classLoader, "mDexs", array4);
    }
}
