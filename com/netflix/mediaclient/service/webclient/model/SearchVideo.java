// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.branches.Video;

public class SearchVideo extends Video implements com.netflix.mediaclient.servicemgr.model.search.SearchVideo
{
    public SearchTitle searchTitle;
    public Summary summary;
    
    @Override
    public Object get(final String s) {
        if ("summary".equals(s)) {
            return this.summary;
        }
        if ("searchTitle".equals(s)) {
            return this.searchTitle;
        }
        return null;
    }
    
    @Override
    public String getBoxshotURL() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getBoxshotURL();
    }
    
    @Override
    public String getCertification() {
        if (this.searchTitle == null) {
            return null;
        }
        return this.searchTitle.certification;
    }
    
    @Override
    public VideoType getErrorType() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getErrorType();
    }
    
    @Override
    public String getHorzDispUrl() {
        if (this.searchTitle != null) {
            return this.searchTitle.horzDispUrl;
        }
        if (this.summary == null) {
            return null;
        }
        return this.summary.getHorzDispUrl();
    }
    
    @Override
    public String getId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getId();
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.summary != null) {
            set.add("summary");
        }
        if (this.searchTitle != null) {
            set.add("searchTitle");
        }
        return set;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if ("summary".equals(s)) {
            return this.summary = new Summary();
        }
        if ("searchTitle".equals(s)) {
            return this.searchTitle = new SearchTitle();
        }
        return null;
    }
    
    @Override
    public String getSquareUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getSquareUrl();
    }
    
    @Override
    public String getTitle() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getTitle();
    }
    
    @Override
    public String getTvCardUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getTvCardUrl();
    }
    
    @Override
    public VideoType getType() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getType();
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
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.summary = (Summary)o;
        }
        else if ("searchTitle".equals(s)) {
            this.searchTitle = (SearchTitle)o;
        }
    }
    
    @Override
    public String toString() {
        return "SearchVideo [getTitle()=" + this.getTitle() + ", getType()=" + this.getType() + "]";
    }
}
