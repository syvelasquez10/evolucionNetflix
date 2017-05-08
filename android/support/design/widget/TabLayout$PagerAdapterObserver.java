// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.database.DataSetObserver;

class TabLayout$PagerAdapterObserver extends DataSetObserver
{
    final /* synthetic */ TabLayout this$0;
    
    TabLayout$PagerAdapterObserver(final TabLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        this.this$0.populateFromPagerAdapter();
    }
    
    public void onInvalidated() {
        this.this$0.populateFromPagerAdapter();
    }
}
