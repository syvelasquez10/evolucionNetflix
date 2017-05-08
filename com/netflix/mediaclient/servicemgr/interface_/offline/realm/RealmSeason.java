// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import io.realm.RealmSeasonRealmProxyInterface;
import io.realm.RealmObject;

public class RealmSeason extends RealmObject implements RealmSeasonRealmProxyInterface
{
    private int number;
    private String title;
    
    public int getNumber() {
        return this.realmGet$number();
    }
    
    public String getTitle() {
        return this.realmGet$title();
    }
    
    public int realmGet$number() {
        return this.number;
    }
    
    public String realmGet$title() {
        return this.title;
    }
    
    public void realmSet$number(final int number) {
        this.number = number;
    }
    
    public void realmSet$title(final String title) {
        this.title = title;
    }
    
    public void setNumber(final int n) {
        this.realmSet$number(n);
    }
    
    public void setTitle(final String s) {
        this.realmSet$title(s);
    }
}
