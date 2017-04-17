package org.dforms.data;

import org.dforms.fields.Field;

import java.text.MessageFormat;

public class ValidationError {
  private Field field;
  private String error;

  public ValidationError ( Field field, String error ) {
    this.field = field;
    this.error = error;
  }

  public Field getField () {
    return field;
  }

  public String getError () {
    return error;
  }

  @Override
  public String toString () {
    return MessageFormat.format ( "{0}: {1}", field.getLabelPath (), error );
  }
}
