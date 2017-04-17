package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrCondition extends AbstractMultipleSubCondition {
  @Override
  @JsonProperty ( "or" )
  public List<Condition> getSubConditions () {
    return super.getSubConditions ();
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    boolean rt = false;

    for ( Condition condition : subConditions ) {
      if ( isTrue ( condition.evaluate ( context ) ) ) {
        rt = true;
        break;
      }
    }

    return rt;
  }
}
