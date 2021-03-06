/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.widgets.client.ui.forms;

import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.pagefield.AbstractPageField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.IAdvancedExampleForm;
import org.eclipse.scout.widgets.client.ui.desktop.pages.PageWithDetailFormTablePage;
import org.eclipse.scout.widgets.client.ui.forms.PageFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widgets.client.ui.forms.PageFieldForm.MainBox.DetailBox.PageBox;
import org.eclipse.scout.widgets.client.ui.forms.PageFieldForm.MainBox.GroupBoxPropertiesBox;
import org.eclipse.scout.widgets.client.ui.template.formfield.AbstractFormFieldPropertiesBox;
import org.eclipse.scout.widgets.client.ui.template.formfield.AbstractGroupBoxPropertiesBox;

public class PageFieldForm extends AbstractForm implements IAdvancedExampleForm {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PageField");
  }

  @Override
  public void startPageForm() {
    startInternal(new PageFormHandler());
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public GroupBoxPropertiesBox getGroupBoxPropertiesBox() {
    return getFieldByClass(GroupBoxPropertiesBox.class);
  }

  public PageBox getPageBox() {
    return getFieldByClass(PageBox.class);
  }

  @Order(10)
  public class MainBox extends AbstractGroupBox {

    @Order(100)
    public class DetailBox extends AbstractGroupBox {

      @Order(10)
      public class PageBox extends AbstractPageField<PageWithDetailFormTablePage> {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execInitField() {
          setPage(new PageWithDetailFormTablePage());
          ((DetailForm) getPage().getDetailForm()).getNavigationButtonInfoField().setVisible(false);
        }
      }

    }

    @Order(150)
    @ClassId("3dcb783c-9171-4e4c-98c0-7d8ad899b299")
    public class GroupBoxPropertiesBox extends AbstractGroupBoxPropertiesBox {

      @Override
      protected void execInitField() {
        setGroupBox(getPageBox());
        // Fields inside the page field are configured to use FULL_WIDTH -> changing the grid column count would not have any effect
        getGridColumnCountField().setVisible(false);
      }

    }

    @ClassId("8674389c-6636-4752-9c72-10249de56dc4")
    @Order(200)
    public class FormFieldPropertiesBox extends AbstractFormFieldPropertiesBox {

      @Override
      protected void execInitField() {
        setFormField(getPageBox());
      }
    }

    @Order(300)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
