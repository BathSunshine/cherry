package com.github.cherry.file.client.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The {@link FileListFilter} implementation to filter those files which {@link File#lastModified()} is less than the
 * {@link #age} in comparison
 * with the current time.
 * <p>
 * The resolution is done in seconds.
 *
 *
 */
public class LastModifiedFileListFilter implements FileListFilter<File> {

    private static final long DEFAULT_AGE = 60;

    private volatile long age = DEFAULT_AGE;

    public long getAge() {
        return this.age;
    }

    /**
     * Set the age that files have to be before being passed by this filter.
     * If {@link File#lastModified()} plus age is greater than the current time, the file
     * is filtered. The resolution is seconds.
     * Defaults to 60 seconds.
     * 
     * @param age the age
     */
    public void setAge(long age) {
        setAge(age, TimeUnit.SECONDS);
    }

    /**
     * Set the age that files have to be before being passed by this filter.
     * If {@link File#lastModified()} plus age is greater than the current time, the file
     * is filtered. The resolution is seconds.
     * Defaults to 60 seconds.
     * 
     * @param age the age
     * @param unit the timeUnit.
     */
    public void setAge(long age, TimeUnit unit) {
        this.age = unit.toSeconds(age);
    }

    @Override
    public List<File> filterFiles(File[] files) {
        List<File> list = new ArrayList<File>();
        long now = System.currentTimeMillis() / 1000;
        for (File file : files) {
            if (file.lastModified() / 1000 + this.age <= now) {
                list.add(file);
            }
        }
        return list;
    }

}
