/**
 * 
 */
function showHideMsg(msg){
	
	var $messageDiv = $('#message'); // get the reference of the div
	$messageDiv.show().html(msg); // show and set the message
	setTimeout(function(){ $messageDiv.hide().html('');}, 3000); // 3 seconds later, hide
    // and clear the message
}

$(document).ready(function(){
	   // do jQuery
	
	$('#name, #namee').keypress(function (e) {
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
	

	var validateEmail = function(elementValue) {
	    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	    return emailPattern.test(elementValue);
	}


var flag =false;
	$('#email, #emaile').keyup(function() {

	    var value = $(this).val();
	    var valid = validateEmail(value);

	    if (!valid) {


	        $(this).css('color', 'red');
	        	
	    } else {

	    	flag= true;
	        $(this).css('color', '#000');

	    }



	});
	
	
	$('#frm , #frmed').submit(function(e) {
		
		if ($("#name, #namee").val() == ''  ){		
			showHideMsg("name is empty");
			return false;
		} else if ($("#email , #emaile ").val() == '' ){
			showHideMsg("email is empty");
			return false;
		}else if(flag == false){
			showHideMsg("email is invalid");
			return false;
		}else{
			alert("record saved");
			return true;
		}
	});

	
	
	});

