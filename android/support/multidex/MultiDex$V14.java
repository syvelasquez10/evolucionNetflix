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
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

final class MultiDex$V14
{
    private static void install(final ClassLoader classLoader, final List<File> list, final File file) {
        final Object value = findField(classLoader, "pathList").get(classLoader);
        expandFieldArray(value, "dexElements", makeDexElements(value, new ArrayList<File>(list), file));
    }
    
    private static Object[] makeDexElements(final Object o, final ArrayList<File> list, final File file) {
        return (Object[])findMethod(o, "makeDexElements", (Class<?>[])new Class[] { ArrayList.class, File.class }).invoke(o, list, file);
    }
}
