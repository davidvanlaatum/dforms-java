package org.dforms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dforms.fields.Input;
import org.dforms.fields.TextInput;
import org.junit.Test;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextInputTest {
  @Test
  public void test () throws Exception {
    ObjectMapper mapper = new ObjectMapper ();
    assertJsonEquals ( "{\"type\":\"text\", \"label\": null}", mapper.writeValueAsString ( new TextInput () ) );
    final String json = "{\n" +
        "  \"type\": \"text\",\n" +
        "  \"label\": \"X\",\n" +
        "  \"help\": \"Bla\",\n" +
        "  \"size\": 1,\n" +
        "  \"placeholder\": \"Y\"\n" +
        "}";
    final Input input = new TextInput ().setHelp ( "Bla" ).setLabel ( "X" ).setSize ( 1 ).setPlaceholder ( "Y" );
    assertJsonEquals ( json, mapper.writeValueAsString ( input ) );
    final Input value = mapper.readValue ( json, Input.class );
    assertThat ( value, isA ( TextInput.class.asSubclass ( Input.class ) ) );
    final String actual = mapper.writeValueAsString ( value );
    assertJsonEquals ( json, actual );
  }
}
