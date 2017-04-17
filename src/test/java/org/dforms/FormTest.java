package org.dforms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.FileUtils;
import org.dforms.data.FormData;
import org.dforms.data.RepeatingRow;
import org.dforms.data.RepeatingSection;
import org.dforms.data.TextField;
import org.dforms.data.ValidationContext;
import org.junit.Before;
import org.junit.Test;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static net.javacrumbs.jsonunit.JsonAssert.when;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_EXTRA_FIELDS;
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
    form.addSection ( "summary2", new Section () ).getLabel ();
    assertEquals ( "summary2", form.getSection ( "summary2" ).getName () );
    assertJsonEquals ( "{\n" +
        "  \"$schema\": \"http://dforms.org/v1#\",\n" +
        "  \"sections\": {\n" +
        "    \"summary2\": {\n" +
        "      \"label\": null\n" +
        "    }\n" +
        "  }\n" +
        "}", mapper.writeValueAsString ( form ) );
    form.getSection ( "summary2" ).addTextField ( "name" );
    form.addSection ( "summary", new Section () );
    assertJsonEquals ( "{\n" +
        "  \"$schema\": \"http://dforms.org/v1#\",\n" +
        "  \"sections\": {\n" +
        "    \"summary\": {\n" +
        "      \"label\": null\n" +
        "    },\n" +
        "    \"summary2\": {\n" +
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
    final FormData formData = form.buildNewDataStructure ();
    assertNotNull ( formData.getFields () );
    assertThat ( formData.getFields ().values (), hasSize ( 20 ) );
    assertNull ( formData.getField ( "volume" ).getValue () );
    formData.parseJSON ( mapper.readTree ( getClass ().getResource ( "/exampleDataInvalid.json" ) ) );
    assertEquals ( "123", formData.getField ( "volume" ).getValue () );
    assertNull ( formData.getField ( "lot" ).getValue () );
    //noinspection ConstantConditions
    assertJsonEquals ( FileUtils.readFileToString ( FileUtils.toFile ( getClass ().getResource ( "/exampleDataInvalid.json" ) ) ),
        mapper.writeValueAsString ( formData ), when ( IGNORING_EXTRA_FIELDS ) );
    ValidationContext validate = formData.validate ();
    assertFalse ( validate.isValid () );
    assertEquals ( "Summary/currentuse: is not valid", validate.toString () );
  }

  @Test
  public void parseDataValid () throws Exception {
    final Form form = mapper.readValue ( getClass ().getResource ( "/example.json" ), Form.class );
    final FormData formData = form.buildNewDataStructure ();
    assertNotNull ( formData.getFields () );
    assertThat ( formData.getFields ().values (), hasSize ( 20 ) );
    formData.parseJSON ( mapper.readTree ( getClass ().getResource ( "/exampleDataValid.json" ) ) );
    assertJsonEquals ( FileUtils.readFileToString ( FileUtils.toFile ( getClass ().getResource ( "/exampleDataValid.json" ) ) ),
        mapper.writeValueAsString ( formData ), when ( IGNORING_EXTRA_FIELDS ) );
    ValidationContext validate = formData.validate ();
    assertTrue ( validate.toString (), validate.isValid () );
    assertEquals ( "", validate.toString () );
  }

  @Test
  public void buildData () throws Exception {
    final Form form = mapper.readValue ( getClass ().getResource ( "/example.json" ), Form.class );
    final FormData formData = form.buildNewDataStructure ();
    final RepeatingRow additions = formData.getField ( "additions", RepeatingSection.class ).addRow ();
    additions.getField ( "additions", TextField.class ).setValue ( "Hi all" );
    assertJsonEquals ( "{\n" +
        "  \"additions\": [\n" +
        "    {\n" +
        "      \"additions\": \"Hi all\"\n" +
        "    }\n" +
        "  ]\n" +
        "}", mapper.writeValueAsString ( formData ), when ( IGNORING_EXTRA_FIELDS ) );
  }
}
