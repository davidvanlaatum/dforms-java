package org.dforms.fields;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dforms.data.DataFieldContainer;
import org.dforms.data.EnumField;

import java.util.LinkedHashMap;
import java.util.List;

public class EnumInput extends Input {
  private LinkedHashMap<String, String> options;
  private List<String> optionsList;
  private String defaultValue;

  @JsonProperty ( "default" )
  public String getDefaultValue () {
    return defaultValue;
  }

  public EnumInput setDefaultValue ( String defaultValue ) {
    this.defaultValue = defaultValue;
    return this;
  }

  @JsonProperty
  public Object getOptions () {
    return options != null ? options : optionsList;
  }

  @SuppressWarnings ( "unchecked" )
  @JsonProperty
  public EnumInput setOptions ( JsonNode node ) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper ();
    if ( node.isArray () ) {
      optionsList = mapper.treeToValue ( node, List.class );
    } else {
      options = mapper.treeToValue ( node, LinkedHashMap.class );
    }
    return this;
  }

  public EnumInput setOptions ( LinkedHashMap<String, String> options ) {
    this.options = options;
    return this;
  }

  public EnumInput setOptions ( List<String> optionsList ) {
    this.optionsList = optionsList;
    return this;
  }

  @Override
  public void buildNewDataStructure ( DataFieldContainer container ) {
    container.addField ( getName (), new EnumField ( null, this, container ) );
  }
}
