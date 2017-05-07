// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ik;
import java.util.Locale;
import org.json.JSONObject;

public class MediaTrack$Builder
{
    private final MediaTrack FF;
    
    public MediaTrack$Builder(final long n, final int n2) {
        this.FF = new MediaTrack(n, n2);
    }
    
    public MediaTrack build() {
        return this.FF;
    }
    
    public MediaTrack$Builder setContentId(final String contentId) {
        this.FF.setContentId(contentId);
        return this;
    }
    
    public MediaTrack$Builder setContentType(final String contentType) {
        this.FF.setContentType(contentType);
        return this;
    }
    
    public MediaTrack$Builder setCustomData(final JSONObject customData) {
        this.FF.setCustomData(customData);
        return this;
    }
    
    public MediaTrack$Builder setLanguage(final String language) {
        this.FF.setLanguage(language);
        return this;
    }
    
    public MediaTrack$Builder setLanguage(final Locale locale) {
        this.FF.setLanguage(ik.b(locale));
        return this;
    }
    
    public MediaTrack$Builder setName(final String name) {
        this.FF.setName(name);
        return this;
    }
    
    public MediaTrack$Builder setSubtype(final int n) {
        this.FF.aa(n);
        return this;
    }
}
