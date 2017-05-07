// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

public class LoMoViewPager$State
{
    LoMoViewPagerAdapter$Memento adapterMemento;
    int currPage;
    
    @Override
    public String toString() {
        return "Page: " + this.currPage + ", adapter: " + this.adapterMemento.toString();
    }
}
