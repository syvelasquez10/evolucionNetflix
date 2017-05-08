// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import android.content.Context;

public final class ExoSoSource extends UnpackingSoSource
{
    public ExoSoSource(final Context context, final String s) {
        super(context, s);
    }
    
    @Override
    protected UnpackingSoSource$Unpacker makeUnpacker() {
        return new ExoSoSource$ExoUnpacker(this);
    }
}
