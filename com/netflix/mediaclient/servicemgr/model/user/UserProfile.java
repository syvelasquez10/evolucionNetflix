// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.user;

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
    
    String getProfileId();
    
    String getProfileName();
    
    ProfileType getProfileType();
    
    String getReqCountry();
    
    String getUserId();
    
    boolean isAutoPlayEnabled();
    
    boolean isIQEnabled();
    
    boolean isKidsProfile();
    
    boolean isPrimaryProfile();
    
    boolean isSocialConnected();
}
