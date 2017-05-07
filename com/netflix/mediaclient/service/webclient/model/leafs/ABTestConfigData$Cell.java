// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

public enum ABTestConfigData$Cell
{
    CELL_FIVE(5), 
    CELL_FOUR(4), 
    CELL_ONE(1), 
    CELL_THREE(3), 
    CELL_TWO(2);
    
    private final int cellId;
    
    private ABTestConfigData$Cell(final int cellId) {
        this.cellId = cellId;
    }
    
    private static ABTestConfigData$Cell fromInt(final int n) {
        final ABTestConfigData$Cell[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final ABTestConfigData$Cell abTestConfigData$Cell = values[i];
            if (abTestConfigData$Cell.cellId == n) {
                return abTestConfigData$Cell;
            }
        }
        return null;
    }
}
