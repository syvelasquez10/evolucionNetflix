// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import android.os.StatFs;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolume;
import java.security.NoSuchAlgorithmException;
import com.netflix.mediaclient.util.CryptoUtils;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import java.util.Random;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import java.io.File;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.OfflineStorageVolumeUiListImpl;
import java.util.List;
import android.content.Context;

public class OfflineRegistry
{
    private static final int META_REGISTRY_VERSION = 1;
    public static final String OFFLINE_DIRECTORY = "/.of";
    private static final String TAG = "nf_offline_registry";
    private final Context mContext;
    private MetaRegistry mMetaRegistry;
    private final List<OfflineStorageVolumeImpl> mOfflineStorageVolumeList;
    private final OfflineStorageVolumeUiListImpl mUiOfflineUiStorageVolumeList;
    
    private OfflineRegistry(final Context mContext) {
        this.mOfflineStorageVolumeList = new ArrayList<OfflineStorageVolumeImpl>();
        this.mUiOfflineUiStorageVolumeList = new OfflineStorageVolumeUiListImpl();
        this.mContext = mContext;
    }
    
    private RegistryData buildRegistryDataFromFile(File file) {
        File file2;
        RegistryData registryData2;
        while (true) {
            final String s = "";
            file2 = new File(OfflinePathUtils.getFilePathForRegistry(file.getAbsolutePath()));
            String calculateChecksum = s;
            while (true) {
                try {
                    RegistryFileWriter.recoverRegistryDataFileAtStart(file2);
                    String byteArrayToString = s;
                    calculateChecksum = s;
                    if (file2.exists()) {
                        calculateChecksum = s;
                        byteArrayToString = StringUtils.byteArrayToString(FileUtils.readFileToByteArray(file2), "utf-8");
                    }
                    calculateChecksum = byteArrayToString;
                    final RegistryData registryData = NetflixApplication.getGson().fromJson(byteArrayToString, RegistryData.class);
                    calculateChecksum = byteArrayToString;
                    registryData2 = registryData;
                    if (registryData2 == null || registryData2.mOfflinePlayablePersistentDataList == null || registryData2.mDeletedPlayableList == null) {
                        file = (File)new RegistryData(new Random().nextInt(), file.getAbsolutePath());
                        return (RegistryData)file;
                    }
                }
                catch (IOException ex) {
                    if (Log.isLoggable()) {
                        Log.e("nf_offline_registry", ex, "buildRegistryDataFromFile file=" + file2.getAbsolutePath(), new Object[0]);
                    }
                    return null;
                }
                catch (JsonSyntaxException ex2) {
                    if (Log.isLoggable()) {
                        Log.e("nf_offline_registry", ex2, "buildRegistryDataFromFile fromJson failed jsonData=" + calculateChecksum, new Object[0]);
                    }
                    registryData2 = null;
                    continue;
                }
                break;
            }
            registryData2.mOfflineRootStorageDirPath = file.getAbsolutePath();
            final String checkSum = this.mMetaRegistry.getCheckSumFor(registryData2.mRegId);
            calculateChecksum = this.calculateChecksum(calculateChecksum);
            if (Log.isLoggable()) {
                Log.i("nf_offline_registry", "buildRegistryDataFromFile savedCheckSum=" + checkSum + " calculatedCheckSum=" + calculateChecksum);
                Log.i("nf_offline_registry", "mMetaRegistry.mMetaRegistryWriteCounter=" + this.mMetaRegistry.mMetaRegistryWriteCounter + " registryData.mMetaRegistryWriteCounter=" + registryData2.mMetaRegistryWriteCounter);
            }
            file = (File)registryData2;
            if (!StringUtils.isNotEmpty(calculateChecksum)) {
                return (RegistryData)file;
            }
            file = (File)registryData2;
            if (!calculateChecksum.equals(checkSum)) {
                break;
            }
            return (RegistryData)file;
        }
        if (this.mMetaRegistry.mMetaRegistryWriteCounter > 0 && this.mMetaRegistry.mMetaRegistryWriteCounter >= registryData2.mMetaRegistryWriteCounter) {
            throw new ChecksumException("checkSumError for " + file2.getAbsolutePath());
        }
        Log.e("nf_offline_registry", "ignoring checksum error");
        return registryData2;
    }
    
