// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import java.util.List;

class BasePaginatedAdapter$Memento<T>
{
    final String currTitle;
    final List<T> data;
    final int listViewPos;
    
    protected BasePaginatedAdapter$Memento(final List<T> data, final int listViewPos, final String currTitle) {
        this.data = data;
        this.listViewPos = listViewPos;
        this.currTitle = currTitle;
    }
    
    @Override
    public String toString() {
        return "title: " + this.currTitle + ", data size: " + this.data.size() + ", listViewPos: " + this.listViewPos;
    }
}
