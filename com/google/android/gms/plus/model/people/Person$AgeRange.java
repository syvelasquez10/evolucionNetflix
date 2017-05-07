// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;

public interface Person$AgeRange extends Freezable<Person$AgeRange>
{
    int getMax();
    
    int getMin();
    
    boolean hasMax();
    
    boolean hasMin();
}
