// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import java.io.File;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineImageUtils;
import com.netflix.mediaclient.service.NetflixService;
import io.realm.RealmProfileRealmProxyInterface;
import io.realm.RealmModel;
import io.realm.Realm;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import io.realm.Realm$Transaction;

final class RealmProfile$1 implements Realm$Transaction
{
    final /* synthetic */ UserProfile val$curProfile;
    final /* synthetic */ String val$curProfileId;
    
    RealmProfile$1(final String val$curProfileId, final UserProfile val$curProfile) {
        this.val$curProfileId = val$curProfileId;
        this.val$curProfile = val$curProfile;
    }
    
    @Override
    public void execute(final Realm realm) {
        if (RealmUtils.idNotExists(realm, RealmProfile.class, this.val$curProfileId)) {
            realm.createObject(RealmProfile.class, this.val$curProfileId).fillForRealm(this.val$curProfile);
        }
    }
}
