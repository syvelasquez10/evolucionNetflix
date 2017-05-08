// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.soloader;

public class SoLoaderShim$DefaultHandler implements SoLoaderShim$Handler
{
    @Override
    public void loadLibrary(final String s) {
        System.loadLibrary(s);
    }
}
