// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion;
import com.netflix.model.BaseFalkorObject;

public class FalkorSuggestion extends BaseFalkorObject implements SearchSuggestion, FalkorObject
{
    public com.netflix.model.leafs.SearchSuggestion searchSuggestion;
    
    public FalkorSuggestion(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "searchTitle": {
                return this.searchSuggestion;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.searchSuggestion != null) {
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
        switch (s) {
            default: {
                return null;
            }
            case "searchTitle": {
                return this.searchSuggestion = new com.netflix.model.leafs.SearchSuggestion();
            }
            case "summary": {
                return null;
            }
        }
    }
    
    @Override
    public String getTitle() {
        if (this.searchSuggestion == null) {
            return null;
        }
        return this.searchSuggestion.getTitle();
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("searchTitle".equals(s)) {
            this.searchSuggestion = (com.netflix.model.leafs.SearchSuggestion)o;
        }
        else if (!"summary".equals(s)) {
            throw new IllegalStateException("Can't set key: " + s);
        }
    }
}
