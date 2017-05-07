// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;

public interface Person$Urls extends Freezable<Person$Urls>
{
    String getLabel();
    
    int getType();
    
    String getValue();
    
    boolean hasLabel();
    
    boolean hasType();
    
    boolean hasValue();
}
