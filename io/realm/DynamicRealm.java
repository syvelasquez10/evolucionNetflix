// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

public final class DynamicRealm extends BaseRealm
{
    private DynamicRealm(final RealmConfiguration realmConfiguration) {
        super(realmConfiguration);
    }
    
    static DynamicRealm createInstance(final RealmConfiguration realmConfiguration) {
        return new DynamicRealm(realmConfiguration);
    }
    
    public static DynamicRealm getInstance(final RealmConfiguration realmConfiguration) {
        if (realmConfiguration == null) {
            throw new IllegalArgumentException("A non-null RealmConfiguration must be provided");
        }
        return RealmCache.createRealmOrGetFromCache(realmConfiguration, DynamicRealm.class);
    }
}
