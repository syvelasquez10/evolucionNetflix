// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.model.BasicLoMo;

class BaseProgressivePagerAdapter$Memento
{
    final BasePaginatedAdapter$Memento adapterMemento;
    final int currDataIndex;
    final boolean hasMoreData;
    final BasicLoMo lomo;
    
    protected BaseProgressivePagerAdapter$Memento(final BasicLoMo lomo, final boolean hasMoreData, final int currDataIndex, final BasePaginatedAdapter<?> basePaginatedAdapter) {
        this.lomo = lomo;
        this.hasMoreData = hasMoreData;
        this.currDataIndex = currDataIndex;
        this.adapterMemento = basePaginatedAdapter.saveToMemento();
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("lomo: ");
        String title;
        if (this.lomo == null) {
            title = "no lomo";
        }
        else {
            title = this.lomo.getTitle();
        }
        return append.append(title).append(", hasMoreData: ").append(this.hasMoreData).append(", currDataIndex: ").append(this.currDataIndex).append(", adapter: ").append(this.adapterMemento).toString();
    }
}
