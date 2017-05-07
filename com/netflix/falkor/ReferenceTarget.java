// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

public interface ReferenceTarget
{
    LinkedList<Ref> getReferences();
    
    void setReferences(final LinkedList<Ref> p0);
}
