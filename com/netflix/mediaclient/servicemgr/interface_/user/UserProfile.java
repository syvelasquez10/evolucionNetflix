// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.user;

import java.util.List;

public interface UserProfile
{
    String getAvatarUrl();
    
    String getEmail();
    
    String getFirstName();
    
    String getGeoCountry();
    
    String[] getLanguages();
    
    String getLanguagesInCsv();
    
    List<String> getLanguagesList();
    
    String getLastName();
    
    String getProfileGuid();
    
    String getProfileName();
    
    String getProfileToken();
    
    ProfileType getProfileType();
    
    String getReqCountry();
    
    boolean isAutoPlayEnabled();
    
    boolean isIQEnabled();
    
    boolean isKidsProfile();
    
    boolean isPrimaryProfile();
    
    boolean isSocialConnected();
}
