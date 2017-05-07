// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.ListView;
import android.widget.ArrayAdapter;

class AlertController$AlertParams$1 extends ArrayAdapter<CharSequence>
{
    final /* synthetic */ AlertController$AlertParams this$0;
    final /* synthetic */ ListView val$listView;
    
    AlertController$AlertParams$1(final AlertController$AlertParams this$0, final Context context, final int n, final int n2, final CharSequence[] array, final ListView val$listView) {
        this.this$0 = this$0;
        this.val$listView = val$listView;
        super(context, n, n2, (Object[])array);
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        view = super.getView(n, view, viewGroup);
        if (this.this$0.mCheckedItems != null && this.this$0.mCheckedItems[n]) {
            this.val$listView.setItemChecked(n, true);
        }
        return view;
    }
}
