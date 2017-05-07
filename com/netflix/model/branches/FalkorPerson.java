// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;
import com.netflix.model.BaseFalkorObject;

public class FalkorPerson extends BaseFalkorObject implements SearchPerson, FalkorObject
{
    public com.netflix.model.leafs.SearchPerson searchPerson;
    
    public FalkorPerson(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "searchTitle": {
                return this.searchPerson;
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
                return null;
            }
            case "searchTitle": {
                return this.searchPerson = new com.netflix.model.leafs.SearchPerson();
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
        }
        else if (!"summary".equals(s)) {
            throw new IllegalStateException("Can't set key: " + s);
        }
    }
}
