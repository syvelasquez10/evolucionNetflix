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
import android.content.Context;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.lang.reflect.Field;
import java.util.Iterator;
import android.util.Log;
import java.util.Collection;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

final class MultiDex$V19
{
    private static void install(final ClassLoader classLoader, final List<File> list, final File file) {
        final Object value = findField(classLoader, "pathList").get(classLoader);
        final ArrayList<IOException> list2 = new ArrayList<IOException>();
        expandFieldArray(value, "dexElements", makeDexElements(value, new ArrayList<File>(list), file, list2));
        if (list2.size() > 0) {
            final Iterator<IOException> iterator = list2.iterator();
            while (iterator.hasNext()) {
                Log.w("MultiDex", "Exception in makeDexElement", (Throwable)iterator.next());
            }
            final Field access$300 = findField(classLoader, "dexElementsSuppressedExceptions");
            final IOException[] array = (IOException[])access$300.get(classLoader);
            IOException[] array2;
            if (array == null) {
                array2 = list2.toArray(new IOException[list2.size()]);
            }
            else {
                array2 = new IOException[list2.size() + array.length];
                list2.toArray(array2);
                System.arraycopy(array, 0, array2, list2.size(), array.length);
            }
            access$300.set(classLoader, array2);
        }
    }
    
    private static Object[] makeDexElements(final Object o, final ArrayList<File> list, final File file, final ArrayList<IOException> list2) {
        return (Object[])findMethod(o, "makeDexElements", (Class<?>[])new Class[] { ArrayList.class, File.class, ArrayList.class }).invoke(o, list, file, list2);
    }
}
