/**
 * 自定义JQuery validate验证规则
 */

/* validator自定义验证相关的设置以及验证方法注册 */
jQuery(document).bind("pageinit", function(){
  /* 注册自定义验证函数 */
	regUserValidateMethods(jQuery);
	/* 注册自定义类规则 */
	regUserClassRules(jQuery);
});

/* jQuery Validation框架的自定义类规则在此添加 */
function regUserClassRules($) {
  /* 带精度小数验证 */
	$.validator.addClassRules("decimal", {
		isDecimal: function(element){
//			alert($(arg1).parent().html());
			var jqElement = $(element);
			var min = jqElement.attr("min");
			var max = jqElement.attr("max");
			var accuracy = jqElement.attr("accuracy");
			return [min, max, accuracy];
		}
	});
}

function regUserValidateMethods($) {
	/* 小数验证，小数点位数按照max参数的小数点位数进行判断
	 * 不能为空、只能输入数字 */
	$.validator.addMethod("isDecimal", function(value, element, params) {
		if(isNaN(params[0])) {
			console.error("参数错误，decimal验证的min只能为数字");
			return false;
		}
		if(isNaN(params[1])) {
			console.error("参数错误，decimal验证的max只能为数字");
			return false;
		}
		if(isNaN(params[2])) {
			console.error("参数错误，decimal验证的accuracy只能为数字");
			return false;
		}
		if(isNaN(value)) {
			return false;
		}
		if(typeof(value) == undefined || value == "") {
			return false;
		}
		var min = Number(params[0]);
		var max = Number(params[1]);
		var testVal = Number(value);
		if(typeof(params[2]) == undefined || params[2] == 0) {
			var regX = /^\d+$/;
		} else {
			var regxStr = "^\\d+(\\.\\d{1,"+params[2]+"})?$";
			var regX = new RegExp(regxStr);
		}
//		console.debug("regX: %o, value: %o, test: %o", regX, value, regX.test(value));
	    return this.optional(element) || (regX.test(value) && testVal >= min && testVal <= max);
	 }, $.validator.format("请正确输入在{0}到{1}之间，最多只保留小数点后{2}的数值"));
}