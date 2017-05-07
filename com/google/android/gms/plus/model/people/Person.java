// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import java.util.List;
import com.google.android.gms.common.data.Freezable;

public interface Person extends Freezable<Person>
{
    String getAboutMe();
    
    Person$AgeRange getAgeRange();
    
    String getBirthday();
    
    String getBraggingRights();
    
    int getCircledByCount();
    
    Person$Cover getCover();
    
    String getCurrentLocation();
    
    String getDisplayName();
    
    int getGender();
    
    String getId();
    
    Person$Image getImage();
    
    String getLanguage();
    
    Person$Name getName();
    
    String getNickname();
    
    int getObjectType();
    
    List<Person$Organizations> getOrganizations();
    
    List<Person$PlacesLived> getPlacesLived();
    
    int getPlusOneCount();
    
    int getRelationshipStatus();
    
    String getTagline();
    
    String getUrl();
    
    List<Person$Urls> getUrls();
    
    boolean hasAboutMe();
    
    boolean hasAgeRange();
    
    boolean hasBirthday();
    
    boolean hasBraggingRights();
    
    boolean hasCircledByCount();
    
    boolean hasCover();
    
    boolean hasCurrentLocation();
    
    boolean hasDisplayName();
    
    boolean hasGender();
    
    boolean hasId();
    
    boolean hasImage();
    
    boolean hasIsPlusUser();
    
    boolean hasLanguage();
    
    boolean hasName();
    
    boolean hasNickname();
    
    boolean hasObjectType();
    
    boolean hasOrganizations();
    
    boolean hasPlacesLived();
    
    boolean hasPlusOneCount();
    
    boolean hasRelationshipStatus();
    
    boolean hasTagline();
    
    boolean hasUrl();
    
    boolean hasUrls();
    
    boolean hasVerified();
    
    boolean isPlusUser();
    
    boolean isVerified();
}
