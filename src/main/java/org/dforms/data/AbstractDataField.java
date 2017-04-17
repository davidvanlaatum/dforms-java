package org.dforms.data;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.BooleanUtils;
import org.dforms.conditions.ExpressionState;
import org.dforms.fields.Input;

public abstract class AbstractDataField<T, F extends Input> implements DataField<T> {
  protected F field;
  protected DataFieldContainer parent;
  private T value;

  public AbstractDataField ( T value, F field, DataFieldContainer parent ) {
    this.value = value;
    this.field = field;
    this.parent = parent;
  }

  @Override
  public String getName () {
    return field.getName ();
  }

  public void setName ( String name ) {

  }

  @JsonValue
  public T getValue () {
    return value;
  }

  @Override
  public String getPath () {
    return ( parent != null ? parent.getPath () : "" ) + getName ();
  }

  public String getLabelPath () {
    return ( parent != null ? parent.getLabelPath () : "" ) + field.getLabel ();
  }

  @Override
  public void validate ( ValidationContext context ) {
    if ( BooleanUtils.isTrue ( field.getRequired () ) ) {
      if ( value == null ) {
        context.addError ( field, "is required" );
      }
    }
    if ( field.getValid () != null ) {
      final ExpressionState expression = context.getExpression ( field.getValid () );
      if ( !expression.isTrue () ) {
        if ( expression.getValue () instanceof String ) {
          context.addError ( field, (String) expression.getValue () );
        } else {
          context.addError ( field, "is not valid" );
        }
      }
    }
  }
}
