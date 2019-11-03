<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

	<#--边栏sidebar-->
	<#include "../common/nav.ftl">

	<#--主要内容content-->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table class="table table-bordered table-condensed">
						<thead>
						<tr>
							<th>图片id</th>
							<th>图片描述</th>
							<th>图片url</th>
							<th>创建时间</th>
							<th colspan="2">操作</th>
						</tr>
						</thead>
						<tbody>

						<#list pictures as picture>
							<tr>
								<td>${picture.picId}</td>
								<td>${picture.picMessage}</td>
								<td>${picture.picUrl}</td>
								<td>${picture.createTime}</td>
								<td><a href="/sell/picture/index?picId=${picture.picId}">修改</a></td>
								<td><a href="/sell/picture/delete?picId=${picture.picId}" onclick="return confirm('你确定要删除该图片吗？')">删除</a></td>
							</tr>
						</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>