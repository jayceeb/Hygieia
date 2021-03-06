package com.capitalone.dashboard.rest;

import com.capitalone.dashboard.editors.CaseInsensitiveCodeQualityTypeEditor;
import com.capitalone.dashboard.model.CodeQuality;
import com.capitalone.dashboard.model.CodeQualityType;
import com.capitalone.dashboard.model.DataResponse;
import com.capitalone.dashboard.request.CodeQualityRequest;
import com.capitalone.dashboard.service.CodeQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CodeQualityController {

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    private final CodeQualityService codeQualityService;

    @Autowired
    public CodeQualityController(CodeQualityService codeQualityService) {
        this.codeQualityService = codeQualityService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CodeQualityType.class, new CaseInsensitiveCodeQualityTypeEditor());
    }

    @RequestMapping(value = "/quality", method = GET, produces = JSON)
    public DataResponse<Iterable<CodeQuality>> qualityData(@Valid CodeQualityRequest request) {
        return codeQualityService.search(request);
    }
}
