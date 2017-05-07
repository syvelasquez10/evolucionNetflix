// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.widget.FrameLayout;
import java.util.LinkedList;
import android.os.Bundle;

public abstract class a<T extends LifecycleDelegate>
{
    private T RP;
    private Bundle RQ;
    private LinkedList<a$a> RR;
    private final f<T> RS;
    
    public a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: new             new            !!! ERROR
        //     8: dup            
        //     9: aload_0        
        //    10: invokespecial   invokespecial  !!! ERROR
        //    13: putfield        com/google/android/gms/dynamic/a.RS:Lcom/google/android/gms/dynamic/f;
        //    16: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.MetadataParser.parseClassSignature(MetadataParser.java:404)
        //     at com.strobel.assembler.metadata.ClassFileReader.populateBaseTypes(ClassFileReader.java:665)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:438)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:366)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:124)
        //     at com.strobel.decompiler.NoRetryMetadataSystem.resolveType(DecompilerDriver.java:463)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:589)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:599)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2428)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    private void a(final Bundle bundle, final a$a a$a) {
        if (this.RP != null) {
            a$a.b(this.RP);
            return;
        }
        if (this.RR == null) {
            this.RR = new LinkedList<a$a>();
        }
        this.RR.add(a$a);
        if (bundle != null) {
            if (this.RQ == null) {
                this.RQ = (Bundle)bundle.clone();
            }
            else {
                this.RQ.putAll(bundle);
            }
        }
        this.a(this.RS);
    }
    
    public static void b(final FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        final String d = GooglePlayServicesUtil.d(context, googlePlayServicesAvailable);
        final String e = GooglePlayServicesUtil.e(context, googlePlayServicesAvailable);
        final LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        frameLayout.addView((View)linearLayout);
        final TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        textView.setText((CharSequence)d);
        linearLayout.addView((View)textView);
        if (e != null) {
            final Button button = new Button(context);
            button.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
            button.setText((CharSequence)e);
            linearLayout.addView((View)button);
            button.setOnClickListener((View$OnClickListener)new a$5(context, googlePlayServicesAvailable));
        }
    }
    
    private void cv(final int n) {
        while (!this.RR.isEmpty() && this.RR.getLast().getState() >= n) {
            this.RR.removeLast();
        }
    }
    
    protected void a(final FrameLayout frameLayout) {
        b(frameLayout);
    }
    
    protected abstract void a(final f<T> p0);
    
    public T it() {
        return this.RP;
    }
    
    public void onCreate(final Bundle bundle) {
        this.a(bundle, new a$3(this, bundle));
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        this.a(bundle, new a$4(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.RP == null) {
            this.a(frameLayout);
        }
        return (View)frameLayout;
    }
    
    public void onDestroy() {
        if (this.RP != null) {
            this.RP.onDestroy();
            return;
        }
        this.cv(1);
    }
    
    public void onDestroyView() {
        if (this.RP != null) {
            this.RP.onDestroyView();
            return;
        }
        this.cv(2);
    }
    
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        this.a(bundle2, new a$2(this, activity, bundle, bundle2));
    }
    
    public void onLowMemory() {
        if (this.RP != null) {
            this.RP.onLowMemory();
        }
    }
    
    public void onPause() {
        if (this.RP != null) {
            this.RP.onPause();
            return;
        }
        this.cv(5);
    }
    
    public void onResume() {
        this.a(null, new a$7(this));
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (this.RP != null) {
            this.RP.onSaveInstanceState(bundle);
        }
        else if (this.RQ != null) {
            bundle.putAll(this.RQ);
        }
    }
    
    public void onStart() {
        this.a(null, new a$6(this));
    }
    
    public void onStop() {
        if (this.RP != null) {
            this.RP.onStop();
            return;
        }
        this.cv(4);
    }
}
