package com.github.cherry.file.operate.file;

import java.io.File;

import org.apache.commons.io.FileCleaningTracker;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class DiskFileItemFactory implements FileItemFactory {

    // ----------------------------------------------------- Manifest constants

    /**
     * The default threshold above which uploads will be stored on disk.
     */
    public static final int DEFAULT_SIZE_THRESHOLD = 10240;

    // ----------------------------------------------------- Instance Variables

    /**
     * The directory in which uploaded files will be stored, if stored on disk.
     */
    private File repository;

    /**
     * The threshold above which uploads will be stored on disk.
     */
    private int sizeThreshold = DEFAULT_SIZE_THRESHOLD;

    /**
     * <p>
     * The instance of {@link FileCleaningTracker}, which is responsible for deleting temporary files.
     * </p>
     * <p>
     * May be null, if tracking files is not required.
     * </p>
     */
    private FileCleaningTracker fileCleaningTracker;

    // ----------------------------------------------------------- Constructors

    /**
     * Constructs an unconfigured instance of this class. The resulting factory
     * may be configured by calling the appropriate setter methods.
     */
    public DiskFileItemFactory() {
        this(DEFAULT_SIZE_THRESHOLD, null);
    }

    /**
     * Constructs a preconfigured instance of this class.
     *
     * @param sizeThreshold The threshold, in bytes, below which items will be
     * retained in memory and above which they will be
     * stored as a file.
     * @param repository The data repository, which is the directory in
     * which files will be created, should the item size
     * exceed the threshold.
     */
    public DiskFileItemFactory(int sizeThreshold, File repository) {
        this.sizeThreshold = sizeThreshold;
        this.repository = repository;
    }

    // ------------------------------------------------------------- Properties

    /**
     * Returns the directory used to temporarily store files that are larger
     * than the configured size threshold.
     *
     * @return The directory in which temporary files will be located.
     *
     * @see #setRepository(java.io.File)
     *
     */
    public File getRepository() {
        return repository;
    }

    /**
     * Sets the directory used to temporarily store files that are larger
     * than the configured size threshold.
     *
     * @param repository The directory in which temporary files will be located.
     *
     * @see #getRepository()
     *
     */
    public void setRepository(File repository) {
        this.repository = repository;
    }

    /**
     * Returns the size threshold beyond which files are written directly to
     * disk. The default value is 10240 bytes.
     *
     * @return The size threshold, in bytes.
     *
     * @see #setSizeThreshold(int)
     */
    public int getSizeThreshold() {
        return sizeThreshold;
    }

    /**
     * Sets the size threshold beyond which files are written directly to disk.
     *
     * @param sizeThreshold The size threshold, in bytes.
     *
     * @see #getSizeThreshold()
     *
     */
    public void setSizeThreshold(int sizeThreshold) {
        this.sizeThreshold = sizeThreshold;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Create a new {@link org.apache.commons.fileupload.disk.DiskFileItem} instance from the supplied parameters and
     * the local factory
     * configuration.
     *
     * @param fieldName The name of the form field.
     * @param contentType The content type of the form field.
     * @param isFormField <code>true</code> if this is a plain form field; <code>false</code> otherwise.
     * @param fileName The name of the uploaded file, if any, as supplied
     * by the browser or other client.
     *
     * @return The newly created file item.
     */
    public FileItem createItem(String contentType, String fileName) {
        DiskFileItem result = new DiskFileItem(contentType, fileName, sizeThreshold, repository);
        FileCleaningTracker tracker = getFileCleaningTracker();
        if (tracker != null) {
            tracker.track(result.getTempFile(), result);
        }
        return result;
    }

    /**
     * Returns the tracker, which is responsible for deleting temporary
     * files.
     *
     * @return An instance of {@link FileCleaningTracker}, or null
     * (default), if temporary files aren't tracked.
     */
    public FileCleaningTracker getFileCleaningTracker() {
        return fileCleaningTracker;
    }

    /**
     * Sets the tracker, which is responsible for deleting temporary
     * files.
     *
     * @param pTracker An instance of {@link FileCleaningTracker},
     * which will from now on track the created files, or null
     * (default), to disable tracking.
     */
    public void setFileCleaningTracker(FileCleaningTracker pTracker) {
        fileCleaningTracker = pTracker;
    }

}
