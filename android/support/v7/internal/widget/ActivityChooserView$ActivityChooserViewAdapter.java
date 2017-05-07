// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.ViewTreeObserver;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$dimen;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.PopupWindow$OnDismissListener;
import android.database.DataSetObserver;
import android.support.v7.widget.ListPopupWindow;
import android.widget.FrameLayout;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View$MeasureSpec;
import android.content.pm.PackageManager;
import android.widget.ImageView;
import android.support.v7.appcompat.R$string;
import android.support.v7.appcompat.R$id;
import android.widget.TextView;
import android.support.v7.appcompat.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.pm.ResolveInfo;
import android.widget.BaseAdapter;

class ActivityChooserView$ActivityChooserViewAdapter extends BaseAdapter
{
    private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
    private static final int ITEM_VIEW_TYPE_COUNT = 3;
    private static final int ITEM_VIEW_TYPE_FOOTER = 1;
    public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
    public static final int MAX_ACTIVITY_COUNT_UNLIMITED = Integer.MAX_VALUE;
    private ActivityChooserModel mDataModel;
    private boolean mHighlightDefaultActivity;
    private int mMaxActivityCount;
    private boolean mShowDefaultActivity;
    private boolean mShowFooterView;
    final /* synthetic */ ActivityChooserView this$0;
    
    private ActivityChooserView$ActivityChooserViewAdapter(final ActivityChooserView this$0) {
        this.this$0 = this$0;
        this.mMaxActivityCount = 4;
    }
    
    public int getActivityCount() {
        return this.mDataModel.getActivityCount();
    }
    
    public int getCount() {
        int activityCount;
        final int n = activityCount = this.mDataModel.getActivityCount();
        if (!this.mShowDefaultActivity) {
            activityCount = n;
            if (this.mDataModel.getDefaultActivity() != null) {
                activityCount = n - 1;
            }
        }
        int min = Math.min(activityCount, this.mMaxActivityCount);
        if (this.mShowFooterView) {
            ++min;
        }
        return min;
    }
    
    public ActivityChooserModel getDataModel() {
        return this.mDataModel;
    }
    
    public ResolveInfo getDefaultActivity() {
        return this.mDataModel.getDefaultActivity();
    }
    
    public int getHistorySize() {
        return this.mDataModel.getHistorySize();
    }
    
    public Object getItem(final int n) {
        switch (this.getItemViewType(n)) {
            default: {
                throw new IllegalArgumentException();
            }
            case 1: {
                return null;
            }
            case 0: {
                int n2 = n;
                if (!this.mShowDefaultActivity) {
                    n2 = n;
                    if (this.mDataModel.getDefaultActivity() != null) {
                        n2 = n + 1;
                    }
                }
                return this.mDataModel.getActivity(n2);
            }
        }
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getItemViewType(final int n) {
        if (this.mShowFooterView && n == this.getCount() - 1) {
            return 1;
        }
        return 0;
    }
    
    public int getMaxActivityCount() {
        return this.mMaxActivityCount;
    }
    
    public boolean getShowDefaultActivity() {
        return this.mShowDefaultActivity;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = null;
        switch (this.getItemViewType(n)) {
            default: {
                throw new IllegalArgumentException();
            }
            case 1: {
                if (view != null) {
                    inflate = view;
                    if (view.getId() == 1) {
                        break;
                    }
                }
                inflate = LayoutInflater.from(this.this$0.getContext()).inflate(R$layout.abc_activity_chooser_view_list_item, viewGroup, false);
                inflate.setId(1);
                ((TextView)inflate.findViewById(R$id.title)).setText((CharSequence)this.this$0.getContext().getString(R$string.abc_activity_chooser_view_see_all));
                break;
            }
            case 0: {
                View inflate2 = null;
                Label_0144: {
                    if (view != null) {
                        inflate2 = view;
                        if (view.getId() == R$id.list_item) {
                            break Label_0144;
                        }
                    }
                    inflate2 = LayoutInflater.from(this.this$0.getContext()).inflate(R$layout.abc_activity_chooser_view_list_item, viewGroup, false);
                }
                final PackageManager packageManager = this.this$0.getContext().getPackageManager();
                final ImageView imageView = (ImageView)inflate2.findViewById(R$id.icon);
                final ResolveInfo resolveInfo = (ResolveInfo)this.getItem(n);
                imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
                ((TextView)inflate2.findViewById(R$id.title)).setText(resolveInfo.loadLabel(packageManager));
                inflate = inflate2;
                if (!this.mShowDefaultActivity) {
                    break;
                }
                inflate = inflate2;
                if (n != 0) {
                    break;
                }
                inflate = inflate2;
                if (this.mHighlightDefaultActivity) {
                    return inflate2;
                }
                break;
            }
        }
        return inflate;
    }
    
    public int getViewTypeCount() {
        return 3;
    }
    
    public int measureContentWidth() {
        int i = 0;
        final int mMaxActivityCount = this.mMaxActivityCount;
        this.mMaxActivityCount = Integer.MAX_VALUE;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int count = this.getCount();
        View view = null;
        int max = 0;
        while (i < count) {
            view = this.getView(i, view, null);
            view.measure(measureSpec, measureSpec2);
            max = Math.max(max, view.getMeasuredWidth());
            ++i;
        }
        this.mMaxActivityCount = mMaxActivityCount;
        return max;
    }
    
    public void setDataModel(final ActivityChooserModel mDataModel) {
        final ActivityChooserModel dataModel = this.this$0.mAdapter.getDataModel();
        if (dataModel != null && this.this$0.isShown()) {
            dataModel.unregisterObserver((Object)this.this$0.mModelDataSetOberver);
        }
        if ((this.mDataModel = mDataModel) != null && this.this$0.isShown()) {
            mDataModel.registerObserver((Object)this.this$0.mModelDataSetOberver);
        }
        this.notifyDataSetChanged();
    }
    
    public void setMaxActivityCount(final int mMaxActivityCount) {
        if (this.mMaxActivityCount != mMaxActivityCount) {
            this.mMaxActivityCount = mMaxActivityCount;
            this.notifyDataSetChanged();
        }
    }
    
    public void setShowDefaultActivity(final boolean mShowDefaultActivity, final boolean mHighlightDefaultActivity) {
        if (this.mShowDefaultActivity != mShowDefaultActivity || this.mHighlightDefaultActivity != mHighlightDefaultActivity) {
            this.mShowDefaultActivity = mShowDefaultActivity;
            this.mHighlightDefaultActivity = mHighlightDefaultActivity;
            this.notifyDataSetChanged();
        }
    }
    
    public void setShowFooterView(final boolean mShowFooterView) {
        if (this.mShowFooterView != mShowFooterView) {
            this.mShowFooterView = mShowFooterView;
            this.notifyDataSetChanged();
        }
    }
}
