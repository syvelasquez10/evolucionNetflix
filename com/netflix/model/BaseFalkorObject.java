// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model;

import com.netflix.falkor.Ref;
import com.netflix.falkor.LinkedList;
import com.netflix.falkor.ModelProxy;
import java.util.Date;
import com.netflix.model.branches.FalkorObject;
import com.netflix.falkor.ReferenceTarget;
import com.netflix.falkor.ModelProxyProvider;
import com.netflix.falkor.Expires;
import com.netflix.falkor.BranchNode;

public abstract class BaseFalkorObject implements BranchNode, Expires, ModelProxyProvider, ReferenceTarget, FalkorObject
{
    private Date expires;
    protected final ModelProxy<? extends BranchNode> proxy;
    private LinkedList<Ref> references;
    
    protected BaseFalkorObject(final ModelProxy<? extends BranchNode> proxy) {
        this.proxy = proxy;
    }
    
    @Override
    public Date getExpires() {
        return this.expires;
    }
    
    @Override
    public ModelProxy<? extends BranchNode> getModelProxy() {
        return this.proxy;
    }
    
    @Override
    public LinkedList<Ref> getReferences() {
        return this.references;
    }
    
    @Override
    public void setExpires(final Date expires) {
        this.expires = expires;
    }
    
    @Override
    public void setReferences(final LinkedList<Ref> references) {
        this.references = references;
    }
}
