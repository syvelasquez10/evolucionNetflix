// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.util.StringUtils;

public class SearchSuggestion implements com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion
{
    public String title;
    
    @Override
    public String getTitle() {
        return StringUtils.capitalizeFirstLetter(this.title);
    }
    
    @Override
    public String toString() {
        return this.title;
    }
}
