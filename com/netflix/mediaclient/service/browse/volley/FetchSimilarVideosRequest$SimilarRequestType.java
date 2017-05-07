// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

enum FetchSimilarVideosRequest$SimilarRequestType
{
    PEOPLE("people"), 
    QUERY_SUGGESTION("suggestions");
    
    public final String keyName;
    
    private FetchSimilarVideosRequest$SimilarRequestType(final String keyName) {
        this.keyName = keyName;
    }
}
