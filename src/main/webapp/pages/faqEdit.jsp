<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>FAQ Edit</title>

<!-- Bootstrap -->
<link href="<%=path%>/app/bootstrap3/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<link href="<%=path%>/css/faqEdit.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=path%>/script/jquery-2.2.2.min.js"></script>
	<script type="text/javascript"
	src="<%=path%>/script/jquery.form.min.js"></script>

<script type="text/javascript"
	src="<%=path%>/app/bootstrap3/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=path%>/script/faq/faqEdit.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- editormd start -->
<link href="<%=path%>/app/editormd/css/editormd.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=path%>/app/editormd/editormd.min.js"></script>
<script type="text/javascript">
  var testEditor;
  
  testEditor=$(function() {
      editormd("test-editormd", {
           width   : "100%",
           height  : 680,
           //markdown : md,
           codeFold : true,
           syncScrolling : "single",
           //你的lib目录的路径
           path    : "<%=request.getContextPath()%>/app/editormd/lib/",
			imageUpload : false,//关闭图片上传功能
			/*  theme: "dark",//工具栏主题
			 previewTheme: "dark",//预览主题
			 editorTheme: "pastel-on-dark",//编辑主题 */
			emoji : false,
			taskList : true,
			tocm : true, // Using [TOCM]
			tex : true, // 开启科学公式TeX语言支持，默认关闭
			flowChart : true, // 开启流程图支持，默认关闭
			sequenceDiagram : true, // 开启时序/序列图支持，默认关闭,
			//这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
			saveHTMLToTextarea : true,
			toolbarIcons : function() {//自定义工具栏
				//return editormd.toolbarModes[name]; // full, simple, mini
				// Using "||" set icons align right.
				return [ "undo", "redo", "|", "bold", "del", "italic", "quote",
						"ucwords", "uppercase", "lowercase", "|", "h1", "h2",
						"h3", "h4", "h5", "h6", "|", "list-ul", "list-ol",
						"hr", "|", "link", "code", "code-block", "datetime",
						"||", "watch", "preview" ];
			},
		});

	});
</script>
<!-- editormd end -->

</head>

<body onload="init()">

	<!--页头  start -->
	<nav class="navbar navbar-default navbar-fixed-top"><!-- style="background:#B5CCDA" -->
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Home</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">FAQ</a></li>
					<li><a href="#">FaqEdit</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">账号...</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">个人 <span class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li><a href="#">批量导入/导出数据</a></li>
							<li><a href="#">切换账号</a></li>
							<li><a href="#">退出登录</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">更改用户名或者密码</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!--页头  end -->

	<div class="container">

		<!-- 表单  start-->

		<form class="form-horizontal" id="top-from" accept-charset="utf-8" name="faqedit" action="<%=basePath%>faq/faqEdit" method="post"  onsubmit="return checkinput()">

			<div class="form-group"> 
				 <label class="col-sm-2 control-label">分类 <span style="color:red">*</span></label> 
				<div class="col-sm-4">
					<select class="form-control" name="category1" id="category1" onchange="getcategory2()">
						<option>----请选择----</option>
						<option>第三方</option>
						<option>多媒体</option>

					</select>
				</div>

				<div class="col-sm-4 col-md-offset-2">
					<select class="form-control" name="category2" id="category2">
						
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">BugId</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="bugid"
						placeholder="7307">
				</div>
				<label for="inputPassword3" class="col-sm-2 control-label">平台</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="platform"
						placeholder="SP603_IN">
				</div>
			</div>

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">现象 <span style="color:red">*</span></label>
				<div class="col-sm-10">
					<input type="text" class="form-control input-lg" name="phenomenon" id="phenomenon"
						placeholder="在手机使用的过程中进场弹出Google Play无相应的提示">
				</div>
			</div>
			
			<input class="btn btn-info btn-lg pull-right btn-block" id="btn-submit" type="submit" value="提交">

			<!-- editormd start -->
			<div class="editormd" id="test-editormd">
				<textarea class="editormd-markdown-textarea form-control" name="editormd"
					id="editormd">
### 1.现象：


### 2.复现步骤：





### 3.分析：





###4.解决方案：






### 5.相关Bug：
    </textarea>
				<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
				<textarea class="editormd-html-textarea form-control" name="editorhtml"
					id="editorhtml"></textarea>
			</div>
			<!-- editormd end -->
		</form>
		<!-- 表单  end-->
		
		<hr style=" height:1px;border:none;border-top:1px dotted #185598;" />
		<footer>
			<p class="text-right">
				FAQ Edit <a href="#top">回到顶部提交</a>.
			</p>
		</footer>
	</div>

</body>
</html>
