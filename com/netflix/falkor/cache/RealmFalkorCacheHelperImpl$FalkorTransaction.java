// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.google.gson.JsonSyntaxException;
import com.netflix.mediaclient.util.LogUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.ThreadUtils;
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
import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.util.Arrays;
import java.util.HashSet;
import io.realm.Realm;
import io.realm.RealmModel;
import java.util.ArrayList;
import com.google.gson.JsonElement;
import java.util.Date;
import io.realm.Realm$Transaction;

class RealmFalkorCacheHelperImpl$FalkorTransaction implements Realm$Transaction
{
    private final Class clazz;
    private final Date expiry;
    private final JsonElement json;
    private final ArrayList<String> path;
    
    private RealmFalkorCacheHelperImpl$FalkorTransaction(final Class<? extends RealmModel> clazz, final ArrayList<String> path, final JsonElement json) {
        this.clazz = clazz;
        this.path = path;
        this.json = json;
        this.expiry = createExpirationDate(json, clazz);
    }
    
    public void execute(final Realm ex) {
        try {
            final boolean sentinel = this.json.isJsonObject() && this.json.getAsJsonObject().has("_sentinel");
            final FalkorCache$FalkorCacheable falkorCache$FalkorCacheable = this.clazz.newInstance();
            falkorCache$FalkorCacheable.setPath(this.path.toString());
            falkorCache$FalkorCacheable.setPayload(this.json.toString());
            falkorCache$FalkorCacheable.setSentinel(sentinel);
            falkorCache$FalkorCacheable.setLastModifiedTime(System.currentTimeMillis());
            if (this.expiry != null) {
                falkorCache$FalkorCacheable.setExpiry(this.expiry);
            }
            if (this.clazz == FalkorRealmCacheLruBased.class) {
                falkorCache$FalkorCacheable.setVolatile(RealmFalkorCacheHelperImpl.VOLATILE_LEAF_NODES.contains(this.path.get(this.path.size() - 1)));
            }
            ((Realm)ex).copyToRealmOrUpdate(falkorCache$FalkorCacheable);
            if (falkorCache$FalkorCacheable instanceof FalkorCache$FalkorEvictable) {
                FalkorCache$LruBackup.push((FalkorCache$FalkorEvictable)falkorCache$FalkorCacheable);
            }
            FalkorCache.getMonitor().add(falkorCache$FalkorCacheable.getPayload());
        }
        catch (InstantiationException ex2) {}
        catch (IllegalAccessException ex) {
            goto Label_0173;
        }
    }
}
