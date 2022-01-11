package com.pfseven.smdb.controller;


import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.SitcomService;
import com.pfseven.smdb.transfer.ApiResponse;
import com.pfseven.smdb.transfer.KeyValue;
import com.pfseven.smdb.transfer.NumberOfProductionsPerYearAndGenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileWriter;
import java.io.IOException;
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

    @GetMapping(params = {"title"})
    public ResponseEntity<ApiResponse<Sitcom>> findByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(ApiResponse.<Sitcom>builder().data(sitcomService.findByTitle(title)).build());
    }

    @GetMapping(params = {"top"})
    public ResponseEntity<ApiResponse<List<Sitcom>>> findTopXRatedSitcoms(@RequestParam("top") Integer sitcomsNum) {
        return ResponseEntity.ok(ApiResponse.<List<Sitcom>>builder().data(sitcomService.findTopXRatedSitcoms(sitcomsNum)).build());
    }

    @GetMapping(params = {"genre"})
    public ResponseEntity<ApiResponse<List<Sitcom>>> findSitcomsByGenre(@RequestParam("genre") Genre genre) {
        return ResponseEntity.ok(ApiResponse.<List<Sitcom>>builder().data(sitcomService.findSitcomsByGenre(genre)).build());
    }

    @GetMapping(headers = "action=findSitcomsNumberPerGenre")
    public ResponseEntity<ApiResponse<List<KeyValue<Genre, Integer>>>> findSitcomsNumberPerGenre() {
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Genre, Integer>>>builder().data(sitcomService.findSitcomsNumberPerGenre()).build());
    }

    @GetMapping(headers = "action=findMoviesNumberPerGenreAndYear")
    public ResponseEntity<ApiResponse<List<NumberOfProductionsPerYearAndGenreDto>>> findMoviesNumberPerGenreAndYear() {
        return ResponseEntity.ok(ApiResponse.<List<NumberOfProductionsPerYearAndGenreDto>>builder().data(sitcomService.findSitcomsNumberPerGenreAndYear()).build());
    }

    @GetMapping(headers="action=export")
    public void exportToCSV() throws IOException {
        sitcomService.exportSitcomsToCsv(new FileWriter("src/main/resources/csvExports/sitcoms.csv"),
                new FileWriter("src/main/resources/csvExports/sitcomsContributions.csv"));
    }
}
