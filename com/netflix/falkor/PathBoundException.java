// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Date;

public class PathBoundException extends Exception implements Expires, PathBound, ReferenceTarget
{
    private Date expires;
    private PQL path;
    private LinkedList<Ref> references;
    
    public PathBoundException(final PQL path, final String s, final Throwable t) {
        super(s, t);
        this.path = path;
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
    
    public Object getValue() {
        return this;
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
        return this.getMessage();
    }
}
