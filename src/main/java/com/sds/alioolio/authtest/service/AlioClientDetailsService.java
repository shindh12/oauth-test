package com.sds.alioolio.authtest.service;

import javax.sql.DataSource;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * Date: 2019-06-03
 * Developer: dongha.shin
 * Description :
 */

@Service
public class AlioClientDetailsService extends JdbcClientDetailsService {
    public AlioClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }
}
