package org.dforms.data;

import org.dforms.conditions.ExpressionState;
import org.dforms.fields.Field;

import java.util.List;

public interface ValidationContext {
  boolean isValid ();

  void addError ( Field field, String error );

  List<ValidationError> getErrors ();

  DataFieldContainer getContainer ();

  ValidationContext contextForContainer ( DataFieldContainer container );

  ExpressionState getExpression ( String name );

  AbstractValidationContext.EvaluationContext getEvaluationContext ();
}
