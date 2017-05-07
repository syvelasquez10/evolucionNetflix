// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.ListAdapter;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu$GenresListAdapter;
import com.netflix.mediaclient.ui.social.notifications.NotificationsFrag$NotificationsListAdapter;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListView;

public class StaticListView extends ListView
{
    private boolean isStatic;
    
    public StaticListView(final Context context) {
        super(context);
        this.isStatic = true;
    }
    
    public StaticListView(final Context context, final AttributeSet set) {
        super(context, set);
        this.isStatic = true;
    }
    
    public StaticListView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.isStatic = true;
    }
    
    public void onMeasure(final int n, final int n2) {
        while (true) {
            try {
                if (this.isStatic) {
                    super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
                }
                else {
                    super.onMeasure(n, n2);
                }
                this.getLayoutParams().height = this.getMeasuredHeight();
            }
            catch (Exception ex) {
                final StringBuffer sb = new StringBuffer(String.format("SPY-7985 - exception occured. isStatic: %s ", this.isStatic));
                final ListAdapter adapter = this.getAdapter();
                if (adapter instanceof NotificationsFrag$NotificationsListAdapter) {
                    sb.append("Adapter is NotificationsListAdapter, total: " + adapter.getCount());
                }
                else if (adapter instanceof StandardSlidingMenu$GenresListAdapter) {
                    sb.append("Adapter is GenresListAdapter, total: " + adapter.getCount());
                }
                else {
                    sb.append("Adapter is just: " + adapter);
                }
                ErrorLoggingManager.logHandledException(sb.toString());
                continue;
            }
            break;
        }
    }
    
    public void setAsStatic(final boolean isStatic) {
        if (this.isStatic != isStatic) {
            this.isStatic = isStatic;
            this.requestLayout();
        }
    }
}
