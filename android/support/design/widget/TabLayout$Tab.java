// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v7.content.res.AppCompatResources;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.view.View;

public final class TabLayout$Tab
{
    public static final int INVALID_POSITION = -1;
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    TabLayout mParent;
    private int mPosition;
    private Object mTag;
    private CharSequence mText;
    TabLayout$TabView mView;
    
    TabLayout$Tab() {
        this.mPosition = -1;
    }
    
    public CharSequence getContentDescription() {
        return this.mContentDesc;
    }
    
    public View getCustomView() {
        return this.mCustomView;
    }
    
    public Drawable getIcon() {
        return this.mIcon;
    }
    
    public int getPosition() {
        return this.mPosition;
    }
    
    public Object getTag() {
        return this.mTag;
    }
    
    public CharSequence getText() {
        return this.mText;
    }
    
    public boolean isSelected() {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.mParent.getSelectedTabPosition() == this.mPosition;
    }
    
    void reset() {
        this.mParent = null;
        this.mView = null;
        this.mTag = null;
        this.mIcon = null;
        this.mText = null;
        this.mContentDesc = null;
        this.mPosition = -1;
        this.mCustomView = null;
    }
    
    public void select() {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        this.mParent.selectTab(this);
    }
    
    public TabLayout$Tab setContentDescription(final int n) {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.setContentDescription(this.mParent.getResources().getText(n));
    }
    
    public TabLayout$Tab setContentDescription(final CharSequence mContentDesc) {
        this.mContentDesc = mContentDesc;
        this.updateView();
        return this;
    }
    
    public TabLayout$Tab setCustomView(final int n) {
        return this.setCustomView(LayoutInflater.from(this.mView.getContext()).inflate(n, (ViewGroup)this.mView, false));
    }
    
    public TabLayout$Tab setCustomView(final View mCustomView) {
        this.mCustomView = mCustomView;
        this.updateView();
        return this;
    }
    
    public TabLayout$Tab setIcon(final int n) {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.setIcon(AppCompatResources.getDrawable(this.mParent.getContext(), n));
    }
    
    public TabLayout$Tab setIcon(final Drawable mIcon) {
        this.mIcon = mIcon;
        this.updateView();
        return this;
    }
    
    void setPosition(final int mPosition) {
        this.mPosition = mPosition;
    }
    
    public TabLayout$Tab setTag(final Object mTag) {
        this.mTag = mTag;
        return this;
    }
    
    public TabLayout$Tab setText(final int n) {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.setText(this.mParent.getResources().getText(n));
    }
    
    public TabLayout$Tab setText(final CharSequence mText) {
        this.mText = mText;
        this.updateView();
        return this;
    }
    
    void updateView() {
        if (this.mView != null) {
            this.mView.update();
        }
    }
}
