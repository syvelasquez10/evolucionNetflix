// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

final class ConfigurationAgent$1 implements KidsOnPhoneConfiguration
{
    @Override
    public KidsOnPhoneConfiguration$ActionBarNavType getActionBarNavType() {
        return KidsOnPhoneConfiguration$ActionBarNavType.UP;
    }
    
    @Override
    public KidsOnPhoneConfiguration$LolomoImageType getLolomoImageType() {
        return KidsOnPhoneConfiguration$LolomoImageType.HORIZONTAL;
    }
    
    @Override
    public KidsOnPhoneConfiguration$ScrollBehavior getScrollBehavior() {
        return KidsOnPhoneConfiguration$ScrollBehavior.UP_DOWN;
    }
    
    @Override
    public boolean isKidsOnPhoneEnabled() {
        return true;
    }
    
    @Override
    public boolean shouldShowKidsEntryInActionBar() {
        return true;
    }
    
    @Override
    public boolean shouldShowKidsEntryInGenreLomo() {
        return true;
    }
    
    @Override
    public boolean shouldShowKidsEntryInMenu() {
        return true;
    }
}
