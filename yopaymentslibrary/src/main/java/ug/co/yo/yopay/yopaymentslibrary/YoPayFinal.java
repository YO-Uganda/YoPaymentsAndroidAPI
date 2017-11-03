package ug.co.yo.yopay.yopaymentslibrary;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by AZIZ on 11/3/2017.
 */

public class YoPayFinal extends AsyncTask<String, Void, YoPaymentsResponse> {

    private String username;
    private String password;
    private boolean NonBlocking = false;
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

    public YoPayFinal() {
    }

    public YoPayFinal(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public YoPaymentsResponse ac_deposit_funds() {
        YoPaymentsResponse response = null;
        String xmlString = getAcDepositFundsXMLString();
        InputStream is = sendXMLRequest(xmlString);
        try {
            response = new YoPaymentsResponseXMLParser().parse(is);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getAcDepositFundsXMLString() {
        String xmlString = "";
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

            xmlString = writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;
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

    private InputStream sendXMLRequest(String xmlString) {
        InputStream is = null;
        try {
            java.net.URL url = new URL(this.URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Length", "" + xmlString.length());
            urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            urlConnection.setRequestProperty("Content-transfer-encoding", "text");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setFixedLengthStreamingMode(xmlString.length());

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(xmlString);
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

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected YoPaymentsResponse doInBackground(String... params) {
        YoPaymentsResponse response = null;
        if (params[0] == "acdepositfunds") {
            response = ac_deposit_funds();
        }
        return response;
    }

    @Override
    protected void onPostExecute(YoPaymentsResponse response) {
    }
}
