package org.dforms.conditions;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractRelativeConditionTest {

  @Test
  public void isGreater () throws Exception {
    TestObject obj = new TestObject ();
    assertFalse ( obj.isGreater ( 1, 2 ) );
    assertTrue ( obj.isGreater ( 2, 1 ) );
    assertFalse ( obj.isGreater ( 0.1, 1 ) );
    assertTrue ( obj.isGreater ( 2.1, 1 ) );
    assertFalse ( obj.isGreater ( 1, 1L ) );
    assertTrue ( obj.isGreater ( 2, 1L ) );
  }

  @Test
  public void areNumbers () throws Exception {
    TestObject obj = new TestObject ();

    assertTrue ( obj.areNumbers ( 1, 2, ( first, second, common ) -> {
      assertEquals ( 1, first );
      assertEquals ( 2, second );
      assertEquals ( Integer.class, common );
      return true;
    } ) );

    assertTrue ( obj.areNumbers ( 1, 2L, ( first, second, common ) -> {
      assertEquals ( 1, first );
      assertEquals ( 2L, second );
      assertEquals ( Long.class, common );
      return true;
    } ) );

    assertTrue ( obj.areNumbers ( 1, 2.1, ( first, second, common ) -> {
      assertEquals ( 1, first );
      assertEquals ( 2.1, second );
      assertEquals ( Double.class, common );
      return true;
    } ) );

    assertTrue ( obj.areNumbers ( 1L, 2, ( first, second, common ) -> {
      assertEquals ( 1L, first );
      assertEquals ( 2, second );
      assertEquals ( Long.class, common );
      return true;
    } ) );

    assertTrue ( obj.areNumbers ( 1.1, 2, ( first, second, common ) -> {
      assertEquals ( 1.1, first );
      assertEquals ( 2, second );
      assertEquals ( Double.class, common );
      return true;
    } ) );

    assertNull ( obj.areNumbers ( "bla", 2, ( first, second, common ) -> {
      fail ( "Should not get here" );
      return true;
    } ) );

    assertNull ( obj.areNumbers ( 1, "bla", ( first, second, common ) -> {
      fail ( "Should not get here" );
      return true;
    } ) );

    assertNull ( obj.areNumbers ( "1", "bla", ( first, second, common ) -> {
      fail ( "Should not get here" );
      return true;
    } ) );
  }

  class TestObject extends AbstractRelativeCondition {

    @Override
    public Object evaluate ( EvaluationContext context ) {
      return null;
    }
  }

}
