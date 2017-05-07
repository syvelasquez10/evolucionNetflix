// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View$MeasureSpec;
import android.widget.ListAdapter;
import android.view.inputmethod.InputMethodManager;
import android.content.ActivityNotFoundException;
import android.support.v4.view.KeyEventCompat;
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
import android.view.KeyEvent;
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
import android.view.View$OnKeyListener;
import android.support.v4.widget.CursorAdapter;
import android.app.SearchableInfo;
import android.graphics.drawable.Drawable$ConstantState;
import java.util.WeakHashMap;
import android.view.View$OnFocusChangeListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.TextView$OnEditorActionListener;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.view.View;
import android.view.View$OnClickListener;

class SearchView$7 implements View$OnClickListener
{
    final /* synthetic */ SearchView this$0;
    
    SearchView$7(final SearchView this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (view == this.this$0.mSearchButton) {
            this.this$0.onSearchClicked();
        }
        else {
            if (view == this.this$0.mCloseButton) {
                this.this$0.onCloseClicked();
                return;
            }
            if (view == this.this$0.mSubmitButton) {
                this.this$0.onSubmitQuery();
                return;
            }
            if (view == this.this$0.mVoiceButton) {
                if (SearchView.IS_AT_LEAST_FROYO) {
                    this.this$0.onVoiceClicked();
                }
            }
            else if (view == this.this$0.mQueryTextView) {
                this.this$0.forceSuggestionQuery();
            }
        }
    }
}