    private String calculateChecksum(String hashSHA256) {
        try {
            hashSHA256 = CryptoUtils.hashSHA256(hashSHA256, "ES6NBf1k7A4YmhA14ZsZQfmEoE8b7mM");
            return hashSHA256;
        }
        catch (NoSuchAlgorithmException ex) {
            if (Log.isLoggable()) {
                Log.e("nf_offline_registry", ex, "calculateChecksum", new Object[0]);
            }
            return null;
        }
    }
    
    public static OfflineRegistry create(final Context context, final OfflineStorageMonitor offlineStorageMonitor) {
        OfflineRegistry offlineRegistry;
        if (!(offlineRegistry = new OfflineRegistry(context)).init(offlineStorageMonitor)) {
            offlineRegistry = null;
        }
        return offlineRegistry;
    }
    
    private List<OfflineStorageVolumeImpl> getOfflineStorageVolumeList() {
        return this.mOfflineStorageVolumeList;
    }
    
    private boolean init(final OfflineStorageMonitor offlineStorageMonitor) {
        this.readMetaRegistry();
        for (final OfflineStorageVolumeInfo offlineStorageVolumeInfo : offlineStorageMonitor.buildOfflineStorageVolumeInfoList()) {
            final File file = new File(offlineStorageVolumeInfo.getDownloadDir().getAbsolutePath() + "/.of");
            if (!file.isDirectory() && !file.mkdirs()) {
                Log.e("nf_offline_registry", "OfflineRegistry can't create directory %s", file.getAbsolutePath());
            }
            else {
                final RegistryData buildRegistryDataFromFile = this.buildRegistryDataFromFile(file);
                if (buildRegistryDataFromFile == null) {
                    continue;
                }
                this.removeCreatingOrFailedItemsFromRegistryData(buildRegistryDataFromFile);
                this.mOfflineStorageVolumeList.add(new OfflineStorageVolumeImpl(this.mMetaRegistry, buildRegistryDataFromFile, offlineStorageVolumeInfo));
            }
        }
        this.mUiOfflineUiStorageVolumeList.update(this.mOfflineStorageVolumeList);
        this.mMetaRegistry.mCurrentRegistryData = null;
        if (this.mOfflineStorageVolumeList.size() > 0) {
            this.mMetaRegistry.mCurrentRegistryData = this.mOfflineStorageVolumeList.get(0).mRegistryData;
            final Iterator<OfflineStorageVolumeImpl> iterator2 = this.mOfflineStorageVolumeList.iterator();
            while (iterator2.hasNext()) {
                final RegistryData mRegistryData = iterator2.next().mRegistryData;
                if (mRegistryData.mRegId == this.mMetaRegistry.mUserSelectedRegId) {
                    Log.i("nf_offline_registry", "found selected regId=%d", mRegistryData.mRegId);
                    this.mMetaRegistry.mCurrentRegistryData = mRegistryData;
                    return true;
                }
            }
            return true;
        }
        return false;
    }
    
