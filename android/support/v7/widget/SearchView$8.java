// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.KeyEvent$DispatcherState;
import android.view.View$MeasureSpec;
import android.widget.ListAdapter;
import android.view.inputmethod.InputMethodManager;
import android.content.ActivityNotFoundException;
import android.text.Editable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.text.SpannableStringBuilder;
import android.widget.AutoCompleteTextView;
import android.content.ComponentName;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.util.Log;
import android.database.Cursor;
import android.net.Uri;
import android.content.res.Resources;
import android.support.v7.appcompat.R$dimen;
import android.support.v7.internal.widget.ViewUtils;
import android.graphics.Rect;
import android.annotation.TargetApi;
import android.view.View$OnLayoutChangeListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.text.TextUtils;
import android.support.v7.appcompat.R$id;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.content.Intent;
import android.support.v7.internal.widget.TintManager;
import android.text.TextWatcher;
import android.support.v4.widget.CursorAdapter;
import android.app.SearchableInfo;
import android.graphics.drawable.Drawable$ConstantState;
import java.util.WeakHashMap;
import android.view.View$OnFocusChangeListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View$OnKeyListener;

class SearchView$8 implements View$OnKeyListener
{
    final /* synthetic */ SearchView this$0;
    
    SearchView$8(final SearchView this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (this.this$0.mSearchable != null) {
            if (this.this$0.mQueryTextView.isPopupShowing() && this.this$0.mQueryTextView.getListSelection() != -1) {
                return this.this$0.onSuggestionsKey(view, n, keyEvent);
            }
            if (!this.this$0.mQueryTextView.isEmpty() && KeyEventCompat.hasNoModifiers(keyEvent) && keyEvent.getAction() == 1 && n == 66) {
                view.cancelLongPress();
                this.this$0.launchQuerySearch(0, null, this.this$0.mQueryTextView.getText().toString());
                return true;
            }
        }
        return false;
    }
}
