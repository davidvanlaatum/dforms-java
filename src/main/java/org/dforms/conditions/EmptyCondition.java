package org.dforms.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

public class EmptyCondition extends AbstractSingleSubCondition {
  @Override
  @JsonProperty ( "empty" )
  public Condition getSubCondition () {
    return super.getSubCondition ();
  }

  @Override
  public Object evaluate ( EvaluationContext context ) {
    final Object value = subCondition.evaluate ( context );
    return value == null || value instanceof String && StringUtils.isEmpty ( (String) value );
  }
}
