// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;

public interface Moment extends Freezable<Moment>
{
    String getId();
    
    ItemScope getResult();
    
    String getStartDate();
    
    ItemScope getTarget();
    
    String getType();
    
    boolean hasId();
    
    boolean hasResult();
    
    boolean hasStartDate();
    
    boolean hasTarget();
    
    boolean hasType();
}
