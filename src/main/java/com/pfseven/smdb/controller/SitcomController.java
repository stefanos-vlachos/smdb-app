package com.pfseven.smdb.controller;


import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.SitcomService;
import com.pfseven.smdb.transfer.ApiResponse;
import com.pfseven.smdb.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/sitcoms")
public class SitcomController extends AbstractController<Sitcom> {

    private final SitcomService sitcomService;

    @Override
    protected BaseService<Sitcom, Long> getBaseService() {
        return sitcomService;
    }

    @GetMapping(params = {"top"})
    public ResponseEntity<ApiResponse<List<Sitcom>>> findTopXRatedMovies(@RequestParam("top") Integer sitcomsNum) {
        return ResponseEntity.ok(ApiResponse.<List<Sitcom>>builder().data(sitcomService.findTopXRatedSitcoms(sitcomsNum)).build());
    }

    @GetMapping(params = {"g"})
    public ResponseEntity<ApiResponse<List<Sitcom>>> findSitcomsByGenres(@RequestParam("g") Genre genre) {
        return ResponseEntity.ok(ApiResponse.<List<Sitcom>>builder().data(sitcomService.findSitcomsByGenres(genre)).build());
    }

    @GetMapping(headers = "action=findSitcomsPerGenre")
    public ResponseEntity<ApiResponse<List<KeyValue<Genre, Integer>>>> findSitcomsPerGenre() {
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Genre, Integer>>>builder().data(sitcomService.findSitcomsPerGenre()).build());
    }

    @GetMapping(headers="action=export")
    public void exportToCSV() throws IOException {
        sitcomService.exportSitcomsToCsv(new FileWriter("src/main/resources/csvExports/sitcoms.csv"),
                new FileWriter("src/main/resources/csvExports/sitcomsContributions.csv"));
    }
}
