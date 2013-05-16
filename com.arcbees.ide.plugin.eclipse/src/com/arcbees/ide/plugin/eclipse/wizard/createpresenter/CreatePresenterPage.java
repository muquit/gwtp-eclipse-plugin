package com.arcbees.ide.plugin.eclipse.wizard.createpresenter;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.arcbees.ide.plugin.eclipse.domain.PresenterConfigModel;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;

public class CreatePresenterPage extends WizardPage {
    private PresenterConfigModel presenterConfigModel;
    private Text text;

    public CreatePresenterPage(PresenterConfigModel presenterConfigModel) {
        super("wizardPageCreatePresenter");
        
        this.presenterConfigModel = presenterConfigModel;
        
        setTitle("Create Presenter");
        setDescription("Create a presenter for the proejct.");
    }

    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        setControl(container);
        container.setLayout(new GridLayout(1, false));
        
        Label lblName = new Label(container, SWT.NONE);
        lblName.setText("Name: 'MyPresenter'");
        
        text = new Text(container, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        Group grpPresenterType = new Group(container, SWT.NONE);
        grpPresenterType.setLayout(null);
        GridData gd_grpPresenterType = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_grpPresenterType.widthHint = 562;
        grpPresenterType.setLayoutData(gd_grpPresenterType);
        grpPresenterType.setText("Presenter Type");
        
        Button btnNestedPresenter = new Button(grpPresenterType, SWT.RADIO);
        btnNestedPresenter.setSelection(true);
        btnNestedPresenter.setBounds(10, 10, 113, 18);
        btnNestedPresenter.setText("Nested Presenter");
        
        Button btnPresenterWidget = new Button(grpPresenterType, SWT.RADIO);
        btnPresenterWidget.setBounds(143, 10, 112, 18);
        btnPresenterWidget.setText("Presenter Widget");
        
        Button btnPopupPresenter = new Button(grpPresenterType, SWT.RADIO);
        btnPopupPresenter.setBounds(273, 10, 109, 18);
        btnPopupPresenter.setText("Popup Presenter");
        
        Label lblAboutPresenterType = new Label(grpPresenterType, SWT.NONE);
        lblAboutPresenterType.setBounds(10, 33, 542, 14);
        lblAboutPresenterType.setText("About presenter type...");
    }
}
