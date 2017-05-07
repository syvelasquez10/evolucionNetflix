// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.widget.Toast;
import android.support.v4.view.ViewCompat;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.view.View;
import android.text.TextUtils;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.text.method.TransformationMethod;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$bool;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.ListPopupWindow$ForwardingListener;
import android.graphics.drawable.Drawable;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.support.v7.widget.ActionMenuView$ActionMenuChildView;
import android.support.v7.internal.widget.CompatTextView;

public class ActionMenuItemView extends CompatTextView implements aa, ActionMenuView$ActionMenuChildView, View$OnClickListener, View$OnLongClickListener
{
    private m a;
    private CharSequence b;
    private Drawable c;
    private k d;
    private ListPopupWindow$ForwardingListener e;
    private c f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    
    public ActionMenuItemView(final Context context) {
        this(context, null);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final Resources resources = context.getResources();
        this.g = resources.getBoolean(R$bool.abc_config_allowActionMenuItemTextWithIcon);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ActionMenuItemView, n, 0);
        this.i = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.k = (int)(resources.getDisplayMetrics().density * 32.0f + 0.5f);
        this.setOnClickListener((View$OnClickListener)this);
        this.setOnLongClickListener((View$OnLongClickListener)this);
        this.setTransformationMethod((TransformationMethod)new AllCapsTransformationMethod(context));
        this.j = -1;
    }
    
    private void c() {
        final boolean b = false;
        final boolean b2 = !TextUtils.isEmpty(this.b) && true;
        boolean b3 = false;
        Label_0051: {
            if (this.c != null) {
                b3 = b;
                if (!this.a.l()) {
                    break Label_0051;
                }
                if (!this.g) {
                    b3 = b;
                    if (!this.h) {
                        break Label_0051;
                    }
                }
            }
            b3 = true;
        }
        CharSequence b4;
        if (b2 & b3) {
            b4 = this.b;
        }
        else {
            b4 = null;
        }
        this.setText(b4);
    }
    
    @Override
    public void a(final m a, int visibility) {
        this.a = a;
        this.setIcon(a.getIcon());
        this.setTitle(a.a(this));
        this.setId(a.getItemId());
        if (a.isVisible()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        this.setVisibility(visibility);
        this.setEnabled(a.isEnabled());
        if (a.hasSubMenu() && this.e == null) {
            this.e = new b(this);
        }
    }
    
    @Override
    public boolean a() {
        return true;
    }
    
    public boolean b() {
        return !TextUtils.isEmpty(this.getText());
    }
    
    @Override
    public m getItemData() {
        return this.a;
    }
    
    @Override
    public boolean needsDividerAfter() {
        return this.b();
    }
    
    @Override
    public boolean needsDividerBefore() {
        return this.b() && this.a.getIcon() == null;
    }
    
    public void onClick(final View view) {
        if (this.d != null) {
            this.d.invokeItem(this.a);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (Build$VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.g = this.getContext().getResources().getBoolean(R$bool.abc_config_allowActionMenuItemTextWithIcon);
        this.c();
    }
    
    public boolean onLongClick(final View view) {
        if (this.b()) {
            return false;
        }
        final int[] array = new int[2];
        final Rect rect = new Rect();
        this.getLocationOnScreen(array);
        this.getWindowVisibleDisplayFrame(rect);
        final Context context = this.getContext();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int n = array[1];
        final int n2 = height / 2;
        int n3 = array[0] + width / 2;
        if (ViewCompat.getLayoutDirection(view) == 0) {
            n3 = context.getResources().getDisplayMetrics().widthPixels - n3;
        }
        final Toast text = Toast.makeText(context, this.a.getTitle(), 0);
        if (n + n2 < rect.height()) {
            text.setGravity(8388661, n3, height);
        }
        else {
            text.setGravity(81, 0, height);
        }
        text.show();
        return true;
    }
    
    protected void onMeasure(int n, final int n2) {
        final boolean b = this.b();
        if (b && this.j >= 0) {
            super.setPadding(this.j, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
        super.onMeasure(n, n2);
        final int mode = View$MeasureSpec.getMode(n);
        n = View$MeasureSpec.getSize(n);
        final int measuredWidth = this.getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            n = Math.min(n, this.i);
        }
        else {
            n = this.i;
        }
        if (mode != 1073741824 && this.i > 0 && measuredWidth < n) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), n2);
        }
        if (!b && this.c != null) {
            super.setPadding((this.getMeasuredWidth() - this.c.getBounds().width()) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.a.hasSubMenu() && this.e != null && this.e.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    public void setCheckable(final boolean b) {
    }
    
    public void setChecked(final boolean b) {
    }
    
    public void setExpandedFormat(final boolean h) {
        if (this.h != h) {
            this.h = h;
            if (this.a != null) {
                this.a.g();
            }
        }
    }
    
    public void setIcon(final Drawable c) {
        this.c = c;
        if (c != null) {
            final int intrinsicWidth = c.getIntrinsicWidth();
            int intrinsicHeight;
            final int n = intrinsicHeight = c.getIntrinsicHeight();
            int k;
            if ((k = intrinsicWidth) > this.k) {
                final float n2 = this.k / intrinsicWidth;
                k = this.k;
                intrinsicHeight = (int)(n * n2);
            }
            int i = intrinsicHeight;
            int n3 = k;
            if (intrinsicHeight > this.k) {
                final float n4 = this.k / intrinsicHeight;
                i = this.k;
                n3 = (int)(k * n4);
            }
            c.setBounds(0, 0, n3, i);
        }
        this.setCompoundDrawables(c, (Drawable)null, (Drawable)null, (Drawable)null);
        this.c();
    }
    
    public void setItemInvoker(final k d) {
        this.d = d;
    }
    
    public void setPadding(final int j, final int n, final int n2, final int n3) {
        super.setPadding(this.j = j, n, n2, n3);
    }
    
    public void setPopupCallback(final c f) {
        this.f = f;
    }
    
    public void setTitle(final CharSequence b) {
        this.setContentDescription(this.b = b);
        this.c();
    }
}
