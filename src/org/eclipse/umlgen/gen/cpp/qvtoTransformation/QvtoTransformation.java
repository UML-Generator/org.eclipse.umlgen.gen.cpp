/*******************************************************************************
 * Copyright (c) 2016 Daniel Kaminski de Souza and Marcelo Giesteira Campani.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Kaminski de Souza - UML to C++ generator implementation
 *     Marcelo Giesteira Campani - UML to C++ generator implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.cpp.qvtoTransformation;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.umlgen.gen.cpp.Activator;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public class QvtoTransformation {
	URI inputModel;
	File targetFolder;
	public QvtoTransformation(URI givenInputModel, File givenTargetFolder){
		inputModel = givenInputModel;
		targetFolder = givenTargetFolder;
	}
	private ModelExtent createInput(ResourceSet givenResourceSet, URI givenURI){
    	// define the transformation input
    	// Remark: we take the objects from a resource, however
    	// a list of arbitrary in-memory EObjects may be passed
    	//ExecutionContextImpl context = new ExecutionContextImpl();
		
		Resource inResource = givenResourceSet.getResource(
				givenURI, true);		
		EList<EObject> inObjects = inResource.getContents();
		
		// create the input extent with its initial contents
		ModelExtent input = new BasicModelExtent(inObjects);
		return input;
	}
	private ResourceSet resourceSetCreation(){
		ResourceSet resourceSet = new ResourceSetImpl();
		
		final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(resourceSet.getPackageRegistry());
		resourceSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);	
		
		//ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put("http://www.eclipse.org/uml2/5.0.0/UML", UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl()); 
		return resourceSet;
	}
	private URI getPluginURI (Bundle givenBundle, String givenString) throws URISyntaxException, IOException{
    	URL A = givenBundle.getEntry(givenString);
    	java.net.URI B = FileLocator.resolve(A).toURI();
    	return URI.createURI(B.toString());
	}
	public URI execute(Activator activator) throws IOException, URISyntaxException {
    	// Refer to an existing transformation via URI
    	Bundle bundle = activator.getBundle();
    	
    	URI transformationURI = getPluginURI(bundle, "/src/org/eclipse/acceleo/module/cppGenerator/qvtoTransformation/StateMachine_ClassDiagram.qvto");
    	
		// create executor for the given transformation
    	TransformationExecutor executor = new TransformationExecutor(transformationURI);
  	
		ResourceSet resourceSet = resourceSetCreation();
    	
    	// create the input extent with its initial contents
    	ModelExtent input = createInput(resourceSet, inputModel);
  	
    	URI stateMachineMetaModelURI = getPluginURI(bundle, "/src/org/eclipse/acceleo/module/cppGenerator/qvtoTransformation/stateMachineMetaModel.uml"); 
    	
    	// create the input extent with its initial contents
    	ModelExtent input2 = createInput(resourceSet, stateMachineMetaModelURI);  	
    	
    	// create an empty extent to catch the output
    	ModelExtent output = new BasicModelExtent();

    	// setup the execution environment details -> 
    	// configuration properties, logger, monitor object etc.
    	ExecutionContextImpl context = new ExecutionContextImpl();
    	context.setConfigProperty("keepModeling", true);
 	
    	// run the transformation assigned to the executor with the given 
    	// input and output and execution context -> ChangeTheWorld(in, out)
    	// Remark: variable arguments count is supported
    	ExecutionDiagnostic result = executor.execute(context, input, input2, output);
 	
    	// check the result for success
    	if(result.getSeverity() == Diagnostic.OK) {
    		// the output objects got captured in the output extent
    		List<EObject> outObjects = output.getContents();
    		// let's persist them using a resource 
    		//ResourceSet resourceSet2 = resourceSetCreation();
    		URI outputURI = URI.createFileURI(targetFolder.getPath()+"/../OutputModel.uml");
    		Resource outResource = resourceSet.createResource(outputURI);
    		outResource.getContents().addAll(outObjects);
    		outResource.save(Collections.emptyMap());

    		return outputURI;
    	} else {
    		// turn the result diagnostic into status and send it to error log			
    		IStatus status = BasicDiagnostic.toIStatus(result);
    		
    		activator.getLog().log(status);
    		return null;
    	}
    }
}
