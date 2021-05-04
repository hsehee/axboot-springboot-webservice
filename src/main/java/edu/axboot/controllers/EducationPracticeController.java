package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.domain.practice.EducationPractice;
import edu.axboot.domain.practice.EducationPracticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

// DTO를 requestParams처럼 한꺼번에 받지 않고 따로따로 생성
@Controller
@RequestMapping(value = "/api/v1/education/practiceGridForm")
public class EducationPracticeController extends BaseController {
    @Inject
    private EducationPracticeService educationPracticeService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list
                                (@RequestParam(value = "companyNm", required = false) String companyNm,
                                 @RequestParam(value = "ceo", required = false) String ceo,
                                 @RequestParam(value = "bizno", required = false) String bizno,
                                 @RequestParam(value = "useYn", required = false) String useYn) {

       List<EducationPractice> list = educationPracticeService.gets(companyNm, ceo, bizno, useYn);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public EducationPractice view(@PathVariable Long id) {
        EducationPractice entity = educationPracticeService.getByOne(id);

        return entity;
    }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody EducationPractice request){
        educationPracticeService.persist(request);
        return null;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON)
    public ApiResponse remove(@PathVariable Long id) {
        educationPracticeService.remove(id);
        return ok();
    }
}
