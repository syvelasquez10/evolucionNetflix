// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;

public interface Person$Name extends Freezable<Person$Name>
{
    String getFamilyName();
    
    String getFormatted();
    
    String getGivenName();
    
    String getHonorificPrefix();
    
    String getHonorificSuffix();
    
    String getMiddleName();
    
    boolean hasFamilyName();
    
    boolean hasFormatted();
    
    boolean hasGivenName();
    
    boolean hasHonorificPrefix();
    
    boolean hasHonorificSuffix();
    
    boolean hasMiddleName();
}