    private void readMetaRegistry() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/File;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/netflix/mediaclient/service/offline/registry/OfflineRegistry.mContext:Landroid/content/Context;
        //     8: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    11: invokestatic    com/netflix/mediaclient/service/offline/utils/OfflinePathUtils.getFilePathForMetaRegistry:(Ljava/io/File;)Ljava/lang/String;
        //    14: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    17: astore          4
        //    19: ldc             ""
        //    21: astore_3       
        //    22: aload_3        
        //    23: astore_1       
        //    24: aload           4
        //    26: invokevirtual   java/io/File.exists:()Z
        //    29: ifeq            135
        //    32: aload_3        
        //    33: astore_1       
        //    34: aload           4
        //    36: invokestatic    com/netflix/mediaclient/util/FileUtils.readFileToByteArray:(Ljava/io/File;)[B
        //    39: ldc             "utf-8"
        //    41: invokestatic    com/netflix/mediaclient/util/StringUtils.byteArrayToString:([BLjava/lang/String;)Ljava/lang/String;
        //    44: astore_3       
        //    45: aload_3        
        //    46: astore_2       
        //    47: aload_3        
        //    48: astore_1       
        //    49: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    52: ifeq            99
        //    55: aload_3        
        //    56: astore_1       
        //    57: ldc             "nf_offline_registry"
        //    59: new             Ljava/lang/StringBuilder;
        //    62: dup            
        //    63: invokespecial   java/lang/StringBuilder.<init>:()V
        //    66: ldc_w           "readMtaRegistryFile="
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: aload           4
        //    74: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: ldc_w           " metaRegistryJson="
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: aload_3        
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    96: pop            
        //    97: aload_3        
        //    98: astore_2       
        //    99: aload_0        
        //   100: invokestatic    com/netflix/mediaclient/NetflixApplication.getGson:()Lcom/google/gson/Gson;
        //   103: aload_2        
        //   104: ldc             Lcom/netflix/mediaclient/service/offline/registry/MetaRegistry;.class
        //   106: invokevirtual   com/google/gson/Gson.fromJson:(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
        //   109: checkcast       Lcom/netflix/mediaclient/service/offline/registry/MetaRegistry;
        //   112: putfield        com/netflix/mediaclient/service/offline/registry/OfflineRegistry.mMetaRegistry:Lcom/netflix/mediaclient/service/offline/registry/MetaRegistry;
        //   115: aload_0        
        //   116: getfield        com/netflix/mediaclient/service/offline/registry/OfflineRegistry.mMetaRegistry:Lcom/netflix/mediaclient/service/offline/registry/MetaRegistry;
        //   119: ifnonnull       134
        //   122: aload_0        
        //   123: new             Lcom/netflix/mediaclient/service/offline/registry/MetaRegistry;
        //   126: dup            
        //   127: iconst_1       
        //   128: invokespecial   com/netflix/mediaclient/service/offline/registry/MetaRegistry.<init>:(I)V
        //   131: putfield        com/netflix/mediaclient/service/offline/registry/OfflineRegistry.mMetaRegistry:Lcom/netflix/mediaclient/service/offline/registry/MetaRegistry;
        //   134: return         
        //   135: aload_3        
        //   136: astore_2       
        //   137: aload_3        
        //   138: astore_1       
        //   139: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   142: ifeq            99
        //   145: aload_3        
        //   146: astore_1       
        //   147: ldc             "nf_offline_registry"
        //   149: new             Ljava/lang/StringBuilder;
        //   152: dup            
        //   153: invokespecial   java/lang/StringBuilder.<init>:()V
        //   156: ldc_w           "file doesn't exist readMtaRegistryFile="
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   162: aload           4
        //   164: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   173: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   176: pop            
        //   177: aload_3        
        //   178: astore_2       
        //   179: goto            99
        //   182: astore_2       
        //   183: aload_1        
        //   184: astore_2       
        //   185: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   188: ifeq            99
        //   191: ldc             "nf_offline_registry"
        //   193: new             Ljava/lang/StringBuilder;
        //   196: dup            
        //   197: invokespecial   java/lang/StringBuilder.<init>:()V
        //   200: ldc_w           "readMetaRegistry error filePath="
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: aload           4
        //   208: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   217: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   220: pop            
        //   221: aload_1        
        //   222: astore_2       
        //   223: goto            99
        //   226: astore_1       
        //   227: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   230: ifeq            115
        //   233: ldc             "nf_offline_registry"
        //   235: aload_1        
        //   236: ldc_w           "readMetaRegistry fromJson failed"
        //   239: iconst_0       
        //   240: anewarray       Ljava/lang/Object;
        //   243: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   246: pop            
        //   247: goto            115
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                 
        //  -----  -----  -----  -----  -------------------------------------
        //  24     32     182    226    Ljava/io/IOException;
        //  34     45     182    226    Ljava/io/IOException;
        //  49     55     182    226    Ljava/io/IOException;
        //  57     97     182    226    Ljava/io/IOException;
        //  99     115    226    250    Lcom/google/gson/JsonSyntaxException;
        //  139    145    182    226    Ljava/io/IOException;
        //  147    177    182    226    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0099:
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
    
    private String registryDataToJson(final RegistryData registryData) {
        return NetflixApplication.getGson().toJson(registryData);
    }
    
