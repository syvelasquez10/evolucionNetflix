// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum UiLocation
{
    GENRE_LOLOMO("browseGenre"), 
    HOME_LOLOMO("browseHome"), 
    MDP("mdp"), 
    MDP_SIMILARS("mdpSimilars"), 
    PEOPLE("people"), 
    SDP("sdp"), 
    SDP_SIMILARS("sdpSimilars"), 
    SEARCH("search"), 
    UNKNOWN("");
    
    private String value;
    
    private UiLocation(final String value) {
        this.value = value;
    }
    
    public static UiLocation create(final String s) {
        final UiLocation[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final UiLocation uiLocation = values[i];
            if (uiLocation.value.equalsIgnoreCase(s)) {
                return uiLocation;
            }
        }
        return UiLocation.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
