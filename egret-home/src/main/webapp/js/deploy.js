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
																						.attr(
																								"class",
																								"bar bar-"
																										+ status);
																				$(
																						"#b_"
																								+ hostIndex
																								+ "_"
																								+ statusIndex)
																						.attr(
																								"rel",
																								"tooltip");
																				$(
																						"#b_"
																								+ hostIndex
																								+ "_"
																								+ statusIndex)
																						.attr(
																								"title",
																								status);
																				if (statusIndex == host.status.length - 1
																						&& (status == 'success'
																								|| status == 'warning' || status == 'failed')) {
																					$(
																							"#p_"
																									+ hostIndex)
																							.removeClass(
																									"active");
																					if (hostIndex == data.hosts.length - 1) {
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
													.append(
															"<span id=offset-"
																	+ data.offset
																	+ " class=\"terminal-like\">"
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