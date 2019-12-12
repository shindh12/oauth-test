package com.sds.alioolio.authtest.util;

import com.sds.alioolio.authtest.service.AlioClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

/**
 * Date: 2019-06-03
 * Developer: dongha.shin
 * Description : Converter from binary to JWT
 */
@Component
public class AlioJwtAccessTokenConverter extends JwtAccessTokenConverter {

    public DefaultAccessTokenConverter getDefaultAccessTokenConverter() {
        return (DefaultAccessTokenConverter) super.getAccessTokenConverter();
    }
}
