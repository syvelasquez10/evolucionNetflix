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
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView
{
    private static final boolean DBG = false;
    static final SearchView$AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    private static final boolean IS_AT_LEAST_FROYO;
    private static final String LOG_TAG = "SearchView";
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    private final ImageView mCloseButton;
    private int mCollapsedImeOptions;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final View$OnClickListener mOnClickListener;
    private SearchView$OnCloseListener mOnCloseListener;
    private final TextView$OnEditorActionListener mOnEditorActionListener;
    private final AdapterView$OnItemClickListener mOnItemClickListener;
    private final AdapterView$OnItemSelectedListener mOnItemSelectedListener;
    private SearchView$OnQueryTextListener mOnQueryChangeListener;
    private View$OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View$OnClickListener mOnSearchClickListener;
    private SearchView$OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap<String, Drawable$ConstantState> mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private final SearchView$SearchAutoComplete mQueryTextView;
    private Runnable mReleaseCursorRunnable;
    private final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final ImageView mSearchHintIcon;
    private final int mSearchIconResId;
    private final View mSearchPlate;
    private SearchableInfo mSearchable;
    private Runnable mShowImeRunnable;
    private final View mSubmitArea;
    private final ImageView mSubmitButton;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    private CursorAdapter mSuggestionsAdapter;
    View$OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private final TintManager mTintManager;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    private final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;
    
    static {
        IS_AT_LEAST_FROYO = (Build$VERSION.SDK_INT >= 8);
        HIDDEN_METHOD_INVOKER = new SearchView$AutoCompleteTextViewReflector();
    }
    
    public SearchView(final Context context) {
        this(context, null);
    }
    
    public SearchView(final Context context, final AttributeSet set) {
        this(context, set, R$attr.searchViewStyle);
    }
    
    public SearchView(final Context context, final AttributeSet set, int inputType) {
        super(context, set, inputType);
        this.mShowImeRunnable = new SearchView$1(this);
        this.mUpdateDrawableStateRunnable = new SearchView$2(this);
        this.mReleaseCursorRunnable = new SearchView$3(this);
        this.mOutsideDrawablesCache = new WeakHashMap<String, Drawable$ConstantState>();
        this.mOnClickListener = (View$OnClickListener)new SearchView$7(this);
        this.mTextKeyListener = (View$OnKeyListener)new SearchView$8(this);
        this.mOnEditorActionListener = (TextView$OnEditorActionListener)new SearchView$9(this);
        this.mOnItemClickListener = (AdapterView$OnItemClickListener)new SearchView$10(this);
        this.mOnItemSelectedListener = (AdapterView$OnItemSelectedListener)new SearchView$11(this);
        this.mTextWatcher = (TextWatcher)new SearchView$12(this);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.SearchView, inputType, 0);
        this.mTintManager = obtainStyledAttributes.getTintManager();
        ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(obtainStyledAttributes.getResourceId(R$styleable.SearchView_layout, 0), (ViewGroup)this, true);
        (this.mQueryTextView = (SearchView$SearchAutoComplete)this.findViewById(R$id.search_src_text)).setSearchView(this);
        this.mSearchEditFrame = this.findViewById(R$id.search_edit_frame);
        this.mSearchPlate = this.findViewById(R$id.search_plate);
        this.mSubmitArea = this.findViewById(R$id.submit_area);
        this.mSearchButton = (ImageView)this.findViewById(R$id.search_button);
        this.mSubmitButton = (ImageView)this.findViewById(R$id.search_go_btn);
        this.mCloseButton = (ImageView)this.findViewById(R$id.search_close_btn);
        this.mVoiceButton = (ImageView)this.findViewById(R$id.search_voice_btn);
        this.mSearchHintIcon = (ImageView)this.findViewById(R$id.search_mag_icon);
        this.mSearchPlate.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_queryBackground));
        this.mSubmitArea.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_submitBackground));
        this.mSearchIconResId = obtainStyledAttributes.getResourceId(R$styleable.SearchView_searchIcon, 0);
        this.mSearchButton.setImageResource(this.mSearchIconResId);
        this.mSubmitButton.setImageDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_goIcon));
        this.mCloseButton.setImageDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_closeIcon));
        this.mVoiceButton.setImageDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_voiceIcon));
        this.mSearchHintIcon.setImageDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_searchIcon));
        this.mSuggestionRowLayout = obtainStyledAttributes.getResourceId(R$styleable.SearchView_suggestionRowLayout, 0);
        this.mSuggestionCommitIconResId = obtainStyledAttributes.getResourceId(R$styleable.SearchView_commitIcon, 0);
        this.mSearchButton.setOnClickListener(this.mOnClickListener);
        this.mCloseButton.setOnClickListener(this.mOnClickListener);
        this.mSubmitButton.setOnClickListener(this.mOnClickListener);
        this.mVoiceButton.setOnClickListener(this.mOnClickListener);
        this.mQueryTextView.setOnClickListener(this.mOnClickListener);
        this.mQueryTextView.addTextChangedListener(this.mTextWatcher);
        this.mQueryTextView.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mQueryTextView.setOnItemClickListener(this.mOnItemClickListener);
        this.mQueryTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        this.mQueryTextView.setOnKeyListener(this.mTextKeyListener);
        this.mQueryTextView.setOnFocusChangeListener((View$OnFocusChangeListener)new SearchView$4(this));
        this.setIconifiedByDefault(obtainStyledAttributes.getBoolean(R$styleable.SearchView_iconifiedByDefault, true));
        inputType = obtainStyledAttributes.getDimensionPixelSize(R$styleable.SearchView_android_maxWidth, -1);
        if (inputType != -1) {
            this.setMaxWidth(inputType);
        }
        final CharSequence text = obtainStyledAttributes.getText(R$styleable.SearchView_queryHint);
        if (!TextUtils.isEmpty(text)) {
            this.setQueryHint(text);
        }
        inputType = obtainStyledAttributes.getInt(R$styleable.SearchView_android_imeOptions, -1);
        if (inputType != -1) {
            this.setImeOptions(inputType);
        }
        inputType = obtainStyledAttributes.getInt(R$styleable.SearchView_android_inputType, -1);
        if (inputType != -1) {
            this.setInputType(inputType);
        }
        this.setFocusable(obtainStyledAttributes.getBoolean(R$styleable.SearchView_android_focusable, true));
        obtainStyledAttributes.recycle();
        (this.mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH")).addFlags(268435456);
        this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        (this.mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH")).addFlags(268435456);
        this.mDropDownAnchor = this.findViewById(this.mQueryTextView.getDropDownAnchor());
        if (this.mDropDownAnchor != null) {
            if (Build$VERSION.SDK_INT >= 11) {
                this.addOnLayoutChangeListenerToDropDownAnchorSDK11();
            }
            else {
                this.addOnLayoutChangeListenerToDropDownAnchorBase();
            }
        }
        this.updateViewsVisibility(this.mIconifiedByDefault);
        this.updateQueryHint();
    }
    
    private void addOnLayoutChangeListenerToDropDownAnchorBase() {
        this.mDropDownAnchor.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new SearchView$6(this));
    }
    
    @TargetApi(11)
    private void addOnLayoutChangeListenerToDropDownAnchorSDK11() {
        this.mDropDownAnchor.addOnLayoutChangeListener((View$OnLayoutChangeListener)new SearchView$5(this));
    }
    
    private void adjustDropDownSizeAndPosition() {
        if (this.mDropDownAnchor.getWidth() > 1) {
            final Resources resources = this.getContext().getResources();
            final int paddingLeft = this.mSearchPlate.getPaddingLeft();
            final Rect rect = new Rect();
            final boolean layoutRtl = ViewUtils.isLayoutRtl((View)this);
            int n;
            if (this.mIconifiedByDefault) {
                n = resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width);
            }
            else {
                n = 0;
            }
            this.mQueryTextView.getDropDownBackground().getPadding(rect);
            int dropDownHorizontalOffset;
            if (layoutRtl) {
                dropDownHorizontalOffset = -rect.left;
            }
            else {
                dropDownHorizontalOffset = paddingLeft - (rect.left + n);
            }
            this.mQueryTextView.setDropDownHorizontalOffset(dropDownHorizontalOffset);
            this.mQueryTextView.setDropDownWidth(n + (this.mDropDownAnchor.getWidth() + rect.left + rect.right) - paddingLeft);
        }
    }
    
    private Intent createIntent(final String s, final Uri data, final String s2, final String s3, final int n, final String s4) {
        final Intent intent = new Intent(s);
        intent.addFlags(268435456);
        if (data != null) {
            intent.setData(data);
        }
        intent.putExtra("user_query", this.mUserQuery);
        if (s3 != null) {
            intent.putExtra("query", s3);
        }
        if (s2 != null) {
            intent.putExtra("intent_extra_data_key", s2);
        }
        if (this.mAppSearchData != null) {
            intent.putExtra("app_data", this.mAppSearchData);
        }
        if (n != 0) {
            intent.putExtra("action_key", n);
            intent.putExtra("action_msg", s4);
        }
        if (SearchView.IS_AT_LEAST_FROYO) {
            intent.setComponent(this.mSearchable.getSearchActivity());
        }
        return intent;
    }
    
    private Intent createIntentFromSuggestion(final Cursor cursor, int position, final String ex) {
        String s2 = null;
        String s;
        String s3;
        String columnString;
        String string = null;
        String s4;
        Uri parse;
        String s5;
        Block_9_Outer:Label_0140_Outer:Label_0169_Outer:
        while (true) {
            while (true) {
                while (true) {
                    Label_0245: {
                        while (true) {
                            Label_0228: {
                                try {
                                    s = (s2 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_action"));
                                    if (s != null) {
                                        break Label_0228;
                                    }
                                    s2 = s;
                                    if (Build$VERSION.SDK_INT >= 8) {
                                        s2 = this.mSearchable.getSuggestIntentAction();
                                    }
                                    break Label_0228;
                                    // iftrue(Label_0079:, !SearchView.IS_AT_LEAST_FROYO || s3 = s5 != null)
                                    while (true) {
                                    Label_0079:
                                        while (true) {
                                            s3 = this.mSearchable.getSuggestIntentData();
                                            break Label_0079;
                                            string = s3 + "/" + Uri.encode(columnString);
                                            break Label_0245;
                                            return this.createIntent(s4, parse, SuggestionsAdapter.getColumnString(cursor, "suggest_intent_extra_data"), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_query"), position, (String)ex);
                                            parse = Uri.parse(string);
                                            return this.createIntent(s4, parse, SuggestionsAdapter.getColumnString(cursor, "suggest_intent_extra_data"), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_query"), position, (String)ex);
                                            s5 = (s3 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data"));
                                            continue Block_9_Outer;
                                        }
                                        columnString = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data_id");
                                        string = s3;
                                        continue Label_0140_Outer;
                                    }
                                }
                                // iftrue(Label_0245:, string = s3 == null)
                                // iftrue(Label_0245:, columnString == null)
                                catch (RuntimeException ex) {
                                    try {
                                        position = cursor.getPosition();
                                        Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", (Throwable)ex);
                                        return null;
                                    }
                                    catch (RuntimeException ex2) {
                                        position = -1;
                                    }
                                }
                            }
                            if ((s4 = s2) == null) {
                                s4 = "android.intent.action.SEARCH";
                                continue;
                            }
                            continue;
                        }
                    }
                    if (string == null) {
                        parse = null;
                        continue Label_0169_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @TargetApi(8)
    private Intent createVoiceAppSearchIntent(final Intent intent, final SearchableInfo searchableInfo) {
        final String s = null;
        final ComponentName searchActivity = searchableInfo.getSearchActivity();
        final Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        final PendingIntent activity = PendingIntent.getActivity(this.getContext(), 0, intent2, 1073741824);
        final Bundle bundle = new Bundle();
        if (this.mAppSearchData != null) {
            bundle.putParcelable("app_data", (Parcelable)this.mAppSearchData);
        }
        final Intent intent3 = new Intent(intent);
        String string = "free_form";
        String string2;
        String string3;
        int voiceMaxResults;
        if (Build$VERSION.SDK_INT >= 8) {
            final Resources resources = this.getResources();
            if (searchableInfo.getVoiceLanguageModeId() != 0) {
                string = resources.getString(searchableInfo.getVoiceLanguageModeId());
            }
            if (searchableInfo.getVoicePromptTextId() != 0) {
                string2 = resources.getString(searchableInfo.getVoicePromptTextId());
            }
            else {
                string2 = null;
            }
            if (searchableInfo.getVoiceLanguageId() != 0) {
                string3 = resources.getString(searchableInfo.getVoiceLanguageId());
            }
            else {
                string3 = null;
            }
            if (searchableInfo.getVoiceMaxResults() != 0) {
                voiceMaxResults = searchableInfo.getVoiceMaxResults();
            }
            else {
                voiceMaxResults = 1;
            }
        }
        else {
            string3 = null;
            string2 = null;
            string = "free_form";
            voiceMaxResults = 1;
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        String flattenToShortString;
        if (searchActivity == null) {
            flattenToShortString = s;
        }
        else {
            flattenToShortString = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", flattenToShortString);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", (Parcelable)activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }
    
    @TargetApi(8)
    private Intent createVoiceWebSearchIntent(final Intent intent, final SearchableInfo searchableInfo) {
        final Intent intent2 = new Intent(intent);
        final ComponentName searchActivity = searchableInfo.getSearchActivity();
        String flattenToShortString;
        if (searchActivity == null) {
            flattenToShortString = null;
        }
        else {
            flattenToShortString = searchActivity.flattenToShortString();
        }
        intent2.putExtra("calling_package", flattenToShortString);
        return intent2;
    }
    
    private void dismissSuggestions() {
        this.mQueryTextView.dismissDropDown();
    }
    
    private void forceSuggestionQuery() {
        SearchView.HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mQueryTextView);
        SearchView.HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mQueryTextView);
    }
    
    private CharSequence getDecoratedHint(final CharSequence charSequence) {
        if (!this.mIconifiedByDefault) {
            return charSequence;
        }
        final Drawable drawable = this.mTintManager.getDrawable(this.mSearchIconResId);
        final int n = (int)(this.mQueryTextView.getTextSize() * 1.25);
        drawable.setBounds(0, 0, n, n);
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)"   ");
        spannableStringBuilder.append(charSequence);
        spannableStringBuilder.setSpan((Object)new ImageSpan(drawable), 1, 2, 33);
        return (CharSequence)spannableStringBuilder;
    }
    
    private int getPreferredWidth() {
        return this.getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }
    
    @TargetApi(8)
    private boolean hasVoiceSearch() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.mSearchable != null) {
            b2 = b;
            if (this.mSearchable.getVoiceSearchEnabled()) {
                Intent intent = null;
                if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
                    intent = this.mVoiceWebSearchIntent;
                }
                else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
                    intent = this.mVoiceAppSearchIntent;
                }
                b2 = b;
                if (intent != null) {
                    b2 = b;
                    if (this.getContext().getPackageManager().resolveActivity(intent, 65536) != null) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    static boolean isLandscapeMode(final Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
    
    private boolean isSubmitAreaEnabled() {
        return (this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !this.isIconified();
    }
    
    private void launchIntent(final Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            this.getContext().startActivity(intent);
        }
        catch (RuntimeException ex) {
            Log.e("SearchView", "Failed launch activity: " + intent, (Throwable)ex);
        }
    }
    
    private void launchQuerySearch(final int n, final String s, final String s2) {
        this.getContext().startActivity(this.createIntent("android.intent.action.SEARCH", null, null, s2, n, s));
    }
    
    private boolean launchSuggestion(final int n, final int n2, final String s) {
        final Cursor cursor = this.mSuggestionsAdapter.getCursor();
        if (cursor != null && cursor.moveToPosition(n)) {
            this.launchIntent(this.createIntentFromSuggestion(cursor, n2, s));
            return true;
        }
        return false;
    }
    
    private void onCloseClicked() {
        if (TextUtils.isEmpty((CharSequence)this.mQueryTextView.getText())) {
            if (this.mIconifiedByDefault && (this.mOnCloseListener == null || !this.mOnCloseListener.onClose())) {
                this.clearFocus();
                this.updateViewsVisibility(true);
            }
            return;
        }
        this.mQueryTextView.setText((CharSequence)"");
        this.mQueryTextView.requestFocus();
        this.setImeVisibility(true);
    }
    
    private boolean onItemClicked(final int n, final int n2, final String s) {
        boolean b = false;
        if (this.mOnSuggestionListener == null || !this.mOnSuggestionListener.onSuggestionClick(n)) {
            this.launchSuggestion(n, 0, null);
            this.setImeVisibility(false);
            this.dismissSuggestions();
            b = true;
        }
        return b;
    }
    
    private boolean onItemSelected(final int n) {
        if (this.mOnSuggestionListener == null || !this.mOnSuggestionListener.onSuggestionSelect(n)) {
            this.rewriteQueryFromSuggestion(n);
            return true;
        }
        return false;
    }
    
    private void onSearchClicked() {
        this.updateViewsVisibility(false);
        this.mQueryTextView.requestFocus();
        this.setImeVisibility(true);
        if (this.mOnSearchClickListener != null) {
            this.mOnSearchClickListener.onClick((View)this);
        }
    }
    
    private void onSubmitQuery() {
        final Editable text = this.mQueryTextView.getText();
        if (text != null && TextUtils.getTrimmedLength((CharSequence)text) > 0 && (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(((CharSequence)text).toString()))) {
            if (this.mSearchable != null) {
                this.launchQuerySearch(0, null, ((CharSequence)text).toString());
            }
            this.setImeVisibility(false);
            this.dismissSuggestions();
        }
    }
    
    private boolean onSuggestionsKey(final View view, int length, final KeyEvent keyEvent) {
        if (this.mSearchable != null && this.mSuggestionsAdapter != null && keyEvent.getAction() == 0 && KeyEventCompat.hasNoModifiers(keyEvent)) {
            if (length == 66 || length == 84 || length == 61) {
                return this.onItemClicked(this.mQueryTextView.getListSelection(), 0, null);
            }
            if (length == 21 || length == 22) {
                if (length == 21) {
                    length = 0;
                }
                else {
                    length = this.mQueryTextView.length();
                }
                this.mQueryTextView.setSelection(length);
                this.mQueryTextView.setListSelection(0);
                this.mQueryTextView.clearListSelection();
                SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mQueryTextView, true);
                return true;
            }
            if (length == 19 && this.mQueryTextView.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }
    
    private void onTextChanged(final CharSequence charSequence) {
        final boolean b = true;
        final Editable text = this.mQueryTextView.getText();
        this.mUserQuery = (CharSequence)text;
        final boolean b2 = !TextUtils.isEmpty((CharSequence)text);
        this.updateSubmitButton(b2);
        this.updateVoiceButton(!b2 && b);
        this.updateCloseButton();
        this.updateSubmitArea();
        if (this.mOnQueryChangeListener != null && !TextUtils.equals(charSequence, this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }
    
    @TargetApi(8)
    private void onVoiceClicked() {
        if (this.mSearchable != null) {
            final SearchableInfo mSearchable = this.mSearchable;
            try {
                if (mSearchable.getVoiceSearchLaunchWebSearch()) {
                    this.getContext().startActivity(this.createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, mSearchable));
                    return;
                }
            }
            catch (ActivityNotFoundException ex) {
                Log.w("SearchView", "Could not find voice search activity");
                return;
            }
            if (mSearchable.getVoiceSearchLaunchRecognizer()) {
                this.getContext().startActivity(this.createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, mSearchable));
            }
        }
    }
    
    private void postUpdateFocusedState() {
        this.post(this.mUpdateDrawableStateRunnable);
    }
    
    private void rewriteQueryFromSuggestion(final int n) {
        final Editable text = this.mQueryTextView.getText();
        final Cursor cursor = this.mSuggestionsAdapter.getCursor();
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(n)) {
            this.setQuery((CharSequence)text);
            return;
        }
        final CharSequence convertToString = this.mSuggestionsAdapter.convertToString(cursor);
        if (convertToString != null) {
            this.setQuery(convertToString);
            return;
        }
        this.setQuery((CharSequence)text);
    }
    
    private void setImeVisibility(final boolean b) {
        if (b) {
            this.post(this.mShowImeRunnable);
        }
        else {
            this.removeCallbacks(this.mShowImeRunnable);
            final InputMethodManager inputMethodManager = (InputMethodManager)this.getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.getWindowToken(), 0);
            }
        }
    }
    
    private void setQuery(final CharSequence text) {
        this.mQueryTextView.setText(text);
        final SearchView$SearchAutoComplete mQueryTextView = this.mQueryTextView;
        int length;
        if (TextUtils.isEmpty(text)) {
            length = 0;
        }
        else {
            length = text.length();
        }
        mQueryTextView.setSelection(length);
    }
    
    private void updateCloseButton() {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (!TextUtils.isEmpty((CharSequence)this.mQueryTextView.getText())) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4 = b;
        if (!b3) {
            b4 = (this.mIconifiedByDefault && !this.mExpandedInActionView && b);
        }
        final ImageView mCloseButton = this.mCloseButton;
        int visibility;
        if (b4) {
            visibility = (b2 ? 1 : 0);
        }
        else {
            visibility = 8;
        }
        mCloseButton.setVisibility(visibility);
        final Drawable drawable = this.mCloseButton.getDrawable();
        int[] state;
        if (b3) {
            state = SearchView.ENABLED_STATE_SET;
        }
        else {
            state = SearchView.EMPTY_STATE_SET;
        }
        drawable.setState(state);
    }
    
    private void updateFocusedState() {
        final boolean hasFocus = this.mQueryTextView.hasFocus();
        final Drawable background = this.mSearchPlate.getBackground();
        int[] state;
        if (hasFocus) {
            state = SearchView.ENABLED_FOCUSED_STATE_SET;
        }
        else {
            state = SearchView.EMPTY_STATE_SET;
        }
        background.setState(state);
        final Drawable background2 = this.mSubmitArea.getBackground();
        int[] state2;
        if (hasFocus) {
            state2 = SearchView.ENABLED_FOCUSED_STATE_SET;
        }
        else {
            state2 = SearchView.EMPTY_STATE_SET;
        }
        background2.setState(state2);
        this.invalidate();
    }
    
    private void updateQueryHint() {
        if (this.mQueryHint != null) {
            this.mQueryTextView.setHint(this.getDecoratedHint(this.mQueryHint));
        }
        else {
            if (!SearchView.IS_AT_LEAST_FROYO || this.mSearchable == null) {
                this.mQueryTextView.setHint(this.getDecoratedHint(""));
                return;
            }
            CharSequence string = null;
            final int hintId = this.mSearchable.getHintId();
            if (hintId != 0) {
                string = this.getContext().getString(hintId);
            }
            if (string != null) {
                this.mQueryTextView.setHint(this.getDecoratedHint(string));
            }
        }
    }
    
    @TargetApi(8)
    private void updateSearchAutoComplete() {
        final boolean b = true;
        this.mQueryTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mQueryTextView.setImeOptions(this.mSearchable.getImeOptions());
        int inputType;
        final int n = inputType = this.mSearchable.getInputType();
        if ((n & 0xF) == 0x1) {
            inputType = (n & 0xFFFEFFFF);
            if (this.mSearchable.getSuggestAuthority() != null) {
                inputType = (inputType | 0x10000 | 0x80000);
            }
        }
        this.mQueryTextView.setInputType(inputType);
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.changeCursor(null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            this.mSuggestionsAdapter = new SuggestionsAdapter(this.getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mQueryTextView.setAdapter((ListAdapter)this.mSuggestionsAdapter);
            final SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter)this.mSuggestionsAdapter;
            int queryRefinement = b ? 1 : 0;
            if (this.mQueryRefinement) {
                queryRefinement = 2;
            }
            suggestionsAdapter.setQueryRefinement(queryRefinement);
        }
    }
    
    private void updateSubmitArea() {
        int visibility = 8;
        Label_0036: {
            if (this.isSubmitAreaEnabled()) {
                if (this.mSubmitButton.getVisibility() != 0) {
                    visibility = visibility;
                    if (this.mVoiceButton.getVisibility() != 0) {
                        break Label_0036;
                    }
                }
                visibility = 0;
            }
        }
        this.mSubmitArea.setVisibility(visibility);
    }
    
    private void updateSubmitButton(final boolean b) {
        int visibility;
        final int n = visibility = 8;
        Label_0045: {
            if (this.mSubmitButtonEnabled) {
                visibility = n;
                if (this.isSubmitAreaEnabled()) {
                    visibility = n;
                    if (this.hasFocus()) {
                        if (!b) {
                            visibility = n;
                            if (this.mVoiceButtonEnabled) {
                                break Label_0045;
                            }
                        }
                        visibility = 0;
                    }
                }
            }
        }
        this.mSubmitButton.setVisibility(visibility);
    }
    
    private void updateViewsVisibility(final boolean mIconified) {
        final boolean b = true;
        final int n = 8;
        this.mIconified = mIconified;
        int visibility;
        if (mIconified) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        final boolean b2 = !TextUtils.isEmpty((CharSequence)this.mQueryTextView.getText());
        this.mSearchButton.setVisibility(visibility);
        this.updateSubmitButton(b2);
        final View mSearchEditFrame = this.mSearchEditFrame;
        int visibility2;
        if (mIconified) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        mSearchEditFrame.setVisibility(visibility2);
        final ImageView mSearchHintIcon = this.mSearchHintIcon;
        int visibility3;
        if (this.mIconifiedByDefault) {
            visibility3 = n;
        }
        else {
            visibility3 = 0;
        }
        mSearchHintIcon.setVisibility(visibility3);
        this.updateCloseButton();
        this.updateVoiceButton(!b2 && b);
        this.updateSubmitArea();
    }
    
    private void updateVoiceButton(final boolean b) {
        int visibility;
        if (this.mVoiceButtonEnabled && !this.isIconified() && b) {
            visibility = 0;
            this.mSubmitButton.setVisibility(8);
        }
        else {
            visibility = 8;
        }
        this.mVoiceButton.setVisibility(visibility);
    }
    
    public void clearFocus() {
        this.mClearingFocus = true;
        this.setImeVisibility(false);
        super.clearFocus();
        this.mQueryTextView.clearFocus();
        this.mClearingFocus = false;
    }
    
    public int getImeOptions() {
        return this.mQueryTextView.getImeOptions();
    }
    
    public int getInputType() {
        return this.mQueryTextView.getInputType();
    }
    
    public int getMaxWidth() {
        return this.mMaxWidth;
    }
    
    public CharSequence getQuery() {
        return (CharSequence)this.mQueryTextView.getText();
    }
    
    public CharSequence getQueryHint() {
        final CharSequence charSequence = null;
        CharSequence mQueryHint;
        if (this.mQueryHint != null) {
            mQueryHint = this.mQueryHint;
        }
        else {
            mQueryHint = charSequence;
            if (SearchView.IS_AT_LEAST_FROYO) {
                mQueryHint = charSequence;
                if (this.mSearchable != null) {
                    final int hintId = this.mSearchable.getHintId();
                    mQueryHint = charSequence;
                    if (hintId != 0) {
                        return this.getContext().getString(hintId);
                    }
                }
            }
        }
        return mQueryHint;
    }
    
    int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }
    
    int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }
    
    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }
    
    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }
    
    public boolean isIconified() {
        return this.mIconified;
    }
    
    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }
    
    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }
    
    @Override
    public void onActionViewCollapsed() {
        this.setQuery("", false);
        this.clearFocus();
        this.updateViewsVisibility(true);
        this.mQueryTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }
    
    @Override
    public void onActionViewExpanded() {
        if (this.mExpandedInActionView) {
            return;
        }
        this.mExpandedInActionView = true;
        this.mCollapsedImeOptions = this.mQueryTextView.getImeOptions();
        this.mQueryTextView.setImeOptions(this.mCollapsedImeOptions | 0x2000000);
        this.mQueryTextView.setText((CharSequence)"");
        this.setIconified(false);
    }
    
    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mUpdateDrawableStateRunnable);
        this.post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }
    
    @Override
    protected void onMeasure(int n, final int n2) {
        if (this.isIconified()) {
            super.onMeasure(n, n2);
            return;
        }
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        switch (mode) {
            default: {
                n = size;
                break;
            }
            case Integer.MIN_VALUE: {
                if (this.mMaxWidth > 0) {
                    n = Math.min(this.mMaxWidth, size);
                    break;
                }
                n = Math.min(this.getPreferredWidth(), size);
                break;
            }
            case 1073741824: {
                n = size;
                if (this.mMaxWidth > 0) {
                    n = Math.min(this.mMaxWidth, size);
                    break;
                }
                break;
            }
            case 0: {
                if (this.mMaxWidth > 0) {
                    n = this.mMaxWidth;
                    break;
                }
                n = this.getPreferredWidth();
                break;
            }
        }
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), n2);
    }
    
    void onQueryRefine(final CharSequence query) {
        this.setQuery(query);
    }
    
    void onTextFocusChanged() {
        this.updateViewsVisibility(this.isIconified());
        this.postUpdateFocusedState();
        if (this.mQueryTextView.hasFocus()) {
            this.forceSuggestionQuery();
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        this.postUpdateFocusedState();
    }
    
    public boolean requestFocus(final int n, final Rect rect) {
        if (this.mClearingFocus || !this.isFocusable()) {
            return false;
        }
        if (!this.isIconified()) {
            final boolean requestFocus = this.mQueryTextView.requestFocus(n, rect);
            if (requestFocus) {
                this.updateViewsVisibility(false);
            }
            return requestFocus;
        }
        return super.requestFocus(n, rect);
    }
    
    public void setAppSearchData(final Bundle mAppSearchData) {
        this.mAppSearchData = mAppSearchData;
    }
    
    public void setIconified(final boolean b) {
        if (b) {
            this.onCloseClicked();
            return;
        }
        this.onSearchClicked();
    }
    
    public void setIconifiedByDefault(final boolean mIconifiedByDefault) {
        if (this.mIconifiedByDefault == mIconifiedByDefault) {
            return;
        }
        this.updateViewsVisibility(this.mIconifiedByDefault = mIconifiedByDefault);
        this.updateQueryHint();
    }
    
    public void setImeOptions(final int imeOptions) {
        this.mQueryTextView.setImeOptions(imeOptions);
    }
    
    public void setInputType(final int inputType) {
        this.mQueryTextView.setInputType(inputType);
    }
    
    public void setMaxWidth(final int mMaxWidth) {
        this.mMaxWidth = mMaxWidth;
        this.requestLayout();
    }
    
    public void setOnCloseListener(final SearchView$OnCloseListener mOnCloseListener) {
        this.mOnCloseListener = mOnCloseListener;
    }
    
    public void setOnQueryTextFocusChangeListener(final View$OnFocusChangeListener mOnQueryTextFocusChangeListener) {
        this.mOnQueryTextFocusChangeListener = mOnQueryTextFocusChangeListener;
    }
    
    public void setOnQueryTextListener(final SearchView$OnQueryTextListener mOnQueryChangeListener) {
        this.mOnQueryChangeListener = mOnQueryChangeListener;
    }
    
    public void setOnSearchClickListener(final View$OnClickListener mOnSearchClickListener) {
        this.mOnSearchClickListener = mOnSearchClickListener;
    }
    
    public void setOnSuggestionListener(final SearchView$OnSuggestionListener mOnSuggestionListener) {
        this.mOnSuggestionListener = mOnSuggestionListener;
    }
    
    public void setQuery(final CharSequence charSequence, final boolean b) {
        this.mQueryTextView.setText(charSequence);
        if (charSequence != null) {
            this.mQueryTextView.setSelection(this.mQueryTextView.length());
            this.mUserQuery = charSequence;
        }
        if (b && !TextUtils.isEmpty(charSequence)) {
            this.onSubmitQuery();
        }
    }
    
    public void setQueryHint(final CharSequence mQueryHint) {
        this.mQueryHint = mQueryHint;
        this.updateQueryHint();
    }
    
    public void setQueryRefinementEnabled(final boolean mQueryRefinement) {
        this.mQueryRefinement = mQueryRefinement;
        if (this.mSuggestionsAdapter instanceof SuggestionsAdapter) {
            final SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter)this.mSuggestionsAdapter;
            int queryRefinement;
            if (mQueryRefinement) {
                queryRefinement = 2;
            }
            else {
                queryRefinement = 1;
            }
            suggestionsAdapter.setQueryRefinement(queryRefinement);
        }
    }
    
    public void setSearchableInfo(final SearchableInfo mSearchable) {
        this.mSearchable = mSearchable;
        if (this.mSearchable != null) {
            if (SearchView.IS_AT_LEAST_FROYO) {
                this.updateSearchAutoComplete();
            }
            this.updateQueryHint();
        }
        this.mVoiceButtonEnabled = (SearchView.IS_AT_LEAST_FROYO && this.hasVoiceSearch());
        if (this.mVoiceButtonEnabled) {
            this.mQueryTextView.setPrivateImeOptions("nm");
        }
        this.updateViewsVisibility(this.isIconified());
    }
    
    public void setSubmitButtonEnabled(final boolean mSubmitButtonEnabled) {
        this.mSubmitButtonEnabled = mSubmitButtonEnabled;
        this.updateViewsVisibility(this.isIconified());
    }
    
    public void setSuggestionsAdapter(final CursorAdapter mSuggestionsAdapter) {
        this.mSuggestionsAdapter = mSuggestionsAdapter;
        this.mQueryTextView.setAdapter((ListAdapter)this.mSuggestionsAdapter);
    }
}
