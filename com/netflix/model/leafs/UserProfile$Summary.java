// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.List;
import com.netflix.mediaclient.servicemgr.model.user.ProfileType;

public class UserProfile$Summary
{
    private String avatarUrl;
    private String email;
    public ProfileType enumType;
    private String experienceType;
    private String firstName;
    private String geoCountry;
    private boolean isAutoPlayEnabled;
    private boolean isIqEnabled;
    private boolean isPrimaryProfile;
    public List<UserProfile$Language> languages;
    private String lastName;
    private String profileId;
    private String profileName;
    private String reqCountry;
    private UserProfile$Social social;
    final /* synthetic */ UserProfile this$0;
    private String userId;
    
    public UserProfile$Summary(final UserProfile this$0) {
        this.this$0 = this$0;
    }
}
