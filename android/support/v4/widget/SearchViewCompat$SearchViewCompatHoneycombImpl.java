// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.view.View;

class SearchViewCompat$SearchViewCompatHoneycombImpl extends SearchViewCompat$SearchViewCompatStubImpl
{
    @Override
    public CharSequence getQuery(final View view) {
        return SearchViewCompatHoneycomb.getQuery(view);
    }
    
    @Override
    public boolean isIconified(final View view) {
        return SearchViewCompatHoneycomb.isIconified(view);
    }
    
    @Override
    public boolean isQueryRefinementEnabled(final View view) {
        return SearchViewCompatHoneycomb.isQueryRefinementEnabled(view);
    }
    
    @Override
    public boolean isSubmitButtonEnabled(final View view) {
        return SearchViewCompatHoneycomb.isSubmitButtonEnabled(view);
    }
    
    @Override
    public Object newOnCloseListener(final SearchViewCompat$OnCloseListenerCompat searchViewCompat$OnCloseListenerCompat) {
        return SearchViewCompatHoneycomb.newOnCloseListener(new SearchViewCompat$SearchViewCompatHoneycombImpl$2(this, searchViewCompat$OnCloseListenerCompat));
    }
    
    @Override
    public Object newOnQueryTextListener(final SearchViewCompat$OnQueryTextListenerCompat searchViewCompat$OnQueryTextListenerCompat) {
        return SearchViewCompatHoneycomb.newOnQueryTextListener(new SearchViewCompat$SearchViewCompatHoneycombImpl$1(this, searchViewCompat$OnQueryTextListenerCompat));
    }
    
    @Override
    public View newSearchView(final Context context) {
        return SearchViewCompatHoneycomb.newSearchView(context);
    }
    
    @Override
    public void setIconified(final View view, final boolean b) {
        SearchViewCompatHoneycomb.setIconified(view, b);
    }
    
    @Override
    public void setMaxWidth(final View view, final int n) {
        SearchViewCompatHoneycomb.setMaxWidth(view, n);
    }
    
    @Override
    public void setOnCloseListener(final Object o, final Object o2) {
        SearchViewCompatHoneycomb.setOnCloseListener(o, o2);
    }
    
    @Override
    public void setOnQueryTextListener(final Object o, final Object o2) {
        SearchViewCompatHoneycomb.setOnQueryTextListener(o, o2);
    }
    
    @Override
    public void setQuery(final View view, final CharSequence charSequence, final boolean b) {
        SearchViewCompatHoneycomb.setQuery(view, charSequence, b);
    }
    
    @Override
    public void setQueryHint(final View view, final CharSequence charSequence) {
        SearchViewCompatHoneycomb.setQueryHint(view, charSequence);
    }
    
    @Override
    public void setQueryRefinementEnabled(final View view, final boolean b) {
        SearchViewCompatHoneycomb.setQueryRefinementEnabled(view, b);
    }
    
    @Override
    public void setSearchableInfo(final View view, final ComponentName componentName) {
        SearchViewCompatHoneycomb.setSearchableInfo(view, componentName);
    }
    
    @Override
    public void setSubmitButtonEnabled(final View view, final boolean b) {
        SearchViewCompatHoneycomb.setSubmitButtonEnabled(view, b);
    }
}
