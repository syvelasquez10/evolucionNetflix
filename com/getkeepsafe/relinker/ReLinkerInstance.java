// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker;

import java.util.Locale;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import com.getkeepsafe.relinker.elf.ElfParser;
import android.util.Log;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;

public class ReLinkerInstance
{
    protected boolean force;
    protected final ReLinker$LibraryInstaller libraryInstaller;
    protected final ReLinker$LibraryLoader libraryLoader;
    protected final Set<String> loadedLibraries;
    protected ReLinker$Logger logger;
    protected boolean recursive;
    
    protected ReLinkerInstance() {
        this(new SystemLibraryLoader(), new ApkLibraryInstaller());
    }
    
    protected ReLinkerInstance(final ReLinker$LibraryLoader libraryLoader, final ReLinker$LibraryInstaller libraryInstaller) {
        this.loadedLibraries = new HashSet<String>();
        if (libraryLoader == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (libraryInstaller == null) {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
        this.libraryLoader = libraryLoader;
        this.libraryInstaller = libraryInstaller;
    }
    
    private void loadLibraryInternal(final Context context, final String s, final String s2) {
        if (this.loadedLibraries.contains(s) && !this.force) {
            this.log("%s already loaded previously!", s);
            return;
        }
        try {
            this.libraryLoader.loadLibrary(s);
            this.loadedLibraries.add(s);
            this.log("%s (%s) was loaded normally!", s, s2);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            this.log("Loading the library normally failed: %s", Log.getStackTraceString((Throwable)unsatisfiedLinkError));
            this.log("%s (%s) was not loaded normally, re-linking...", s, s2);
            final File workaroundLibFile = this.getWorkaroundLibFile(context, s, s2);
            if (!workaroundLibFile.exists() || this.force) {
                if (this.force) {
                    this.log("Forcing a re-link of %s (%s)...", s, s2);
                }
                this.cleanupOldLibFiles(context, s, s2);
                this.libraryInstaller.installLibrary(context, this.libraryLoader.supportedAbis(), this.libraryLoader.mapLibraryName(s), workaroundLibFile, this);
            }
            try {
                if (this.recursive) {
                    final Iterator<String> iterator = new ElfParser(workaroundLibFile).parseNeededDependencies().iterator();
                    while (iterator.hasNext()) {
                        this.loadLibrary(context, this.libraryLoader.unmapLibraryName(iterator.next()));
                    }
                }
            }
            catch (IOException ex) {}
            this.libraryLoader.loadPath(workaroundLibFile.getAbsolutePath());
            this.loadedLibraries.add(s);
            this.log("%s (%s) was re-linked!", s, s2);
        }
    }
    
    protected void cleanupOldLibFiles(final Context context, final String s, final String s2) {
        final File workaroundLibDir = this.getWorkaroundLibDir(context);
        final File workaroundLibFile = this.getWorkaroundLibFile(context, s, s2);
        final File[] listFiles = workaroundLibDir.listFiles(new ReLinkerInstance$2(this, this.libraryLoader.mapLibraryName(s)));
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                final File file = listFiles[i];
                if (this.force || !file.getAbsolutePath().equals(workaroundLibFile.getAbsolutePath())) {
                    file.delete();
                }
            }
        }
    }
    
    protected File getWorkaroundLibDir(final Context context) {
        return context.getDir("lib", 0);
    }
    
    protected File getWorkaroundLibFile(final Context context, String mapLibraryName, final String s) {
        mapLibraryName = this.libraryLoader.mapLibraryName(mapLibraryName);
        if (TextUtils.isEmpty(s)) {
            return new File(this.getWorkaroundLibDir(context), mapLibraryName);
        }
        return new File(this.getWorkaroundLibDir(context), mapLibraryName + "." + s);
    }
    
    public void loadLibrary(final Context context, final String s) {
        this.loadLibrary(context, s, null, null);
    }
    
    public void loadLibrary(final Context context, final String s, final String s2, final ReLinker$LoadListener reLinker$LoadListener) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        if (TextUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        this.log("Beginning load of %s...", s);
        if (reLinker$LoadListener == null) {
            this.loadLibraryInternal(context, s, s2);
            return;
        }
        new Thread(new ReLinkerInstance$1(this, context, s, s2, reLinker$LoadListener)).start();
    }
    
    public void log(final String s) {
        if (this.logger != null) {
            this.logger.log(s);
        }
    }
    
    public void log(final String s, final Object... array) {
        this.log(String.format(Locale.US, s, array));
    }
}
