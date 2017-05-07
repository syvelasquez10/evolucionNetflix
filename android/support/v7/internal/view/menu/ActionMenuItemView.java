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

public class ActionMenuItemView extends CompatTextView implements ab, ActionMenuView$ActionMenuChildView, View$OnClickListener, View$OnLongClickListener
{
    private static final String a = "ActionMenuItemView";
    private static final int l = 32;
    private m b;
    private CharSequence c;
    private Drawable d;
    private k e;
    private ListPopupWindow$ForwardingListener f;
    private c g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private int m;
    
    public ActionMenuItemView(final Context context) {
        this(context, null);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final Resources resources = context.getResources();
        this.h = resources.getBoolean(R$bool.abc_config_allowActionMenuItemTextWithIcon);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ActionMenuItemView, n, 0);
        this.j = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.m = (int)(resources.getDisplayMetrics().density * 32.0f + 0.5f);
        this.setOnClickListener((View$OnClickListener)this);
        this.setOnLongClickListener((View$OnLongClickListener)this);
        this.setTransformationMethod((TransformationMethod)new AllCapsTransformationMethod(context));
        this.k = -1;
    }
    
    private void e() {
        final boolean b = false;
        final boolean b2 = !TextUtils.isEmpty(this.c) && true;
        boolean b3 = false;
        Label_0051: {
            if (this.d != null) {
                b3 = b;
                if (!this.b.m()) {
                    break Label_0051;
                }
                if (!this.h) {
                    b3 = b;
                    if (!this.i) {
                        break Label_0051;
                    }
                }
            }
            b3 = true;
        }
        CharSequence c;
        if (b2 & b3) {
            c = this.c;
        }
        else {
            c = null;
        }
        this.setText(c);
    }
    
    @Override
    public m a() {
        return this.b;
    }
    
    @Override
    public void a(final Drawable d) {
        this.d = d;
        if (d != null) {
            final int intrinsicWidth = d.getIntrinsicWidth();
            int intrinsicHeight;
            final int n = intrinsicHeight = d.getIntrinsicHeight();
            int m;
            if ((m = intrinsicWidth) > this.m) {
                final float n2 = this.m / intrinsicWidth;
                m = this.m;
                intrinsicHeight = (int)(n * n2);
            }
            int i = intrinsicHeight;
            int n3 = m;
            if (intrinsicHeight > this.m) {
                final float n4 = this.m / intrinsicHeight;
                i = this.m;
                n3 = (int)(m * n4);
            }
            d.setBounds(0, 0, n3, i);
        }
        this.setCompoundDrawables(d, (Drawable)null, (Drawable)null, (Drawable)null);
        this.e();
    }
    
    public void a(final c g) {
        this.g = g;
    }
    
    public void a(final k e) {
        this.e = e;
    }
    
    @Override
    public void a(final m b, int visibility) {
        this.b = b;
        this.a(b.getIcon());
        this.a(b.a(this));
        this.setId(b.getItemId());
        if (b.isVisible()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        this.setVisibility(visibility);
        this.setEnabled(b.isEnabled());
        if (b.hasSubMenu() && this.f == null) {
            this.f = new b(this);
        }
    }
    
    @Override
    public void a(final CharSequence c) {
        this.setContentDescription(this.c = c);
        this.e();
    }
    
    @Override
    public void a(final boolean b) {
    }
    
    @Override
    public void a(final boolean b, final char c) {
    }
    
    @Override
    public void b(final boolean b) {
    }
    
    @Override
    public boolean b() {
        return true;
    }
    
    public void c(final boolean i) {
        if (this.i != i) {
            this.i = i;
            if (this.b != null) {
                this.b.h();
            }
        }
    }
    
    public boolean c() {
        return !TextUtils.isEmpty(this.getText());
    }
    
    @Override
    public boolean d() {
        return true;
    }
    
    @Override
    public boolean needsDividerAfter() {
        return this.c();
    }
    
    @Override
    public boolean needsDividerBefore() {
        return this.c() && this.b.getIcon() == null;
    }
    
    public void onClick(final View view) {
        if (this.e != null) {
            this.e.invokeItem(this.b);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (Build$VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.h = this.getContext().getResources().getBoolean(R$bool.abc_config_allowActionMenuItemTextWithIcon);
        this.e();
    }
    
    public boolean onLongClick(final View view) {
        if (this.c()) {
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
        final Toast text = Toast.makeText(context, this.b.getTitle(), 0);
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
        final boolean c = this.c();
        if (c && this.k >= 0) {
            super.setPadding(this.k, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
        super.onMeasure(n, n2);
        final int mode = View$MeasureSpec.getMode(n);
        n = View$MeasureSpec.getSize(n);
        final int measuredWidth = this.getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            n = Math.min(n, this.j);
        }
        else {
            n = this.j;
        }
        if (mode != 1073741824 && this.j > 0 && measuredWidth < n) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), n2);
        }
        if (!c && this.d != null) {
            super.setPadding((this.getMeasuredWidth() - this.d.getBounds().width()) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.b.hasSubMenu() && this.f != null && this.f.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    public void setPadding(final int k, final int n, final int n2, final int n3) {
        super.setPadding(this.k = k, n, n2, n3);
    }
}
