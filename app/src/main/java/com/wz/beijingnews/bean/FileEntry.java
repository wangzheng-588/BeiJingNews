package com.wz.beijingnews.bean;

import java.io.Serializable;

/**
 * Created by wz on 17-6-8.
 */

public class FileEntry  implements Serializable{

    private boolean isFile;//是文件还是目录
    private String path;
    private String parent;
    private boolean isCheck;
    private int childSize;//目录下有多少个文件


    public FileEntry() {
    }

    public FileEntry(boolean isFile, String path) {
        this.isFile = isFile;
        this.path = path;
    }

    public FileEntry(boolean isFile, String path, String parent) {
        this.isFile = isFile;
        this.path = path;
        this.parent = parent;

    }

    public FileEntry(boolean isFile, String path, String parent, int childSize) {
        this.isFile = isFile;
        this.path = path;
        this.parent = parent;
        this.childSize = childSize;
    }

    public int getChildSize() {
        return childSize;
    }

    public void setChildSize(int childSize) {
        this.childSize = childSize;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
