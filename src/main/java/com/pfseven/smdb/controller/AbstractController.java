package com.pfseven.smdb.controller;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.BaseModel;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.transfer.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractController<T extends BaseModel> extends AbstractLogComponent {
    protected abstract BaseService<T, Long> getBaseService();

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.<T>builder().data(getBaseService().find(id)).build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<T>>> findAll() {
        return ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build());
    }
}
