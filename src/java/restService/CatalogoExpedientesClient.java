/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ExpedientesResource [exp]<br>
 * USAGE:
 * <pre>
 *        CatalogoExpedientesClient client = new CatalogoExpedientesClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Alfonso Felix
 */
public class CatalogoExpedientesClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8443/Equipo0_CatalogoExpediente/res";

    public CatalogoExpedientesClient() {
        client = ClientBuilder.newBuilder().sslContext(getSSLContext()).hostnameVerifier(new HostnameVerifier(){
              @Override
              public boolean verify(String paramString, SSLSession paramSSLSession) {
               return true;
             }
        }).build();
        webTarget = client.target(BASE_URI).path("exp");
    }

    public String postConsultaExpediente(Object requestEntity) throws ClientErrorException {
        return webTarget.path("consultaexpediente").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public void close() {
        client.close();
    }
    
    private SSLContext getSSLContext() {
        // for alternative implementation checkout org.glassfish.jersey.SslConfigurator
        javax.net.ssl.TrustManager x509 = new javax.net.ssl.X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new javax.net.ssl.TrustManager[]{x509}, null);
        } catch (java.security.GeneralSecurityException ex) {
        }
        return ctx;
    }
    
}
