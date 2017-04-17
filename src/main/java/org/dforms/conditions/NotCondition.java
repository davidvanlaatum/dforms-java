package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotCondition extends AbstractSingleSubCondition {
  @Override
  @JsonProperty ( "not" )
  public Condition getSubCondition () {
    return super.getSubCondition ();
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    return !isTrue ( subCondition.evaluate ( context ) );
  }
}
