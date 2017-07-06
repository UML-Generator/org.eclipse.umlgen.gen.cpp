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
import java.util.*;

import org.eclipse.umlgen.gen.cpp.main.Instance;
import org.eclipse.umlgen.gen.cpp.main.NonCompositeAssociation;
//import org.eclipse.emf.ecore.EObject;
public class Utility{
	private static ArrayList<String> includeList;
	
	private static Integer currentInstance;

	private static ArrayList<NonCompositeAssociation> nonCompositeList;	
	
	private static ArrayList<Instance> InstanceList;
	private static ArrayList<Object> definedInstancesList;
	
	public Utility(){
		currentInstance = -1;
		includeList = new ArrayList<String>();
	
		InstanceList = new ArrayList<Instance>();
		definedInstancesList = new ArrayList<Object>();
		
		nonCompositeList = new ArrayList<NonCompositeAssociation>();
	}

	
   /**********************************
	* 	INCLUDE LIST				 *
	**********************************/			
	public boolean addInclude(String includeString) {
		if (!includeList.contains(includeString)){
			includeList.add(includeString);
			return true;
		}
		return false;
	}
	public ArrayList<String> getIncludeList() {
		return includeList;
	}
	
   /**********************************
	* 	DEFINED INSTANCES			 *
	**********************************/		
	public boolean addDefinedInstance(Object givenInstance) {
		return  definedInstancesList.add(givenInstance);
	}

	public ArrayList<Object> getDefinedInstancesList() {
		return definedInstancesList;
	}	
	
	
   /**********************************
	* 	NON COMPOSITE ASSOCIATION	 *
	**********************************/	
	public ArrayList<String> getInstances(){
		ArrayList<String> returnList = new ArrayList<String>();
		
		Iterator<Instance> itr = InstanceList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().InstanceString);
		 }
		return returnList;
	}
	public ArrayList<String> getInstancePointersList() {
		ArrayList<String> returnList = new ArrayList<String>();
		
		Iterator<Instance> itr = InstanceList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().InstancePointer);
		 }
		return returnList;
	}		
	public ArrayList<String> getClassStringList() {
		ArrayList<String> returnList = new ArrayList<String>();
		
		Iterator<Instance> itr = InstanceList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().ClassString);
		 }
		return returnList;
	}		
	public ArrayList<Object> getClassList() {
		ArrayList<Object> returnList = new ArrayList<Object>();
		
		Iterator<Instance> itr = InstanceList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().itsClass);
		 }
		return returnList;		
	}			
	public ArrayList<Object> getInstanceList() {
		ArrayList<Object> returnList = new ArrayList<Object>();
		
		Iterator<Instance> itr = InstanceList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().instance);
		 }
		return returnList;		
	}	
	public ArrayList<String> getInstantiationStringList() {
		ArrayList<String> returnList = new ArrayList<String>();
		
		Iterator<Instance> itr = InstanceList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().instantiationString);
		 }
		return returnList;		
	}			
	
	public boolean addInstance(Object givenInstance, String givenString, Object givenClass){
		currentInstance++;
		return InstanceList.add(new Instance(givenInstance, givenString, givenClass));
	}
	public boolean addInstancePointer(String givenString){
		InstanceList.get(currentInstance).InstancePointer = givenString;
		return true;
	}
	public boolean addClass(String givenString){
		InstanceList.get(currentInstance).ClassString = givenString;
		return true;
	}	
	
	public boolean addInstantiationString(String givenString){
		InstanceList.get(currentInstance).instantiationString = givenString;
		return true;
	}
	
   /**********************************
	* 	NON COMPOSITE ASSOCIATION	 *
	**********************************/
	public boolean addNonCompositeAssociation(Object givenInstance, String givenString){
		return nonCompositeList.add(new NonCompositeAssociation(givenInstance, givenString, givenInstance));
	}		
	public boolean addNonCompositeAssociation(Object givenInstance, String givenString, Object givenProperty){
		return nonCompositeList.add(new NonCompositeAssociation(givenInstance, givenString, givenProperty));
	}		
	
	public ArrayList<Object> getNonCompositeAssociationList() {
		ArrayList<Object> returnList = new ArrayList<Object>();
		
		Iterator<NonCompositeAssociation> itr = nonCompositeList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().instance);
		 }
		return returnList;		
	}
	public ArrayList<String> getNonCompositeAssociationPath() {
		ArrayList<String> returnList = new ArrayList<String>();
		
		Iterator<NonCompositeAssociation> itr = nonCompositeList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().path);
		 }
		return returnList;		
	}
	public ArrayList<Object> getNonCompositeAssociationProperty() {
		ArrayList<Object> returnList = new ArrayList<Object>();
		
		Iterator<NonCompositeAssociation> itr = nonCompositeList.iterator();
		 while (itr.hasNext()){
			returnList.add(itr.next().property);
		 }
		return returnList;		
	}		
}