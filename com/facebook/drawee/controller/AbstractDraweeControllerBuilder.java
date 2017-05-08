// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.controller;

import com.facebook.common.internal.Preconditions;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import java.util.Iterator;
import java.util.List;
import com.facebook.datasource.FirstAvailableDataSourceSupplier;
import java.util.ArrayList;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.datasource.DataSource;
import com.facebook.common.internal.Supplier;
import android.content.Context;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;

public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements SimpleDraweeControllerBuilder
{
    private static final NullPointerException NO_REQUEST_EXCEPTION;
    private static final ControllerListener<Object> sAutoPlayAnimationsListener;
    private static final AtomicLong sIdCounter;
    private boolean mAutoPlayAnimations;
    private final Set<ControllerListener> mBoundControllerListeners;
    private Object mCallerContext;
    private final Context mContext;
    private ControllerListener<? super INFO> mControllerListener;
    private Supplier<DataSource<IMAGE>> mDataSourceSupplier;
    private REQUEST mImageRequest;
    private REQUEST mLowResImageRequest;
    private REQUEST[] mMultiImageRequests;
    private DraweeController mOldController;
    private boolean mRetainImageOnFailure;
    private boolean mTapToRetryEnabled;
    private boolean mTryCacheOnlyFirst;
    
    static {
        sAutoPlayAnimationsListener = new AbstractDraweeControllerBuilder$1();
        NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
        sIdCounter = new AtomicLong();
    }
    
    protected AbstractDraweeControllerBuilder(final Context mContext, final Set<ControllerListener> mBoundControllerListeners) {
        this.mContext = mContext;
        this.mBoundControllerListeners = mBoundControllerListeners;
        this.init();
    }
    
    protected static String generateUniqueControllerId() {
        return String.valueOf(AbstractDraweeControllerBuilder.sIdCounter.getAndIncrement());
    }
    
    private void init() {
        this.mCallerContext = null;
        this.mImageRequest = null;
        this.mLowResImageRequest = null;
        this.mMultiImageRequests = null;
        this.mTryCacheOnlyFirst = true;
        this.mControllerListener = null;
        this.mTapToRetryEnabled = false;
        this.mAutoPlayAnimations = false;
        this.mOldController = null;
    }
    
    @Override
    public AbstractDraweeController build() {
        this.validate();
        if (this.mImageRequest == null && this.mMultiImageRequests == null && this.mLowResImageRequest != null) {
            this.mImageRequest = this.mLowResImageRequest;
            this.mLowResImageRequest = null;
        }
        return this.buildController();
    }
    
    protected AbstractDraweeController buildController() {
        final AbstractDraweeController obtainController = this.obtainController();
        obtainController.setRetainImageOnFailure(this.getRetainImageOnFailure());
        this.maybeBuildAndSetRetryManager(obtainController);
        this.maybeAttachListeners(obtainController);
        return obtainController;
    }
    
    public Object getCallerContext() {
        return this.mCallerContext;
    }
    
    protected abstract DataSource<IMAGE> getDataSourceForRequest(final REQUEST p0, final Object p1, final boolean p2);
    
    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(final REQUEST request) {
        return this.getDataSourceSupplierForRequest(request, false);
    }
    
    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(final REQUEST p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             new            !!! ERROR
        //     3: dup            
        //     4: aload_0        
        //     5: aload_1        
        //     6: aload_0        
        //     7: invokevirtual   com/facebook/drawee/controller/AbstractDraweeControllerBuilder.getCallerContext:()Ljava/lang/Object;
        //    10: iload_2        
        //    11: invokespecial   invokespecial  !!! ERROR
        //    14: areturn        
        //    Signature:
        //  (TREQUEST;Z)Lcom/facebook/common/internal/Supplier<Lcom/facebook/datasource/DataSource<TIMAGE;>;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalArgumentException: Argument 'typeArguments' must not have any null elements.
        //     at com.strobel.core.VerifyArgument.noNullElementsAndNotEmpty(VerifyArgument.java:145)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.makeGenericType(CoreMetadataFactory.java:570)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory.makeParameterizedType(CoreMetadataFactory.java:156)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:125)
        //     at com.strobel.assembler.metadata.signatures.ClassTypeSignature.accept(ClassTypeSignature.java:46)
        //     at com.strobel.assembler.metadata.signatures.Reifier.reifyTypeArguments(Reifier.java:53)
        //     at com.strobel.assembler.metadata.signatures.Reifier.visitClassTypeSignature(Reifier.java:123)
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
    
    protected Supplier<DataSource<IMAGE>> getFirstAvailableDataSourceSupplier(final REQUEST[] array, final boolean b) {
        final boolean b2 = false;
        final ArrayList<Supplier<DataSource<IMAGE>>> list = new ArrayList<Supplier<DataSource<IMAGE>>>(array.length * 2);
        int i = b2 ? 1 : 0;
        if (b) {
            int n = 0;
            while (true) {
                i = (b2 ? 1 : 0);
                if (n >= array.length) {
                    break;
                }
                list.add(this.getDataSourceSupplierForRequest(array[n], true));
                ++n;
            }
        }
        while (i < array.length) {
            list.add(this.getDataSourceSupplierForRequest(array[i]));
            ++i;
        }
        return FirstAvailableDataSourceSupplier.create(list);
    }
    
    public DraweeController getOldController() {
        return this.mOldController;
    }
    
    public boolean getRetainImageOnFailure() {
        return this.mRetainImageOnFailure;
    }
    
    protected abstract BUILDER getThis();
    
    protected void maybeAttachListeners(final AbstractDraweeController abstractDraweeController) {
        if (this.mBoundControllerListeners != null) {
            final Iterator<ControllerListener> iterator = this.mBoundControllerListeners.iterator();
            while (iterator.hasNext()) {
                abstractDraweeController.addControllerListener(iterator.next());
            }
        }
        if (this.mControllerListener != null) {
            abstractDraweeController.addControllerListener(this.mControllerListener);
        }
        if (this.mAutoPlayAnimations) {
            abstractDraweeController.addControllerListener(AbstractDraweeControllerBuilder.sAutoPlayAnimationsListener);
        }
    }
    
    protected void maybeBuildAndSetGestureDetector(final AbstractDraweeController abstractDraweeController) {
        if (abstractDraweeController.getGestureDetector() == null) {
            abstractDraweeController.setGestureDetector(GestureDetector.newInstance(this.mContext));
        }
    }
    
    protected void maybeBuildAndSetRetryManager(final AbstractDraweeController abstractDraweeController) {
        if (!this.mTapToRetryEnabled) {
            return;
        }
        RetryManager retryManager;
        if ((retryManager = abstractDraweeController.getRetryManager()) == null) {
            retryManager = new RetryManager();
            abstractDraweeController.setRetryManager(retryManager);
        }
        retryManager.setTapToRetryEnabled(this.mTapToRetryEnabled);
        this.maybeBuildAndSetGestureDetector(abstractDraweeController);
    }
    
    protected abstract AbstractDraweeController obtainController();
    
    protected Supplier<DataSource<IMAGE>> obtainDataSourceSupplier() {
        Supplier<DataSource<IMAGE>> mDataSourceSupplier;
        if (this.mDataSourceSupplier != null) {
            mDataSourceSupplier = this.mDataSourceSupplier;
        }
        else {
            Supplier<DataSource<IMAGE>> supplier = null;
            if (this.mImageRequest != null) {
                supplier = this.getDataSourceSupplierForRequest(this.mImageRequest);
            }
            else if (this.mMultiImageRequests != null) {
                supplier = this.getFirstAvailableDataSourceSupplier(this.mMultiImageRequests, this.mTryCacheOnlyFirst);
            }
            Supplier<DataSource<IMAGE>> create;
            if ((create = supplier) != null) {
                create = supplier;
                if (this.mLowResImageRequest != null) {
                    final ArrayList<Supplier<DataSource<IMAGE>>> list = new ArrayList<Supplier<DataSource<IMAGE>>>(2);
                    list.add(supplier);
                    list.add(this.getDataSourceSupplierForRequest(this.mLowResImageRequest));
                    create = IncreasingQualityDataSourceSupplier.create(list);
                }
            }
            if ((mDataSourceSupplier = create) == null) {
                return DataSources.getFailedDataSourceSupplier(AbstractDraweeControllerBuilder.NO_REQUEST_EXCEPTION);
            }
        }
        return mDataSourceSupplier;
    }
    
    public BUILDER reset() {
        this.init();
        return this.getThis();
    }
    
    public BUILDER setAutoPlayAnimations(final boolean mAutoPlayAnimations) {
        this.mAutoPlayAnimations = mAutoPlayAnimations;
        return this.getThis();
    }
    
    @Override
    public BUILDER setCallerContext(final Object mCallerContext) {
        this.mCallerContext = mCallerContext;
        return this.getThis();
    }
    
    public BUILDER setControllerListener(final ControllerListener<? super INFO> mControllerListener) {
        this.mControllerListener = mControllerListener;
        return this.getThis();
    }
    
    public BUILDER setImageRequest(final REQUEST mImageRequest) {
        this.mImageRequest = mImageRequest;
        return this.getThis();
    }
    
    public BUILDER setLowResImageRequest(final REQUEST mLowResImageRequest) {
        this.mLowResImageRequest = mLowResImageRequest;
        return this.getThis();
    }
    
    @Override
    public BUILDER setOldController(final DraweeController mOldController) {
        this.mOldController = mOldController;
        return this.getThis();
    }
    
    protected void validate() {
        final boolean b = false;
        Preconditions.checkState(this.mMultiImageRequests == null || this.mImageRequest == null, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        boolean b2 = false;
        Label_0061: {
            if (this.mDataSourceSupplier != null) {
                b2 = b;
                if (this.mMultiImageRequests != null) {
                    break Label_0061;
                }
                b2 = b;
                if (this.mImageRequest != null) {
                    break Label_0061;
                }
                b2 = b;
                if (this.mLowResImageRequest != null) {
                    break Label_0061;
                }
            }
            b2 = true;
        }
        Preconditions.checkState(b2, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }
}
