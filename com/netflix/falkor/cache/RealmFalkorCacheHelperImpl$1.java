// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.google.gson.JsonSyntaxException;
import com.netflix.mediaclient.util.LogUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.ThreadUtils;
import java.util.ArrayList;
import com.netflix.model.leafs.ListOfListOfGenres;
import com.netflix.falkor.Sentinel;
import com.netflix.model.leafs.BaseFalkorLeafItem;
import com.netflix.falkor.PQL;
import com.netflix.falkor.Undefined;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchNode;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import io.realm.RealmModel;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.util.Arrays;
import java.util.HashSet;
import com.netflix.mediaclient.Log;
import io.realm.Realm;
import com.google.gson.JsonElement;
import io.realm.Realm$Transaction;

class RealmFalkorCacheHelperImpl$1 implements Realm$Transaction
{
    final /* synthetic */ RealmFalkorCacheHelperImpl this$0;
    final /* synthetic */ JsonElement val$jsonValue;
    final /* synthetic */ String val$payload;
    
    RealmFalkorCacheHelperImpl$1(final RealmFalkorCacheHelperImpl this$0, final String val$payload, final JsonElement val$jsonValue) {
        this.this$0 = this$0;
        this.val$payload = val$payload;
        this.val$jsonValue = val$jsonValue;
    }
    
    public void execute(final Realm realm) {
        final FalkorRealmCacheHomeLolomo falkorRealmCacheHomeLolomo = (FalkorRealmCacheHomeLolomo)this.this$0.mRealm.where(FalkorRealmCacheHomeLolomo.class).findFirst();
        FalkorRealmCacheHomeLolomo falkorRealmCacheHomeLolomo2;
        if (falkorRealmCacheHomeLolomo == null) {
            falkorRealmCacheHomeLolomo2 = realm.createObject(FalkorRealmCacheHomeLolomo.class);
        }
        else {
            Log.i("FalkorCache.RealmHelper", "Overwriting non-expired lolomo %s (expire=%s)", falkorRealmCacheHomeLolomo.getLolomosRef(), falkorRealmCacheHomeLolomo.getExpiry().toString());
            falkorRealmCacheHomeLolomo2 = falkorRealmCacheHomeLolomo;
        }
        falkorRealmCacheHomeLolomo2.setLolomosRef(this.val$payload);
        falkorRealmCacheHomeLolomo2.setExpiry(createExpirationDate(this.val$jsonValue, (Class<? extends RealmModel>)FalkorRealmCacheHomeLolomo.class));
    }
}
