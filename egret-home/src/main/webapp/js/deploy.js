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
										$(".progress").removeClass("active");
										stopTimer();
									},
									success : function(data) {
										console.log(data);
										var finished;
										$
												.each(
														data.hosts,
														function(hostIndex,
																host) {
															$("#p_" + hostIndex)
																	.addClass(
																			"active");
															$
																	.each(
																			host.status,
																			function(
																					statusIndex,
																					status) {
																				$(
																						"#b_"
																								+ hostIndex
																								+ "_"
																								+ statusIndex)
																						.addClass(
																								"bar-"
																										+ status);
																				if (statusIndex == host.status.length
																						&& (status == 'success'
																								|| status == 'warning' || status == 'failed')) {
																					$(
																							"#p_"
																									+ hostIndex)
																							.removeClass(
																									"active");
																					if (hostIndex == data.hosts.length) {
																						finished = true;
																					}
																				}
																			});
														});

										if (finished) {
											showResult("success",
													"Deploy Completed");
											$(".progress")
													.removeClass("active");
											stopTimer();
										}

										$("#offset").val(data.offset);

										if (data.content) {
											$("#status")
													.prepend(
															"<span id=offset-"
																	+ data.offset
																	+ " class=\"label label-inverse\">"
																	+ data.content
																	+ "</span>");
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