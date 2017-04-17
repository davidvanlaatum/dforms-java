package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GreaterThanCondition extends AbstractRelativeCondition {
  @Override
  @JsonProperty ( "greaterthan" )
  public List<Condition> getSubConditions () {
    return super.getSubConditions ();
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    final Object first = subConditions.get ( 0 ).evaluate ( context );
    final Object second = subConditions.get ( 1 ).evaluate ( context );
    return isGreater ( first, second );
  }

}
