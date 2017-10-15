package com.github.cherry.file.client.filters;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * {@link FileListFilter} that passes files only one time. This can
 * conveniently be used to prevent duplication of files, as is done in
 * {@link org.springframework.integration.file.FileReadingMessageSource}.
 * <p>
 * This implementation is thread safe.
 *
 */
public class AcceptOnceFileListFilter<F> extends AbstractFileListFilter<F> implements ReversibleFileListFilter<F>,
        ResettableFileListFilter<F> {

    private final Queue<F> seen;

    private final Set<F> seenSet = new HashSet<F>();

    private final Object monitor = new Object();

    /**
     * Creates an AcceptOnceFileListFilter that is based on a bounded queue. If the queue overflows,
     * files that fall out will be passed through this filter again if passed to the {@link #filterFiles(Object[])}
     *
     * @param maxCapacity the maximum number of Files to maintain in the 'seen' queue.
     */
    public AcceptOnceFileListFilter(int maxCapacity) {
        this.seen = new LinkedBlockingQueue<F>(maxCapacity);
    }

    /**
     * Creates an AcceptOnceFileListFilter based on an unbounded queue.
     */
    public AcceptOnceFileListFilter() {
        this.seen = null;
    }

    @Override
    public boolean accept(F file) {
        synchronized (this.monitor) {
            if (this.seenSet.contains(file)) {
                return false;
            }
            if (this.seen != null) {
                if (!this.seen.offer(file)) {
                    F removed = this.seen.poll();
                    this.seenSet.remove(removed);
                    this.seen.add(file);
                }
            }
            this.seenSet.add(file);
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback(F file, List<F> files) {
        synchronized (this.monitor) {
            boolean rollingBack = false;
            for (F fileToRollback : files) {
                if (fileToRollback.equals(file)) {
                    rollingBack = true;
                }
                if (rollingBack) {
                    remove(fileToRollback);
                }
            }
        }
    }

    @Override
    public boolean remove(F fileToRemove) {
        boolean removed = this.seenSet.remove(fileToRemove);
        if (this.seen != null) {
            this.seen.remove(fileToRemove);
        }
        return removed;
    }

}
