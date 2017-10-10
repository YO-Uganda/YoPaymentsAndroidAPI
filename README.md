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
Initialize the library

```
YoPay yoAPI = new YoPay(YoAPIUsername, YoAPIPassword);
```

And that's it! You now have access to the library functions and can make mobile money payments programatically!


## A Simple Example

Have a user receive the mobile money USSD prompt after clicking a button.

```
Button payButton = (Button) findViewById(R.id.fab);
payButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        YoPay yoAPI = new YoPay(YoAPIUsername, YoAPIPassword);
        YoPaymentsResponse response = yoAPI.ac_deposit_funds("256701234567",1000,"Reason for payment");
        if(response.getStatus().equals("OK")){
            try {
                YoPaymentsResponse checkResponse = yoAPI.check_transaction(response.getTransactionReference());
                if(checkResponse.getTransactionStatus().equals("SUCCEEDED")){
                    // The payment was successful.
                    Log.i("YOPAY","The payment was successfully transferred to your Yo! Payments Account. "+response.toString());
                }else if(checkResponse.getTransactionStatus().equals("FAILED")){
                    // The payment failed.
                    Log.i("YOPAY","The payment failed. Funds were not transferred to your Yo! Payments Account. "+response.toString());
                }else if(checkResponse.getTransactionStatus().equals("PENDING")){
                    // The payment is still pending.
                    Log.i("YOPAY","The payment is still pending. Please check again later. "+response.toString());
                }else{
                    // The payment is indeterminate.
                    Log.i("YOPAY","The payment is indeterminate. Please contact Yo! Payments support. "+response.toString());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Log.e("YOPAY","There was an error transferring funds. Please try again later.");
        }
    }
});
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
