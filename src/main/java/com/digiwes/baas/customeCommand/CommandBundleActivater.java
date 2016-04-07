package com.digiwes.baas.customeCommand;

import com.sun.jndi.toolkit.ctx.ComponentContext;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyp-pc on 2016/3/30.
 */
public class CommandBundleActivater implements BundleActivator {
    public static Map<String,List<Package>> exportPackageMap ;
    public void start(BundleContext bundleContext) throws Exception {
        packageAnalyzer(bundleContext);
        DupPackagesCommand.bundleContext = bundleContext;
        bundleContext.registerService(CommandProvider.class.getName(),new DupPackagesCommand(), null);
    }

    public void  packageAnalyzer(BundleContext bundleContext){
        exportPackageMap = new HashMap<String, List<Package>>();
        Bundle[] bundles = bundleContext.getBundles();
        PackageAnalyzer packageAnalyzer = new PackageAnalyzer();
        for (Bundle bundle :bundles){
            Dictionary<String, String> headers = bundle.getHeaders();
            String export_package =  headers.get("Export-Package");
            if(null != export_package && !export_package.equals("")){
                packageAnalyzer.initPackage(export_package,bundle.getSymbolicName(),exportPackageMap);
            }
        }
    }


    public void stop(BundleContext bundleContext) throws Exception {

    }
}
