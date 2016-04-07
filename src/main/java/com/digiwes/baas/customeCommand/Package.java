package com.digiwes.baas.customeCommand;

/**
 * Created by zhaoyp on 2016/4/6.
 */
public class Package {

    private String packageName;
    private String version;
    private String bundleName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }
}
