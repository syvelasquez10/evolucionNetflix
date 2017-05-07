// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.Set;
import com.netflix.falkor.Ref;
import com.netflix.falkor.LinkedList;
import com.netflix.falkor.Func;
import java.util.Date;
import com.netflix.falkor.BranchMap;

public class SummarizedList<T, L> extends BranchMap<T>
{
    private Date expires;
    private final Func<L> listSummaryCreator;
    private LinkedList<Ref> references;
    private L summary;
    
    public SummarizedList(final Func<T> func, final Func<L> listSummaryCreator) {
        super(func);
        this.listSummaryCreator = listSummaryCreator;
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
            this.summary = this.listSummaryCreator.call();
            o = this.summary;
        }
        return o;
    }
    
    @Override
    public LinkedList<Ref> getReferences() {
        return this.references;
    }
    
    public L getSummary() {
        return this.summary;
    }
    
    @Override
    public void set(final String s, final Object summary) {
        if ("summary".equals(s)) {
            this.summary = (L)summary;
            return;
        }
        super.set(s, summary);
    }
    
    @Override
    public void setExpires(final Date expires) {
        this.expires = expires;
    }
    
    @Override
    public void setReferences(final LinkedList<Ref> references) {
        this.references = references;
    }
    
    public void setSummary(final L summary) {
        this.summary = summary;
    }
}
