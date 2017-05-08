// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import com.netflix.model.BaseFalkorObject;

public class FalkorPerson extends BaseFalkorObject implements SearchPerson, FalkorObject
{
    public FalkorPerson$PersonDetail detail;
    public com.netflix.model.leafs.SearchPerson searchPerson;
    public Video$Summary summary;
    public FalkorPerson$PersonVideos videos;
    
    public FalkorPerson(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "summary": {
                return this.summary;
            }
            case "searchTitle": {
                return this.searchPerson;
            }
            case "detail": {
                return this.detail;
            }
            case "videoListForPerson": {
                return this.videos;
            }
        }
    }
    
    @Override
    public String getId() {
        if (this.searchPerson == null) {
            return null;
        }
        return this.searchPerson.getId();
    }
    
    @Override
    public String getImgUrl() {
        if (this.searchPerson == null) {
            return null;
        }
        return this.searchPerson.getImgUrl();
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.searchPerson != null) {
            set.add("searchTitle");
        }
        if (this.detail != null) {
            set.add("detail");
        }
        if (this.summary != null) {
            set.add("summary");
        }
        if (this.videos != null) {
            set.add("videoListForPerson");
        }
        return set;
    }
    
    @Override
    public String getName() {
        if (this.searchPerson == null) {
            return null;
        }
        return this.searchPerson.getName();
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        switch (s) {
            default: {
                return null;
            }
            case "summary": {
                return this.summary = new Video$Summary();
            }
            case "searchTitle": {
                return this.searchPerson = new com.netflix.model.leafs.SearchPerson();
            }
            case "detail": {
                return this.detail = new FalkorPerson$PersonDetail();
            }
            case "videoListForPerson": {
                return this.videos = new FalkorPerson$PersonVideos(this);
            }
        }
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("searchTitle".equals(s)) {
            this.searchPerson = (com.netflix.model.leafs.SearchPerson)o;
            return;
        }
        if ("summary".equals(s)) {
            this.summary = (Video$Summary)o;
            return;
        }
        if ("detail".equals(s)) {
            this.detail = (FalkorPerson$PersonDetail)o;
            return;
        }
        if ("videoListForPerson".equals(s)) {
            this.videos = (FalkorPerson$PersonVideos)o;
            return;
        }
        throw new IllegalStateException("Can't set key: " + s);
    }
}
