// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import io.realm.internal.RealmObjectProxy;
import java.util.Date;
import io.realm.RealmModel;
import io.realm.FalkorRealmCacheHomeLolomoRealmProxyInterface;

public class FalkorRealmCacheHomeLolomo implements FalkorRealmCacheHomeLolomoRealmProxyInterface, RealmModel
{
    private Date expiry;
    private String lolomosRef;
    
    public FalkorRealmCacheHomeLolomo() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy)this).realm$injectObjectContext();
        }
    }
    
    public Date getExpiry() {
        return this.realmGet$expiry();
    }
    
    public String getLolomosRef() {
        return this.realmGet$lolomosRef();
    }
    
    public Date realmGet$expiry() {
        return this.expiry;
    }
    
    public String realmGet$lolomosRef() {
        return this.lolomosRef;
    }
    
    public void realmSet$expiry(final Date expiry) {
        this.expiry = expiry;
    }
    
    public void realmSet$lolomosRef(final String lolomosRef) {
        this.lolomosRef = lolomosRef;
    }
    
    public void setExpiry(final Date date) {
        this.realmSet$expiry(date);
    }
    
    public void setLolomosRef(final String s) {
        this.realmSet$lolomosRef(s);
    }
}
