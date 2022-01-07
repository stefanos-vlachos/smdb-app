package com.pfseven.smdb.controller;

import com.pfseven.smdb.domain.*;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.ContributorService;
import com.pfseven.smdb.transfer.ApiResponse;
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
@RequestMapping("contributors")
public class ContributorController extends AbstractController<Contributor>{

    private final ContributorService contributorService;

    @Override
    protected BaseService<Contributor, Long> getBaseService() {
        return contributorService;
    }

    @GetMapping(headers = "action=getMovies", params = {"id"})
    public ResponseEntity<ApiResponse<List<Movie>>> findMoviesOfContributor(@RequestParam("id") Long id) {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder().data(contributorService.findMoviesOfContributor(id)).build());
    }

    @GetMapping(headers = "action=getMovies", params = {"id", "role"})
    public ResponseEntity<ApiResponse<List<Movie>>> findMoviesOfContributorByRole(@RequestParam("id") Long id, @RequestParam("role") String role) {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder().data(contributorService.findMoviesOfContributorByRole(id, Role.roleCompare(role))).build());
    }

    @GetMapping(headers = "action=getSitcoms", params = {"id"})
    public ResponseEntity<ApiResponse<List<Sitcom>>> findSitcomsOfContributor(@RequestParam("id") Long id) {
        return ResponseEntity.ok(ApiResponse.<List<Sitcom>>builder().data(contributorService.findSitcomsOfContributor(id)).build());
    }

    @GetMapping(headers = "action=getSitcoms", params = {"id", "role"})
    public ResponseEntity<ApiResponse<List<Sitcom>>> findSitcomsOfContributorByRole(@RequestParam("id") Long id, @RequestParam("role") String role) {
        return ResponseEntity.ok(ApiResponse.<List<Sitcom>>builder().data(contributorService.findSitcomsOfContributorByRole(id, Role.roleCompare(role))).build());
    }

    @GetMapping(headers = "action=getContent", params = {"id"})
    public ResponseEntity<ApiResponse<List<Production>>> findContentOfContributor(@RequestParam("id") Long id) {
        return ResponseEntity.ok(ApiResponse.<List<Production>>builder().data(contributorService.findContentOfContributor(id)).build());
    }

    @GetMapping(headers = "action=getContent", params = {"id, role"})
    public ResponseEntity<ApiResponse<List<Production>>> findContentOfContributorByRole(@RequestParam("id") Long id, @RequestParam("role") String role) {
        return ResponseEntity.ok(ApiResponse.<List<Production>>builder().data(contributorService.findContentOfContributorByRole(id, Role.roleCompare(role))).build());
    }

    @GetMapping(headers="action=export")
    public void exportToCSV() throws IOException {
       contributorService.exportContributorsToCsv(new FileWriter("src/main/resources/contributors.csv"));
    }
}
