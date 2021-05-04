package edu.axboot.domain.practice;

import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class EducationPracticeService extends BaseService<EducationPractice, Long> {
    private static final Logger logger = LoggerFactory.getLogger(EducationPracticeService.class);
    private EducationPracticeRepository educationPracticeRepository;

   // @Inject
    EducationPracticeMapper educationPracticeMapper;

    @Inject
    public EducationPracticeService(EducationPracticeRepository educationPracticeRepository) {
        super(educationPracticeRepository);
        this.educationPracticeRepository = educationPracticeRepository;
    }

    


    // JAP
    public List<EducationPractice> gets(RequestParams<EducationPractice> requestParams) {
        List<EducationPractice> list = this.getFilter(findAll(), requestParams.getString("companyNm", ""), 1);
        List<EducationPractice> list2 = this.getFilter(list, requestParams.getString("companyNm", ""), 1);
        List<EducationPractice> list3 = this.getFilter(list2, requestParams.getString("companyNm", ""), 1);
        List<EducationPractice> list4 = this.getFilter(list3, requestParams.getString("companyNm", ""), 1);

        return list4; //빈배열 던짐
    }

    // QueryDSL
    private List<EducationPractice> getFilter(List<EducationPractice> sources, String filter, int typ) {
        List<EducationPractice> targets = new ArrayList<EducationPractice>();
        for (EducationPractice entity : sources) {
            if ("".equals(filter)) { //필터가 비어있으면 바로 추가
                targets.add(entity);
            } else { // 그렇지 않으면 타입 검수...
                if (typ == 1) {
                    if (entity.getCompanyNm().equals(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 2) {
                    if (entity.getCeo().equals(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 3) {
                    if (entity.getBizno().equals(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 4) {
                    if (entity.getUseYn().equals(filter)) {
                        targets.add(entity);
                    }
                }
            }
        }
        return targets;
    }

    public List<EducationPractice> gets(String companyNm, String ceo, String bizno, String useYn) {

        BooleanBuilder builder = new BooleanBuilder(); //builder로 조건문 채우기
        //////조건문 build////
        if (isNotEmpty(companyNm)) { //널,공백이 아닌 것 참거짓 체크
            builder.and(qEducationPractice.companyNm.like("%" + companyNm + "%")); //like검색어 문구포함 검색
        }
        if (isNotEmpty(ceo)) {
            builder.and(qEducationPractice.ceo.like("%" + ceo + "%"));
        }
        if (isNotEmpty(bizno)) {
            builder.and(qEducationPractice.bizno.like("%" + bizno + "%"));
        }
        if (isNotEmpty(useYn)) {
            builder.and(qEducationPractice.bizno.eq(useYn));
        }


        //---반환 형태대로 데이터 취합.
        List<EducationPractice> practiceList = select()
                .from(qEducationPractice)
                .where(builder)
                .orderBy(qEducationPractice.companyNm.asc())
                .fetch(); // list로 받는 함수 fetch
        return practiceList;
    }

    public EducationPractice getByOne(Long id) {
        BooleanBuilder builder = new BooleanBuilder(); // BooleanBuilder : where뒤에 조건을 생성해줌
        builder.and(qEducationPractice.id.eq(id));

        EducationPractice entity = select()
                .from(qEducationPractice)
                .where(builder)
                .fetchOne(); // 한건으로 받기 fetchOne.
        return entity;
    }

    @Transactional
    public void persist(EducationPractice request) { //저장
        //requestbody에 id의 유무를 확인
        if (request.getId() == null || request.getId() == 0) {
            save(request);
        } else {
            update(qEducationPractice)
                    .set(qEducationPractice.companyNm, request.getCompanyNm())
                    .set(qEducationPractice.ceo, request.getCeo())
                    .set(qEducationPractice.bizno, request.getBizno())
                    .set(qEducationPractice.tel, request.getTel())
                    .set(qEducationPractice.zip, request.getZip())
                    .set(qEducationPractice.address, request.getAddress())
                    .set(qEducationPractice.addressDetail, request.getAddressDetail())
                    .set(qEducationPractice.email, request.getEmail())
                    .set(qEducationPractice.useYn, request.getUseYn())
                    .where(qEducationPractice.id.eq(request.getId()))
                    .execute();
        }
    }

    @Transactional
    public void remove(Long id) {
        delete(qEducationPractice).where(qEducationPractice.id.eq(id)).execute();
    }


    // Mybatis
    public List<EducationPractice> select(String companyNm, String ceo, String bizno, String useYn) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("companyNm", companyNm);
        params.put("ceo", ceo);
        params.put("bizno", bizno);
        params.put("useYn", useYn);

        List<EducationPractice> list = educationPracticeMapper.select(params);

        return list;
    }

    public EducationPractice selectOne(Long id) {
        return educationPracticeMapper.selectOne(id);
    }


    public void enroll(EducationPractice request) {
        if (request.getId() == null || request.getId() == 0) {
            educationPracticeMapper.insert(request);
        } else {
            educationPracticeMapper.update(request);
        }
    }

    public void del(Long id) {

        educationPracticeMapper.delete(id);
    }

    ////////////
    public List<EducationPractice> getByQueryDsl(RequestParams<EducationPractice> requestParams) {

        String companyNm = requestParams.getString("company", "");
        String ceo = requestParams.getString("ceo", "");
        String bizno = requestParams.getString("bizno", "");
        String useYn = requestParams.getString("useYn", "");
        logger.info("회사명 : " + companyNm);
        logger.info("대표자 : " + ceo);
        logger.info("사업자번호 : " + bizno);
        logger.info("사용여부 : " + useYn);

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(companyNm)) builder.and(qEducationPractice.companyNm.contains(companyNm));
        if (isNotEmpty(ceo)) builder.and(qEducationPractice.ceo.contains(ceo));
        if (isNotEmpty(bizno)) builder.and(qEducationPractice.bizno.contains(bizno));
        if (isNotEmpty(useYn)) builder.and(qEducationPractice.useYn.eq(useYn));
        List<EducationPractice> educationPracticeList = select().
                from(qEducationPractice).
                where(builder).
                orderBy(qEducationPractice.companyNm.asc())
                .fetch();
        return educationPracticeList;
    }

/*    public List<EducationPractice> getByQueryDsl(RequestParams<EducationPractice> requestParams) {
           String company = requestParams.getString("company", "");
           String ceo = requestParams.getString("ceo", "");
           String bizno = requestParams.getString("bizno", "");
           String useYn = requestParams.getString("useYn", "");

           EducationPractice educationPractice = new EducationPractice();
           educationPractice.setCompanyNm(requestParams.getString("company",""));
           educationPractice.setCeo(requestParams.getString("ceo",""));
           educationPractice.setBizno(requestParams.getString("bizno",""));
           educationPractice.setUseYn(requestParams.getString("useYn",""));

         List<EducationPractice> educationPracticeList = this.getByQueryDsl(company, ceo, bizno, useYn);
         return educationPracticeList;
    }*/


    @Transactional
    public void saveOneByQueryDsl(List<EducationPractice> request) {
        for (EducationPractice practice : request) {
            if (practice.isCreated()) {
                save(request);
            } else if (practice.isModified()) {
                update(qEducationPractice)
                        .set(qEducationPractice.companyNm, practice.getCompanyNm())
                        .set(qEducationPractice.ceo, practice.getCeo())
                        .set(qEducationPractice.useYn, practice.getUseYn())
                        .where(qEducationPractice.id.eq(practice.getId()))
                        .execute(); // excute의 리턴 값은 성공 여부에 따라 0 or 1
            } else if (practice.isDeleted()) {
                delete(qEducationPractice)
                        .where(qEducationPractice.id.eq(practice.getId()))
                        .execute();
            }
        }
    }
}



