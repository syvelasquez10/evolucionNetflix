// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.service.webclient.model.branches.Video$SearchTitle;
import com.netflix.mediaclient.service.webclient.model.branches.Video;

public class SearchVideo extends Video implements com.netflix.mediaclient.servicemgr.model.search.SearchVideo
{
    public Video$SearchTitle searchTitle;
    
    @Override
    public String getCertification() {
        if (this.searchTitle == null) {
            return null;
        }
        return this.searchTitle.certification;
    }
    
    @Override
    public String getHorzDispUrl() {
        if (this.searchTitle != null) {
            return this.searchTitle.horzDispUrl;
        }
        return super.getHorzDispUrl();
    }
    
    @Override
    public int getYear() {
        Integer value;
        if (this.searchTitle == null) {
            value = null;
        }
        else {
            value = this.searchTitle.releaseYear;
        }
        return value;
    }
    
    @Override
    public String toString() {
        return "SearchVideo [getTitle()=" + this.getTitle() + ", getType()=" + this.getType() + "]";
    }
}
