// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View$MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.MotionEvent;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.ActionMenuPresenter;
import android.view.View$OnClickListener;
import android.support.v7.widget.ActionBarContextView$1;
import android.support.v7.widget.TintTypedArray;
import android.widget.LinearLayout;
import android.support.v7.widget.AbsActionBarView;
import android.support.v7.appcompat.R$color;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.widget.ViewStubCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.AppCompatDrawableManager;
import android.content.res.Configuration;
import android.support.v4.view.LayoutInflaterCompat;
import android.app.Dialog;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.LayoutInflater$Factory;
import android.util.AttributeSet;
import android.util.AndroidRuntimeException;
import android.view.KeyCharacterMap;
import android.view.ViewParent;
import android.view.Window$Callback;
import android.view.WindowManager$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.WindowManager;
import android.view.Menu;
import android.util.Log;
import android.media.AudioManager;
import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.view.KeyEvent;
import android.content.res.Resources$Theme;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.text.TextUtils;
import android.support.v7.widget.ContentFrameLayout$OnAttachListener;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.support.v7.widget.ViewUtils;
import android.support.v7.widget.FitWindowsViewGroup$OnFitSystemWindowsListener;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.support.v7.appcompat.R$id;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.appcompat.R$layout;
import android.view.LayoutInflater;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.Window;
import android.content.Context;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.ActionBarContextView;
import android.widget.PopupWindow;
import android.support.v7.view.ActionMode;
import android.annotation.TargetApi;
import android.support.v7.view.menu.MenuBuilder$Callback;
import android.support.v4.view.LayoutInflaterFactory;

@TargetApi(9)
class AppCompatDelegateImplV9 extends AppCompatDelegateImplBase implements LayoutInflaterFactory, MenuBuilder$Callback
{
    private AppCompatDelegateImplV9$ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private AppCompatViewInflater mAppCompatViewInflater;
    private boolean mClosingActionMenu;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    private boolean mLongPressBackDown;
    private AppCompatDelegateImplV9$PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private AppCompatDelegateImplV9$PanelFeatureState[] mPanels;
    private AppCompatDelegateImplV9$PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private TextView mTitleView;
    
    AppCompatDelegateImplV9(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mFadeAnim = null;
        this.mInvalidatePanelMenuRunnable = new AppCompatDelegateImplV9$1(this);
    }
    
    private void applyFixedSizeWindow() {
        final ContentFrameLayout contentFrameLayout = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
        final View decorView = this.mWindow.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }
    
