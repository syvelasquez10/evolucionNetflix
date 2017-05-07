// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.google.gson.annotations.SerializedName;

public enum KidsOnPhoneConfiguration$LolomoImageType
{
    HORIZONTAL("horizontal"), 
    ONE_TO_ONE("one2one");
    
    private String value;
    
    private KidsOnPhoneConfiguration$LolomoImageType(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
