// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import io.realm.RealmIncompleteVideoDetailsRealmProxyInterface;
import io.realm.RealmObject;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.Log;
import io.realm.Realm;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import io.realm.Realm$Transaction;

final class RealmIncompleteVideoDetails$1 implements Realm$Transaction
{
    final /* synthetic */ String val$playableId;
    final /* synthetic */ String val$profileId;
    final /* synthetic */ VideoType val$videoType;
    
    RealmIncompleteVideoDetails$1(final String val$playableId, final VideoType val$videoType, final String val$profileId) {
        this.val$playableId = val$playableId;
        this.val$videoType = val$videoType;
        this.val$profileId = val$profileId;
    }
    
    public void execute(final Realm realm) {
        final RealmIncompleteVideoDetails realmIncompleteVideoDetails = (RealmIncompleteVideoDetails)realm.where(RealmIncompleteVideoDetails.class).equalTo("playableId", this.val$playableId).findFirst();
        RealmIncompleteVideoDetails realmIncompleteVideoDetails2;
        if (realmIncompleteVideoDetails == null) {
            realmIncompleteVideoDetails2 = realm.createObject(RealmIncompleteVideoDetails.class, this.val$playableId);
        }
        else {
            final String string = "Incomplete object was already in realm " + realmIncompleteVideoDetails.getPlayableId() + ";" + realmIncompleteVideoDetails.getProfileId() + ";" + realmIncompleteVideoDetails.getVideoType();
            Log.d(RealmIncompleteVideoDetails.TAG, string);
            LogUtils.reportErrorSafely(string);
            realmIncompleteVideoDetails2 = realmIncompleteVideoDetails;
        }
        realmIncompleteVideoDetails2.setVideoType(this.val$videoType.getKey());
        realmIncompleteVideoDetails2.setProfileId(this.val$profileId);
    }
}