    private ViewGroup createSubDecor() {
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        if (!obtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            this.requestWindowFeature(1);
        }
        else if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBar, false)) {
            this.requestWindowFeature(108);
        }
        if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            this.requestWindowFeature(109);
        }
        if (obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            this.requestWindowFeature(10);
        }
        this.mIsFloating = obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
        this.mWindow.getDecorView();
        final LayoutInflater from = LayoutInflater.from(this.mContext);
        ViewGroup contentView;
        if (!this.mWindowNoTitle) {
            if (this.mIsFloating) {
                contentView = (ViewGroup)from.inflate(R$layout.abc_dialog_title_material, (ViewGroup)null);
                this.mOverlayActionBar = false;
                this.mHasActionBar = false;
            }
            else if (this.mHasActionBar) {
                final TypedValue typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                Object mContext;
                if (typedValue.resourceId != 0) {
                    mContext = new ContextThemeWrapper(this.mContext, typedValue.resourceId);
                }
                else {
                    mContext = this.mContext;
                }
                contentView = (ViewGroup)LayoutInflater.from((Context)mContext).inflate(R$layout.abc_screen_toolbar, (ViewGroup)null);
                (this.mDecorContentParent = (DecorContentParent)contentView.findViewById(R$id.decor_content_parent)).setWindowCallback(this.getWindowCallback());
                if (this.mOverlayActionBar) {
                    this.mDecorContentParent.initFeature(109);
                }
                if (this.mFeatureProgress) {
                    this.mDecorContentParent.initFeature(2);
                }
                if (this.mFeatureIndeterminateProgress) {
                    this.mDecorContentParent.initFeature(5);
                }
            }
            else {
                contentView = null;
            }
        }
        else {
            if (this.mOverlayActionMode) {
                contentView = (ViewGroup)from.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup)null);
            }
            else {
                contentView = (ViewGroup)from.inflate(R$layout.abc_screen_simple, (ViewGroup)null);
            }
            if (Build$VERSION.SDK_INT >= 21) {
                ViewCompat.setOnApplyWindowInsetsListener((View)contentView, new AppCompatDelegateImplV9$2(this));
            }
            else {
                ((FitWindowsViewGroup)contentView).setOnFitSystemWindowsListener(new AppCompatDelegateImplV9$3(this));
            }
        }
        if (contentView == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
        }
        if (this.mDecorContentParent == null) {
            this.mTitleView = (TextView)contentView.findViewById(R$id.title);
        }
        ViewUtils.makeOptionalFitsSystemWindows((View)contentView);
        final ContentFrameLayout contentFrameLayout = (ContentFrameLayout)contentView.findViewById(R$id.action_bar_activity_content);
        final ViewGroup viewGroup = (ViewGroup)this.mWindow.findViewById(16908290);
        if (viewGroup != null) {
            while (viewGroup.getChildCount() > 0) {
                final View child = viewGroup.getChildAt(0);
                viewGroup.removeViewAt(0);
                contentFrameLayout.addView(child);
            }
            viewGroup.setId(-1);
            contentFrameLayout.setId(16908290);
            if (viewGroup instanceof FrameLayout) {
                ((FrameLayout)viewGroup).setForeground((Drawable)null);
            }
        }
        this.mWindow.setContentView((View)contentView);
        contentFrameLayout.setAttachListener(new AppCompatDelegateImplV9$4(this));
        return contentView;
    }
    
    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = this.createSubDecor();
            final CharSequence title = this.getTitle();
            if (!TextUtils.isEmpty(title)) {
                this.onTitleChanged(title);
            }
            this.applyFixedSizeWindow();
            this.onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(0, false);
            if (!this.isDestroyed() && (panelState == null || panelState.menu == null)) {
                this.invalidatePanelMenu(108);
            }
        }
    }
    
    private boolean initializePanelContent(final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState) {
        if (appCompatDelegateImplV9$PanelFeatureState.createdPanelView != null) {
            appCompatDelegateImplV9$PanelFeatureState.shownPanelView = appCompatDelegateImplV9$PanelFeatureState.createdPanelView;
            return true;
        }
        if (appCompatDelegateImplV9$PanelFeatureState.menu == null) {
            return false;
        }
        if (this.mPanelMenuPresenterCallback == null) {
            this.mPanelMenuPresenterCallback = new AppCompatDelegateImplV9$PanelMenuPresenterCallback(this);
        }
        appCompatDelegateImplV9$PanelFeatureState.shownPanelView = (View)appCompatDelegateImplV9$PanelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
        return appCompatDelegateImplV9$PanelFeatureState.shownPanelView != null;
    }
    
    private boolean initializePanelDecor(final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState) {
        appCompatDelegateImplV9$PanelFeatureState.setStyle(this.getActionBarThemedContext());
        appCompatDelegateImplV9$PanelFeatureState.decorView = (ViewGroup)new AppCompatDelegateImplV9$ListMenuDecorView(this, appCompatDelegateImplV9$PanelFeatureState.listPresenterContext);
        appCompatDelegateImplV9$PanelFeatureState.gravity = 81;
        return true;
    }
    
    private boolean initializePanelMenu(final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState) {
        final Context mContext = this.mContext;
        while (true) {
            Label_0203: {
                if ((appCompatDelegateImplV9$PanelFeatureState.featureId != 0 && appCompatDelegateImplV9$PanelFeatureState.featureId != 108) || this.mDecorContentParent == null) {
                    break Label_0203;
                }
                final TypedValue typedValue = new TypedValue();
                final Resources$Theme theme = mContext.getTheme();
                theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                Resources$Theme theme2 = null;
                if (typedValue.resourceId != 0) {
                    theme2 = mContext.getResources().newTheme();
                    theme2.setTo(theme);
                    theme2.applyStyle(typedValue.resourceId, true);
                    theme2.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
                }
                else {
                    theme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
                }
                Resources$Theme theme3 = theme2;
                if (typedValue.resourceId != 0) {
                    if ((theme3 = theme2) == null) {
                        theme3 = mContext.getResources().newTheme();
                        theme3.setTo(theme);
                    }
                    theme3.applyStyle(typedValue.resourceId, true);
                }
                if (theme3 == null) {
                    break Label_0203;
                }
                final Object o = new ContextThemeWrapper(mContext, 0);
                ((Context)o).getTheme().setTo(theme3);
                final MenuBuilder menu = new MenuBuilder((Context)o);
                menu.setCallback(this);
                appCompatDelegateImplV9$PanelFeatureState.setMenu(menu);
                return true;
            }
            final Object o = mContext;
            continue;
        }
    }
    
    private void invalidatePanelMenu(final int n) {
        this.mInvalidatePanelMenuFeatures |= 1 << n;
        if (!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }
    
    private boolean onKeyDownPanel(final int n, final KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(n, true);
            if (!panelState.isOpen) {
                return this.preparePanel(panelState, keyEvent);
            }
        }
        return false;
    }
    
    private boolean onKeyUpPanel(final int n, final KeyEvent keyEvent) {
        final boolean b = true;
        if (this.mActionMode != null) {
            return false;
        }
        final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(n, true);
        while (true) {
            Label_0229: {
                boolean b2;
                if (n == 0 && this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && !ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext))) {
                    if (!this.mDecorContentParent.isOverflowMenuShowing()) {
                        if (this.isDestroyed() || !this.preparePanel(panelState, keyEvent)) {
                            break Label_0229;
                        }
                        b2 = this.mDecorContentParent.showOverflowMenu();
                    }
                    else {
                        b2 = this.mDecorContentParent.hideOverflowMenu();
                    }
                }
                else if (panelState.isOpen || panelState.isHandled) {
                    b2 = panelState.isOpen;
                    this.closePanel(panelState, true);
                }
                else {
                    if (!panelState.isPrepared) {
                        break Label_0229;
                    }
                    int preparePanel;
                    if (panelState.refreshMenuContent) {
                        panelState.isPrepared = false;
                        preparePanel = (this.preparePanel(panelState, keyEvent) ? 1 : 0);
                    }
                    else {
                        preparePanel = 1;
                    }
                    if (preparePanel == 0) {
                        break Label_0229;
                    }
                    this.openPanel(panelState, keyEvent);
                    b2 = b;
                }
                if (b2) {
                    final AudioManager audioManager = (AudioManager)this.mContext.getSystemService("audio");
                    if (audioManager != null) {
                        audioManager.playSoundEffect(0);
                    }
                    else {
                        Log.w("AppCompatDelegate", "Couldn't get audio manager");
                    }
                }
                return b2;
            }
            boolean b2 = false;
            continue;
        }
    }
    
    private void openPanel(final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState, final KeyEvent keyEvent) {
        final int n = -1;
        if (!appCompatDelegateImplV9$PanelFeatureState.isOpen && !this.isDestroyed()) {
            if (appCompatDelegateImplV9$PanelFeatureState.featureId == 0) {
                final Context mContext = this.mContext;
                boolean b;
                if ((mContext.getResources().getConfiguration().screenLayout & 0xF) == 0x4) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (mContext.getApplicationInfo().targetSdkVersion >= 11) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    return;
                }
            }
            final Window$Callback windowCallback = this.getWindowCallback();
            if (windowCallback != null && !windowCallback.onMenuOpened(appCompatDelegateImplV9$PanelFeatureState.featureId, (Menu)appCompatDelegateImplV9$PanelFeatureState.menu)) {
                this.closePanel(appCompatDelegateImplV9$PanelFeatureState, true);
                return;
            }
            final WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
            if (windowManager != null && this.preparePanel(appCompatDelegateImplV9$PanelFeatureState, keyEvent)) {
                int n2 = 0;
                Label_0307: {
                    if (appCompatDelegateImplV9$PanelFeatureState.decorView == null || appCompatDelegateImplV9$PanelFeatureState.refreshDecorView) {
                        if (appCompatDelegateImplV9$PanelFeatureState.decorView == null) {
                            if (!this.initializePanelDecor(appCompatDelegateImplV9$PanelFeatureState) || appCompatDelegateImplV9$PanelFeatureState.decorView == null) {
                                return;
                            }
                        }
                        else if (appCompatDelegateImplV9$PanelFeatureState.refreshDecorView && appCompatDelegateImplV9$PanelFeatureState.decorView.getChildCount() > 0) {
                            appCompatDelegateImplV9$PanelFeatureState.decorView.removeAllViews();
                        }
                        if (!this.initializePanelContent(appCompatDelegateImplV9$PanelFeatureState) || !appCompatDelegateImplV9$PanelFeatureState.hasPanelItems()) {
                            return;
                        }
                        ViewGroup$LayoutParams layoutParams = appCompatDelegateImplV9$PanelFeatureState.shownPanelView.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ViewGroup$LayoutParams(-2, -2);
                        }
                        appCompatDelegateImplV9$PanelFeatureState.decorView.setBackgroundResource(appCompatDelegateImplV9$PanelFeatureState.background);
                        final ViewParent parent = appCompatDelegateImplV9$PanelFeatureState.shownPanelView.getParent();
                        if (parent != null && parent instanceof ViewGroup) {
                            ((ViewGroup)parent).removeView(appCompatDelegateImplV9$PanelFeatureState.shownPanelView);
                        }
                        appCompatDelegateImplV9$PanelFeatureState.decorView.addView(appCompatDelegateImplV9$PanelFeatureState.shownPanelView, layoutParams);
                        if (!appCompatDelegateImplV9$PanelFeatureState.shownPanelView.hasFocus()) {
                            appCompatDelegateImplV9$PanelFeatureState.shownPanelView.requestFocus();
                        }
                        n2 = -2;
                    }
                    else {
                        if (appCompatDelegateImplV9$PanelFeatureState.createdPanelView != null) {
                            final ViewGroup$LayoutParams layoutParams2 = appCompatDelegateImplV9$PanelFeatureState.createdPanelView.getLayoutParams();
                            if (layoutParams2 != null) {
                                n2 = n;
                                if (layoutParams2.width == -1) {
                                    break Label_0307;
                                }
                            }
                        }
                        n2 = -2;
                    }
                }
                appCompatDelegateImplV9$PanelFeatureState.isHandled = false;
                final WindowManager$LayoutParams windowManager$LayoutParams = new WindowManager$LayoutParams(n2, -2, appCompatDelegateImplV9$PanelFeatureState.x, appCompatDelegateImplV9$PanelFeatureState.y, 1002, 8519680, -3);
                windowManager$LayoutParams.gravity = appCompatDelegateImplV9$PanelFeatureState.gravity;
                windowManager$LayoutParams.windowAnimations = appCompatDelegateImplV9$PanelFeatureState.windowAnimations;
                windowManager.addView((View)appCompatDelegateImplV9$PanelFeatureState.decorView, (ViewGroup$LayoutParams)windowManager$LayoutParams);
                appCompatDelegateImplV9$PanelFeatureState.isOpen = true;
            }
        }
    }
    
    private boolean performPanelShortcut(final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState, final int n, final KeyEvent keyEvent, final int n2) {
        final boolean b = false;
        final boolean b2 = false;
        boolean b3;
        if (keyEvent.isSystem()) {
            b3 = b2;
        }
        else {
            boolean performShortcut = false;
            Label_0064: {
                if (!appCompatDelegateImplV9$PanelFeatureState.isPrepared) {
                    performShortcut = b;
                    if (!this.preparePanel(appCompatDelegateImplV9$PanelFeatureState, keyEvent)) {
                        break Label_0064;
                    }
                }
                performShortcut = b;
                if (appCompatDelegateImplV9$PanelFeatureState.menu != null) {
                    performShortcut = appCompatDelegateImplV9$PanelFeatureState.menu.performShortcut(n, keyEvent, n2);
                }
            }
            b3 = performShortcut;
            if (performShortcut) {
                b3 = performShortcut;
                if ((n2 & 0x1) == 0x0) {
                    b3 = performShortcut;
                    if (this.mDecorContentParent == null) {
                        this.closePanel(appCompatDelegateImplV9$PanelFeatureState, true);
                        return performShortcut;
                    }
                }
            }
        }
        return b3;
    }
    
    private boolean preparePanel(final AppCompatDelegateImplV9$PanelFeatureState mPreparedPanel, final KeyEvent keyEvent) {
        if (!this.isDestroyed()) {
            if (mPreparedPanel.isPrepared) {
                return true;
            }
            if (this.mPreparedPanel != null && this.mPreparedPanel != mPreparedPanel) {
                this.closePanel(this.mPreparedPanel, false);
            }
            final Window$Callback windowCallback = this.getWindowCallback();
            if (windowCallback != null) {
                mPreparedPanel.createdPanelView = windowCallback.onCreatePanelView(mPreparedPanel.featureId);
            }
            boolean b;
            if (mPreparedPanel.featureId == 0 || mPreparedPanel.featureId == 108) {
                b = true;
            }
            else {
                b = false;
            }
            if (b && this.mDecorContentParent != null) {
                this.mDecorContentParent.setMenuPrepared();
            }
            if (mPreparedPanel.createdPanelView == null && (!b || !(this.peekSupportActionBar() instanceof ToolbarActionBar))) {
                if (mPreparedPanel.menu == null || mPreparedPanel.refreshMenuContent) {
                    if (mPreparedPanel.menu == null && (!this.initializePanelMenu(mPreparedPanel) || mPreparedPanel.menu == null)) {
                        return false;
                    }
                    if (b && this.mDecorContentParent != null) {
                        if (this.mActionMenuPresenterCallback == null) {
                            this.mActionMenuPresenterCallback = new AppCompatDelegateImplV9$ActionMenuPresenterCallback(this);
                        }
                        this.mDecorContentParent.setMenu((Menu)mPreparedPanel.menu, this.mActionMenuPresenterCallback);
                    }
                    mPreparedPanel.menu.stopDispatchingItemsChanged();
                    if (!windowCallback.onCreatePanelMenu(mPreparedPanel.featureId, (Menu)mPreparedPanel.menu)) {
                        mPreparedPanel.setMenu(null);
                        if (b && this.mDecorContentParent != null) {
                            this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                            return false;
                        }
                        return false;
                    }
                    else {
                        mPreparedPanel.refreshMenuContent = false;
                    }
                }
                mPreparedPanel.menu.stopDispatchingItemsChanged();
                if (mPreparedPanel.frozenActionViewState != null) {
                    mPreparedPanel.menu.restoreActionViewStates(mPreparedPanel.frozenActionViewState);
                    mPreparedPanel.frozenActionViewState = null;
                }
                if (!windowCallback.onPreparePanel(0, mPreparedPanel.createdPanelView, (Menu)mPreparedPanel.menu)) {
                    if (b && this.mDecorContentParent != null) {
                        this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                    }
                    mPreparedPanel.menu.startDispatchingItemsChanged();
                    return false;
                }
                int deviceId;
                if (keyEvent != null) {
                    deviceId = keyEvent.getDeviceId();
                }
                else {
                    deviceId = -1;
                }
                mPreparedPanel.qwertyMode = (KeyCharacterMap.load(deviceId).getKeyboardType() != 1);
                mPreparedPanel.menu.setQwertyMode(mPreparedPanel.qwertyMode);
                mPreparedPanel.menu.startDispatchingItemsChanged();
            }
            mPreparedPanel.isPrepared = true;
            mPreparedPanel.isHandled = false;
            this.mPreparedPanel = mPreparedPanel;
            return true;
        }
        return false;
    }
    
    private void reopenMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && (!ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext)) || this.mDecorContentParent.isOverflowMenuShowPending())) {
            final Window$Callback windowCallback = this.getWindowCallback();
            if (!this.mDecorContentParent.isOverflowMenuShowing() || !b) {
                if (windowCallback != null && !this.isDestroyed()) {
                    if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 0x1) != 0x0) {
                        this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                        this.mInvalidatePanelMenuRunnable.run();
                    }
                    final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(0, true);
                    if (panelState.menu != null && !panelState.refreshMenuContent && windowCallback.onPreparePanel(0, panelState.createdPanelView, (Menu)panelState.menu)) {
                        windowCallback.onMenuOpened(108, (Menu)panelState.menu);
                        this.mDecorContentParent.showOverflowMenu();
                    }
                }
            }
            else {
                this.mDecorContentParent.hideOverflowMenu();
                if (!this.isDestroyed()) {
                    windowCallback.onPanelClosed(108, (Menu)this.getPanelState(0, true).menu);
                }
            }
            return;
        }
        final AppCompatDelegateImplV9$PanelFeatureState panelState2 = this.getPanelState(0, true);
        panelState2.refreshDecorView = true;
        this.closePanel(panelState2, false);
        this.openPanel(panelState2, null);
    }
    
    private int sanitizeWindowFeatureId(final int n) {
        int n2;
        if (n == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            n2 = 108;
        }
        else if ((n2 = n) == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
        return n2;
    }
    
    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            return false;
        }
        final View decorView = this.mWindow.getDecorView();
        while (parent != null) {
            if (parent == decorView || !(parent instanceof View) || ViewCompat.isAttachedToWindow((View)parent)) {
                return false;
            }
            parent = parent.getParent();
        }
        return true;
    }
    
    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }
    
    @Override
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.ensureSubDecor();
        ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(view, viewGroup$LayoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    View callActivityOnCreateView(View onCreateView, final String s, final Context context, final AttributeSet set) {
        if (this.mOriginalWindowCallback instanceof LayoutInflater$Factory) {
            onCreateView = ((LayoutInflater$Factory)this.mOriginalWindowCallback).onCreateView(s, context, set);
            if (onCreateView != null) {
                return onCreateView;
            }
        }
        return null;
    }
    
    void callOnPanelClosed(final int n, final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState, final Menu menu) {
        AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState2 = appCompatDelegateImplV9$PanelFeatureState;
        Object menu2 = menu;
        if (menu == null) {
            AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState3;
            if ((appCompatDelegateImplV9$PanelFeatureState3 = appCompatDelegateImplV9$PanelFeatureState) == null) {
                appCompatDelegateImplV9$PanelFeatureState3 = appCompatDelegateImplV9$PanelFeatureState;
                if (n >= 0) {
                    appCompatDelegateImplV9$PanelFeatureState3 = appCompatDelegateImplV9$PanelFeatureState;
                    if (n < this.mPanels.length) {
                        appCompatDelegateImplV9$PanelFeatureState3 = this.mPanels[n];
                    }
                }
            }
            appCompatDelegateImplV9$PanelFeatureState2 = appCompatDelegateImplV9$PanelFeatureState3;
            menu2 = menu;
            if (appCompatDelegateImplV9$PanelFeatureState3 != null) {
                menu2 = appCompatDelegateImplV9$PanelFeatureState3.menu;
                appCompatDelegateImplV9$PanelFeatureState2 = appCompatDelegateImplV9$PanelFeatureState3;
            }
        }
        if ((appCompatDelegateImplV9$PanelFeatureState2 == null || appCompatDelegateImplV9$PanelFeatureState2.isOpen) && !this.isDestroyed()) {
            this.mOriginalWindowCallback.onPanelClosed(n, (Menu)menu2);
        }
    }
    
    void checkCloseActionMenu(final MenuBuilder menuBuilder) {
        if (this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        this.mDecorContentParent.dismissPopups();
        final Window$Callback windowCallback = this.getWindowCallback();
        if (windowCallback != null && !this.isDestroyed()) {
            windowCallback.onPanelClosed(108, (Menu)menuBuilder);
        }
        this.mClosingActionMenu = false;
    }
    
    void closePanel(final int n) {
        this.closePanel(this.getPanelState(n, true), true);
    }
    
    void closePanel(final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState, final boolean b) {
        if (b && appCompatDelegateImplV9$PanelFeatureState.featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing()) {
            this.checkCloseActionMenu(appCompatDelegateImplV9$PanelFeatureState.menu);
        }
        else {
            final WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
            if (windowManager != null && appCompatDelegateImplV9$PanelFeatureState.isOpen && appCompatDelegateImplV9$PanelFeatureState.decorView != null) {
                windowManager.removeView((View)appCompatDelegateImplV9$PanelFeatureState.decorView);
                if (b) {
                    this.callOnPanelClosed(appCompatDelegateImplV9$PanelFeatureState.featureId, appCompatDelegateImplV9$PanelFeatureState, null);
                }
            }
            appCompatDelegateImplV9$PanelFeatureState.isPrepared = false;
            appCompatDelegateImplV9$PanelFeatureState.isHandled = false;
            appCompatDelegateImplV9$PanelFeatureState.isOpen = false;
            appCompatDelegateImplV9$PanelFeatureState.shownPanelView = null;
            appCompatDelegateImplV9$PanelFeatureState.refreshDecorView = true;
            if (this.mPreparedPanel == appCompatDelegateImplV9$PanelFeatureState) {
                this.mPreparedPanel = null;
            }
        }
    }
    
    public View createView(final View view, final String s, final Context context, final AttributeSet set) {
        final boolean b = Build$VERSION.SDK_INT < 21;
        if (this.mAppCompatViewInflater == null) {
            this.mAppCompatViewInflater = new AppCompatViewInflater();
        }
        return this.mAppCompatViewInflater.createView(view, s, context, set, b && this.shouldInheritContext((ViewParent)view), b, true, VectorEnabledTintResources.shouldBeUsed());
    }
    
    void dismissPopups() {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.dismissPopups();
        }
        Label_0060: {
            if (this.mActionModePopup == null) {
                break Label_0060;
            }
            this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            while (true) {
                if (!this.mActionModePopup.isShowing()) {
                    break Label_0055;
                }
                try {
                    this.mActionModePopup.dismiss();
                    this.mActionModePopup = null;
                    this.endOnGoingFadeAnimation();
                    final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(0, false);
                    if (panelState != null && panelState.menu != null) {
                        panelState.menu.close();
                    }
                }
                catch (IllegalArgumentException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        int n = 1;
        if (keyEvent.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            n = 0;
        }
        if (n != 0) {
            return this.onKeyDown(keyCode, keyEvent);
        }
        return this.onKeyUp(keyCode, keyEvent);
    }
    
    void doInvalidatePanelMenu(final int n) {
        final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(n, true);
        if (panelState.menu != null) {
            final Bundle frozenActionViewState = new Bundle();
            panelState.menu.saveActionViewStates(frozenActionViewState);
            if (frozenActionViewState.size() > 0) {
                panelState.frozenActionViewState = frozenActionViewState;
            }
            panelState.menu.stopDispatchingItemsChanged();
            panelState.menu.clear();
        }
        panelState.refreshMenuContent = true;
        panelState.refreshDecorView = true;
        if ((n == 108 || n == 0) && this.mDecorContentParent != null) {
            final AppCompatDelegateImplV9$PanelFeatureState panelState2 = this.getPanelState(0, false);
            if (panelState2 != null) {
                panelState2.isPrepared = false;
                this.preparePanel(panelState2, null);
            }
        }
    }
    
    void endOnGoingFadeAnimation() {
        if (this.mFadeAnim != null) {
            this.mFadeAnim.cancel();
        }
    }
    
    AppCompatDelegateImplV9$PanelFeatureState findMenuPanel(final Menu menu) {
        final AppCompatDelegateImplV9$PanelFeatureState[] mPanels = this.mPanels;
        int length;
        if (mPanels != null) {
            length = mPanels.length;
        }
        else {
            length = 0;
        }
        for (int i = 0; i < length; ++i) {
            final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState = mPanels[i];
            if (appCompatDelegateImplV9$PanelFeatureState != null && appCompatDelegateImplV9$PanelFeatureState.menu == menu) {
                return appCompatDelegateImplV9$PanelFeatureState;
            }
        }
        return null;
    }
    
    @Override
    public View findViewById(final int n) {
        this.ensureSubDecor();
        return this.mWindow.findViewById(n);
    }
    
    protected AppCompatDelegateImplV9$PanelFeatureState getPanelState(final int n, final boolean b) {
        final AppCompatDelegateImplV9$PanelFeatureState[] mPanels = this.mPanels;
        AppCompatDelegateImplV9$PanelFeatureState[] mPanels2 = null;
        Label_0049: {
            if (mPanels != null) {
                mPanels2 = mPanels;
                if (mPanels.length > n) {
                    break Label_0049;
                }
            }
            mPanels2 = new AppCompatDelegateImplV9$PanelFeatureState[n + 1];
            if (mPanels != null) {
                System.arraycopy(mPanels, 0, mPanels2, 0, mPanels.length);
            }
            this.mPanels = mPanels2;
        }
        final AppCompatDelegateImplV9$PanelFeatureState appCompatDelegateImplV9$PanelFeatureState = mPanels2[n];
        if (appCompatDelegateImplV9$PanelFeatureState == null) {
            return mPanels2[n] = new AppCompatDelegateImplV9$PanelFeatureState(n);
        }
        return appCompatDelegateImplV9$PanelFeatureState;
    }
    
    public void initWindowDecorActionBar() {
        this.ensureSubDecor();
        if (this.mHasActionBar && this.mActionBar == null) {
            if (this.mOriginalWindowCallback instanceof Activity) {
                this.mActionBar = new WindowDecorActionBar((Activity)this.mOriginalWindowCallback, this.mOverlayActionBar);
            }
            else if (this.mOriginalWindowCallback instanceof Dialog) {
                this.mActionBar = new WindowDecorActionBar((Dialog)this.mOriginalWindowCallback);
            }
            if (this.mActionBar != null) {
                this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }
    
    @Override
    public void installViewFactory() {
        final LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory(from, this);
        }
        else if (!(LayoutInflaterCompat.getFactory(from) instanceof AppCompatDelegateImplV9)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }
    
    @Override
    public void invalidateOptionsMenu() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null && supportActionBar.invalidateOptionsMenu()) {
            return;
        }
        this.invalidatePanelMenu(0);
    }
    
    boolean onBackPressed() {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        else {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar == null || !supportActionBar.collapseActionView()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        if (this.mHasActionBar && this.mSubDecorInstalled) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.onConfigurationChanged(configuration);
            }
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        this.applyDayNight();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        if (this.mOriginalWindowCallback instanceof Activity && NavUtils.getParentActivityName((Activity)this.mOriginalWindowCallback) != null) {
            final ActionBar peekSupportActionBar = this.peekSupportActionBar();
            if (peekSupportActionBar != null) {
                peekSupportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
                return;
            }
            this.mEnableDefaultActionBarUp = true;
        }
    }
    
    @Override
    public final View onCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        final View callActivityOnCreateView = this.callActivityOnCreateView(view, s, context, set);
        if (callActivityOnCreateView != null) {
            return callActivityOnCreateView;
        }
        return this.createView(view, s, context, set);
    }
    
    @Override
    public void onDestroy() {
        if (this.mInvalidatePanelMenuPosted) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }
        super.onDestroy();
        if (this.mActionBar != null) {
            this.mActionBar.onDestroy();
        }
    }
    
    boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        boolean mLongPressBackDown = true;
        switch (n) {
            case 82: {
                this.onKeyDownPanel(0, keyEvent);
                return true;
            }
            case 4: {
                if ((keyEvent.getFlags() & 0x80) == 0x0) {
                    mLongPressBackDown = false;
                }
                this.mLongPressBackDown = mLongPressBackDown;
                break;
            }
        }
        if (Build$VERSION.SDK_INT < 11) {
            this.onKeyShortcut(n, keyEvent);
        }
        return false;
    }
    
    @Override
    boolean onKeyShortcut(final int n, final KeyEvent keyEvent) {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.onKeyShortcut(n, keyEvent)) {
            if (this.mPreparedPanel == null || !this.performPanelShortcut(this.mPreparedPanel, keyEvent.getKeyCode(), keyEvent, 1)) {
                if (this.mPreparedPanel == null) {
                    final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(0, true);
                    this.preparePanel(panelState, keyEvent);
                    final boolean performPanelShortcut = this.performPanelShortcut(panelState, keyEvent.getKeyCode(), keyEvent, 1);
                    panelState.isPrepared = false;
                    if (performPanelShortcut) {
                        return true;
                    }
                }
                return false;
            }
            if (this.mPreparedPanel != null) {
                return this.mPreparedPanel.isHandled = true;
            }
        }
        return true;
    }
    
    boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        boolean b = true;
        switch (n) {
            case 82: {
                this.onKeyUpPanel(0, keyEvent);
                return true;
            }
            case 4: {
                final boolean mLongPressBackDown = this.mLongPressBackDown;
                this.mLongPressBackDown = false;
                final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(0, false);
                if (panelState != null && panelState.isOpen) {
                    if (!mLongPressBackDown) {
                        this.closePanel(panelState, true);
                        return true;
                    }
                    return b;
                }
                else {
                    if (this.onBackPressed()) {
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        b = false;
        return b;
    }
    
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        final Window$Callback windowCallback = this.getWindowCallback();
        if (windowCallback != null && !this.isDestroyed()) {
            final AppCompatDelegateImplV9$PanelFeatureState menuPanel = this.findMenuPanel((Menu)menuBuilder.getRootMenu());
            if (menuPanel != null) {
                return windowCallback.onMenuItemSelected(menuPanel.featureId, menuItem);
            }
        }
        return false;
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
        this.reopenMenu(menuBuilder, true);
    }
    
    @Override
    boolean onMenuOpened(final int n, final Menu menu) {
        if (n == 108) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(true);
            }
            return true;
        }
        return false;
    }
    
    @Override
    void onPanelClosed(final int n, final Menu menu) {
        if (n == 108) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        }
        else if (n == 0) {
            final AppCompatDelegateImplV9$PanelFeatureState panelState = this.getPanelState(n, true);
            if (panelState.isOpen) {
                this.closePanel(panelState, false);
            }
        }
    }
    
    @Override
    public void onPostCreate(final Bundle bundle) {
        this.ensureSubDecor();
    }
    
    @Override
    public void onPostResume() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }
    
    @Override
    public void onStop() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
    }
    
    void onSubDecorInstalled(final ViewGroup viewGroup) {
    }
    
    @Override
    void onTitleChanged(final CharSequence text) {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(text);
        }
        else {
            if (this.peekSupportActionBar() != null) {
                this.peekSupportActionBar().setWindowTitle(text);
                return;
            }
            if (this.mTitleView != null) {
                this.mTitleView.setText(text);
            }
        }
    }
    
    @Override
    public boolean requestWindowFeature(int sanitizeWindowFeatureId) {
        sanitizeWindowFeatureId = this.sanitizeWindowFeatureId(sanitizeWindowFeatureId);
        if (this.mWindowNoTitle && sanitizeWindowFeatureId == 108) {
            return false;
        }
        if (this.mHasActionBar && sanitizeWindowFeatureId == 1) {
            this.mHasActionBar = false;
        }
        switch (sanitizeWindowFeatureId) {
            default: {
                return this.mWindow.requestFeature(sanitizeWindowFeatureId);
            }
            case 108: {
                this.throwFeatureRequestIfSubDecorInstalled();
                return this.mHasActionBar = true;
            }
            case 109: {
                this.throwFeatureRequestIfSubDecorInstalled();
                return this.mOverlayActionBar = true;
            }
            case 10: {
                this.throwFeatureRequestIfSubDecorInstalled();
                return this.mOverlayActionMode = true;
            }
            case 2: {
                this.throwFeatureRequestIfSubDecorInstalled();
                return this.mFeatureProgress = true;
            }
            case 5: {
                this.throwFeatureRequestIfSubDecorInstalled();
                return this.mFeatureIndeterminateProgress = true;
            }
            case 1: {
                this.throwFeatureRequestIfSubDecorInstalled();
                return this.mWindowNoTitle = true;
            }
        }
    }
    
    @Override
    public void setContentView(final int n) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(n, viewGroup);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    @Override
    public void setContentView(final View view) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    @Override
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, viewGroup$LayoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    @Override
    public void setSupportActionBar(final Toolbar toolbar) {
        if (!(this.mOriginalWindowCallback instanceof Activity)) {
            return;
        }
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar instanceof WindowDecorActionBar) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
        this.mMenuInflater = null;
        if (supportActionBar != null) {
            supportActionBar.onDestroy();
        }
        if (toolbar != null) {
            final ToolbarActionBar mActionBar = new ToolbarActionBar(toolbar, ((Activity)this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
            this.mActionBar = mActionBar;
            this.mWindow.setCallback(mActionBar.getWrappedWindowCallback());
        }
        else {
            this.mActionBar = null;
            this.mWindow.setCallback(this.mAppCompatWindowCallback);
        }
        this.invalidateOptionsMenu();
    }
    
    final boolean shouldAnimateActionModeView() {
        return this.mSubDecorInstalled && this.mSubDecor != null && ViewCompat.isLaidOut((View)this.mSubDecor);
    }
    
    @Override
    public ActionMode startSupportActionMode(final ActionMode$Callback actionMode$Callback) {
        if (actionMode$Callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        final AppCompatDelegateImplV9$ActionModeCallbackWrapperV9 appCompatDelegateImplV9$ActionModeCallbackWrapperV9 = new AppCompatDelegateImplV9$ActionModeCallbackWrapperV9(this, actionMode$Callback);
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            this.mActionMode = supportActionBar.startActionMode(appCompatDelegateImplV9$ActionModeCallbackWrapperV9);
            if (this.mActionMode != null && this.mAppCompatCallback != null) {
                this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
            }
        }
        if (this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(appCompatDelegateImplV9$ActionModeCallbackWrapperV9);
        }
        return this.mActionMode;
    }
    
    @Override
    ActionMode startSupportActionModeFromWindow(final ActionMode$Callback actionMode$Callback) {
        this.endOnGoingFadeAnimation();
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        ActionMode$Callback actionMode$Callback2 = actionMode$Callback;
        if (!(actionMode$Callback instanceof AppCompatDelegateImplV9$ActionModeCallbackWrapperV9)) {
            actionMode$Callback2 = new AppCompatDelegateImplV9$ActionModeCallbackWrapperV9(this, actionMode$Callback);
        }
    Label_0065:
        while (true) {
            if (this.mAppCompatCallback == null || this.isDestroyed()) {
                final ActionMode onWindowStartingSupportActionMode = null;
                break Label_0065;
            }
            while (true) {
                while (true) {
                    try {
                        final ActionMode onWindowStartingSupportActionMode = this.mAppCompatCallback.onWindowStartingSupportActionMode(actionMode$Callback2);
                        if (onWindowStartingSupportActionMode != null) {
                            this.mActionMode = onWindowStartingSupportActionMode;
                            if (this.mActionMode != null && this.mAppCompatCallback != null) {
                                this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
                            }
                            return this.mActionMode;
                        }
                    }
                    catch (AbstractMethodError abstractMethodError) {
                        final ActionMode onWindowStartingSupportActionMode = null;
                        continue Label_0065;
                    }
                    if (this.mActionModeView == null) {
                        if (this.mIsFloating) {
                            final TypedValue typedValue = new TypedValue();
                            final Resources$Theme theme = this.mContext.getTheme();
                            theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                            Object mContext;
                            if (typedValue.resourceId != 0) {
                                final Resources$Theme theme2 = this.mContext.getResources().newTheme();
                                theme2.setTo(theme);
                                theme2.applyStyle(typedValue.resourceId, true);
                                mContext = new ContextThemeWrapper(this.mContext, 0);
                                ((Context)mContext).getTheme().setTo(theme2);
                            }
                            else {
                                mContext = this.mContext;
                            }
                            this.mActionModeView = new ActionBarContextView((Context)mContext);
                            PopupWindowCompat.setWindowLayoutType(this.mActionModePopup = new PopupWindow((Context)mContext, (AttributeSet)null, R$attr.actionModePopupWindowStyle), 2);
                            this.mActionModePopup.setContentView((View)this.mActionModeView);
                            this.mActionModePopup.setWidth(-1);
                            ((Context)mContext).getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                            this.mActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, ((Context)mContext).getResources().getDisplayMetrics()));
                            this.mActionModePopup.setHeight(-2);
                            this.mShowActionModePopup = new AppCompatDelegateImplV9$5(this);
                        }
                        else {
                            final ViewStubCompat viewStubCompat = (ViewStubCompat)this.mSubDecor.findViewById(R$id.action_mode_bar_stub);
                            if (viewStubCompat != null) {
                                viewStubCompat.setLayoutInflater(LayoutInflater.from(this.getActionBarThemedContext()));
                                this.mActionModeView = (ActionBarContextView)viewStubCompat.inflate();
                            }
                        }
                    }
                    if (this.mActionModeView == null) {
                        continue;
                    }
                    this.endOnGoingFadeAnimation();
                    this.mActionModeView.killMode();
                    final StandaloneActionMode mActionMode = new StandaloneActionMode(this.mActionModeView.getContext(), this.mActionModeView, actionMode$Callback2, this.mActionModePopup == null);
                    if (!actionMode$Callback2.onCreateActionMode(mActionMode, mActionMode.getMenu())) {
                        this.mActionMode = null;
                        continue;
                    }
                    mActionMode.invalidate();
                    this.mActionModeView.initForMode(mActionMode);
                    this.mActionMode = mActionMode;
                    if (this.shouldAnimateActionModeView()) {
                        ViewCompat.setAlpha((View)this.mActionModeView, 0.0f);
                        (this.mFadeAnim = ViewCompat.animate((View)this.mActionModeView).alpha(1.0f)).setListener(new AppCompatDelegateImplV9$6(this));
                    }
                    else {
                        ViewCompat.setAlpha((View)this.mActionModeView, 1.0f);
                        this.mActionModeView.setVisibility(0);
                        this.mActionModeView.sendAccessibilityEvent(32);
                        if (this.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent());
                        }
                    }
                    if (this.mActionModePopup != null) {
                        this.mWindow.getDecorView().post(this.mShowActionModePopup);
                        continue;
                    }
                    continue;
                }
            }
            break;
        }
    }
    
    int updateStatusGuard(int n) {
        int n2 = 1;
        boolean b = true;
        final boolean b2 = false;
        int n6;
        if (this.mActionModeView != null && this.mActionModeView.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) {
            final ViewGroup$MarginLayoutParams layoutParams = (ViewGroup$MarginLayoutParams)this.mActionModeView.getLayoutParams();
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                final Rect mTempRect1 = this.mTempRect1;
                final Rect mTempRect2 = this.mTempRect2;
                mTempRect1.set(0, n, 0, 0);
                ViewUtils.computeFitSystemWindows((View)this.mSubDecor, mTempRect1, mTempRect2);
                int n3;
                if (mTempRect2.top == 0) {
                    n3 = n;
                }
                else {
                    n3 = 0;
                }
                int n4;
                if (layoutParams.topMargin != n3) {
                    layoutParams.topMargin = n;
                    if (this.mStatusGuard == null) {
                        (this.mStatusGuard = new View(this.mContext)).setBackgroundColor(this.mContext.getResources().getColor(R$color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup$LayoutParams(-1, n));
                        n4 = 1;
                    }
                    else {
                        final ViewGroup$LayoutParams layoutParams2 = this.mStatusGuard.getLayoutParams();
                        if (layoutParams2.height != n) {
                            layoutParams2.height = n;
                            this.mStatusGuard.setLayoutParams(layoutParams2);
                        }
                        n4 = 1;
                    }
                }
                else {
                    n4 = 0;
                }
                if (this.mStatusGuard == null) {
                    b = false;
                }
                int n5 = n;
                if (!this.mOverlayActionMode) {
                    n5 = n;
                    if (b) {
                        n5 = 0;
                    }
                }
                n = n5;
                n2 = n4;
                n6 = (b ? 1 : 0);
            }
            else if (layoutParams.topMargin != 0) {
                layoutParams.topMargin = 0;
                n6 = 0;
            }
            else {
                n2 = 0;
                n6 = 0;
            }
            if (n2 != 0) {
                this.mActionModeView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
        }
        else {
            n6 = 0;
        }
        if (this.mStatusGuard != null) {
            final View mStatusGuard = this.mStatusGuard;
            int visibility;
            if (n6 != 0) {
                visibility = (b2 ? 1 : 0);
            }
            else {
                visibility = 8;
            }
            mStatusGuard.setVisibility(visibility);
        }
        return n;
    }
}
