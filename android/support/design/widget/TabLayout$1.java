// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.view.View$OnClickListener;

class TabLayout$1 implements View$OnClickListener
{
    final /* synthetic */ TabLayout this$0;
    
    TabLayout$1(final TabLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        ((TabLayout$TabView)view).getTab().select();
    }
}
