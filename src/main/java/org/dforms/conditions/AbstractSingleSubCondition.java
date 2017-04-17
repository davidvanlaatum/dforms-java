package org.dforms.conditions;

public abstract class AbstractSingleSubCondition extends AbstractCondition {
  protected Condition subCondition;

  public Condition getSubCondition () {
    return subCondition;
  }

  public AbstractSingleSubCondition setSubCondition ( Condition subCondition ) {
    this.subCondition = subCondition;
    return this;
  }
}
