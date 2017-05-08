// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

public final class UtcTimingElement
{
    public final String schemeIdUri;
    public final String value;
    
    public UtcTimingElement(final String schemeIdUri, final String value) {
        this.schemeIdUri = schemeIdUri;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.schemeIdUri + ", " + this.value;
    }
}
