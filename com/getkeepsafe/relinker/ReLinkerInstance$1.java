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
import java.util.HashSet;
import java.util.Set;
import android.content.Context;

class ReLinkerInstance$1 implements Runnable
{
    final /* synthetic */ ReLinkerInstance this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$library;
    final /* synthetic */ ReLinker$LoadListener val$listener;
    final /* synthetic */ String val$version;
    
    ReLinkerInstance$1(final ReLinkerInstance this$0, final Context val$context, final String val$library, final String val$version, final ReLinker$LoadListener val$listener) {
        this.this$0 = this$0;
        this.val$context = val$context;
        this.val$library = val$library;
        this.val$version = val$version;
        this.val$listener = val$listener;
    }
    
    @Override
    public void run() {
        try {
            this.this$0.loadLibraryInternal(this.val$context, this.val$library, this.val$version);
            this.val$listener.success();
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            this.val$listener.failure(unsatisfiedLinkError);
        }
        catch (MissingLibraryException ex) {
            this.val$listener.failure(ex);
        }
    }
}
