/**
 * 
 */

$('#datepicker').datepicker({
    format: 'yyyy-mm-dd'
});


$('.location-region-picker').LocationRegionPicker({
	  'google_api_key' : 'AIzaSyD0FKiBrSpNNjryMKg667J8rnSqq7o9CK4',
	  'types': '(regions)'
	});

function showHideMsg(msg){
	
	var $messageDiv = $('#message'); // get the reference of the div
	$messageDiv.show().html(msg); // show and set the message
	setTimeout(function(){ $messageDiv.hide().html('');}, 3000); // 3 seconds later, hide
    // and clear the message
}

$(document).ready(function(){
	$('#frm').submit(function(e) {
		
		//if ($("#iduser").val() != '' ){		

			$('#iduser , #idpwd, #location').keypress(function (e) {
		        var regex = new RegExp("^[a-zA-Z\s]+$");
		        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		        if (regex.test(str)) {
		            return true;
		        }
		        else
		        {
		        e.preventDefault();
		        showHideMsg('Please Enter Alphabate');
		        return false;
		        }
		    });
			
			$('#datepicker').keypress(function (e) {
				e.preventDefault();
				showHideMsg('Please Select Date');
		        return false;
		        
		    });
			
			$('#idedad').keypress(function (e) {
				
				var regex = /^\d+$/;
				
				
				 var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
			        if (regex.test(str)) {
			            return true;
			        }
			        else
			        {
			        e.preventDefault();
			        showHideMsg('Please Enter Number');
			        return false;
			        }
				
				
			  });
			
			
			
	//	}
		if ($("#iduser").val() == ''){
			showHideMsg("usuario is empty");
			return false;
		}
		
		if ($("#idpwd").val() == ''  ){
			showHideMsg("password is empty");
			return false;			
		}else if ($("#idedad").val() <= 8  && $("#idedad").val() < 100){
			showHideMsg("edad must be between 8-100");
			return false;
		}else if ($("#datepicker").val() ==''){
			showHideMsg("date is empty");
			return false;								
		}else if ($("#location").val() ==''){
			showHideMsg("location is empty");
			return false;												
		}else {
			
			return true;
		}
		
		
	});
	
});

function login(){

	var data = {}
	data["user"] = $("#userid").val();
	data["pass"] = $("#passid").val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/checkUsr",
		data : JSON.stringify(data),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			//alert(data["msg"]);
			if (data["msg"] == 'error')
				showHideMsg("User's not registered ");
			else if (data["msg"] == 'exists')
				window.location = "/menu?name="+$("#userid").val();
		},
		error : function(e) {
			console.log("ERROR: ", e);
			
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	
	//window.location = "/greeting";

	
	
}