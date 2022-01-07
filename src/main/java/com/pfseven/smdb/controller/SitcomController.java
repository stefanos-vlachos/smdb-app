package com.pfseven.smdb.controller;


import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.SitcomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/sitcoms")
public class SitcomController extends AbstractController<Sitcom> {

    private final SitcomService sitcomService;

    @Override
    protected BaseService<Sitcom, Long> getBaseService() {
        return sitcomService;
    }

    @GetMapping(headers="action=export")
    public void exportToCSV() throws IOException {
        sitcomService.exportSitcomsToCsv(new FileWriter("src/main/resources/csvExports/sitcoms.csv"),
                new FileWriter("src/main/resources/csvExports/sitcomsContributions.csv"));
    }
}
