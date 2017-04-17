package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class EqualsCondition extends AbstractMultipleSubCondition {
  @Override
  @JsonProperty ( "equals" )
  public List<Condition> getSubConditions () {
    return super.getSubConditions ();
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    boolean rt = true;
    final Object first = subConditions.get ( 0 ).evaluate ( context );
    for ( int i = 1; i < subConditions.size (); i++ ) {
      final Object second = subConditions.get ( i ).evaluate ( context );
      if ( !Objects.equals ( first, second ) ) {
        rt = false;
        break;
      }
    }
    return rt;
  }
}
