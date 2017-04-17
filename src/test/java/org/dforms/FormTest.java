package org.dforms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.FileUtils;
import org.dforms.data.FormData;
import org.dforms.data.ValidationContext;
import org.junit.Before;
import org.junit.Test;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class FormTest {
  private ObjectMapper mapper;

  @Before
  public void setUp () throws Exception {
    mapper = new ObjectMapper ();
    mapper.configure ( SerializationFeature.INDENT_OUTPUT, true );
  }

  @Test
  public void readTest () throws Exception {
    final Form form = mapper.readValue ( getClass ().getResource ( "/example.json" ), Form.class );
    assertNotNull ( form );
    final Section summary = form.getSection ( "summary" );
    assertThat ( summary.getName (), equalTo ( "summary" ) );
    assertEquals ( summary, summary.getField ( "pt_jr_id" ).getParent () );
    assertEquals ( summary, summary.getField ( "area" ).getParent () );
    assertEquals ( summary.getField ( "area" ), summary.getField ( "ppt_area" ).getParent () );
    assertEquals ( summary, summary.getField ( "additions" ).getParent () );
    assertJsonEquals ( FileUtils.readFileToString ( FileUtils.toFile ( getClass ().getResource ( "/example.json" ) ) ), mapper.writeValueAsString ( form ) );
  }

  @Test
  public void buildTest () throws Exception {
    Form form = new Form ();
    form.addSection ( "summary", new Section () );
    assertEquals ( "summary", form.getSection ( "summary" ).getName () );
    assertJsonEquals ( "{\n" +
        "  \"$schema\": \"http://dforms.org/v1#\",\n" +
        "  \"sections\": {\n" +
        "    \"summary\": {\n" +
        "      \"label\": null\n" +
        "    }\n" +
        "  }\n" +
        "}", mapper.writeValueAsString ( form ) );
    form.getSection ( "summary" ).addTextField ( "name" );
    assertJsonEquals ( "{\n" +
        "  \"$schema\": \"http://dforms.org/v1#\",\n" +
        "  \"sections\": {\n" +
        "    \"summary\": {\n" +
        "      \"label\": null,\n" +
        "      \"fields\": {\n" +
        "        \"name\": {\n" +
        "          \"type\": \"text\",\n" +
        "          \"label\": null\n" +
        "        }\n" +
        "      }\n" +
        "    }\n" +
        "  }\n" +
        "}", mapper.writeValueAsString ( form ) );
  }

  @Test
  public void parseDataInvalid () throws Exception {
    final Form form = mapper.readValue ( getClass ().getResource ( "/example.json" ), Form.class );
    FormData formData = form.parseJSON ( mapper.readTree ( getClass ().getResource ( "/exampleDataInvalid.json" ) ) );
    assertNotNull ( formData.getFields () );
    assertThat ( formData.getFields ().values (), hasSize ( 18 ) );
    assertEquals ( "123", formData.getField ( "volume" ).getValue () );
    assertNull ( formData.getField ( "lot" ).getValue () );
    assertJsonEquals ( FileUtils.readFileToString ( FileUtils.toFile ( getClass ().getResource ( "/exampleDataInvalid.json" ) ) ),
        mapper.writeValueAsString ( formData ) );
    ValidationContext validate = formData.validate ();
    assertFalse ( validate.isValid () );
    assertEquals ( "Summary/currentuse: is not valid", validate.toString () );
  }

  @Test
  public void parseDataValid () throws Exception {
    final Form form = mapper.readValue ( getClass ().getResource ( "/example.json" ), Form.class );
    FormData formData = form.parseJSON ( mapper.readTree ( getClass ().getResource ( "/exampleDataValid.json" ) ) );
    assertNotNull ( formData.getFields () );
    assertThat ( formData.getFields ().values (), hasSize ( 18 ) );
    assertJsonEquals ( FileUtils.readFileToString ( FileUtils.toFile ( getClass ().getResource ( "/exampleDataValid.json" ) ) ),
        mapper.writeValueAsString ( formData ) );
    ValidationContext validate = formData.validate ();
    assertTrue ( validate.toString (), validate.isValid () );
    assertEquals ( "", validate.toString () );
  }
}
