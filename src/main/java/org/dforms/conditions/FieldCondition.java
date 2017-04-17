package org.dforms.conditions;

public class FieldCondition extends AbstractCondition {
  private String field;

  public String getField () {
    return field;
  }

  public FieldCondition setField ( String field ) {
    this.field = field;
    return this;
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    return context.getDataContext ().getField ( field ).getValue ();
  }
}
