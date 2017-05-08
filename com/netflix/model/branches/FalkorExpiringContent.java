// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentType;
import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.ExpiringContent;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;
import com.netflix.model.BaseFalkorObject;

public class FalkorExpiringContent extends BaseFalkorObject implements IExpiringContentWarning, FalkorObject
{
    public static final String SHOULD_SHOW = "should_show";
    ExpiringContent expiringContent;
    
    public FalkorExpiringContent(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "should_show": {
                return this.expiringContent;
            }
        }
    }
    
    @Override
    public long getExpirationTime() {
        return this.expiringContent.getExpirationTime();
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.expiringContent != null) {
            set.add("should_show");
        }
        return set;
    }
    
    @Override
    public String getLocalizedDate() {
        return this.expiringContent.getLocalizedDate();
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
            case "should_show": {
                return this.expiringContent = new ExpiringContent();
            }
        }
    }
    
    @Override
    public ExpiringContentType getWarningType() {
        return this.expiringContent.getWarningType();
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("should_show".equals(s)) {
            this.expiringContent = (ExpiringContent)o;
            return;
        }
        throw new IllegalStateException("Can't set key: " + s);
    }
    
    @Override
    public boolean willExpire() {
        return this.expiringContent != null && this.expiringContent.willExpire();
    }
}
