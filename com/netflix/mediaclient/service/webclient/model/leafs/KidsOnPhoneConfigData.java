// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration$ScrollBehavior;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration$LolomoImageType;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration$ActionBarNavType;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration;

public class KidsOnPhoneConfigData implements KidsOnPhoneConfiguration
{
    public static final KidsOnPhoneConfigData DEFAULT_KIDS_CONFIG;
    @SerializedName("abNav")
    private KidsOnPhoneConfiguration$ActionBarNavType actionBarNavType;
    @SerializedName("imageType")
    private KidsOnPhoneConfiguration$LolomoImageType imageType;
    @SerializedName("isEntryInActionBar")
    private boolean isEntryInActionBar;
    @SerializedName("isEntryInGenreLolomo")
    private boolean isEntryInGenreLolomo;
    @SerializedName("isEntryInMenu")
    private boolean isEntryInMenu;
    @SerializedName("isKidsOnPhoneEnabled")
    private boolean isKidsOnPhoneEnabled;
    @SerializedName("scrollBehavior")
    private KidsOnPhoneConfiguration$ScrollBehavior scrollBehavior;
    
    static {
        DEFAULT_KIDS_CONFIG = new KidsOnPhoneConfigData();
    }
    
    public KidsOnPhoneConfigData() {
        this.scrollBehavior = KidsOnPhoneConfiguration$ScrollBehavior.LRUD;
        this.imageType = KidsOnPhoneConfiguration$LolomoImageType.HORIZONTAL;
        this.actionBarNavType = KidsOnPhoneConfiguration$ActionBarNavType.UP;
    }
    
    @Override
    public KidsOnPhoneConfiguration$ActionBarNavType getActionBarNavType() {
        return this.actionBarNavType;
    }
    
    @Override
    public KidsOnPhoneConfiguration$LolomoImageType getLolomoImageType() {
        return this.imageType;
    }
    
    @Override
    public KidsOnPhoneConfiguration$ScrollBehavior getScrollBehavior() {
        return this.scrollBehavior;
    }
    
    @Override
    public boolean isKidsOnPhoneEnabled() {
        return this.isKidsOnPhoneEnabled;
    }
    
    @Override
    public boolean shouldShowKidsEntryInActionBar() {
        return this.isEntryInActionBar;
    }
    
    @Override
    public boolean shouldShowKidsEntryInGenreLomo() {
        return this.isEntryInGenreLolomo;
    }
    
    @Override
    public boolean shouldShowKidsEntryInMenu() {
        return this.isEntryInMenu;
    }
}
