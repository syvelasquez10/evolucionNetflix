// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;

public interface Person$Organizations extends Freezable<Person$Organizations>
{
    String getDepartment();
    
    String getDescription();
    
    String getEndDate();
    
    String getLocation();
    
    String getName();
    
    String getStartDate();
    
    String getTitle();
    
    int getType();
    
    boolean hasDepartment();
    
    boolean hasDescription();
    
    boolean hasEndDate();
    
    boolean hasLocation();
    
    boolean hasName();
    
    boolean hasPrimary();
    
    boolean hasStartDate();
    
    boolean hasTitle();
    
    boolean hasType();
    
    boolean isPrimary();
}
