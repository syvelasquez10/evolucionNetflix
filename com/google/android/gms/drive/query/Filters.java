// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.FieldOnlyFilter;
import com.google.android.gms.drive.query.internal.NotFilter;
import com.google.android.gms.drive.query.internal.InFilter;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.query.internal.ComparisonFilter;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.Operator;

public class Filters
{
    public static Filter and(final Filter filter, final Filter... array) {
        return new LogicalFilter(Operator.GZ, filter, array);
    }
    
    public static Filter and(final Iterable<Filter> iterable) {
        return new LogicalFilter(Operator.GZ, iterable);
    }
    
    public static Filter contains(final SearchableMetadataField<String> searchableMetadataField, final String s) {
        return new ComparisonFilter<Object>(Operator.Hc, searchableMetadataField, s);
    }
    
    public static <T> Filter eq(final SearchableMetadataField<T> searchableMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.GU, searchableMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter greaterThan(final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.GX, searchableOrderedMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter greaterThanEquals(final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.GY, searchableOrderedMetadataField, t);
    }
    
    public static <T> Filter in(final SearchableCollectionMetadataField<T> searchableCollectionMetadataField, final T t) {
        return new InFilter<Object>(searchableCollectionMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter lessThan(final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.GV, searchableOrderedMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter lessThanEquals(final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, final T t) {
        return new ComparisonFilter<Object>(Operator.GW, searchableOrderedMetadataField, t);
    }
    
    public static Filter not(final Filter filter) {
        return new NotFilter(filter);
    }
    
    public static Filter or(final Filter filter, final Filter... array) {
        return new LogicalFilter(Operator.Ha, filter, array);
    }
    
    public static Filter or(final Iterable<Filter> iterable) {
        return new LogicalFilter(Operator.Ha, iterable);
    }
    
    public static Filter sharedWithMe() {
        return new FieldOnlyFilter(SearchableField.GE);
    }
}
