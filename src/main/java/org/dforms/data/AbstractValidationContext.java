package org.dforms.data;

import org.dforms.conditions.AbstractEvaluationContext;
import org.dforms.conditions.ExpressionState;

public abstract class AbstractValidationContext implements ValidationContext {
  protected final ValidationContext parent;
  private final DataFieldContainer container;
  private final EvaluationContext evaluationContext;

  public AbstractValidationContext ( DataFieldContainer container, ValidationContext parent ) {
    this.container = container;
    this.parent = parent;
    evaluationContext = new EvaluationContext ( parent != null ? parent.getEvaluationContext () : null );
  }

  public DataFieldContainer getContainer () {
    return container;
  }

  public abstract ValidationContext contextForContainer ( DataFieldContainer container );

  public ExpressionState getExpression ( String name ) {
    return evaluationContext.getExpression ( name );
  }

  @Override
  public EvaluationContext getEvaluationContext () {
    return evaluationContext;
  }

  class EvaluationContext extends AbstractEvaluationContext {

    EvaluationContext ( org.dforms.conditions.EvaluationContext parent ) {
      super ( parent );
    }

    @Override
    public DataFieldContainer getDataContext () {
      return container;
    }
  }
}
