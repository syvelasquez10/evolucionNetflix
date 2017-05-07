// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.data;

import java.util.Comparator;

class FileSystemDataRepositoryImpl$1 implements Comparator<DataRepository$Entry>
{
    final /* synthetic */ FileSystemDataRepositoryImpl this$0;
    
    FileSystemDataRepositoryImpl$1(final FileSystemDataRepositoryImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final DataRepository$Entry dataRepository$Entry, final DataRepository$Entry dataRepository$Entry2) {
        if (dataRepository$Entry.getTs() == dataRepository$Entry2.getTs()) {
            return 0;
        }
        if (dataRepository$Entry.getTs() < dataRepository$Entry2.getTs()) {
            return -1;
        }
        return 1;
    }
}
