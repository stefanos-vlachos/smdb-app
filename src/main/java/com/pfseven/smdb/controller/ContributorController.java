package com.pfseven.smdb.controller;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("contributors")
public class ContributorController extends AbstractController<Contributor>{

    private final ContributorService contributorService;

    @Override
    protected BaseService<Contributor, Long> getBaseService() {
        return contributorService;
    }
}
