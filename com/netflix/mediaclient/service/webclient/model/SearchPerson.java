// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

public class SearchPerson implements com.netflix.mediaclient.servicemgr.SearchPerson
{
    public String id;
    public String imgUrl;
    public String title;
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String getImgUrl() {
        return this.imgUrl;
    }
    
    @Override
    public String getName() {
        return this.title;
    }
    
    @Override
    public String toString() {
        return this.title;
    }
}
