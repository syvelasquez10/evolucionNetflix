// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

public enum KubrickKidsConfigData$KubrickKidsCell
{
    CONTROL(1), 
    CONTROL_FILTERED(2), 
    HERO_IMAGES(3), 
    HIGH_DENSITY(4), 
    HIGH_DENSITY_VERTICAL(5);
    
    private final int cellId;
    
    private KubrickKidsConfigData$KubrickKidsCell(final int cellId) {
        this.cellId = cellId;
    }
    
    private static KubrickKidsConfigData$KubrickKidsCell fromInt(final int n) {
        final KubrickKidsConfigData$KubrickKidsCell[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final KubrickKidsConfigData$KubrickKidsCell kubrickKidsConfigData$KubrickKidsCell = values[i];
            if (kubrickKidsConfigData$KubrickKidsCell.cellId == n) {
                return kubrickKidsConfigData$KubrickKidsCell;
            }
        }
        return null;
    }
}
