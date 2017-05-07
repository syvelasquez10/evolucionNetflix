// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.widget.TextView;
import android.widget.RadioButton;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import android.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.Button;
import android.view.ViewGroup$LayoutParams;
import android.app.Dialog;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListView;
import com.netflix.mediaclient.media.Language;

public abstract class LanguageSelector
{
    protected static final String TAG = "nf_language_selector";
    private Language language;
    protected ListView mAudiosListView;
    protected final LanguageSelectorCallback mCallback;
    protected final NetflixActivity mController;
    protected final int mRowColor;
    protected final int mSelectedRowColor;
    protected ListView mSubtitlesListView;
    
    LanguageSelector(final NetflixActivity mController, final LanguageSelectorCallback mCallback) {
        this.mController = mController;
        this.mCallback = mCallback;
        this.mSelectedRowColor = this.mController.getResources().getColor(2131296404);
        this.mRowColor = this.mController.getResources().getColor(2131296403);
    }
    
    public static LanguageSelector createInstance(final NetflixActivity netflixActivity, final boolean b, final LanguageSelectorCallback languageSelectorCallback) {
        if (b) {
            return new LanguageSelectorTablet(netflixActivity, languageSelectorCallback);
        }
        return new LanguageSelectorPhone(netflixActivity, languageSelectorCallback);
    }
    
    private boolean shouldForceFirst(final Language language, final int n, final Subtitle selectedSubtitle) {
        if (n != 0) {
            return false;
        }
        final Subtitle selectedSubtitle2 = language.getSelectedSubtitle();
        final AudioSource selectedAudio = language.getSelectedAudio();
        if (selectedAudio != null && selectedAudio.isAllowedSubtitle(selectedSubtitle2)) {
            Log.d("nf_language_selector", "Selected subtitle is allowed");
            return false;
        }
        if (Log.isLoggable("nf_language_selector", 3)) {
            Log.d("nf_language_selector", "Selected subtitle is not allowed, set to firsyt subtitle " + selectedSubtitle);
        }
        language.setSelectedSubtitle(selectedSubtitle);
        return true;
    }
    
    protected abstract int calculateListViewHeight();
    
