<#macro layout pageTitle path>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width,user-scalable=no" />
<#include "/base/include.ftl"/>
<link rel="stylesheet" href="/static/css/business/${path}.css?random=${version!0}"></link>
<script src="/static/js/business/base/layout.js"></script>
<script src="/static/js/business/${path}.js?random=${version!0}"></script>
</head>

<body ng-app="layoutApp">
	<div class="content" ng-controller="contentController"><@title "${pageTitle}"/> <#nested/></div>
</body>
</html>

</#macro>
