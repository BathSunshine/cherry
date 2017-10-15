package com.github.cherry.file.client.filters;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.github.cherry.framework.common.utils.Assert;

/**
 * The {@link CompositeFileListFilter} extension which chains the result
 * of the previous filter to the next one. If a filter in the chain returns
 * an empty list, the remaining filters are not invoked.
 *
 * @param <F> The type that will be filtered.
 * 
 * @author Scott
 * @since 1.0
 */
public class ChainFileListFilter<F> extends CompositeFileListFilter<F> {

    @Override
    public List<F> filterFiles(F[] files) {
        Assert.notNull(files, "'files' should not be null");
        List<F> leftOver = Arrays.asList(files);
        for (FileListFilter<F> fileFilter : this.fileFilters) {
            if (leftOver.size() == 0) {
                break;
            }
            @SuppressWarnings("unchecked")
            F[] fileArray = leftOver.toArray((F[]) Array.newInstance(leftOver.get(0).getClass(), leftOver.size()));
            leftOver = fileFilter.filterFiles(fileArray);
        }
        return leftOver;
    }

}
