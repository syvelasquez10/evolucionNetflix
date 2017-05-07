// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.google.gson.annotations.SerializedName;

public interface KidsOnPhoneConfiguration
{
    LolomoImageType getLolomoImageType();
    
    ScrollBehavior getScrollBehavior();
    
    boolean isKidsOnPhoneEnabled();
    
    boolean shouldShowKidsEntryInActionBar();
    
    boolean shouldShowKidsEntryInGenreLomo();
    
    boolean shouldShowKidsEntryInMenu();
    
    public enum LolomoImageType
    {
        HORIZONTAL("horizontal"), 
        ONE_TO_ONE("one2one");
        
        private String value;
        
        private LolomoImageType(final String value) {
            this.value = value;
        }
        
        @Override
        public String toString() {
            return this.value;
        }
    }
    
    public enum ScrollBehavior
    {
        LRUD, 
        UP_DOWN;
    }
}
