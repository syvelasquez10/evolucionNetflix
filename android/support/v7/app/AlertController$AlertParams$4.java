// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.DialogInterface$OnClickListener;
import android.view.KeyEvent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.appcompat.R$id;
import android.text.TextUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.content.Context;
import android.os.Message;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.widget.ListAdapter;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView$OnItemClickListener;

class AlertController$AlertParams$4 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ AlertController$AlertParams this$0;
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ ListView val$listView;
    
    AlertController$AlertParams$4(final AlertController$AlertParams this$0, final ListView val$listView, final AlertController val$dialog) {
        this.this$0 = this$0;
        this.val$listView = val$listView;
        this.val$dialog = val$dialog;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (this.this$0.mCheckedItems != null) {
            this.this$0.mCheckedItems[n] = this.val$listView.isItemChecked(n);
        }
        this.this$0.mOnCheckboxClickListener.onClick((DialogInterface)this.val$dialog.mDialog, n, this.val$listView.isItemChecked(n));
    }
}
