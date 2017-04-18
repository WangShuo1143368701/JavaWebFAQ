$(document).ready(function() {
	
	var options = {
			dataType: "json",           //html(默认), xml, script, json...接受服务端返回的类型
            //clearForm: true,          //成功提交后，清除所有表单元素的值
            //resetForm: true, 
	        success: function (data) {
	        	alert(data.text);
	        }
	    };
	
	   $('#top-from').submit(function() {     
	         // submit the form     
	         $(this).ajaxSubmit(options);     
	         // return false to prevent normal browser submit and page navigation     
	         return false; 
	        });   
});




function checkinput(){   
	var category1 = document.getElementById("category1").value;
	if(category1 ==  null || category1 == '' || category1 =="----请选择----"){
	alert("分类不能为空");
	return false;  
	}  
	var category2 = document.getElementById("category2").value;
	if(category2 ==  null || category2 == '' || category2 =="----请选择----"){
	alert("分类不能为空");
	return false;  
	}  
	var phenomenon = document.getElementById("phenomenon").value;
	if(phenomenon ==  null || phenomenon == ''){
	alert("现象不能为空");
	return false;  
	}
	var editormd = document.getElementById("editormd").value;
	if(editormd ==  null || editormd == ''){
	alert("编辑器内容不能为空");
	return false;  
	} 
	return true;
	}
//二级联动 start
var arr = new Array(); 
arr[0 ]="----请选择----";
arr[1 ]="Google Play services,Play store,Android system webview,Calendar";  
arr[2 ]="视频播放器,音乐播放器，FM,录音机";

function init() {    
	var category2 = document.getElementById("category2"); 
	var cityArr = arr[0].split(",");  
	for(var i=0;i<cityArr.length;i++)    {   
		category2[i]=new Option(cityArr[i],cityArr[i]);  
		}  
	getcategory2();
	};
	
	function getcategory2() {   
		var category1 = document.getElementById("category1");  
		var category2 = document.getElementById("category2");  
		var index = category1.selectedIndex;  
		var cityArr = arr[index].split(",");     
		category2.length = 0;        
		for(var i=0;i<cityArr.length;i++)    
		{     
			category2[i]=new Option(cityArr[i],cityArr[i]);    
		}   
		}   
	//二级联动 end