// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

final class BaseRealm$ThreadLocalRealmObjectContext extends ThreadLocal<BaseRealm$RealmObjectContext>
{
    @Override
    protected BaseRealm$RealmObjectContext initialValue() {
        return new BaseRealm$RealmObjectContext();
    }
}
