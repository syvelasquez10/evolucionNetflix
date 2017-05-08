// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.model.leafs.InteractivePostplay;
import com.netflix.model.leafs.InteractiveDetails;
import com.netflix.falkor.BranchNode;

public class InteractiveInfo implements BranchNode
{
    public InteractiveDetails details;
    public InteractivePostplay postplay;
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "postplay": {
                return this.postplay;
            }
            case "details": {
                return this.details;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.postplay != null) {
            set.add("postplay");
        }
        if (this.details != null) {
            set.add("details");
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
            case "postplay": {
                return this.postplay = new InteractivePostplay();
            }
            case "details": {
                return this.details = new InteractiveDetails();
            }
        }
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("postplay".equals(s)) {
            this.postplay = (InteractivePostplay)o;
            return;
        }
        if ("details".equals(s)) {
            this.details = (InteractiveDetails)o;
            return;
        }
        throw new IllegalStateException("Can't set key: " + s);
    }
    
    @Override
    public String toString() {
        return "InteractiveInfo{postplay=" + this.postplay + ", details=" + this.details + '}';
    }
}
