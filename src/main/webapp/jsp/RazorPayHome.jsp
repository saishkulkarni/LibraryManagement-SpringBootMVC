<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body{
text-align: center;
position :relative;
top:290px;
}
button{
        height: 30px;
        width: 100px;
        font-weight: bold;
        border-radius: 5px;
        box-shadow: 2px 2px 5px black;
        transition: all 1s linear;

    }
    button:hover{
        transform: scale(1.2,1.2);
    }
</style>
</head>
<body>
	<a href="/" style="text-decoration: none;"><button id="rzp-button1" style="width: 3.5%;height: 125%;position: relative;font-weight: 700;border-radius: 5px;">Pay</button></a>
	<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
	<script>
		var options = {
			"key" : "${details.getKeyDetails()}", // Enter the Key ID generated from the Dashboard
			"amount" : "${details.getAmount()}", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
			"currency" : "${details.getCurrency()}",
			"name" : "LibraryManagement", //your business name
			"description" : "Test Transaction",
			"image" : "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGCSMBVsMjV4ifuhwSkbcsMG2lqAWUKq8OMw&usqp=CAU",
			"order_id" : "${details.getOrderId()}", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
			"callback_url" : "/student/paymentSuccess",
			"prefill" : { //We recommend using the prefill parameter to auto-fill customer's contact information especially their phone number
				"name" : "${student.getName()}", //your customer's name
				"email" : "${student.getEmail()}",
				"contact" : "+91${student.getMobile()}" //Provide the customer's phone number for better conversion rates 
			},
			"notes" : {
				"address" : "Razorpay Corporate Office"
			},
			"theme" : {
				"color" : "#3399cc"
			}
		};
		var rzp1 = new Razorpay(options);
		document.getElementById('rzp-button1').onclick = function(e) {
			rzp1.open();
			e.preventDefault();
		}
	</script>
</body>
</html>