// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.util.SparseIntArray;
import android.util.SparseArray;

public final class UiUtils
{
    public static final int NUM_LOMOS_TO_FETCH_PER_BATCH = 20;
    private static final SparseArray<SparseIntArray> numCWVideosPerPageTable;
    private static final SparseArray<SparseIntArray> numVideosPerPageTable;
    
    static {
        numVideosPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray.put(2, 3);
        sparseIntArray.put(3, 4);
        sparseIntArray.put(4, 4);
        UiUtils.numVideosPerPageTable.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, 3);
        sparseIntArray2.put(2, 4);
        sparseIntArray2.put(3, 5);
        sparseIntArray2.put(4, 6);
        UiUtils.numVideosPerPageTable.put(2, (Object)sparseIntArray2);
        numCWVideosPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray3.put(1, 1);
        sparseIntArray3.put(2, 1);
        sparseIntArray3.put(3, 2);
        sparseIntArray3.put(4, 2);
        UiUtils.numCWVideosPerPageTable.put(1, (Object)sparseIntArray3);
        final SparseIntArray sparseIntArray4 = new SparseIntArray();
        sparseIntArray4.put(1, 2);
        sparseIntArray4.put(2, 2);
        sparseIntArray4.put(3, 3);
        sparseIntArray4.put(4, 3);
        UiUtils.numCWVideosPerPageTable.put(2, (Object)sparseIntArray4);
    }
    
    public static int computeNumCWItemsPerPage(final int n, final int n2) {
        return ((SparseIntArray)UiUtils.numCWVideosPerPageTable.get(n)).get(n2);
    }
    
    public static int computeNumCWVideosToFetchPerBatch(final int n) {
        return Math.max(((SparseIntArray)UiUtils.numCWVideosPerPageTable.get(1)).get(n) * ((SparseIntArray)UiUtils.numCWVideosPerPageTable.get(2)).get(n), 4);
    }
    
    public static int computeNumItemsPerPage(final int n, final int n2) {
        return ((SparseIntArray)UiUtils.numVideosPerPageTable.get(n)).get(n2);
    }
    
    public static int computeNumVideosToFetchPerBatch(final int n) {
        return ((SparseIntArray)UiUtils.numVideosPerPageTable.get(1)).get(n) * ((SparseIntArray)UiUtils.numVideosPerPageTable.get(2)).get(n);
    }
}
