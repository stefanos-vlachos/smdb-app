package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.repository.SitcomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SitcomServiceImpl extends BaseServiceImpl<Sitcom> implements SitcomService {
    private final SitcomRepository sitcomRepository;

    @Override
    public JpaRepository<Sitcom, Long> getRepository() {
        return sitcomRepository;
    }
}
