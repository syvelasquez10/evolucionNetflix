// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v7.internal.view.menu.i;
import android.support.v7.appcompat.R$id;
import android.support.v7.internal.view.menu.y;
import android.view.Menu;
import android.support.v7.widget.Toolbar$LayoutParams;
import android.util.Log;
import android.content.Context;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$drawable;
import android.support.v7.appcompat.R$string;
import android.support.v7.internal.app.WindowCallback;
import android.support.v7.widget.Toolbar;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ActionMenuPresenter;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class ToolbarWidgetWrapper$3 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ ToolbarWidgetWrapper this$0;
    
    ToolbarWidgetWrapper$3(final ToolbarWidgetWrapper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.mToolbar.setVisibility(0);
    }
}
