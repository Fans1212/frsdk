package com.javajy.sdk.pay;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public abstract class FRPayConfig extends WXPayConfig {

	private byte[] certData;

    public FRPayConfig() throws Exception {
//        String certPath = "/path/to/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
    }

    public abstract String getAppID();

    public abstract String getMchID();

    public String getKey() {
        return "aabbccddeeffgghhiijjkkllmmnnoopp";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

	@Override
	IWXPayDomain getWXPayDomain() {
		IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
	}

}
