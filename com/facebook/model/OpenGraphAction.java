// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.util.List;
import java.util.Date;
import org.json.JSONObject;

public interface OpenGraphAction extends GraphObject
{
    GraphObject getApplication();
    
    JSONObject getComments();
    
    Date getCreatedTime();
    
    Date getEndTime();
    
    Date getExpiresTime();
    
    GraphUser getFrom();
    
    String getId();
    
    List<JSONObject> getImage();
    
    JSONObject getLikes();
    
    String getMessage();
    
    GraphPlace getPlace();
    
    Date getPublishTime();
    
    String getRef();
    
    Date getStartTime();
    
    List<GraphObject> getTags();
    
    void setApplication(final GraphObject p0);
    
    void setComments(final JSONObject p0);
    
    void setCreatedTime(final Date p0);
    
    void setEndTime(final Date p0);
    
    void setExpiresTime(final Date p0);
    
    void setFrom(final GraphUser p0);
    
    void setId(final String p0);
    
    void setImage(final List<JSONObject> p0);
    
    void setLikes(final JSONObject p0);
    
    void setMessage(final String p0);
    
    void setPlace(final GraphPlace p0);
    
    void setPublishTime(final Date p0);
    
    void setRef(final String p0);
    
    void setStartTime(final Date p0);
    
    void setTags(final List<? extends GraphObject> p0);
}
