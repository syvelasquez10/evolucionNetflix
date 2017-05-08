// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.common;

import java.util.Locale;
import com.facebook.common.util.HashCodeUtil;
import com.facebook.common.internal.Preconditions;

public class ResizeOptions
{
    public final int height;
    public final int width;
    
    public ResizeOptions(final int width, final int height) {
        final boolean b = true;
        Preconditions.checkArgument(width > 0);
        Preconditions.checkArgument(height > 0 && b);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ResizeOptions)) {
                return false;
            }
            final ResizeOptions resizeOptions = (ResizeOptions)o;
            if (this.width != resizeOptions.width || this.height != resizeOptions.height) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return HashCodeUtil.hashCode(this.width, this.height);
    }
    
    @Override
    public String toString() {
        return String.format(null, "%dx%d", this.width, this.height);
    }
}
