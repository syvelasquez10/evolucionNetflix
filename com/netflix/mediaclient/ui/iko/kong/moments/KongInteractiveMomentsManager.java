// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.moments;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import java.util.Comparator;
import java.util.Collections;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.util.AudioUtils;
import android.content.Context;
import android.animation.ObjectAnimator;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import java.util.Map;
import android.media.SoundPool;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel$KongInteractiveMoment;
import java.util.List;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsManager;

public class KongInteractiveMomentsManager implements InteractiveMomentsManager
{
    private static final String ANIMATOR_PROPERTY_ROTATION = "rotation";
    private static final String TAG = "KongInteractiveMomentsManager";
    private boolean bottomPanelVisible;
    private KongInteractiveMomentsModel data;
    private int failureCount;
    private boolean hasInteractiveMoments;
    private ImageView image;
    private ImageView imageBackground;
    private ViewGroup imageContainer;
    private List<KongInteractiveMomentsModel$KongInteractiveMoment> interactiveMoments;
    private boolean isShowingInteractiveMoment;
    private int mBottomPanelHeight;
    private PlayerFragment mFragment;
    private SoundPool mSoundPool;
    private List<Integer> momentsDisplayTimeList;
    private int resourceRequestCounter;
    private int resourceResponseCounter;
    private Map<String, LocalCachedFileMetadata> resourceToLocalCacheFileMap;
    private ServiceManager svcManager;
    private TextView title;
    
    public KongInteractiveMomentsManager() {
        this.resourceToLocalCacheFileMap = new HashMap<String, LocalCachedFileMetadata>();
        this.momentsDisplayTimeList = new ArrayList<Integer>();
    }
    
    private void cacheResources(final String s) {
        if (this.mFragment == null || !this.mFragment.isActivityValid()) {
            Log.e("KongInteractiveMomentsManager", "Player Fragment is null or activity is not valid.");
        }
        else {
            if (StringUtils.isEmpty(s)) {
                Log.e("KongInteractiveMomentsManager", "Invalid request to cache resource for an empty or null url.");
                return;
            }
            if (this.svcManager != null) {
                if (Log.isLoggable()) {
                    Log.d("KongInteractiveMomentsManager", "Fetching and caching resource " + s);
                }
                this.svcManager.fetchAndCacheResource(s, IClientLogging$AssetType.interactiveContent, new KongInteractiveMomentsManager$2(this));
                return;
            }
            if (Log.isLoggable()) {
                Log.d("KongInteractiveMomentsManager", "Service manager is null. Cannot fetch resource - " + s);
            }
        }
    }
    
