/*******************************************************************************
 * Copyright (c) 2008, 2011, 2016 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *     Daniel Kaminski de Souza - UML to C++ generator implementation
 *     Marcelo Giesteira Campani - UML to C++ generator implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.cpp;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.eclipse.core.runtime.Status;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends Plugin {
    private int i = 0;
    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.eclipse.umlgen.gen.cpp";

    /**
     * The shared instance.
     */
    private static Activator plugin;
    
    /**
     * The constructor.
     */
    public Activator() {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    
    public boolean log(String msg, Integer status) {
    	log(msg, null, status);
        return true;
     }
    public boolean log(String msg, Exception e, Integer severity) {
       getLog().log(new Status(severity, PLUGIN_ID, Status.OK, ++i+" "+msg, e));
       return true;
    }    
    public boolean stop(String msg) {
    	try{
    		stop((this.getBundle()).getBundleContext());
    		log(msg, Status.OK);
    	}catch(Exception e){
    		log ("StopException", e, Status.ERROR);
        }
    	return true;
     }
}
