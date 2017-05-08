// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.nativecode;

import com.facebook.common.soloader.SoLoaderShim;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ImagePipelineNativeLoader
{
    public static final List<String> DEPENDENCIES;
    
    static {
        DEPENDENCIES = Collections.unmodifiableList((List<? extends String>)new ArrayList<String>());
    }
    
    public static void load() {
        SoLoaderShim.loadLibrary("imagepipeline");
    }
}
