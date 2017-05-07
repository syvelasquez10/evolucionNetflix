// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.internal.view.StandaloneActionMode;
import android.support.v7.internal.widget.ViewStubCompat;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.support.v7.internal.widget.FitWindowsViewGroup$OnFitSystemWindowsListener;
import android.support.v7.internal.widget.FitWindowsViewGroup;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.view.LayoutInflater;
import android.support.v7.internal.widget.TintCheckedTextView;
import android.support.v7.internal.widget.TintRadioButton;
import android.support.v7.internal.widget.TintCheckBox;
import android.support.v7.internal.widget.TintSpinner;
import android.support.v7.internal.widget.TintEditText;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.appcompat.R$color;
import android.support.v7.internal.widget.ViewUtils;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.view.KeyCharacterMap;
import android.support.v4.view.ViewCompat;
import android.content.res.Resources$Theme;
import android.support.v7.internal.view.menu.y;
import android.support.v7.appcompat.R$id;
import android.support.v7.internal.widget.ProgressBarCompat;
import android.support.v7.appcompat.R$layout;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$attr;
import android.view.KeyEvent;
import android.os.Bundle;
import android.support.v7.internal.app.WindowCallback;
import android.util.DisplayMetrics;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.view.menu.i;
import android.view.Menu;
import android.support.v7.internal.view.menu.g;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.View;
import android.support.v7.internal.widget.DecorContentParent;
import android.support.v7.internal.widget.ActionBarContextView;
import android.widget.PopupWindow;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.view.menu.j;

class ActionBarActivityDelegateBase extends ActionBarActivityDelegate implements j
{
    private ActionBarActivityDelegateBase$ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private boolean mClosingActionMenu;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private int mInvalidatePanelMenuFeatures;
    private boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    private ActionBarActivityDelegateBase$PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private ActionBarActivityDelegateBase$PanelFeatureState[] mPanels;
    private ActionBarActivityDelegateBase$PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitleToSet;
    private g mToolbarListMenuPresenter;
    private ViewGroup mWindowDecor;
    
    ActionBarActivityDelegateBase(final ActionBarActivity actionBarActivity) {
        super(actionBarActivity);
        this.mInvalidatePanelMenuRunnable = new ActionBarActivityDelegateBase$1(this);
    }
    
