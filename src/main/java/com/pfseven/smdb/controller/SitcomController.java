package com.pfseven.smdb.controller;

import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.SitcomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sitcoms")
public class SitcomController extends AbstractController<Sitcom> {

    private final SitcomService sitcomService;

    @Override
    protected BaseService<Sitcom, Long> getBaseService() {
        return sitcomService;
    }
}