    private void removeCreatingOrFailedItemsFromRegistryData(final RegistryData registryData) {
        final Iterator<OfflinePlayablePersistentData> iterator = registryData.mOfflinePlayablePersistentDataList.iterator();
        while (iterator.hasNext()) {
            final OfflinePlayablePersistentData offlinePlayablePersistentData = iterator.next();
            if (offlinePlayablePersistentData.getDownloadState() == DownloadState.Creating || offlinePlayablePersistentData.getDownloadState() == DownloadState.CreateFailed) {
                if (Log.isLoggable()) {
                    Log.i("nf_offline_registry", "removeCreatingOrFailedItemsFromRegistryData playableId=" + offlinePlayablePersistentData.mPlayableId + " state=" + offlinePlayablePersistentData.getDownloadState());
                }
                final String directoryPathForViewable = OfflinePathUtils.getDirectoryPathForViewable(registryData.mOfflineRootStorageDirPath, offlinePlayablePersistentData.mPlayableId);
                Log.i("nf_offline_registry", "removeCreatingOrFailedItemsFromRegistryData deleting downloads path=%s success=%b", directoryPathForViewable, OfflineUtils.deletePlayableDirectory(directoryPathForViewable));
                iterator.remove();
            }
        }
    }
    
    public void addToCurrentRegistryData(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        this.mMetaRegistry.mCurrentRegistryData.mOfflinePlayablePersistentDataList.add(offlinePlayablePersistentData);
    }
    
    public boolean areDownloadsPausedByUser() {
        return this.mMetaRegistry.mDownloadsPausedByUser;
    }
    
    public void deleteDeletedList() {
        this.mMetaRegistry.mCurrentRegistryData.mDeletedPlayableList.clear();
    }
    
    public List<OfflinePlayablePersistentData> getAllDeletedPlayable() {
        final ArrayList<OfflinePlayablePersistentData> list = new ArrayList<OfflinePlayablePersistentData>();
        final Iterator<OfflineStorageVolumeImpl> iterator = this.mOfflineStorageVolumeList.iterator();
        while (iterator.hasNext()) {
            final Iterator<OfflinePlayablePersistentData> iterator2 = iterator.next().mRegistryData.mDeletedPlayableList.iterator();
            while (iterator2.hasNext()) {
                list.add(iterator2.next());
            }
        }
        return list;
    }
    
    public String getCurrentOfflineStorageDirPath() {
        return this.mMetaRegistry.mCurrentRegistryData.mOfflineRootStorageDirPath;
    }
    
    public String getGeoCountryCode() {
        return this.mMetaRegistry.mGeoCountryCode;
    }
    
    public String getPrimaryProfileGuid() {
        return this.mMetaRegistry.mPrimaryProfileGuid;
    }
    
    public OfflineRegistry$RegistryEnumerator getRegistryEnumerator() {
        return new OfflineRegistry$RegistryEnumerator(this);
    }
    
    public OfflineStorageVolumeUiList getUiStorageVolumeList() {
        return this.mUiOfflineUiStorageVolumeList;
    }
    
    public boolean hasAtLeastOnePlayable() {
        final Iterator<OfflineStorageVolumeImpl> iterator = this.mOfflineStorageVolumeList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().mRegistryData.mOfflinePlayablePersistentDataList.size() > 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean persistRegistry() {
        final Iterator<OfflineStorageVolumeImpl> iterator = this.mOfflineStorageVolumeList.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final RegistryData mRegistryData = iterator.next().mRegistryData;
            mRegistryData.mMetaRegistryWriteCounter = this.mMetaRegistry.mMetaRegistryWriteCounter + 1;
            final String registryDataToJson = this.registryDataToJson(mRegistryData);
            if (Log.isLoggable()) {
                Log.i("nf_offline_registry", "persistRegistry jsonData=" + registryDataToJson);
            }
            final File file = new File(OfflinePathUtils.getFilePathForRegistry(mRegistryData.mOfflineRootStorageDirPath));
            Log.i("nf_offline_registry", "persistRegistry writing registry=%s", file.getAbsolutePath());
            final boolean writeRegistryFileRecoverable = RegistryFileWriter.writeRegistryFileRecoverable(file, registryDataToJson);
            if (b) {
                b = writeRegistryFileRecoverable;
            }
            if (writeRegistryFileRecoverable) {
                final String calculateChecksum = this.calculateChecksum(registryDataToJson);
                if (!StringUtils.isNotEmpty(calculateChecksum)) {
                    continue;
                }
                if (Log.isLoggable()) {
                    Log.i("nf_offline_registry", "persistRegistry newCheckSum=" + calculateChecksum);
                }
                this.mMetaRegistry.updateCheckSum(mRegistryData.mRegId, calculateChecksum);
            }
        }
        ++this.mMetaRegistry.mMetaRegistryWriteCounter;
        final String json = NetflixApplication.getGson().toJson(this.mMetaRegistry);
        if (Log.isLoggable()) {
            Log.i("nf_offline_registry", "persistRegistry metaRegistryJson=" + json);
        }
        final boolean writeBytesToFile = FileUtils.writeBytesToFile(OfflinePathUtils.getFilePathForMetaRegistry(this.mContext.getFilesDir()), json.getBytes());
        if (b && writeBytesToFile) {
            return true;
        }
        if (Log.isLoggable()) {
            Log.e("nf_offline_registry", "persistRegistry can't save allRegistriesSaved=" + b + "metaRegistrySaved=" + writeBytesToFile);
        }
        return false;
    }
    
