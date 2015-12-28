package com.json;

import java.io.Serializable;

/**
 * Created by liming on 15/4/21.
 */
public class TreeBean implements Serializable{

    //treeId
    String id;
    //父Id
    String pId;
    //节点名称
    String name;
    //是否打开
    String open;
    //是否有子节点
    String isParent;
    //图标
    String iconSkin;
    //资源类型
    String rsType;


    public TreeBean(String id, String pId, String name, String open, String isParent,String rsType) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.open = open;
        this.isParent = isParent;
        this.rsType = rsType;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }
}
