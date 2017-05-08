// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

public enum ABTestConfig$Cell
{
    CELL_EIGHT(8), 
    CELL_FIVE(5), 
    CELL_FOUR(4), 
    CELL_NINE(9), 
    CELL_ONE(1), 
    CELL_SEVEN(7), 
    CELL_SIX(6), 
    CELL_TEN(10), 
    CELL_THREE(3), 
    CELL_TWO(2);
    
    private final int cellId;
    
    private ABTestConfig$Cell(final int cellId) {
        this.cellId = cellId;
    }
    
    public static ABTestConfig$Cell fromInt(final int n) {
        final ABTestConfig$Cell[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final ABTestConfig$Cell abTestConfig$Cell = values[i];
            if (abTestConfig$Cell.cellId == n) {
                return abTestConfig$Cell;
            }
        }
        return null;
    }
    
    public int getCellId() {
        return this.cellId;
    }
}
