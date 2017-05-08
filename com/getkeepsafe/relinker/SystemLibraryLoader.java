// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker;

import android.os.Build;
import android.os.Build$VERSION;

final class SystemLibraryLoader implements ReLinker$LibraryLoader
{
    @Override
    public void loadLibrary(final String s) {
        System.loadLibrary(s);
    }
    
    @Override
    public void loadPath(final String s) {
        System.load(s);
    }
    
    @Override
    public String mapLibraryName(final String s) {
        if (s.startsWith("lib") && s.endsWith(".so")) {
            return s;
        }
        return System.mapLibraryName(s);
    }
    
    @Override
    public String[] supportedAbis() {
        if (Build$VERSION.SDK_INT >= 21 && Build.SUPPORTED_ABIS.length > 0) {
            return Build.SUPPORTED_ABIS;
        }
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            return new String[] { Build.CPU_ABI, Build.CPU_ABI2 };
        }
        return new String[] { Build.CPU_ABI };
    }
    
    @Override
    public String unmapLibraryName(final String s) {
        return s.substring(3, s.length() - 3);
    }
}
