// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.FieldOnlyFilter;
import com.google.android.gms.drive.query.internal.NotFilter;
import com.google.android.gms.drive.query.internal.InFilter;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import com.google.android.gms.drive.metadata.OrderedMetadataField;
import com.google.android.gms.drive.query.internal.ComparisonFilter;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.List;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.Operator;

public class Filters
{
    public static Filter and(final Filter filter, final Filter... array) {
        return new LogicalFilter(Operator.si, filter, array);
    }
    
    public static Filter and(final List<Filter> list) {
        return new LogicalFilter(Operator.si, list);
    }
    
    public static Filter contains(final MetadataField<String> metadataField, final String s) {
        return new ComparisonFilter<Object>(Operator.sl, metadataField, s);
    }
    
    public static <T> Filter eq(final MetadataField<T> metadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.sd, metadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter greaterThan(final OrderedMetadataField<T> orderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.sg, orderedMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter greaterThanEquals(final OrderedMetadataField<T> orderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.sh, orderedMetadataField, t);
    }
    
    public static <T> Filter in(final CollectionMetadataField<T> collectionMetadataField, final T t) {
        return new InFilter<Object>(collectionMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter lessThan(final OrderedMetadataField<T> orderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.se, orderedMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter lessThanEquals(final OrderedMetadataField<T> orderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.sf, orderedMetadataField, t);
    }
    
    public static Filter not(final Filter filter) {
        return new NotFilter(filter);
    }
    
    public static Filter or(final Filter filter, final Filter... array) {
        return new LogicalFilter(Operator.sj, filter, array);
    }
    
    public static Filter or(final List<Filter> list) {
        return new LogicalFilter(Operator.sj, list);
    }
    
    public static Filter sharedWithMe() {
        return new FieldOnlyFilter(SearchableField.rM);
    }
}
