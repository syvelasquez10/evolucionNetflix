// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import java.util.Locale;
import com.netflix.mediaclient.servicemgr.model.IconFontGlyph;
import com.google.gson.annotations.SerializedName;

public class Video$Evidence
{
    @SerializedName("kind")
    private String kind;
    @SerializedName("text")
    private String text;
    
    public IconFontGlyph getIconFontGlyph() {
        if (this.kind == null) {
            return null;
        }
        final String lowerCase = this.kind.toLowerCase(Locale.US);
        switch (lowerCase) {
            default: {
                return IconFontGlyph.EVIDENCE_GENERIC;
            }
            case "awards": {
                return IconFontGlyph.EVIDENCE_AWARDS;
            }
            case "boxoffice": {
                return IconFontGlyph.EVIDENCE_BOX_OFFICE;
            }
            case "talent": {
                return IconFontGlyph.EVIDENCE_TALENT;
            }
            case "tvratings": {
                return IconFontGlyph.EVIDENCE_TV_RATINGS;
            }
        }
    }
    
    public String getText() {
        return this.text;
    }
    
    @Override
    public String toString() {
        return "Evidence [kind=" + this.kind + ", text=" + this.text + "]";
    }
}
