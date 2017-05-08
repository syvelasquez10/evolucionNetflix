// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import java.io.File;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineImageUtils;
import io.realm.Realm$Transaction;
import com.netflix.mediaclient.service.NetflixService;
import io.realm.Realm;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import io.realm.internal.RealmObjectProxy;
import io.realm.RealmProfileRealmProxyInterface;
import io.realm.RealmModel;

public class RealmProfile implements RealmModel, RealmProfileRealmProxyInterface
{
    private String iconUrl;
    private String id;
    private boolean kids;
    private String name;
    
    public RealmProfile() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy)this).realm$injectObjectContext();
        }
    }
    
    private void fillForRealm(final UserProfile userProfile) {
        this.realmSet$name(userProfile.getProfileName());
        this.realmSet$kids(userProfile.isKidsProfile());
        this.realmSet$iconUrl(userProfile.getAvatarUrl());
    }
    
    public static void insertProfileIfNeeded(final Realm realm, final NetflixService netflixService, final UserProfile userProfile) {
        final String profileGuid = userProfile.getProfileGuid();
        if (RealmUtils.idNotExists(realm, RealmProfile.class, profileGuid)) {
            RealmUtils.executeTransactionAsync(realm, (Realm$Transaction)new RealmProfile$1(profileGuid, userProfile));
            OfflineImageUtils.cacheProfileImage(netflixService, userProfile.getAvatarUrl(), profileGuid);
        }
    }
    
    public String getId() {
        return this.realmGet$id();
    }
    
    public String getName() {
        return this.realmGet$name();
    }
    
    public String getRealmProfileIconUrl(final Context context) {
        final String localFileForProfileImage = OfflineImageUtils.getLocalFileForProfileImage(context, this.getId());
        if (new File(localFileForProfileImage).exists()) {
            return "file://" + localFileForProfileImage;
        }
        return this.realmGet$iconUrl();
    }
    
    public boolean isKids() {
        return this.realmGet$kids();
    }
    
    public String realmGet$iconUrl() {
        return this.iconUrl;
    }
    
    public String realmGet$id() {
        return this.id;
    }
    
    public boolean realmGet$kids() {
        return this.kids;
    }
    
    public String realmGet$name() {
        return this.name;
    }
    
    public void realmSet$iconUrl(final String iconUrl) {
        this.iconUrl = iconUrl;
    }
    
    public void realmSet$id(final String id) {
        this.id = id;
    }
    
    public void realmSet$kids(final boolean kids) {
        this.kids = kids;
    }
    
    public void realmSet$name(final String name) {
        this.name = name;
    }
}
