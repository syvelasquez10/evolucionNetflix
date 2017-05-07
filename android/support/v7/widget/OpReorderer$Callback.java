// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

interface OpReorderer$Callback
{
    AdapterHelper$UpdateOp obtainUpdateOp(final int p0, final int p1, final int p2);
    
    void recycleUpdateOp(final AdapterHelper$UpdateOp p0);
}
