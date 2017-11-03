package ug.co.yo.yopay.yopaymentslibrary;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AZIZ on 10/10/2017.
 */

public class YoPaymentsResponseXMLParser {
    private static final String ns = null; // We don't use namespaces so leave this as null

    public YoPaymentsResponse parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readAutocreate(parser);
        } finally {
            in.close();
        }
    }

    private YoPaymentsResponse readAutocreate(XmlPullParser parser) throws XmlPullParserException, IOException {
        YoPaymentsResponse response = new YoPaymentsResponse();
        parser.require(XmlPullParser.START_TAG, ns, "AutoCreate");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("Response")) {
                return readResponse(parser);//This may be a redundancy here
            }
        }
        return response;
    }

    private YoPaymentsResponse readResponse(XmlPullParser parser) throws IOException, XmlPullParserException {
        YoPaymentsResponse response = new YoPaymentsResponse();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("Status")) {
                parser.require(XmlPullParser.START_TAG, ns, "Status");
                response.setStatus(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "Status");
            } else if (name.equals("StatusCode")) {
                parser.require(XmlPullParser.START_TAG, ns, "StatusCode");
                response.setStatusCode(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "StatusCode");
            } else if (name.equals("StatusMessage")) {
                parser.require(XmlPullParser.START_TAG, ns, "StatusMessage");
                response.setStatusMessage(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "StatusMessage");
            } else if (name.equals("ErrorMessageCode")) {
                parser.require(XmlPullParser.START_TAG, ns, "ErrorMessageCode");
                response.setErrorMessageCode(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "ErrorMessageCode");
            } else if (name.equals("ErrorMessage")) {
                parser.require(XmlPullParser.START_TAG, ns, "ErrorMessage");
                response.setErrorMessage(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "ErrorMessage");
            } else if (name.equals("TransactionStatus")) {
                parser.require(XmlPullParser.START_TAG, ns, "TransactionStatus");
                response.setTransactionStatus(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "TransactionStatus");
            } else if (name.equals("TransactionReference")) {
                parser.require(XmlPullParser.START_TAG, ns, "TransactionReference");
                response.setTransactionReference(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "TransactionReference");
            } else if (name.equals("Amount")) {
                parser.require(XmlPullParser.START_TAG, ns, "Amount");
                response.setAmount(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "Amount");
            } else if (name.equals("AmountFormatted")) {
                parser.require(XmlPullParser.START_TAG, ns, "AmountFormatted");
                response.setFormattedAmount(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "AmountFormatted");
            } else if (name.equals("CurrencyCode")) {
                parser.require(XmlPullParser.START_TAG, ns, "CurrencyCode");
                response.setCurrencyCode(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "CurrencyCode");
            } else if (name.equals("TransactionInitiationDate")) {
                parser.require(XmlPullParser.START_TAG, ns, "TransactionInitiationDate");
                response.setTransactionInitiationDate(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "TransactionInitiationDate");
            } else if (name.equals("TransactionCompletionDate")) {
                parser.require(XmlPullParser.START_TAG, ns, "TransactionCompletionDate");
                response.setTransactionCompletionDate(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "TransactionCompletionDate");
            } else if (name.equals("MNOTransactionReferenceId")) {
                parser.require(XmlPullParser.START_TAG, ns, "MNOTransactionReferenceId");
                response.setMNOTransactionReferenceId(readText(parser));
                parser.require(XmlPullParser.END_TAG, ns, "MNOTransactionReferenceId");
            } else {
                skip(parser);
            }
        }
        return response;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}

