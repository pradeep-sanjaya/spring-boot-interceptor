package com.techprovint.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
public class AuditTrail {
    private String method;
    private String queryString;
    private String requestUrl;
    private String remoteAddress;

    public AuditTrail(HttpServletRequest request) {
        this.method = request.getMethod();
        this.queryString = request.getQueryString();
        this.requestUrl = request.getRequestURI();
        this.remoteAddress = getRemoteAddressByRequest(request);
    }

    private String getRemoteAddressByRequest(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }
}
