package com.digiwes.baas.customeCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoyp on 2016/4/6.
 */
public class PackageAnalyzer {

    public void initPackage(String exprotPackages, String bundlename, Map<String,List<Package>> exportPackageMap){

        exprotPackages = replaceValueByRegx(";\\s*uses\\s*:\\s*=\\s*\\\".*?\\\"","",exprotPackages);
        exprotPackages =replaceValueByRegx(";\\s*x-internal\\s*:\\s*=\\s*\\w*","",exprotPackages);
        exprotPackages = replaceValueByRegx(";\\s*x-friends\\s*:\\s*=\\s*\\\".*?\\\"","",exprotPackages);
        exprotPackages = replaceValueByRegx(";\\s*provider\\s*=\\s*\\w*","",exprotPackages);
        String packageArray[] = exprotPackages.split(",");
        for(String packagesInfo : packageArray){
            Package packageInfo = new Package();
            packageInfo.setBundleName(bundlename);
            String exportPackageName = packagesInfo;
            if(packagesInfo.indexOf(";")!=-1){
                String[] packageInfoArray = packagesInfo.split(";");
                packageInfo.setPackageName(packageInfoArray[0]);
                String version = getValueByRegx("\\\"[^\\\"]*\\\"",packageInfoArray[1]);
                version.replaceAll("\"","");
                packageInfo.setVersion(version);
                exportPackageName = packageInfoArray[0];
            }else{
                packageInfo.setPackageName(packagesInfo);
            }
            if( null != exportPackageMap.get(exportPackageName)){
               List<Package> packages =  exportPackageMap.get(exportPackageName);
               packages.add(packageInfo);
               exportPackageMap.put(exportPackageName,packages);
            }else {
                List<Package> packages = new ArrayList<Package>();
                packages.add(packageInfo);
                exportPackageMap.put(exportPackageName,packages);
            }
        }
    }

    public String replaceValueByRegx(String expression,String replaceValue,String str){
        Pattern usesPattern = Pattern.compile(expression);
        Matcher matcher=usesPattern.matcher(str);
        if(matcher.find()){
            str = matcher.replaceAll(replaceValue);
        }
        return str;
    }
    public String getValueByRegx(String expression,String str){
        Pattern versionPattern = Pattern.compile(expression);
        Matcher matcher =  versionPattern.matcher(str);
        String value ="";
        if(matcher.find()){
            value =  matcher.group();
        }
        return value;
    }


}
