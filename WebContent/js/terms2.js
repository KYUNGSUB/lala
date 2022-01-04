$(function() {
	var service = false;
	var indivisual = false;
	var consignment = false;
	var marketing = false;
	var allagree = false;
	
	$("#service-check input").change(function() {
		service = this.checked;
	});
	$("#indivisual-check input").change(function() {
		indivisual = this.checked;
	});
	$("#consignment-check input").change(function() {
		consignment = this.checked;
	});
	$("#marketing-check input").change(function() {
		marketing = this.checked;
	});
	
	$("#allagree-check input").change(function() {
		allagree = this.checked;
		if(allagree) {
			service = indivisual = consignment = marketing = true;
			$("#service-check input").prop("checked", true);
			$("#indivisual-check input").prop("checked", true);
			$("#consignment-check input").prop("checked", true);
			$("#marketing-check input").prop("checked", true);
		} else {
			service = indivisual = consignment = marketing = false;
			$("#service-check input").prop("checked", false);
			$("#indivisual-check input").prop("checked", false);
			$("#consignment-check input").prop("checked", false);
			$("#marketing-check input").prop("checked", false);
		}
	});
	
	var agreeForm = $("form");
	$(".btnA[type=submit]").on("click", function(e) {
		e.preventDefault();
		if(!service) {	// 서비스 이용약관 동의를 하지 않은 경우
			alert("라라마켓 서비스 이용약관에 동의해 주세요!");
			return;
		}
		if(!indivisual) {	// 개인정보 처리방침 동의를 하지 않은 경우
			alert("개인정보 처리방침에 동의해 주세요!");
			return;
		}
		if(!consignment) {	// 개인정보 제3자 제공에 동의를 하지 않은 경우
			alert("개인정보 제3자 제공에 동의해 주세요!");
			return;
		}
		
		agreeForm.append("<input type='hidden' name='agreement' value='" + marketing + "'>");
		agreeForm.submit();
	});
	
	$(".btnA[type=button]").on("click", function() {
		var reply = confirm("회원 가입을 취소하시겠습니까?");
		if(reply) {
			location.href = "/index.do";
		}
	});
});