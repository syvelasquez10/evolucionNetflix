// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class RoleDetailsFrag$4 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ RoleDetailsFrag this$0;
    
    RoleDetailsFrag$4(final RoleDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return (View)new RoleDetailsFrag$ActorRelatedView(this.this$0, (Context)this.this$0.getActivity());
    }
}
