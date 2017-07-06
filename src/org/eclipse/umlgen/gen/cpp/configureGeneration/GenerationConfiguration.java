package org.eclipse.umlgen.gen.cpp.configureGeneration;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

public class GenerationConfiguration {

	private Object objectToNotify;
	
	public boolean receipt = false;
	
	protected Shell shlCodeGenerationOptions;
	
	public String memoryLeakTest;
	public String compiler;
	
	private CCombo compilerCombo;
	private CCombo memoryLeakCombo;
	
	public GenerationConfiguration(Object givenObjectToNotify) {
		objectToNotify = givenObjectToNotify;
		this.open();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCodeGenerationOptions.open();
		shlCodeGenerationOptions.layout();
		while (!shlCodeGenerationOptions.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCodeGenerationOptions = new Shell(SWT.CLOSE |SWT.ON_TOP);
		shlCodeGenerationOptions.setBounds(calculateShellBounds());
		shlCodeGenerationOptions.setSize(290, 168);
		shlCodeGenerationOptions.setText("Code Generation Options");
		shlCodeGenerationOptions.setLayout(new GridLayout(2, false));
		new Label(shlCodeGenerationOptions, SWT.NONE);
		new Label(shlCodeGenerationOptions, SWT.NONE);
		
		Label lblCompiler = new Label(shlCodeGenerationOptions, SWT.NONE);
		lblCompiler.setText("Compiler");
		
		compilerCombo = new CCombo(shlCodeGenerationOptions, SWT.BORDER);
		compilerCombo.add("Mingw");
		compilerCombo.add("Codewarrior");
		GridData gd_compilerCombo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_compilerCombo.widthHint = 230;
		compilerCombo.setLayoutData(gd_compilerCombo);
		
		Label lblMemoryLeakTest = new Label(shlCodeGenerationOptions, SWT.NONE);
		GridData gd_lblMemoryLeakTest = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblMemoryLeakTest.widthHint = 113;
		lblMemoryLeakTest.setLayoutData(gd_lblMemoryLeakTest);
		lblMemoryLeakTest.setText("Memory Leak Test");
		
		memoryLeakCombo = new CCombo(shlCodeGenerationOptions, SWT.BORDER);
		memoryLeakCombo.add("Off");
		memoryLeakCombo.add("On");
		GridData gd_memoryLeakCombo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_memoryLeakCombo.widthHint = 230;
		memoryLeakCombo.setLayoutData(gd_memoryLeakCombo);
		new Label(shlCodeGenerationOptions, SWT.NONE);
		new Label(shlCodeGenerationOptions, SWT.NONE);
		new Label(shlCodeGenerationOptions, SWT.NONE);
		
		Button btnGenerate = new Button(shlCodeGenerationOptions, SWT.NONE);
		btnGenerate.setText("Generate");
		
		btnGenerate.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				receipt = true;
				synchronized (objectToNotify) {
					objectToNotify.notify();
					compiler = compilerCombo.getItem(compilerCombo.getSelectionIndex()).toUpperCase();
					memoryLeakTest = memoryLeakCombo.getItem(memoryLeakCombo.getSelectionIndex()).toUpperCase();
					shlCodeGenerationOptions.dispose();
				}		
				
			}
		});		

	}
	/**
	 * @return
	 */
	private Rectangle calculateShellBounds(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
		int w = 350;
		int h = 220;
        int x = (dim.width-w+1)/2;
        int y = (dim.height-h+1)/2;	
		return new Rectangle(x, y, w, h);
	}
}
