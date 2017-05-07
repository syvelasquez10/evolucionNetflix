// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

public interface GraphPlace extends GraphObject
{
    String getCategory();
    
    String getId();
    
    GraphLocation getLocation();
    
    String getName();
    
    void setCategory(final String p0);
    
    void setId(final String p0);
    
    void setLocation(final GraphLocation p0);
    
    void setName(final String p0);
}
