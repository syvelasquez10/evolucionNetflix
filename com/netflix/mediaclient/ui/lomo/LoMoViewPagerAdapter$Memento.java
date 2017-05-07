// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;

class LoMoViewPagerAdapter$Memento
{
    final BaseProgressiveRowAdapter$Memento adapterMemento;
    final BasicLoMo lomo;
    final LoMoViewPagerAdapter$Type preErrorState;
    final LoMoViewPagerAdapter$Type state;
    
    protected LoMoViewPagerAdapter$Memento(final LoMoViewPagerAdapter$Type state, final LoMoViewPagerAdapter$Type preErrorState, final BasicLoMo lomo, final RowAdapter rowAdapter) {
        this.state = state;
        this.preErrorState = preErrorState;
        this.lomo = lomo;
        this.adapterMemento = (BaseProgressiveRowAdapter$Memento)rowAdapter.saveToMemento();
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("lomo: ");
        String title;
        if (this.lomo == null) {
            title = "n/a";
        }
        else {
            title = this.lomo.getTitle();
        }
        return append.append(title).append(", state: ").append(this.state).append(", adapter: ").append(this.adapterMemento).toString();
    }
}
