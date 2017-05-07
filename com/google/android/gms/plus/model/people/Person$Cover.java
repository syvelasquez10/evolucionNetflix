// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;

public interface Person$Cover extends Freezable<Person$Cover>
{
    Person$Cover$CoverInfo getCoverInfo();
    
    Person$Cover$CoverPhoto getCoverPhoto();
    
    int getLayout();
    
    boolean hasCoverInfo();
    
    boolean hasCoverPhoto();
    
    boolean hasLayout();
}
