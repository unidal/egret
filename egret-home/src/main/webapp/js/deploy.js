function updateDeployStatus() {
	$(document)
			.ready(
					function() {
						$
								.ajax({
									type : "GET",
									url : "?op=log",
									dataType : "json",
									data : {
										offset : $("#offset").val(),
										plan : $("#plan").val()
									},
									error : function(xhRequest, ErrorText,
											thrownError) {
										console.log(xhRequest);
										
										console.log(ErrorText);
										console.log(thrownError);
										showResult("error", ErrorText);
										stopTimer();
									},
									success : function(data) {
										// $("#progressbar").css("width",
										// data.progress + "%");
										console.log(data);
										$("#p_" + data.index)
												.addClass("active");
										if (data.index > 0) {
											$("#p_" + (data.index - 1))
													.removeClass("active");
										}
										$("#b_" + data.index + "_" + data.step)
												.addClass("bar-" + data.status);
										
										if (data.progress >= 100) {
											showResult("success",
													"Deploy Completed");
											$("#b_" + data.index).removeClass(
													"active");
											stopTimer();
										}

										$("#offset").val(data.offset);

										if (data.content) {
											$("#status")
													.prepend(
															"<p id="
																	+ data.offset
																	+ "><span class=\"label label-inverse\">"
																	+ data.content
																	+ "</span></p>");
//											new TypingText(
//													document
//															.getElementById(data.offset));
										}
									}
								});
					});
}
var interval = setInterval('updateDeployStatus()', 1000);
function stopTimer() {
	clearInterval(interval);
} 

function showResult(type, content) {
	$("#result").html(
			"<div id='alert' class='alert alert-" + type + "'>" + content
					+ "</div>");
	$("#result").show();
}