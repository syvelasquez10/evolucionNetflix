// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import android.view.View$OnClickListener;

class RoleDetailsFrag$ActorDetailsView$1 implements View$OnClickListener
{
    final /* synthetic */ RoleDetailsFrag$ActorDetailsView this$1;
    
    RoleDetailsFrag$ActorDetailsView$1(final RoleDetailsFrag$ActorDetailsView this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        this.this$1.this$0.getActivity().onBackPressed();
    }
}
