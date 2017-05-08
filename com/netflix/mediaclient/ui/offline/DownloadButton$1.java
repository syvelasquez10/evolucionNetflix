// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View$OnLongClickListener;

class DownloadButton$1 implements View$OnLongClickListener
{
    final /* synthetic */ DownloadButton this$0;
    
    DownloadButton$1(final DownloadButton this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onLongClick(final View view) {
        CharSequence charSequence = null;
        switch (DownloadButton$3.$SwitchMap$com$netflix$mediaclient$ui$offline$DownloadButton$ButtonState[((DownloadButton)view).getState().ordinal()]) {
            default: {
                charSequence = null;
                break;
            }
            case 1:
            case 2:
            case 3: {
                charSequence = this.this$0.getResources().getString(2131231315);
                break;
            }
            case 4: {
                charSequence = this.this$0.getResources().getString(2131231320);
                break;
            }
            case 5: {
                charSequence = this.this$0.getResources().getString(2131231323);
                break;
            }
            case 6: {
                charSequence = null;
                break;
            }
            case 7: {
                charSequence = this.this$0.getResources().getString(2131230925);
                break;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            Snackbar.make(view, charSequence, -1).show();
        }
        return true;
    }
}
