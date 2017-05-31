<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Error">
	<p><#if status??>Status: ${status}</#if> <#if error??>Error: ${error}</#if> <#if message??>Message: ${message}</#if></p>
</@layout.masterTemplate>
