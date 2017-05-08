// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import io.realm.Realm$Transaction;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import io.realm.Realm;
import com.netflix.mediaclient.util.LogUtils;
import io.realm.RealmIncompleteVideoDetailsRealmProxyInterface;
import io.realm.RealmObject;

public class RealmIncompleteVideoDetails extends RealmObject implements RealmIncompleteVideoDetailsRealmProxyInterface
{
    private static final String TAG;
    private String playableId;
    private String profileId;
    private int videoType;
    
    static {
        TAG = LogUtils.getTag(RealmIncompleteVideoDetails.class);
    }
    
    public static void insertInRealm(final Realm realm, final String s, final VideoType videoType, final String s2) {
        realm.executeTransaction((Realm$Transaction)new RealmIncompleteVideoDetails$1(s, videoType, s2));
    }
    
    public String getPlayableId() {
        return this.realmGet$playableId();
    }
    
    public String getProfileId() {
        return this.realmGet$profileId();
    }
    
    public int getVideoType() {
        return this.realmGet$videoType();
    }
    
    public String realmGet$playableId() {
        return this.playableId;
    }
    
    public String realmGet$profileId() {
        return this.profileId;
    }
    
    public int realmGet$videoType() {
        return this.videoType;
    }
    
    public void realmSet$playableId(final String playableId) {
        this.playableId = playableId;
    }
    
    public void realmSet$profileId(final String profileId) {
        this.profileId = profileId;
    }
    
    public void realmSet$videoType(final int videoType) {
        this.videoType = videoType;
    }
    
    public void setPlayableId(final String s) {
        this.realmSet$playableId(s);
    }
    
    public void setProfileId(final String s) {
        this.realmSet$profileId(s);
    }
    
    public void setVideoType(final int n) {
        this.realmSet$videoType(n);
    }
}
