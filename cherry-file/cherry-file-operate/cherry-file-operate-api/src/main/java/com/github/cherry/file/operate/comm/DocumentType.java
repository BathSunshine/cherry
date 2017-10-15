package com.github.cherry.file.operate.comm;

import com.github.cherry.framework.common.constants.LabeledEnum;

/**
 * 操作文档类型
 *
 * @author Scott
 * @since 1.0
 */
public enum DocumentType implements LabeledEnum<DocumentType> {
    EXCEL03("EXCEL", ".xls"), EXCEL07("EXCEL", ".xlsx"), CSV("CSV", "。csv");

    private String typeName;

    private String extension;

    private DocumentType(String typeName, String extension) {
        this.typeName = typeName;
        this.extension = extension;
    }

    /**
     * typeName.
     *
     * @return the typeName
     * @since 1.0
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @see com.github.cherry.framework.common.constants.LabeledEnum#getLabel()
     */
    @Override
    public String getLabel() {
        return typeName;
    }

    /**
     * extension.
     *
     * @return the extension
     * @since 1.0
     */
    public String getExtension() {
        return extension;
    }

}
