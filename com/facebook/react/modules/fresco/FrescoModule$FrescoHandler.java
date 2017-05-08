// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.fresco;

import com.facebook.soloader.SoLoader;
import com.facebook.common.soloader.SoLoaderShim$Handler;

class FrescoModule$FrescoHandler implements SoLoaderShim$Handler
{
    @Override
    public void loadLibrary(final String s) {
        SoLoader.loadLibrary(s);
    }
}
