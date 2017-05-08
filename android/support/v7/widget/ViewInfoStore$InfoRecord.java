// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.util.Pools$SimplePool;
import android.support.v4.util.Pools$Pool;

class ViewInfoStore$InfoRecord
{
    static Pools$Pool<ViewInfoStore$InfoRecord> sPool;
    int flags;
    RecyclerView$ItemAnimator$ItemHolderInfo postInfo;
    RecyclerView$ItemAnimator$ItemHolderInfo preInfo;
    
    static {
        ViewInfoStore$InfoRecord.sPool = new Pools$SimplePool<ViewInfoStore$InfoRecord>(20);
    }
    
    static void drainCache() {
        while (ViewInfoStore$InfoRecord.sPool.acquire() != null) {}
    }
    
    static ViewInfoStore$InfoRecord obtain() {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord;
        if ((viewInfoStore$InfoRecord = ViewInfoStore$InfoRecord.sPool.acquire()) == null) {
            viewInfoStore$InfoRecord = new ViewInfoStore$InfoRecord();
        }
        return viewInfoStore$InfoRecord;
    }
    
    static void recycle(final ViewInfoStore$InfoRecord viewInfoStore$InfoRecord) {
        viewInfoStore$InfoRecord.flags = 0;
        viewInfoStore$InfoRecord.preInfo = null;
        viewInfoStore$InfoRecord.postInfo = null;
        ViewInfoStore$InfoRecord.sPool.release(viewInfoStore$InfoRecord);
    }
}
