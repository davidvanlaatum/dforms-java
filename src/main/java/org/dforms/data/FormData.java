package org.dforms.data;

import org.dforms.FieldContainer;

public class FormData extends AbstractDataFieldContainer {
  public FormData ( FieldContainer container ) {
    super ( null, container );
  }

  public ValidationContext validate () {
    ValidationContext ctx = new ValidationContextImpl ( this, null );
    validate ( ctx );
    return ctx;
  }

  @Override
  public String getName () {
    return "Form";
  }

  @Override
  public void setName ( String name ) {

  }

  @Override
  public String getPath () {
    return "/";
  }
}
