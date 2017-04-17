package org.dforms.fields;

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
  public void buildNewDataStructure ( DataFieldContainer container ) {
    container.addField ( getName (), new TextField ( null, this, container ) );
  }
}
