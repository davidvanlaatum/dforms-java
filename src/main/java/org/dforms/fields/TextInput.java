package org.dforms.fields;

import com.fasterxml.jackson.databind.JsonNode;
import org.dforms.data.DataField;
import org.dforms.data.DataFieldContainer;
import org.dforms.data.TextField;

import java.util.List;

public class TextInput extends Input {
  private List<String> suggestions;

  public TextInput ( String name ) {
    super ( name );
  }

  public TextInput () {
  }

  public List<String> getSuggestions () {
    return suggestions;
  }

  public TextInput setSuggestions ( List<String> suggestions ) {
    this.suggestions = suggestions;
    return this;
  }

  @Override
  public DataField parseJSON ( JsonNode node, DataFieldContainer parent ) {
    return new TextField ( node.isNull () ? null : node.asText (), this, parent );
  }
}
