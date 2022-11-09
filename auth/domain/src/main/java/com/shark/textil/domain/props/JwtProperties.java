package com.shark.textil.domain.props;

import lombok.Data;

@Data
public class JwtProperties {
    private String secret;
    private String bearer;
    private Validity validity;

    @Data
    public static class Validity {
        private Long time;
        private Long refreshTime;
    }
}
