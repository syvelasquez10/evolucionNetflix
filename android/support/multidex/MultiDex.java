// 
// Decompiled by Procyon v0.5.30
// 

package android.support.multidex;

import java.util.ListIterator;
import dalvik.system.DexFile;
import java.util.zip.ZipFile;
import java.util.Collection;
import java.io.IOException;
import java.util.ArrayList;
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
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.io.File;
import java.util.Set;

public final class MultiDex
{
    private static final boolean IS_VM_MULTIDEX_CAPABLE;
    private static final String SECONDARY_FOLDER_NAME;
    private static final Set<String> installedApk;
    
    static {
        SECONDARY_FOLDER_NAME = "code_cache" + File.separator + "secondary-dexes";
        installedApk = new HashSet<String>();
        IS_VM_MULTIDEX_CAPABLE = isVMMultidexCapable(System.getProperty("java.vm.version"));
    }
    
    private static boolean checkValidZipFiles(final List<File> list) {
        final Iterator<File> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!MultiDexExtractor.verifyZipFile(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    private static void clearOldDexDir(final Context context) {
        final File file = new File(context.getFilesDir(), "secondary-dexes");
        if (file.isDirectory()) {
            Log.i("MultiDex", "Clearing old secondary dex dir (" + file.getPath() + ").");
            final File[] listFiles = file.listFiles();
            if (listFiles == null) {
                Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
            }
            else {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    final File file2 = listFiles[i];
                    Log.i("MultiDex", "Trying to delete old file " + file2.getPath() + " of size " + file2.length());
                    if (!file2.delete()) {
                        Log.w("MultiDex", "Failed to delete old file " + file2.getPath());
                    }
                    else {
                        Log.i("MultiDex", "Deleted old file " + file2.getPath());
                    }
                }
                if (!file.delete()) {
                    Log.w("MultiDex", "Failed to delete secondary dex dir " + file.getPath());
                    return;
                }
                Log.i("MultiDex", "Deleted old secondary dex dir " + file.getPath());
            }
        }
    }
    
    private static void expandFieldArray(final Object o, final String s, final Object[] array) {
        final Field field = findField(o, s);
        final Object[] array2 = (Object[])field.get(o);
        final Object[] array3 = (Object[])Array.newInstance(array2.getClass().getComponentType(), array2.length + array.length);
        System.arraycopy(array2, 0, array3, 0, array2.length);
        System.arraycopy(array, 0, array3, array2.length, array.length);
        field.set(o, array3);
    }
    
    private static Field findField(final Object o, final String s) {
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            try {
                final Field declaredField = clazz.getDeclaredField(s);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            }
            catch (NoSuchFieldException ex) {
                clazz = clazz.getSuperclass();
                continue;
            }
            break;
        }
        throw new NoSuchFieldException("Field " + s + " not found in " + o.getClass());
    }
    
