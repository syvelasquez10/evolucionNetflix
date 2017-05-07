// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Date;

public class Sentinel<T> implements Expires, PathBound, ReferenceTarget
{
    public static final String JSON_KEY = "_sentinel";
    private Date expires;
    private PQL path;
    private LinkedList<Ref> references;
    private final T value;
    
    public Sentinel(final T value) {
        this.value = value;
    }
    
    @Override
    public Date getExpires() {
        return this.expires;
    }
    
    @Override
    public PQL getPath() {
        return this.path;
    }
    
    @Override
    public LinkedList<Ref> getReferences() {
        return this.references;
    }
    
    public T getValue() {
        return this.value;
    }
    
    @Override
    public void setExpires(final Date expires) {
        this.expires = expires;
    }
    
    @Override
    public void setPath(final PQL path) {
        this.path = path;
    }
    
    @Override
    public void setReferences(final LinkedList<Ref> references) {
        this.references = references;
    }
    
    @Override
    public String toString() {
        return "Sentinel [value=" + this.value + "]";
    }
}
