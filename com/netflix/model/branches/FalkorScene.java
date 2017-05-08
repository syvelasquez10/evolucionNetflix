// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.SceneSummary;
import com.netflix.model.BaseFalkorObject;

public class FalkorScene extends BaseFalkorObject implements FalkorObject
{
    public SceneSummary sceneSummary;
    
    public FalkorScene(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "summary": {
                return this.sceneSummary;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.sceneSummary != null) {
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
                return this.sceneSummary = new SceneSummary();
            }
        }
    }
    
    public int getScenePosition() {
        if (this.sceneSummary == null) {
            return 0;
        }
        return this.sceneSummary.position;
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.sceneSummary = (SceneSummary)o;
        }
    }
    
    @Override
    public String toString() {
        return "FalkorScene{sceneSummary=" + this.sceneSummary + '}';
    }
}
