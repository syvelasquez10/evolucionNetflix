// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.io.Serializable;
import com.netflix.mediaclient.Log;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Date;

public class Ref implements ReferenceTarget, PathBound, Expires
{
    static final String TAG = "Ref";
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
        if (Log.isLoggable("Ref", 2)) {
            Log.v("Ref", "Clearing hard ref for path: " + this.refPath);
        }
        this.valueRef.set(null);
    }
    
    @Override
    public Date getExpires() {
        return this.expires;
    }
    
    public Object getHardValue() {
        final Object value = this.valueRef.get();
        if (Log.isLoggable("Ref", 2)) {
            final StringBuilder append = new StringBuilder().append("Hard value: ");
            Serializable class1;
            if (value == null) {
                class1 = "n/a";
            }
            else {
                class1 = value.getClass();
            }
            Log.v("Ref", append.append(class1).append(", path: ").append(this.refPath).toString());
        }
        return value;
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
            if (Log.isLoggable("Ref", 2)) {
                Log.v("Ref", "getValue returned hard ref for path: " + this.refPath);
            }
            return value;
        }
        final Object value2 = modelProxy.getValue(this.refPath);
        if (value2 instanceof ReferenceTarget) {
            if (Log.isLoggable("Ref", 2)) {
                Log.v("Ref", "Target is capable of storing references, create hard reference: " + this.refPath);
            }
            final ReferenceTarget referenceTarget = (ReferenceTarget)value2;
            referenceTarget.setReferences(new LinkedList<Ref>(this, (LinkedList<Object>)referenceTarget.getReferences()));
            this.valueRef.set(value2);
        }
        else if (Log.isLoggable("Ref", 5)) {
            final StringBuilder append = new StringBuilder().append("Target CANNOT store references, got value for path: ").append(this.refPath).append(", class: ");
            Serializable class1;
            if (value2 == null) {
                class1 = "n/a";
            }
            else {
                class1 = ((ReferenceTarget)value2).getClass();
            }
            Log.w("Ref", append.append(class1).toString());
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
        if (Log.isLoggable("Ref", 2)) {
            Log.v("Ref", "Setting ref path to: " + refPath);
        }
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
