package ug.co.yo.yopay.yopaymentslibrary;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class YoCertificate {
    public InputStream getInputStream(){
        InputStream stream = null;
        stream = new ByteArrayInputStream(getCertificateString().getBytes());
        return stream;
    }
    private String getCertificateString(){
        return "MIIEvTCCA6WgAwIBAgIJAN3e7VqDg5zQMA0GCSqGSIb3DQEBBQUAMIGaMQswCQYD" +
                "VQQGEwJVRzEQMA4GA1UECBMHS2FtcGFsYTEQMA4GA1UEBxMHS2FtcGFsYTEbMBkG" +
                "A1UECgwSWW8hIFVnYW5kYSBMaW1pdGVkMRUwEwYDVQQLDAxZbyEgUGF5bWVudHMx" +
                "FTATBgNVBAMTDHd3dy55by5jby51ZzEcMBoGCSqGSIb3DQEJARYNaW5mb0B5by5j" +
                "by51ZzAeFw0xMzA4MDkwNTQyMTRaFw0yMzA4MDcwNTQyMTRaMIGaMQswCQYDVQQG" +
                "EwJVRzEQMA4GA1UECBMHS2FtcGFsYTEQMA4GA1UEBxMHS2FtcGFsYTEbMBkGA1UE" +
                "CgwSWW8hIFVnYW5kYSBMaW1pdGVkMRUwEwYDVQQLDAxZbyEgUGF5bWVudHMxFTAT" +
                "BgNVBAMTDHd3dy55by5jby51ZzEcMBoGCSqGSIb3DQEJARYNaW5mb0B5by5jby51" +
                "ZzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAPPo+N67Z56ebScXJ9tX" +
                "tFpSNBNNyDlqU/X8bqouZjWuvxpWOI4xZkPKXi0t205ooVbQL/+962NASjJRrouQ" +
                "IUhJq7xhwb+KKcWyFpA25742mNgaxeZJa9iofiHeKotBvHz6pswuqa2gXAyTTmYf" +
                "j6BOIFhDeUffOjfJYbzACy7WLtbK6VIRSTHypQY+zMQluw1euyY8524GYzf8E+c5" +
                "9qjIa5YY5PPianvvR25VDNRCm0Z6GPolhIGvYPUWHFZx+HtU8xoZumi5Kddvipew" +
                "uujxNVBRyQ8bVRoYxKKuDMFHiXA6V01oPzSOtfPK7JI+rd2JFU7dQgbFxTXI9+Qx" +
                "2yUCAwEAAaOCAQIwgf8wHQYDVR0OBBYEFPj0nwwE8lJByx243yV6cfXbTKbhMIHP" +
                "BgNVHSMEgccwgcSAFPj0nwwE8lJByx243yV6cfXbTKbhoYGgpIGdMIGaMQswCQYD" +
                "VQQGEwJVRzEQMA4GA1UECBMHS2FtcGFsYTEQMA4GA1UEBxMHS2FtcGFsYTEbMBkG" +
                "A1UECgwSWW8hIFVnYW5kYSBMaW1pdGVkMRUwEwYDVQQLDAxZbyEgUGF5bWVudHMx" +
                "FTATBgNVBAMTDHd3dy55by5jby51ZzEcMBoGCSqGSIb3DQEJARYNaW5mb0B5by5j" +
                "by51Z4IJAN3e7VqDg5zQMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADggEB" +
                "AGCaUMHBxGVtVsA8xMDWknjH6hV9yuca3s0qRrOoMfM7nyOjeYtUNgZlsLxuX2n3" +
                "FhoeK9DUBvIKVSlVfO5SXgsXyWKG54YFEkZ8D50Krsyl5NCfaAJezkQ0MNdtpG98" +
                "wlD/cYa6C6DC/s1eilUbI5QqaxLo+EFy5VuHQ8tAuxJbNTVPMW9GvTjxofeMUnug" +
                "SxUMDqHmEkzbQV7yCBVqf3yi4XOM4/6B7Tr6gaandpuR+v2XaKl4SOf8G5svn96g" +
                "Kn+Bk8p6rlBWAl+5hWxHWi4dkjiLsk8q+aeKh6ibwYtRjEt/sbWTgJAZjI1mTT8d" +
                "wsLYlL7k1O3wCjUeMQzi274=";
    }
}
