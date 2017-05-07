// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public enum IconFontGlyph
{
    EVIDENCE_AWARDS('\ue736'), 
    EVIDENCE_BOX_OFFICE('\ue737'), 
    EVIDENCE_GENERIC('\ue738'), 
    EVIDENCE_TALENT('\ue739'), 
    EVIDENCE_TV_RATINGS('\ue733'), 
    NETFLIX_LOGO('\ue600'), 
    NETFLIX_N_ICON('\ue602'), 
    PLAY_W_RING('\ue647');
    
    private final Character unicodeChar;
    
    private IconFontGlyph(final Character unicodeChar) {
        this.unicodeChar = unicodeChar;
    }
    
    public Character getUnicodeChar() {
        return this.unicodeChar;
    }
}
