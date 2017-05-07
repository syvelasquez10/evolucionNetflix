// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

class StickyGridHeadersSimpleAdapterWrapper$HeaderData
{
    private int mCount;
    private int mRefPosition;
    final /* synthetic */ StickyGridHeadersSimpleAdapterWrapper this$0;
    
    public StickyGridHeadersSimpleAdapterWrapper$HeaderData(final StickyGridHeadersSimpleAdapterWrapper this$0, final int mRefPosition) {
        this.this$0 = this$0;
        this.mRefPosition = mRefPosition;
        this.mCount = 0;
    }
    
    public int getCount() {
        return this.mCount;
    }
    
    public int getRefPosition() {
        return this.mRefPosition;
    }
    
    public void incrementCount() {
        ++this.mCount;
    }
}