    protected void createAndShowDialog(final View view) {
        final LanguageAlertDialog languageAlertDialog = new LanguageAlertDialog((Context)this.mController);
        languageAlertDialog.setView(view);
        languageAlertDialog.setCancelable(true);
        languageAlertDialog.setButton(-1, (CharSequence)this.mController.getString(2131492983), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            final /* synthetic */ boolean val$wasPlaying = LanguageSelector.this.mCallback.wasPlaying();
            
            public void onClick(final DialogInterface dialogInterface, final int n) {
                Log.d("nf_language_selector", "Languages::apply");
                LanguageSelector.this.mCallback.languageChanged(LanguageSelector.this.language, this.val$wasPlaying);
            }
        });
        languageAlertDialog.setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                Log.d("nf_language_selector", "Languages::cancel");
                LanguageSelector.this.mCallback.userCanceled();
            }
        });
        final int calculateListViewHeight = this.calculateListViewHeight();
        if (calculateListViewHeight >= 0) {
            Log.d("nf_language_selector", "Sets view height.");
            final ViewGroup$LayoutParams layoutParams = this.mAudiosListView.getLayoutParams();
            layoutParams.height = calculateListViewHeight;
            layoutParams.width = -2;
            this.mAudiosListView.setLayoutParams(layoutParams);
            final ViewGroup$LayoutParams layoutParams2 = this.mSubtitlesListView.getLayoutParams();
            layoutParams2.height = calculateListViewHeight;
            layoutParams2.width = -2;
            this.mSubtitlesListView.setLayoutParams(layoutParams2);
        }
        else {
            Log.d("nf_language_selector", "Do NOT set view height.");
        }
        Log.d("nf_language_selector", "Languages::open dialog");
        this.mCallback.updateDialog((Dialog)languageAlertDialog);
        if (languageAlertDialog != null) {
            this.mController.displayDialog((Dialog)languageAlertDialog);
            final Button button = languageAlertDialog.getButton(-1);
            if (button == null) {
                Log.e("nf_language_selector", "Button NOT found!");
                return;
            }
            Log.d("nf_language_selector", "Button found!");
            button.setBackgroundColor(this.mController.getResources().getColor(2131296403));
            button.setTextColor(this.mController.getResources().getColor(2131296359));
            button.setTextAppearance((Context)this.mController, 2131558700);
        }
    }
    
    public void display(final Language p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_1        
        //     3: ifnonnull       17
        //     6: ldc             "nf_language_selector"
        //     8: ldc             "Language data is null!"
        //    10: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    13: pop            
        //    14: aload_0        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: aload_1        
        //    19: invokevirtual   com/netflix/mediaclient/media/Language.toJsonString:()Ljava/lang/String;
        //    22: invokestatic    com/netflix/mediaclient/media/Language.restoreLanguage:(Ljava/lang/String;)Lcom/netflix/mediaclient/media/Language;
        //    25: putfield        com/netflix/mediaclient/ui/common/LanguageSelector.language:Lcom/netflix/mediaclient/media/Language;
        //    28: aload_0        
        //    29: getfield        com/netflix/mediaclient/ui/common/LanguageSelector.mController:Lcom/netflix/mediaclient/android/activity/NetflixActivity;
        //    32: invokestatic    android/view/LayoutInflater.from:(Landroid/content/Context;)Landroid/view/LayoutInflater;
        //    35: aload_0        
        //    36: invokevirtual   com/netflix/mediaclient/ui/common/LanguageSelector.getDialogLayoutId:()I
        //    39: aconst_null    
        //    40: invokevirtual   android/view/LayoutInflater.inflate:(ILandroid/view/ViewGroup;)Landroid/view/View;
        //    43: astore_1       
        //    44: aload_0        
        //    45: getfield        com/netflix/mediaclient/ui/common/LanguageSelector.language:Lcom/netflix/mediaclient/media/Language;
        //    48: aload_0        
        //    49: getfield        com/netflix/mediaclient/ui/common/LanguageSelector.language:Lcom/netflix/mediaclient/media/Language;
        //    52: invokevirtual   com/netflix/mediaclient/media/Language.getCurrentAudioSource:()Lcom/netflix/mediaclient/media/AudioSource;
        //    55: invokevirtual   com/netflix/mediaclient/media/Language.setSelectedAudio:(Lcom/netflix/mediaclient/media/AudioSource;)Lcom/netflix/mediaclient/media/AudioSource;
        //    58: pop            
        //    59: aload_0        
        //    60: getfield        com/netflix/mediaclient/ui/common/LanguageSelector.language:Lcom/netflix/mediaclient/media/Language;
        //    63: aload_0        
        //    64: getfield        com/netflix/mediaclient/ui/common/LanguageSelector.language:Lcom/netflix/mediaclient/media/Language;
        //    67: invokevirtual   com/netflix/mediaclient/media/Language.getCurrentSubtitle:()Lcom/netflix/mediaclient/media/Subtitle;
        //    70: invokevirtual   com/netflix/mediaclient/media/Language.setSelectedSubtitle:(Lcom/netflix/mediaclient/media/Subtitle;)Lcom/netflix/mediaclient/media/Subtitle;
        //    73: pop            
        //    74: aload_0        
        //    75: aload_1        
        //    76: aload_0        
        //    77: getfield        com/netflix/mediaclient/ui/common/LanguageSelector.language:Lcom/netflix/mediaclient/media/Language;
        //    80: invokevirtual   com/netflix/mediaclient/ui/common/LanguageSelector.init:(Landroid/view/View;Lcom/netflix/mediaclient/media/Language;)V
        //    83: aload_0        
        //    84: aload_1        
        //    85: invokevirtual   com/netflix/mediaclient/ui/common/LanguageSelector.createAndShowDialog:(Landroid/view/View;)V
        //    88: goto            14
        //    91: astore_1       
        //    92: aload_0        
        //    93: monitorexit    
        //    94: aload_1        
        //    95: athrow         
        //    96: astore_1       
        //    97: ldc             "nf_language_selector"
        //    99: aload_1        
        //   100: invokestatic    com/netflix/mediaclient/Log.handleException:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   103: goto            14
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  6      14     91     96     Any
        //  17     28     96     106    Lorg/json/JSONException;
        //  17     28     91     96     Any
        //  28     88     91     96     Any
        //  97     103    91     96     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected abstract int getDialogLayoutId();
    
    protected Language getLanguage() {
        return this.language;
    }
    
    protected void init(final View view, final Language language) {
        this.initLists(view, language);
    }
    
    protected void initLists(final View view, final Language language) {
        (this.mAudiosListView = (ListView)view.findViewById(2131165437)).setChoiceMode(1);
        final AudioAdapter adapter = new AudioAdapter(language);
        this.mAudiosListView.setAdapter((ListAdapter)adapter);
        (this.mSubtitlesListView = (ListView)view.findViewById(2131165438)).setChoiceMode(1);
        final SubtitleAdapter adapter2 = new SubtitleAdapter(language);
        this.mSubtitlesListView.setAdapter((ListAdapter)adapter2);
        this.mAudiosListView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                final AudioSource item = adapter.getItem(n);
                if (Log.isLoggable("nf_language_selector", 3)) {
                    Log.d("nf_language_selector", "Audio selected on position " + n + ", audio choosen: " + item);
                }
                if (language.getSelectedAudio() != item) {
                    Log.v("nf_language_selector", "Audio is changed, refresh both views");
                    language.setSelectedAudio(item);
                    adapter.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                    return;
                }
                Log.v("nf_language_selector", "Audio is not changed, do not refresh");
            }
        });
        this.mSubtitlesListView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                final Subtitle item = adapter2.getItem(n);
                if (Log.isLoggable("nf_language_selector", 3)) {
                    Log.d("nf_language_selector", "Subtitle selected on position " + n + ", data: " + item);
                }
                if (language.getSelectedSubtitle() != item) {
                    Log.v("nf_language_selector", "Subtitle is changed, refresh subtitle list view");
                    language.setSelectedSubtitle(item);
                    adapter2.notifyDataSetChanged();
                    return;
                }
                Log.v("nf_language_selector", "Subtitle is not changed, do not refresh");
            }
        });
    }
    
    public class AudioAdapter extends BaseAdapter
    {
        private final Language language;
        
        public AudioAdapter(final Language language) {
            this.language = language;
        }
        
        public int getCount() {
            return this.language.getAltAudios().length;
        }
        
        public AudioSource getItem(final int n) {
            return this.language.getAltAudios()[n];
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                Log.d("nf_language_selector", "Audio create row " + n);
                inflate = LanguageSelector.this.mController.getLayoutInflater().inflate(2130903113, viewGroup, false);
                inflate.setTag((Object)new RowHolder(inflate));
            }
            final RowHolder rowHolder = (RowHolder)inflate.getTag();
            final AudioSource item = this.getItem(n);
            final boolean equals = item.equals(this.language.getSelectedAudio());
            rowHolder.name.setText((CharSequence)item.getLanguageDescription());
            rowHolder.choice.setChecked(equals);
            if (equals) {
                Log.d("nf_language_selector", "Audio is selected " + item);
                inflate.setBackgroundColor(LanguageSelector.this.mSelectedRowColor);
                return inflate;
            }
            inflate.setBackgroundColor(LanguageSelector.this.mRowColor);
            return inflate;
        }
    }
    
    private static class LanguageAlertDialog extends AlertDialog implements MdxMiniPlayerDialog
    {
        private LanguageAlertDialog(final Context context) {
            super(context);
        }
        
        private LanguageAlertDialog(final Context context, final int n) {
            super(context, n);
        }
        
        private LanguageAlertDialog(final Context context, final boolean b, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
            super(context, b, dialogInterface$OnCancelListener);
        }
    }
    
    public interface LanguageSelectorCallback
    {
        void languageChanged(final Language p0, final boolean p1);
        
        void updateDialog(final Dialog p0);
        
        void userCanceled();
        
        boolean wasPlaying();
    }
    
    static class RowHolder
    {
        RadioButton choice;
        TextView name;
        
        RowHolder(final View view) {
            this.name = (TextView)view.findViewById(2131165439);
            this.choice = (RadioButton)view.findViewById(2131165440);
        }
    }
    
    public class SubtitleAdapter extends BaseAdapter
    {
        private final Language language;
        
        public SubtitleAdapter(final Language language) {
            this.language = language;
        }
        
        public int getCount() {
            return this.language.getUsedSubtitles().size();
        }
        
        public Subtitle getItem(final int n) {
            return this.language.getUsedSubtitles().get(n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            boolean equals = false;
            View inflate = view;
            if (view == null) {
                Log.d("nf_language_selector", "Subtitle create row " + n);
                inflate = LanguageSelector.this.mController.getLayoutInflater().inflate(2130903113, viewGroup, false);
                inflate.setTag((Object)new RowHolder(inflate));
            }
            final RowHolder rowHolder = (RowHolder)inflate.getTag();
            final Subtitle item = this.getItem(n);
            Subtitle subtitle = this.language.getSelectedSubtitle();
            if (LanguageSelector.this.shouldForceFirst(this.language, n, item)) {
                Log.d("nf_language_selector", "Previously selected subtitle is not allowed anymore, reset to first on list, reload seleted subtitle");
                subtitle = this.language.getSelectedSubtitle();
            }
            String text;
            if (item != null) {
                final StringBuilder sb = new StringBuilder(item.getLanguageDescription());
                if (item.isCC()) {
                    Log.d("nf_language_selector", "Add CC");
                    sb.append(' ');
                    sb.append(LanguageSelector.this.mController.getText(2131493138));
                }
                final String string = sb.toString();
                equals = item.equals(subtitle);
                text = string;
            }
            else {
                final String string2 = LanguageSelector.this.mController.getString(2131493131);
                if (subtitle == null) {
                    equals = true;
                }
                text = string2;
            }
            rowHolder.name.setText((CharSequence)text);
            rowHolder.choice.setChecked(equals);
            if (equals) {
                Log.d("nf_language_selector", "Subtitle is selected " + item);
                inflate.setBackgroundColor(LanguageSelector.this.mSelectedRowColor);
                return inflate;
            }
            inflate.setBackgroundColor(LanguageSelector.this.mRowColor);
            return inflate;
        }
    }
}
