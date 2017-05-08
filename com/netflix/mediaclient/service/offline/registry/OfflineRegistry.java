// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.Iterator;
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
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import android.content.Context;

public class OfflineRegistry
{
    private static final int META_REGISTRY_VERSION = 1;
    public static final String OFFLINE_DIRECTORY = "/.of";
    private static final String TAG = "nf_offline_registry";
    private final Context mContext;
    private RegistryData mCurrentRegistryData;
    private MetaRegistry mMetaRegistry;
    private final File[] mOfflineStorageDirArray;
    private final List<RegistryData> mRegistryList;
    
    private OfflineRegistry(final File[] mOfflineStorageDirArray, final Context mContext) {
        this.mRegistryList = new ArrayList<RegistryData>();
        this.mOfflineStorageDirArray = mOfflineStorageDirArray;
        this.mContext = mContext;
    }
    
    private RegistryData buildRegistryDataFromFile(File file) {
        File file2;
        RegistryData registryData2;
        while (true) {
            final String s = "";
            file2 = new File(OfflinePathUtils.getFilePathForRegistry(file.getAbsolutePath()));
            String byteArrayToString = s;
            String calculateChecksum = s;
            while (true) {
                try {
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
    
    public static OfflineRegistry create(final Context context) {
        final File[] offlineStorageDirectoryArray = getOfflineStorageDirectoryArray(context);
        if (offlineStorageDirectoryArray == null || offlineStorageDirectoryArray.length <= 0) {
            return null;
        }
        final OfflineRegistry offlineRegistry = new OfflineRegistry(offlineStorageDirectoryArray, context);
        offlineRegistry.readMetaRegistry();
        offlineRegistry.readRegistryDataList();
        final boolean hasAtLeastOneRegistryData = offlineRegistry.hasAtLeastOneRegistryData();
        if (hasAtLeastOneRegistryData) {
            offlineRegistry.ensureCurrentRegistryData();
        }
        if (hasAtLeastOneRegistryData) {
            return offlineRegistry;
        }
        return null;
    }
    
    private void ensureCurrentRegistryData() {
        for (final RegistryData mCurrentRegistryData : this.mRegistryList) {
            if (mCurrentRegistryData.mRegId == this.mMetaRegistry.mCurrentActiveRegId) {
                if (Log.isLoggable()) {
                    Log.i("nf_offline_registry", "found active reg " + mCurrentRegistryData.mRegId);
                }
                this.mCurrentRegistryData = mCurrentRegistryData;
                return;
            }
        }
        this.mCurrentRegistryData = this.mRegistryList.get(0);
        this.mMetaRegistry.mCurrentActiveRegId = this.mCurrentRegistryData.mRegId;
    }
    
    private static File[] getOfflineStorageDirectoryArray(final Context context) {
        final File externalDownloadDirIfAvailable = AndroidUtils.getExternalDownloadDirIfAvailable(context);
        if (externalDownloadDirIfAvailable != null) {
            final File file = new File(externalDownloadDirIfAvailable.getAbsolutePath() + "/.of");
            if (file.isDirectory() || file.mkdirs()) {
                return new File[] { file };
            }
            if (Log.isLoggable()) {
                Log.e("nf_offline_registry", "getOfflineStorageDirectoryArray can't create directory");
                return null;
            }
        }
        return null;
    }
    
    private boolean hasAtLeastOneRegistryData() {
        return this.mRegistryList.size() > 0;
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
    
    private void readRegistryDataList() {
        final File[] mOfflineStorageDirArray = this.mOfflineStorageDirArray;
        for (int length = mOfflineStorageDirArray.length, i = 0; i < length; ++i) {
            final RegistryData buildRegistryDataFromFile = this.buildRegistryDataFromFile(mOfflineStorageDirArray[i]);
            if (buildRegistryDataFromFile != null) {
                this.removeCreatingOrFailedItemsFromRegistryData(buildRegistryDataFromFile);
                this.mRegistryList.add(buildRegistryDataFromFile);
            }
        }
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
                    Log.i("nf_offline_registry", "removeCreatingOrFailedItemsFromRegistryData ignoring playableId=" + offlinePlayablePersistentData.mPlayableId + " state=" + offlinePlayablePersistentData.getDownloadState());
                }
                iterator.remove();
            }
        }
    }
    
    public void addToCurrentRegistryData(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        this.mCurrentRegistryData.mOfflinePlayablePersistentDataList.add(offlinePlayablePersistentData);
    }
    
    public boolean areDownloadsPausedByUser() {
        return this.mMetaRegistry.mDownloadsPausedByUser;
    }
    
    public void deleteDeletedList() {
        this.mCurrentRegistryData.mDeletedPlayableList.clear();
    }
    
    public List<OfflinePlayablePersistentData> getAllDeletedPlayable() {
        final ArrayList<OfflinePlayablePersistentData> list = new ArrayList<OfflinePlayablePersistentData>();
        final Iterator<RegistryData> iterator = this.mRegistryList.iterator();
        while (iterator.hasNext()) {
            final Iterator<OfflinePlayablePersistentData> iterator2 = iterator.next().mDeletedPlayableList.iterator();
            while (iterator2.hasNext()) {
                list.add(iterator2.next());
            }
        }
        return list;
    }
    
    public String getCurrentOfflineStorageDirPath() {
        return this.mCurrentRegistryData.mOfflineRootStorageDirPath;
    }
    
    public String getGeoCountryCode() {
        return this.mMetaRegistry.mGeoCountryCode;
    }
    
    public String getPrimaryProfileGuid() {
        return this.mMetaRegistry.mPrimaryProfileGuid;
    }
    
    public List<RegistryData> getRegistryList() {
        return this.mRegistryList;
    }
    
    public boolean hasAtLeastOnePlayable() {
        final Iterator<RegistryData> iterator = this.mRegistryList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().mOfflinePlayablePersistentDataList.size() > 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean persistRegistry() {
        final Iterator<RegistryData> iterator = this.mRegistryList.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final RegistryData registryData = iterator.next();
            registryData.mMetaRegistryWriteCounter = this.mMetaRegistry.mMetaRegistryWriteCounter + 1;
            final String registryDataToJson = this.registryDataToJson(registryData);
            if (Log.isLoggable()) {
                Log.i("nf_offline_registry", "persistRegistry jsonData=" + registryDataToJson);
            }
            final File file = new File(OfflinePathUtils.getFilePathForRegistry(registryData.mOfflineRootStorageDirPath));
            if (Log.isLoggable()) {
                Log.i("nf_offline_registry", "persistRegistry writing registry=" + file.getAbsolutePath());
            }
            final boolean writeBytesToFile = FileUtils.writeBytesToFile(file.getAbsolutePath(), registryDataToJson.getBytes());
            boolean b2 = b;
            if (b) {
                b2 = writeBytesToFile;
            }
            b = b2;
            if (writeBytesToFile) {
                final String calculateChecksum = this.calculateChecksum(registryDataToJson);
                b = b2;
                if (!StringUtils.isNotEmpty(calculateChecksum)) {
                    continue;
                }
                if (Log.isLoggable()) {
                    Log.i("nf_offline_registry", "persistRegistry newCheckSum=" + calculateChecksum);
                }
                this.mMetaRegistry.updateCheckSum(registryData.mRegId, calculateChecksum);
                b = b2;
            }
        }
        ++this.mMetaRegistry.mMetaRegistryWriteCounter;
        final String json = NetflixApplication.getGson().toJson(this.mMetaRegistry);
        if (Log.isLoggable()) {
            Log.i("nf_offline_registry", "persistRegistry metaRegistryJson=" + json);
        }
        final boolean writeBytesToFile2 = FileUtils.writeBytesToFile(OfflinePathUtils.getFilePathForMetaRegistry(this.mContext.getFilesDir()), json.getBytes());
        if (b && writeBytesToFile2) {
            return true;
        }
        if (Log.isLoggable()) {
            Log.e("nf_offline_registry", "persistRegistry can't save allRegistriesSaved=" + b + "metaRegistrySaved=" + writeBytesToFile2);
        }
        return false;
    }
    
    public void removeFromDeletedList(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        final Iterator<RegistryData> iterator = this.mRegistryList.iterator();
        while (iterator.hasNext()) {
            iterator.next().mDeletedPlayableList.remove(offlinePlayablePersistentData);
        }
    }
    
    public void removePlayable(final OfflinePlayablePersistentData offlinePlayablePersistentData, final boolean b) {
        RegistryData registryData = null;
    Label_0013:
        for (final RegistryData registryData2 : this.mRegistryList) {
            final Iterator<OfflinePlayablePersistentData> iterator2 = registryData2.mOfflinePlayablePersistentDataList.iterator();
            while (true) {
                while (iterator2.hasNext()) {
                    if (iterator2.next().mPlayableId.equals(offlinePlayablePersistentData.mPlayableId)) {
                        registryData = registryData2;
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
