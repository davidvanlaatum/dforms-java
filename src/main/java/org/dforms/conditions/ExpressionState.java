package org.dforms.conditions;

import org.dforms.Expression;

public class ExpressionState {
  private Expression expression;
  private Object value;
  private boolean evaluated;
  private EvaluationContext context;

  public ExpressionState ( Expression expression, EvaluationContext context ) {
    this.expression = expression;
    this.context = context;
  }

  public Object getValue () {
    if ( !evaluated ) {
      value = expression.getExpression ().evaluate ( context );
      evaluated = true;
    }
    return value;
  }

  public boolean isTrue () {
    return AbstractCondition.isTrue ( getValue () );
  }
}
