// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.widget.CompoundButton;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.support.v7.appcompat.R$id;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.appcompat.R$layout;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ListMenuItemView extends LinearLayout implements ab
{
    private static final String a = "ListMenuItemView";
    private m b;
    private ImageView c;
    private RadioButton d;
    private TextView e;
    private CheckBox f;
    private TextView g;
    private Drawable h;
    private int i;
    private Context j;
    private boolean k;
    private int l;
    private Context m;
    private LayoutInflater n;
    private boolean o;
    
    public ListMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ListMenuItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set);
        this.m = context;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.MenuView, n, 0);
        this.h = obtainStyledAttributes.getDrawable(R$styleable.MenuView_android_itemBackground);
        this.i = obtainStyledAttributes.getResourceId(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.k = obtainStyledAttributes.getBoolean(R$styleable.MenuView_preserveIconSpacing, false);
        this.j = context;
        obtainStyledAttributes.recycle();
    }
    
    private void c() {
        this.addView((View)(this.c = (ImageView)this.g().inflate(R$layout.abc_list_menu_item_icon, (ViewGroup)this, false)), 0);
    }
    
    private void e() {
        this.addView((View)(this.d = (RadioButton)this.g().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup)this, false)));
    }
    
    private void f() {
        this.addView((View)(this.f = (CheckBox)this.g().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup)this, false)));
    }
    
    private LayoutInflater g() {
        if (this.n == null) {
            this.n = LayoutInflater.from(this.m);
        }
        return this.n;
    }
    
    public m a() {
        return this.b;
    }
    
    public void a(Drawable imageDrawable) {
        boolean b;
        if (this.b.i() || this.o) {
            b = true;
        }
        else {
            b = false;
        }
        if ((b || this.k) && (this.c != null || imageDrawable != null || this.k)) {
            if (this.c == null) {
                this.c();
            }
            if (imageDrawable == null && !this.k) {
                this.c.setVisibility(8);
                return;
            }
            final ImageView c = this.c;
            if (!b) {
                imageDrawable = null;
            }
            c.setImageDrawable(imageDrawable);
            if (this.c.getVisibility() != 0) {
                this.c.setVisibility(0);
            }
        }
    }
    
    public void a(final m b, int n) {
        this.b = b;
        this.l = n;
        if (b.isVisible()) {
            n = 0;
        }
        else {
            n = 8;
        }
        this.setVisibility(n);
        this.a(b.a(this));
        this.a(b.isCheckable());
        this.a(b.f(), b.d());
        this.a(b.getIcon());
        this.setEnabled(b.isEnabled());
    }
    
    public void a(final CharSequence text) {
        if (text != null) {
            this.e.setText(text);
            if (this.e.getVisibility() != 0) {
                this.e.setVisibility(0);
            }
        }
        else if (this.e.getVisibility() != 8) {
            this.e.setVisibility(8);
        }
    }
    
    public void a(final boolean b) {
        if (b || this.d != null || this.f != null) {
            Object o;
            Object o2;
            if (this.b.g()) {
                if (this.d == null) {
                    this.e();
                }
                o = this.d;
                o2 = this.f;
            }
            else {
                if (this.f == null) {
                    this.f();
                }
                o = this.f;
                o2 = this.d;
            }
            if (b) {
                ((CompoundButton)o).setChecked(this.b.isChecked());
                int visibility;
                if (b) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                if (((CompoundButton)o).getVisibility() != visibility) {
                    ((CompoundButton)o).setVisibility(visibility);
                }
                if (o2 != null && ((CompoundButton)o2).getVisibility() != 8) {
                    ((CompoundButton)o2).setVisibility(8);
                }
            }
            else {
                if (this.f != null) {
                    this.f.setVisibility(8);
                }
                if (this.d != null) {
                    this.d.setVisibility(8);
                }
            }
        }
    }
    
    public void a(final boolean b, final char c) {
        int visibility;
        if (b && this.b.f()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        if (visibility == 0) {
            this.g.setText((CharSequence)this.b.e());
        }
        if (this.g.getVisibility() != visibility) {
            this.g.setVisibility(visibility);
        }
    }
    
    public void b(final boolean checked) {
        Object o;
        if (this.b.g()) {
            if (this.d == null) {
                this.e();
            }
            o = this.d;
        }
        else {
            if (this.f == null) {
                this.f();
            }
            o = this.f;
        }
        ((CompoundButton)o).setChecked(checked);
    }
    
    public boolean b() {
        return false;
    }
    
    public void c(final boolean b) {
        this.o = b;
        this.k = b;
    }
    
    public boolean d() {
        return this.o;
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.setBackgroundDrawable(this.h);
        this.e = (TextView)this.findViewById(R$id.title);
        if (this.i != -1) {
            this.e.setTextAppearance(this.j, this.i);
        }
        this.g = (TextView)this.findViewById(R$id.shortcut);
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.c != null && this.k) {
            final ViewGroup$LayoutParams layoutParams = this.getLayoutParams();
            final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.c.getLayoutParams();
            if (layoutParams.height > 0 && linearLayout$LayoutParams.width <= 0) {
                linearLayout$LayoutParams.width = layoutParams.height;
            }
        }
        super.onMeasure(n, n2);
    }
}
