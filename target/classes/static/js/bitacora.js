/**
 * 
 */


function showHideMsg(msg){
	
	var $messageDiv = $('#message'); // get the reference of the div
	$messageDiv.show().html(msg); // show and set the message
	setTimeout(function(){ $messageDiv.hide().html('');}, 3000); // 3 seconds later, hide
    // and clear the message
}

$('#frm').submit(function(e) {
	
	if($("#idgluc").val() == 0 || $("#idgluc").val() == ""){
		//alert("empty");
		showHideMsg("enter glucemia quantity");
		return false;
	}else if($("#idinsul").val() == 0 || $("#idinsul").val() == ""){
		showHideMsg("enter insulin quantity");
		return false;
	}
	else if($("#datepicker").val() == null || $("#datepicker").val() == ""){
		showHideMsg("enter date");
		return false;
	}
	else if($("#idtime").val() == null || $("#idtime").val() == ""){
		showHideMsg("enter time");
		return false;
	}
	
	
	
	
	
});


$(document).ready(function() {
    $('#insulrecord').DataTable( {
        "pagingType": "full_numbers"
    } );
} );



$('#datepicker').datepicker({
    format: 'yyyy-mm-dd'
});


$('#idtime').datetimepicker({
	 format: 'yyyy-mm-dd HH:MM:ss'
});

function showHideMsg(msg){
	
	var $messageDiv = $('#message'); // get the reference of the div
	$messageDiv.show().html(msg); // show and set the message
	setTimeout(function(){ $messageDiv.hide().html('');}, 3000); // 3 seconds later, hide
    // and clear the message
}


$('#datepicker').keypress(function (e) {
	e.preventDefault();
	//showHideMsg('Please Select Date');
    return false;
    
});

function dateFilter(){

	var data = {}
//	data["nombreUsr"] = $("#nombreUsr").val();
	
	if ($("#datepicker").val() == null)
		data["datepicker"] ="";
	else
		data["datepicker"] = $("#datepicker").val();
	//console.log("data -->>"+$("#nombreUsr").val() + " " +$("#datepicker").val());
	//alert("data -->>"+$("#nombreUsr").val() + " " +$("#datepicker").val());
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/dateFilter",
		data : JSON.stringify(data),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			//alert(data["msg"]);
			if (data["msg"] == 'error')
				showHideMsg("User's not registered ");
			else if (data["msg"] == 'ok'){
				//alert(data["msg"]);
				window.location = "/history";
			}
				//window.location = "/history";
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