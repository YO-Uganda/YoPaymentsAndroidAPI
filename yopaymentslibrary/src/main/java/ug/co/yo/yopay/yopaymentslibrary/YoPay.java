package ug.co.yo.yopay.yopaymentslibrary;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class YoPay {
    private String username;
    private String password;
    private boolean NonBlocking = true;
    private String external_reference;
    private String internal_reference;
    private String provider_reference_text;
    private String instant_notification_url;
    private String failure_notification_url;
    private String msisdn;
    private String narrative;
    private double amount;
    private String deposit_transaction_type = "PULL";
    private String URL = "https://paymentsapi1.yo.co.ug/ybs/task.php";

    private static final long ONE_MINUTE_IN_MILLIS = 60000;
    private static final int WAIT_TIME_IN_MINUTES = 1;
    private static final int SLEEP_TIME_IN_SECONDS = 10;

    public YoPay() {
    }

    public YoPay(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setNonBlocking(boolean nonBlocking) {
        this.NonBlocking = nonBlocking;
    }

    public boolean getNonBlocking() {
        return this.NonBlocking;
    }

    public void setExternalReference(String external_reference) {
        this.external_reference = external_reference;
    }

    public String getExternalReference() {
        return this.external_reference;
    }

    public void setInternalReference(String internal_reference) {
        this.internal_reference = internal_reference;
    }

    public String getInternalReference() {
        return this.internal_reference;
    }

    public void setProviderReferenceText(String provider_reference_text) {
        this.provider_reference_text = provider_reference_text;
    }

    public String getProviderReferenceText() {
        return this.provider_reference_text;
    }

    public void setInstantNotificationUrl(String instant_notification_url) {
        this.instant_notification_url = instant_notification_url;
    }

    public String getInstantNotificationUrl() {
        return this.instant_notification_url;
    }

    public void setFailureNotificationUrl(String failure_notification_url) {
        this.failure_notification_url = failure_notification_url;
    }

    public String getFailureNotificationUrl() {
        return this.failure_notification_url;
    }

    public void setURL(String url) {
        this.URL = url;
    }

    public String getURL() {
        return this.URL;
    }

    public YoPaymentsResponse ac_deposit_funds(String msisdn, double amount, String narrative) {
        YoPaymentsResponse response = new YoPaymentsResponse();
        this.msisdn = msisdn;
        this.amount = amount;
        this.narrative = narrative;

        try {
            String xml = getXMLString();
            return new SendAsyncXMLRequest().execute(xml).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public YoPaymentsResponse check_transaction(String transaction_reference) {
        YoPaymentsResponse response = null;
        Calendar now_time = Calendar.getInstance();
        long current_time_in_ms = now_time.getTimeInMillis();
        long finish_time_in_ms = current_time_in_ms + (WAIT_TIME_IN_MINUTES * ONE_MINUTE_IN_MILLIS);

        while (current_time_in_ms < finish_time_in_ms) {
            try {
                TimeUnit.SECONDS.sleep(SLEEP_TIME_IN_SECONDS);
                response = ac_check_transaction_status(transaction_reference);
                if (!response.getTransactionStatus().equals("PENDING")) {
                    break;
                }
                current_time_in_ms = Calendar.getInstance().getTimeInMillis();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return response;
    }

    public YoPaymentsResponse ac_check_transaction_status(String transaction_reference) {
        YoPaymentsResponse response = new YoPaymentsResponse();
        try {
            String xml = getCheckTransactionXML(transaction_reference);
            return new SendAsyncXMLRequest().execute(xml).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private InputStream sendXMLRequest(String xml) {
        String response = "";
        InputStream is = null;
        try {
            java.net.URL url = new URL(this.URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Length", "" + xml.length());
            urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            urlConnection.setRequestProperty("Content-transfer-encoding", "text");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setFixedLengthStreamingMode(xml.length());

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(xml);
            writer.flush();
            writer.close();
            out.close();

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                is = urlConnection.getInputStream();
            }

        } catch (SSLHandshakeException e) {

        } catch (Exception e) {
            Log.e("YOPAYxxx", "ERROR: " + e);
        }

        return is;
    }

    public void setupHTTPSConnection() {

        try {
            java.net.URL url = new URL(this.URL);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = new BufferedInputStream(new YoCertificate().getInputStream());
            Certificate ca = cf.generateCertificate(caInput);
            Log.i("MAIN", "ca=" + ((X509Certificate) ca).getSubjectDN());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    private String getCheckTransactionXML(String transactionReference) throws Exception {
        String xml = "";
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbfactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element autoCreate = doc.createElement("AutoCreate");
            doc.appendChild(autoCreate);
            autoCreate.appendChild(getTransactionCheckRequest(doc, transactionReference));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            xml = writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    private String getXMLString() throws Exception {
        String xml = "";
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbfactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element autoCreate = doc.createElement("AutoCreate");
            doc.appendChild(autoCreate);
            autoCreate.appendChild(getRequest(doc));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            xml = writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    private Node getTransactionCheckRequest(Document doc, String transactionReference) {
        Element request = doc.createElement("Request");
        request.appendChild(getRequestElements(doc, request, "APIUsername", this.username));
        request.appendChild(getRequestElements(doc, request, "APIPassword", this.password));
        request.appendChild(getRequestElements(doc, request, "Method", "actransactioncheckstatus"));
        request.appendChild(getRequestElements(doc, request, "TransactionReference", "" + transactionReference));
        return request;
    }

    private Node getRequest(Document doc) {
        Element request = doc.createElement("Request");
        request.appendChild(getRequestElements(doc, request, "APIUsername", this.username));
        request.appendChild(getRequestElements(doc, request, "APIPassword", this.password));
        request.appendChild(getRequestElements(doc, request, "Method", "acdepositfunds"));
        if (this.NonBlocking) {
            request.appendChild(getRequestElements(doc, request, "NonBlocking", "TRUE"));
        }
        request.appendChild(getRequestElements(doc, request, "Account", this.msisdn));
        request.appendChild(getRequestElements(doc, request, "Amount", "" + this.amount));
        request.appendChild(getRequestElements(doc, request, "Narrative", this.narrative));
        if (this.external_reference != null) {
            request.appendChild(getRequestElements(doc, request, "ExternalReference", this.external_reference));
        }
        if (this.instant_notification_url != null) {
            request.appendChild(getRequestElements(doc, request, "InstantNotificationUrl", this.instant_notification_url));
        }
        if (this.failure_notification_url != null) {
            request.appendChild(getRequestElements(doc, request, "FailureNotificationUrl", this.failure_notification_url));
        }
        return request;
    }

    private static Node getRequestElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private class SendAsyncXMLRequest extends AsyncTask<String, Void, YoPaymentsResponse> {
        protected YoPaymentsResponse doInBackground(String... xml) {
            YoPaymentsResponse response = null;
            try {
                InputStream is = sendXMLRequest(xml[0]);
                response = new YoPaymentsResponseXMLParser().parse(is);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }
    }
}

