// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.drm;

import com.google.android.exoplayer.util.Assertions;
import android.media.MediaCrypto;
import android.annotation.TargetApi;

@TargetApi(16)
public final class FrameworkMediaCrypto implements ExoMediaCrypto
{
    private final MediaCrypto mediaCrypto;
    
    public FrameworkMediaCrypto(final MediaCrypto mediaCrypto) {
        this.mediaCrypto = Assertions.checkNotNull(mediaCrypto);
    }
    
    public MediaCrypto getWrappedMediaCrypto() {
        return this.mediaCrypto;
    }
    
    public boolean requiresSecureDecoderComponent(final String s) {
        return this.mediaCrypto.requiresSecureDecoderComponent(s);
    }
}
