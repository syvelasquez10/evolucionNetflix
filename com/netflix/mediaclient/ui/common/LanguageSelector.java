// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.app.Activity;
import android.view.ViewGroup$LayoutParams;
import android.app.Dialog;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListView;
import com.netflix.mediaclient.media.Language;

public abstract class LanguageSelector
{
    protected static final String TAG = "nf_language_selector";
    protected Language language;
    protected ListView mAudiosListView;
    protected final LanguageSelector$LanguageSelectorCallback mCallback;
    protected final NetflixActivity mController;
    protected ListView mSubtitlesListView;
    
    LanguageSelector(final NetflixActivity mController, final LanguageSelector$LanguageSelectorCallback mCallback) {
        this.mController = mController;
        this.mCallback = mCallback;
    }
    
    public static LanguageSelector createInstance(final NetflixActivity netflixActivity, final boolean b, final LanguageSelector$LanguageSelectorCallback languageSelector$LanguageSelectorCallback) {
        if (BrowseExperience.isKubrick()) {
            return new LanguageSelectorKubrick(netflixActivity, languageSelector$LanguageSelectorCallback);
        }
        if (b) {
            return new LanguageSelectorTablet(netflixActivity, languageSelector$LanguageSelectorCallback);
        }
        return new LanguageSelectorPhone(netflixActivity, languageSelector$LanguageSelectorCallback);
    }
    
    protected abstract int calculateListViewHeight();
    
    protected void createAndShowDialog(final View view) {
        final LanguageSelector$LanguageAlertDialog languageSelector$LanguageAlertDialog = new LanguageSelector$LanguageAlertDialog(this, (Context)this.mController, null);
        languageSelector$LanguageAlertDialog.setButton(-1, (CharSequence)this.mController.getString(2131165588), (DialogInterface$OnClickListener)new LanguageSelector$3(this, this.mCallback.wasPlaying(), languageSelector$LanguageAlertDialog));
        languageSelector$LanguageAlertDialog.setView(view);
        languageSelector$LanguageAlertDialog.setCancelable(true);
        languageSelector$LanguageAlertDialog.setOnCancelListener((DialogInterface$OnCancelListener)new LanguageSelector$4(this));
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
        this.mCallback.updateDialog((Dialog)languageSelector$LanguageAlertDialog);
        if (languageSelector$LanguageAlertDialog != null) {
            this.mController.displayDialog((Dialog)languageSelector$LanguageAlertDialog);
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
        (this.mAudiosListView = (ListView)view.findViewById(2131624302)).setChoiceMode(1);
        final LanguageSelector$AudioAdapter adapter = new LanguageSelector$AudioAdapter(language, this.mController);
        this.mAudiosListView.setAdapter((ListAdapter)adapter);
        (this.mSubtitlesListView = (ListView)view.findViewById(2131624304)).setChoiceMode(1);
        final LanguageSelector$SubtitleAdapter adapter2 = new LanguageSelector$SubtitleAdapter(language, this.mController);
        this.mSubtitlesListView.setAdapter((ListAdapter)adapter2);
        this.mAudiosListView.setOnItemClickListener((AdapterView$OnItemClickListener)new LanguageSelector$1(this, adapter, language, adapter2));
        this.mSubtitlesListView.setOnItemClickListener((AdapterView$OnItemClickListener)new LanguageSelector$2(this, adapter2, language));
    }
}
