/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.vw.apirest.api.controller;

import com.vw.apirest.api.dto.UserRequest;
import com.vw.apirest.api.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-08T16:50:54.066988100+02:00[Europe/Madrid]", comments = "Generator version: 7.5.0")
@Validated
@Tag(name = "users", description = "the users API")
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /users/{userId} : Delete a user by ID
     *
     * @param userId  (required)
     * @return User deleted (status code 204)
     */
    @Operation(
        operationId = "deleteUser",
        summary = "Delete a user by ID",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "204", description = "User deleted")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/users/{userId}"
    )
    
    default ResponseEntity<Void> deleteUser(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users : List all users
     *
     * @return List of users (status code 200)
     */
    @Operation(
        operationId = "getAllUsers",
        summary = "List all users",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "200", description = "List of users", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<UserResponse>> getAllUsers(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"consents\" : [ { \"id\" : \"id\", \"enabled\" : true }, { \"id\" : \"id\", \"enabled\" : true } ], \"id\" : \"id\", \"email\" : \"email\" }, { \"consents\" : [ { \"id\" : \"id\", \"enabled\" : true }, { \"id\" : \"id\", \"enabled\" : true } ], \"id\" : \"id\", \"email\" : \"email\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users/{userId} : Get a user by ID
     *
     * @param userId  (required)
     * @return User found (status code 200)
     *         or User not found (status code 404)
     */
    @Operation(
        operationId = "getUser",
        summary = "Get a user by ID",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "200", description = "User found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/{userId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<UserResponse> getUser(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"consents\" : [ { \"id\" : \"id\", \"enabled\" : true }, { \"id\" : \"id\", \"enabled\" : true } ], \"id\" : \"id\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /users/{userId} : Update a user by ID
     *
     * @param userId  (required)
     * @param userRequest  (required)
     * @return User updated (status code 200)
     */
    @Operation(
        operationId = "putUser",
        summary = "Update a user by ID",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "200", description = "User updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/users/{userId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<UserResponse> putUser(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId,
        @Parameter(name = "UserRequest", description = "", required = true) @Valid @RequestBody UserRequest userRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"consents\" : [ { \"id\" : \"id\", \"enabled\" : true }, { \"id\" : \"id\", \"enabled\" : true } ], \"id\" : \"id\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /users : Create or update a user
     *
     * @param userRequest  (required)
     * @return Created or updated user (status code 201)
     *         or User not valid (status code 422)
     */
    @Operation(
        operationId = "upsertUser",
        summary = "Create or update a user",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Created or updated user"),
            @ApiResponse(responseCode = "422", description = "User not valid")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<UserResponse> upsertUser(
        @Parameter(name = "UserRequest", description = "", required = true) @Valid @RequestBody UserRequest userRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
