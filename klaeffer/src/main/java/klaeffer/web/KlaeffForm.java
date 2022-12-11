package klaeffer.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class KlaeffForm {
  @NotEmpty
  @Size(max = 39)
  private String name;
  @NotEmpty
  @Size(max = 300)
  private String text;

  public KlaeffForm(String name, String text) {
    this.name = name;
    this.text = text;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
