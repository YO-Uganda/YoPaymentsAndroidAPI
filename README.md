# Yo! Payments API Android Library

Yo! Payments is a revolutionary mobile payments gateway service. Yo! Payments enables businesses to receive payments from their customers via mobile money, as well as make mobile money payments to any mobile money account holder. Yo! Payments also has the capability to send mobile calling credit (“airtime”) directly to users. 

Yo! Payments API Android Library is an Android library that can be included in your Android project to enable seamless integration with your Android application.

## Getting Started

### Prerequisites

To use the API, you must, first of all, have a Yo! Payments Business Account. The API is not available for Personal Accounts

* Yo! Payments API Username
* Yo! Payments API Password

```
YoPay yoAPI = new YoPay(YoAPIUsername, YoAPIPassword);
```

### Installing

Add it to your build.gradle with:

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

and

```
dependencies {
    compile 'com.github.YO-Uganda:YoPaymentsAndroidAPI:1.0.0'
}
```
Add Internet Permissions to your Android Manifest file

```
<uses-permission android:name="android.permission.INTERNET"/>
```
Initialize the library

```
YoPay yoAPI = new YoPay(YoAPIUsername, YoAPIPassword);
```

And that's it! You now have access to the library functions and can make mobile money payments programatically!


## A Simple Example

Have a user receive the mobile money USSD prompt after clicking a button.

```
        @Override
        public void onClick(View v) {
            Log.i("YOPAY", "Here we go...");
            Log.i("YOPAY", "Clicked Button. Going to callYO now...");
            YoPay yoAPI = new YoPay("YoAPIUsername", "YoAPIPassword");
            yoAPI.setMsisdn("256712256785");
            yoAPI.setAmount(500);
            yoAPI.setNarrative("Reason for deposit of funds");
            try {
                YoPaymentsResponse response = yoAPI.execute("acdepositfunds").get();
                Log.i("YOPAY", "" + response.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
```

That's it! You should now be ready to use YoAPI

## Built With

* [Java](https://www.oracle.com/java/index.html) - Java Programming Language

## Authors

* **Aziz Kirumira** - *Initial work* - [Yo (U) Ltd](https://github.com/YO-Uganda)
* **Arnold Kunihira** - *Initial work* - [Yo (U) Ltd](https://github.com/YO-Uganda)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Gerald Begumisa
* Grace Kyeyune
* Joseph Tabajjwa
