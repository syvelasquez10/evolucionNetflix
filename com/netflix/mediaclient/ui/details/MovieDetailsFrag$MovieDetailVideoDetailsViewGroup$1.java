// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import android.view.View$OnSystemUiVisibilityChangeListener;

class MovieDetailsFrag$MovieDetailVideoDetailsViewGroup$1 implements View$OnSystemUiVisibilityChangeListener
{
    final /* synthetic */ MovieDetailsFrag$MovieDetailVideoDetailsViewGroup this$1;
    final /* synthetic */ MovieDetailsFrag val$this$0;
    
    MovieDetailsFrag$MovieDetailVideoDetailsViewGroup$1(final MovieDetailsFrag$MovieDetailVideoDetailsViewGroup this$1, final MovieDetailsFrag val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    public void onSystemUiVisibilityChange(final int n) {
        if (n == 0) {
            ((StickyGridHeadersGridView)this.this$1.this$0.gridView).invalidateViews();
        }
    }
}
