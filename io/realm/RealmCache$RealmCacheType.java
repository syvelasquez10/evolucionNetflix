// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

enum RealmCache$RealmCacheType
{
    DYNAMIC_REALM, 
    TYPED_REALM;
    
    static RealmCache$RealmCacheType valueOf(final Class<? extends BaseRealm> clazz) {
        if (clazz == Realm.class) {
            return RealmCache$RealmCacheType.TYPED_REALM;
        }
        if (clazz == DynamicRealm.class) {
            return RealmCache$RealmCacheType.DYNAMIC_REALM;
        }
        throw new IllegalArgumentException("The type of Realm class must be Realm or DynamicRealm.");
    }
}