    private static Method findMethod(final Object o, final String s, final Class<?>... array) {
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            try {
                final Method declaredMethod = clazz.getDeclaredMethod(s, array);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            }
            catch (NoSuchMethodException ex) {
                clazz = clazz.getSuperclass();
                continue;
            }
            break;
        }
        throw new NoSuchMethodException("Method " + s + " with parameters " + Arrays.asList(array) + " not found in " + o.getClass());
    }
    
    private static ApplicationInfo getApplicationInfo(final Context context) {
        PackageManager packageManager;
        String packageName;
        try {
            packageManager = context.getPackageManager();
            packageName = context.getPackageName();
            if (packageManager == null || packageName == null) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", (Throwable)ex);
            return null;
        }
        return packageManager.getApplicationInfo(packageName, 128);
    }
    
    public static void install(Context load) {
        Log.i("MultiDex", "install");
        if (MultiDex.IS_VM_MULTIDEX_CAPABLE) {
            Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
        }
        else {
            if (Build$VERSION.SDK_INT < 4) {
                throw new RuntimeException("Multi dex installation failed. SDK " + Build$VERSION.SDK_INT + " is unsupported. Min SDK version is " + 4 + ".");
            }
            ApplicationInfo applicationInfo;
            String sourceDir;
            try {
                applicationInfo = getApplicationInfo(load);
                if (applicationInfo == null) {
                    return;
                }
                synchronized (MultiDex.installedApk) {
                    sourceDir = applicationInfo.sourceDir;
                    if (MultiDex.installedApk.contains(sourceDir)) {
                        return;
                    }
                }
            }
            catch (Exception ex) {
                Log.e("MultiDex", "Multidex installation failure", (Throwable)ex);
                throw new RuntimeException("Multi dex installation failed (" + ex.getMessage() + ").");
            }
            MultiDex.installedApk.add(sourceDir);
            if (Build$VERSION.SDK_INT > 20) {
                Log.w("MultiDex", "MultiDex is not guaranteed to work in SDK version " + Build$VERSION.SDK_INT + ": SDK version higher than " + 20 + " should be backed by " + "runtime with built-in multidex capabilty but it's not the " + "case here: java.vm.version=\"" + System.getProperty("java.vm.version") + "\"");
            }
            ClassLoader classLoader;
            try {
                classLoader = load.getClassLoader();
                if (classLoader == null) {
                    Log.e("MultiDex", "Context class loader is null. Must be running in test mode. Skip patching.");
                    // monitorexit(set)
                    return;
                }
            }
            catch (RuntimeException ex2) {
                Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", (Throwable)ex2);
                // monitorexit(set)
                return;
            }
        Label_0335_Outer:
            while (true) {
                while (true) {
                    File file;
                    while (true) {
                        try {
                            clearOldDexDir(load);
                            file = new File(applicationInfo.dataDir, MultiDex.SECONDARY_FOLDER_NAME);
                            final List<File> load2 = MultiDexExtractor.load(load, applicationInfo, file, false);
                            if (checkValidZipFiles(load2)) {
                                installSecondaryDexes(classLoader, file, load2);
                                // monitorexit(set)
                                Log.i("MultiDex", "install done");
                                return;
                            }
                        }
                        catch (Throwable t) {
                            Log.w("MultiDex", "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", t);
                            continue Label_0335_Outer;
                        }
                        break;
                    }
                    Log.w("MultiDex", "Files were not valid zip files.  Forcing a reload.");
                    load = (Context)MultiDexExtractor.load(load, applicationInfo, file, true);
                    if (checkValidZipFiles((List<File>)load)) {
                        installSecondaryDexes(classLoader, file, (List<File>)load);
                        continue;
                    }
                    break;
                }
                break;
            }
            throw new RuntimeException("Zip files were not valid.");
        }
    }
    
    private static void installSecondaryDexes(final ClassLoader classLoader, final File file, final List<File> list) {
        if (!list.isEmpty()) {
            if (Build$VERSION.SDK_INT >= 19) {
                install(classLoader, list, file);
            }
            else {
                if (Build$VERSION.SDK_INT >= 14) {
                    install(classLoader, list, file);
                    return;
                }
                install(classLoader, list);
            }
        }
    }
    
    static boolean isVMMultidexCapable(String s) {
        boolean b2;
        final boolean b = b2 = false;
        while (true) {
            if (s == null) {
                break Label_0076;
            }
            final Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(s);
            b2 = b;
            if (!matcher.matches()) {
                break Label_0076;
            }
            try {
                final int int1 = Integer.parseInt(matcher.group(1));
                final int int2 = Integer.parseInt(matcher.group(2));
                if (int1 <= 2) {
                    b2 = b;
                    if (int1 != 2) {
                        break Label_0076;
                    }
                    b2 = b;
                    if (int2 < 1) {
                        break Label_0076;
                    }
                }
                b2 = true;
                final StringBuilder append = new StringBuilder().append("VM with version ").append(s);
                if (b2) {
                    s = " has multidex support";
                }
                else {
                    s = " does not have multidex support";
                }
                Log.i("MultiDex", append.append(s).toString());
                return b2;
            }
            catch (NumberFormatException ex) {
                b2 = b;
                continue;
            }
            break;
        }
    }
}
