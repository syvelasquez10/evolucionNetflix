// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.View;
import android.view.ViewConfiguration;

final class StickyGridHeadersGridView$CheckForHeaderTap implements Runnable
{
    final /* synthetic */ StickyGridHeadersGridView this$0;
    
    StickyGridHeadersGridView$CheckForHeaderTap(final StickyGridHeadersGridView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mTouchMode == 0) {
            this.this$0.mTouchMode = 1;
            final View header = this.this$0.getHeaderAt(this.this$0.mMotionHeaderPosition);
            if (header != null && !this.this$0.mHeaderChildBeingPressed) {
                if (this.this$0.mDataChanged) {
                    this.this$0.mTouchMode = 2;
                    return;
                }
                header.setPressed(true);
                this.this$0.setPressed(true);
                this.this$0.refreshDrawableState();
                final int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                if (!this.this$0.isLongClickable()) {
                    this.this$0.mTouchMode = 2;
                    return;
                }
                if (this.this$0.mPendingCheckForLongPress == null) {
                    this.this$0.mPendingCheckForLongPress = new StickyGridHeadersGridView$CheckForHeaderLongPress(this.this$0, null);
                }
                this.this$0.mPendingCheckForLongPress.rememberWindowAttachCount();
                this.this$0.postDelayed((Runnable)this.this$0.mPendingCheckForLongPress, (long)longPressTimeout);
            }
        }
    }
}
