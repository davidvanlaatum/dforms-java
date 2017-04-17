package org.dforms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.dforms.data.FormData;
import org.dforms.fields.Field;

import java.util.LinkedHashMap;

@JsonInclude ( JsonInclude.Include.NON_EMPTY )
public class Form implements FieldContainer {
  private LinkedHashMap<String, Section> sections = new LinkedHashMap<> ();
  private Expressions expressions;
  private String schema = "http://dforms.org/v1#";

  @JsonProperty ( "$schema" )
  public String getSchema () {
    return schema;
  }

  public Form setSchema ( String schema ) {
    this.schema = schema;
    return this;
  }

  public Expressions getExpressions () {
    return expressions;
  }

  public Form setExpressions ( Expressions expressions ) {
    this.expressions = expressions;
    return this;
  }

  public LinkedHashMap<String, Section> getSections () {
    return sections;
  }

  @JsonDeserialize ( using = SectionDeserializer.class )
  public Form setSections ( LinkedHashMap<String, Section> sections ) {
    this.sections = sections;
    return this;
  }

  public FormData parseJSON ( JsonNode node ) {
    final FormData data = new FormData ( this );
    jsonToFields ( node, data );
    return data;
  }

  public Form addSection ( String name, Section section ) {
    if ( sections.containsKey ( name ) ) {
      throw new IllegalArgumentException ( "Section " + name + " already exists" );
    }
    sections.put ( name, section );
    section.setName ( name );
    return this;
  }

  public Section getSection ( String name ) {
    Section rt = sections.get ( name );
    if ( rt == null ) {
      throw new IllegalArgumentException ( "Section " + name + " not found" );
    }
    return rt;
  }

  @Override
  @JsonIgnore
  public String getLabel () {
    return "root";
  }

  @Override
  public LinkedHashMap<String, Field> getFields () {
    return null;
  }

  public Field getField ( String name ) {
    Field rt = null;

    for ( Section section : sections.values () ) {
      rt = section.getField ( name );
      if ( rt != null ) {
        break;
      }
    }

    return rt;
  }

  @Override
  public Expression getExpression ( String name ) {
    return expressions.get ( name );
  }

  @Override
  public String getName () {
    return null;
  }

  @Override
  public void setName ( String name ) {

  }

  @Override
  public String getPath () {
    return null;
  }

  @Override
  public String getLabelPath () {
    return null;
  }

  public static class SectionDeserializer extends NamedFieldDeserializer<Section> {
    protected SectionDeserializer () {
      super ( Section.class );
    }
  }
}
