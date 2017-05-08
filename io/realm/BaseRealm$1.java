// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.SharedRealm$SchemaVersionListener;

class BaseRealm$1 implements SharedRealm$SchemaVersionListener
{
    final /* synthetic */ BaseRealm this$0;
    
    BaseRealm$1(final BaseRealm this$0) {
        this.this$0 = this$0;
    }
    
    public void onSchemaVersionChanged(final long n) {
        RealmCache.updateSchemaCache((Realm)this.this$0);
    }
}
