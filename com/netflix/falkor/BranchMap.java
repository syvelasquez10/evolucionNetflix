// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Collection;
import java.util.Set;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class BranchMap<T> extends HashMap<String, T> implements BranchNode, Expires, ReferenceTarget
{
    private Map<String, Object> errorsOrUndefineds;
    private Date expires;
    private LinkedList<Ref> references;
    Func<T> typeCreator;
    
    public BranchMap(final Func<T> typeCreator) {
        this.typeCreator = typeCreator;
    }
    
    @Override
    public Object get(final String s) {
        Object o2;
        final Object o = o2 = super.get(s);
        if (o == null) {
            o2 = o;
            if (this.errorsOrUndefineds != null) {
                o2 = this.errorsOrUndefineds.get(s);
            }
        }
        return o2;
    }
    
    @Override
    public Date getExpires() {
        return this.expires;
    }
    
    @Override
    public Set<String> getKeys() {
        final Set<String> keySet = this.keySet();
        if (this.errorsOrUndefineds != null) {
            keySet.addAll(this.errorsOrUndefineds.keySet());
        }
        return keySet;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        Object o;
        if ((o = this.get(s)) == null) {
            o = this.typeCreator.call();
            this.put(s, (T)o);
        }
        return o;
    }
    
    @Override
    public LinkedList<Ref> getReferences() {
        return this.references;
    }
    
    @Override
    public void set(final String s, final Object o) {
        if (o instanceof Exception || o instanceof Undefined) {
            if (this.errorsOrUndefineds == null) {
                this.errorsOrUndefineds = new HashMap<String, Object>();
            }
            this.errorsOrUndefineds.put(s, o);
            return;
        }
        super.put(s, (T)o);
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
