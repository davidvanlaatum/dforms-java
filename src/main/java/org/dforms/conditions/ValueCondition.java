package org.dforms.conditions;

public class ValueCondition extends AbstractCondition {
  private Object value;

  public Object getValue () {
    return value;
  }

  public ValueCondition setValue ( Object value ) {
    this.value = value;
    return this;
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    return value;
  }
}
