package com.github.cherry.file.operate.comm;

import com.github.cherry.framework.common.constants.LabeledEnum;

/**
 * 文档操作类型
 *
 * @author Scott
 * @since 1.0
 */
public enum FileOperateMode implements LabeledEnum<FileOperateMode> {
    IMPORT("importFile"), EXPORT("exportFile");

    private String label;

    private FileOperateMode(String label) {
        this.label = label;
    }

    /**
     * @see com.github.cherry.framework.common.constants.LabeledEnum#getLabel()
     */
    @Override
    public String getLabel() {
        return this.label;
    }

}
