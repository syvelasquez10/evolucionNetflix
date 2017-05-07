// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

public interface KidsOnPhoneConfiguration
{
    KidsOnPhoneConfiguration$ActionBarNavType getActionBarNavType();
    
    KidsOnPhoneConfiguration$LolomoImageType getLolomoImageType();
    
    KidsOnPhoneConfiguration$ScrollBehavior getScrollBehavior();
    
    boolean isKidsOnPhoneEnabled();
    
    boolean shouldShowKidsEntryInActionBar();
    
    boolean shouldShowKidsEntryInGenreLomo();
    
    boolean shouldShowKidsEntryInMenu();
}
