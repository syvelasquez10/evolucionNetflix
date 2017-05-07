// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.LoMo;

public class ListOfMoviesSummary extends TrackableListSummary implements LoMo, Genre
{
    private String displayName;
    private LoMoType enumType;
    private String id;
    private SocialEvidence socialEvidence;
    private String type;
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        if (this.socialEvidence != null) {
            return this.socialEvidence.getFacebookFriends();
        }
        return null;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public int getNumVideos() {
        return this.getLength();
    }
    
    @Override
    public String getTitle() {
        return StringUtils.decodeHtmlEntities(this.displayName);
    }
    
    @Override
    public LoMoType getType() {
        if (this.enumType == null) {
            this.enumType = LoMoType.create(this.type);
        }
        return this.enumType;
    }
    
    @Override
    public boolean isBillboard() {
        return this.getType() == LoMoType.BILLBOARD;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
}
