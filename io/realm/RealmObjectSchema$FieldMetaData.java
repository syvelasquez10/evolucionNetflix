// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

class RealmObjectSchema$FieldMetaData
{
    public final boolean defaultNullable;
    public final RealmFieldType realmType;
    
    public RealmObjectSchema$FieldMetaData(final RealmFieldType realmType, final boolean defaultNullable) {
        this.realmType = realmType;
        this.defaultNullable = defaultNullable;
    }
}
