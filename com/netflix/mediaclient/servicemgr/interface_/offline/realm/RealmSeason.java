// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import io.realm.RealmSeasonRealmProxyInterface;
import io.realm.RealmObject;

public class RealmSeason extends RealmObject implements RealmSeasonRealmProxyInterface
{
    private String label;
    private int number;
    
    public String getLabel() {
        return this.realmGet$label();
    }
    
    public int getNumber() {
        return this.realmGet$number();
    }
    
    @Override
    public String realmGet$label() {
        return this.label;
    }
    
    @Override
    public int realmGet$number() {
        return this.number;
    }
    
    @Override
    public void realmSet$label(final String label) {
        this.label = label;
    }
    
    @Override
    public void realmSet$number(final int number) {
        this.number = number;
    }
    
    public void setLabel(final String s) {
        this.realmSet$label(s);
    }
    
    public void setNumber(final int n) {
        this.realmSet$number(n);
    }
}
