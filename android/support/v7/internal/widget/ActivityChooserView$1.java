// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View$MeasureSpec;
import android.view.ViewTreeObserver;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.appcompat.R$string;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View;
import android.widget.ListAdapter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$dimen;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$layout;
import android.view.LayoutInflater;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.PopupWindow$OnDismissListener;
import android.support.v7.widget.ListPopupWindow;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.ViewGroup;
import android.database.DataSetObserver;

class ActivityChooserView$1 extends DataSetObserver
{
    final /* synthetic */ ActivityChooserView this$0;
    
    ActivityChooserView$1(final ActivityChooserView this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        super.onChanged();
        this.this$0.mAdapter.notifyDataSetChanged();
    }
    
    public void onInvalidated() {
        super.onInvalidated();
        this.this$0.mAdapter.notifyDataSetInvalidated();
    }
}
