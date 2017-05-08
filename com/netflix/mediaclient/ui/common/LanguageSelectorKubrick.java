// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class LanguageSelectorKubrick extends LanguageSelectorTablet
{
    public LanguageSelectorKubrick(final NetflixActivity netflixActivity, final LanguageSelector$LanguageSelectorCallback languageSelector$LanguageSelectorCallback) {
        super(netflixActivity, languageSelector$LanguageSelectorCallback);
    }
    
    @Override
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
        //    25: putfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.language:Lcom/netflix/mediaclient/media/Language;
        //    28: aload_0        
        //    29: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.language:Lcom/netflix/mediaclient/media/Language;
        //    32: aload_0        
        //    33: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.language:Lcom/netflix/mediaclient/media/Language;
        //    36: invokevirtual   com/netflix/mediaclient/media/Language.getCurrentAudioSource:()Lcom/netflix/mediaclient/media/AudioSource;
        //    39: invokevirtual   com/netflix/mediaclient/media/Language.setSelectedAudio:(Lcom/netflix/mediaclient/media/AudioSource;)Lcom/netflix/mediaclient/media/AudioSource;
        //    42: pop            
        //    43: aload_0        
        //    44: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.language:Lcom/netflix/mediaclient/media/Language;
        //    47: aload_0        
        //    48: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.language:Lcom/netflix/mediaclient/media/Language;
        //    51: invokevirtual   com/netflix/mediaclient/media/Language.getCurrentSubtitle:()Lcom/netflix/mediaclient/media/Subtitle;
        //    54: invokevirtual   com/netflix/mediaclient/media/Language.setSelectedSubtitle:(Lcom/netflix/mediaclient/media/Subtitle;)Lcom/netflix/mediaclient/media/Subtitle;
        //    57: pop            
        //    58: aload_0        
        //    59: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.mController:Lcom/netflix/mediaclient/android/activity/NetflixActivity;
        //    62: invokestatic    android/view/LayoutInflater.from:(Landroid/content/Context;)Landroid/view/LayoutInflater;
        //    65: ldc             2130903155
        //    67: aconst_null    
        //    68: invokevirtual   android/view/LayoutInflater.inflate:(ILandroid/view/ViewGroup;)Landroid/view/View;
        //    71: astore_1       
        //    72: aload_0        
        //    73: aload_1        
        //    74: aload_0        
        //    75: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.language:Lcom/netflix/mediaclient/media/Language;
        //    78: invokevirtual   com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.initLists:(Landroid/view/View;Lcom/netflix/mediaclient/media/Language;)V
        //    81: aload_0        
        //    82: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.mCallback:Lcom/netflix/mediaclient/ui/common/LanguageSelector$LanguageSelectorCallback;
        //    85: invokeinterface com/netflix/mediaclient/ui/common/LanguageSelector$LanguageSelectorCallback.wasPlaying:()Z
        //    90: istore_2       
        //    91: aload_1        
        //    92: ldc             2131689887
        //    94: invokevirtual   android/view/View.findViewById:(I)Landroid/view/View;
        //    97: new             Lcom/netflix/mediaclient/ui/common/LanguageSelectorKubrick$1;
        //   100: dup            
        //   101: aload_0        
        //   102: iload_2        
        //   103: invokespecial   com/netflix/mediaclient/ui/common/LanguageSelectorKubrick$1.<init>:(Lcom/netflix/mediaclient/ui/common/LanguageSelectorKubrick;Z)V
        //   106: invokevirtual   android/view/View.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   109: new             Lcom/netflix/mediaclient/ui/common/LanguageSelectorKubrick$AudioSubtitlesDlg;
        //   112: dup            
        //   113: invokespecial   com/netflix/mediaclient/ui/common/LanguageSelectorKubrick$AudioSubtitlesDlg.<init>:()V
        //   116: astore_3       
        //   117: aload_3        
        //   118: iconst_1       
        //   119: ldc             2131427569
        //   121: invokevirtual   com/netflix/mediaclient/ui/common/LanguageSelectorKubrick$AudioSubtitlesDlg.setStyle:(II)V
        //   124: aload_3        
        //   125: aload_1        
        //   126: aload_0        
        //   127: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.mCallback:Lcom/netflix/mediaclient/ui/common/LanguageSelector$LanguageSelectorCallback;
        //   130: invokevirtual   com/netflix/mediaclient/ui/common/LanguageSelectorKubrick$AudioSubtitlesDlg.setViewAndCallback:(Landroid/view/View;Lcom/netflix/mediaclient/ui/common/LanguageSelector$LanguageSelectorCallback;)V
        //   133: aload_0        
        //   134: getfield        com/netflix/mediaclient/ui/common/LanguageSelectorKubrick.mController:Lcom/netflix/mediaclient/android/activity/NetflixActivity;
        //   137: aload_3        
        //   138: invokevirtual   com/netflix/mediaclient/android/activity/NetflixActivity.showDialog:(Landroid/app/DialogFragment;)Z
        //   141: pop            
        //   142: goto            14
        //   145: astore_1       
        //   146: aload_0        
        //   147: monitorexit    
        //   148: aload_1        
        //   149: athrow         
        //   150: astore_1       
        //   151: ldc             "nf_language_selector"
        //   153: aload_1        
        //   154: invokestatic    com/netflix/mediaclient/Log.handleException:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   157: goto            14
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  6      14     145    150    Any
        //  17     28     150    160    Lorg/json/JSONException;
        //  17     28     145    150    Any
        //  28     142    145    150    Any
        //  151    157    145    150    Any
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
}