    private void applyFixedSizeWindow() {
        TypedValue typedValue = null;
        final TypedValue typedValue2 = null;
        final TypedArray obtainStyledAttributes = this.mActivity.obtainStyledAttributes(R$styleable.Theme);
        TypedValue typedValue3;
        if (obtainStyledAttributes.hasValue(R$styleable.Theme_windowFixedWidthMajor)) {
            if (!false) {
                typedValue3 = new TypedValue();
            }
            else {
                typedValue3 = null;
            }
            obtainStyledAttributes.getValue(R$styleable.Theme_windowFixedWidthMajor, typedValue3);
        }
        else {
            typedValue3 = null;
        }
        TypedValue typedValue4;
        if (obtainStyledAttributes.hasValue(R$styleable.Theme_windowFixedWidthMinor)) {
            if (!false) {
                typedValue4 = new TypedValue();
            }
            else {
                typedValue4 = null;
            }
            obtainStyledAttributes.getValue(R$styleable.Theme_windowFixedWidthMinor, typedValue4);
        }
        else {
            typedValue4 = null;
        }
        TypedValue typedValue5;
        if (obtainStyledAttributes.hasValue(R$styleable.Theme_windowFixedHeightMajor)) {
            if (!false) {
                typedValue5 = new TypedValue();
            }
            else {
                typedValue5 = null;
            }
            obtainStyledAttributes.getValue(R$styleable.Theme_windowFixedHeightMajor, typedValue5);
        }
        else {
            typedValue5 = null;
        }
        if (obtainStyledAttributes.hasValue(R$styleable.Theme_windowFixedHeightMinor)) {
            typedValue = typedValue2;
            if (!false) {
                typedValue = new TypedValue();
            }
            obtainStyledAttributes.getValue(R$styleable.Theme_windowFixedHeightMinor, typedValue);
        }
        final DisplayMetrics displayMetrics = this.mActivity.getResources().getDisplayMetrics();
        boolean b;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            b = true;
        }
        else {
            b = false;
        }
        if (!b) {
            typedValue4 = typedValue3;
        }
        while (true) {
            Label_0372: {
                if (typedValue4 == null || typedValue4.type == 0) {
                    break Label_0372;
                }
                int n;
                if (typedValue4.type == 5) {
                    n = (int)typedValue4.getDimension(displayMetrics);
                }
                else {
                    if (typedValue4.type != 6) {
                        break Label_0372;
                    }
                    n = (int)typedValue4.getFraction((float)displayMetrics.widthPixels, (float)displayMetrics.widthPixels);
                }
                if (!b) {
                    typedValue5 = typedValue;
                }
                while (true) {
                    Label_0367: {
                        if (typedValue5 == null || typedValue5.type == 0) {
                            break Label_0367;
                        }
                        int n2;
                        if (typedValue5.type == 5) {
                            n2 = (int)typedValue5.getDimension(displayMetrics);
                        }
                        else {
                            if (typedValue5.type != 6) {
                                break Label_0367;
                            }
                            n2 = (int)typedValue5.getFraction((float)displayMetrics.heightPixels, (float)displayMetrics.heightPixels);
                        }
                        if (n != -1 || n2 != -1) {
                            this.mActivity.getWindow().setLayout(n, n2);
                        }
                        obtainStyledAttributes.recycle();
                        return;
                    }
                    int n2 = -1;
                    continue;
                }
            }
            int n = -1;
            continue;
        }
    }
    
    private void callOnPanelClosed(final int n, final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState, final Menu menu) {
        ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState2 = actionBarActivityDelegateBase$PanelFeatureState;
        Object menu2 = menu;
        if (menu == null) {
            ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState3;
            if ((actionBarActivityDelegateBase$PanelFeatureState3 = actionBarActivityDelegateBase$PanelFeatureState) == null) {
                actionBarActivityDelegateBase$PanelFeatureState3 = actionBarActivityDelegateBase$PanelFeatureState;
                if (n >= 0) {
                    actionBarActivityDelegateBase$PanelFeatureState3 = actionBarActivityDelegateBase$PanelFeatureState;
                    if (n < this.mPanels.length) {
                        actionBarActivityDelegateBase$PanelFeatureState3 = this.mPanels[n];
                    }
                }
            }
            actionBarActivityDelegateBase$PanelFeatureState2 = actionBarActivityDelegateBase$PanelFeatureState3;
            menu2 = menu;
            if (actionBarActivityDelegateBase$PanelFeatureState3 != null) {
                menu2 = actionBarActivityDelegateBase$PanelFeatureState3.menu;
                actionBarActivityDelegateBase$PanelFeatureState2 = actionBarActivityDelegateBase$PanelFeatureState3;
            }
        }
        if (actionBarActivityDelegateBase$PanelFeatureState2 != null && !actionBarActivityDelegateBase$PanelFeatureState2.isOpen) {
            return;
        }
        this.getWindowCallback().onPanelClosed(n, (Menu)menu2);
    }
    
    private void checkCloseActionMenu(final i i) {
        if (this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        this.mDecorContentParent.dismissPopups();
        final WindowCallback windowCallback = this.getWindowCallback();
        if (windowCallback != null && !this.isDestroyed()) {
            windowCallback.onPanelClosed(8, (Menu)i);
        }
        this.mClosingActionMenu = false;
    }
    
    private void closePanel(final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState, final boolean b) {
        if (b && actionBarActivityDelegateBase$PanelFeatureState.featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing()) {
            this.checkCloseActionMenu(actionBarActivityDelegateBase$PanelFeatureState.menu);
        }
        else {
            if (actionBarActivityDelegateBase$PanelFeatureState.isOpen && b) {
                this.callOnPanelClosed(actionBarActivityDelegateBase$PanelFeatureState.featureId, actionBarActivityDelegateBase$PanelFeatureState, null);
            }
            actionBarActivityDelegateBase$PanelFeatureState.isPrepared = false;
            actionBarActivityDelegateBase$PanelFeatureState.isHandled = false;
            actionBarActivityDelegateBase$PanelFeatureState.isOpen = false;
            actionBarActivityDelegateBase$PanelFeatureState.shownPanelView = null;
            actionBarActivityDelegateBase$PanelFeatureState.refreshDecorView = true;
            if (this.mPreparedPanel == actionBarActivityDelegateBase$PanelFeatureState) {
                this.mPreparedPanel = null;
            }
        }
    }
    
    private void doInvalidatePanelMenu(final int n) {
        final ActionBarActivityDelegateBase$PanelFeatureState panelState = this.getPanelState(n, true);
        if (panelState.menu != null) {
            final Bundle frozenActionViewState = new Bundle();
            panelState.menu.a(frozenActionViewState);
            if (frozenActionViewState.size() > 0) {
                panelState.frozenActionViewState = frozenActionViewState;
            }
            panelState.menu.g();
            panelState.menu.clear();
        }
        panelState.refreshMenuContent = true;
        panelState.refreshDecorView = true;
        if ((n == 8 || n == 0) && this.mDecorContentParent != null) {
            final ActionBarActivityDelegateBase$PanelFeatureState panelState2 = this.getPanelState(0, false);
            if (panelState2 != null) {
                panelState2.isPrepared = false;
                this.preparePanel(panelState2, null);
            }
        }
    }
    
    private void ensureToolbarListMenuPresenter() {
        if (this.mToolbarListMenuPresenter == null) {
            final TypedValue typedValue = new TypedValue();
            this.mActivity.getTheme().resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            final ActionBarActivity mActivity = this.mActivity;
            int n;
            if (typedValue.resourceId != 0) {
                n = typedValue.resourceId;
            }
            else {
                n = R$style.Theme_AppCompat_CompactMenu;
            }
            this.mToolbarListMenuPresenter = new g((Context)new ContextThemeWrapper((Context)mActivity, n), R$layout.abc_list_menu_item_layout);
        }
    }
    
    private ActionBarActivityDelegateBase$PanelFeatureState findMenuPanel(final Menu menu) {
        final ActionBarActivityDelegateBase$PanelFeatureState[] mPanels = this.mPanels;
        int length;
        if (mPanels != null) {
            length = mPanels.length;
        }
        else {
            length = 0;
        }
        for (int i = 0; i < length; ++i) {
            final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState = mPanels[i];
            if (actionBarActivityDelegateBase$PanelFeatureState != null && actionBarActivityDelegateBase$PanelFeatureState.menu == menu) {
                return actionBarActivityDelegateBase$PanelFeatureState;
            }
        }
        return null;
    }
    
    private ProgressBarCompat getCircularProgressBar() {
        final ProgressBarCompat progressBarCompat = (ProgressBarCompat)this.mActivity.findViewById(R$id.progress_circular);
        if (progressBarCompat != null) {
            progressBarCompat.setVisibility(4);
        }
        return progressBarCompat;
    }
    
    private ProgressBarCompat getHorizontalProgressBar() {
        final ProgressBarCompat progressBarCompat = (ProgressBarCompat)this.mActivity.findViewById(R$id.progress_horizontal);
        if (progressBarCompat != null) {
            progressBarCompat.setVisibility(4);
        }
        return progressBarCompat;
    }
    
    private ActionBarActivityDelegateBase$PanelFeatureState getPanelState(final int n, final boolean b) {
        final ActionBarActivityDelegateBase$PanelFeatureState[] mPanels = this.mPanels;
        ActionBarActivityDelegateBase$PanelFeatureState[] mPanels2 = null;
        Label_0049: {
            if (mPanels != null) {
                mPanels2 = mPanels;
                if (mPanels.length > n) {
                    break Label_0049;
                }
            }
            mPanels2 = new ActionBarActivityDelegateBase$PanelFeatureState[n + 1];
            if (mPanels != null) {
                System.arraycopy(mPanels, 0, mPanels2, 0, mPanels.length);
            }
            this.mPanels = mPanels2;
        }
        final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState = mPanels2[n];
        if (actionBarActivityDelegateBase$PanelFeatureState == null) {
            return mPanels2[n] = new ActionBarActivityDelegateBase$PanelFeatureState(n);
        }
        return actionBarActivityDelegateBase$PanelFeatureState;
    }
    
    private void hideProgressBars(final ProgressBarCompat progressBarCompat, final ProgressBarCompat progressBarCompat2) {
        if (this.mFeatureIndeterminateProgress && progressBarCompat2.getVisibility() == 0) {
            progressBarCompat2.setVisibility(4);
        }
        if (this.mFeatureProgress && progressBarCompat.getVisibility() == 0) {
            progressBarCompat.setVisibility(4);
        }
    }
    
    private boolean initializePanelContent(final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState) {
        if (actionBarActivityDelegateBase$PanelFeatureState.menu == null) {
            return false;
        }
        if (this.mPanelMenuPresenterCallback == null) {
            this.mPanelMenuPresenterCallback = new ActionBarActivityDelegateBase$PanelMenuPresenterCallback(this, null);
        }
        actionBarActivityDelegateBase$PanelFeatureState.shownPanelView = (View)actionBarActivityDelegateBase$PanelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
        return actionBarActivityDelegateBase$PanelFeatureState.shownPanelView != null;
    }
    
    private void initializePanelDecor(final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState) {
        actionBarActivityDelegateBase$PanelFeatureState.decorView = this.mWindowDecor;
        actionBarActivityDelegateBase$PanelFeatureState.setStyle(this.getActionBarThemedContext());
    }
    
    private boolean initializePanelMenu(final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState) {
        final ActionBarActivity mActivity = this.mActivity;
        while (true) {
            Label_0203: {
                if ((actionBarActivityDelegateBase$PanelFeatureState.featureId != 0 && actionBarActivityDelegateBase$PanelFeatureState.featureId != 8) || this.mDecorContentParent == null) {
                    break Label_0203;
                }
                final TypedValue typedValue = new TypedValue();
                final Resources$Theme theme = ((Context)mActivity).getTheme();
                theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                Resources$Theme theme2 = null;
                if (typedValue.resourceId != 0) {
                    theme2 = ((Context)mActivity).getResources().newTheme();
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
                        theme3 = ((Context)mActivity).getResources().newTheme();
                        theme3.setTo(theme);
                    }
                    theme3.applyStyle(typedValue.resourceId, true);
                }
                if (theme3 == null) {
                    break Label_0203;
                }
                final Object o = new ContextThemeWrapper((Context)mActivity, 0);
                ((Context)o).getTheme().setTo(theme3);
                final i menu = new i((Context)o);
                menu.a(this);
                actionBarActivityDelegateBase$PanelFeatureState.setMenu(menu);
                return true;
            }
            final Object o = mActivity;
            continue;
        }
    }
    
    private void invalidatePanelMenu(final int n) {
        this.mInvalidatePanelMenuFeatures |= 1 << n;
        if (!this.mInvalidatePanelMenuPosted && this.mWindowDecor != null) {
            ViewCompat.postOnAnimation((View)this.mWindowDecor, this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }
    
    private void openPanel(final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState, final KeyEvent keyEvent) {
        if (!actionBarActivityDelegateBase$PanelFeatureState.isOpen && !this.isDestroyed()) {
            if (actionBarActivityDelegateBase$PanelFeatureState.featureId == 0) {
                final ActionBarActivity mActivity = this.mActivity;
                boolean b;
                if ((((Context)mActivity).getResources().getConfiguration().screenLayout & 0xF) == 0x4) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (((Context)mActivity).getApplicationInfo().targetSdkVersion >= 11) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    return;
                }
            }
            final WindowCallback windowCallback = this.getWindowCallback();
            if (windowCallback != null && !windowCallback.onMenuOpened(actionBarActivityDelegateBase$PanelFeatureState.featureId, (Menu)actionBarActivityDelegateBase$PanelFeatureState.menu)) {
                this.closePanel(actionBarActivityDelegateBase$PanelFeatureState, true);
                return;
            }
            if (this.preparePanel(actionBarActivityDelegateBase$PanelFeatureState, keyEvent)) {
                if (actionBarActivityDelegateBase$PanelFeatureState.decorView == null || actionBarActivityDelegateBase$PanelFeatureState.refreshDecorView) {
                    this.initializePanelDecor(actionBarActivityDelegateBase$PanelFeatureState);
                }
                if (this.initializePanelContent(actionBarActivityDelegateBase$PanelFeatureState) && actionBarActivityDelegateBase$PanelFeatureState.hasPanelItems()) {
                    actionBarActivityDelegateBase$PanelFeatureState.isHandled = false;
                    actionBarActivityDelegateBase$PanelFeatureState.isOpen = true;
                }
            }
        }
    }
    
    private boolean preparePanel(final ActionBarActivityDelegateBase$PanelFeatureState mPreparedPanel, final KeyEvent keyEvent) {
        if (!this.isDestroyed()) {
            if (mPreparedPanel.isPrepared) {
                return true;
            }
            if (this.mPreparedPanel != null && this.mPreparedPanel != mPreparedPanel) {
                this.closePanel(this.mPreparedPanel, false);
            }
            boolean b;
            if (mPreparedPanel.featureId == 0 || mPreparedPanel.featureId == 8) {
                b = true;
            }
            else {
                b = false;
            }
            if (b && this.mDecorContentParent != null) {
                this.mDecorContentParent.setMenuPrepared();
            }
            if (mPreparedPanel.menu == null || mPreparedPanel.refreshMenuContent) {
                if (mPreparedPanel.menu == null && (!this.initializePanelMenu(mPreparedPanel) || mPreparedPanel.menu == null)) {
                    return false;
                }
                if (b && this.mDecorContentParent != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionBarActivityDelegateBase$ActionMenuPresenterCallback(this, null);
                    }
                    this.mDecorContentParent.setMenu((Menu)mPreparedPanel.menu, this.mActionMenuPresenterCallback);
                }
                mPreparedPanel.menu.g();
                if (!this.getWindowCallback().onCreatePanelMenu(mPreparedPanel.featureId, (Menu)mPreparedPanel.menu)) {
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
            mPreparedPanel.menu.g();
            if (mPreparedPanel.frozenActionViewState != null) {
                mPreparedPanel.menu.b(mPreparedPanel.frozenActionViewState);
                mPreparedPanel.frozenActionViewState = null;
            }
            if (!this.getWindowCallback().onPreparePanel(0, null, (Menu)mPreparedPanel.menu)) {
                if (b && this.mDecorContentParent != null) {
                    this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                }
                mPreparedPanel.menu.h();
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
            mPreparedPanel.menu.h();
            mPreparedPanel.isPrepared = true;
            mPreparedPanel.isHandled = false;
            this.mPreparedPanel = mPreparedPanel;
            return true;
        }
        return false;
    }
    
    private void reopenMenu(final i i, final boolean b) {
        if (this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && (!ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get((Context)this.mActivity)) || this.mDecorContentParent.isOverflowMenuShowPending())) {
            final WindowCallback windowCallback = this.getWindowCallback();
            if (!this.mDecorContentParent.isOverflowMenuShowing() || !b) {
                if (windowCallback != null && !this.isDestroyed()) {
                    if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 0x1) != 0x0) {
                        this.mWindowDecor.removeCallbacks(this.mInvalidatePanelMenuRunnable);
                        this.mInvalidatePanelMenuRunnable.run();
                    }
                    final ActionBarActivityDelegateBase$PanelFeatureState panelState = this.getPanelState(0, true);
                    if (panelState.menu != null && !panelState.refreshMenuContent && windowCallback.onPreparePanel(0, null, (Menu)panelState.menu)) {
                        windowCallback.onMenuOpened(8, (Menu)panelState.menu);
                        this.mDecorContentParent.showOverflowMenu();
                    }
                }
            }
            else {
                this.mDecorContentParent.hideOverflowMenu();
                if (!this.isDestroyed()) {
                    this.mActivity.onPanelClosed(8, (Menu)this.getPanelState(0, true).menu);
                }
            }
            return;
        }
        final ActionBarActivityDelegateBase$PanelFeatureState panelState2 = this.getPanelState(0, true);
        panelState2.refreshDecorView = true;
        this.closePanel(panelState2, false);
        this.openPanel(panelState2, null);
    }
    
    private void showProgressBars(final ProgressBarCompat progressBarCompat, final ProgressBarCompat progressBarCompat2) {
        if (this.mFeatureIndeterminateProgress && progressBarCompat2.getVisibility() == 4) {
            progressBarCompat2.setVisibility(0);
        }
        if (this.mFeatureProgress && progressBarCompat.getProgress() < 10000) {
            progressBarCompat.setVisibility(0);
        }
    }
    
    private void updateProgressBars(int progress) {
        final ProgressBarCompat circularProgressBar = this.getCircularProgressBar();
        final ProgressBarCompat horizontalProgressBar = this.getHorizontalProgressBar();
        if (progress == -1) {
            if (this.mFeatureProgress) {
                progress = horizontalProgressBar.getProgress();
                if (horizontalProgressBar.isIndeterminate() || progress < 10000) {
                    progress = 0;
                }
                else {
                    progress = 4;
                }
                horizontalProgressBar.setVisibility(progress);
            }
            if (this.mFeatureIndeterminateProgress) {
                circularProgressBar.setVisibility(0);
            }
        }
        else if (progress == -2) {
            if (this.mFeatureProgress) {
                horizontalProgressBar.setVisibility(8);
            }
            if (this.mFeatureIndeterminateProgress) {
                circularProgressBar.setVisibility(8);
            }
        }
        else {
            if (progress == -3) {
                horizontalProgressBar.setIndeterminate(true);
                return;
            }
            if (progress == -4) {
                horizontalProgressBar.setIndeterminate(false);
                return;
            }
            if (progress >= 0 && progress <= 10000) {
                horizontalProgressBar.setProgress(progress + 0);
                if (progress < 10000) {
                    this.showProgressBars(horizontalProgressBar, circularProgressBar);
                    return;
                }
                this.hideProgressBars(horizontalProgressBar, circularProgressBar);
            }
        }
    }
    
    private int updateStatusGuard(int n) {
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
                        (this.mStatusGuard = new View((Context)this.mActivity)).setBackgroundColor(this.mActivity.getResources().getColor(R$color.abc_input_method_navigation_guard));
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
    
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.ensureSubDecor();
        ((ViewGroup)this.mActivity.findViewById(16908290)).addView(view, viewGroup$LayoutParams);
        this.mActivity.onSupportContentChanged();
    }
    
    public ActionBar createSupportActionBar() {
        this.ensureSubDecor();
        final WindowDecorActionBar windowDecorActionBar = new WindowDecorActionBar(this.mActivity, this.mOverlayActionBar);
        windowDecorActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
        return windowDecorActionBar;
    }
    
    @Override
    View createView(final String s, final AttributeSet set) {
        if (Build$VERSION.SDK_INT < 21) {
            switch (s) {
                case "EditText": {
                    return (View)new TintEditText((Context)this.mActivity, set);
                }
                case "Spinner": {
                    return (View)new TintSpinner((Context)this.mActivity, set);
                }
                case "CheckBox": {
                    return (View)new TintCheckBox((Context)this.mActivity, set);
                }
                case "RadioButton": {
                    return (View)new TintRadioButton((Context)this.mActivity, set);
                }
                case "CheckedTextView": {
                    return (View)new TintCheckedTextView((Context)this.mActivity, set);
                }
            }
        }
        return null;
    }
    
    final void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            if (this.mHasActionBar) {
                final TypedValue typedValue = new TypedValue();
                this.mActivity.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                Object mActivity;
                if (typedValue.resourceId != 0) {
                    mActivity = new ContextThemeWrapper((Context)this.mActivity, typedValue.resourceId);
                }
                else {
                    mActivity = this.mActivity;
                }
                this.mSubDecor = (ViewGroup)LayoutInflater.from((Context)mActivity).inflate(R$layout.abc_screen_toolbar, (ViewGroup)null);
                (this.mDecorContentParent = (DecorContentParent)this.mSubDecor.findViewById(R$id.decor_content_parent)).setWindowCallback(this.getWindowCallback());
                if (this.mOverlayActionBar) {
                    this.mDecorContentParent.initFeature(9);
                }
                if (this.mFeatureProgress) {
                    this.mDecorContentParent.initFeature(2);
                }
                if (this.mFeatureIndeterminateProgress) {
                    this.mDecorContentParent.initFeature(5);
                }
            }
            else {
                if (this.mOverlayActionMode) {
                    this.mSubDecor = (ViewGroup)LayoutInflater.from((Context)this.mActivity).inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup)null);
                }
                else {
                    this.mSubDecor = (ViewGroup)LayoutInflater.from((Context)this.mActivity).inflate(R$layout.abc_screen_simple, (ViewGroup)null);
                }
                if (Build$VERSION.SDK_INT >= 21) {
                    ViewCompat.setOnApplyWindowInsetsListener((View)this.mSubDecor, new ActionBarActivityDelegateBase$2(this));
                }
                else {
                    ((FitWindowsViewGroup)this.mSubDecor).setOnFitSystemWindowsListener(new ActionBarActivityDelegateBase$3(this));
                }
            }
            ViewUtils.makeOptionalFitsSystemWindows((View)this.mSubDecor);
            this.mActivity.superSetContentView((View)this.mSubDecor);
            final View viewById = this.mActivity.findViewById(16908290);
            viewById.setId(-1);
            this.mActivity.findViewById(R$id.action_bar_activity_content).setId(16908290);
            if (viewById instanceof FrameLayout) {
                ((FrameLayout)viewById).setForeground((Drawable)null);
            }
            if (this.mTitleToSet != null && this.mDecorContentParent != null) {
                this.mDecorContentParent.setWindowTitle(this.mTitleToSet);
                this.mTitleToSet = null;
            }
            this.applyFixedSizeWindow();
            this.onSubDecorInstalled();
            this.mSubDecorInstalled = true;
            final ActionBarActivityDelegateBase$PanelFeatureState panelState = this.getPanelState(0, false);
            if (!this.isDestroyed() && (panelState == null || panelState.menu == null)) {
                this.invalidatePanelMenu(8);
            }
        }
    }
    
    public boolean onBackPressed() {
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
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (this.mHasActionBar && this.mSubDecorInstalled) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.onConfigurationChanged(configuration);
            }
        }
    }
    
    public void onContentChanged() {
    }
    
    @Override
    void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mWindowDecor = (ViewGroup)this.mActivity.getWindow().getDecorView();
        if (NavUtils.getParentActivityName(this.mActivity) != null) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
                return;
            }
            this.mEnableDefaultActionBarUp = true;
        }
    }
    
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        return n != 0 && this.getWindowCallback().onCreatePanelMenu(n, menu);
    }
    
    public View onCreatePanelView(final int n) {
        if (this.mActionMode == null) {
            final WindowCallback windowCallback = this.getWindowCallback();
            View onCreatePanelView;
            if (windowCallback != null) {
                onCreatePanelView = windowCallback.onCreatePanelView(n);
            }
            else {
                onCreatePanelView = null;
            }
            View shownPanelView = onCreatePanelView;
            if (onCreatePanelView == null) {
                shownPanelView = onCreatePanelView;
                if (this.mToolbarListMenuPresenter == null) {
                    final ActionBarActivityDelegateBase$PanelFeatureState panelState = this.getPanelState(n, true);
                    this.openPanel(panelState, null);
                    shownPanelView = onCreatePanelView;
                    if (panelState.isOpen) {
                        shownPanelView = panelState.shownPanelView;
                    }
                }
            }
            return shownPanelView;
        }
        return null;
    }
    
    @Override
    boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return this.onKeyShortcut(n, keyEvent);
    }
    
    @Override
    boolean onKeyShortcut(final int n, final KeyEvent keyEvent) {
        if (this.mPreparedPanel == null || !this.performPanelShortcut(this.mPreparedPanel, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.mPreparedPanel == null) {
                final ActionBarActivityDelegateBase$PanelFeatureState panelState = this.getPanelState(0, true);
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
            this.mPreparedPanel.isHandled = true;
        }
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(final i i, final MenuItem menuItem) {
        final WindowCallback windowCallback = this.getWindowCallback();
        if (windowCallback != null && !this.isDestroyed()) {
            final ActionBarActivityDelegateBase$PanelFeatureState menuPanel = this.findMenuPanel((Menu)i.p());
            if (menuPanel != null) {
                return windowCallback.onMenuItemSelected(menuPanel.featureId, menuItem);
            }
        }
        return false;
    }
    
    @Override
    public void onMenuModeChange(final i i) {
        this.reopenMenu(i, true);
    }
    
    @Override
    boolean onMenuOpened(final int n, final Menu menu) {
        if (n == 8) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(true);
            }
            return true;
        }
        return this.mActivity.superOnMenuOpened(n, menu);
    }
    
    public void onPanelClosed(final int n, final Menu menu) {
        final ActionBarActivityDelegateBase$PanelFeatureState panelState = this.getPanelState(n, false);
        if (panelState != null) {
            this.closePanel(panelState, false);
        }
        if (n == 8) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        }
        else if (!this.isDestroyed()) {
            this.mActivity.superOnPanelClosed(n, menu);
        }
    }
    
    public void onPostResume() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }
    
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        return n != 0 && this.getWindowCallback().onPreparePanel(n, view, menu);
    }
    
    public void onStop() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
    }
    
    void onSubDecorInstalled() {
    }
    
    public void onTitleChanged(final CharSequence mTitleToSet) {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(mTitleToSet);
            return;
        }
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setWindowTitle(mTitleToSet);
            return;
        }
        this.mTitleToSet = mTitleToSet;
    }
    
    final boolean performPanelShortcut(final ActionBarActivityDelegateBase$PanelFeatureState actionBarActivityDelegateBase$PanelFeatureState, final int n, final KeyEvent keyEvent, final int n2) {
        final boolean b = false;
        final boolean b2 = false;
        boolean b3;
        if (keyEvent.isSystem()) {
            b3 = b2;
        }
        else {
            boolean performShortcut = false;
            Label_0064: {
                if (!actionBarActivityDelegateBase$PanelFeatureState.isPrepared) {
                    performShortcut = b;
                    if (!this.preparePanel(actionBarActivityDelegateBase$PanelFeatureState, keyEvent)) {
                        break Label_0064;
                    }
                }
                performShortcut = b;
                if (actionBarActivityDelegateBase$PanelFeatureState.menu != null) {
                    performShortcut = actionBarActivityDelegateBase$PanelFeatureState.menu.performShortcut(n, keyEvent, n2);
                }
            }
            b3 = performShortcut;
            if (performShortcut) {
                b3 = performShortcut;
                if ((n2 & 0x1) == 0x0) {
                    b3 = performShortcut;
                    if (this.mDecorContentParent == null) {
                        this.closePanel(actionBarActivityDelegateBase$PanelFeatureState, true);
                        return performShortcut;
                    }
                }
            }
        }
        return b3;
    }
    
    public void setContentView(final int n) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mActivity.findViewById(16908290);
        viewGroup.removeAllViews();
        this.mActivity.getLayoutInflater().inflate(n, viewGroup);
        this.mActivity.onSupportContentChanged();
    }
    
    public void setContentView(final View view) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mActivity.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mActivity.onSupportContentChanged();
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mActivity.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, viewGroup$LayoutParams);
        this.mActivity.onSupportContentChanged();
    }
    
    @Override
    void setSupportActionBar(final Toolbar toolbar) {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar instanceof WindowDecorActionBar) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
        if (supportActionBar instanceof ToolbarActionBar) {
            ((ToolbarActionBar)supportActionBar).setListMenuPresenter(null);
        }
        final ToolbarActionBar supportActionBar2 = new ToolbarActionBar(toolbar, this.mActivity.getTitle(), this.mActivity.getWindow(), this.mDefaultWindowCallback);
        this.ensureToolbarListMenuPresenter();
        supportActionBar2.setListMenuPresenter(this.mToolbarListMenuPresenter);
        this.setSupportActionBar(supportActionBar2);
        this.setWindowCallback(supportActionBar2.getWrappedWindowCallback());
        supportActionBar2.invalidateOptionsMenu();
    }
    
    @Override
    void setSupportProgress(final int n) {
        this.updateProgressBars(n + 0);
    }
    
    @Override
    void setSupportProgressBarIndeterminate(final boolean b) {
        int n;
        if (b) {
            n = -3;
        }
        else {
            n = -4;
        }
        this.updateProgressBars(n);
    }
    
    @Override
    void setSupportProgressBarIndeterminateVisibility(final boolean b) {
        int n;
        if (b) {
            n = -1;
        }
        else {
            n = -2;
        }
        this.updateProgressBars(n);
    }
    
    @Override
    void setSupportProgressBarVisibility(final boolean b) {
        int n;
        if (b) {
            n = -1;
        }
        else {
            n = -2;
        }
        this.updateProgressBars(n);
    }
    
    public ActionMode startSupportActionMode(final ActionMode$Callback actionMode$Callback) {
        if (actionMode$Callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        final ActionBarActivityDelegateBase$ActionModeCallbackWrapper actionBarActivityDelegateBase$ActionModeCallbackWrapper = new ActionBarActivityDelegateBase$ActionModeCallbackWrapper(this, actionMode$Callback);
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            this.mActionMode = supportActionBar.startActionMode(actionBarActivityDelegateBase$ActionModeCallbackWrapper);
            if (this.mActionMode != null) {
                this.mActivity.onSupportActionModeStarted(this.mActionMode);
            }
        }
        if (this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(actionBarActivityDelegateBase$ActionModeCallbackWrapper);
        }
        return this.mActionMode;
    }
    
    @Override
    ActionMode startSupportActionModeFromWindow(final ActionMode$Callback actionMode$Callback) {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        final ActionBarActivityDelegateBase$ActionModeCallbackWrapper actionBarActivityDelegateBase$ActionModeCallbackWrapper = new ActionBarActivityDelegateBase$ActionModeCallbackWrapper(this, actionMode$Callback);
        final Context actionBarThemedContext = this.getActionBarThemedContext();
        if (this.mActionModeView == null) {
            if (this.mIsFloating) {
                this.mActionModeView = new ActionBarContextView(actionBarThemedContext);
                (this.mActionModePopup = new PopupWindow(actionBarThemedContext, (AttributeSet)null, R$attr.actionModePopupWindowStyle)).setContentView((View)this.mActionModeView);
                this.mActionModePopup.setWidth(-1);
                final TypedValue typedValue = new TypedValue();
                this.mActivity.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                this.mActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, this.mActivity.getResources().getDisplayMetrics()));
                this.mActionModePopup.setHeight(-2);
                this.mShowActionModePopup = new ActionBarActivityDelegateBase$4(this);
            }
            else {
                final ViewStubCompat viewStubCompat = (ViewStubCompat)this.mActivity.findViewById(R$id.action_mode_bar_stub);
                if (viewStubCompat != null) {
                    viewStubCompat.setLayoutInflater(LayoutInflater.from(actionBarThemedContext));
                    this.mActionModeView = (ActionBarContextView)viewStubCompat.inflate();
                }
            }
        }
        if (this.mActionModeView != null) {
            this.mActionModeView.killMode();
            final StandaloneActionMode mActionMode = new StandaloneActionMode(actionBarThemedContext, this.mActionModeView, actionBarActivityDelegateBase$ActionModeCallbackWrapper, this.mActionModePopup == null);
            if (actionMode$Callback.onCreateActionMode(mActionMode, mActionMode.getMenu())) {
                mActionMode.invalidate();
                this.mActionModeView.initForMode(mActionMode);
                this.mActionModeView.setVisibility(0);
                this.mActionMode = mActionMode;
                if (this.mActionModePopup != null) {
                    this.mActivity.getWindow().getDecorView().post(this.mShowActionModePopup);
                }
                this.mActionModeView.sendAccessibilityEvent(32);
                if (this.mActionModeView.getParent() != null) {
                    ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent());
                }
            }
            else {
                this.mActionMode = null;
            }
        }
        if (this.mActionMode != null && this.mActivity != null) {
            this.mActivity.onSupportActionModeStarted(this.mActionMode);
        }
        return this.mActionMode;
    }
    
    public void supportInvalidateOptionsMenu() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null && supportActionBar.invalidateOptionsMenu()) {
            return;
        }
        this.invalidatePanelMenu(0);
    }
    
    public boolean supportRequestWindowFeature(final int n) {
        switch (n) {
            default: {
                return this.mActivity.requestWindowFeature(n);
            }
            case 8: {
                return this.mHasActionBar = true;
            }
            case 9: {
                return this.mOverlayActionBar = true;
            }
            case 10: {
                return this.mOverlayActionMode = true;
            }
            case 2: {
                return this.mFeatureProgress = true;
            }
            case 5: {
                return this.mFeatureIndeterminateProgress = true;
            }
        }
    }
}
