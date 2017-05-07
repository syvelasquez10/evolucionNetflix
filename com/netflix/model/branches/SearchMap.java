// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.BranchNode;

public class SearchMap implements BranchNode
{
    private static final String TAG = "SearchMap";
    private SearchMap$SearchQueryMap people;
    private SearchMap$SearchQueryMap suggestions;
    private SearchMap$SearchQueryMap videos;
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                Log.d("SearchMap", "Could not find key: " + s);
                return null;
            }
            case "videos": {
                return this.videos;
            }
            case "people": {
                return this.people;
            }
            case "suggestions": {
                return this.suggestions;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.videos != null) {
            set.add("videos");
        }
        if (this.people != null) {
            set.add("people");
        }
        if (this.suggestions != null) {
            set.add("suggestions");
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
                throw new IllegalArgumentException("Unknown key: " + s);
            }
            case "videos": {
                return this.videos = new SearchMap$SearchQueryMap();
            }
            case "people": {
                return this.people = new SearchMap$SearchQueryMap();
            }
            case "suggestions": {
                return this.suggestions = new SearchMap$SearchQueryMap();
            }
        }
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {
                Log.d("SearchMap", "Don't know how to set key: " + s);
            }
            case "videos": {
                this.videos = (SearchMap$SearchQueryMap)o;
            }
            case "people": {
                this.people = (SearchMap$SearchQueryMap)o;
            }
            case "suggestions": {
                this.suggestions = (SearchMap$SearchQueryMap)o;
            }
        }
    }
}
