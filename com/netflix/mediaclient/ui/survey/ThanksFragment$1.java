// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.survey;

class ThanksFragment$1 implements Runnable
{
    final /* synthetic */ ThanksFragment this$0;
    
    ThanksFragment$1(final ThanksFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.getActivity() != null && !this.this$0.getActivity().isFinishing()) {
            this.this$0.getActivity().overridePendingTransition(17432577, 17432576);
            this.this$0.getActivity().finish();
        }
    }
}
