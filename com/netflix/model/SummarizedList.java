// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model;

import java.util.Set;
import com.netflix.falkor.Func;
import com.netflix.mediaclient.service.webclient.model.leafs.ListSummary;
import com.netflix.falkor.Ref;
import com.netflix.falkor.LinkedList;
import java.util.Date;
import com.netflix.falkor.BranchMap;

public class SummarizedList<T> extends BranchMap<T>
{
    private Date expires;
    private LinkedList<Ref> references;
    private ListSummary summary;
    
    public SummarizedList(final Func<T> func) {
        super(func);
    }
    
    @Override
    public Object get(final String s) {
        if ("summary".equals(s)) {
            return this.summary;
        }
        return super.get(s);
    }
    
    @Override
    public Date getExpires() {
        return this.expires;
    }
    
    @Override
    public Set<String> getKeys() {
        final Set<String> keys = super.getKeys();
        if (this.summary != null) {
            keys.add("summary");
        }
        return keys;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        Object o;
        if ((o = this.get(s)) == null) {
            if (!"summary".equals(s)) {
                return super.getOrCreate(s);
            }
            this.summary = new ListSummary(0);
            o = this.summary;
        }
        return o;
    }
    
    @Override
    public LinkedList<Ref> getReferences() {
        return this.references;
    }
    
    public ListSummary getSummary() {
        return this.summary;
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.summary = (ListSummary)o;
            return;
        }
        super.set(s, o);
    }
    
    @Override
    public void setExpires(final Date expires) {
        this.expires = expires;
    }
    
    @Override
    public void setReferences(final LinkedList<Ref> references) {
        this.references = references;
    }
    
    public void setSummary(final ListSummary summary) {
        this.summary = summary;
    }
}
