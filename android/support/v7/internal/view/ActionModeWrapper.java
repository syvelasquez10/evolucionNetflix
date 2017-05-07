// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.view.MenuItem;
import android.view.ActionMode$Callback;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.view.Menu;
import android.view.View;
import android.content.Context;
import android.view.MenuInflater;
import android.support.v7.view.ActionMode;

public class ActionModeWrapper extends ActionMode
{
    final MenuInflater mInflater;
    final android.view.ActionMode mWrappedObject;
    
    public ActionModeWrapper(final Context context, final android.view.ActionMode mWrappedObject) {
        this.mWrappedObject = mWrappedObject;
        this.mInflater = new SupportMenuInflater(context);
    }
    
    @Override
    public void finish() {
        this.mWrappedObject.finish();
    }
    
    @Override
    public View getCustomView() {
        return this.mWrappedObject.getCustomView();
    }
    
    @Override
    public Menu getMenu() {
        return MenuWrapperFactory.createMenuWrapper(this.mWrappedObject.getMenu());
    }
    
    @Override
    public MenuInflater getMenuInflater() {
        return this.mInflater;
    }
    
    @Override
    public CharSequence getSubtitle() {
        return this.mWrappedObject.getSubtitle();
    }
    
    @Override
    public Object getTag() {
        return this.mWrappedObject.getTag();
    }
    
    @Override
    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }
    
    @Override
    public boolean getTitleOptionalHint() {
        return this.mWrappedObject.getTitleOptionalHint();
    }
    
    @Override
    public void invalidate() {
        this.mWrappedObject.invalidate();
    }
    
    @Override
    public boolean isTitleOptional() {
        return this.mWrappedObject.isTitleOptional();
    }
    
    @Override
    public void setCustomView(final View customView) {
        this.mWrappedObject.setCustomView(customView);
    }
    
    @Override
    public void setSubtitle(final int subtitle) {
        this.mWrappedObject.setSubtitle(subtitle);
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        this.mWrappedObject.setSubtitle(subtitle);
    }
    
    @Override
    public void setTag(final Object tag) {
        this.mWrappedObject.setTag(tag);
    }
    
    @Override
    public void setTitle(final int title) {
        this.mWrappedObject.setTitle(title);
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        this.mWrappedObject.setTitle(title);
    }
    
    @Override
    public void setTitleOptionalHint(final boolean titleOptionalHint) {
        this.mWrappedObject.setTitleOptionalHint(titleOptionalHint);
    }
    
    public static class CallbackWrapper implements ActionMode$Callback
    {
        final Context mContext;
        private ActionModeWrapper mLastStartedActionMode;
        final Callback mWrappedCallback;
        
        public CallbackWrapper(final Context mContext, final Callback mWrappedCallback) {
            this.mContext = mContext;
            this.mWrappedCallback = mWrappedCallback;
        }
        
        private ActionMode getActionModeWrapper(final android.view.ActionMode actionMode) {
            if (this.mLastStartedActionMode != null && this.mLastStartedActionMode.mWrappedObject == actionMode) {
                return this.mLastStartedActionMode;
            }
            return new ActionModeWrapper(this.mContext, actionMode);
        }
        
        public boolean onActionItemClicked(final android.view.ActionMode actionMode, final MenuItem menuItem) {
            return this.mWrappedCallback.onActionItemClicked(this.getActionModeWrapper(actionMode), MenuWrapperFactory.createMenuItemWrapper(menuItem));
        }
        
        public boolean onCreateActionMode(final android.view.ActionMode actionMode, final Menu menu) {
            return this.mWrappedCallback.onCreateActionMode(this.getActionModeWrapper(actionMode), MenuWrapperFactory.createMenuWrapper(menu));
        }
        
        public void onDestroyActionMode(final android.view.ActionMode actionMode) {
            this.mWrappedCallback.onDestroyActionMode(this.getActionModeWrapper(actionMode));
        }
        
        public boolean onPrepareActionMode(final android.view.ActionMode actionMode, final Menu menu) {
            return this.mWrappedCallback.onPrepareActionMode(this.getActionModeWrapper(actionMode), MenuWrapperFactory.createMenuWrapper(menu));
        }
        
        public void setLastStartedActionMode(final ActionModeWrapper mLastStartedActionMode) {
            this.mLastStartedActionMode = mLastStartedActionMode;
        }
    }
}
