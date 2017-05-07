// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

public enum Falkor$SimilarRequestType
{
    PEOPLE("people"), 
    QUERY_SUGGESTION("suggestions");
    
    public final String value;
    
    private Falkor$SimilarRequestType(final String value) {
        this.value = value;
    }
}