    public void recalculateOsvSpaceUsage() {
        for (final OfflineStorageVolumeImpl offlineStorageVolumeImpl : this.mOfflineStorageVolumeList) {
            final StatFs statFsForExternalStorageDir = AndroidUtils.getStatFsForExternalStorageDir(offlineStorageVolumeImpl.getDownloadDir());
            if (statFsForExternalStorageDir != null) {
                offlineStorageVolumeImpl.mOfflineStorageVolumeInfo.updateSpaceInfo(statFsForExternalStorageDir);
            }
        }
    }
    
    public void removeFromDeletedList(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        final Iterator<OfflineStorageVolumeImpl> iterator = this.mOfflineStorageVolumeList.iterator();
        while (iterator.hasNext()) {
            iterator.next().mRegistryData.mDeletedPlayableList.remove(offlinePlayablePersistentData);
        }
    }
    
    public void removePlayable(final OfflinePlayablePersistentData offlinePlayablePersistentData, final boolean b) {
        RegistryData registryData = null;
        final Iterator<OfflineStorageVolumeImpl> iterator = this.mOfflineStorageVolumeList.iterator();
    Label_0013:
        while (iterator.hasNext()) {
            final RegistryData mRegistryData = iterator.next().mRegistryData;
            final Iterator<OfflinePlayablePersistentData> iterator2 = mRegistryData.mOfflinePlayablePersistentDataList.iterator();
            while (true) {
                while (iterator2.hasNext()) {
                    if (iterator2.next().mPlayableId.equals(offlinePlayablePersistentData.mPlayableId)) {
                        registryData = mRegistryData;
                        continue Label_0013;
                    }
                }
                continue;
            }
        }
        if (registryData != null) {
            registryData.mOfflinePlayablePersistentDataList.remove(offlinePlayablePersistentData);
            if (b) {
                registryData.mDeletedPlayableList.add(offlinePlayablePersistentData);
            }
        }
        else if (Log.isLoggable()) {
            Log.e("nf_offline_registry", "removePlayable can't remove the playable");
        }
    }
    
    public boolean setCurrentOfflineVolume(final int n) {
        if (n >= 0 && n < this.mOfflineStorageVolumeList.size()) {
            final OfflineStorageVolumeImpl offlineStorageVolumeImpl = this.mOfflineStorageVolumeList.get(n);
            final Iterator<OfflineStorageVolumeImpl> iterator = this.mOfflineStorageVolumeList.iterator();
            while (iterator.hasNext()) {
                final RegistryData mRegistryData = iterator.next().mRegistryData;
                if (mRegistryData.mRegId == offlineStorageVolumeImpl.getVolumeRegId()) {
                    this.mMetaRegistry.mCurrentRegistryData = mRegistryData;
                    this.mMetaRegistry.mUserSelectedRegId = mRegistryData.mRegId;
                    Log.i("nf_offline_registry", "setCurrentOfflineVolume success mRegId=%d", mRegistryData.mRegId);
                    return true;
                }
            }
        }
        Log.i("nf_offline_registry", "setCurrentOfflineVolume invalid selectedVolumeIndex=%d", n);
        return false;
    }
    
    public void setDownloadsPausedByUser(final boolean mDownloadsPausedByUser) {
        this.mMetaRegistry.mDownloadsPausedByUser = mDownloadsPausedByUser;
    }
    
    public void setGeoCountryCode(final String mGeoCountryCode) {
        this.mMetaRegistry.mGeoCountryCode = mGeoCountryCode;
    }
    
    public void setPrimaryProfileGuid(final String mPrimaryProfileGuid) {
        this.mMetaRegistry.mPrimaryProfileGuid = mPrimaryProfileGuid;
    }
}
