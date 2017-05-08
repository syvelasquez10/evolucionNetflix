// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import java.io.File;
import java.io.FilenameFilter;

class ImageEditingManager$CleanTask$1 implements FilenameFilter
{
    final /* synthetic */ ImageEditingManager$CleanTask this$0;
    
    ImageEditingManager$CleanTask$1(final ImageEditingManager$CleanTask this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean accept(final File file, final String s) {
        return s.startsWith("ReactNative_cropped_image_");
    }
}
