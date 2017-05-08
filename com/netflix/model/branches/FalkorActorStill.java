// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.BaseFalkorObject;

public class FalkorActorStill extends BaseFalkorObject implements FalkorObject
{
    public FalkorActorStill$Summary summary;
    
    public FalkorActorStill(final ModelProxy<? extends BranchNode> modelProxy) {
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
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.summary != null) {
            set.add("summary");
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
            case "summary": {
                return this.summary = new FalkorActorStill$Summary();
            }
        }
    }
    
    public String getStillUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.stillImageUrl;
    }
    
    public float getStillXFocus() {
        Float value;
        if (this.summary == null) {
            value = null;
        }
        else {
            value = this.summary.stillImageXFocus;
        }
        return value;
    }
    
    public float getStillYFocus() {
        Float value;
        if (this.summary == null) {
            value = null;
        }
        else {
            value = this.summary.stillImageYFocus;
        }
        return value;
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.summary = (FalkorActorStill$Summary)o;
            return;
        }
        throw new IllegalStateException("Can't set key: " + s);
    }
}
