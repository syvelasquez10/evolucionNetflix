// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.support.v4.view.GravityCompat;
import java.util.Iterator;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.widget.TextView;
import android.support.v7.appcompat.R$layout;
import android.widget.FrameLayout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.HeaderViewListAdapter;
import android.view.MenuItem;
import android.widget.AdapterView$OnItemClickListener;
import android.util.AttributeSet;
import android.support.v7.widget.MenuPopupWindow;
import android.content.res.Resources;
import android.support.v7.appcompat.R$dimen;
import java.util.ArrayList;
import java.util.LinkedList;
import android.view.ViewTreeObserver;
import android.os.Handler;
import java.util.List;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow$OnDismissListener;
import android.view.View$OnKeyListener;

final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View$OnKeyListener, PopupWindow$OnDismissListener
{
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final ViewTreeObserver$OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener;
    private final int mMenuMaxWidth;
    private PopupWindow$OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List<MenuBuilder> mPendingMenus;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter$Callback mPresenterCallback;
    private int mRawDropDownGravity;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    final List<CascadingMenuPopup$CascadingMenuInfo> mShowingMenus;
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler;
    private ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;
    
    public CascadingMenuPopup(final Context mContext, final View mAnchorView, final int mPopupStyleAttr, final int mPopupStyleRes, final boolean mOverflowOnly) {
        this.mPendingMenus = new LinkedList<MenuBuilder>();
        this.mShowingMenus = new ArrayList<CascadingMenuPopup$CascadingMenuInfo>();
        this.mGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new CascadingMenuPopup$1(this);
        this.mMenuItemHoverListener = new CascadingMenuPopup$2(this);
        this.mRawDropDownGravity = 0;
        this.mDropDownGravity = 0;
        this.mContext = mContext;
        this.mAnchorView = mAnchorView;
        this.mPopupStyleAttr = mPopupStyleAttr;
        this.mPopupStyleRes = mPopupStyleRes;
        this.mOverflowOnly = mOverflowOnly;
        this.mForceShowIcon = false;
        this.mLastPosition = this.getInitialMenuPosition();
        final Resources resources = mContext.getResources();
        this.mMenuMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.mSubMenuHoverHandler = new Handler();
    }
    
    private MenuPopupWindow createPopupWindow() {
        final MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        menuPopupWindow.setHoverListener(this.mMenuItemHoverListener);
        menuPopupWindow.setOnItemClickListener((AdapterView$OnItemClickListener)this);
        menuPopupWindow.setOnDismissListener((PopupWindow$OnDismissListener)this);
        menuPopupWindow.setAnchorView(this.mAnchorView);
        menuPopupWindow.setDropDownGravity(this.mDropDownGravity);
        menuPopupWindow.setModal(true);
        return menuPopupWindow;
    }
    
    private int findIndexOfAddedMenu(final MenuBuilder menuBuilder) {
        for (int i = 0; i < this.mShowingMenus.size(); ++i) {
            if (menuBuilder == this.mShowingMenus.get(i).menu) {
                return i;
            }
        }
        return -1;
    }
    
    private MenuItem findMenuItemForSubmenu(final MenuBuilder menuBuilder, final MenuBuilder menuBuilder2) {
        for (int size = menuBuilder.size(), i = 0; i < size; ++i) {
            final MenuItem item = menuBuilder.getItem(i);
            if (item.hasSubMenu() && menuBuilder2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }
    
    private View findParentViewForSubmenu(final CascadingMenuPopup$CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo, final MenuBuilder menuBuilder) {
        int i = 0;
        final MenuItem menuItemForSubmenu = this.findMenuItemForSubmenu(cascadingMenuPopup$CascadingMenuInfo.menu, menuBuilder);
        if (menuItemForSubmenu == null) {
            return null;
        }
        final ListView listView = cascadingMenuPopup$CascadingMenuInfo.getListView();
        final ListAdapter adapter = listView.getAdapter();
        int headersCount;
        MenuAdapter menuAdapter;
        if (adapter instanceof HeaderViewListAdapter) {
            final HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter)adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter)headerViewListAdapter.getWrappedAdapter();
        }
        else {
            menuAdapter = (MenuAdapter)adapter;
            headersCount = 0;
        }
        while (true) {
            while (i < menuAdapter.getCount()) {
                if (menuItemForSubmenu == menuAdapter.getItem(i)) {
                    if (i == -1) {
                        return null;
                    }
                    final int n = i + headersCount - listView.getFirstVisiblePosition();
                    if (n < 0 || n >= listView.getChildCount()) {
                        return null;
                    }
                    return listView.getChildAt(n);
                }
                else {
                    ++i;
                }
            }
            i = -1;
            continue;
        }
    }
    
    private int getInitialMenuPosition() {
        int n = 1;
        if (ViewCompat.getLayoutDirection(this.mAnchorView) == 1) {
            n = 0;
        }
        return n;
    }
    
    private int getNextMenuPosition(final int n) {
        final ListView listView = this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
        final int[] array = new int[2];
        listView.getLocationOnScreen(array);
        final Rect rect = new Rect();
        this.mShownAnchorView.getWindowVisibleDisplayFrame(rect);
        if (this.mLastPosition == 1) {
            if (listView.getWidth() + array[0] + n > rect.right) {
                return 0;
            }
            return 1;
        }
        else {
            if (array[0] - n < 0) {
                return 1;
            }
            return 0;
        }
    }
    
    private void showMenu(final MenuBuilder menuBuilder) {
        final LayoutInflater from = LayoutInflater.from(this.mContext);
        final MenuAdapter adapter = new MenuAdapter(menuBuilder, from, this.mOverflowOnly);
        if (!this.isShowing() && this.mForceShowIcon) {
            adapter.setForceShowIcon(true);
        }
        else if (this.isShowing()) {
            adapter.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(menuBuilder));
        }
        final int measureIndividualMenuWidth = MenuPopup.measureIndividualMenuWidth((ListAdapter)adapter, null, this.mContext, this.mMenuMaxWidth);
        final MenuPopupWindow popupWindow = this.createPopupWindow();
        popupWindow.setAdapter((ListAdapter)adapter);
        popupWindow.setContentWidth(measureIndividualMenuWidth);
        popupWindow.setDropDownGravity(this.mDropDownGravity);
        CascadingMenuPopup$CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo;
        View parentViewForSubmenu;
        if (this.mShowingMenus.size() > 0) {
            cascadingMenuPopup$CascadingMenuInfo = this.mShowingMenus.get(this.mShowingMenus.size() - 1);
            parentViewForSubmenu = this.findParentViewForSubmenu(cascadingMenuPopup$CascadingMenuInfo, menuBuilder);
        }
        else {
            parentViewForSubmenu = null;
            cascadingMenuPopup$CascadingMenuInfo = null;
        }
        if (parentViewForSubmenu != null) {
            popupWindow.setTouchModal(false);
            popupWindow.setEnterTransition(null);
            final int nextMenuPosition = this.getNextMenuPosition(measureIndividualMenuWidth);
            boolean b;
            if (nextMenuPosition == 1) {
                b = true;
            }
            else {
                b = false;
            }
            this.mLastPosition = nextMenuPosition;
            final int[] array = new int[2];
            parentViewForSubmenu.getLocationInWindow(array);
            final int n = cascadingMenuPopup$CascadingMenuInfo.window.getHorizontalOffset() + array[0];
            final int verticalOffset = cascadingMenuPopup$CascadingMenuInfo.window.getVerticalOffset();
            final int n2 = array[1];
            int horizontalOffset;
            if ((this.mDropDownGravity & 0x5) == 0x5) {
                if (b) {
                    horizontalOffset = n + measureIndividualMenuWidth;
                }
                else {
                    horizontalOffset = n - parentViewForSubmenu.getWidth();
                }
            }
            else if (b) {
                horizontalOffset = parentViewForSubmenu.getWidth() + n;
            }
            else {
                horizontalOffset = n - measureIndividualMenuWidth;
            }
            popupWindow.setHorizontalOffset(horizontalOffset);
            popupWindow.setVerticalOffset(n2 + verticalOffset);
        }
        else {
            if (this.mHasXOffset) {
                popupWindow.setHorizontalOffset(this.mXOffset);
            }
            if (this.mHasYOffset) {
                popupWindow.setVerticalOffset(this.mYOffset);
            }
            popupWindow.setEpicenterBounds(this.getEpicenterBounds());
        }
        this.mShowingMenus.add(new CascadingMenuPopup$CascadingMenuInfo(popupWindow, menuBuilder, this.mLastPosition));
        popupWindow.show();
        if (cascadingMenuPopup$CascadingMenuInfo == null && this.mShowTitle && menuBuilder.getHeaderTitle() != null) {
            final ListView listView = popupWindow.getListView();
            final FrameLayout frameLayout = (FrameLayout)from.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup)listView, false);
            final TextView textView = (TextView)frameLayout.findViewById(16908310);
            frameLayout.setEnabled(false);
            textView.setText(menuBuilder.getHeaderTitle());
            listView.addHeaderView((View)frameLayout, (Object)null, false);
            popupWindow.show();
        }
    }
    
    @Override
    public void addMenu(final MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.mContext);
        if (this.isShowing()) {
            this.showMenu(menuBuilder);
            return;
        }
        this.mPendingMenus.add(menuBuilder);
    }
    
    @Override
    protected boolean closeMenuOnSubMenuOpened() {
        return false;
    }
    
    public void dismiss() {
        final int size = this.mShowingMenus.size();
        if (size > 0) {
            final CascadingMenuPopup$CascadingMenuInfo[] array = this.mShowingMenus.toArray(new CascadingMenuPopup$CascadingMenuInfo[size]);
            for (int i = size - 1; i >= 0; --i) {
                final CascadingMenuPopup$CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo = array[i];
                if (cascadingMenuPopup$CascadingMenuInfo.window.isShowing()) {
                    cascadingMenuPopup$CascadingMenuInfo.window.dismiss();
                }
            }
        }
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    public ListView getListView() {
        if (this.mShowingMenus.isEmpty()) {
            return null;
        }
        return this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
    }
    
    public boolean isShowing() {
        return this.mShowingMenus.size() > 0 && this.mShowingMenus.get(0).window.isShowing();
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        final int indexOfAddedMenu = this.findIndexOfAddedMenu(menuBuilder);
        if (indexOfAddedMenu >= 0) {
            final int n = indexOfAddedMenu + 1;
            if (n < this.mShowingMenus.size()) {
                this.mShowingMenus.get(n).menu.close(false);
            }
            final CascadingMenuPopup$CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo = this.mShowingMenus.remove(indexOfAddedMenu);
            cascadingMenuPopup$CascadingMenuInfo.menu.removeMenuPresenter(this);
            if (this.mShouldCloseImmediately) {
                cascadingMenuPopup$CascadingMenuInfo.window.setExitTransition(null);
                cascadingMenuPopup$CascadingMenuInfo.window.setAnimationStyle(0);
            }
            cascadingMenuPopup$CascadingMenuInfo.window.dismiss();
            final int size = this.mShowingMenus.size();
            if (size > 0) {
                this.mLastPosition = this.mShowingMenus.get(size - 1).position;
            }
            else {
                this.mLastPosition = this.getInitialMenuPosition();
            }
            if (size == 0) {
                this.dismiss();
                if (this.mPresenterCallback != null) {
                    this.mPresenterCallback.onCloseMenu(menuBuilder, true);
                }
                if (this.mTreeObserver != null) {
                    if (this.mTreeObserver.isAlive()) {
                        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
                    }
                    this.mTreeObserver = null;
                }
                this.mOnDismissListener.onDismiss();
                return;
            }
            if (b) {
                this.mShowingMenus.get(0).menu.close(false);
            }
        }
    }
    
    public void onDismiss() {
        while (true) {
            for (int size = this.mShowingMenus.size(), i = 0; i < size; ++i) {
                final CascadingMenuPopup$CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo = this.mShowingMenus.get(i);
                if (!cascadingMenuPopup$CascadingMenuInfo.window.isShowing()) {
                    if (cascadingMenuPopup$CascadingMenuInfo != null) {
                        cascadingMenuPopup$CascadingMenuInfo.menu.close(false);
                    }
                    return;
                }
            }
            final CascadingMenuPopup$CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo = null;
            continue;
        }
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && n == 82) {
            this.dismiss();
            return true;
        }
        return false;
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        for (final CascadingMenuPopup$CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo : this.mShowingMenus) {
            if (subMenuBuilder == cascadingMenuPopup$CascadingMenuInfo.menu) {
                cascadingMenuPopup$CascadingMenuInfo.getListView().requestFocus();
                return true;
            }
        }
        if (subMenuBuilder.hasVisibleItems()) {
            this.addMenu(subMenuBuilder);
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onOpenSubMenu(subMenuBuilder);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void setAnchorView(final View mAnchorView) {
        if (this.mAnchorView != mAnchorView) {
            this.mAnchorView = mAnchorView;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }
    
    @Override
    public void setCallback(final MenuPresenter$Callback mPresenterCallback) {
        this.mPresenterCallback = mPresenterCallback;
    }
    
    @Override
    public void setForceShowIcon(final boolean mForceShowIcon) {
        this.mForceShowIcon = mForceShowIcon;
    }
    
    @Override
    public void setGravity(final int mRawDropDownGravity) {
        if (this.mRawDropDownGravity != mRawDropDownGravity) {
            this.mRawDropDownGravity = mRawDropDownGravity;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }
    
    @Override
    public void setHorizontalOffset(final int mxOffset) {
        this.mHasXOffset = true;
        this.mXOffset = mxOffset;
    }
    
    @Override
    public void setOnDismissListener(final PopupWindow$OnDismissListener mOnDismissListener) {
        this.mOnDismissListener = mOnDismissListener;
    }
    
    @Override
    public void setShowTitle(final boolean mShowTitle) {
        this.mShowTitle = mShowTitle;
    }
    
    @Override
    public void setVerticalOffset(final int myOffset) {
        this.mHasYOffset = true;
        this.mYOffset = myOffset;
    }
    
    public void show() {
        if (!this.isShowing()) {
            final Iterator<MenuBuilder> iterator = this.mPendingMenus.iterator();
            while (iterator.hasNext()) {
                this.showMenu(iterator.next());
            }
            this.mPendingMenus.clear();
            this.mShownAnchorView = this.mAnchorView;
            if (this.mShownAnchorView != null) {
                int n;
                if (this.mTreeObserver == null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
                if (n != 0) {
                    this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
            }
        }
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        final Iterator<CascadingMenuPopup$CascadingMenuInfo> iterator = this.mShowingMenus.iterator();
        while (iterator.hasNext()) {
            MenuPopup.toMenuAdapter(iterator.next().getListView().getAdapter()).notifyDataSetChanged();
        }
    }
}
