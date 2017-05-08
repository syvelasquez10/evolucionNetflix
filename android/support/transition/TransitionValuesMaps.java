// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.View;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.annotation.TargetApi;

@TargetApi(14)
class TransitionValuesMaps
{
    public SparseArray<TransitionValues> idValues;
    public LongSparseArray<TransitionValues> itemIdValues;
    public ArrayMap<View, TransitionValues> viewValues;
    
    TransitionValuesMaps() {
        this.viewValues = new ArrayMap<View, TransitionValues>();
        this.idValues = (SparseArray<TransitionValues>)new SparseArray();
        this.itemIdValues = new LongSparseArray<TransitionValues>();
    }
}
