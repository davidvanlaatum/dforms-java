package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AndCondition extends AbstractMultipleSubCondition {

  @Override
  @JsonProperty ( "and" )
  public List<Condition> getSubConditions () {
    return super.getSubConditions ();
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    boolean rt = true;

    for ( Condition condition : subConditions ) {
      if ( !isTrue ( condition.evaluate ( context ) ) ) {
        rt = false;
        break;
      }
    }

    return rt;
  }

}
