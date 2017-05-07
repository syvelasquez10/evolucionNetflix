// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.falkor.Func;
import com.netflix.falkor.Ref;
import com.netflix.falkor.LinkedList;
import java.util.Date;
import com.netflix.falkor.BranchMap;

public class UnsummarizedList<T> extends BranchMap<T>
{
    private Date expires;
    private LinkedList<Ref> references;
    
    public UnsummarizedList(final Func<T> func) {
        super(func);
    }
    
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
