// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

final class Realm$1 implements Realm$Transaction
{
    @Override
    public void execute(final Realm realm) {
        realm.setVersion(realm.configuration.getSchemaVersion());
    }
}
