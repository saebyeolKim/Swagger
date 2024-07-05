package com.example.swagger.controller;

import com.example.swagger.dto.BasicResponseDto;
import com.example.swagger.dto.MemberJoinRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Example", description = "Example API") //컨트롤러에 대한 설명 정의
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/example")
public class ExampleApiController {

    @PostMapping("/{pathValue}")
    @Operation(summary = "Example API Summary", description = "Your description") //각각의 API에 대한 섦여 정의
    @ApiResponses(value = { //각각의 응답 코드에 따른 케이스를 설명
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = BasicResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public BasicResponseDto exampleAPI(
            //Path Parameter
            @PathVariable
            @Schema(description = "Path Value", example = "1")
            Long pathValue,

            //Query Parameter
            @Parameter(name = "paramValue", description = "Parameter Value", example = "3", required = true)
            @RequestParam final Long paramValue,

            //Request Body
            @RequestBody @Valid MemberJoinRequestDto requestBody
    ) {
        String s = String.format("PathValue = %d , ParamValue = %s, Request Email : %s", pathValue, paramValue, requestBody.getEmail());
        BasicResponseDto response = new BasicResponseDto(true, "Example API Success",  s);
        return response;
    }
}
