package com.mycourse.services;

import com.mycourse.dao.DetailSyllabusDao;
import com.mycourse.dao.HeaderSyllabusDao;
import com.mycourse.dao.SyllabusDao;
import com.mycourse.dto.SyllabusDto;
import com.mycourse.entity.DetailSyllabus;
import com.mycourse.entity.HeaderSyllabus;
import com.mycourse.entity.Syllabus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class SyllabusServices {

    @Autowired private SyllabusDao syllabusDao;
    @Autowired private HeaderSyllabusDao headerSyllabusDao;
    @Autowired private DetailSyllabusDao detailSyllabusDao;


    @Transactional
    public void saveSyllabus(Syllabus form){

        Syllabus syllabus = new Syllabus();
        syllabus.setName(form.getName());
        syllabus.setDescription(form.getDescription());
        syllabus.setPrice(form.getPrice());
        syllabus.setPpn(form.getPpn());

        for (HeaderSyllabus header : form.getHeaderSyllabusList()){
            log.debug("deader name : {}", header.getName());
            header.setSyllabus(syllabus);
            headerSyllabusDao.save(header);
            for (DetailSyllabus detail : header.getDetailSyllabusList()){
                log.debug("detail name : {}", detail.getName());
                detail.setHeaderSyllabus(header);
                detailSyllabusDao.save(detail);
            }
        }

        syllabusDao.save(syllabus);

    }

}
