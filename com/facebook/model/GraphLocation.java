// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

public interface GraphLocation extends GraphObject
{
    String getCity();
    
    String getCountry();
    
    double getLatitude();
    
    double getLongitude();
    
    String getState();
    
    String getStreet();
    
    String getZip();
    
    void setCity(final String p0);
    
    void setCountry(final String p0);
    
    void setLatitude(final double p0);
    
    void setLongitude(final double p0);
    
    void setState(final String p0);
    
    void setStreet(final String p0);
    
    void setZip(final String p0);
}
