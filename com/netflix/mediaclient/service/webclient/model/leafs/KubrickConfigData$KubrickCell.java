// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

public enum KubrickConfigData$KubrickCell
{
    CONTROL(1), 
    KUBRICK_HERO_IMAGES(2), 
    KUBRICK_HIGH_DENSITY(3);
    
    private final int cellId;
    
    private KubrickConfigData$KubrickCell(final int cellId) {
        this.cellId = cellId;
    }
    
    private static KubrickConfigData$KubrickCell fromInt(final int n) {
        final KubrickConfigData$KubrickCell[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final KubrickConfigData$KubrickCell kubrickConfigData$KubrickCell = values[i];
            if (kubrickConfigData$KubrickCell.cellId == n) {
                return kubrickConfigData$KubrickCell;
            }
        }
        return null;
    }
}
