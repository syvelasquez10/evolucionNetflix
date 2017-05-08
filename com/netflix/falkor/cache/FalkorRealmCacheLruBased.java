// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import io.realm.internal.RealmObjectProxy;
import java.util.Date;
import io.realm.FalkorRealmCacheLruBasedRealmProxyInterface;

public class FalkorRealmCacheLruBased implements FalkorCache$FalkorCacheable, FalkorCache$FalkorEvictable, FalkorRealmCacheLruBasedRealmProxyInterface
{
    private Date expiry;
    private boolean isVolatile;
    private long lastModified;
    private String path;
    private String payload;
    private boolean sentinel;
    
    public FalkorRealmCacheLruBased() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy)this).realm$injectObjectContext();
        }
    }
    
    @Override
    public Date getExpiry() {
        return this.realmGet$expiry();
    }
    
    @Override
    public long getLastModifiedTime() {
        return this.realmGet$lastModified();
    }
    
    @Override
    public String getPath() {
        return this.realmGet$path();
    }
    
    @Override
    public String getPayload() {
        return this.realmGet$payload();
    }
    
    @Override
    public boolean getSentinel() {
        return this.realmGet$sentinel();
    }
    
    @Override
    public boolean getVolatile() {
        return this.realmGet$isVolatile();
    }
    
    public Date realmGet$expiry() {
        return this.expiry;
    }
    
    public boolean realmGet$isVolatile() {
        return this.isVolatile;
    }
    
    public long realmGet$lastModified() {
        return this.lastModified;
    }
    
    public String realmGet$path() {
        return this.path;
    }
    
    public String realmGet$payload() {
        return this.payload;
    }
    
    public boolean realmGet$sentinel() {
        return this.sentinel;
    }
    
    public void realmSet$expiry(final Date expiry) {
        this.expiry = expiry;
    }
    
    public void realmSet$isVolatile(final boolean isVolatile) {
        this.isVolatile = isVolatile;
    }
    
    public void realmSet$lastModified(final long lastModified) {
        this.lastModified = lastModified;
    }
    
    public void realmSet$path(final String path) {
        this.path = path;
    }
    
    public void realmSet$payload(final String payload) {
        this.payload = payload;
    }
    
    public void realmSet$sentinel(final boolean sentinel) {
        this.sentinel = sentinel;
    }
    
    @Override
    public void setExpiry(final Date date) {
        this.realmSet$expiry(date);
    }
    
    @Override
    public void setLastModifiedTime(final long n) {
        this.realmSet$lastModified(n);
    }
    
    @Override
    public void setPath(final String s) {
        this.realmSet$path(s);
    }
    
    @Override
    public void setPayload(final String s) {
        this.realmSet$payload(s);
    }
    
    @Override
    public void setSentinel(final boolean b) {
        this.realmSet$sentinel(b);
    }
    
    @Override
    public void setVolatile(final boolean b) {
        this.realmSet$isVolatile(b);
    }
}
