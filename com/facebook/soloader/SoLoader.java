// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import android.content.pm.ApplicationInfo;
import java.io.FileNotFoundException;
import android.os.Build$VERSION;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import android.os.StrictMode;
import android.content.Context;
import java.util.HashSet;
import android.os.StrictMode$ThreadPolicy;
import java.util.Set;

public class SoLoader
{
    private static String SO_STORE_NAME_MAIN;
    private static int sFlags;
    private static final Set<String> sLoadedLibraries;
    private static StrictMode$ThreadPolicy sOldPolicy;
    private static SoSource[] sSoSources;
    
    static {
        SoLoader.sSoSources = null;
        sLoadedLibraries = new HashSet<String>();
        SoLoader.sOldPolicy = null;
        SoLoader.SO_STORE_NAME_MAIN = "lib-main";
    }
    
    private static void assertInitialized() {
        if (SoLoader.sSoSources == null) {
            throw new RuntimeException("SoLoader.init() not yet called");
        }
    }
    
    public static void init(final Context context, final int n) {
        final StrictMode$ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            initImpl(context, n);
        }
        finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }
    
    public static void init(final Context context, final boolean b) {
        Label_0012: {
            if (!b) {
                break Label_0012;
            }
            int n = 1;
            try {
                while (true) {
                    init(context, n);
                    return;
                    n = 0;
                    continue;
                }
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    private static void initImpl(final Context context, int length) {
    Label_0201_Outer:
        while (true) {
            final int n = 0;
            int prepareFlags = 0;
            final Context sSoSources;
            Label_0277: {
                while (true) {
                    final ArrayList<ApkSoSource> list;
                    Object o = null;
                    Label_0285: {
                        Label_0228: {
                            synchronized (SoLoader.class) {
                                if (SoLoader.sSoSources != null) {
                                    break;
                                }
                                SoLoader.sFlags = length;
                                list = new ArrayList<ApkSoSource>();
                                if ((o = System.getenv("LD_LIBRARY_PATH")) == null) {
                                    o = "/vendor/lib:/system/lib";
                                }
                                o = ((String)o).split(":");
                                for (int i = 0; i < ((ApplicationInfo)o).length; ++i) {
                                    list.add((ApkSoSource)new DirectorySoSource(new File(o[i]), 2));
                                }
                                if (context != null) {
                                    if ((length & 0x1) != 0x0) {
                                        list.add(0, (ApkSoSource)new ExoSoSource(context, SoLoader.SO_STORE_NAME_MAIN));
                                    }
                                    else {
                                        o = context.getApplicationInfo();
                                        if ((((ApplicationInfo)o).flags & 0x1) != 0x0 && (((ApplicationInfo)o).flags & 0x80) == 0x0) {
                                            length = 1;
                                            break Label_0285;
                                        }
                                        break Label_0228;
                                    }
                                }
                                while (true) {
                                    final SoSource[] array = list.toArray(new SoSource[list.size()]);
                                    prepareFlags = makePrepareFlags();
                                    length = array.length;
                                    while (true) {
                                        final int n2 = length - 1;
                                        if (length <= 0) {
                                            break Label_0277;
                                        }
                                        array[n2].prepare(prepareFlags);
                                        length = n2;
                                    }
                                    list.add(0, new ApkSoSource(sSoSources, SoLoader.SO_STORE_NAME_MAIN, length));
                                    continue Label_0201_Outer;
                                }
                            }
                        }
                        length = 0;
                    }
                    if (length != 0) {
                        length = prepareFlags;
                        continue;
                    }
                    length = n;
                    if (Build$VERSION.SDK_INT <= 17) {
                        length = 1;
                    }
                    list.add(0, (ApkSoSource)new DirectorySoSource(new File(((ApplicationInfo)o).nativeLibraryDir), length));
                    length = 1;
                    continue;
                }
            }
            SoLoader.sSoSources = (SoSource[])(Object)sSoSources;
            break;
        }
    }
    // monitorexit(SoLoader.class)
    
    public static void loadLibrary(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             Lcom/facebook/soloader/SoLoader;.class
        //     2: monitorenter   
        //     3: getstatic       com/facebook/soloader/SoLoader.sSoSources:[Lcom/facebook/soloader/SoSource;
        //     6: ifnonnull       25
        //     9: ldc             "http://www.android.com/"
        //    11: ldc             "java.vendor.url"
        //    13: invokestatic    java/lang/System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //    16: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    19: ifeq            37
        //    22: invokestatic    com/facebook/soloader/SoLoader.assertInitialized:()V
        //    25: aload_0        
        //    26: invokestatic    java/lang/System.mapLibraryName:(Ljava/lang/String;)Ljava/lang/String;
        //    29: iconst_0       
        //    30: invokestatic    com/facebook/soloader/SoLoader.loadLibraryBySoName:(Ljava/lang/String;I)V
        //    33: ldc             Lcom/facebook/soloader/SoLoader;.class
        //    35: monitorexit    
        //    36: return         
        //    37: aload_0        
        //    38: invokestatic    java/lang/System.loadLibrary:(Ljava/lang/String;)V
        //    41: goto            33
        //    44: astore_0       
        //    45: ldc             Lcom/facebook/soloader/SoLoader;.class
        //    47: monitorexit    
        //    48: aload_0        
        //    49: athrow         
        //    50: astore_0       
        //    51: new             Ljava/lang/RuntimeException;
        //    54: dup            
        //    55: aload_0        
        //    56: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    59: athrow         
        //    60: astore_0       
        //    61: aload_0        
        //    62: invokevirtual   java/lang/UnsatisfiedLinkError.getMessage:()Ljava/lang/String;
        //    65: astore_1       
        //    66: aload_1        
        //    67: ifnull          88
        //    70: aload_1        
        //    71: ldc             "unexpected e_machine:"
        //    73: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    76: ifeq            88
        //    79: new             Lcom/facebook/soloader/SoLoader$WrongAbiError;
        //    82: dup            
        //    83: aload_0        
        //    84: invokespecial   com/facebook/soloader/SoLoader$WrongAbiError.<init>:(Ljava/lang/Throwable;)V
        //    87: athrow         
        //    88: aload_0        
        //    89: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  3      25     44     50     Any
        //  25     33     50     60     Ljava/io/IOException;
        //  25     33     60     90     Ljava/lang/UnsatisfiedLinkError;
        //  25     33     44     50     Any
        //  37     41     44     50     Any
        //  51     60     44     50     Any
        //  61     66     44     50     Any
        //  70     88     44     50     Any
        //  88     90     44     50     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void loadLibraryBySoName(final String s, int n) {
        while (true) {
            while (true) {
                int loadLibrary = 0;
                Label_0035: {
                Label_0090:
                    while (true) {
                        Label_0014: {
                            if (SoLoader.sLoadedLibraries.contains(s)) {
                                loadLibrary = 1;
                                break Label_0014;
                            }
                            Label_0069: {
                                break Label_0069;
                                while (true) {
                                    Label_0154: {
                                        try {
                                            final int n2;
                                            if (n2 < SoLoader.sSoSources.length) {
                                                loadLibrary = SoLoader.sSoSources[n2].loadLibrary(s, n);
                                                ++n2;
                                                break Label_0035;
                                            }
                                            final int n3;
                                            if (n3 == 0) {
                                                break Label_0154;
                                            }
                                            StrictMode.setThreadPolicy(SoLoader.sOldPolicy);
                                            SoLoader.sOldPolicy = null;
                                            n = loadLibrary;
                                            if (n == 0) {
                                                throw new UnsatisfiedLinkError("couldn't find DSO to load: " + s);
                                            }
                                            break;
                                            loadLibrary = 0;
                                            break Label_0014;
                                        }
                                        finally {
                                            final int n3;
                                            if (n3 != 0) {
                                                StrictMode.setThreadPolicy(SoLoader.sOldPolicy);
                                                SoLoader.sOldPolicy = null;
                                            }
                                        }
                                        break;
                                    }
                                    n = loadLibrary;
                                    continue Label_0090;
                                }
                            }
                            if (n == 1) {
                                SoLoader.sLoadedLibraries.add(s);
                            }
                            return;
                        }
                        if (loadLibrary != 0) {
                            n = loadLibrary;
                            continue Label_0090;
                        }
                        break;
                    }
                    if (SoLoader.sOldPolicy == null) {
                        SoLoader.sOldPolicy = StrictMode.allowThreadDiskReads();
                        final int n3 = 1;
                    }
                    else {
                        final int n3 = 0;
                    }
                    int n2 = 0;
                }
                if (loadLibrary == 0) {
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private static int makePrepareFlags() {
        int n = 0;
        if ((SoLoader.sFlags & 0x2) != 0x0) {
            n = 1;
        }
        return n;
    }
    
    public static File unpackLibraryAndDependencies(final String s) {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(s));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    static File unpackLibraryBySoName(final String s) {
        for (int i = 0; i < SoLoader.sSoSources.length; ++i) {
            final File unpackLibrary = SoLoader.sSoSources[i].unpackLibrary(s);
            if (unpackLibrary != null) {
                return unpackLibrary;
            }
        }
        throw new FileNotFoundException(s);
    }
}
