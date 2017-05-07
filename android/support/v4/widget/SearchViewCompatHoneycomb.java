// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.app.SearchManager;
import android.content.ComponentName;
import android.widget.SearchView$OnQueryTextListener;
import android.widget.SearchView$OnCloseListener;
import android.content.Context;
import android.widget.SearchView;
import android.view.View;

class SearchViewCompatHoneycomb
{
    public static CharSequence getQuery(final View view) {
        return ((SearchView)view).getQuery();
    }
    
    public static boolean isIconified(final View view) {
        return ((SearchView)view).isIconified();
    }
    
    public static boolean isQueryRefinementEnabled(final View view) {
        return ((SearchView)view).isQueryRefinementEnabled();
    }
    
    public static boolean isSubmitButtonEnabled(final View view) {
        return ((SearchView)view).isSubmitButtonEnabled();
    }
    
    public static Object newOnCloseListener(final SearchViewCompatHoneycomb$OnCloseListenerCompatBridge searchViewCompatHoneycomb$OnCloseListenerCompatBridge) {
        return new SearchViewCompatHoneycomb$2(searchViewCompatHoneycomb$OnCloseListenerCompatBridge);
    }
    
    public static Object newOnQueryTextListener(final SearchViewCompatHoneycomb$OnQueryTextListenerCompatBridge searchViewCompatHoneycomb$OnQueryTextListenerCompatBridge) {
        return new SearchViewCompatHoneycomb$1(searchViewCompatHoneycomb$OnQueryTextListenerCompatBridge);
    }
    
    public static View newSearchView(final Context context) {
        return (View)new SearchView(context);
    }
    
    public static void setIconified(final View view, final boolean iconified) {
        ((SearchView)view).setIconified(iconified);
    }
    
    public static void setMaxWidth(final View view, final int maxWidth) {
        ((SearchView)view).setMaxWidth(maxWidth);
    }
    
    public static void setOnCloseListener(final Object o, final Object o2) {
        ((SearchView)o).setOnCloseListener((SearchView$OnCloseListener)o2);
    }
    
    public static void setOnQueryTextListener(final Object o, final Object o2) {
        ((SearchView)o).setOnQueryTextListener((SearchView$OnQueryTextListener)o2);
    }
    
    public static void setQuery(final View view, final CharSequence charSequence, final boolean b) {
        ((SearchView)view).setQuery(charSequence, b);
    }
    
    public static void setQueryHint(final View view, final CharSequence queryHint) {
        ((SearchView)view).setQueryHint(queryHint);
    }
    
    public static void setQueryRefinementEnabled(final View view, final boolean queryRefinementEnabled) {
        ((SearchView)view).setQueryRefinementEnabled(queryRefinementEnabled);
    }
    
    public static void setSearchableInfo(final View view, final ComponentName componentName) {
        final SearchView searchView = (SearchView)view;
        searchView.setSearchableInfo(((SearchManager)searchView.getContext().getSystemService("search")).getSearchableInfo(componentName));
    }
    
    public static void setSubmitButtonEnabled(final View view, final boolean submitButtonEnabled) {
        ((SearchView)view).setSubmitButtonEnabled(submitButtonEnabled);
    }
}
