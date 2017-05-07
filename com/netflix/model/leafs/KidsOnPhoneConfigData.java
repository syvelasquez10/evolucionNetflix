// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration;

public class KidsOnPhoneConfigData implements KidsOnPhoneConfiguration
{
    public static final KidsOnPhoneConfigData DEFAULT_KIDS_CONFIG;
    @SerializedName("abNav")
    private ActionBarNavType actionBarNavType;
    @SerializedName("imageType")
    private LolomoImageType imageType;
    @SerializedName("isEntryInActionBar")
    private boolean isEntryInActionBar;
    @SerializedName("isEntryInGenreLolomo")
    private boolean isEntryInGenreLolomo;
    @SerializedName("isEntryInMenu")
    private boolean isEntryInMenu;
    @SerializedName("isKidsOnPhoneEnabled")
    private boolean isKidsOnPhoneEnabled;
    @SerializedName("scrollBehavior")
    private ScrollBehavior scrollBehavior;
    
    static {
        DEFAULT_KIDS_CONFIG = new KidsOnPhoneConfigData();
    }
    
    public KidsOnPhoneConfigData() {
        this.scrollBehavior = ScrollBehavior.LRUD;
        this.imageType = LolomoImageType.HORIZONTAL;
        this.actionBarNavType = ActionBarNavType.UP;
    }
    
    @Override
    public ActionBarNavType getActionBarNavType() {
        return this.actionBarNavType;
    }
    
    @Override
    public LolomoImageType getLolomoImageType() {
        return this.imageType;
    }
    
    @Override
    public ScrollBehavior getScrollBehavior() {
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
