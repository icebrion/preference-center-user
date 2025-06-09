package com.vw.apirest.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-08T16:50:54.066988100+02:00[Europe/Madrid]", comments = "Generator version: 7.5.0")
public class UserRequest {

  private String email;

  @Valid
  private List<@Valid Consent> consents = new ArrayList<>();

  public UserRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  
  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserRequest consents(List<@Valid Consent> consents) {
    this.consents = consents;
    return this;
  }

  public UserRequest addConsentsItem(Consent consentsItem) {
    if (this.consents == null) {
      this.consents = new ArrayList<>();
    }
    this.consents.add(consentsItem);
    return this;
  }

  /**
   * Get consents
   * @return consents
  */
  @Valid 
  @Schema(name = "consents", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("consents")
  public List<@Valid Consent> getConsents() {
    return consents;
  }

  public void setConsents(List<@Valid Consent> consents) {
    this.consents = consents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRequest userRequest = (UserRequest) o;
    return Objects.equals(this.email, userRequest.email) &&
        Objects.equals(this.consents, userRequest.consents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, consents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    consents: ").append(toIndentedString(consents)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

