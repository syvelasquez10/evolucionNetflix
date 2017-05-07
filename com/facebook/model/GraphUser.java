// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

public interface GraphUser extends GraphObject
{
    String getBirthday();
    
    String getFirstName();
    
    String getId();
    
    String getLastName();
    
    String getLink();
    
    GraphLocation getLocation();
    
    String getMiddleName();
    
    String getName();
    
    String getUsername();
    
    void setBirthday(final String p0);
    
    void setFirstName(final String p0);
    
    void setId(final String p0);
    
    void setLastName(final String p0);
    
    void setLink(final String p0);
    
    void setLocation(final GraphLocation p0);
    
    void setMiddleName(final String p0);
    
    void setName(final String p0);
    
    void setUsername(final String p0);
}
