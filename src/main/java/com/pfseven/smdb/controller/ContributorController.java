package com.pfseven.smdb.controller;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.domain.Role;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.ContributorService;
import com.pfseven.smdb.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(params = {"id"})
    public ResponseEntity<ApiResponse<List<Movie>>> findMoviesOfContributor(@RequestParam("id") Long id) {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder().data(contributorService.findMoviesOfContributor(id)).build());
    }

    @GetMapping(params = {"id", "role"})
    public ResponseEntity<ApiResponse<List<Movie>>> findMoviesOfContributorByRole(@RequestParam("id") Long id, @RequestParam("role") String role) {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder().data(contributorService.findMoviesOfContributorByRole(id, Role.roleCompare(role))).build());
    }
}
