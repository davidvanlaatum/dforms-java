package org.dforms.data;

import org.dforms.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class ValidationContextImpl extends AbstractValidationContext {
  private List<ValidationError> errors = new ArrayList<> ();

  public ValidationContextImpl ( DataFieldContainer container, ValidationContext parent ) {
    super ( container, parent );
  }

  @Override
  public ValidationContext contextForContainer ( DataFieldContainer container ) {
    return new ValidationContextImpl.SubContext ( container, this );
  }

  @Override
  public boolean isValid () {
    return errors.isEmpty ();
  }

  @Override
  public void addError ( Field field, String error ) {
    errors.add ( new ValidationError ( field, error ) );
  }

  @Override
  public List<ValidationError> getErrors () {
    return errors;
  }

  @Override
  public String toString () {
    StringBuilder rt = new StringBuilder ();

    for ( ValidationError error : errors ) {
      rt.append ( error ).append ( "\n" );
    }

    return rt.toString ().trim ();
  }

  class SubContext extends AbstractValidationContext {
    SubContext ( DataFieldContainer container, ValidationContext parent ) {
      super ( container, parent );
    }

    @Override
    public boolean isValid () {
      return errors.isEmpty ();
    }

    @Override
    public void addError ( Field field, String error ) {
      errors.add ( new ValidationError ( field, error ) );
    }

    @Override
    public List<ValidationError> getErrors () {
      return errors;
    }

    @Override
    public ValidationContext contextForContainer ( DataFieldContainer container ) {
      return new SubContext ( container, this );
    }
  }
}
