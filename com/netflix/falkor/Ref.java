// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Date;

public class Ref implements ReferenceTarget, PathBound, Expires
{
    private Date expires;
    private PQL path;
    private PQL refPath;
    private LinkedList<Ref> references;
    private final AtomicReference<Object> valueRef;
    
    public Ref() {
        this.valueRef = new AtomicReference<Object>();
    }
    
    public Ref(final PQL refPath) {
        this.valueRef = new AtomicReference<Object>();
        this.refPath = refPath;
    }
    
    public void clearValue() {
        this.valueRef.set(null);
    }
    
    @Override
    public Date getExpires() {
        return this.expires;
    }
    
    public Object getHardValue() {
        return this.valueRef.get();
    }
    
    @Override
    public PQL getPath() {
        return this.path;
    }
    
    public PQL getRefPath() {
        return this.refPath;
    }
    
    @Override
    public LinkedList<Ref> getReferences() {
        return this.references;
    }
    
    public Object getValue(final ModelProxy<?> modelProxy) {
        final Object value = this.valueRef.get();
        if (value != null) {
            return value;
        }
        final Object value2 = modelProxy.getValue(this.refPath);
        if (value2 instanceof ReferenceTarget) {
            final ReferenceTarget referenceTarget = (ReferenceTarget)value2;
            referenceTarget.setReferences(new LinkedList<Ref>(this, (LinkedList<Object>)referenceTarget.getReferences()));
            this.valueRef.set(value2);
        }
        return value2;
    }
    
    @Override
    public void setExpires(final Date expires) {
        this.expires = expires;
    }
    
    @Override
    public void setPath(final PQL path) {
        this.path = path;
    }
    
    public void setRefPath(final PQL refPath) {
        this.refPath = refPath;
    }
    
    @Override
    public void setReferences(final LinkedList<Ref> references) {
        this.references = references;
    }
    
    @Override
    public String toString() {
        return String.format("Ref path: %s", this.getRefPath());
    }
}
