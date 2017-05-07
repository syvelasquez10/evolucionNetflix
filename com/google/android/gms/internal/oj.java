// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.data.d;

public final class oj extends d implements Person
{
    public oj(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    @Override
    public String getAboutMe() {
        return null;
    }
    
    @Override
    public AgeRange getAgeRange() {
        return null;
    }
    
    @Override
    public String getBirthday() {
        return null;
    }
    
    @Override
    public String getBraggingRights() {
        return null;
    }
    
    @Override
    public int getCircledByCount() {
        return 0;
    }
    
    @Override
    public Cover getCover() {
        return null;
    }
    
    @Override
    public String getCurrentLocation() {
        return null;
    }
    
    @Override
    public String getDisplayName() {
        return this.getString("displayName");
    }
    
    @Override
    public int getGender() {
        return 0;
    }
    
    @Override
    public String getId() {
        return this.getString("personId");
    }
    
    @Override
    public Image getImage() {
        return new ny.c(this.getString("image"));
    }
    
    @Override
    public String getLanguage() {
        return null;
    }
    
    @Override
    public Name getName() {
        return null;
    }
    
    @Override
    public String getNickname() {
        return null;
    }
    
    @Override
    public int getObjectType() {
        return ny.e.cf(this.getString("objectType"));
    }
    
    @Override
    public int getPlusOneCount() {
        return 0;
    }
    
    @Override
    public int getRelationshipStatus() {
        return 0;
    }
    
    @Override
    public String getTagline() {
        return null;
    }
    
    @Override
    public String getUrl() {
        return this.getString("url");
    }
    
    @Override
    public boolean hasAboutMe() {
        return false;
    }
    
    @Override
    public boolean hasAgeRange() {
        return false;
    }
    
    @Override
    public boolean hasBirthday() {
        return false;
    }
    
    @Override
    public boolean hasBraggingRights() {
        return false;
    }
    
    @Override
    public boolean hasCircledByCount() {
        return false;
    }
    
    @Override
    public boolean hasCover() {
        return false;
    }
    
    @Override
    public boolean hasCurrentLocation() {
        return false;
    }
    
    @Override
    public boolean hasDisplayName() {
        return true;
    }
    
    @Override
    public boolean hasGender() {
        return false;
    }
    
    @Override
    public boolean hasId() {
        return true;
    }
    
    @Override
    public boolean hasImage() {
        return true;
    }
    
    @Override
    public boolean hasIsPlusUser() {
        return false;
    }
    
    @Override
    public boolean hasLanguage() {
        return false;
    }
    
    @Override
    public boolean hasName() {
        return false;
    }
    
    @Override
    public boolean hasNickname() {
        return false;
    }
    
    @Override
    public boolean hasObjectType() {
        return true;
    }
    
    @Override
    public boolean hasOrganizations() {
        return false;
    }
    
    @Override
    public boolean hasPlacesLived() {
        return false;
    }
    
    @Override
    public boolean hasPlusOneCount() {
        return false;
    }
    
    @Override
    public boolean hasRelationshipStatus() {
        return false;
    }
    
    @Override
    public boolean hasTagline() {
        return false;
    }
    
    @Override
    public boolean hasUrl() {
        return true;
    }
    
    @Override
    public boolean hasUrls() {
        return false;
    }
    
    @Override
    public boolean hasVerified() {
        return false;
    }
    
    @Override
    public boolean isPlusUser() {
        return false;
    }
    
    @Override
    public boolean isVerified() {
        return false;
    }
    
    public ArrayList<Organizations> nD() {
        return null;
    }
    
    public ArrayList<PlacesLived> nE() {
        return null;
    }
    
    public ArrayList<Urls> nF() {
        return null;
    }
    
    public Person nG() {
        return new ny(this.getDisplayName(), this.getId(), (ny.c)this.getImage(), this.getObjectType(), this.getUrl());
    }
}
