/**
 * Copyright 2014 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.gwtp.plugin.core.project;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.arcbees.gwtp.plugin.core.project.features.Feature;
import com.arcbees.gwtp.plugin.core.project.features.FeatureCheckStateProvider;
import com.arcbees.gwtp.plugin.core.project.features.FeatureContentProvider;
import com.arcbees.gwtp.plugin.core.project.features.FeatureLabelProvider;
import com.arcbees.gwtp.plugin.core.project.features.Node;

public class AddFeaturesPage extends WizardPage {
    private static final AddFeaturesPage INSTANCE = new AddFeaturesPage();

    private AddFeaturesPage() {
        super("Add Features To Your Project", "Add Features To Your Project", null);
    }

    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        setControl(container);
        container.setLayout(new GridLayout(1, false));

        final CheckboxTreeViewer checkboxTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
        checkboxTreeViewer.setAutoExpandLevel(-1);
        checkboxTreeViewer.setExpandPreCheckFilters(false);
        Tree tree = checkboxTreeViewer.getTree();
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        checkboxTreeViewer.setContentProvider(new FeatureContentProvider());
        checkboxTreeViewer.setLabelProvider(new FeatureLabelProvider());
        checkboxTreeViewer.setCheckStateProvider(new FeatureCheckStateProvider());
        checkboxTreeViewer.setInput("init");

        checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                Node<Feature> featureNode = ((Node<Feature>) event.getElement());
                if (!event.getChecked()) {
                    checkboxTreeViewer.setSubtreeChecked(event.getElement(), false);
                    deselectFeatureNode(featureNode);
                } else {
                    featureNode.getData().setSelected(true);
                }
                FeatureConfigPage.get().showSelectedFeatures();
            }
        });
    }

    private void deselectFeatureNode(Node<Feature> featureNode) {
        featureNode.getData().setSelected(false);
        for (Node<Feature> child: featureNode.getChildren()) {
            deselectFeatureNode(child);
        }
    }

    static AddFeaturesPage get() {
        return INSTANCE;
    }

    private void fillContext(List<Node<Feature>> children, Map<String, Object> context) {
        for (Node<Feature> node: children) {
            if (node.getData().isSelected()) {
                context.put(node.getData().name(), true);
            } else {
                context.remove(node.getData().name());
            }
            fillContext(node.getChildren(), context);
        }
    }

    public void fillContext(Map<String, Object> context) {
        fillContext(Feature.getFeatures().getChildren(), context);
    }
}
