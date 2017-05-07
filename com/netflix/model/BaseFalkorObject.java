// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model;

import com.netflix.falkor.Ref;
import com.netflix.falkor.LinkedList;
import java.util.Date;
import com.netflix.falkor.Expires;
import com.netflix.falkor.ReferenceTarget;
import com.netflix.falkor.BranchNode;

public abstract class BaseFalkorObject implements BranchNode, ReferenceTarget, Expires
{
    private Date expires;
    private LinkedList<Ref> references;
    
    @Override
    public Date getExpires() {
        return this.expires;
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
