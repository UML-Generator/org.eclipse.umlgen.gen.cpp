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
package org.eclipse.umlgen.gen.cpp.main;

public class NonCompositeAssociation {
	public Object instance;
	public Object property;
	public String path;
	
	public NonCompositeAssociation(Object givenAssociation, String givenAssociationPath){
		path = givenAssociationPath;
		instance = givenAssociation;
	}

	public NonCompositeAssociation(Object givenAssociation, String givenAssociationPath, Object givenProperty){
		path = givenAssociationPath;
		instance = givenAssociation;
		property = givenProperty;
	}
}
