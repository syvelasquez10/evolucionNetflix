// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.view.MenuItem;
import android.view.ActionMode$Callback;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.view.Menu;
import android.view.View;
import android.content.Context;
import android.view.MenuInflater;
import android.annotation.TargetApi;
import android.view.ActionMode;

@TargetApi(11)
public class SupportActionModeWrapper extends ActionMode
{
    final MenuInflater mInflater;
    final android.support.v7.view.ActionMode mWrappedObject;
    
    public SupportActionModeWrapper(final Context context, final android.support.v7.view.ActionMode mWrappedObject) {
        this.mWrappedObject = mWrappedObject;
        this.mInflater = new SupportMenuInflater(context);
    }
    
    public void finish() {
        this.mWrappedObject.finish();
    }
    
    public View getCustomView() {
        return this.mWrappedObject.getCustomView();
    }
    
    public Menu getMenu() {
        return MenuWrapperFactory.createMenuWrapper(this.mWrappedObject.getMenu());
    }
    
    public MenuInflater getMenuInflater() {
        return this.mInflater;
    }
    
    public CharSequence getSubtitle() {
        return this.mWrappedObject.getSubtitle();
    }
    
    public Object getTag() {
        return this.mWrappedObject.getTag();
    }
    
    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }
    
    public boolean getTitleOptionalHint() {
        return this.mWrappedObject.getTitleOptionalHint();
    }
    
    public void invalidate() {
        this.mWrappedObject.invalidate();
    }
    
    public boolean isTitleOptional() {
        return this.mWrappedObject.isTitleOptional();
    }
    
    public void setCustomView(final View customView) {
        this.mWrappedObject.setCustomView(customView);
    }
    
    public void setSubtitle(final int subtitle) {
        this.mWrappedObject.setSubtitle(subtitle);
    }
    
    public void setSubtitle(final CharSequence subtitle) {
        this.mWrappedObject.setSubtitle(subtitle);
    }
    
    public void setTag(final Object tag) {
        this.mWrappedObject.setTag(tag);
    }
    
    public void setTitle(final int title) {
        this.mWrappedObject.setTitle(title);
    }
    
    public void setTitle(final CharSequence title) {
        this.mWrappedObject.setTitle(title);
    }
    
    public void setTitleOptionalHint(final boolean titleOptionalHint) {
        this.mWrappedObject.setTitleOptionalHint(titleOptionalHint);
    }
    
    public static class CallbackWrapper implements Callback
    {
        final SimpleArrayMap<ActionMode, SupportActionModeWrapper> mActionModes;
        final Context mContext;
        final ActionMode$Callback mWrappedCallback;
        
        public CallbackWrapper(final Context mContext, final ActionMode$Callback mWrappedCallback) {
            this.mContext = mContext;
            this.mWrappedCallback = mWrappedCallback;
            this.mActionModes = new SimpleArrayMap<ActionMode, SupportActionModeWrapper>();
        }
        
        private android.view.ActionMode getActionModeWrapper(final ActionMode actionMode) {
            final SupportActionModeWrapper supportActionModeWrapper = this.mActionModes.get(actionMode);
            if (supportActionModeWrapper != null) {
                return supportActionModeWrapper;
            }
            final SupportActionModeWrapper supportActionModeWrapper2 = new SupportActionModeWrapper(this.mContext, actionMode);
            this.mActionModes.put(actionMode, supportActionModeWrapper2);
            return supportActionModeWrapper2;
        }
        
        @Override
        public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
            return this.mWrappedCallback.onActionItemClicked(this.getActionModeWrapper(actionMode), MenuWrapperFactory.createMenuItemWrapper(menuItem));
        }
        
        @Override
        public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
            return this.mWrappedCallback.onCreateActionMode(this.getActionModeWrapper(actionMode), MenuWrapperFactory.createMenuWrapper(menu));
        }
        
        @Override
        public void onDestroyActionMode(final ActionMode actionMode) {
            this.mWrappedCallback.onDestroyActionMode(this.getActionModeWrapper(actionMode));
        }
        
        @Override
        public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
            return this.mWrappedCallback.onPrepareActionMode(this.getActionModeWrapper(actionMode), MenuWrapperFactory.createMenuWrapper(menu));
        }
    }
}
