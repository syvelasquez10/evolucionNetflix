// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.widget.AdapterView$OnItemClickListener;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListAdapter;
import android.support.v7.widget.ListPopupWindow;

class SpinnerCompat$DropdownPopup extends ListPopupWindow implements SpinnerCompat$SpinnerPopup
{
    private ListAdapter mAdapter;
    private CharSequence mHintText;
    final /* synthetic */ SpinnerCompat this$0;
    
    public SpinnerCompat$DropdownPopup(final SpinnerCompat spinnerCompat, final Context context, final AttributeSet set, final int n) {
        this.this$0 = spinnerCompat;
        super(context, set, n);
        this.setAnchorView((View)spinnerCompat);
        this.setModal(true);
        this.setPromptPosition(0);
        this.setOnItemClickListener((AdapterView$OnItemClickListener)new SpinnerCompat$DropdownPopup$1(this, spinnerCompat));
    }
    
    @Override
    public void setAdapter(final ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.mAdapter = listAdapter;
    }
    
    @Override
    public void setPromptText(final CharSequence mHintText) {
        this.mHintText = mHintText;
    }
}
