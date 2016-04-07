package com.digiwes.baas.customeCommand;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.eclipse.osgi.framework.internal.core.ExportedPackageImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.service.packageadmin.ExportedPackage;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyp-pc on 2016/3/30.
 */
public class DupPackagesCommand implements CommandProvider {
    public static  BundleContext bundleContext ;
    public String getHelp() {
        return "\n_rePackages  List all bundle which have duplicated packages";
    }
    public void _dupPackages(CommandInterpreter cli){
        for (String key : CommandBundleActivater.exportPackageMap.keySet()) {
           if(CommandBundleActivater.exportPackageMap.get(key).size()>1){
               cli.println("package:"+key);
               for (Package packageinfo : CommandBundleActivater.exportPackageMap.get(key)){
                   cli.print("\tBundle:"+packageinfo.getBundleName());
                   if(null != packageinfo.getVersion() && !"".equals(packageinfo.getVersion())){
                       cli.print("   version:"+packageinfo.getVersion());
                   }
                   cli.println("");
               }
           }
        }
    }

}
