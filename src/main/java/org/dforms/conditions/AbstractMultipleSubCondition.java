package org.dforms.conditions;

import java.util.List;

public abstract class AbstractMultipleSubCondition extends AbstractCondition {
  protected List<Condition> subConditions;

  public List<Condition> getSubConditions () {
    return this.subConditions;
  }

  public void setSubConditions ( List<Condition> sub ) {
    this.subConditions = sub;
  }
}