    private Bitmap fetchImageFromCache(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.e("KongInteractiveMomentsManager", " Empty or null request url to load image from cache");
            return null;
        }
        final LocalCachedFileMetadata localCachedFileMetadata = this.resourceToLocalCacheFileMap.get(s);
        if (localCachedFileMetadata == null || StringUtils.isEmpty(localCachedFileMetadata.getLocalUrl())) {
            Log.d("KongInteractiveMomentsManager", "Resource not downloaded to disk cache. Ignore request to load image.");
            return null;
        }
        final String localUrl = localCachedFileMetadata.getLocalUrl();
        if (Log.isLoggable()) {
            Log.d("KongInteractiveMomentsManager", "Loading image from cache for url " + s + " Local url = " + localUrl);
        }
        final File file = new File(localUrl);
        if (!file.exists()) {
            return null;
        }
        try {
            return BitmapFactory.decodeByteArray(FileUtils.readFileToByteArray(file), (int)localCachedFileMetadata.getByteOffset(), (int)localCachedFileMetadata.getByteSize());
        }
        catch (IOException ex2) {
            Log.e("KongInteractiveMomentsManager", "Error loading image from cache for url " + localUrl);
            return null;
        }
        catch (Exception ex) {
            Log.e("KongInteractiveMomentsManager", "Error loading image from cache for url " + localUrl + " Exception - " + ex.getMessage());
            return null;
        }
        return null;
    }
    
    private int loadSoundPoolVo(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/ui/iko/kong/moments/KongInteractiveMomentsManager.mSoundPool:Landroid/media/SoundPool;
        //     4: ifnonnull       34
        //     7: ldc             "KongInteractiveMomentsManager"
        //     9: new             Ljava/lang/StringBuilder;
        //    12: dup            
        //    13: invokespecial   java/lang/StringBuilder.<init>:()V
        //    16: ldc             "Sound is null. Request to load url failed for - "
        //    18: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    21: aload_1        
        //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    25: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    28: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    31: pop            
        //    32: iconst_m1      
        //    33: ireturn        
        //    34: aload_0        
        //    35: getfield        com/netflix/mediaclient/ui/iko/kong/moments/KongInteractiveMomentsManager.resourceToLocalCacheFileMap:Ljava/util/Map;
        //    38: aload_1        
        //    39: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    44: checkcast       Lcom/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata;
        //    47: astore          6
        //    49: aload           6
        //    51: ifnull          32
        //    54: aload           6
        //    56: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getLocalUrl:()Ljava/lang/String;
        //    59: astore          5
        //    61: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    64: ifeq            102
        //    67: ldc             "KongInteractiveMomentsManager"
        //    69: new             Ljava/lang/StringBuilder;
        //    72: dup            
        //    73: invokespecial   java/lang/StringBuilder.<init>:()V
        //    76: ldc             "Loading audio from cache for url "
        //    78: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    81: aload_1        
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: ldc             " Local url = "
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: aload           5
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    98: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   101: pop            
        //   102: new             Ljava/io/File;
        //   105: dup            
        //   106: aload           5
        //   108: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   111: astore_3       
        //   112: aload_3        
        //   113: invokevirtual   java/io/File.exists:()Z
        //   116: ifeq            350
        //   119: new             Ljava/io/FileInputStream;
        //   122: dup            
        //   123: aload_3        
        //   124: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   127: astore_3       
        //   128: aload_3        
        //   129: astore          4
        //   131: aload_0        
        //   132: getfield        com/netflix/mediaclient/ui/iko/kong/moments/KongInteractiveMomentsManager.mSoundPool:Landroid/media/SoundPool;
        //   135: aload_3        
        //   136: invokevirtual   java/io/FileInputStream.getFD:()Ljava/io/FileDescriptor;
        //   139: aload           6
        //   141: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getByteOffset:()J
        //   144: aload           6
        //   146: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/LocalCachedFileMetadata.getByteSize:()J
        //   149: iconst_1       
        //   150: invokevirtual   android/media/SoundPool.load:(Ljava/io/FileDescriptor;JJI)I
        //   153: istore_2       
        //   154: aload_3        
        //   155: astore          4
        //   157: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   160: ifeq            200
        //   163: aload_3        
        //   164: astore          4
        //   166: ldc             "KongInteractiveMomentsManager"
        //   168: new             Ljava/lang/StringBuilder;
        //   171: dup            
        //   172: invokespecial   java/lang/StringBuilder.<init>:()V
        //   175: ldc             "Sound pool id for url "
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: aload_1        
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: ldc             "is "
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: iload_2        
        //   190: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   193: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   196: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   199: pop            
        //   200: aload_3        
        //   201: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   204: iload_2        
        //   205: ireturn        
        //   206: astore_3       
        //   207: aconst_null    
        //   208: astore_3       
        //   209: iconst_m1      
        //   210: istore_2       
        //   211: ldc             "KongInteractiveMomentsManager"
        //   213: new             Ljava/lang/StringBuilder;
        //   216: dup            
        //   217: invokespecial   java/lang/StringBuilder.<init>:()V
        //   220: ldc             "FileNotFoundException while loading resource "
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: aload_1        
        //   226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   229: ldc             " from cache file "
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   234: aload           5
        //   236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   239: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   242: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   245: pop            
        //   246: aload_3        
        //   247: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   250: goto            204
        //   253: astore_3       
        //   254: aconst_null    
        //   255: astore_3       
        //   256: iconst_m1      
        //   257: istore_2       
        //   258: aload_3        
        //   259: astore          4
        //   261: ldc             "KongInteractiveMomentsManager"
        //   263: new             Ljava/lang/StringBuilder;
        //   266: dup            
        //   267: invokespecial   java/lang/StringBuilder.<init>:()V
        //   270: ldc_w           "IOException while loading resource "
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: aload_1        
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: ldc             " from cache file "
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: aload           5
        //   287: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   290: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   293: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   296: pop            
        //   297: aload_3        
        //   298: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   301: goto            204
        //   304: astore_1       
        //   305: aconst_null    
        //   306: astore          4
        //   308: aload           4
        //   310: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   313: aload_1        
        //   314: athrow         
        //   315: astore_1       
        //   316: goto            308
        //   319: astore_1       
        //   320: aload_3        
        //   321: astore          4
        //   323: goto            308
        //   326: astore          4
        //   328: iconst_m1      
        //   329: istore_2       
        //   330: goto            258
        //   333: astore          4
        //   335: goto            258
        //   338: astore          4
        //   340: iconst_m1      
        //   341: istore_2       
        //   342: goto            211
        //   345: astore          4
        //   347: goto            211
        //   350: iconst_m1      
        //   351: istore_2       
        //   352: goto            204
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  119    128    206    211    Ljava/io/FileNotFoundException;
        //  119    128    253    258    Ljava/io/IOException;
        //  119    128    304    308    Any
        //  131    154    338    345    Ljava/io/FileNotFoundException;
        //  131    154    326    333    Ljava/io/IOException;
        //  131    154    315    319    Any
        //  157    163    345    350    Ljava/io/FileNotFoundException;
        //  157    163    333    338    Ljava/io/IOException;
        //  157    163    315    319    Any
        //  166    200    345    350    Ljava/io/FileNotFoundException;
        //  166    200    333    338    Ljava/io/IOException;
        //  166    200    315    319    Any
        //  211    246    319    326    Any
        //  261    297    315    319    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 171, Size: 171
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private void loadViewResources() {
        AsyncTaskCompat.execute(new KongInteractiveMomentsManager$3(this));
    }
    
    private void loadViews(final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment) {
        this.title.setText((CharSequence)kongInteractiveMomentsModel$KongInteractiveMoment.getName());
        this.image.setImageBitmap(kongInteractiveMomentsModel$KongInteractiveMoment.getImageBitmap());
        this.imageBackground.setImageBitmap(kongInteractiveMomentsModel$KongInteractiveMoment.getImageBackgroundBitmap());
    }
    
    private void releaseBitmapMemory() {
        for (final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment : this.interactiveMoments) {
            if (kongInteractiveMomentsModel$KongInteractiveMoment != null) {
                kongInteractiveMomentsModel$KongInteractiveMoment.setImageBitmap(null);
                kongInteractiveMomentsModel$KongInteractiveMoment.setImageBackgroundBitmap(null);
            }
        }
        ViewUtils.resetImageDrawable(this.image);
        ViewUtils.resetImageDrawable(this.imageBackground);
    }
    
    private void show(final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment) {
        if (kongInteractiveMomentsModel$KongInteractiveMoment == null) {
            Log.e("KongInteractiveMomentsManager", "Request to show unlocking animation on an null collection item.");
        }
        else {
            if (!this.isManagerReady()) {
                Log.d("KongInteractiveMomentsManager", "Resources not available to show moment. Skipping for now...");
            }
            if (this.isShowingInteractiveMoment) {
                Log.d("KongInteractiveMomentsManager", "Already showing interactive moment. Ignore request.");
                return;
            }
            this.isShowingInteractiveMoment = true;
            this.loadViews(kongInteractiveMomentsModel$KongInteractiveMoment);
            AnimationUtils.startViewAppearanceAnimation((View)this.imageContainer, true);
            ObjectAnimator.ofFloat((Object)this.imageBackground, "rotation", new float[] { 0.0f, 360.0f }).setDuration((long)kongInteractiveMomentsModel$KongInteractiveMoment.getDurationMS()).start();
            if (this.mSoundPool != null) {
                this.mSoundPool.play(kongInteractiveMomentsModel$KongInteractiveMoment.getSfxSoundPoolId(), kongInteractiveMomentsModel$KongInteractiveMoment.getUnlockSfxSoundVolume(), kongInteractiveMomentsModel$KongInteractiveMoment.getUnlockSfxSoundVolume(), 1, 0, 0.0f);
            }
        }
    }
    
    private void startCachingResources() {
        final List<String> preCacheableResourcesList = this.data.getPreCacheableResourcesList();
        if (preCacheableResourcesList != null && preCacheableResourcesList.size() > 0) {
            this.resourceRequestCounter = preCacheableResourcesList.size();
            this.resourceResponseCounter = 0;
            this.failureCount = 0;
            final Iterator<String> iterator = this.data.getPreCacheableResourcesList().iterator();
            while (iterator.hasNext()) {
                this.cacheResources(iterator.next());
            }
        }
    }
    
    @Override
    public void hide() {
        AnimationUtils.startViewAppearanceAnimation((View)this.imageContainer, false);
        this.isShowingInteractiveMoment = false;
    }
    
    @Override
    public void init(final PlayerFragment mFragment) {
        if (mFragment == null) {
            Log.e("KongInteractiveMomentsManager", "Player fragment is null. This should not happen");
            return;
        }
        this.mFragment = mFragment;
        final View view = mFragment.getView();
        this.imageContainer = (ViewGroup)view.findViewById(2131689851);
        if (this.imageContainer == null) {
            Log.d("KongInteractiveMomentsManager", "No interactive moments view container. Exiting the decorator.");
            return;
        }
        this.hasInteractiveMoments = false;
        this.imageBackground = (ImageView)view.findViewById(2131689853);
        this.image = (ImageView)view.findViewById(2131689561);
        this.title = (TextView)view.findViewById(2131689565);
        this.mBottomPanelHeight = this.mFragment.getResources().getDimensionPixelSize(2131361929);
        if (ViewUtils.isNavigationBarBelowContent(this.mFragment.getActivity())) {
            this.mBottomPanelHeight += ViewUtils.getNavigationBarHeight((Context)this.mFragment.getActivity(), false);
        }
        this.svcManager = ServiceManager.getServiceManager(mFragment.getNetflixActivity());
        this.mSoundPool = AudioUtils.createSoundPool(5);
    }
    
    @Override
    public boolean isManagerReady() {
        return this.resourceResponseCounter >= this.resourceRequestCounter && this.failureCount == 0;
    }
    
    @Override
    public void onDestroy() {
        if (this.mSoundPool != null) {
            this.mSoundPool.release();
            this.mSoundPool = null;
        }
        this.releaseBitmapMemory();
    }
    
    @Override
    public void onMomentsFetched(final InteractivePlaybackMoments interactivePlaybackMoments) {
        if (interactivePlaybackMoments != null) {
            final InteractiveMomentsModel data = interactivePlaybackMoments.getData();
            if (data != null && !"kong".equalsIgnoreCase(data.getType())) {
                Log.e("KongInteractiveMomentsManager", "Interactive data is null or of wrong type.");
            }
            else {
                if (data == null || !(data instanceof KongInteractiveMomentsModel)) {
                    Log.e("KongInteractiveMomentsManager", "Interactive data is null or not instance of KongInteractiveMomentsModel.");
                    return;
                }
                this.data = (KongInteractiveMomentsModel)data;
                this.interactiveMoments = this.data.getMoments();
                if (this.interactiveMoments != null && this.interactiveMoments.size() > 0) {
                    this.hasInteractiveMoments = true;
                    Collections.sort(this.interactiveMoments, new KongInteractiveMomentsManager$1(this));
                    for (final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment : this.interactiveMoments) {
                        if (kongInteractiveMomentsModel$KongInteractiveMoment != null && kongInteractiveMomentsModel$KongInteractiveMoment.getDurationMS() > 0) {
                            this.momentsDisplayTimeList.add(kongInteractiveMomentsModel$KongInteractiveMoment.getMomentStartTimeMS());
                            this.momentsDisplayTimeList.add(kongInteractiveMomentsModel$KongInteractiveMoment.getMomentEndTimeMS());
                        }
                    }
                    this.startCachingResources();
                }
            }
        }
    }
    
    @Override
    public void onPause() {
    }
    
    @Override
    public void onResume() {
    }
    
    @Override
    public void onStart() {
    }
    
    @Override
    public void onStop() {
    }
    
    @Override
    public void onVideoDetailsFetched(final VideoDetails videoDetails) {
    }
    
    @Override
    public void playerOverlayVisibility(final boolean bottomPanelVisible) {
        this.bottomPanelVisible = bottomPanelVisible;
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.imageContainer.getLayoutParams();
        if (bottomPanelVisible) {
            layoutParams.setMargins(0, 0, 0, this.mBottomPanelHeight);
        }
        else {
            layoutParams.setMargins(0, 0, 0, 0);
        }
        this.imageContainer.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    @Override
    public void setTimelineProgress(int binarySearch, final boolean b) {
        if (b && this.hasInteractiveMoments) {
            binarySearch = Collections.binarySearch(this.momentsDisplayTimeList, binarySearch);
            if (binarySearch >= 0) {
                this.show(this.interactiveMoments.get(binarySearch / 2));
            }
            else {
                binarySearch = binarySearch * -1 - 1;
                if (binarySearch % 2 != 0) {
                    this.show(this.interactiveMoments.get(binarySearch / 2));
                    return;
                }
                this.hide();
            }
        }
    }
}
